package dev.husein.persistence;

import java.util.List;

import javax.ejb.LocalBean;

import dev.husein.model.User;

@LocalBean
public interface UserPersistenceLocal {
	
	public List<User> getUsers();
	
	public void addUser(User user);
	
	public User findUser(long userId);
	
	public void deleteUser(User user);
	
	public List<User> searchByName(String name);
	
	public void saveUser(User user);

	public boolean checkLoginCredentials(String email, String password);
}
