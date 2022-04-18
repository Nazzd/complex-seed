package com.nazzd.complex.seed.service;

import java.util.List;
import java.util.Map;

public interface LoadService {

    List<Map<String, Object>> queryMaps(String sql);

    int healthCheck();
}
