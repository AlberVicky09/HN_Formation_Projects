package com.HNServices.HNProfile.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.HNServices.HNProfile.entity.TypeUser;
import com.HNServices.HNProfile.entity.Users;

@Repository
public class TypeUserDAOImpl implements DAOInterface<TypeUser> {

	@Autowired
	private EntityManager entityManager;

	@Override
	public List<TypeUser> get() {
		//Get current session
		Session currentSession = entityManager.unwrap(Session.class);
		
		return currentSession.createQuery("FROM TypeUser", TypeUser.class).getResultList();
	}

	public TypeUser exists(String type, Session currentSession) {
		
		//Check if type is already in db
		Query<TypeUser> query = currentSession.createQuery("FROM TypeUser t WHERE t.typeName=:type", TypeUser.class).setParameter("type", type);
		try{
			return query.getSingleResult();
		}catch(javax.persistence.NoResultException e) {
			return null;
		}
	}
	
	public boolean hasReference(TypeUser typeUser, Session currentSession) {

		//Check if typeUser is referenced
		Query<Users> query = currentSession.createQuery("FROM Users u WHERE u.typeUser=:type", Users.class)
				.setParameter("type", typeUser);
		
		System.out.println("Times referenced: " + query.getResultList().size());
		return query.getResultList().size() != 0;
	}

	@Override
	public TypeUser get(int id) {
		//Get current session
		Session currentSession = entityManager.unwrap(Session.class);
		
		Query<TypeUser> query = currentSession.createQuery("FROM TypeUser t WHERE t.id = :id", TypeUser.class);
		query.setParameter("id", id);
		
		try {
			return query.getSingleResult();
		}catch (NoResultException e) {
			System.out.println("No entity to get");
			return null;
		}
	}
	
	@Override
	public boolean update(int id, TypeUser object) {
		//Get current session
		Session currentSession = entityManager.unwrap(Session.class);
		
		//Get the type from id
		TypeUser t = currentSession.get(TypeUser.class, id);
		
		if(t != null) {
			currentSession.createQuery("UPDATE TypeUser t SET t.typeName=:type WHERE t.id=:id")
				.setParameter("type", object.getTypeName()).setParameter("id", id).executeUpdate();
			return true;
		}else {
			return false;
		}		
	}

	@Override
	public boolean save(TypeUser object) {
	
		//Get current session
		Session currentSession = entityManager.unwrap(Session.class);
		
		if(exists(object.getTypeName(), currentSession) == null) {
			currentSession.persist(object);
			System.out.println("Type saved");
			return true;
		}else {
			System.out.println("Type already in db");
			return false;
		}
		
	}

	@Override
	public boolean delete(int id) {
		//Get current session
		Session currentSession = entityManager.unwrap(Session.class);
		
		//Get the type from id
		TypeUser t = currentSession.get(TypeUser.class, id);

		//If typeUser exists
		if(t != null) {
			//If it´s not referenced by any user
			if(!hasReference(t, currentSession)) {
				currentSession.delete(t);
				return true;
			}else {
				System.out.println("Can´t delete referenced TypeUser");
				return false;
			}
		}else {
			System.out.println("No entity to delete!");
			return false;
		}		
	}
}
