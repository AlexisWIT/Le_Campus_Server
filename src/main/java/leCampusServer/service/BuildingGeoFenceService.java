package leCampusServer.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import leCampusServer.domain.BuildingGeoFence;
import leCampusServer.repository.BuildingGeoFenceRepository;

@Service
public class BuildingGeoFenceService {

	@Autowired
	private BuildingGeoFenceRepository buildingGeoFenceRepository;
	
	public Object findAllBuildingGeoFences() {
		return buildingGeoFenceRepository.findAll();
	}
	
	public Iterable findAllBuildingGeoFencesIterable() {
		return buildingGeoFenceRepository.findAll();
	}
	
	public BuildingGeoFence findByServerId(int serverId) {
		return buildingGeoFenceRepository.findOne(serverId);
	}
	
	public List<BuildingGeoFence> findByBuilding(String building) {
		return buildingGeoFenceRepository.findByBuilding(building);
	}
	
	public void deleteByServerId(int id) {
		buildingGeoFenceRepository.delete(id);
	}
	
	public void saveBuildingGeoFence(BuildingGeoFence buildingGeoFence) {
		buildingGeoFenceRepository.save(buildingGeoFence);
	}
	
	
}
