package com.vv;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

/**
 * Hello world!
 *
 */
public class consumerJava {
    public static void main( String[] args ){
        ConnectionFactory cf = new ConnectionFactory();
        cf.setHost("10.0.16.173");
        cf.setPort(5672);
        cf.setUsername("admin");
        cf.setPassword("admin");
        try (Connection c = cf.newConnection();
             Channel channel = c.createChannel();){
            channel.queueDeclare("test",true,false,false,null);
            channel.basicConsume("test",true,new DefaultConsumer(channel){
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                    String str = new String(body,StandardCharsets.UTF_8);
                    System.out.println(str);
                }
            });
        } catch (TimeoutException | IOException e) {
            e.printStackTrace();
        }
    }
}
