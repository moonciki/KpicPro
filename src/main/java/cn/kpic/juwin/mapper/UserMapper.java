package cn.kpic.juwin.mapper;

import cn.kpic.juwin.domain.User;
import cn.kpic.juwin.domain.vo.TopicManager;
import cn.kpic.juwin.domain.vo.UserVo;

import java.util.List;
import java.util.Map;
import java.util.Objects;

/*
 * mapper接口，相当于dao接口
 * mybatis的mapper代理开发模式
 * */
public interface UserMapper {
	//根据id查对象
	User findUserById(Long id) throws Exception;
	List<User> getAllUser() throws Exception;
	void addUser(User user);
	User jugeUser(Map<String, Object> params);
	User findUserByName(Long num);
	void update(User user);
	List<TopicManager> getAllSmallManagerByPbarId(Long id);
	List<Long> isSmallManager(Map params);
	List<UserVo> getAllPbarUsers(Map params);
	void updPostNum(Long id);
	void jm(Long id);
	void delSmallManager(Long id);
	Long checkName(String name);
	Long checkNum(Long num);

	void save(User user);
}
