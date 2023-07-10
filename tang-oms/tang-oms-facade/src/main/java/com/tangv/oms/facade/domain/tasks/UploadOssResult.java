/**
 * shipper.id Inc.
 * Copyright (c) 2018-2022 All Rights Reserved.
 */
package com.tangv.oms.facade.domain.tasks;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * @author tang wei
 * @version StreamTskResult.java, v 0.1 2023/7/10 12:01 tang wei Exp $
 */
@Data
public class UploadOssResult extends TaskResultInfo implements Serializable {

    private String fileId;

}
