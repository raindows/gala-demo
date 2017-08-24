package org.gala.demo.dubbox.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.gala.demo.dubbox.model.UserModel;
import org.springframework.stereotype.Service;

/**
 * @author yuan.li
 *
 */
@Path("user")
@Service("userService")
public class UserServiceImpl implements UserService {

	// http://localhost:18080/user/getUser/8888
	@GET
	@Path("getUser/{id}")
	@Produces({ MediaType.APPLICATION_JSON })
	@Override
	public UserModel getUser(@PathParam("id") String id) {

		System.out.println(id);
		UserModel model = new UserModel();
		model.setId(id);
		model.setName("yuan");
		model.setAge(18);
		return model;
	}

	@POST
	@Path("saveUser")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Override
	public Boolean saveUser(UserModel model) {

		System.out.println(model);
		return true;
	}

	@Override
	public Boolean timeOut(String id) {

		try {
			System.out.println(id);

			Thread.sleep(5 * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return true;
	}

	@Override
	public Boolean concurrent(String id) {
		
		try {
			System.out.println(id);

			Thread.sleep(1 * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return true;
	}

}
