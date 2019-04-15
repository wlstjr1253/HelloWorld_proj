package kr.spring.item.controller;

import java.io.File;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import kr.spring.board.domain.BoardCommand;
import kr.spring.category.domain.ItemCategoryCommand;
import kr.spring.category.service.ItemCategoryService;
import kr.spring.item.domain.ItemCommand;
import kr.spring.item.service.ItemService;
import kr.spring.itemReview.domain.ItemReviewCommand;
import kr.spring.itemReview.service.ItemReviewService;
import kr.spring.util.PagingUtil;

@Controller
public class ItemController {
	private Logger log = Logger.getLogger(this.getClass());
	private int rowCount = 10;
	private int pageCount = 10;

	@Resource
	private ItemService itemService;

	@Resource
	private ItemCategoryService itemCategoryService;
	
	@Resource
	private ItemReviewService itemReviewService;

	
	// �ڹٺ�(Ŀ�ǵ� ��ü) �ʱ�ȭ
	@ModelAttribute("ICommand")
	public ItemCommand initCommand() {
		return new ItemCommand();
	}
	//=======================������============================//
	// ================ (������)�Խ��� �� ��� ================ //
	// ��� ��
	@RequestMapping(value="/item/itemWrite.do", method=RequestMethod.GET)
	public String form(HttpSession session, Model model) {
		String id = (String)session.getAttribute("user_id");

		ItemCommand icommand = new ItemCommand();

		List<ItemCategoryCommand> list = itemCategoryService.selectList();

		model.addAttribute("icommand", icommand);
		model.addAttribute("list", list);

		return "itemWrite";
	}

	// ���۵� ������ ó��
	@RequestMapping(value="/item/itemWrite.do", method=RequestMethod.POST)
	@ResponseBody
	public Map<String,String> submit(@ModelAttribute("ICommand")
	@Valid ItemCommand itemCommand,
	BindingResult result,
	HttpSession session) {
		if (log.isDebugEnabled()) {
			log.debug("<<itemCommand>> : " + itemCommand);
		}

		// �۾���
		itemService.insert(itemCommand);

		Map<String,String> map = new HashMap<String,String>();
		map.put("result", "success");

		return map;
	}
	//������ ��ǰ ���
	@RequestMapping("/item/admin_itemList.do")
	public ModelAndView process(@RequestParam(value="pageNum",defaultValue="1") int currentPage,
			@RequestParam(value="keyfield",defaultValue="") String keyfield,
			@RequestParam(value="keyword",defaultValue="") String keyword,
			@RequestParam(value="order",defaultValue="") String order) {

		Map<String,Object> map = 
				new HashMap<String, Object>();
		map.put("keyfield", keyfield);
		map.put("keyword", keyword);

		//�� ���� ���� �Ǵ� �˻��� ���� ����
		int count = itemService.selectRowCount(map);

		if(log.isDebugEnabled()) {
			log.debug("<<count>> : " + count);
		}

		PagingUtil page = 
				new PagingUtil(keyfield,keyword,currentPage,
						count,rowCount,pageCount,"admin_itemList.do");
		map.put("start", page.getStartCount());
		map.put("end", page.getEndCount());
		map.put("order", order);

		List<ItemCommand> list = null;
		if(count > 0) {
			list = itemService.selectList(map);
		}

		ModelAndView mav = new ModelAndView();
		mav.setViewName("admin_itemList");
		mav.addObject("count", count);
		mav.addObject("list", list);
		mav.addObject("pagingHtml", page.getPagingHtml());

		return mav;
	}

	//�̹��� ���
	@RequestMapping("/item/imageView.do")
	public ModelAndView viewImage(@RequestParam("i_num") int i_num) {

		ItemCommand itemCommand = itemService.selectItem(i_num);

		if(log.isDebugEnabled()) {
			log.debug("itemCommand:"+itemCommand);
		}

		ModelAndView mav = new ModelAndView();
		mav.setViewName("imageView");
		mav.addObject("imageFile", itemCommand.getI_img());
		mav.addObject("filename", "image.jpg");

		return mav;
	}
	//==========�۾��� �̹��� ���ε�===========//
	@RequestMapping("/item/imageUploader.do")
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
	}

	//��ǰ����������
	@RequestMapping("/item/itemMain.do")
	public ModelAndView itemMainProcess(@RequestParam(value="pageNum",defaultValue="1") int currentPage,
			@RequestParam(value="keyfield",defaultValue="") String keyfield,
			@RequestParam(value="keyword",defaultValue="") String keyword,
			@RequestParam(value="order",defaultValue="") String order) {

		Map<String,Object> map = 
				new HashMap<String, Object>();
		map.put("keyfield", keyfield);
		map.put("keyword", keyword);

		//�� ���� ���� �Ǵ� �˻��� ���� ����
		int count = itemService.selectRowCount(map);

		if(log.isDebugEnabled()) {
			log.debug("<<count>> : " + count);
		}

		PagingUtil page = 
				new PagingUtil(keyfield, keyword,currentPage,
						count,rowCount,pageCount,"camera.do");
		map.put("start", page.getStartCount());
		map.put("end", page.getEndCount());
		map.put("order", order);

		List<ItemCommand> list = null;
		if(count > 0) {
			list = itemService.selectList(map);
		}

		ModelAndView mav = new ModelAndView();
		mav.setViewName("itemMain");
		mav.addObject("count", count);
		mav.addObject("list", list);
		mav.addObject("pagingHtml", page.getPagingHtml());

		return mav;
	}
	//ī�װ��� ������
	@RequestMapping("/item/camera.do")
	public ModelAndView cameraProcess(@RequestParam(value="pageNum",defaultValue="1") int currentPage,
			@RequestParam(value="keyfield",defaultValue="") String keyfield,
			@RequestParam(value="keyword",defaultValue="") String keyword,
			@RequestParam(value="order",defaultValue="") String order) {

		
		Map<String,Object> map = 
				new HashMap<String, Object>();
		map.put("keyfield", keyfield);
		map.put("keyword", keyword);

		//�� ���� ���� �Ǵ� �˻��� ���� ����
		int count = itemService.selectRowCount(map);

		if(log.isDebugEnabled()) {
			log.debug("<<count>> : " + count);
		}

		PagingUtil page = 
				new PagingUtil(keyfield, keyword,currentPage,
						count,rowCount,pageCount,"camera.do");
		map.put("start", page.getStartCount());
		map.put("end", page.getEndCount());
		map.put("order", order);

		List<ItemCommand> list = null;
		if(count > 0) {
			list = itemService.selectList(map);
		}

		ModelAndView mav = new ModelAndView();
		mav.setViewName("camera");
		mav.addObject("count", count);
		mav.addObject("list", list);
		mav.addObject("pagingHtml", page.getPagingHtml());

		return mav;
	}
	//ī�װ��� ������
	@RequestMapping("/item/mountain.do")
	public ModelAndView mountainProcess(@RequestParam(value="pageNum",defaultValue="1") int currentPage,
			@RequestParam(value="keyfield",defaultValue="") String keyfield,
			@RequestParam(value="keyword",defaultValue="") String keyword,
			@RequestParam(value="order",defaultValue="") String order) {

		Map<String,Object> map = 
				new HashMap<String, Object>();
		map.put("keyfield", keyfield);
		map.put("keyword", keyword);

		//�� ���� ���� �Ǵ� �˻��� ���� ����
		int count = itemService.selectRowCount(map);

		if(log.isDebugEnabled()) {
			log.debug("<<count>> : " + count);
		}

		PagingUtil page = 
				new PagingUtil(keyfield, keyword,currentPage,
						count,rowCount,pageCount,"mountain.do");
		map.put("start", page.getStartCount());
		map.put("end", page.getEndCount());
		map.put("order", order);

		List<ItemCommand> list1 = null;
		if(count > 0) {
			list1 = itemService.selectList(map);
		}

		ModelAndView mav = new ModelAndView();
		mav.setViewName("mountain");
		mav.addObject("count", count);
		mav.addObject("list1", list1);
		mav.addObject("pagingHtml", page.getPagingHtml());

		return mav;
	}
	//ī�װ��� ������
	@RequestMapping("/item/water.do")
	public ModelAndView waterProcess(@RequestParam(value="pageNum",defaultValue="1") int currentPage,
			@RequestParam(value="keyfield",defaultValue="") String keyfield,
			@RequestParam(value="keyword",defaultValue="") String keyword,
			@RequestParam(value="order",defaultValue="") String order) {

		Map<String,Object> map = 
				new HashMap<String, Object>();
		map.put("keyfield", keyfield);
		map.put("keyword", keyword);

		//�� ���� ���� �Ǵ� �˻��� ���� ����
		int count = itemService.selectRowCount(map);

		if(log.isDebugEnabled()) {
			log.debug("<<count>> : " + count);
		}

		PagingUtil page = 
				new PagingUtil(keyfield, keyword,currentPage,
						count,rowCount,pageCount,"water.do");
		map.put("start", page.getStartCount());
		map.put("end", page.getEndCount());
		map.put("order", order);

		List<ItemCommand> list = null;
		if(count > 0) {
			list = itemService.selectList(map);
		}

		ModelAndView mav = new ModelAndView();
		mav.setViewName("water");
		mav.addObject("count", count);
		mav.addObject("list", list);
		mav.addObject("pagingHtml", page.getPagingHtml());

		return mav;
	}
	//ī�װ��� ������
	@RequestMapping("/item/phone.do")
	public ModelAndView phoneProcess(@RequestParam(value="pageNum",defaultValue="1") int currentPage,
			@RequestParam(value="keyfield",defaultValue="") String keyfield,
			@RequestParam(value="keyword",defaultValue="") String keyword,
			@RequestParam(value="order",defaultValue="") String order) {

		Map<String,Object> map = 
				new HashMap<String, Object>();
		map.put("keyfield", keyfield);
		map.put("keyword", keyword);

		//�� ���� ���� �Ǵ� �˻��� ���� ����
		int count = itemService.selectRowCount(map);

		if(log.isDebugEnabled()) {
			log.debug("<<count>> : " + count);
		}

		PagingUtil page = 
				new PagingUtil(keyfield, keyword,currentPage,
						count,rowCount,pageCount,"phone.do");
		map.put("start", page.getStartCount());
		map.put("end", page.getEndCount());
		map.put("order", order);

		List<ItemCommand> list = null;
		if(count > 0) {
			list = itemService.selectList(map);
		}

		ModelAndView mav = new ModelAndView();
		mav.setViewName("phone");
		mav.addObject("count", count);
		mav.addObject("list", list);
		mav.addObject("pagingHtml", page.getPagingHtml());

		return mav;
	}
	//ī�װ��� ������
	@RequestMapping("/item/play.do")
	public ModelAndView playProcess(@RequestParam(value="pageNum",defaultValue="1") int currentPage,
			@RequestParam(value="keyfield",defaultValue="") String keyfield,
			@RequestParam(value="keyword",defaultValue="") String keyword,
			@RequestParam(value="order",defaultValue="") String order) {

		Map<String,Object> map = 
				new HashMap<String, Object>();
		map.put("keyfield", keyfield);
		map.put("keyword", keyword);

		//�� ���� ���� �Ǵ� �˻��� ���� ����
		int count = itemService.selectRowCount(map);

		if(log.isDebugEnabled()) {
			log.debug("<<count>> : " + count);
		}

		PagingUtil page = 
				new PagingUtil(keyfield, keyword,currentPage,
						count,rowCount,pageCount,"play.do");
		map.put("start", page.getStartCount());
		map.put("end", page.getEndCount());
		map.put("order", order);

		List<ItemCommand> list = null;
		if(count > 0) {
			list = itemService.selectList(map);
		}

		ModelAndView mav = new ModelAndView();
		mav.setViewName("play");
		mav.addObject("count", count);
		mav.addObject("list", list);
		mav.addObject("pagingHtml", page.getPagingHtml());

		return mav;
	}
	//ī�װ��� ������
	@RequestMapping("/item/etc.do")
	public ModelAndView etcProcess(@RequestParam(value="pageNum",defaultValue="1") int currentPage,
			@RequestParam(value="keyfield",defaultValue="") String keyfield,
			@RequestParam(value="keyword",defaultValue="") String keyword,
			@RequestParam(value="order",defaultValue="") String order) {

		Map<String,Object> map = 
				new HashMap<String, Object>();
		map.put("keyfield", keyfield);
		map.put("keyword", keyword);

		//�� ���� ���� �Ǵ� �˻��� ���� ����
		int count = itemService.selectRowCount(map);

		if(log.isDebugEnabled()) {
			log.debug("<<count>> : " + count);
		}

		PagingUtil page = 
				new PagingUtil(keyfield, keyword,currentPage,
						count,rowCount,pageCount,"etc.do");
		map.put("start", page.getStartCount());
		map.put("end", page.getEndCount());
		map.put("order", order);

		List<ItemCommand> list = null;
		if(count > 0) {
			list = itemService.selectList(map);
		}

		ModelAndView mav = new ModelAndView();
		mav.setViewName("etc");
		mav.addObject("count", count);
		mav.addObject("list", list);
		mav.addObject("pagingHtml", page.getPagingHtml());

		return mav;
	}
	//���๰ǰ ����
	@RequestMapping(value="/item/itemModify.do",method=RequestMethod.GET)
	public String form(@RequestParam("i_num") int i_num, Model model) {

		ItemCommand itemCommand = itemService.selectItem(i_num);//�Ѱ��� �����͸� ����
		List<ItemCategoryCommand> list = itemCategoryService.selectList();

		model.addAttribute("ICommand",itemCommand);
		model.addAttribute("list",list);

		return "itemModify";//���Ǵϼ� ����
	}
	//���� ������ ���۵� ������ ó��
	@RequestMapping(value="/item/itemModify.do",method=RequestMethod.POST)
	public String submit(@ModelAttribute("ICommand")@Valid ItemCommand itemCommand,
			BindingResult result,HttpSession session, HttpServletRequest request) {

		if(log.isDebugEnabled()) {
			log.debug("<<itemCommand>> : " + itemCommand);
		}

		//�� ����
		itemService.update(itemCommand);
		//boardService.update(boardCommand);

		return "redirect:/item/admin_itemList.do";
	}
	//==========���๰ǰ �� ��===============//
	@RequestMapping("/item/itemDetail.do")
	public ModelAndView process(@RequestParam("i_num") int i_num) {

		//�α� ���
		if(log.isDebugEnabled()) {
			log.debug("<<i_num>> : " + i_num);
		}

		//�Ѱ��� ���ڵ带 �о��
		ItemCommand item = itemService.selectItem(i_num);
		
		//������ ��������
		List<ItemReviewCommand> list = itemReviewService.selectListReview();
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("itemDetail");
		mav.addObject("item",item);
		mav.addObject("list",list);
		return mav;
	}


	//==========���๰ǰ ����============//
	@RequestMapping("/item/itemDelete.do")
	public String submit(@RequestParam("i_num") int i_num) {

		if(log.isDebugEnabled()) {
			log.debug("<<i_num>> : " + i_num);
		}
		//�� ����
		itemService.delete(i_num);

		return "redirect:/item/admin_itemList.do";
	}
}