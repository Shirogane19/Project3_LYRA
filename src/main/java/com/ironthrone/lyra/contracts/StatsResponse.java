package com.ironthrone.lyra.contracts;

import com.ironthrone.lyra.pojo.StatsPOJO;

public class StatsResponse extends BaseResponse{

	private StatsPOJO stats;
	
	public StatsResponse() {
		// TODO Auto-generated constructor stub
	}

	public StatsPOJO getStats() {
		return stats;
	}

	public void setStats(StatsPOJO stats) {
		this.stats = stats;
	}
	

}
