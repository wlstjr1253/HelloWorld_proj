package kr.spring.interceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import kr.spring.member.domain.MemberCommand;
import kr.spring.member.service.MemberService;

public class LoginCheckInterceptor extends HandlerInterceptorAdapter {
	private Logger log = Logger.getLogger(this.getClass());
	
	@Resource
	private MemberService memberService;
	
	@Override
	public boolean preHandle(HttpServletRequest request, 
			HttpServletResponse response,
			Object handler) throws Exception {
		
		if (log.isDebugEnabled()) {
			log.debug("<<LoginCheckInterceptor����>>");
		}
		
		// �α��� ���� �˻�
		HttpSession session = request.getSession();
		
		if (session.getAttribute("user_id") == null) {
			// �α����� ���� ���� ���
			response.sendRedirect(request.getContextPath()+"/member/login.do");
			return false; // <-- Ŭ���̾�Ʈ�� ��û�� URL ��ȣ��
		}else {
			//�α��� ����
			MemberCommand member = memberService.selectMember((String)session.getAttribute("user_id"));
			if (log.isDebugEnabled()) {
				log.debug("<<session user_auth>> : "+ (Integer)session.getAttribute("user_auth"));
				log.debug("<<user_auth>> : "+ member.getUser_auth());
			}
			int user_auth=(Integer)session.getAttribute("user_auth");
			
			if(user_auth!=member.getUser_auth()) {
				session.setAttribute("user_auth", member.getUser_auth());
			}
		}
		return true; // <-- Ŭ���̾�Ʈ�� ��û�� URL ȣ��
	}
	
}
