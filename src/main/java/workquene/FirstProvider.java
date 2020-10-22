package workquene;

import cn.hd.util.RabbitMQUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;

public class FirstProvider {
    public static void main(String[] args) throws IOException {
        Connection connection = RabbitMQUtils.getConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare("work",true,false,false,null);
        for (int i = 0; i < 10000000; i++) {
            channel.basicPublish("","work",null,(i+"work quene").getBytes());
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        RabbitMQUtils.closeChannelAndConnection(channel,connection);

    }
}
