package helloword;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class FirstProvider {

    //生产消息
    @Test
    public void testSendMQ() throws IOException, TimeoutException {
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
        channel.queueDeclare("hello",false,false,false,null);


        //9.发布消息
        /**
         * 参数1：交换机名称
         * 参数2：队列名称
         * 参数3：属性
         * 参数4：消息内容
         */
        channel.basicPublish("","hello",null,"hello rabbitmq".getBytes());


        //关闭通道
        channel.close();
        //关闭连接
        connection.close();
        //关闭工厂
        connectionFactory.clone();

    }
}
