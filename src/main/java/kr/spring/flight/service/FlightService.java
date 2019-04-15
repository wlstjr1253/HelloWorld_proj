package kr.spring.flight.service;

import java.util.List;
import java.util.Map;

import kr.spring.flight.domain.FlightCommand;
import kr.spring.flight.domain.FlightPayCommand;
import kr.spring.flight.domain.FlightRsrvCommand;

public interface FlightService {
	// �װ���
	public List<FlightCommand> selectFlightList(Map<String, Object> map);
	public int selectFlightRowCount(Map<String, Object> map);
	//public FlightCommand selectFlight(Integer fsi_idx);
	
	//�װ��� ���̵�
	public int getFr_id();
	//�װ��� ����	
	public void insertFlightRsrv(FlightRsrvCommand flightRsrv);	
	public FlightCommand selectFlight(Integer fsi_idx);
	
	//����
	public void insertFlightPay(FlightPayCommand flightPay);
	
}
  