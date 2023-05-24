package com.example.LoadApi;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DataService {

	@Autowired
	private DataRepository dataRepository;

	public Data saveLoad(Data load) {
		return dataRepository.save(load);
	}

	public List<Data> getLoadsByShipperId(String shipperId) {
		return dataRepository.findByShipperUID(shipperId);
	}

	public Optional<Data> getLoadById(Integer loadId) {
		return dataRepository.findById(loadId);
	}

	public Optional<Data> updateLoad(Integer loadId, Data updatedLoad) {
		Optional<Data> existingLoad = dataRepository.findById(loadId);
		if (existingLoad.isPresent()) {
			Data load = existingLoad.get();
			load.setLoadingPoint(updatedLoad.getLoadingPoint());
			load.setUnloadingPoint(updatedLoad.getUnloadingPoint());
			load.setProductType(updatedLoad.getProductType());
			load.setTruckType(updatedLoad.getTruckType());
			load.setNoOfTrucks(updatedLoad.getNoOfTrucks());
			load.setWeight(updatedLoad.getWeight());
			load.setOptional(updatedLoad.getOptional());
			load.setDate(updatedLoad.getDate());
			return Optional.of(dataRepository.save(load));
		} else {
			return Optional.empty();
		}
	}

	public boolean deleteLoadById(Integer loadId) {
		if (dataRepository.existsById(loadId)) {
			dataRepository.deleteById(loadId);
			return true;
		} else {
			return false;
		}
	}

}
