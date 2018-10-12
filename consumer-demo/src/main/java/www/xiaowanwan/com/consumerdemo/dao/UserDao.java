package www.xiaowanwan.com.consumerdemo.dao;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import www.xiaowanwan.com.consumerdemo.entity.User;

import java.util.ArrayList;
import java.util.List;

/**
 * description:
 * Author:Xiaowanwan
 * Date:2018/10/2-11:45
 */
@Component//把普通pojo实例化到spring容器中，相当于配置文件中的<bean id="" class=""/>
public class UserDao {
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private DiscoveryClient discoveryClient;
    //@Autowired
    //private HystrixCommand hystrixCommand;

    @HystrixCommand(fallbackMethod = "queryUserByIdFallback")//熔断了就调用该方法
    public User queryUserById(String id){
        // 根据服务名称，获取服务实例
        //List<ServiceInstance> instances = discoveryClient.getInstances("user-service");
        // 因为只有一个UserService,因此我们直接get(0)获取
        //ServiceInstance instance = instances.get(0);
        // 获取ip和端口信息
        //String baseUrl = "http://"+instance.getHost() + ":" + instance.getPort()+"/user/"+id;
        //String url = "http://192.168.1.106:8080/user/"+id;
        String url = "http://user-service/user/"+id;
        Long start = System.currentTimeMillis();
        User user = restTemplate.getForObject(url, User.class);
        Long end = System.currentTimeMillis();
        System.out.println("id="+id+"time="+(end-start));
        //HystrixProperty[] hystrixProperties = hystrixCommand.commandProperties();
        //System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        //for (HystrixProperty h:hystrixProperties) {
        //    System.out.println(h.name()+":"+h.value());
        //}

        return user;
    }

    //熔断执行的方法
    public User queryUserByIdFallback(String id){
        User user = new User();
        user.setId(id);
        user.setUsername("用户信息查询出现异常！");
        return user;
    }

    public List<User> queryUserByIds(List<String> ids){
        List<User> list = new ArrayList<>();
        // 根据服务名称，获取服务实例s
        List<ServiceInstance> instances = discoveryClient.getInstances("user-service");
        //等于1直接获取，大于1就负载均衡算法(随机数或者是求余等等)
        // 因为只有一个UserService,因此我们直接get(0)获取
        ServiceInstance instance = instances.get(0);
        // 获取ip和端口信息
        String baseUrl = "http://"+instance.getHost() + ":" + instance.getPort()+"/user/";
        //String url = "http://192.168.1.106:8080/user/"+id;
        ids.forEach(id -> {
            // 我们测试多次查询，
            list.add(this.restTemplate.getForObject(baseUrl + id, User.class));
            // 每次间隔500毫秒
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        return list;
    }

    public int addUser(User user){
        String url = "http://192.168.1.106:8080/user/addUser";
        //return restTemplate.getForObject(url,User.class);
        //return restTemplate.getForObject(url,Integer.class);

        ResponseEntity<String> postForEntity = restTemplate.postForEntity(url,user,String.class);
        System.out.println("postForEntity:"+postForEntity);
        System.out.println("              getStatusCodeValue:"+ postForEntity.getStatusCodeValue());
        System.out.println("              getHeaders:"+ postForEntity.getHeaders());
        System.out.println("              getBody:"+ postForEntity.getBody());
        //System.out.println("              getHeaders:"+ postForEntity.);
        return Integer.parseInt(postForEntity.getBody());
    }

    public int deleteUser(List<String> ids){
        String url = "http://192.168.1.106:8080/user/deleteUser";
        //return restTemplate.getForObject(url,User.class);
        //return restTemplate.getForObject(url,Integer.class);

        ResponseEntity<String> postForEntity = restTemplate.postForEntity(url,ids,String.class);

        return Integer.parseInt(postForEntity.getBody());
    }
}
