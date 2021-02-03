package com.dao;

import java.util.List;

import com.model.User;

public interface UserDao 
{

	public int saveUser(User user);
	public int loginCheck(User user);
	public List<User> displayAll(User user);
	public int deleteUser(User user);
	public User editUser(User user);
	public int updateUser(User user);
	
}
