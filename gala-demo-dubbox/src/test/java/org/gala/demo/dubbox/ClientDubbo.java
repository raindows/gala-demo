package org.gala.demo.dubbox;

import java.io.IOException;
import org.gala.demo.dubbox.model.UserModel;
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
public class ClientDubbo {

	@Autowired
	UserService dubboUserService;

	@Test
	public void test() throws IOException {

		System.out.println("...........");

		getUser();
		saveUser();

		System.in.read();
	}

	/**
	 * 超时test
	 */
	@Test
	public void timeOutTest() throws IOException {

		System.out.println("...........");

		timeOut();

		System.in.read();
	}

	/**
	 * 并发test
	 */
	@Test
	public void maxConcurTest() throws IOException {

		System.in.read();
	}

	public void getUser() {

		System.out.println("getUser...........");
		dubboUserService.getUser("12346");
	}

	public void saveUser() {

		System.out.println("saveUser...........");

		UserModel model = new UserModel();
		model.setId("12346");
		model.setName("yuan1");
		model.setAge(181);
		dubboUserService.saveUser(model);
	}

	public void timeOut() {

		System.out.println("timeOut...........");
		dubboUserService.timeOut("12346");
	}

	public void concurrent() {

		System.out.println("concurrent...........");
		dubboUserService.concurrent("987654");
	}
}
