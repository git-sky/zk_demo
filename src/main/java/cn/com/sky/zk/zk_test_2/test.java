package cn.com.sky.zk.zk_test_2;

import java.io.IOException;

import org.apache.zookeeper.KeeperException;

public class test {
	public static void main(String[] args) throws IOException,
			InterruptedException, KeeperException {
		ZooKeeperWatcher zw1 = new ZooKeeperWatcher();
		zw1.connect("127.0.0.1:2181", "/root1");
		ZooKeeperWatcher zw2 = new ZooKeeperWatcher();
		zw2.connect("127.0.0.1:2181", "/root1");
		new Thread(zw1).start();
		new Thread(zw2).start();
		ConfigCenter cc = new ConfigCenter("127.0.0.1:2181", "/root1");
		cc.updateConfig("a");
		cc.updateConfig("b");
		cc.updateConfig("c");
		cc.updateConfig("d");
	}
}