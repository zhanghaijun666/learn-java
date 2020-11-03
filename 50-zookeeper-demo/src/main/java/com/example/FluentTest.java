package com.example;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;

/**
 * @Author: 爱做梦的奋斗青年
 * @Date: 2020/11/3 10:42
 */
public class FluentTest {
    // 指定zk集群
    private static final String CLUSTER = "zkOS:2181";
    // private static final String CLUSTER = "zkOS1:2181,zkOS2:2181,zkOS3:2181,zkOS4:2181";
    // 指定当前操作的根节点名称
    private static final String ROOT_PATH = "mytest";

    public static void main(String[] args) throws Exception {
        // ---------------- 创建会话 -----------
        // 创建重试策略对象：重试失败后会间隔1秒再重试，最多重试3次
        ExponentialBackoffRetry retryPolicy = new ExponentialBackoffRetry(1000, 3);
        // 创建客户端
        CuratorFramework client = CuratorFrameworkFactory
                .builder()
                .connectString(CLUSTER)
                .sessionTimeoutMs(15000)
                .connectionTimeoutMs(13000)
                .retryPolicy(retryPolicy)
                .namespace(ROOT_PATH)  // 指定客户端隔离命名空间
                .build();
        // 开启客户端
        client.start();

        // 指定要创建和操作的节点，注意，其是相对于/logs节点的
        String nodePath = "/host";

        // ---------------- 创建节点 -----------
        // 创建一个节点，初始数据内容为空
        // String nodeName = client.create().forPath(nodePath);
        // 创建一个节点，附带初始数据内容
        // String nodeName = client.create()
        //                         .forPath(nodePath, "myhost".getBytes());
        // 创建临时节点
        // String nodeName = client.create().withMode(CreateMode.EPHEMERAL)
        //                         .forPath(nodePath, "myhost".getBytes());
        // 递归创建父节点
        String nodeName = client.create().creatingParentsIfNeeded()
                .withMode(CreateMode.EPHEMERAL)
                .forPath(nodePath, "myhost".getBytes());
        System.out.println("新创建的节点名称为：" + nodeName);

        // ---------------- 获取数据内容并注册watcher -----------
        // byte[] data = client.getData().usingWatcher((CuratorWatcher) event -> {
        //     System.out.println(event.getPath() + "数据内容发生变化");
        // }).forPath(nodePath);
        // System.out.println("节点的数据内容为：" + new String(data));

        // ---------------- 获取子节点列表 -----------
        // List<String> children = client.getChildren().forPath(nodePath);
        // System.out.println("当前节点的子节点列表为：" + children);


        // ---------------- 更新数据内容 -----------
        // client.setData().forPath(nodePath, "newhost".getBytes());
        // // 获取更新过的数据内容
        // byte[] newData = client.getData().forPath(nodePath);
        // System.out.println("更新过的数据内容为：" + new String(newData));

        // ---------------- 删除节点 -----------
        // if(client.checkExists().forPath(nodePath) != null) {
        //     // 若当前指定节点下存在子节点，则当前节点无法删除
        //     // client.delete().forPath(nodePath);
        //     // 删除指定节点，并且会递归删除其所有子孙节点
        //     client.delete().deletingChildrenIfNeeded().forPath(nodePath);
        // }
    }
}
