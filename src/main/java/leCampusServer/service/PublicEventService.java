package leCampusServer.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import leCampusServer.domain.PublicEvent;
import leCampusServer.repository.PublicEventRepository;

@Service
public class PublicEventService {

	@Autowired
	private PublicEventRepository publicEventRepository;
	
	public Object findAllEvents() {
		return publicEventRepository.findAll();
	}
	
	public Iterable findAllEventsIterable() {
		return publicEventRepository.findAll();
	}
	
	public PublicEvent findByServerId(int serverId) {
		return publicEventRepository.findOne(serverId);
	}
	
	public List<PublicEvent> findByType(String eventType) {
		return publicEventRepository.findByEventType(eventType);
	}
	
	public List<PublicEvent> findByCode(String eventCode) {
		return publicEventRepository.findByEventCode(eventCode);
	}
	
	public void deleteByServerId(int id) {
		publicEventRepository.delete(id);
	}
	
	public void saveEvent(PublicEvent publicEvent) {
		publicEventRepository.save(publicEvent);
	}
}
