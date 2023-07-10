/**
 * shipper.id Inc.
 * Copyright (c) 2018-2022 All Rights Reserved.
 */
package com.tangv.oms.core.service.impl;

import cn.hutool.core.util.StrUtil;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.builder.ExcelWriterSheetBuilder;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.aliyun.oss.OSS;
import com.aliyun.oss.model.AppendObjectRequest;
import com.aliyun.oss.model.AppendObjectResult;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.PutObjectResult;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tangv.oms.api.order.OmsOrderService;
import com.tangv.oms.core.consts.CommonConstants;
import com.tangv.oms.core.dao.OmsOrderMapper;
import com.tangv.oms.core.model.entity.OmsOrder;
import com.tangv.oms.facade.domain.order.dto.OrderDownloadDto;
import com.tangv.oms.facade.domain.order.vo.OrderVo;
import com.tangv.oms.facade.domain.tasks.UploadOssResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.BeanUtils;
import org.springframework.core.annotation.Order;

import javax.annotation.Resource;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

/**
 * @author tang wei
 * @version OmsOrderServiceImpl.java, v 0.1 2023/3/26 21:10 tang wei Exp $
 */
@Slf4j
@DubboService
public class OmsOrderServiceImpl implements OmsOrderService {

    private static final int QUERY_MAX_SIZE = 200;

    private static final String PATH = "/Users/tangwei/temp_file/";

    @Resource
    private OmsOrderMapper omsOrderMapper;

    @Resource
    private ExecutorService taskPoolExecutor;

    @Resource
    private OSS ossClient;

    @Override
    public OrderVo queryOrderByCode(String orderCode) {
        OrderVo orderVo = new OrderVo();
        OmsOrder omsOrder = omsOrderMapper.selectById(12);
        BeanUtils.copyProperties(omsOrder, orderVo);
        return orderVo;
    }

    @Override
    public UploadOssResult downloadOrder(OrderDownloadDto orderDownloadDto) {
        Integer type = orderDownloadDto.getType();
        String fileName = PATH + "ORDER-" + System.currentTimeMillis() + ".xlsx";
        ExcelWriter excelWriter = EasyExcel.write(fileName, OrderVo.class).build();
        WriteSheet writeSheet = EasyExcel.writerSheet().sheetName("SHEET1").sheetNo(1).build();
        return uploadToOss(orderDownloadDto);
        /*try {
            if (type == null) {
                uploadToOss(orderDownloadDto);
                return;
            }
            switch (type) {
                case 1:
                    download1(orderDownloadDto, excelWriter, writeSheet);
                    break;
                case 2:
                    download2(orderDownloadDto, excelWriter, writeSheet);
                    break;
                case 3:
                    download3(orderDownloadDto, excelWriter, writeSheet);
                    break;
            }
        } finally {
            excelWriter.finish();
        }*/
    }

    private void download1(OrderDownloadDto orderDownloadDto, ExcelWriter excelWriter, WriteSheet writeSheet) {
        PageInfo<OrderVo> orderVoPageInfo = queryPage(orderDownloadDto, Integer.MAX_VALUE, 1);
        List<OrderVo> orderVoList = orderVoPageInfo.getList();
        excelWriter.write(orderVoList, writeSheet);
    }

    private void download2(OrderDownloadDto orderDownloadDto, ExcelWriter excelWriter, WriteSheet writeSheet) {
        int countOrder = countOrder(orderDownloadDto);
        int size = QUERY_MAX_SIZE;
        int loop = countOrder/size;
        boolean remainder = countOrder % size > 0;
        if (remainder) {
            loop = loop + 1;
        }

        List<Future<PageInfo<OrderVo>>> futureList = new ArrayList<>(loop);
        for (int each = 0; each < loop; each++) {
            int finalEach = each;
            if (remainder && finalEach == loop - 1) {
                size = countOrder % size;
            }
            int finalSize = size;
            Future<PageInfo<OrderVo>> pageInfoFuture = taskPoolExecutor.submit(() -> {
                PageInfo<OrderVo> orderVoPageInfo = queryPage(orderDownloadDto, finalSize, finalEach + 1);
                return orderVoPageInfo;
            });
            futureList.add(pageInfoFuture);
        }
        for (Future<PageInfo<OrderVo>> pageInfoFuture : futureList) {
            try {
                PageInfo<OrderVo> orderVoPageInfo = pageInfoFuture.get();
                List<OrderVo> orderVoList = orderVoPageInfo.getList();
                excelWriter.write(orderVoList, writeSheet);
            } catch (Exception e) {
                log.error("查询失败", e);
                e.printStackTrace();
            }
        }
    }

    private void download3(OrderDownloadDto orderDownloadDto, ExcelWriter excelWriter, WriteSheet writeSheet) {
        int countOrder = countOrder(orderDownloadDto);
        int size = QUERY_MAX_SIZE;
        int loop = countOrder/size;
        boolean remainder = countOrder % size > 0;
        if (remainder) {
            loop = loop + 1;
        }

        for (int each = 0; each < loop; each++) {
            int finalEach = each;
            if (remainder && finalEach == loop - 1) {
                size = countOrder % size;
            }
            int finalSize = size;
            PageInfo<OrderVo> orderVoPageInfo = queryPage(orderDownloadDto, finalSize, finalEach + 1);
            List<OrderVo> orderVoList = orderVoPageInfo.getList();
            WriteSheet writeSheetEach = EasyExcel.writerSheet().sheetName("SHEET" + (each + 1)).sheetNo(each + 1).build();
            excelWriter.write(orderVoList, writeSheetEach);
        }
    }

    private UploadOssResult uploadToOss(OrderDownloadDto orderDownloadDto) {
        UploadOssResult uploadOssResult = new UploadOssResult();
        uploadOssResult.setIsSuccess(false);
        int countOrder = countOrder(orderDownloadDto);
        int size = QUERY_MAX_SIZE;
        int loop = countOrder/size;
        boolean remainder = countOrder % size > 0;
        if (remainder) {
            loop = loop + 1;
        }

        String fileName = "excels/" + "ORDER-" + System.currentTimeMillis() + ".xlsx";
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try (ExcelWriter excelWriter = EasyExcel.write(outputStream, Order.class).build()) {
            WriteSheet writeSheet = EasyExcel.writerSheet(0).build();
            for (int each = 0; each < loop; each++) {
                int finalEach = each;
                if (remainder && finalEach == loop - 1) {
                    size = countOrder % size;
                }
                int finalSize = size;
                PageInfo<OrderVo> orderVoPageInfo = queryPage(orderDownloadDto, finalSize, finalEach + 1);
                List<OrderVo> orderVoList = orderVoPageInfo.getList();
                writeSheet.setSheetNo(each);
                writeSheet.setSheetName("sheet" + writeSheet.getSheetNo());
                excelWriter.write(orderVoList, writeSheet);
            }
        }
        InputStream inputStream = new ByteArrayInputStream(outputStream.toByteArray());
        PutObjectResult putObjectResult = ossClient.putObject(CommonConstants.BUCKET, fileName, inputStream);
        uploadOssResult.setIsSuccess(true);
        uploadOssResult.setFileId(CommonConstants.OSS_FILE_PREFIX + fileName);
        try {
            inputStream.close();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return uploadOssResult;
    }

    @Override
    public Integer countOrder(OrderDownloadDto orderDownloadDto) {
        Long selectCount = omsOrderMapper.selectCount(Wrappers.lambdaQuery(OmsOrder.class)
                .eq(OmsOrder::getOwnerCode, orderDownloadDto.getOwnerCode()));
        return selectCount.intValue();
    }

    @Override
    public PageInfo<OrderVo> queryPage(OrderDownloadDto orderDownloadDto, Integer pageSize, Integer pageNum) {
        Page<OmsOrder> omsOrderList =
                PageHelper.startPage(pageNum, pageSize).doSelectPage(() -> {
                    omsOrderMapper.selectList(
                            Wrappers.lambdaQuery(OmsOrder.class)
                                    .eq(OmsOrder::getOwnerCode, orderDownloadDto.getOwnerCode()));
                });
        List<OrderVo> orderVoList = omsOrderList.stream().map(order -> {
            OrderVo orderVo = new OrderVo();
            BeanUtils.copyProperties(order, orderVo);
            return orderVo;
        }).collect(Collectors.toList());
        return PageInfo.of(orderVoList);
    }
}
