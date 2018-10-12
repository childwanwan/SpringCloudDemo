package www.xiaowanwan.com.consumerdemo.config;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import org.springframework.context.annotation.Bean;

/**
 * description:
 * Author:Xiaowanwan
 * Date:2018/10/4-20:44
 */

public class MyHystrixCommand extends HystrixCommand {

    private HystrixCommandGroupKey group;

    public MyHystrixCommand(HystrixCommandGroupKey group) {
        super(group);
        this.group = group;
    }


    @Override
    @Bean
    protected Object run() throws Exception {
        System.out.println(this.getExecutionTimeInMilliseconds());
        return null;
    }
}
