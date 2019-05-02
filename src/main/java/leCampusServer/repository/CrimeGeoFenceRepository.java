package leCampusServer.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import leCampusServer.domain.CrimeGeoFence;

public interface CrimeGeoFenceRepository extends CrudRepository<CrimeGeoFence, Integer> {

	List<CrimeGeoFence> findByCategory(String category);
	List<CrimeGeoFence> findByLocation(String location);
}
