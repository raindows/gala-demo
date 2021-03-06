package org.gala.demo.dubbox;

import java.io.IOException;

import org.gala.demo.dubbox.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author yuan.li
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-dubbo-client.xml")
public class ClientHttp {
	
	@Autowired
	UserService httpUserService;

	@Test
	public void test() throws IOException {

		System.out.println("...........");

		getUser();
		saveUser();
		
		System.in.read();
	}
	
	public void getUser() {
		System.out.println("getUser...........");
		httpUserService.getUser("12346");
	}

	public void saveUser() {
	
	}
}
