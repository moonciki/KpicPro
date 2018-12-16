package cn.kpic.juwin.jms.sender;

import cn.kpic.juwin.domain.Damaku;
import cn.kpic.juwin.domain.vo.MvCount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

import javax.jms.*;

/**
 * 数据信息持久化队列
 * Created by Administrator on 2017/1/27 0027.
 */
@Component
public class MvCountSender {

    @Autowired
    private JmsTemplate jmsTemplate;

    @Autowired
    @Qualifier("mvcountQueue")
    private Destination destination;

    public void send(final MvCount mvCount) {
        MessageCreator messageCreator = new MessageCreator() {
            public Message createMessage(Session session) {
                ObjectMessage message = null;
                try {
                    message = session.createObjectMessage();
                    message.setObject(mvCount);
                } catch (JMSException e) {
                    e.printStackTrace();
                }
                return (Message) message;
            }
        };
        jmsTemplate.send(this.destination, messageCreator);
    }

}
