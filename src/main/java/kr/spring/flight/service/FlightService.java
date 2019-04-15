package kr.spring.flight.service;

import java.util.List;
import java.util.Map;

import kr.spring.flight.domain.FlightCommand;

public interface FlightService {
	// 항공권
	public List<FlightCommand> selectFlightList(Map<String, Object> map);
	public int selectFlightRowCount(Map<String, Object> map);
	public FlightCommand selectFlight(Integer fsi_idx);
	
/*	//항공권 아이디
	public int getFr_id();*/
	//항공권 예약	
	public void flightRsrv(Map<String,Object> map);

}
  