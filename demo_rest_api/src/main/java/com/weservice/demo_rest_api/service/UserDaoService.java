package com.weservice.demo_rest_api.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;
import com.weservice.demo_rest_api.bean.UserBean;

@Component
public class UserDaoService {
	private static List<UserBean> list = new ArrayList<UserBean>();
	private static int counter = 4;
	
	static {
		list.add(new UserBean(1, "Dipak", new Date()));
		list.add(new UserBean(2, "Rahul", new Date()));
		list.add(new UserBean(3, "Anindya", new Date()));
		list.add(new UserBean(4, "Santanu", new Date()));
	}
	
	public List<UserBean> findAll(){
		return list;
	}
	
	public UserBean save(UserBean user) {
		if (user.getId() == null) {
			user.setId(++counter);
		}
		list.add(user);
		return user;
	}
	
	public UserBean findOne(int id) {
		for (UserBean user : list) {
			if (user.getId() == id) {
				return user;
			}
		}
		return null;
	}
	
	public UserBean deleteById(int id) {
		Iterator<UserBean> iterator = list.iterator();
		while (iterator.hasNext()) {
			UserBean user = iterator.next();
			if (user.getId() == id) {
				iterator.remove();
				return user;
			}
		}
		return null;
	}
}
