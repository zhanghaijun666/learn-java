package com.mybatisv3.sqlsession;

import java.util.List;
import java.util.Map;

public interface SqlSession {
    /**
     * 根据参数查询信息列表
     * @param s
     * @param param
     * @param <T>
     * @return
     */
    <T> List<T> selectList(String s, Map param);

    /**
     * 根据参数查询单个信息
     * @param s
     * @param param
     * @param <T>
     * @return
     */
    <T> T selectOne(String s, Map param);
}
