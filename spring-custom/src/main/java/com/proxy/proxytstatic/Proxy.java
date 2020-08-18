package com.proxy.proxytstatic;

public class Proxy implements Sourceable {
  // 持有源对象的引⽤
  private Source source;

  public Proxy() {
    super();
    this.source = new Source();
  }

  @Override
  public void method() {
    before();
    source.method();
    atfer();
  }

  private void atfer() {
    System.out.println("after proxy!");
  }

  private void before() {
    System.out.println("before proxy!");
  }
}
