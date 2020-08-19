package com.proxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class ProxyCglibTest {
    public static void main(String[] args) {
        //在指定目录下生成动态代理类，我们可以反编译看一下里面到底是一些什么东西
        //System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "D:\\proxy_workapace");

        //创建Enhancer对象
        Enhancer enhancer = new Enhancer();
        //设置目标类的字节码文件
        enhancer.setSuperclass(Dao.class);
        //设置回调函数
        enhancer.setCallback(new ProxyDao());

        //这里的creat方法就是正式创建代理类
        Dao proxyDog = (Dao) enhancer.create();
        proxyDog.select();
        proxyDog.update();
    }
}

class ProxyDao implements MethodInterceptor {
    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        System.out.println("======插入前置通知======");
        Object object = proxy.invokeSuper(obj, args);
        System.out.println("======插入后置通知======");
        return object;
    }
}
class Dao {
    public Dao() {
        System.out.println("Dao.构造器方法");
    }

    public void update() {
        System.out.println("Dao.update()");
    }

    public void select() {
        System.out.println("Dao.select()");
    }
}
