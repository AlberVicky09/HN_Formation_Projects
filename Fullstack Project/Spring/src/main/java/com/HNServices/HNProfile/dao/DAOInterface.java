package com.HNServices.HNProfile.dao;

import java.util.List;

public interface DAOInterface <T> {

	public List<T> get();
			
	public T get(int id);
	
	public boolean update(int id, T object);
	
	public boolean save(T object);
	
	public boolean delete(int id);
}
