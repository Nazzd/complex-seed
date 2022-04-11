package com.nazzd.complex.seed.utils;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Slf4j
public class DataConvertUtil {
    /**
     * List<Map<String, Object>> 到 List<T> 数据转换
     */
    public static <T> List<T> toList(List<Map<String, Object>> srcList, Class<T> clazz) {
        List<T> list = new ArrayList<>();
        srcList.forEach(x -> {
            try {
                T t = clazz.newInstance();
                Field[] fields = t.getClass().getDeclaredFields();
                for (Field field : fields) {
                    if (!"serialVersionUID".equals(field.getName())) {
                        //设置对象的访问权限，保证对private的属性的访问
                        field.setAccessible(true);
                        //读取配置转换字段名，并从map中取出数据
                        Object v = x.get(field.getName());
                        field.set(t, convert(v, field.getType()));
                    }
                }
                list.add(t);
            } catch (Exception ex) {
                log.error(ex.toString());
            }
        });
        return list;
    }

    /**
     * Field类型转换
     */
    private static <T> T convert(Object obj, Class<T> type) {
        if (obj != null && StringUtils.isNotBlank(obj.toString())) {
            if (type.equals(String.class)) {
                return (T) obj.toString();
            } else if (type.equals(BigDecimal.class)) {
                return (T) new BigDecimal(obj.toString());
            } else if (type.equals(Double.class)) {
                return (T) Double.valueOf(obj.toString());
            } else if (type.equals(Integer.class)) {
                return (T) Integer.valueOf(obj.toString());
            } else if (type.equals(Date.class)) {
                return (T) new Date(obj.toString());
            } else if (type.equals(Long.class)) {
                return (T) Long.valueOf(obj.toString());
            } else if (type.equals(Boolean.class)) {
                return (T) Boolean.valueOf(obj.toString());
            } else {
                //其他类型转换
                return (T) obj.toString();
            }
        }
        return null;
    }
}