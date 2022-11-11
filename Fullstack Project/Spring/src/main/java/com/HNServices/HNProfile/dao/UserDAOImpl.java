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
public class UserDAOImpl implements DAOInterface<Users> {

	@Autowired
	private EntityManager entityManager;
	@Autowired
	private TypeUserDAOImpl typeUserDAO;

	@Override
	public List<Users> get() {
		//Get current session
		Session currentSession = entityManager.unwrap(Session.class);

		return currentSession.createQuery("FROM Users", Users.class).getResultList();
	}

	@Override
	public Users get(int id) {
		//Get current session
		Session currentSession = entityManager.unwrap(Session.class);
		
		Query<Users> query = currentSession.createQuery("FROM Users u WHERE u.id = :id", Users.class);
		query.setParameter("id", id);
		
		try {
			return query.getSingleResult();
		}catch (NoResultException e) {
			System.out.println("No entity to get");
			return null;
		}
	}
	
	@Override
	public boolean update(int id, Users object) {
		//Get current session
		Session currentSession = entityManager.unwrap(Session.class);
		
		//Get the type from id
		Users t = currentSession.get(Users.class, id);
		
		if(t != null) {
			TypeUser type = typeUserDAO.exists(object.getTypeUser().getTypeName(), currentSession);
			currentSession.createQuery("UPDATE Users u SET u.name=:name, u.firstName=:firstName, u.email=:email, u.typeUser=:type WHERE u.id=:id")
				.setParameter("name", object.getName()).setParameter("firstName", object.getFirstName()).setParameter("email", object.getEmail())
				.setParameter("type", type).setParameter("id", id).executeUpdate();
			return true;
		}else {
			return false;
		}		
	}

	@Override
	public boolean save(Users object) {
		
		//Get current session
		Session currentSession = entityManager.unwrap(Session.class);
		
		//Check if typeUser exists
		TypeUser type = typeUserDAO.exists(object.getTypeUser().getTypeName(), currentSession);
		if(type != null) {
							
			//Assign type to user and persist it
			object.setTypeUser(type);
			currentSession.save(object);
			System.out.println("User saved!");
			return true;
		}else {
			System.out.println("Type doesn´t exist!");
			return false;
		}
	}

	@Override
	public boolean delete(int id) {
		//Get current session
		Session currentSession = entityManager.unwrap(Session.class);
		
		Users u = currentSession.get(Users.class, id);
		if(u != null) {
			currentSession.delete(u);
			System.out.println("User deleted");
			return true;
		}else {
			System.out.println("No entity to delete!");
			return false;
		}
	}
}
