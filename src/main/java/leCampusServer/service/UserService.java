package leCampusServer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import leCampusServer.domain.User;
import leCampusServer.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	public Object findAllUsers() {
		return userRepository.findAll();
	}
	
	public Iterable findAllUsersIterable() {
		return userRepository.findAll();
	}
	
	public User findByServerId(int serverId) {
			//userRepository.findById(serverI);
		return userRepository.findOne(serverId);
	}
	
	public User findByStudentNumber(String studentNumber) {
		return userRepository.findByStudentNumber(studentNumber);
	}
	
	public User findByEmail(String email) {
		return userRepository.findByUolEmail(email);
	}
	
	public void deleteByServerId(int id) {
		userRepository.delete(id);
	}
	
	public void saveUser(User user) {
		userRepository.save(user);
	}

}
