package com.nazzd.complex.seed.convert;

import com.nazzd.complex.seed.bo.UserAdd;
import com.nazzd.complex.seed.common.BaseConvert;
import com.nazzd.complex.seed.po.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserAddConvert extends BaseConvert<UserAdd, User> {
}
