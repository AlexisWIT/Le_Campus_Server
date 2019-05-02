package leCampusServer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import leCampusServer.domain.EmergencyRequest;
import leCampusServer.repository.EmergencyRequestRepository;

@Service
public class EmergencyRequestService {

	@Autowired
	private EmergencyRequestRepository emergencyRequestRepository;
	
	public Object findAllEmergencyRequests() {
		return emergencyRequestRepository.findAll();
	}
	
	public EmergencyRequest findEmergencyRequestByUserName(String userName) {
		return emergencyRequestRepository.findByUserName(userName);
	}
}
