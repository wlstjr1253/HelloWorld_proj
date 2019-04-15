package kr.spring.order.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import kr.spring.cart.domain.ItemCartCommand;
import kr.spring.order.domain.ItemOrderCommand;
import kr.spring.order.domain.ItemOrderDetailCommand;

public interface ItemOrderMapper {
	@Select("SELECT item_buy_hist_seq.nextval FROM dual")
	public int getOrderNum();					//�ֹ���ȣ
	public List<ItemOrderCommand> getListOrder(Integer ibh_idx);		//�ֹ���ü���
	@Select("SELECT COUNT (*) FROM item_buy_hist")
	public int getOrderCount();						//�ֹ���ü����
	//public int getOrderCountById();					//ID�� �ֹ���ü����
	public List<ItemOrderCommand> getListOrderById(); 	//ID�� �ֹ���ü���
	//public List<ItemOrderDetailCommand> getListOrderDetail(int order_num);	//�ֹ���ȣ�� �ֹ���
	public ItemOrderCommand getOrderDetail(int ibh_idx,String user_id);	//ID,�ֹ���ȣ�� �ֹ���
	

	@Insert("INSERT INTO item_buy_hist "
			+ " (ibh_idx,  ibh_total, user_id, ibh_phone, ibh_nm, ibh_email, ibh_pay, ibh_request, reg_date) "
			+ "VALUES (#{ibh_idx}, #{ibh_total}, #{user_id}, #{ibh_phone}, #{ibh_nm}, #{ibh_email}, #{ibh_pay}, #{ibh_request}, SYSDATE)")
	public void insertOrder(ItemOrderCommand itemOrderCommand);	//�ֹ����
	
	@Insert("INSERT INTO item_buy_hist_detail(detail_num,i_num,item_nm,item_pc,order_quan,ibh_idx) "
			+ " VALUES (item_buy_hist_detail_seq.NEXTVAL,#{i_num},#{i_nm},#{i_pc},#{ic_quan},#{ibh_idx})")
	public void insertDetailOrder(ItemCartCommand itemCartCommand);	//�ֹ����

//	@Insert("INSERT INTO item_buy_hist(ibh_idx,i_num,ibh_total,ph_idx,user_id,ibh_rent_num,ibh_phone,ibh_nm,ibh_email,ibh_pay,ibh_request,reg_date) VALUES (item_buy_hist_seq.NEXTVAL,#{i_num},#{ibh_quan},#{ph_idx},#{user_id},#{ibh_rent_num},#{ibh_phone},#{ibh_nm},#{ibh_email},#{ibh_pay},#{ibh_request},SYSDATE)")
//	public void insertOrder(ItemOrderCommand itemOrderCommand, List<ItemOrderCommand> itemOrder);	//�ֹ����


	//public void insertOrder(ItemOrderCommand itemOrderCommand, List<ItemOrderCommand> itemOrder);	//�ֹ����
	public void updateOrder(ItemOrderCommand itemOrderCommand);	
	public void deleteOrder(Integer num);
}
