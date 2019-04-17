package kr.spring.flight.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import kr.spring.flight.domain.FlightCommand;
import kr.spring.flight.domain.FlightRsrvCommand;
import kr.spring.flight.service.FlightService;
import kr.spring.hotel.domain.HotelRsrvCommand;
import kr.spring.hotel.domain.HotelVwCommand;
import kr.spring.util.PagingUtil;

@Controller
public class FlightController {
	private Logger log = Logger.getLogger(this.getClass());
	private int rowCount = 10;
	private int pageCount = 10;
	
	@Resource
	private FlightService flightService;
	
	/*// 자바 빈 초기화
	@ModelAttribute("fCommand")
	public FlightCommand initCommand() {
				
		return new FlightCommand();
	}*/

	// =============== 항공권 조회 =============== //
	@RequestMapping(value="/flight/list.do")
	public ModelAndView process(
			@RequestParam(value="flight_s_nc", defaultValue="ALL") 
			String flight_s_nc,
			@RequestParam(value="flight_a_nc", defaultValue="ALL") 
			String flight_a_nc,
			@RequestParam(value="pageNum", defaultValue="1")
			int currentPage,
			@RequestParam(value="keyfield",defaultValue="")
			String keyfield,
			@RequestParam(value="keyword", defaultValue="")
			String keyword) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("flight_s_nc", flight_s_nc);
		map.put("flight_a_nc", flight_a_nc);
		map.put("keyfield", keyfield);
		map.put("keyword", keyword);
		
		// 등록된 항공권 갯수
		int flightCount = flightService.selectFlightRowCount(map);
		
		if (log.isDebugEnabled()) {
			log.debug("<<flightCount>> : " + flightCount);
		}
		
		PagingUtil page =
				new PagingUtil(keyfield, keyword, currentPage, 
						flightCount, rowCount, pageCount, "list.do");
		map.put("start", page.getStartCount());
		map.put("end", page.getEndCount());
		
		List<FlightCommand> flightList = null;	
		if (flightCount > 0) {
			flightList = flightService.selectFlightList(map);
		}
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("flightList");
		mav.addObject("fCount", flightCount);
		mav.addObject("fList", flightList);
		mav.addObject("pagingHtml", page.getPagingHtml());
		
		return mav;
		
	} 
	
	@RequestMapping("/flight/flightRsrv.do")
	public ModelAndView flightRsrv(@ModelAttribute("command") @Valid FlightRsrvCommand flightRsrvCommand, HttpSession session) {

		flightRsrvCommand.setUser_id((String)session.getAttribute("user_id"));
		FlightCommand flightCommand = flightService.selectFlight(flightRsrvCommand.getFsi_idx());
		
		if(log.isDebugEnabled()) {
			log.debug("<<flightRsrvCommand>> : " + flightRsrvCommand);
			log.debug("<<flightCommand>> : " + flightCommand);
		}
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("flightRsrv");
		mav.addObject("rsrv", flightRsrvCommand);
		mav.addObject("flightCommand", flightCommand);

		return mav;
	}
	
	@RequestMapping("/flight/flightResult.do")
	public ModelAndView flightResult(@ModelAttribute("command") @Valid FlightRsrvCommand flightRsrvCommand,
			@RequestParam("cp_num") int cp_num, @RequestParam("cp_pin_num") int cp_pin_num,
			@RequestParam("cp_year_month") String cp_year_month) {

		FlightCommand flightCommand = flightService.selectFlight(flightRsrvCommand.getFsi_idx());

		Map<String,Object> map = new HashMap<String, Object>();
		map.put("p_fsi_idx", flightCommand.getFsi_idx());
		map.put("p_user_id", flightRsrvCommand.getUser_id());
		map.put("p_rsrv_seat", flightRsrvCommand.getFr_rsrv_seat_type());
		map.put("p_fr_adult_pp", flightRsrvCommand.getFr_adult_pp());
		map.put("p_fr_kid_pp", flightRsrvCommand.getFr_kid_pp());
		map.put("p_fr_nm", flightRsrvCommand.getFr_nm());
		map.put("p_fr_email", flightRsrvCommand.getFr_email());
		map.put("p_fr_phone", flightRsrvCommand.getFr_phone());
		map.put("p_fr_passport", flightRsrvCommand.getFr_passport());
		map.put("p_fr_age", flightRsrvCommand.getFr_age());
		map.put("p_fr_fnm", flightRsrvCommand.getFr_fnm());
		map.put("p_total_pc", flightRsrvCommand.getFr_total_pc());
		map.put("p_num", cp_num);
		map.put("p_pin_num", cp_pin_num);
		
		String cp_ym[] = cp_year_month.split("/");
		
		map.put("p_year", Integer.parseInt(cp_ym[1]));
		map.put("p_month", Integer.parseInt(cp_ym[0]));
		
		if(log.isDebugEnabled()) {
			log.debug("<<flightRsrvCommand>> : " + flightRsrvCommand);
			log.debug("<<flightCommand>> : " + flightCommand);
			log.debug("<<map>> : " + map );
		}
		
		flightService.flightRsrv(map);
		
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("flightResult");
		mav.addObject("rsrv", flightRsrvCommand);
		mav.addObject("flightCommand", flightCommand);

		return mav;
	}
}
