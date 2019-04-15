package kr.spring.member.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.spring.member.domain.MemberCommand;
import kr.spring.member.service.MemberService;

@Controller
public class FindIdAjaxController {
	private Logger log = Logger.getLogger(this.getClass());
	
	@Resource
	private MemberService memberService;
	
	//===========�̸��Ϸ� ���̵� ã�� ============//
	//�� ȣ��
	@RequestMapping("/member/findIdForm.do")
	public String formFindId() {
		return "memberFindId";
	}
	
	@RequestMapping("/member/findId.do")
	@ResponseBody
	public Map<String, String> process(@RequestParam("user_email") String user_email) {
		if (log.isDebugEnabled()) {
			log.debug("<<user_email>> : " + user_email);
		}
		Map<String, String> map = new HashMap<String, String>();
		
		MemberCommand member = memberService.selectMemberByEmail(user_email);
		//�̸����� null�� �ƴϸ� �ش� �̸��Ϸ� ȸ�� ������ �����´�.
		if (member != null) {
			
			// �̸��� �� ������ �������� ��.
			
			map.put("result", "emailNotNull");
			map.put("user_id", member.getUser_id());
		} else {
			// ���̵� ���ߺ�
			map.put("result", "emailNull");
		}
		return map;
	}
}
