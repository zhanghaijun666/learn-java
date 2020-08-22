package com.mybatisv2.framework.config;

import com.mybatisv2.framework.sqlsource.SqlSourceV2;

/**
 * 封装了statement标签中的信息
 */
public class MappedStatementV2 {
    private String statementId;

    private String statementType;

    private String resultType;

    private Class resultTypeClass;

    private SqlSourceV2 sqlSource;

    public MappedStatementV2(String statementId, Class resultTypeClass, String statementType, SqlSourceV2 sqlSource) {
        this.statementId = statementId;
        this.statementType = statementType;
        this.resultTypeClass = resultTypeClass;
        this.sqlSource = sqlSource;
    }

    public String getStatementId() {
        return statementId;
    }

    public void setStatementId(String statementId) {
        this.statementId = statementId;
    }

    public String getStatementType() {
        return statementType;
    }

    public void setStatementType(String statementType) {
        this.statementType = statementType;
    }

    public String getResultType() {
        return resultType;
    }

    public void setResultType(String resultType) {
        this.resultType = resultType;
    }

    public Class getResultTypeClass() {
        return resultTypeClass;
    }

    public void setResultTypeClass(Class resultTypeClass) {
        this.resultTypeClass = resultTypeClass;
    }

    public SqlSourceV2 getSqlSource() {
        return sqlSource;
    }

    public void setSqlSource(SqlSourceV2 sqlSource) {
        this.sqlSource = sqlSource;
    }
}
