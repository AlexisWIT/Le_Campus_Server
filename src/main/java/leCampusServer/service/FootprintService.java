package leCampusServer.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import leCampusServer.domain.Footprint;
import leCampusServer.repository.FootprintRepository;

@Service
public class FootprintService {

	@Autowired
	private FootprintRepository footprintRepository;
	
	public Object findAllFootprints() {
		return footprintRepository.findAll();
	}
	
	public Iterable findAllFootprintsIterable() {
		return footprintRepository.findAll();
	}
	
	public Footprint findByServerId(int serverId) {
		return footprintRepository.findOne(serverId);
	}
	
	public List<Footprint> findByTitle(String title) {
		return footprintRepository.findByTitle(title);
	}
	
	public List<Footprint> findByCreator(String creator) {
		return footprintRepository.findByCreator(creator);
	}
	
	public List<Footprint> findByType(String type) {
		return footprintRepository.findByType(type);
	}
	
	public void deleteByServerId(int id) {
		footprintRepository.delete(id);
	}
	
	public void saveFootprint(Footprint footprint) {
		footprintRepository.save(footprint);
	}
}
