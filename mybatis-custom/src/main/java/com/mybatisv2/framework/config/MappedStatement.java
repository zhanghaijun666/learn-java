package com.mybatisv2.framework.config;


import com.mybatisv2.framework.sqlsource.SqlSource;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MappedStatement {
    private String statementId;

    private String statementType;

    private String resultType;

    private Class resultTypeClass;

    private SqlSource sqlSource;

    public MappedStatement(String statementId, Class resultTypeClass,String statementType,  SqlSource sqlSource) {
        this.statementId = statementId;
        this.statementType = statementType;
        this.resultTypeClass = resultTypeClass;
        this.sqlSource = sqlSource;
    }
}
