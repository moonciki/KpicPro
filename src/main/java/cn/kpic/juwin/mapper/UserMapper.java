package cn.kpic.juwin.mapper;

import cn.kpic.juwin.domain.User;
import cn.kpic.juwin.domain.vo.TopicManager;

import java.util.List;
import java.util.Map;

/*
 * mapper�ӿڣ��൱��dao�ӿ�
 * mybatis��mapper������ģʽ
 * */
public interface UserMapper {
	//����id�����
	User findUserById(Long id) throws Exception;
	List<User> getAllUser() throws Exception;
	void addUser(User user);
	User jugeUser(Map<String, Object> params);
	User findUserByName(String name);
	void update(User user);
	List<TopicManager> getAllSmallManagerByPbarId(Long id);
}
