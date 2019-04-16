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

	// 자바빈(커맨드 객체) 초기화
	@ModelAttribute("command")
	public ItemOrderCommand initCommand() {
		return new ItemOrderCommand();
	}



	//이미지 출력
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
	// ================ 장바구니 전체 상품 ================ //
	@RequestMapping(value="/itemcart/orderForm.do", method=RequestMethod.GET)
	public ModelAndView insertOrder(HttpSession session) {
		String user_id = (String)session.getAttribute("user_id");

		int getTotalById = itemCartService.getTotalById(user_id);//장바구니 전체금액 호출

		List<ItemCartCommand> list = itemCartService.selectCartList(user_id);

		if (log.isDebugEnabled()) {
			log.debug("<<list>> : " + list);
		}

		//Model이 들어간 이름은 리퀘스트에 담겨있고 el이 가져다씀
		ModelAndView mav = new ModelAndView();
		mav.setViewName("orderForm");
		mav.addObject("list",list);
		//mav에 담은걸 el이 뽑아서 사용한다 view에서


		mav.addObject("getTotalById",getTotalById);	//주문상품 전체금액

		return mav;

	}

	// 전송된 데이터 처리
	@RequestMapping(value="/itemcart/orderForm.do", method=RequestMethod.POST)
	public String submit(@ModelAttribute("command") @Valid ItemOrderCommand itemOrderCommand,
			BindingResult result,HttpSession session) {


		// 유효성 체크
		/*if (result.hasErrors()) {
			return "orderForm";
		}*/

		//주문번호 생성
		int order_num = itemOrderService.getOrderNum();
		itemOrderCommand.setIbh_idx(order_num);

		if (log.isDebugEnabled()) {
			log.debug("<<itemOrderCommand>> : " + itemOrderCommand);
		}

		String user_id = (String)session.getAttribute("user_id");
		//상세정보 처리를 위해서 장바구니 테이블에 저장된 정보 호출
		List<ItemCartCommand> itemOrderlist = itemCartService.selectCartList(user_id);

		// 글쓰기
		itemOrderService.insertOrder(itemOrderCommand,itemOrderlist,user_id,null);

		// RedirectAttributes 객체는 리다이렉트 시점에 한 번만 사용되는
		// 데이터를 전송.
		// 브라우저에 데이터를 전송하지만 URI상에는 보이지 않는 숨겨진 데이터의
		// 형태로 전달


		return "redirect:/itemcart/orderCheck.do";
	}


	// ================ 장바구니 개별 상품 ================ //
	@RequestMapping(value="/itemcart/orderFormPart.do")
	public ModelAndView insertOrderPart(@RequestParam("checked_num") String checked_num) {

		if (log.isDebugEnabled()) {
			log.debug("<<checked_num>> : " + checked_num);
		}

		List<String> ic_nums =Arrays.asList(checked_num.split(","));


		int getTotalByIdChosen = itemCartService.getTotalByIdChosen(ic_nums);//장바구니 전체금액 호출

		List<ItemCartCommand> list = itemCartService.selectCartListChosen(ic_nums);

		if (log.isDebugEnabled()) {
			log.debug("<<list>> : " + list);
			log.debug("<<getTotalByIdChosen>> : " + getTotalByIdChosen);
		}

		//Model이 들어간 이름은 리퀘스트에 담겨있고 el이 가져다씀
		ModelAndView mav = new ModelAndView();
		mav.setViewName("orderFormPart");
		mav.addObject("list",list);
		mav.addObject("checked_num", checked_num);
		//mav에 담은걸 el이 뽑아서 사용한다 view에서


		mav.addObject("getTotalById",getTotalByIdChosen);	//주문상품 전체금액

		return mav;

	}

	// 전송된 데이터 처리
	@RequestMapping(value="/itemcart/orderSubmitPart.do")
	public String submitPart(@RequestParam("checked_num") String checked_num, 
			@ModelAttribute("command") ItemOrderCommand itemOrderCommand,
			BindingResult result,HttpSession session) {


		// 유효성 체크
		/*if (result.hasErrors()) {
				return "orderForm";
			}*/

		//주문번호 생성
		int order_num = itemOrderService.getOrderNum();
		itemOrderCommand.setIbh_idx(order_num);

		if (log.isDebugEnabled()) {
			log.debug("<<itemOrderCommand>> : " + itemOrderCommand);
		}

		List<String> ic_nums =Arrays.asList(checked_num.split(","));			
		//상세정보 처리를 위해서 장바구니 테이블에 저장된 정보 호출
		List<ItemCartCommand> itemOrderlist = itemCartService.selectCartListChosen(ic_nums);

		String user_id = (String)session.getAttribute("user_id");
		// 글쓰기
		itemOrderService.insertOrder(itemOrderCommand,itemOrderlist,user_id,ic_nums);

		// RedirectAttributes 객체는 리다이렉트 시점에 한 번만 사용되는
		// 데이터를 전송.
		// 브라우저에 데이터를 전송하지만 URI상에는 보이지 않는 숨겨진 데이터의
		// 형태로 전달


		return "redirect:/itemcart/orderCheck.do";
	}



	// ================ 주문확인 ================ //
	@RequestMapping("/itemcart/orderCheck.do")
	public String process(HttpSession session,Model model, 
			@Valid ItemOrderCommand itemOrderCommand) {
		String user_id=(String)session.getAttribute("user_id");


		itemOrderService.getOrderDetail(itemOrderCommand.getIbh_idx(),user_id);

		if (log.isDebugEnabled()) {
			log.debug("<<itemOrderCommand>> : "+itemOrderCommand);
		}

		//뽑아낸 데이터는 모델에 넣어준다. 뷰에서 자바빈에 접근해서 사용 할 수 있도록.
		model.addAttribute("itemOrderCommand",itemOrderCommand);

		return "orderCheck";
	}




	/*//=========주문 내역 =========//
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

		// 총 결제 갯수 또는 검색된 결제의 갯수
		//어떤 결제??? 대여? 투어 결제? 항공권 결제? 호텔 결제?
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


