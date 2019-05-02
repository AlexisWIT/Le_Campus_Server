package leCampusServer.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import leCampusServer.domain.BuildingGeoFence;

public interface BuildingGeoFenceRepository extends CrudRepository<BuildingGeoFence, Integer> {
	List<BuildingGeoFence> findByBuilding(String building);
}
