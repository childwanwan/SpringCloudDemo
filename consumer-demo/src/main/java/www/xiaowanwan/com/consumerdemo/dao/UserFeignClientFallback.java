package www.xiaowanwan.com.consumerdemo.dao;

import org.springframework.stereotype.Component;
import www.xiaowanwan.com.consumerdemo.entity.User;

/**
 * description:
 * Author:Xiaowanwan
 * Date:2018/10/4-21:51
 */
@Component
public class UserFeignClientFallback implements UserClient {
    @Override
    public User queryUserById(String id) {
        User user = new User();
        user.setId(id);
        user.setUsername("用户查询出现异常！");
        return user;
    }
}
