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

import kr.spring.tour_info.service.Tour_infoService;
import kr.spring.tour_info.domain.Tour_infoCommand;
import kr.spring.util.PagingUtil;

@Controller
public class Tour_infoController {
	private Logger log = Logger.getLogger(this.getClass());
	private int rowCount = 10;
	private int pageCount = 10;
	
	@Resource
	private Tour_infoService tour_infoService;

	//================���� �Խ��� �� ��� ==================//
	@ModelAttribute("command")
	public Tour_infoCommand initCommand() {
		return new Tour_infoCommand();
	}
	
	@RequestMapping(value="/tour_info/write.do",method=RequestMethod.GET)
	public String form(HttpSession session, Model model) {
		String user_id = (String)session.getAttribute("user_id");
		
		Tour_infoCommand command = new Tour_infoCommand();
		
		command.setUser_id(user_id);
		
		model.addAttribute("command", command);
		
		return "tour_infoWrite";
	}
    // ��� ������ ����
	@RequestMapping(value="/tour_info/write.do",method=RequestMethod.POST)
	public String submit(@ModelAttribute("command")@Valid Tour_infoCommand tour_infoCommand, BindingResult result,
			                                           HttpServletRequest request,
			                                            RedirectAttributes redirect) {
		//�α� ó��
		if(log.isDebugEnabled()) {
			log.debug("<<memberCommand>>: " + tour_infoCommand);
		}
		
		//������ ��ȿ�� üũ
		if(result.hasErrors()) {//���� �߻��� ������
			return "tour_infoWrite";
		}
		//����ϱ�
		tour_infoService.insert(tour_infoCommand);
		
		redirect.addFlashAttribute("result","success");
		
		return "redirect:/main/main.do";
	}
	
	//======================���� �� ���========================//
	@RequestMapping("/tour_info/list.do")
	public ModelAndView process(
			            @RequestParam(value="pageNum", defaultValue="1")
		                int currentPage,
		                @RequestParam(value="keyword", defaultValue="")
			            String keyword,
			            @RequestParam(value="keyword2", defaultValue="")
			            String keyword2,
			            @RequestParam(value="keyword3", defaultValue="")
			            String keyword3
			            ) {
		           Map<String,Object> map = new HashMap<String,Object>();
		           
		           map.put("keyword", keyword);
		           map.put("keyword2", keyword2);
		           map.put("keyword3", keyword3);
		           
		           //���� ���� �Ǵ� �˻��� ���� ����
		           int count = tour_infoService.selectRowCount(map);
		           if(log.isDebugEnabled()) {
		   			log.debug("<<count>> : " + count);
		   		}
		           //����¡ ó��
		           PagingUtil page =
		   				new PagingUtil(keyword, keyword2, 
		   						currentPage, count, 
		   						rowCount, pageCount, "list.do","keyword3="+keyword3);
		   		map.put("start", page.getStartCount());
		   		map.put("end", page.getEndCount());
		   		
		   		List<Tour_infoCommand> list = null;
		   		if (count > 0) {
		   			list = tour_infoService.selectList(map);
		   		} 
		   		
		   	 if(log.isDebugEnabled()) {
		   			log.debug("<<list>> : " + list);
		   		}
		   		
	          ModelAndView mav = new ModelAndView();
	          mav.setViewName("tour_infoList");
	          mav.addObject("count",count);
	          mav.addObject("list", list);
	  	    	mav.addObject("pagingHtml", page.getPagingHtml());
	  		
	  		return mav; 
	}
 	//==============�Խ��� �� ��==================//
	@RequestMapping("/tour_info/detail.do")
	public ModelAndView process(@RequestParam("ti_id") int ti_id) {
		//�α� 
		if(log.isDebugEnabled()) {
			log.debug("<<ti_id>> : " + ti_id);
		}
	//�� �� �б�
	Tour_infoCommand tour_info = tour_infoService.selectTour_info(ti_id);
	                                    //�� ����        , �Ӽ���    , �Ӽ���
         	return new ModelAndView("tour_infoDetail","tour_info",tour_info);
}
	
	//�̹��� ���
		@RequestMapping("/tour_info/imageView.do")
		public ModelAndView viewImage(@RequestParam("ti_id") int ti_id) {
			
			if(log.isDebugEnabled()) {
				log.debug("<<ti_id>> : " + ti_id);
			}
			
			Tour_infoCommand tour_info = tour_infoService.selectTour_info(ti_id);
			
			if(log.isDebugEnabled()) {
				log.debug("<<tour_info>> : " + tour_info);
			}
			
			ModelAndView mav = new ModelAndView();
			mav.setViewName("imageView");
			mav.addObject("imageFile", tour_info.getUploadfile());
			mav.addObject("filename", tour_info.getTi_img());
			
			return mav;
		}
	//==========�۾��� �̹��� ���ε�===========//
	/*	@RequestMapping("/tour_info/imageUploader.do")
		public void uploadImage(MultipartFile file,HttpServletRequest request,
				                 HttpServletResponse response,HttpSession session)throws Exception{
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			
			//���ε��� ���� ���
			String realFolder = session.getServletContext().getRealPath("/resources/image_upload");
			
			//���ε��� ���� �̸�
			String org_filename = file.getOriginalFilename();
			String str_filename = System.currentTimeMillis()+org_filename; //�ð����� ���� �̸��� �޶����� ��ġ������
			
			if(log.isDebugEnabled()) {
				log.debug("<<���� ���ϸ�>> : " + org_filename);
				log.debug("<<������ ���ϸ�>> : " + str_filename);
			}
			
			String filepath = realFolder + "\\" + str_filename;
			
			File f = new File(filepath);
			if(log.isDebugEnabled()) {
				log.debug("<<���� ���>> : " + filepath);
			}
			
			//������ ��ο� ������ ����
			file.transferTo(f);
			
			out.println(request.getContextPath()+"/resources/image_upload/"+str_filename);
			out.close();
		}*/
		//==========================�� ����=============================//
		@RequestMapping("/tour_info/delete.do")
		public String submit(@RequestParam("ti_id") int ti_id) {
			if(log.isDebugEnabled()) {
				log.debug("<<ti_id>>:"+ ti_id);
			}
			//�� ����
			tour_infoService.delete(ti_id);
			
			return "redirect:/tour_info/list.do";
		}
		
		
		//=========================�� ����===========================//
		@RequestMapping(value="/tour_info/update.do",method=RequestMethod.GET)
		public String form(@RequestParam("ti_id") int ti_id, Model model) {
			Tour_infoCommand tour_infoCommand = tour_infoService.selectTour_info(ti_id);
			model.addAttribute("command",tour_infoCommand);
			return "tour_infoModify"; //���Ǵϼ� ����
		}
		//���� �� ������ ó��
		@RequestMapping(value="/tour_info/update.do", method=RequestMethod.POST)
		public String submit(@ModelAttribute("command")@Valid Tour_infoCommand tour_infoCommand,
							BindingResult result,HttpSession session, HttpServletRequest request) {
			
			if (log.isDebugEnabled()) {
				log.debug("<<Tour_infoCommand>> : " + tour_infoCommand);
			}
		    //������ ��ȿ��
			if(result.hasErrors()) {
				return "tour_infoModify";//���Ǵϼ� ����
			}
		     //����
			tour_infoService.update(tour_infoCommand);
			return "redirect:/tour_info/list.do";
   }
}