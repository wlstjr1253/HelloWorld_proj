package kr.spring.tour_info.controller;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.spring.tour_info.service.Tour_infoService;
import kr.spring.tour_info.domain.Tour_infoReplyCommand;
import kr.spring.util.PagingUtil;

@Controller
public class Tour_infoReplyAjaxController {
	private Logger log = Logger.getLogger(this.getClass());
	private int rowCount = 10;
	private int pageCount = 10;
	
	@Resource
	private Tour_infoService tour_infoService;
	
	//��� ���
	@RequestMapping("/tour_info/writeReply.do")
	@ResponseBody
	public Map<String, String> writeReply(Tour_infoReplyCommand tour_infoReplyCommand,HttpSession session,HttpServletRequest request){
		if(log.isDebugEnabled()) {
			log.debug("<<tour_infoReplyCommand>> : " + tour_infoReplyCommand);
		}
		Map<String,String> map = new HashMap<String, String>();
		
		//�α��� ���� üũ 
		String id = (String)session.getAttribute("user_id");
		if(id==null) {
			//�α��� �ȵ�
			map.put("result", "logout");
		}else {
			//��� ���
			tour_infoService.insertReply(tour_infoReplyCommand);
			map.put("result", "success");
		}
		return map;
	}
		//��� ���
	@RequestMapping("tour_info/listReply.do")
	@ResponseBody
	public Map<String,Object> getList(@RequestParam(value="pageNum",defaultValue="1") int currentPage,
			                          @RequestParam("ti_id") int ti_id){
		if(log.isDebugEnabled()) {
			log.debug("<<currentPage>>:" + currentPage);
			log.debug("<<tr_idx>> : " + ti_id);
		}
		
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("ti_id", ti_id);
		
		//�� ���� ����
		int count = tour_infoService.selectRowCountReply(map);
		
		PagingUtil page = new PagingUtil(currentPage, count, rowCount, pageCount, null);
		
		map.put("start", page.getStartCount());
		map.put("end", page.getEndCount());
		
		List<Tour_infoReplyCommand> list = null;
		if(count > 0) {
			list = tour_infoService.selectListReply(map);
		}else {
			list = Collections.emptyList();
		}
		
		if(log.isDebugEnabled()) {
			log.debug("<<list>>:" + list);
		}
		
		Map<String, Object> mapJson = new HashMap<String, Object>();
		mapJson.put("count", count);
		mapJson.put("rowCount", rowCount);
		mapJson.put("list", list);
		
		return mapJson;
	}
	
	//��� ����
	@RequestMapping("/tour_info/deleteReply.do")
	@ResponseBody
	public Map<String,String> deleteReply(@RequestParam("tr_idx") int tr_idx,
			                              @RequestParam("user_id") String user_id, HttpSession session) {
		
		if(log.isDebugEnabled()) {
			log.debug("<<tr_idx>> : " + tr_idx);
			log.debug("<<user_id>> : " + user_id);
		}
	
        Map<String, String> map = new HashMap<String, String>();	
         
        //�α��� ���� üũ
        String id = (String)session.getAttribute("user_id");
        if(id==null) {
        	//�α��� �Ǿ����� ����
        	map.put("result", "logout");
        }else if(id!=null && id.equals(user_id)) {
        	//�α����� �Ǿ��ְ� �α����� ���̵�� �ۼ��� ���̵� ��ġ
        	tour_infoService.deleteReply(tr_idx);
        	map.put("result", "success");
        }else {
        	//�α����� ���̵�� �ۼ��� ���̵� ����ġ
        	map.put("result", "wrongAccess");
        }
        return map;
    }
	//��� ����
	@RequestMapping("/tour_info/updateReply.do")
	@ResponseBody//json ���ڿ� ���� �Է�
	public Map<String,String> modifyReply(Tour_infoReplyCommand tour_infoReplyCommand,
			                               HttpSession session,
			                                HttpServletRequest request){
		if(log.isDebugEnabled()) {
			log.debug("<<boardReplyCommand>> : " +tour_infoReplyCommand);
		}
		Map<String, String> map = new HashMap<String,String>();
		//�α��� üũ
		String id = (String)session.getAttribute("user_id");
		
		if(id==null) {
			//�α��� �� �Ǿ��ִ� ���
			map.put("result", "logout");
		}else if(id!=null && id.equals(tour_infoReplyCommand.getUser_id())) {
			//�α��� ���̵�� �ۼ��� ���̵� ��ġ
			//��� ����
			tour_infoService.updateReply(tour_infoReplyCommand);
			map.put("result", "success");
		}else {
			//�α��� ���̵�� �ۼ��� ���̵� ����ġ
			map.put("result", "wrongAccess");
		}
		
		return map;
	}
	
}