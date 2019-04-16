package kr.spring.main.controller;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import kr.spring.tour_info.domain.Tour_infoCommand;
import kr.spring.tour_info.service.Tour_infoService;
import kr.spring.travelreview.domain.TravelReviewCommand;
import kr.spring.travelreview.service.TravelReviewService;

@Controller
public class MainController {
 
	private Logger log = Logger.getLogger(this.getClass());

	@Resource
	private Tour_infoService tour_infoService;
	@Resource
	private TravelReviewService travelReviewService;
	/*private HotelService hotelService;*/

	@RequestMapping("/main/main.do")
	public ModelAndView hotelList() {

		if(log.isDebugEnabled()) log.debug("<<main>>");
		
		List<Tour_infoCommand> tour_list = tour_infoService.selectMainList();
		List<TravelReviewCommand> review = travelReviewService.selectMainList();
		
		if(log.isDebugEnabled()) {
			log.debug("<<tour_list>> : " + tour_list);
			log.debug("<<review>> : " + review);
		}
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("main");
		mav.addObject("tour_list", tour_list);
		mav.addObject("review", review);

		return mav;
	}
}
