package com.proxy.proxyjdk;

public class JDKProxyTarget implements ProxyInterface {
    @Override
    public void targetMethod() {
        System.out.println("代理方法...");
    }
}
