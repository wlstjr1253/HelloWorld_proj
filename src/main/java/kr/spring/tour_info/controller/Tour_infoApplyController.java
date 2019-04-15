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
	//��û�� ����Ʈ ���
	@RequestMapping(value="/tour_info/apply.do", method=RequestMethod.GET)
	public String form(HttpSession session, Model model) {
	  String id = (String)session.getAttribute("user_id");
	
	Tour_infoApplyCommand command = new Tour_infoApplyCommand();
	command.setUser_id(id);
	
	model.addAttribute("command",command);
	
	return "tour_infoApply";
	}
	//��� ������ ����
	@RequestMapping(value="/tour_info/apply.do",method=RequestMethod.POST)
	public String submit(@ModelAttribute("command")@Valid Tour_infoApplyCommand tour_infoApplyCommand, BindingResult result,
			                                           HttpServletRequest request,
			                                            RedirectAttributes redirect) {
		//�α� ó��
		if(log.isDebugEnabled()) {
			log.debug("<<tour_infoApplyCommand>>: " + tour_infoApplyCommand);
		}
		//����ϱ�
		tour_infoService.insertApply(tour_infoApplyCommand);
		
		redirect.addFlashAttribute("result","success");
		
		return "redirect:/tour_info/list.do";
	}
	//��û�� ��� (���̵� �������� �� �� �ִ� �κ�?)
	@RequestMapping("/tour_info/applyList.do")
	public ModelAndView process(@RequestParam(value="pageNum", defaultValue="1")
	                            int currentPage,
	                            @RequestParam(value="keyfield", defaultValue="")
                              	String keyfield,
	                            @RequestParam(value="keyword", defaultValue="")
	                            String keyword,
			                    HttpSession session) {
		Map<String,Object> map = new HashMap<String,Object>();//map ������ Ű�ʵ�� Ű���� �־��ش�.
		
		map.put("keyfield", keyfield);
		map.put("keyword", keyword);
		
		String user_id = (String)session.getAttribute("user_id");
		
		map.put("user_id", user_id);
		
		//�� ���� ���� �Ǵ� �˻��� ���� ����
		int count = tour_infoService.selectRowCountApply(map);
		
		if(log.isDebugEnabled()) {
			log.debug("<<count>> : " + count);
		}
		//����¡ ó�� 
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
		
		ModelAndView mav = new ModelAndView();//��ó��
		mav.setViewName("tour_infoMemberList");//���Ǵϼ� ���� ����  
		mav.addObject("count", count);
		mav.addObject("list", list);
		mav.addObject("pagingHtml", page.getPagingHtml());
		
		return mav;
	}
	
}
