package cn.kpic.juwin.domain;

/**
 * ��������ӦͼƬ
 * Created by bjsunqinwen on 2016/3/9.
 */
public class TopicImg implements java.io.Serializable{

    private Long id;
    private String imagePath;
    private String imgKey;//1��ͼƬ  2����Ƶ   3����Ƶ
    private Long topicId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public Long getTopicId() {
        return topicId;
    }

    public void setTopicId(Long topicId) {
        this.topicId = topicId;
    }

    public String getImgKey() {
        return imgKey;
    }

    public void setImgKey(String imgKey) {
        this.imgKey = imgKey;
    }
}
