package com.nazzd.complex.seed.load;

import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.injector.DefaultSqlInjector;

import java.util.List;

public class GeneralMybatisPlusSqlInjector extends DefaultSqlInjector {

    @Override
    public List<AbstractMethod> getMethodList(Class<?> mapperClass) {

        List<AbstractMethod> methodList = super.getMethodList(mapperClass);

        methodList.add(new CustomLoad());
        return methodList;
    }

}
