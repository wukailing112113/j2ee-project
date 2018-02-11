package com.soecode.lyf.kafka;



import org.apache.kafka.clients.consumer.Consumer;

import java.util.Properties;

/**
* @ClassName:IKafkaConsumer.java
* @Description:消息消费监听器接口
* @author:  Sawyer
* @date:    2016-12-3
 */
public interface IKafkaConsumer {

	void init();

	Consumer<String, MessageBean> consumeList(String... topic);

	void destory();

	void setProperties(Properties properties);

	public String receive();
}