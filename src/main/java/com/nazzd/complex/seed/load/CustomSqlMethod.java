package com.nazzd.complex.seed.load;

public enum CustomSqlMethod {

    LOAD_DATA("loadData", "加载数据", "<script>%s SELECT %s FROM %s %s %s %s\n</script>"),
    ;

    private final String method;
    private final String desc;
    private final String sql;

    private CustomSqlMethod(String method, String desc, String sql) {
        this.method = method;
        this.desc = desc;
        this.sql = sql;
    }

    public String getMethod() {
        return this.method;
    }

    public String getDesc() {
        return this.desc;
    }

    public String getSql() {
        return this.sql;
    }
}
