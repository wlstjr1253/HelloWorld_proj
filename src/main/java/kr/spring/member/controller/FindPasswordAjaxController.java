package kr.spring.member.controller;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.spring.member.domain.MemberCommand;
import kr.spring.member.email.Email;
import kr.spring.member.email.EmailSender;
import kr.spring.member.service.MemberService;

@Controller
public class FindPasswordAjaxController {
	private Logger log = Logger.getLogger(this.getClass());
	
	@Autowired
	private EmailSender emailSender;
	@Autowired
	private Email email;
	
	//�ڹٺ� �ʱ�ȭ
	@ModelAttribute("command")
	public MemberCommand initCommand() {
		return new MemberCommand();
	}
	
	@Resource
	private MemberService memberService;
	
	//============���̵�/email�� ��й�ȣ ã�� =============//
	//�� ȣ��
	@RequestMapping(value="/member/findPasswordForm.do")
	public String formFindPassword() {
		return "memberFindPassword";
	}
	//���̵�� �̸����� DB�� ��ġ�ϸ� ���� �߼�
	
	//������ ó��
	@RequestMapping("/member/findPassword.do")
	public String process(@ModelAttribute("command") 
						@Valid MemberCommand memberCommand, 
						BindingResult result) throws Exception{
		if (log.isDebugEnabled()) {
			log.debug("<<user_email>> : " + memberCommand.getUser_email());
			log.debug("<<user_id>> : " + memberCommand.getUser_id());
		}
		//��ȿ�� üũ
		if(result.hasFieldErrors("user_id") || result.hasFieldErrors("user_email")){
			return formFindPassword();
		}
		MemberCommand member = memberService.selectMember(memberCommand.getUser_id());
		
		//�Է��� ���̵�� ������ �� null�� �ƴϰ� �̸��ϰ� �Է��� �̸����� ���� �� 
		if (member!=null && member.getUser_email().equals(memberCommand.getUser_email())) {
			//�̸��� ���´ٴ� �˸�â ����
			if (log.isDebugEnabled()) {
				log.debug("<<----mailing ����----->>");
				
			}
			
			//�⺻��й�ȣ�� �ӽú�й�ȣ�� ����
			String passwd = randomPassword(10);
			//member.setPasswd(cipherAES.encrypt(passwd));
			member.setUser_pw(passwd);
			
			//����� �ӽú�й�ȣ�� DB�� ����
			memberService.updateRandomPassword(member);

			email.setContent("�ӽ� ��й�ȣ�� " + passwd +" �Դϴ�.");
			email.setReceiver(member.getUser_email());
			email.setSubject(member.getUser_id()+" �� ��й�ȣ ã�� �����Դϴ�.");
			System.out.println(passwd + member.getUser_email() + member.getUser_id());
			emailSender.sendEmail(email);

			return "emailSendSuccess";
			
		} else {
			result.reject("invalidIdOrEmail");
			return formFindPassword();
		}
		
	}
	
	//��й�ȣ ������ ���� ���� �߻� �޼ҵ�
	public String randomPassword(int length){
		int index = 0;
		char[] charSet = new char[]{
				'0','1','2','3','4','5','6','7','8','9'
				,'A','B','C','D','E','F','G','H','I','J','K','L','M'
				,'N','O','P','Q','R','S','T','U','V','W','X','Y','Z'
				,'a','b','c','d','e','f','g','h','i','h','k','l','m'
				,'n','o','p','q','r','s','t','u','v','w','x','y','z'
		};

		StringBuffer sb = new StringBuffer();

		for(int i=0;i<length;i++){
			index = (int)(charSet.length * Math.random());
			sb.append(charSet[index]);
		}

		return sb.toString();
	}
	
}
