package kr.spring.flight.controller;

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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.spring.board.domain.BoardCommand;
import kr.spring.flight.domain.FlightCommand;
import kr.spring.flight.domain.FlightPayCommand;
import kr.spring.flight.domain.FlightRsrvCommand;
import kr.spring.flight.service.FlightService;

@Controller
public class FlightRsrvController {
	private Logger log = Logger.getLogger(this.getClass());
	
	@Resource
	private FlightService flightService;
	
	//======�Խ��� �� ���=======//
	//��� ��
	@RequestMapping(value="/flight/rsrvWrite.do",
	        			method=RequestMethod.GET)
	public String form(@RequestParam("fsi_idx") int fsi_idx, HttpSession session, Model model) {
		
		FlightCommand flightCommand = flightService.selectFlight(fsi_idx);
		
		String user_id = (String)session.getAttribute("user_id");
		
		FlightRsrvCommand command = new FlightRsrvCommand();
		command.setUser_id(user_id);
		
		model.addAttribute("command", command);
		model.addAttribute("flightCommand", flightCommand);
		
		return "flightRsrv";
	}
	//���۵� ������ ó��
	@RequestMapping(value="/flight/rsrvWrite.do",
					method=RequestMethod.POST)
	public String submit(@ModelAttribute("command")
						 FlightRsrvCommand flightRsrvCommand,
						 BindingResult result,
						 HttpServletRequest request,
						 RedirectAttributes redirect) {

		if(log.isDebugEnabled()) {
			log.debug("<<FlightRsrvCommand>> : " + flightRsrvCommand);
		}

		//������ ��ȿ�� üũ
		/*if(result.hasErrors()) {
			return "flightRsrv";
		}*/

		int fr_id = flightService.getFr_id();
		if(log.isDebugEnabled()) {
			log.debug("<<fr_id>> : " + fr_id);
		}
		
		flightRsrvCommand.setFr_id(fr_id);
		
		//�۾���
		flightService.insertFlightRsrv(flightRsrvCommand);

		//RedirectAttributes ��ü�� �����̷�Ʈ ������ �� ����
		//���Ǵ� �����͸� ����.
		//�������� �����͸� ���������� URI�󿡴� ������ �ʴ� 
		//������ �������� ���·� ����
		redirect.addFlashAttribute("fr_id", fr_id);
		
		return "redirect:/flight/flightPay.do";
	}
	
	//=====���� �ϱ�=====//
	//��� ��
	@RequestMapping(value="/flight/flightPay.do",
						method=RequestMethod.GET)
	public String form(HttpSession session, Model model) {

		String user_id = (String)session.getAttribute("user_id");

		FlightPayCommand command = new FlightPayCommand();
		command.setUser_id(user_id);

		model.addAttribute("command", command);
		
		return "flightPay";
	}
	//���۵� ������ ó��
	@RequestMapping(value="/flight/flightPay.do",
			method=RequestMethod.POST)
	public String submit(@ModelAttribute("command")
						 FlightPayCommand flightPayCommand,
						 BindingResult result,
						 HttpServletRequest request,
						 RedirectAttributes redirect) {

		if(log.isDebugEnabled()) {
			log.debug("<<FlightPayCommand>> : " + flightPayCommand);
		}

		//������ ��ȿ�� üũ
		/*if(result.hasErrors()) {
				return "flightRsrv";
			}*/

		//�۾���
		flightService.insertFlightPay(flightPayCommand);

		//RedirectAttributes ��ü�� �����̷�Ʈ ������ �� ����
		//���Ǵ� �����͸� ����.
		//�������� �����͸� ���������� URI�󿡴� ������ �ʴ� 
		//������ �������� ���·� ����
		redirect.addFlashAttribute("result", "success");

		return "redirect:/main/main.do";
	}
}
