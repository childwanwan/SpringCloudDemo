package www.xiaowanwan.com.consumerdemo.dao;

import lombok.experimental.FieldDefaults;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import www.xiaowanwan.com.consumerdemo.entity.User;

/**
 * description:
 * Author:Xiaowanwan
 * Date:2018/10/4-21:39
 */
@FeignClient(value = "user-service",path = "user",fallback = UserFeignClientFallback.class)
public interface UserClient {
    @GetMapping("/{id}")
    User queryUserById(@PathVariable("id") String id);
}
