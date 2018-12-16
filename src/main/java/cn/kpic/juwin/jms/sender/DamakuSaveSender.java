package cn.kpic.juwin.jms.sender;

import cn.kpic.juwin.domain.Damaku;
import cn.kpic.juwin.domain.vo.JmsUpdPbar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

import javax.jms.*;

/**
 * 弹幕持久化队列
 * Created by Administrator on 2017/1/21 0021.
 */
@Component
public class DamakuSaveSender {

    @Autowired
    private JmsTemplate jmsTemplate;

    @Autowired
    @Qualifier("damakuQueue")
    private Destination destination;

    public void send(final Damaku damaku) {
        MessageCreator messageCreator = new MessageCreator() {
            public Message createMessage(Session session) {
                ObjectMessage message = null;
                try {
                    message = session.createObjectMessage();
                    message.setObject(damaku);
                } catch (JMSException e) {
                    e.printStackTrace();
                }
                return (Message) message;
            }
        };
        jmsTemplate.send(this.destination, messageCreator);
    }

}
