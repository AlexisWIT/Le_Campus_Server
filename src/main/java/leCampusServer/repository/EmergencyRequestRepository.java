package leCampusServer.repository;

import org.springframework.data.repository.CrudRepository;

import leCampusServer.domain.EmergencyRequest;

public interface EmergencyRequestRepository extends CrudRepository<EmergencyRequest, Integer> {

	EmergencyRequest findByUserName(String userName);
}
