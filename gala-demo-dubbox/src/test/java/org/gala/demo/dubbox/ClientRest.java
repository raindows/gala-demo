package org.gala.demo.dubbox;

import java.io.IOException;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.gala.demo.dubbox.model.UserModel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author yuan.li
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-dubbo-client.xml")
public class ClientRest {

	@Test
	public void test() throws IOException {

		System.out.println("...........");

		getUser();
		saveUser();

		System.in.read();
	}
	

	private String url = "http://localhost:" + 18080 + "/user";
	private MediaType mediaType = MediaType.APPLICATION_JSON_TYPE;

	public void getUser() {

		System.out.println("getUser...........");
		
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(url+"/getUser/987654");
		Response response = target.request().get();
		try {
			
			if (response.getStatus() != 200) {
				throw new RuntimeException("error code : " + response.getStatus());
			}
			System.out.println("result: " + response.readEntity(UserModel.class));
		} finally {
			response.close();
			client.close();
		}

	}

	public void saveUser() {

		System.out.println("saveUser...........");

		UserModel model = new UserModel();
		model.setId("12346");
		model.setName("yuan1");
		model.setAge(181);

		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(url + "/saveUser.json");
		Response response = target.request().post(Entity.entity(model, mediaType));

		try {
			if (response.getStatus() != 200) {
				throw new RuntimeException("error code : " + response.getStatus());
			}
			System.out.println("result: " + response.readEntity(Boolean.class));

		} finally {
			response.close();
			client.close();
		}
	}
}
