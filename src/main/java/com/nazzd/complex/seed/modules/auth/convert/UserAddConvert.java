package com.nazzd.complex.seed.modules.auth.convert;

import com.nazzd.complex.seed.modules.auth.bo.UserAddAndUpdate;
import com.nazzd.complex.seed.common.BaseConvert;
import com.nazzd.complex.seed.modules.auth.po.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserAddConvert extends BaseConvert<UserAddAndUpdate, User> {
}
