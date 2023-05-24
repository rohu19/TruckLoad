package com.example.LoadApi;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class DataController {

	@Autowired
	private DataService dataService;

	@PostMapping("/save")
	public ResponseEntity<String> addLoad(@RequestBody Data load) {
		Data savedLoad = dataService.saveLoad(load);
		if (savedLoad != null) {
			return ResponseEntity.ok("Load details added successfully");
		} else {
			return ResponseEntity.badRequest().body("Failed to add load details");
		}
	}

	@GetMapping("/load")
	public ResponseEntity<List<Data>> getLoadsByShipperId(@RequestParam("shipperUID") String shipperUID) {
		List<Data> loads = dataService.getLoadsByShipperId(shipperUID);
		return ResponseEntity.ok(loads);
	}

	@GetMapping("/load/{loadId}")
	public ResponseEntity<Data> getLoadById(@PathVariable("loadId") Integer loadId) {
		Optional<Data> load = dataService.getLoadById(loadId);
		if (load.isPresent()) {
			return ResponseEntity.ok(load.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@PutMapping("/load/{loadId}")
	public ResponseEntity<Data> updateLoad(@PathVariable("loadId") Integer loadId, @RequestBody Data updatedLoad) {
		Optional<Data> load = dataService.updateLoad(loadId, updatedLoad);
		if (load.isPresent()) {
			return ResponseEntity.ok(load.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/load/{loadId}")
	public ResponseEntity<Void> deleteLoad(@PathVariable("loadId") Integer loadId) {
		boolean isDeleted = dataService.deleteLoadById(loadId);
		if (isDeleted) {
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}

}
