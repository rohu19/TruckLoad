package com.example.LoadApi;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface DataRepository extends CrudRepository<Data, Integer> {
	List<Data> findByShipperUID(String shipperUID);

	Optional<Data> findById(Integer loadId);

	void deleteById(Integer loadId);
}
