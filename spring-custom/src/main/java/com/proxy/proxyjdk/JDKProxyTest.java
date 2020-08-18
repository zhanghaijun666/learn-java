package com.proxy.proxyjdk;

import sun.misc.ProxyGenerator;

import java.io.FileOutputStream;

public class JDKProxyTest {
  public static void main(String[] args) throws Exception {
    JDKProxy proxy = new JDKProxy(new JDKProxyTarget());
    ProxyInterface proxyInterface = (ProxyInterface) proxy.invoke();
    System.out.println(proxyInterface.getClass());
    proxyInterface.targetMethod();

//    byte[] bytes = ProxyGenerator.generateProxyClass("$Proxy0", new Class[] {ProxyInterface.class});
//    FileOutputStream outputStream = new FileOutputStream("D:\\$Proxy0.class");
//    outputStream.write(bytes);
//    outputStream.close();
  }
}
