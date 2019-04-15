package kr.spring.order.service;

import java.util.List;

import kr.spring.cart.domain.ItemCartCommand;
import kr.spring.order.domain.ItemOrderCommand;
import kr.spring.order.domain.ItemOrderDetailCommand;

public interface ItemOrderService {
	public int getOrderNum();					//�ֹ���ȣ
	public List<ItemOrderCommand> getListOrder(Integer ibh_idx);		//�ֹ���ü���
	//public int getOrderCount();						//�ֹ���ü����
	//public int getOrderCountById();					//ID�� �ֹ���ü����
	public List<ItemOrderCommand> getListOrderById(); 	//ID�� �ֹ���ü���
	//public List<ItemOrderDetailCommand> getListOrderDetail(int order_num);	//�ֹ���ȣ�� �ֹ���
	public ItemOrderCommand getOrderDetail(int ibh_idx,String user_id);	//ID,�ֹ���ȣ�� �ֹ���
	
	public void insertOrder(ItemOrderCommand itemOrderCommand,List<ItemCartCommand> itemOrderlist);	//�ֹ����
	public void updateOrder(ItemOrderCommand itemOrderCommand);	
	public void deleteOrder(Integer num);

}
