package www.xiaowanwan.com.consumerdemo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import www.xiaowanwan.com.consumerdemo.dao.UserClient;
import www.xiaowanwan.com.consumerdemo.dao.UserDao;
import www.xiaowanwan.com.consumerdemo.entity.User;

import java.util.ArrayList;
import java.util.List;

/**
 * description:
 * Author:Xiaowanwan
 * Date:2018/10/2-11:51
 */
@Service
public class UserService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private UserClient userClient;

    public List<User> queryUserByIds(List<String> ids){
        List<User> list = new ArrayList<>();
        for (String id:ids) {
           list.add( userClient.queryUserById(id));
        }
        return list;
    }

    public List<User> selectUserByIds(List<String> ids){
        return userDao.queryUserByIds(ids);
    }

    public int addUser(User user){
        return userDao.addUser(user);
    }

    public int deleteUser(List<String> ids){
        return userDao.deleteUser(ids);
    }
}
