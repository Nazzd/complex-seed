package com.nazzd.complex.seed.drools;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public enum RuleTypeEnum {

    TRADE1_SECRET(1, "com.nazzd.trade", "trade-secret", "1.0.1"),
    TRADE2_SECRET(2, "com.nazzd.trade", "trade-secret", "1.0.2"),
    NATIONAL_SECRET(3, "com.nazzd.national", "national-secret", "1.0.1");

    private Integer type;

    private String groupId;

    private String artifactId;

    private String version;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getArtifactId() {
        return artifactId;
    }

    public void setArtifactId(String artifactId) {
        this.artifactId = artifactId;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    RuleTypeEnum(int type, String groupId, String artifactId, String version) {
        this.type = type;
        this.groupId = groupId;
        this.artifactId = artifactId;
        this.version = version;
    }

    public static final Map<Integer, RuleTypeEnum> cache = new ConcurrentHashMap<>();

    static {
        for (RuleTypeEnum ele : values()) {
            cache.put(ele.getType(), ele);
        }
    }

    public static RuleTypeEnum fromInt(Integer type) {
        return cache.get(type);
    }
}
