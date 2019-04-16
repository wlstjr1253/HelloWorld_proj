package kr.spring.order.controller;


import java.util.ArrayList;
import java.util.Arrays;
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

import kr.spring.cart.domain.ItemCartCommand;
import kr.spring.cart.service.ItemCartService;
import kr.spring.item.domain.ItemCommand;
import kr.spring.item.service.ItemService;
import kr.spring.member.domain.MemberCommand;
import kr.spring.order.domain.ItemOrderCommand;
import kr.spring.order.domain.ItemOrderDetailCommand;
import kr.spring.order.service.ItemOrderService;
import kr.spring.util.PagingUtil;

@Controller
public class ItemOrderController {
	private Logger log = Logger.getLogger(this.getClass()); 
	private int rowCount = 10;
	private int pageCount = 10;

	@Resource
	private ItemOrderService itemOrderService;
	@Resource
	private ItemCartService itemCartService;
	@Resource
	private ItemService itemService;

	// �ڹٺ�(Ŀ�ǵ� ��ü) �ʱ�ȭ
	@ModelAttribute("command")
	public ItemOrderCommand initCommand() {
		return new ItemOrderCommand();
	}



	//�̹��� ���
	@RequestMapping("/itemcart/imageView.do")
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
	// ================ ��ٱ��� ��ü ��ǰ ================ //
	@RequestMapping(value="/itemcart/orderForm.do", method=RequestMethod.GET)
	public ModelAndView insertOrder(HttpSession session) {
		String user_id = (String)session.getAttribute("user_id");

		int getTotalById = itemCartService.getTotalById(user_id);//��ٱ��� ��ü�ݾ� ȣ��

		List<ItemCartCommand> list = itemCartService.selectCartList(user_id);

		if (log.isDebugEnabled()) {
			log.debug("<<list>> : " + list);
		}

		//Model�� �� �̸��� ������Ʈ�� ����ְ� el�� �����پ�
		ModelAndView mav = new ModelAndView();
		mav.setViewName("orderForm");
		mav.addObject("list",list);
		//mav�� ������ el�� �̾Ƽ� ����Ѵ� view����


		mav.addObject("getTotalById",getTotalById);	//�ֹ���ǰ ��ü�ݾ�

		return mav;

	}

	// ���۵� ������ ó��
	@RequestMapping(value="/itemcart/orderForm.do", method=RequestMethod.POST)
	public String submit(@ModelAttribute("command") @Valid ItemOrderCommand itemOrderCommand,
			BindingResult result,HttpSession session) {


		// ��ȿ�� üũ
		/*if (result.hasErrors()) {
			return "orderForm";
		}*/

		//�ֹ���ȣ ����
		int order_num = itemOrderService.getOrderNum();
		itemOrderCommand.setIbh_idx(order_num);

		if (log.isDebugEnabled()) {
			log.debug("<<itemOrderCommand>> : " + itemOrderCommand);
		}

		String user_id = (String)session.getAttribute("user_id");
		//������ ó���� ���ؼ� ��ٱ��� ���̺� ����� ���� ȣ��
		List<ItemCartCommand> itemOrderlist = itemCartService.selectCartList(user_id);

		// �۾���
		itemOrderService.insertOrder(itemOrderCommand,itemOrderlist,user_id,null);

		// RedirectAttributes ��ü�� �����̷�Ʈ ������ �� ���� ���Ǵ�
		// �����͸� ����.
		// �������� �����͸� ���������� URI�󿡴� ������ �ʴ� ������ ��������
		// ���·� ����


		return "redirect:/itemcart/orderCheck.do";
	}


	// ================ ��ٱ��� ���� ��ǰ ================ //
	@RequestMapping(value="/itemcart/orderFormPart.do")
	public ModelAndView insertOrderPart(@RequestParam("checked_num") String checked_num) {

		if (log.isDebugEnabled()) {
			log.debug("<<checked_num>> : " + checked_num);
		}

		List<String> ic_nums =Arrays.asList(checked_num.split(","));


		int getTotalByIdChosen = itemCartService.getTotalByIdChosen(ic_nums);//��ٱ��� ��ü�ݾ� ȣ��

		List<ItemCartCommand> list = itemCartService.selectCartListChosen(ic_nums);

		if (log.isDebugEnabled()) {
			log.debug("<<list>> : " + list);
			log.debug("<<getTotalByIdChosen>> : " + getTotalByIdChosen);
		}

		//Model�� �� �̸��� ������Ʈ�� ����ְ� el�� �����پ�
		ModelAndView mav = new ModelAndView();
		mav.setViewName("orderFormPart");
		mav.addObject("list",list);
		mav.addObject("checked_num", checked_num);
		//mav�� ������ el�� �̾Ƽ� ����Ѵ� view����


		mav.addObject("getTotalById",getTotalByIdChosen);	//�ֹ���ǰ ��ü�ݾ�

		return mav;

	}

	// ���۵� ������ ó��
	@RequestMapping(value="/itemcart/orderSubmitPart.do")
	public String submitPart(@RequestParam("checked_num") String checked_num, 
			@ModelAttribute("command") ItemOrderCommand itemOrderCommand,
			BindingResult result,HttpSession session) {


		// ��ȿ�� üũ
		/*if (result.hasErrors()) {
				return "orderForm";
			}*/

		//�ֹ���ȣ ����
		int order_num = itemOrderService.getOrderNum();
		itemOrderCommand.setIbh_idx(order_num);

		if (log.isDebugEnabled()) {
			log.debug("<<itemOrderCommand>> : " + itemOrderCommand);
		}

		List<String> ic_nums =Arrays.asList(checked_num.split(","));			
		//������ ó���� ���ؼ� ��ٱ��� ���̺� ����� ���� ȣ��
		List<ItemCartCommand> itemOrderlist = itemCartService.selectCartListChosen(ic_nums);

		String user_id = (String)session.getAttribute("user_id");
		// �۾���
		itemOrderService.insertOrder(itemOrderCommand,itemOrderlist,user_id,ic_nums);

		// RedirectAttributes ��ü�� �����̷�Ʈ ������ �� ���� ���Ǵ�
		// �����͸� ����.
		// �������� �����͸� ���������� URI�󿡴� ������ �ʴ� ������ ��������
		// ���·� ����


		return "redirect:/itemcart/orderCheck.do";
	}



	// ================ �ֹ�Ȯ�� ================ //
	@RequestMapping("/itemcart/orderCheck.do")
	public String process(HttpSession session,Model model, 
			@Valid ItemOrderCommand itemOrderCommand) {
		String user_id=(String)session.getAttribute("user_id");


		itemOrderService.getOrderDetail(itemOrderCommand.getIbh_idx(),user_id);

		if (log.isDebugEnabled()) {
			log.debug("<<itemOrderCommand>> : "+itemOrderCommand);
		}

		//�̾Ƴ� �����ʹ� �𵨿� �־��ش�. �信�� �ڹٺ� �����ؼ� ��� �� �� �ֵ���.
		model.addAttribute("itemOrderCommand",itemOrderCommand);

		return "orderCheck";
	}




	/*//=========�ֹ� ���� =========//
	@RequestMapping("/itemcart/mypage.do")
	public ModelAndView processMypage(
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
		int count = itemOrderService.selectItemBuyHistRowCount(user_id);

		if(log.isDebugEnabled()) {
			log.debug("<<count>> : " + count);
		}

		PagingUtil page = new PagingUtil(keyfield, keyword,currentPage, count,rowCount, pageCount, "memberPayHistory.do");
		map.put("start", page.getStartCount());
		map.put("end", page.getEndCount());


		List<ItemOrderCommand> list = null;
		if (count > 0) {
			list = itemOrderService.selectItemBuyHist(map);
		}

		ModelAndView mav = new ModelAndView();
		mav.setViewName("mypage");
		mav.addObject("count", count);
		mav.addObject("mypage", list);
		mav.addObject("pagingHtml", page.getPagingHtml());

		return mav;
	}*/
}


