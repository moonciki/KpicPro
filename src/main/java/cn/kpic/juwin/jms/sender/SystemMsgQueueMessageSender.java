package cn.kpic.juwin.jms.sender;

import cn.kpic.juwin.domain.vo.JmsSystemMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

import javax.jms.*;

/**
 * Created by bjsunqinwen on 2016/4/27.
 */
@Component
public class SystemMsgQueueMessageSender {

    @Autowired
    private JmsTemplate jmsTemplate;

    @Autowired
    @Qualifier("systemMsgQueue")
    private Destination destination;

    public void send(final JmsSystemMsg jmsSystemMsg){
        MessageCreator messageCreator = new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                ObjectMessage message = null;
                try {
                    message = session.createObjectMessage();
                    message.setObject(jmsSystemMsg);
                } catch (JMSException e) {
                    e.printStackTrace();
                }
                return (Message) message;
            }
        };
        this.jmsTemplate.send(this.destination, messageCreator);
    }

}
