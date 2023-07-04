package com.tangv.oms.core.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tangv.oms.core.model.entity.TStreamTask;
import com.tangv.oms.facade.domain.order.vo.TStreamTaskVo;

import java.util.Date;
import java.util.List;

public interface TStreamTaskMapper extends BaseMapper<TStreamTask> {

    List<TStreamTaskVo> findWaitTasks(List<String> taskTypes, int limit, boolean locked);

    int updateStatusByIds(List<Long> taskIdList, Integer prevStatus, Integer targetStatus);

    int updateStatusById(Long taskId, Integer targetStatus, String remark, Double cost, Date nextRequestTime, Integer version);

}