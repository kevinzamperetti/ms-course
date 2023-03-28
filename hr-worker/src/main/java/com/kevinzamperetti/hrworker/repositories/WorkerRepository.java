package com.kevinzamperetti.hrworker.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kevinzamperetti.hrworker.entities.Worker;

public interface WorkerRepository extends JpaRepository<Worker, Long> {

	
}
