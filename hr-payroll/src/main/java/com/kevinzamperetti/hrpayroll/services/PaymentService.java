package com.kevinzamperetti.hrpayroll.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kevinzamperetti.hrpayroll.entities.Payment;
import com.kevinzamperetti.hrpayroll.entities.Worker;
import com.kevinzamperetti.hrpayroll.feignclients.WorkerFeignClient;

@Service
public class PaymentService {

	@Autowired
	private WorkerFeignClient workerFeignClient;
	
	public Payment getPayment(Long workerId, Integer days) {

		Worker worker = workerFeignClient.findById(workerId).getBody();		
		
		return new Payment(worker.getName(), worker.getDailyIncome(), days);
		
	}
	
}
