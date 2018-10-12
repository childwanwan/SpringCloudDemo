package www.xiaowanwan.com.consumerdemo.controller;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import www.xiaowanwan.com.consumerdemo.entity.User;
import www.xiaowanwan.com.consumerdemo.service.UserService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * description:
 * Author:Xiaowanwan
 * Date:2018/10/2-11:55
 */
@RequestMapping("consumer")
@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("list")
    public List<User> consume(@RequestParam("ids") List<String> ids) {
        //System.out.println("consumer-demo进来了吗？");
        return userService.queryUserByIds(ids);
        //return userService.selectUserByIds(ids);
    }

    @ResponseBody
    //@GetMapping("/addUser")
    @RequestMapping(value = "addUser", method = RequestMethod.POST)
    public int addUser(@RequestBody User user) {
        Map<String, Object> map = new HashMap<>();
        //JSONObject jsonObject = new JSONObject();
        //System.out.println("id="+id);
        //jsonObject.put("User",this.userService.queryUserById(id));
        //map.put("ok",jsonObject);
        //System.out.println("HHH");
        return userService.addUser(user);
    }


    @ResponseBody
    //@GetMapping("/addUser")
    @RequestMapping(value = "deleteUser", method = RequestMethod.POST)
    public int deleteUser(@RequestBody Object ids) {
        List<String> listId = new ArrayList<>();
        JSONObject j = JSONObject.fromObject(ids);

        JSONArray array = JSONArray.fromObject(j.get("ids"));

        for (int i = 0; i < array.size(); i++) {
            listId.add((String) array.get(i));
            //System.out.println(array.get(i));
        }
        Map<String, Object> map = new HashMap<>();
        //JSONObject jsonObject = new JSONObject();
        //System.out.println("id="+id);
        //jsonObject.put("User",this.userService.queryUserById(id));
        //map.put("ok",jsonObject);
        //System.out.println("HHH");
        return userService.deleteUser(listId);
    }
}
