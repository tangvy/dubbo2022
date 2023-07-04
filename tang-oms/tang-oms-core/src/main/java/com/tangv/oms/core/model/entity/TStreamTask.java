package com.tangv.oms.core.model.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class TStreamTask implements Serializable {

    @TableId("task_id")
    private Long taskId;

    private String taskType;

    private String bizType;

    private String taskName;

    private String param;

    private Integer taskStatus;

    private String fileId;

    private String remark;

    private Date nextRequestTime;

    private Double costTime;

    private Integer version;

    private Boolean disabled;

    private Date createTime;

    private Date modifyTime;

    private static final long serialVersionUID = 1L;

}