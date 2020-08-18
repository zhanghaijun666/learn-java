package com.proxy.proxyjdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class JDKProxy {
  private ProxyInterface proxyInterface;

  public JDKProxy(ProxyInterface proxyInterface) {
    this.proxyInterface = proxyInterface;
  }

  public Object invoke() {
    System.out.println("代理方法开始...");
    ProxyInterface instance = (ProxyInterface)Proxy.newProxyInstance(
                proxyInterface.getClass().getClassLoader(),
                proxyInterface.getClass().getInterfaces(),
                new InvocationHandler() {
                  @Override
                  public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                    method.invoke(proxyInterface, args);
                    System.out.println("代理方法结束...");
                    return null;
                  }
                });
    return instance;
  }
}
