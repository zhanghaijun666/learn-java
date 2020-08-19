package com.custom.spring.factory;

import java.util.List;

/**
 * 对于Bean容器中的Bean可以进行集合操作或者说叫批量操作
 */
public interface ListableBeanFactory extends BeanFactory{
    /**
     * 可以根据指定类型回去它或者它实现类的对象
     * @param type
     * @return
     */
    List<Object> getBeansByType(Class type);
}
