package com.nazzd.complex.seed.controller;

import com.nazzd.complex.seed.load.AbstractResult;
import com.nazzd.complex.seed.load.LoadDataEnum;
import com.nazzd.complex.seed.load.Student;
import com.nazzd.complex.seed.load.TestMapper;
import com.nazzd.complex.seed.mapper.CustomMapper;
import com.nazzd.complex.seed.utils.DataConvertUtil;
import lombok.Data;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/custom")
public class CustomController {

    @Resource
    private CustomMapper customMapper;

    @GetMapping("/test")
    public void test() {
        List<Map<String, Object>> maps = customMapper.queryMaps(LoadDataEnum.LOAD_STUDENT_CONFIG);

        List<Student> students = DataConvertUtil.toList(maps, Student.class);
        System.out.println(maps);

        System.out.println(students);
    }
}
