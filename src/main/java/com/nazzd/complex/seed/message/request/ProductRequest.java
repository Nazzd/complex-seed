package com.nazzd.complex.seed.message.request;

import com.nazzd.complex.seed.message.common.AbstractMessageRequest;
import com.nazzd.complex.seed.message.enums.ServiceTypeEnum;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductRequest extends AbstractMessageRequest {

    private Long id;

    private String name;

    private Long brandId;

    private String brandName;

    private BigDecimal price;

    private Integer stock;

    @Override
    public int getServiceType() {
        return ServiceTypeEnum.PRODUCT.getServiceType();
    }
}
