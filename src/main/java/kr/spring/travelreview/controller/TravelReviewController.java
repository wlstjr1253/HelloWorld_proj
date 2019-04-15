package kr.spring.travelreview.controller;

import java.util.Collections;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.spring.board.domain.BoardCommand;
import kr.spring.board.domain.BoardReplyCommand;
import kr.spring.board.service.BoardService;
import kr.spring.travelreview.domain.TravelReviewCommand;
import kr.spring.travelreview.service.TravelReviewService;
import kr.spring.util.PagingUtil;

@Controller
public class TravelReviewController {
	private Logger log = Logger.getLogger(this.getClass());
	private int rowCount = 10;
	private int pageCount = 10;
	
	@Resource
	private TravelReviewService travelReviewService;

	// ================ �Խ��� �� ��� ================ //
	// ��� ��
	@RequestMapping(value="/review/write.do", method=RequestMethod.GET)
	public String form(HttpSession session, Model model) {

		String user_id = (String)session.getAttribute("user_id");

		TravelReviewCommand command = new TravelReviewCommand();
		command.setUser_id(user_id);

		model.addAttribute("command", command);

		return "reviewWrite";
	}

	// ���۵� ������ ó��
	@RequestMapping(value="/review/write.do", method=RequestMethod.POST)
	public String submit(@ModelAttribute("command")
	@Valid TravelReviewCommand travelReviewCommand, 
	BindingResult result, 
	HttpServletRequest request,
	RedirectAttributes redirect) {
		if (log.isDebugEnabled()) {
			log.debug("<<travelReviewCommand>> : " + travelReviewCommand);
		}

		// ��ȿ�� üũ
		if (result.hasErrors()) {
			return "reviewWrite";
		}

		// �۾���
		travelReviewService.insert(travelReviewCommand);

		// RedirectAttributes ��ü�� �����̷�Ʈ ������ �� ���� ���Ǵ�
		// �����͸� ����.
		// �������� �����͸� ���������� URI�󿡴� ������ �ʴ� ������ ��������
		// ���·� ����
		redirect.addFlashAttribute("result", "success");

		return "redirect:/review/list.do";
	}
	// ================ �Խ��� �� ��� ================ //
	@RequestMapping("/review/list.do")
	public ModelAndView process(
			@RequestParam(value="pageNum", defaultValue="1")
			int currentPage,
			@RequestParam(value="keyfield", defaultValue="")
			String keyfield,
			@RequestParam(value="keyword", defaultValue="")
			String keyword
			) {
		Map<String,Object> map = 
				new HashMap<String, Object>();

		map.put("keyfield", keyfield);
		map.put("keyword", keyword);

		// �� ���� ���� �Ǵ� �˻��� ���� ����
		int count = travelReviewService.selectRowCount(map);

		if(log.isDebugEnabled()) {
			log.debug("<<count>> : " + count);
		}

		PagingUtil page =
				new PagingUtil(keyfield, keyword, 
						currentPage, count, 
						rowCount, pageCount, "list.do");
		map.put("start", page.getStartCount());
		map.put("end", page.getEndCount());

		List<TravelReviewCommand> list = null;
		if (count > 0) {
			list = travelReviewService.selectList(map);
		}

		ModelAndView mav = new ModelAndView();
		mav.setViewName("reviewList");
		mav.addObject("count", count);
		mav.addObject("list", list);
		mav.addObject("pagingHtml", page.getPagingHtml());

		return mav;
	}
	// ================ �Խ��� �� �� ================ //
	@RequestMapping("/review/detail.do")
	public ModelAndView process(@RequestParam("tr_idx") int tr_idx) {

		if (log.isDebugEnabled()) {
			log.debug("<<tr_idx>> : " + tr_idx);
		}

		// �ش� ���� ��ȸ�� ����
		travelReviewService.updateHit(tr_idx);

		TravelReviewCommand review = travelReviewService.selectBoard(tr_idx);
		// view name   �Ӽ���     �Ӽ���
		return new ModelAndView("reviewView", "review", review);
	}
	//�̹��� ���
	@RequestMapping("/review/imageView.do")
	public ModelAndView viewImage(@RequestParam("tr_idx") int tr_idx) {

		TravelReviewCommand review = 
				travelReviewService.selectBoard(tr_idx);

		ModelAndView mav = new ModelAndView();
		mav.setViewName("imageView");
		mav.addObject("imageFile", review.getTr_img());
		mav.addObject("filename", "image.jpg");

		return mav;
	}
	// ================ �Խ��� �� ���� ================ //
	@RequestMapping("/review/delete.do")
	public String submit(@RequestParam("tr_idx") int tr_idx) {

		if (log.isDebugEnabled()) {
			log.debug("<<tr_idx>> : " + tr_idx);
		}

		// �� ����
		travelReviewService.delete(tr_idx);

		return "redirect:/review/list.do";
	}
	// ================ �Խ��� �� ���� ================ //
	// ���� ��
	@RequestMapping(value="/review/update.do", 
			method=RequestMethod.GET)
	public String form(@RequestParam("tr_idx") int tr_idx, 
			Model model) {

		TravelReviewCommand travelReviewCommand =	travelReviewService.selectBoard(tr_idx);

		model.addAttribute("command", travelReviewCommand);

		return "reviewModify";
	}
	// ���� ������ ���۵� ������ ó��
	@RequestMapping(value="/review/update.do", method=RequestMethod.POST)
	public String submit(@ModelAttribute("command") 
	@Valid TravelReviewCommand reviewCommand,
	BindingResult result,
	HttpServletRequest request) {

		if (log.isDebugEnabled()) {
			log.debug("<<reviewCommand>> : " + reviewCommand);
		}

		// ������ ��ȿ�� üũ
		if(result.hasErrors()) {
			return "reviewModify";
		}


		// �� ����
		travelReviewService.update(reviewCommand);

		return "redirect:/review/list.do";
	}
}
