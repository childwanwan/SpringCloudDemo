package www.xiaowanwan.com.demo.eurekademo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer   // 声明这个应用是一个EurekaServer
@SpringBootApplication
public class EurekaDemoApplication {
	public static void main(String[] args) {
		SpringApplication.run(EurekaDemoApplication.class, args);
	}
}
