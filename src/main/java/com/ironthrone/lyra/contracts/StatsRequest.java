package com.ironthrone.lyra.contracts;

import com.ironthrone.lyra.pojo.StatsPOJO;

public class StatsRequest extends BaseRequest{

	private StatsPOJO stats;
	
	public StatsRequest() {
		// TODO Auto-generated constructor stub
	}

	public StatsPOJO getStats() {
		return stats;
	}

	public void setStats(StatsPOJO stats) {
		this.stats = stats;
	}
}
