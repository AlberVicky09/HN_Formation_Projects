package com.HNServices.HNProfile.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.HNServices.HNProfile.dao.DAOInterface;
import com.HNServices.HNProfile.entity.Users;

@Service
public class UserService implements ServiceInterface<Users>{

	@Autowired
	private DAOInterface<Users> userDAO; 
	
	@Override
	@Transactional
	public List<Users> get() {
		return userDAO.get();
	}

	@Override
	@Transactional
	public Users get(int id) {
		return userDAO.get(id);
	}

	@Override
	@Transactional
	public boolean update(int id, Users object) {
		return userDAO.update(id, object);
	}
	
	@Override
	@Transactional
	public boolean save(Users object) {
		return userDAO.save(object);
	}

	@Override
	@Transactional
	public boolean delete(int id) {
		return userDAO.delete(id);
	}

}
