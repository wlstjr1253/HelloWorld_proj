package kr.spring.flight.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.spring.flight.dao.FlightMapper;
import kr.spring.flight.domain.FlightCommand;

@Service("flightService")
public class FlightServiceImpl implements FlightService {
	
	@Resource
	private FlightMapper flightMapper;

	@Override
	public List<FlightCommand> selectFlightList(Map<String, Object> map) {
		return flightMapper.selectFlightList(map);
	}
	
	@Override
	public int selectFlightRowCount(Map<String, Object> map) {
		return flightMapper.selectFlightRowCount(map);
	}

	@Override
	public FlightCommand selectFlight(Integer fsi_idx) {	
		return flightMapper.selectFlight(fsi_idx);
	}

/*	@Override
	public int getFr_id() {
		return flightMapper.getFr_id();
	}*/
	
	@Override
	public void flightRsrv(Map<String, Object> map) {
		flightMapper.flightRsrv(map);
	}

}
