package kr.spring.cart.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.spring.cart.dao.ItemCartMapper;
import kr.spring.cart.domain.ItemCartCommand;


@Service("itemCartService")
public class ItemCartServiceImpl implements ItemCartService{

	@Resource
	private ItemCartMapper itemCartMapper;
	
	//��ٱ��ϸ��
	@Override
	public List<ItemCartCommand> selectCartList(String user_id) {
		// TODO Auto-generated method stub
		return itemCartMapper.selectCartList(user_id);
	}

	
	/*@Override
	public int selectRowCount(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return 0;
	}*/

	//��ٱ����߰�
	@Override
	public void insertCart(ItemCartCommand cart) {
		itemCartMapper.insertCart(cart);
		
	}

	//��ٱ��� �ݾ��հ�
	@Override
	public int getTotalById(String user_id) {
		// TODO Auto-generated method stub
		return itemCartMapper.getTotalById(user_id);
	}

	//��ٱ��� ��ǰ Ȯ��
	@Override
	public int selectCartDetail(int i_num,String user_id) {
		// TODO Auto-generated method stub
		return itemCartMapper.selectCartDetail(i_num,user_id);
	}

	//��ٱ��� ��ǰ��������
	@Override
	public void updateCart(ItemCartCommand cart) {
		itemCartMapper.updateCart(cart);
		
	}

	@Override
	public void deleteCart(Integer ic_num) {
		// TODO Auto-generated method stub
		itemCartMapper.deleteCart(ic_num);
	}


	

}
