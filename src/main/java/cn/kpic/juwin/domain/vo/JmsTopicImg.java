package cn.kpic.juwin.domain.vo;

/**
 * Created by bjsunqinwen on 2016/3/10.
 * ����jms��Ϣ�첽��������ͼƬ��Ϣ��¼
 * ���ھ������й��ߴ��䣬�����Ҫ���л�
 */
public class JmsTopicImg implements java.io.Serializable{

    private String content;
    private Long topicPostid;

    public JmsTopicImg(String content, Long topicPostid){

        this.content = content;
        this.topicPostid = topicPostid;

    }

    public String getContent() {
        return content;
    }

    public Long getTopicPostid() {
        return topicPostid;
    }
}
