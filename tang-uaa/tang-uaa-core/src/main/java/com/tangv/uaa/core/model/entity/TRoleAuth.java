package com.tangv.uaa.core.model.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class TRoleAuth implements Serializable {

    @TableId("role_auth_id")
    private Long roleAuthId;

    private Long roleId;

    private Long authId;

    private Boolean disabled;

    private Boolean deleted;

    private Date createTime;

    private Date modifyTime;

    private static final long serialVersionUID = 1L;
}