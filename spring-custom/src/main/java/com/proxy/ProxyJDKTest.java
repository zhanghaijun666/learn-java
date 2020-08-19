package com.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyJDKTest {
    public static void main(String[] args) throws Exception {
        ProxyJDK proxy = new ProxyJDK(new ProxyTarget());
        ProxyInterface proxyInterface = (ProxyInterface) proxy.invoke();
        System.out.println(proxyInterface.getClass());
        proxyInterface.targetMethod();

//    byte[] bytes = ProxyGenerator.generateProxyClass("$Proxy0", new Class[] {ProxyInterface.class});
//    FileOutputStream outputStream = new FileOutputStream("D:\\$Proxy0.class");
//    outputStream.write(bytes);
//    outputStream.close();
    }
}

interface ProxyInterface {
    public void targetMethod();
}

class ProxyTarget implements ProxyInterface {
    @Override
    public void targetMethod() {
        System.out.println("代理方法...");
    }
}

class ProxyJDK {
    private ProxyInterface proxyInterface;

    public ProxyJDK(ProxyInterface proxyInterface) {
        this.proxyInterface = proxyInterface;
    }

    public Object invoke() {
        System.out.println("代理方法开始...");
        ProxyInterface instance = (ProxyInterface) Proxy.newProxyInstance(
                proxyInterface.getClass().getClassLoader(),
                proxyInterface.getClass().getInterfaces(),
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        System.out.println("======插入前置通知======");
                        method.invoke(proxyInterface, args);
                        System.out.println("======插入后置通知======");
                        return null;
                    }
                });
        return instance;
    }
}
