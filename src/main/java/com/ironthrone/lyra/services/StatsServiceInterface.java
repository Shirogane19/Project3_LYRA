package com.ironthrone.lyra.services;

import com.ironthrone.lyra.contracts.StatsRequest;
import com.ironthrone.lyra.pojo.StatsPOJO;

public interface StatsServiceInterface {
	
	StatsPOJO getStatsByInstitution(StatsRequest r);

	void startPeriodo();

}
