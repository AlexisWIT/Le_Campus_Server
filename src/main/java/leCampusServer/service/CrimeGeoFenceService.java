package leCampusServer.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import leCampusServer.domain.CrimeGeoFence;
import leCampusServer.repository.CrimeGeoFenceRepository;

@Service
public class CrimeGeoFenceService {

	@Autowired
	private CrimeGeoFenceRepository crimeGeoFenceRepository;
	
	public Object findAllCrimeGeoFences() {
		return crimeGeoFenceRepository.findAll();
	}
	
	public Iterable findAllCrimeGeoFencesIterable() {
		return crimeGeoFenceRepository.findAll();
	}
	
	public CrimeGeoFence findByServerId(int serverId) {
		return crimeGeoFenceRepository.findOne(serverId);
	}
	
	public List<CrimeGeoFence> findByCategory(String category) {
		return crimeGeoFenceRepository.findByCategory(category);
	}
	
	public List<CrimeGeoFence> findByLocation(String location) {
		return crimeGeoFenceRepository.findByLocation(location);
	}
	
	public void deleteByServerId(int id) {
		crimeGeoFenceRepository.delete(id);
	}
	
	public void saveCrimeGeoFence(CrimeGeoFence crimeGeoFence) {
		crimeGeoFenceRepository.save(crimeGeoFence);
	}
	
}
