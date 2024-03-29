package com.tangv.common.base.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.tangv.common.annotations.IPojo;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Description:
 * Author:      TangWei
 * Date:        2020/9/12 9:53 下午
 */
@Data
public class BasePojo implements IPojo, Serializable {

    @TableId(value = "id")
    private Long id;

    private LocalDateTime createTime;

    private LocalDateTime modifyTime;

    private Boolean isDelete;

    @Override
    public void prepareInsert() {
        this.setCreateTime(LocalDateTime.now());
        this.setModifyTime(LocalDateTime.now());
    }

    @Override
    public void prepareUpdate() {
        this.setModifyTime(LocalDateTime.now());
    }
}
