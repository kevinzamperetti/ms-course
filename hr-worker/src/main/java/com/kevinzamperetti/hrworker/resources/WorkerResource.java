package com.kevinzamperetti.hrworker.resources;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kevinzamperetti.hrworker.entities.Worker;
import com.kevinzamperetti.hrworker.repositories.WorkerRepository;

@RestController
@RequestMapping(value = "/workers")
public class WorkerResource {

	@Autowired
	private WorkerRepository repository;
	
	@GetMapping
	public ResponseEntity<List<Worker>> findAll() {
		List<Worker> workerList = repository.findAll();
		return ResponseEntity.ok(workerList);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Worker> findById(@PathVariable Long id) {
		Optional<Worker> worker = repository.findById(id);
		if (worker.isPresent()) {
			return ResponseEntity.ok(worker.get());
		}
		return ResponseEntity.notFound().build();
	}
	
}
