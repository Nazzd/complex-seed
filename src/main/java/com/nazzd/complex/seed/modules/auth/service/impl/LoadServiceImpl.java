package com.nazzd.complex.seed.modules.auth.service.impl;

import com.nazzd.complex.seed.modules.auth.mapper.CustomMapper;
import com.nazzd.complex.seed.modules.auth.service.LoadService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class LoadServiceImpl implements LoadService {

    @Resource
    private CustomMapper customMapper;

    @Override
    public List<Map<String, Object>> queryMaps(String sql) {
        return customMapper.queryMaps(sql);
    }

    @Override
    public int healthCheck() {
      return customMapper.healthCheck();
    }


}
