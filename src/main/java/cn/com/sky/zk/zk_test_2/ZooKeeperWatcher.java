package cn.com.sky.zk.zk_test_2;

import java.io.IOException;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

public class ZooKeeperWatcher implements Watcher ,Runnable {
	
	private ZooKeeper zooKeeper = null;
	private String znode;

	public ZooKeeper getZooKeeper() {
		return this.zooKeeper;
	}

	public void connect(String hosts, String znode) throws IOException,
			InterruptedException, KeeperException {
		this.zooKeeper = new ZooKeeper(hosts, 2000, this);
		this.znode = znode;
		this.zooKeeper.exists(znode, true);
	}

	public void setData(byte[] data) {
		try {
			Stat s = this.zooKeeper.exists(znode, false);
			this.zooKeeper.setData(znode, data, s.getVersion());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void process(WatchedEvent event) {
		System.out.println(Thread.currentThread().getName()+ "--watcher="+event.toString());
		try {
			Stat stat=this.zooKeeper.exists(znode, true);//不知道为什么一定要加上这句话，下次事件到来时，才会触发process事件
		
		} catch (KeeperException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void run() {
		try {
			synchronized (this) {
                           while (true) {
                              wait();
                            }
                        }
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}