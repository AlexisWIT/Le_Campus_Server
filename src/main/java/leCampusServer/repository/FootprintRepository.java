package leCampusServer.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import leCampusServer.domain.Footprint;

public interface FootprintRepository extends CrudRepository<Footprint, Integer> {
	Footprint findByServerId(int serverId);
	List<Footprint> findByTitle(String title);
	List<Footprint> findByCreator(String creator);
	List<Footprint> findByType(String type);
	
}
