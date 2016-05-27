package cn.kpic.juwin.jms.sender;

import cn.kpic.juwin.domain.vo.JmsTip;
import cn.kpic.juwin.domain.vo.JmsUserIntegrityUpd;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

import javax.jms.*;

/**
 * Created by bjsunqinwen on 2016/5/27.
 */
@Component
public class TipQueueMessageSender {
    @Autowired
    private JmsTemplate jmsTemplate;

    @Autowired
    @Qualifier("tipQueue")
    private Destination destination;

    public void send(final JmsTip jmsTip) {
        MessageCreator messageCreator = new MessageCreator() {
            public Message createMessage(Session session) {
                ObjectMessage message = null;
                try {
                    message = session.createObjectMessage();
                    message.setObject(jmsTip);
                } catch (JMSException e) {
                    e.printStackTrace();
                }
                return (Message) message;
            }
        };
        jmsTemplate.send(this.destination, messageCreator);
    }
}
