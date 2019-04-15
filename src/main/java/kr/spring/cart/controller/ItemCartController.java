package kr.spring.cart.controller;

import java.util.List;

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

@Controller
@RequestMapping
public class ItemCartController {
	private Logger log = Logger.getLogger(this.getClass());
	
	@Resource
	private ItemCartService itemCartService;
	
	
	// �ڹٺ�(Ŀ�ǵ� ��ü) �ʱ�ȭ
	@ModelAttribute("Command")
	public ItemCartCommand initCommand() {
		return new ItemCartCommand();
	}
	
		
		// ��� ��
		@RequestMapping(value="/itemcart/cartInsert.do", method=RequestMethod.GET)
		public String form(ItemCartCommand itemCartCommand, HttpSession session, Model model) {
			if(log.isDebugEnabled()) {
				log.debug("<<itemCartCommand>> :" + itemCartCommand);
			}
			
			String user_id = (String)session.getAttribute("user_id");
			itemCartCommand.setUser_id(user_id);
			
			
			
			//��ٱ��Ͽ� ���� ��ǰ�� �ִ��� �˻�
			int count = itemCartService.selectCartDetail(itemCartCommand.getI_num(),user_id);
			//count == 0 ? itemCartService.updateCart(itemCartCommand) : itemCartService.insertCart(itemCartCommand);
			
			
			if(count==0) {
				//������ insert
				itemCartService.insertCart(itemCartCommand);
			}else {
				//������ update
				itemCartService.updateCart(itemCartCommand);
			}

			model.addAttribute("Command",itemCartCommand);

			return "cartInsert";
		}

		// ���۵� ������ ó��
		@RequestMapping(value="/itemcart/cartInsert.do", method=RequestMethod.POST)
		public String submit(@ModelAttribute("Command")
		@Valid ItemCartCommand itemCartCommand,
		BindingResult result,
		HttpSession session) {
			if (log.isDebugEnabled()) {
				log.debug("<<itemCartCommand>> : " + itemCartCommand);
			}

			
			// �۾���
			itemCartService.insertCart(itemCartCommand);

			return "redirect:/itemcart/cartList.do";
		}
	
	
	
	/*// ================ ��ٱ��� �߰� ================ //
		// ��� ��
		@ResponseBody
		@RequestMapping(value="/itemcart/cartInsert.do", method=RequestMethod.POST)
		public String insert(@ModelAttribute ItemCartCommand itemCartCommand, HttpSession session) throws Exception{
			String user_id = (String)session.getAttribute("user_id");
			itemCartCommand.setUser_id(user_id);
			//��ٱ��Ͽ� ���� ��ǰ�� �ִ��� �˻�
			int count = itemCartService.selectCartDetail(itemCartCommand.getI_num(),user_id);
			//count == 0 ? itemCartService.updateCart(itemCartCommand) : itemCartService.insertCart(itemCartCommand);
			
			if(count==0) {
				//������ insert
				itemCartService.insertCart(itemCartCommand);
			}else {
				//������ update
				itemCartService.updateCart(itemCartCommand);
			}

				return "redirect:/itemcart/cartList.do";
			}*/
		
	
	
	
	
		//======��ٱ��� �� ���=======//
			@RequestMapping("/itemcart/cartList.do")
			public ModelAndView list(HttpSession session, ModelAndView mav) {
				String user_id = (String)session.getAttribute("user_id"); //session�� ����� user_id

				List<ItemCartCommand> list = itemCartService.selectCartList(user_id); //��ٱ��� ����
				int getTotalById = itemCartService.getTotalById(user_id);//��ٱ��� ��ü�ݾ� ȣ��
				
				if (log.isDebugEnabled()) {
					log.debug("<<list>> : " + list);
				}
				
				mav.addObject("list", list); //��ٱ��� ������ map�� ����
				mav.addObject("count", list.size()); //��ٱ��� ��ǰ ����
				mav.addObject("getTotalById", getTotalById); //��ٱ��� ��ü�ݾ�
				mav.addObject("allTotal",getTotalById);	//�ֹ���ǰ ��ü�ݾ�
				mav.setViewName("cartList");	//view(jsp)�� �̸� ����
				
				return mav;
			}
			
			
			//=======��ٱ��� ����========//
			@RequestMapping("/itemcart/cartDelete.do")
			public String delete(@RequestParam int ic_num) {
				itemCartService.deleteCart(ic_num);
				return "redirect:/itemcart/cartList.do";
			}
			
			//=======��ٱ��� ����========//
			@RequestMapping("/itemcart/cartUpdate.do")
			public String update(@RequestParam int[] ic_quan, @RequestParam int[] i_num, HttpSession session) {
				//session�� id
				String user_id = (String)session.getAttribute("user_id");
				//���ڵ��� ���� ��ŭ �ݺ��� ����
				for(int i=0; i<i_num.length;i++) {
					ItemCartCommand itemCartCommand = new ItemCartCommand();
					itemCartCommand.setUser_id(user_id);
					itemCartCommand.setIc_quan(ic_quan[i]);
					itemCartCommand.setI_num(i_num[i]);
					itemCartService.updateCart(itemCartCommand);
				}
				return "redirect:/itemcart/cartList.do";
			}
			
			
			
			/*//========��ٱ��� �� ��=========//
			@RequestMapping("cartDetail.do")
			public ModelAndView process(
					               @RequestParam ("num") int num) {
				
				if(log.isDebugEnabled()) {
					log.debug("<<num>> : " + num);
				}
				
				
				
				//ItemCartCommand list = itemCartService.selectCartList(num);
				ItemCartCommand list = null;
						              //view name    �Ӽ���  �Ӽ���
				return new ModelAndView("itemCartView","list",list);
			}*/

}
