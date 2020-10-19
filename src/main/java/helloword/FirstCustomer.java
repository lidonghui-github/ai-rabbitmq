package helloword;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 消息消费者
 */
public class FirstCustomer {
    public static void main(String[] args) throws IOException, TimeoutException {
        //1.创建连接mq的连接对象工厂
        ConnectionFactory connectionFactory = new ConnectionFactory();
        //2.设置连接mq主机
        connectionFactory.setHost("localhost");
        //3.设置端口号
        connectionFactory.setPort(5672);
        //4.设置连接哪个虚拟主机
        connectionFactory.setVirtualHost("/ems");
        //5.设置访问虚拟主机的用户名和密码
        connectionFactory.setUsername("ldh");
        connectionFactory.setPassword("123456");

        //6.获取连接对象
        Connection connection = connectionFactory.newConnection();
        //7.获取连接中的通道对象
        Channel channel = connection.createChannel();
        //8.通道绑定对应的消息队列
        /**
         * 参数1:队列名称，如果队列不存在，自动创建
         *参数2：用来定义队列特性是否要持久化， true表示要持久化    false不持久化
         * 参数3：是否独占队列   true 独占队列   false 不独占队列
         * 参数4：是否在消费完成后自动删除队列    true  自动删除    false 不自动删除
         * 参数5：额外参数
         */
        channel.queueDeclare("hello", false, false, false, null);

        //9.消费消息
        /**
         * 参数1：消费哪个队列的消息    队列名
         * 参数2：开启消息的自动确认机制
         * 参数3：消费时的回调接口
         */
        channel.basicConsume("hello", true, new DefaultConsumer(channel) {
            @Override
            // body:从消息队列中取出的消息
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) {
                System.out.println(new String(body));

            }

        });

     /*   //关闭通道
        channel.close();
        //关闭连接
        connection.close();
        //关闭工厂
        connectionFactory.clone();*/
    }
}
