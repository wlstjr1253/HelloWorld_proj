package kr.spring.tour_info.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.spring.hotel.domain.HotelRsrvCommand;
import kr.spring.hotel.domain.HotelVwCommand;
import kr.spring.tour_info.domain.Tour_infoApplyCommand;
import kr.spring.tour_info.service.Tour_infoService;
import kr.spring.util.PagingUtil;

@Controller
public class Tour_infoApplyController {
	private Logger log = Logger.getLogger(this.getClass());
	private int rowCount = 10;
	private int pageCount = 10;
	
	@Resource
	private Tour_infoService tour_infoService;
	//신청자 리스트 등록
	@RequestMapping(value="/tour_info/apply.do", method=RequestMethod.GET)
	public String form(HttpSession session, Model model) {
	  String id = (String)session.getAttribute("user_id");
	
	Tour_infoApplyCommand command = new Tour_infoApplyCommand();
	command.setUser_id(id);
	
	model.addAttribute("command",command);
	
	return "tour_infoRsrv";
	}
	//등록 데이터 전송
	@RequestMapping(value="/tour_info/apply.do",method=RequestMethod.POST)
	public String submit(@ModelAttribute("command")@Valid Tour_infoApplyCommand tour_infoApplyCommand, BindingResult result,
			                                           HttpServletRequest request,
			                                            RedirectAttributes redirect) {
		//로그 처리
		if(log.isDebugEnabled()) {
			log.debug("<<tour_infoApplyCommand>>: " + tour_infoApplyCommand);
		}
		//등록하기
		tour_infoService.insertApply(tour_infoApplyCommand);
		
		redirect.addFlashAttribute("result","success");
		
		return "redirect:/tour_info/list.do";
	}
	//투어 신청 목록1(내가 신청한 목록)
	@RequestMapping("/tour_info/applyList.do")
	public ModelAndView process(@RequestParam(value="pageNum", defaultValue="1")
	                            int currentPage,
	                            @RequestParam(value="keyfield", defaultValue="")
                              	String keyfield,
	                            @RequestParam(value="keyword", defaultValue="")
	                            String keyword,
			                    HttpSession session) {
		Map<String,Object> map = new HashMap<String,Object>();//map 구조로 키필드와 키워드 넣어준다.
		
		map.put("keyfield", keyfield);
		map.put("keyword", keyword);
		
		String user_id = (String)session.getAttribute("user_id");
		
		map.put("user_id", user_id);
		
		//총 글의 개수 또는 검색된 글의 개수
		int count = tour_infoService.selectRowCountApply(map);
		
		if(log.isDebugEnabled()) {
			log.debug("<<count>> : " + count);
		}
		//페이징 처리 
		PagingUtil page = new PagingUtil(currentPage, count, rowCount, pageCount, "list.do");
		map.put("start", page.getStartCount());
   		map.put("end", page.getEndCount());
   		
		List<Tour_infoApplyCommand> list = null;
		if(count > 0) {
			list = tour_infoService.selectListApply(map);
		}
		
		if(log.isDebugEnabled()) {
   			log.debug("<<list>> : " + list);
   		}
		
		ModelAndView mav = new ModelAndView();//뷰처리
		mav.setViewName("tour_infoMemberList");//데피니션 설정 지정  
		mav.addObject("count", count);
		mav.addObject("list", list);
		mav.addObject("pagingHtml", page.getPagingHtml());
		
		return mav;
	}
	//투어 신청 목록2(나에게 신청한 회원)
		@RequestMapping("/tour_info/GuideList.do")
		public ModelAndView process2(@RequestParam(value="pageNum", defaultValue="1")
		                            int currentPage,
		                            @RequestParam(value="keyfield", defaultValue="")
	                              	String keyfield,
		                            @RequestParam(value="keyword", defaultValue="")
		                            String keyword,
				                    HttpSession session) {
			
			String writer_user = (String)session.getAttribute("user_id");
			
			
			Map<String,Object> map = new HashMap<String,Object>();//map 구조로 키필드와 키워드 넣어준다.
			
			map.put("keyfield", keyfield);
			map.put("keyword", keyword);
			map.put("user_id", writer_user);
			
			//총 글의 개수 또는 검색된 글의 개수
			int count = tour_infoService.selectRowCountGuide(map);
			
			if(log.isDebugEnabled()) {
				log.debug("<<count>> : " + count);
			}
			//페이징 처리 
			PagingUtil page = new PagingUtil(currentPage, count, rowCount, pageCount, "list.do");
			map.put("start", page.getStartCount());
	   		map.put("end", page.getEndCount());
	   		
			List<Tour_infoApplyCommand> list = null;
			if(count > 0) {
				list = tour_infoService.selectListGuide(map);
			}
			
			if(log.isDebugEnabled()) {
	   			log.debug("<<list>> : " + list);
	   		}
			
			ModelAndView mav = new ModelAndView();//뷰처리
			mav.setViewName("tour_infoGuideList");//데피니션 설정 지정  
			mav.addObject("count", count);
			mav.addObject("list", list);
			mav.addObject("pagingHtml", page.getPagingHtml());
			
			return mav;
		}
	
	//신청 삭제
	@RequestMapping("/tour_info/applyDelete.do")
	public String submit(@RequestParam("ta_idx") int ta_idx) {
		if(log.isDebugEnabled()) {
			log.debug("<<ta_idx>>:"+ta_idx);
		}
		
		tour_infoService.deleteApply(ta_idx);
		
		return "redirect:/tour_info/applyList.do";
	}
	//가이드 쪽에서 신청 삭제
		@RequestMapping("/tour_info/guideDelete.do")
		public String submit2(@RequestParam("ta_idx") int ta_idx) {
			if(log.isDebugEnabled()) {
				log.debug("<<ta_idx>>:"+ta_idx);
			}
			
			tour_infoService.deleteApply(ta_idx);
			
			return "redirect:/tour_info/GuideList.do";
		}
	
	//========================================신청 결제 페이지(진석이거 임시로 가져온 거)========================================= 
	/*@RequestMapping("/tour_info/tour_infoRsrv.do")
	public ModelAndView tour_infoRsrv(@ModelAttribute("command") @Valid HotelRsrvCommand hotelRsrvCommand, HttpSession session) {

		hotelRsrvCommand.setUser_id((String)session.getAttribute("user_id"));
		HotelVwCommand hotelVwCommand = tour_infoService.getRoomInfo(hotelRsrvCommand.getSr_id());
		hotelVwCommand.setSt_cvntl_list(tour_infoService.selectCvntlList(hotelVwCommand.getSt_cvntl_id()));
		
		if(log.isDebugEnabled()) {
			log.debug("<<hotelRsrvCommand>> : " + hotelRsrvCommand);
			log.debug("<<hotelVwCommand>> : " + hotelVwCommand);
		}
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("hotelRsrv");
		mav.addObject("rsrv", hotelRsrvCommand);
		mav.addObject("hotel", hotelVwCommand);

		return mav;
	}

	@RequestMapping("/tour_info/tour_infoResult.do")
	public ModelAndView hotelResult(@ModelAttribute("command") @Valid HotelRsrvCommand hotelRsrvCommand,
			@RequestParam("cp_num") int cp_num, @RequestParam("cp_pin_num") int cp_pin_num,
			@RequestParam("cp_year_month") String cp_year_month) {

		HotelVwCommand hotelVwCommand = hotelService.getRoomInfo(hotelRsrvCommand.getSr_id());
		hotelVwCommand.setSt_cvntl_list(hotelService.selectCvntlList(hotelVwCommand.getSt_cvntl_id()));
		
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("p_sr_id", hotelVwCommand.getSr_id());
		map.put("p_user_id", hotelRsrvCommand.getUser_id());
		map.put("p_total_pc", hotelRsrvCommand.getSrl_total_pc());
		map.put("p_check_in_dt", hotelRsrvCommand.getSrl_check_in_dt());
		map.put("p_check_out_dt", hotelRsrvCommand.getSrl_check_out_dt());
		map.put("p_srl_nm", hotelRsrvCommand.getSrl_nm());
		map.put("p_srl_email", hotelRsrvCommand.getSrl_email());
		map.put("p_srl_phone", hotelRsrvCommand.getSrl_phone());
		map.put("p_srl_adult_pp", hotelRsrvCommand.getSrl_adult_pp());
		map.put("p_srl_kid_pp", hotelRsrvCommand.getSrl_kid_pp());
		map.put("p_num", cp_num);
		map.put("p_pin_num", cp_pin_num);
		
		String cp_ym[] = cp_year_month.split("/");
		
		map.put("p_year", Integer.parseInt(cp_ym[1]));
		map.put("p_month", Integer.parseInt(cp_ym[0]));
		
		if(log.isDebugEnabled()) {
			log.debug("<<hotelRsrvCommand>> : " + hotelRsrvCommand);
			log.debug("<<hotelVwCommand>> : " + hotelVwCommand);
			log.debug("<<map>> : " + map );
		}
		
		hotelService.hotelRsrv(map);
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("hotelResult");
		mav.addObject("rsrv", hotelRsrvCommand);
		mav.addObject("hotel", hotelVwCommand);

		return mav;
	}*/
	
}
