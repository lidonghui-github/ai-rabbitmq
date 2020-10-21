package helloword;

import cn.hd.util.RabbitMQUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;
import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 消息生产者
 */
public class SecondProvider {

    //生产消息
    @Test
    public void testSendMQ() throws IOException {
        //6.获取连接对象
        Connection connection = RabbitMQUtils.getConnection();
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
        channel.queueDeclare("hello3", true, false, true, null);


        //9.发布消息
        /**
         * 参数1：交换机名称
         * 参数2：队列名称
         * 参数3：属性
         * 参数4：消息内容
         */
        for(int k=0;k<1000;k++) {
            channel.basicPublish("", "hello3", MessageProperties.PERSISTENT_TEXT_PLAIN, ("哈哈"+k).getBytes());
        }

        //关闭通道
        RabbitMQUtils.closeChannelAndConnection(channel, connection);

    }
}
