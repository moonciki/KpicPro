package cn.kpic.juwin.mapper;

import cn.kpic.juwin.domain.User;
import cn.kpic.juwin.domain.vo.TopicManager;
import cn.kpic.juwin.domain.vo.UserVo;

import java.util.List;
import java.util.Map;
import java.util.Objects;

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
	List<Long> isSmallManager(Map params);
	List<UserVo> getAllPbarUsers(Map params);
	void updPostNum(Long id);
	void jm(Long id);
}
