package org.gala.demo.dubbox;

import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author yuan.li 
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-dubbo-server.xml")
public class Server { 
 
	@Test
	public void test() throws IOException {

		System.out.println("server started...........");
		
		System.in.read();
	}
}
