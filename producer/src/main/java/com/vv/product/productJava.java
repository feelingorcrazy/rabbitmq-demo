package com.vv.product;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

/**
 * @author wlj
 * @date 2021/08/17 09:51
 **/
public class productJava {
    public static void main(String[] args) {
        ConnectionFactory cf = new ConnectionFactory();
        cf.setHost("10.0.16.173");
        cf.setPort(5672);
        cf.setUsername("admin");
        cf.setPassword("admin");
        try (Connection c = cf.newConnection();
             Channel channel = c.createChannel();){
            channel.queueDeclare("test",true,false,false,null);
            channel.basicPublish("","test",null,"testfsdfsdfsdfsf".getBytes(StandardCharsets.UTF_8));
        } catch (TimeoutException | IOException e) {
            e.printStackTrace();
        }
    }
}
