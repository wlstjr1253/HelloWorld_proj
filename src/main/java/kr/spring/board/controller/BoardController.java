package kr.spring.board.controller;

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
import kr.spring.util.PagingUtil;

@Controller
public class BoardController {
	private Logger log = Logger.getLogger(this.getClass());
	private int rowCount = 10;
	private int pageCount = 10;
	
	@Resource
	private BoardService boardService;
	
	// ================ �Խ��� �� ��� ================ //
	// ��� ��
	@RequestMapping(value="/board/write.do", method=RequestMethod.GET)
	public String form(HttpSession session, Model model) {
		
		String id = (String)session.getAttribute("user_id");
		
		BoardCommand command = new BoardCommand();
		command.setId(id);
		
		model.addAttribute("command", command);
		
		return "boardWrite";
	}
	
	// ���۵� ������ ó��
	@RequestMapping(value="/board/write.do", method=RequestMethod.POST)
	public String submit(@ModelAttribute("command")
						@Valid BoardCommand boardCommand, 
						BindingResult result, 
						HttpServletRequest request,
						RedirectAttributes redirect) {
		if (log.isDebugEnabled()) {
			log.debug("<<boardCommand>> : " + boardCommand);
		}
		
		// ��ȿ�� üũ
		if (result.hasErrors()) {
			return "boardWrite";
		}
		
		// ip����
		boardCommand.setIp(request.getRemoteAddr());
		
		// �۾���
		boardService.insert(boardCommand);
		
		// RedirectAttributes ��ü�� �����̷�Ʈ ������ �� ���� ���Ǵ�
		// �����͸� ����.
		// �������� �����͸� ���������� URI�󿡴� ������ �ʴ� ������ ��������
		// ���·� ����
		redirect.addFlashAttribute("result", "success");
		
		return "redirect:/board/list.do";
	}
	
	// ================ �Խ��� �� ��� ================ //
	@RequestMapping("/board/list.do")
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
		int count = boardService.selectRowCount(map);
		
		if(log.isDebugEnabled()) {
			log.debug("<<count>> : " + count);
		}
		
		PagingUtil page =
				new PagingUtil(keyfield, keyword, 
						currentPage, count, 
						rowCount, pageCount, "list.do");
		map.put("start", page.getStartCount());
		map.put("end", page.getEndCount());
		
		List<BoardCommand> list = null;
		if (count > 0) {
			list = boardService.selectList(map);
		}
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("boardList");
		mav.addObject("count", count);
		mav.addObject("list", list);
		mav.addObject("pagingHtml", page.getPagingHtml());
		
		return mav;
	}
	
	// ================ �Խ��� �� �� ================ //
	@RequestMapping("/board/detail.do")
	public ModelAndView process(@RequestParam("num") int num) {
		
		if (log.isDebugEnabled()) {
			log.debug("<<num>> : " + num);
		}
		
		// �ش� ���� ��ȸ�� ����
		boardService.updateHit(num);
		
		BoardCommand board = boardService.selectBoard(num);
							    // view name   �Ӽ���     �Ӽ���
		return new ModelAndView("boardView", "board", board);
	}
	
	// ���� �ٿ�ε�
	@RequestMapping("/board/file.do")
	public ModelAndView download(@RequestParam("num") int num) {
		
		BoardCommand board =
				boardService.selectBoard(num);
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("downloadView");
		mav.addObject("downloadFile", board.getUploadfile());
		mav.addObject("filename", board.getFilename());
		
		return mav;
	}
	
	// ================ �Խ��� �� ���� ================ //
	// ���� ��
	@RequestMapping(value="/board/update.do", 
			method=RequestMethod.GET)
	public String form(@RequestParam("num") int num, 
					Model model) {
		
		BoardCommand boardCommand =	boardService.selectBoard(num);
		
		model.addAttribute("command", boardCommand);
		
		return "boardModify";
	}
	// ���� ������ ���۵� ������ ó��
	@RequestMapping(value="/board/update.do", method=RequestMethod.POST)
	public String submit(@ModelAttribute("command") 
						@Valid BoardCommand boardCommand,
						BindingResult result,
						HttpServletRequest request) {
		
		if (log.isDebugEnabled()) {
			log.debug("<<boardCommand>> : " + boardCommand);
		}
		
		// ������ ��ȿ�� üũ
		if(result.hasErrors()) {
			return "boardModify";
		}
		
		// ip����
		boardCommand.setIp(request.getRemoteAddr());
		
		// �� ����
		boardService.update(boardCommand);
		
		return "redirect:/board/list.do";
	}
	
	
	// ================ �Խ��� �� ���� ================ //
	@RequestMapping("/board/delete.do")
	public String submit(@RequestParam("num") int num) {
		
		if (log.isDebugEnabled()) {
			log.debug("<<num>> : " + num);
		}
		
		// �� ����
		boardService.delete(num);
		
		return "redirect:/board/list.do";
	}
	
	// ��� ���
	@RequestMapping("/board/listReply.do")
	@ResponseBody
	public Map<String, Object> getList(@RequestParam(value="pageNum", defaultValue="1") int currentPage, 
			@RequestParam("num") int num) {
		
		if (log.isDebugEnabled()) {
			log.debug("<<currentPage>> : " + currentPage);
			log.debug("<<num>> : " + num);
		}
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("num", num);
		
		// �� ���� ����
		int count = boardService.selectRowCountReply(map);
		
		PagingUtil page = new PagingUtil(currentPage, count,
				rowCount, pageCount, null);
		
		map.put("start", page.getStartCount());
		map.put("end", page.getEndCount());
		
		List<BoardReplyCommand> list = null;
		if(count > 0) {
			list = boardService.selectListReply(map);
		} else {
			list = Collections.emptyList();
		}
		
		Map<String, Object> mapJson = new HashMap<String, Object>();
		mapJson.put("count", count);
		mapJson.put("rowCount", rowCount);
		mapJson.put("list", list);
		
		return mapJson;
	}
}
