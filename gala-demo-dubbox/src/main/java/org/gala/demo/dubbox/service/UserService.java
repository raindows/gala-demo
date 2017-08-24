package org.gala.demo.dubbox.service;

import org.gala.demo.dubbox.model.UserModel;

/**
 * @author yuan.li
 *
 */
public interface UserService {

	UserModel getUser(String id);
	
	Boolean saveUser(UserModel model);
	
	Boolean timeOut(String id);
	
	Boolean concurrent(String id);
}
