package com.nazzd.complex.seed.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.Valid;

@ApiModel(value = "用户新增和更新的对象")
@Data
public class UserAddAndUpdate {

    @ApiModelProperty(value = "姓名",required = true)
    private String name;

    @ApiModelProperty(value = "描述")
    private String description;

    @ApiModelProperty(value = "是否启用",required = true)
    private Boolean isEnable;
}
