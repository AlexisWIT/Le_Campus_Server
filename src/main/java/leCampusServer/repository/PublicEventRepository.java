package leCampusServer.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import leCampusServer.domain.PublicEvent;

public interface PublicEventRepository extends CrudRepository<PublicEvent, Integer> {
	PublicEvent findByServerId(int serverId);
	List<PublicEvent> findByHoldBy(String holdBy);
	List<PublicEvent> findByEventType(String eventType);
	List<PublicEvent> findByEventCode(String eventCode);
	List<PublicEvent> findByEventTitle(String eventTitle);
	List<PublicEvent> findByLocation(String location);
	List<PublicEvent> findByAddress(String address);
	List<PublicEvent> findByPostCode(String postCode);
	List<PublicEvent> findByHost(String host);
	List<PublicEvent> findAllByOrderByStartTimeAsc();
	List<PublicEvent> findByStartTimeGreaterThanOrderByStartTimeAsc(String startTime);
	
}
