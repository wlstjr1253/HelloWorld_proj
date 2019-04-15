package kr.spring.category.controller;

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

import kr.spring.category.domain.ItemCategoryCommand;
import kr.spring.category.service.ItemCategoryService;
import kr.spring.member.domain.MemberCommand;
import kr.spring.util.PagingUtil;

@Controller
public class ItemCategoryController {
	private Logger log = Logger.getLogger(this.getClass());

	@Resource
	private ItemCategoryService itemCategoryService;

	// �ڹٺ�(Ŀ�ǵ� ��ü) �ʱ�ȭ
	@ModelAttribute("ICCommand")
	public ItemCategoryCommand initCommand() {
		return new ItemCategoryCommand();
	}

	// ================ ī�װ� ��� ================ //
	// ��� ��
	@RequestMapping(value="/item/categoryWrite.do", method=RequestMethod.GET)
	public String form() {
		return "categoryWrite";
	}

	// ���۵� ������ ó��
	@RequestMapping(value="/item/categoryWrite.do", method=RequestMethod.POST)
	public String submit(@ModelAttribute("ICCommand")
	ItemCategoryCommand itemCategoryCommand) {
		if (log.isDebugEnabled()) {
			log.debug("<<itemCategoryCommand>> : " + itemCategoryCommand);
		}
		System.out.println(itemCategoryCommand);

		// �۾���
		itemCategoryService.insert(itemCategoryCommand);

		// RedirectAttributes ��ü�� �����̷�Ʈ ������ �� ���� ���Ǵ�
		// �����͸� ����.
		// �������� �����͸� ���������� URI�󿡴� ������ �ʴ� ������ ��������
		// ���·� ����

		return "redirect:/item/categorylist.do";
	}

	// ================ ī�װ� ��� ================ //
	@RequestMapping("/item/categorylist.do")
	public ModelAndView process() {

		List<ItemCategoryCommand> list = itemCategoryService.selectList();

		//Model�� �� �̸��� ������Ʈ�� ����ְ� el�� �����پ�
		ModelAndView mav = new ModelAndView();
		mav.setViewName("admin_categoryList");
		mav.addObject("list",list);
		//mav�� ������ el�� �̾Ƽ� ����Ѵ� view����
		return mav;
	}

	//ī�װ� ����//
	@RequestMapping("/item/categoryDelete.do")
	public String submit(@RequestParam("ict_num") int ict_num) {

		if(log.isDebugEnabled()) {
			log.debug("<<ict_num>> : " + ict_num);
		}
		//�� ����
		itemCategoryService.delete(ict_num);

		return "redirect:/item/categorylist.do";
	}
	//ī�װ� ����
	@RequestMapping(value="/item/categoryUpdate.do", method=RequestMethod.GET)
	public String updateForm(@RequestParam("ict_num") int ict_num, Model model) {
		
		ItemCategoryCommand itemCategory = itemCategoryService.selectCategory(ict_num);
		List<ItemCategoryCommand> list = itemCategoryService.selectList();
		
		model.addAttribute("ICCommand",itemCategory);
		model.addAttribute("list",list);
		
		return "categoryUpdate";
	}
	// ���۵� ������ ó��
		@RequestMapping(value="/item/categoryUpdate.do", method=RequestMethod.POST)
		public String updateSubmit(@ModelAttribute("ICCommand")
		@Valid ItemCategoryCommand itemCategory,
        BindingResult result,HttpSession session, HttpServletRequest request) {
			if (log.isDebugEnabled()) {
				log.debug("<<itemCategoryCommand>> : " + itemCategory);
			}

			// ����
			itemCategoryService.update(itemCategory);

			// RedirectAttributes ��ü�� �����̷�Ʈ ������ �� ���� ���Ǵ�
			// �����͸� ����.
			// �������� �����͸� ���������� URI�󿡴� ������ �ʴ� ������ ��������
			// ���·� ����

			return "redirect:/item/categorylist.do";
		}

}