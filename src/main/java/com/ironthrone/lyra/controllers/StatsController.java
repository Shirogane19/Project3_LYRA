package com.ironthrone.lyra.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ironthrone.lyra.contracts.StatsRequest;
import com.ironthrone.lyra.contracts.StatsResponse;
import com.ironthrone.lyra.services.StatsServiceInterface;

@RestController
@RequestMapping(value ="rest/protected/stats")

public class StatsController {
	
@Autowired private StatsServiceInterface statsService;


	/**
	 * Retorna los stats de una institucion dependiendo del ID
	 * @param statsRequest
	 * @return Stats Response
	 */
	@RequestMapping(value ="/getStats", method = RequestMethod.POST)
	public StatsResponse getById(@RequestBody StatsRequest request){	
			
		StatsResponse stats = new StatsResponse();
		stats.setCode(200);
		stats.setCodeMessage("stats fetch success");
		stats.setStats(statsService.getStatsByInstitution(request));
		
		return stats;		
	}

}
