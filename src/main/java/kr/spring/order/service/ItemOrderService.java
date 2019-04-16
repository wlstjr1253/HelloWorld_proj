package kr.spring.order.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Select;

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
	
	public void insertOrder(ItemOrderCommand itemOrderCommand,List<ItemCartCommand> itemOrderlist, String user_id,List<String> ic_nums);	//�ֹ����
	public void updateOrder(ItemOrderCommand itemOrderCommand);	
	public void deleteOrder(Integer num);
	
	/*public List<ItemOrderCommand> selectItemBuyHist(Map<String, Object> map);
	public int selectItemBuyHistRowCount(String user_id);*/

}
