package dev.husein.persistence;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import dev.husein.model.User;

public class UserPersistenceService implements UserPersistenceLocal {
	@PersistenceContext
	private EntityManager entityManager;
	
	public UserPersistenceService() {
	}
	
	@Override
	public List<User> getUsers() {
		return this.entityManager.createQuery("SELECT user FROM User user", User.class).getResultList();
	}

	@Override
	public void addUser(User user) {
		this.entityManager.persist(user);
		
	}

	@Override
	public User findUser(long userId) {
		return this.entityManager.find(User.class, userId);
	}

	@Override
	public void deleteUser(User user) {
		this.entityManager.remove(this.entityManager.contains(user) ? user : this.entityManager.merge(user));
		
	}

	@Override
	public List<User> searchByName(String name) {
		return this.entityManager.createQuery("SELECT user FROM User user WHERE user.name LIKE :name", User.class).setParameter("name", "%" + name + "%").getResultList();
	}

	@Override
	public void saveUser(User user) {
		this.entityManager.merge(user);
		
	}

	@Override
	public boolean checkLoginCredentials(String email, String password) {
		List<User> users = this.entityManager.createQuery("SELECT user FROM User user WHERE user.email=:email AND user.password:=password AND user.authorized=:authorized", User.class).setParameter("email", email).setParameter("password", password).setParameter("authorized", "yes").getResultList();
		
		if (users.size() > 0) {
			return true;
		}
		
		return false;
	}

}
