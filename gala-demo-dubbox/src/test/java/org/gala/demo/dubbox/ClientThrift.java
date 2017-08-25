package org.gala.demo.dubbox;

import java.io.IOException;
import org.apache.thrift.TException;
import org.gala.demo.dubbox.service.thrift.UserModel2;
import org.gala.demo.dubbox.service.thrift.UserService2;
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
public class ClientThrift {

	@Autowired
	UserService2.Iface thriftUserService;

	@Test
	public void test() throws IOException {

		System.out.println("...........");

		getUser();
		saveUser();
		System.in.read();
	}

	public void getUser() {

		System.out.println("getUser...........");
		try {
			thriftUserService.getUser("12346");
		} catch (TException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void saveUser() {

		System.out.println("saveUser...........");

		UserModel2 model = new UserModel2();
		model.setId("12346");
		model.setName("yuan1");
		model.setAge(181);
		try {
			thriftUserService.saveUser(model);
		} catch (TException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
