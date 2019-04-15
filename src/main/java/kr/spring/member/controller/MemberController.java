package kr.spring.member.controller;

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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import kr.spring.util.CipherTemplate;
import kr.spring.member.domain.MemberCommand;
import kr.spring.member.service.MemberService;
import kr.spring.util.LoginException;
import kr.spring.util.PagingUtil;

@Controller
public class MemberController {
	private Logger log = Logger.getLogger(this.getClass());
	private int rowCount = 10;
	private int pageCount = 10;
	
	@Resource
	private MemberService memberService;
	
	@Resource CipherTemplate cipherAES;
	
	// �ڹٺ�(Ŀ�ǵ� ��ü) �ʱ�ȭ
	@ModelAttribute("command")
	public MemberCommand initCommand() {
		return new MemberCommand();
	}
	
	// =============== ȸ�� ���� ===============// 
	// ȸ����� �� ȣ��
	@RequestMapping(value="/member/write.do",method=RequestMethod.GET)
	public String form() {
		return "memberWrite";
	}
	
	// ȸ������ ������ ����
	@RequestMapping(value="/member/write.do",method=RequestMethod.POST)
	public String submit(@ModelAttribute("command") 
						@Valid MemberCommand memberCommand,
						BindingResult result) {
		if (log.isDebugEnabled()) {
			log.debug("<<memberCommand>> : "+memberCommand);
		}
		//������ ������ �ٽ� �� ȣ��
		if (result.hasErrors()) {
			return form();
		}
		memberCommand.setUser_pw(cipherAES.encrypt(memberCommand.getUser_pw()));
		
		//ȸ�� ����
							//�ڹٺ��� �ѱ��.
		memberService.insert(memberCommand);
		
		return "redirect:/main/main.do";
	}
	
	// =============== ȸ�� �α��� =============== //
	// �α��� ��
	@RequestMapping(value="/member/login.do", method=RequestMethod.GET)
	public String formLogin() {
		return "memberLogin";
	}
	
	// �α��� ���� ���۵� ������ ó��
	@RequestMapping(value="/member/login.do", method=RequestMethod.POST)
	public String submitLogin(@ModelAttribute("command") 
							  @Valid MemberCommand memberCommand, 
							  BindingResult result, 
							  HttpSession session) {
		if (log.isDebugEnabled()) {
			log.debug("<<memberCommand>> : " + memberCommand);
		}
		
		// ��ȿ�� üũ(id�� passwd �ʵ常 üũ)
		if (result.hasFieldErrors("user_id") || result.hasFieldErrors("user_pw")) {
			return formLogin();
		}
		
		// �α��� üũ(id, ��й�ȣ ��ġ ���� üũ)
		try {
			MemberCommand member = 
					memberService.selectMember(memberCommand.getUser_id());
			boolean check = false;
			
			if (member != null) {
				// ��й�ȣ ��ġ ���� üũ
				check = member.isCheckedPasswd(memberCommand.getUser_pw());
			}
			
			if (check) {
				// ���� ����, �α��� ó��
				session.setAttribute("user_id", member.getUser_id());
				session.setAttribute("user_auth", member.getUser_auth());
				
				if (log.isDebugEnabled()) {
					log.debug("<<���� ����>>");
					log.debug("<<user_id>> : " + member.getUser_id());
					log.debug("<<user_auth>> : " + member.getUser_auth());
				}
				
				return "redirect:/main/main.do";
				
			} else { 
				// ���� ����
				throw new LoginException();
			}
		} catch(LoginException e) {
			// ���� ���з� �α����� ȣ��
			result.reject("invalidIdOrPassword"); // �޽��� ó��
			
			if (log.isDebugEnabled()) {
				log.debug("<<���� ����>>");
			}
			
			return formLogin(); // �α����� ȣ��
		}
		
	}
	// header���� member auth�� ���������� �Ѵ�.
	
	// =============== ȸ�� �α׾ƿ� =============== //
	@RequestMapping("/member/logout.do")
	public String processLogout(HttpSession session) {
		//�α׾ƿ�
		session.invalidate();
		
		return "redirect:/main/main.do";
	}
	
	// =============== ȸ�������� =============== //
	@RequestMapping("/member/detail.do")
	public String process(HttpSession session,Model model) {
		String user_id=(String)session.getAttribute("user_id");
		
		MemberCommand member = memberService.selectMember(user_id);
		
		if (log.isDebugEnabled()) {
			log.debug("<<memberCommand>> : "+member);
		}
		
		//�̾Ƴ� �����ʹ� �𵨿� �־��ش�. �信�� �ڹٺ� �����ؼ� ��� �� �� �ֵ���.
		model.addAttribute("member",member);
		
		return "memberView";
	}
	
	
	//=========���̵� ��û========//
	@RequestMapping("/member/applyGuide.do")
	public String submitGuide(HttpSession session) {
		String user_id=(String)session.getAttribute("user_id");
		memberService.applyGuide(user_id);
		
		return "redirect:/member/detail.do";
	}
	
	//=========���̵� ���========//
	@RequestMapping("/member/cancelGuide.do")
	public String revokeGuide(HttpSession session) {
		String user_id=(String)session.getAttribute("user_id");
		memberService.cancelGuide(user_id);
		
		MemberCommand member=memberService.selectMember(user_id);
		session.setAttribute("user_auth", member.getUser_auth());
		
		return "redirect:/member/detail.do";
	}
	
	
	// =============== ȸ���������� =============== //
	// ������
	@RequestMapping(value="/member/update.do",method=RequestMethod.GET)
	public String formUpdate(HttpSession session,Model model) {
		String user_id=(String)session.getAttribute("user_id");
		
		MemberCommand member=memberService.selectMember(user_id);
		
		model.addAttribute("command",member);
		
		return "memberModify";
	}
	
	// ���������� ���۵� ������ ó��
	@RequestMapping(value="/member/update.do",method=RequestMethod.POST)
	public String submitUpdate(@ModelAttribute("command") 
								@Valid MemberCommand memberCommand,
								BindingResult result) {
		if (log.isDebugEnabled()) {
			log.debug("<<memberCommand>> : "+memberCommand);
		}
		if (result.hasErrors()) {
			return "memberModify";
		}
		//ȸ������ ����
		memberService.update(memberCommand);
		
		return "redirect:/member/detail.do";
	}
	
	// ===============��й�ȣ ����=============== //
	//���� ��
	@RequestMapping(value="/member/changePassword.do",method=RequestMethod.GET)
	public String formChangePassword(HttpSession session,Model model) {
		String user_id=(String)session.getAttribute("user_id");
		
		MemberCommand member=memberService.selectMember(user_id);
		
		model.addAttribute("command",member);
		
		return "memberChangePassword";
	}
	
	// ��й�ȣ ���������� ���۵� ������ ó��
	@RequestMapping(value="/member/changePassword.do",method=RequestMethod.POST)
	public String submitChangePassword(@ModelAttribute("command")
										@Valid MemberCommand memberCommand,
										BindingResult result) {
		if (log.isDebugEnabled()) {
			log.debug("<<memberCommand>> : "+memberCommand);
		}
		if (result.hasFieldErrors("user_pw")||
			result.hasFieldErrors("old_pw")||
			result.hasFieldErrors("user_pw")) {
			return "memberChangePassword";
		}
		//���� ��й�ȣ (old_passwd) ��ġ ���� üũ
		MemberCommand member=memberService.selectMember(memberCommand.getUser_id());
		//����ڰ� �Է��� ��й�ȣ�� DB�� ��й�ȣ ��ġ ����
		if (!member.getUser_pw().equals(cipherAES.encrypt(memberCommand.getOld_pw()))) {
			result.rejectValue("old_pw", "invalidPassword");
			return "memberChangePassword";
		}
		memberCommand.setUser_pw(cipherAES.encrypt(memberCommand.getUser_pw()));
		//��й�ȣ ����
		memberService.updatePassword(memberCommand);
		
		return "redirect:/member/detail.do";
	}
	
	
	// ============== ȸ������(ȸ��Ż��) ============== //
	// ȸ�� ���� ��
	@RequestMapping(value="/member/delete.do",method=RequestMethod.GET)
	public String formDelete(HttpSession session,Model model) {
		String user_id=(String)session.getAttribute("user_id");
		MemberCommand member=new MemberCommand();
		member.setUser_id(user_id);
		
		model.addAttribute("command",member);
		
		return "memberDelete";
	}
	// ȸ�� ���� ó��
	@RequestMapping(value="member/delete.do",method=RequestMethod.POST)
	public String submitDelete(@ModelAttribute("command")
								@Valid MemberCommand memberCommand,
								BindingResult result,
								HttpSession session) {
		
		if (log.isDebugEnabled()) {
			log.debug("<<memberCommand>> : "+memberCommand);
		}
		
		//id,passwd �ʵ��� ���� üũ
		if (result.hasFieldErrors("user_id")||result.hasFieldErrors("user_pw")) {
			return "memberDelete";
		}
		
		//��й�ȣ ��ġ ���� üũ
		try {
			MemberCommand member=memberService.selectMember(memberCommand.getUser_id());
			boolean check=false;
			
			if (member!=null) {
				//��й�ȣ ��ġ���� üũ
				check=member.isCheckedPasswd(memberCommand.getUser_pw());
			}
			if (check) {
				//�������� ���� ����
				memberService.delete(memberCommand.getUser_id());
				
				//�α� �ƿ�
				session.invalidate();
				return "redirect:/main/main.do";
				
			}else {
				throw new LoginException();
			}
			
		} catch (LoginException e) {
			result.rejectValue("pw", "invalidPassword");
			//ȸ�� Ż�� �� ȣ��"
			return "memberDelete";
		}
	}
	
	//=========ȸ�� ���� ���� =========//
	@RequestMapping("/member/memberPayHistory.do")
	public ModelAndView processMember(
			@RequestParam(value="pageNum", defaultValue="1")int currentPage,
			@RequestParam(value="keyfield", defaultValue="user_id")String keyfield,
			@RequestParam(value="keyword", defaultValue="")String keyword,
			HttpSession session
			) {
		String user_id=(String)session.getAttribute("user_id");
		keyword = user_id;
		
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("keyfield", keyfield);
		map.put("keyword", keyword);
		
		// �� ���� ���� �Ǵ� �˻��� ������ ����
		//� ����??? �뿩? ���� ����? �װ��� ����? ȣ�� ����?
		int count = memberService.selectPayHistRowCount(user_id);
		 
		if(log.isDebugEnabled()) {
			log.debug("<<count>> : " + count);
		}
		
		PagingUtil page = new PagingUtil(keyfield, keyword,currentPage, count,rowCount, pageCount, "memberPayHistory.do");
		map.put("start", page.getStartCount());
		map.put("end", page.getEndCount());
		
		
		List<MemberCommand> list = null;
		if (count > 0) {
			list = memberService.selectPayHist(map);
		}
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("memberPayHistory");
		mav.addObject("count", count);
		mav.addObject("memberPayHistory", list);
		mav.addObject("pagingHtml", page.getPagingHtml());
		
		return mav;
	}
}
