package kr.spring.member.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import kr.spring.board.domain.BoardCommand;
import kr.spring.member.domain.MemberCommand;
import kr.spring.member.service.MemberService;
import kr.spring.util.PagingUtil;

@Controller
public class MemberAdminController {
	private Logger log = Logger.getLogger(this.getClass());
	private int rowCount = 10;
	private int pageCount = 10;
	
	@Resource
	private MemberService memberService;
	
	// �ڹٺ�(Ŀ�ǵ� ��ü) �ʱ�ȭ
	@ModelAttribute("command")
	public MemberCommand initCommand() {
		return new MemberCommand();
	}
	
	// =============== ȸ����� ���� ===============//
	@RequestMapping("/member/admin_list.do")
	public ModelAndView process(
			@RequestParam(value="pageNum", defaultValue="1")int currentPage,
			@RequestParam(value="keyfield", defaultValue="")String keyfield, 
			@RequestParam(value="keyword", defaultValue="")String keyword) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("keyfield", keyfield);
		map.put("keyword", keyword);
		
		// �� ���� ���� �Ǵ� �˻��� ���� ����
		int count = memberService.selectRowCount(map);
		
		if (log.isDebugEnabled()) {
			log.debug("<<count>> : " + count);
		}
		
		PagingUtil page = new PagingUtil(keyfield, keyword, 
				currentPage, count, rowCount, pageCount, "admin_list.do");
		
		map.put("start", page.getStartCount());
		map.put("end", page.getEndCount());
		
		List<MemberCommand> list = null;
		if(count > 0) {
			list = memberService.selectList(map);
		}
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("admin_memberList");
		mav.addObject("count", count);
		mav.addObject("list", list);
		mav.addObject("pagingHtml", page.getPagingHtml());
		
		return mav;
	}
	
	//============ȸ�� ���===========/
	@RequestMapping("/member/memberList.do")
	public ModelAndView processMember(
			@RequestParam(value="pageNum", defaultValue="1")int currentPage,
			@RequestParam(value="keyfield", defaultValue="")String keyfield,
			@RequestParam(value="keyword", defaultValue="")String keyword
			) {
		Map<String,Object> map = new HashMap<String, Object>();
		
		map.put("keyfield", keyfield);
		map.put("keyword", keyword);
		
		// �� ȸ���� �Ǵ� �˻��� ȸ���� ����
		int count = memberService.selectRowCount(map);
		
		if(log.isDebugEnabled()) {
			log.debug("<<count>> : " + count);
		}
		
		PagingUtil page = new PagingUtil(keyfield, keyword,currentPage, count,rowCount, pageCount, "memberList.do");
		map.put("start", page.getStartCount());
		map.put("end", page.getEndCount());
		
		List<MemberCommand> list = null;
		if (count > 0) {
			list = memberService.selectList(map);
		}
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("memberList");
		mav.addObject("count", count);
		mav.addObject("memberList", list);
		mav.addObject("pagingHtml", page.getPagingHtml());
		
		return mav;
	}
	//================ȸ�� �� ����============//
	@RequestMapping("/member/adminMemberDetail.do")
	public ModelAndView process(@RequestParam("user_id") String user_id) {
		if (log.isDebugEnabled()) {
			log.debug("<<user_id>> : " + user_id);
		}
		MemberCommand member = memberService.selectMember(user_id);
							    // view name   �Ӽ���     �Ӽ���
		return new ModelAndView("memberView", "member", member);
	}
	
	//================���̵� ���================//
	@RequestMapping("/member/guideList.do")
	public ModelAndView processGuide(
			@RequestParam(value="pageNum", defaultValue="1")int currentPage,
			@RequestParam(value="keyfield", defaultValue="")String keyfield,
			@RequestParam(value="keyword", defaultValue="")String keyword) {
		Map<String,Object> map = new HashMap<String, Object>();
		
		map.put("keyfield", keyfield);
		map.put("keyword", keyword);
		
		// ȸ���� Ȥ�� �˻��� ȸ�� ��
		int count = memberService.selectGuideRowCount(map);
		
		if(log.isDebugEnabled()) {
			log.debug("<<count>> : " + count);
		}
		
		PagingUtil page = new PagingUtil(keyfield, keyword,currentPage, count,rowCount, pageCount, "guideList.do");
		map.put("start", page.getStartCount());
		map.put("end", page.getEndCount());
		
		List<MemberCommand> list = null;
		if (count > 0) {
			list = memberService.selectGuideList(map);
		}
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("guideList");
		mav.addObject("count", count);
		mav.addObject("guideList", list);
		mav.addObject("pagingHtml", page.getPagingHtml());
		
		return mav;
	}
	
	//=========���̵� ����========//
	@RequestMapping("/member/confirmGuide.do")
	public String confirmGuide(@RequestParam("user_id") String user_id) {
		if (log.isDebugEnabled()) {
			log.debug("<<user_id>> : " + user_id);
		}
		memberService.confirmGuide(user_id);
		
		
		//DB�� auth �� ������ ���
		//�ش� ���̵�� �α����� ������ ���ǿ��� auth���� ����ȴٸ� auth�� üũ�ؼ� session�� �ٽ� ���� �־� ��� �Ѵ�.
		
		//DB�� ���� �����ϰ� session�� �ٽ� �����ϰ� �Ѵ�.
		
		
		return "redirect:/member/guideList.do";
	}
	//=========���̵� �ź�========//
	@RequestMapping("/member/refuseGuide.do")
	public String revokeGuide(@RequestParam("user_id") String user_id,
							HttpSession session) {
		if (log.isDebugEnabled()) {
			log.debug("<<user_id>> : " + user_id);
		}
		memberService.refuseGuide(user_id);
		
		return "redirect:/member/guideList.do";
	}
	
	
}
