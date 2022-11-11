package com.HNServices.HNProfile.service;

import java.util.List;

public interface ServiceInterface <T>{

	public List<T> get();
		
	public T get(int id);
	
	public boolean update(int id, T object);
	
	public boolean save(T object);
	
	public boolean delete(int id);
}
