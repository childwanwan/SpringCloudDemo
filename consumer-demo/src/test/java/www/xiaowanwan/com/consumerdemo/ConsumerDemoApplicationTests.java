package www.xiaowanwan.com.consumerdemo;

import com.netflix.hystrix.HystrixCommandGroupKey;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import www.xiaowanwan.com.consumerdemo.config.MyHystrixCommand;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ConsumerDemoApplicationTests {
	//@Autowired
	//private MyHystrixCommand myHystrixCommand;
	private HystrixCommandGroupKey group;
	@Test
	public void contextLoads() {
		MyHystrixCommand myHystrixCommand = new MyHystrixCommand(group);
		myHystrixCommand.getExecutionTimeInMilliseconds();
	}

}
