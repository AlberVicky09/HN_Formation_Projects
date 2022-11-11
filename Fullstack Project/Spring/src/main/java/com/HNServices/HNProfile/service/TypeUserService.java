package com.HNServices.HNProfile.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.HNServices.HNProfile.dao.DAOInterface;
import com.HNServices.HNProfile.entity.TypeUser;

@Service
public class TypeUserService implements ServiceInterface<TypeUser>{

	@Autowired
	private DAOInterface<TypeUser> typeUserDAO; 
	
	@Override
	@Transactional
	public List<TypeUser> get() {
		return typeUserDAO.get();
	}

	@Override
	@Transactional
	public TypeUser get(int id) {
		return typeUserDAO.get(id);
	}
	
	@Override
	@Transactional
	public boolean update(int id, TypeUser object) {
		return typeUserDAO.update(id, object);
	}

	@Override
	@Transactional
	public boolean save(TypeUser object) {
		return typeUserDAO.save(object);
	}

	@Override
	@Transactional
	public boolean delete(int id) {
		return typeUserDAO.delete(id);
	}

}
