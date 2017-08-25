package org.gala.demo.dubbox.service.thrift;

import org.apache.thrift.TException;
import org.springframework.stereotype.Service;

/**
 * @author yuan.li
 *
 */
@Service("thriftUserService")
public class UserServiceImpl2 implements UserService2.Iface {

	@Override
	public UserModel2 getUser(String id) throws TException {
		System.out.println(id);
		UserModel2 model = new UserModel2();
		model.setId(id);
		model.setName("yuan");
		model.setAge(18);
		return model;
	}

	@Override
	public boolean saveUser(UserModel2 model) throws TException {
		System.out.println(model);
		return true;
	}
}
