package fanout;

import cn.hd.util.RabbitMQUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;

public class Provider {
    public static void main(String[] args) throws IOException {
        //1.获取连接
        Connection connection = RabbitMQUtils.getConnection();
        //2.获取通道
        Channel channel = connection.createChannel();
        //3.声明交换机  参数1 ：交换机名称    参数2：交换机类型   fanout 广播类型
        channel.exchangeDeclare("logs","fanout");
        //4.发布消息  参数1 ：交换机名称    参数2：路由
        for (int i = 0; i <100 ; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            channel.basicPublish("logs","",null,("fanout message quene"+i).getBytes());
        }

        //5.关闭连接
        RabbitMQUtils.closeChannelAndConnection(channel,connection);
    }
}
