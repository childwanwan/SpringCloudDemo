package www.xiaowanwan.com.userserver.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import www.xiaowanwan.com.userserver.entity.User;
import www.xiaowanwan.com.userserver.mapper.UserMapper;

import java.util.Random;

/**
 * description:
 * Author:Xiaowanwan
 * Date:2018/10/1-21:40
 */
@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;
    public User queryUserById(String id){
        Random random = new Random();
        int i = random.nextInt(2000);
        try {
            Thread.sleep(i);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return userMapper.selectByPrimaryKey(id);
    }
    public int addUser(User user){
        return userMapper.insert(user);
    }
}
