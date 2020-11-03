package com.example;

import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.serialize.SerializableSerializer;
import org.apache.zookeeper.CreateMode;

/**
 * @Author: 爱做梦的奋斗青年
 * @Date: 2020/11/3 10:41
 */
public class ZKClientTest {
    // 指定zk集群
    private static final String CLUSTER = "zkOS1:2181,zkOS2:2181,zkOS3:2181,zkOS4:2181";
    // private static final String CLUSTER = "zkOS:2181";
    // 指定节点名称
    private static final String PATH = "/mylogtest";

    public static void main(String[] args) {
        // ---------------- 创建会话 -----------
        // 创建zkClient
        ZkClient zkClient = new ZkClient(CLUSTER);
        // 为zkClient指定序列化器
        zkClient.setZkSerializer(new SerializableSerializer());

        // ---------------- 创建节点 -----------
        // 指定创建持久节点
        CreateMode mode = CreateMode.PERSISTENT;
        // 指定节点数据内容
        String data = "first log";
        // 创建节点
        String nodeName = zkClient.create(PATH, data, mode);
        System.out.println("新创建的节点名称为：" + nodeName);

        // ---------------- 获取数据内容 -----------
        Object readData = zkClient.readData(PATH);
        System.out.println("节点的数据内容为：" + readData);

        // ---------------- 注册watcher -----------
        zkClient.subscribeDataChanges(PATH, new IZkDataListener() {
            @Override
            public void handleDataChange(String dataPath, Object data) throws Exception {
                System.out.print("节点" + dataPath);
                System.out.println("的数据已经更新为了" + data);
            }

            @Override
            public void handleDataDeleted(String dataPath) throws Exception {
                System.out.println(dataPath + "的数据内容被删除");
            }
        });

        // ---------------- 更新数据内容 -----------
        zkClient.writeData(PATH, "second log");
        String updatedData = zkClient.readData(PATH);
        System.out.println("更新过的数据内容为：" + updatedData);

        // ---------------- 删除节点 -----------
        zkClient.delete(PATH);

        // ---------------- 判断节点存在性 -----------
        boolean isExists = zkClient.exists(PATH);
        System.out.println(PATH + "节点仍存在吗？" + isExists);
    }
}
