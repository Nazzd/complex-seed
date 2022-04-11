package com.nazzd.complex.seed.load;

public enum LoadDataEnum {


    LOAD_STUDENT_CONFIG(1, "select * from t_student");

    private Integer serviceType;

    private String sql;

    public Integer getServiceType() {
        return serviceType;
    }

    public void setServiceType(Integer serviceType) {
        this.serviceType = serviceType;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    LoadDataEnum(Integer serviceType, String sql) {
        this.sql = sql;
        this.serviceType = serviceType;
    }
}
