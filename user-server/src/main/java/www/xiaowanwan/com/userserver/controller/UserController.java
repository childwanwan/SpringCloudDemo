package www.xiaowanwan.com.userserver.controller;

import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import www.xiaowanwan.com.userserver.entity.User;
import www.xiaowanwan.com.userserver.service.UserService;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * description:
 * Author:Xiaowanwan
 * Date:2018/10/1-21:39
 */
@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;

    @ResponseBody
    @GetMapping("/{id}")
    public User queryUserById(@PathVariable("id") String id){
        //Map<String,Object> map = new HashMap<>();
        //JSONObject jsonObject = new JSONObject();
        //System.out.println("id="+id);
        //jsonObject.put("User",this.userService.queryUserById(id));
        //map.put("ok",jsonObject);
        System.out.println("user-service2收到"+id+"请求，正在处理请求");
        try {
            Thread.sleep(new Random().nextInt(3000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return userService.queryUserById(id);
    }
    @ResponseBody
    //@GetMapping("/addUser")
    @RequestMapping(value = "addUser",method = RequestMethod.POST)
    public int addUser(@RequestBody User user){
        Map<String,Object> map = new HashMap<>();
        //JSONObject jsonObject = new JSONObject();
        //System.out.println("id="+id);
        //jsonObject.put("User",this.userService.queryUserById(id));
        //map.put("ok",jsonObject);
        //System.out.println("HHH");
        return userService.addUser(user);
    }

}
