package com.serviceImp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.UserDao;
import com.model.User;
import com.service.UserService;

@Service
public class UserServiceImp implements UserService
{
	@Autowired
	private UserDao dao;


	public int saveUser(User user) 
	{
		return dao.saveUser(user);
	}


	@Override
	public int loginCheck(User user) {
		return dao.loginCheck(user);
	}


	@Override
	public List<User> displayAll(User user) {
		return dao.displayAll(user);
	}


	@Override
	public int deleteUser(User user) 
	{
		return dao.deleteUser(user);
	}


	@Override
	public User editUser(User user) 
	{
		return dao.editUser(user);
	}


	@Override
	public int updateUser(User user) 
	{
		return dao.updateUser(user);
	}


	

}
