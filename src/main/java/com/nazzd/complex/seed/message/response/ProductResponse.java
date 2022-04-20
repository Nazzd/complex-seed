package com.nazzd.complex.seed.message.response;

import com.nazzd.complex.seed.message.common.AbstractMessageResponse;
import com.nazzd.complex.seed.message.enums.ServiceTypeEnum;

public class ProductResponse extends AbstractMessageResponse {

    @Override
    public int getServiceType() {
        return ServiceTypeEnum.PRODUCT.getServiceType();
    }
}
