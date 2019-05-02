package leCampusServer.repository;

import org.springframework.data.repository.CrudRepository;

import leCampusServer.domain.User;

public interface UserRepository extends CrudRepository<User, Integer> {
	User findByServerId(int serverId);
	User findByStudentNumber(String studentNumber);
	User findByUolEmail(String uolEmail);
}
