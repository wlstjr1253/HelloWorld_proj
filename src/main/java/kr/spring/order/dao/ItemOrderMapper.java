package kr.spring.order.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import kr.spring.cart.domain.ItemCartCommand;
import kr.spring.member.domain.MemberCommand;
import kr.spring.order.domain.ItemOrderCommand;
import kr.spring.order.domain.ItemOrderDetailCommand;

public interface ItemOrderMapper {
	@Select("SELECT item_buy_hist_seq.nextval FROM dual")
	public int getOrderNum();					//주문번호
	public List<ItemOrderCommand> getListOrder(Integer ibh_idx);		//주문전체목록
	@Select("SELECT COUNT (*) FROM item_buy_hist")
	public int getOrderCount();						//주문전체개수
	//public int getOrderCountById();					//ID별 주문전체개수
	public List<ItemOrderCommand> getListOrderById(); 	//ID별 주문전체목록
	//public List<ItemOrderDetailCommand> getListOrderDetail(int order_num);	//주문번호별 주문상세
	public ItemOrderCommand getOrderDetail(int ibh_idx,String user_id);	//ID,주문번호별 주문상세
	
	@Select("SELECT * FROM ITEM_BUY_HIST_DETAIL WHERE USER_ID = #{user_id}")
	public ItemOrderDetailCommand getOrderDetailById(String user_id);

	@Insert("INSERT INTO item_buy_hist "
			+ " (ibh_idx,  ibh_total, user_id, ibh_phone, ibh_nm, ibh_email, ibh_pay, ibh_request, reg_date) "
			+ "VALUES (#{ibh_idx}, #{ibh_total}, #{user_id}, #{ibh_phone}, #{ibh_nm}, #{ibh_email}, #{ibh_pay}, #{ibh_request}, SYSDATE)")
	public void insertOrder(ItemOrderCommand itemOrderCommand);	//주문등록
	
	@Insert("INSERT INTO item_buy_hist_detail(detail_num,i_num,item_nm,item_pc,order_quan,ibh_idx,i_rent_day,i_return_day,i_rent_nc,i_return_nc) "
			+ " VALUES (item_buy_hist_detail_seq.NEXTVAL,#{i_num},#{i_nm},#{i_pc},#{ic_quan},#{ibh_idx},#{i_rent_day},#{i_return_day},#{i_rent_nc},#{i_return_nc})")
	public void insertDetailOrder(ItemCartCommand itemCartCommand);	//주문등록

//	@Insert("INSERT INTO item_buy_hist(ibh_idx,i_num,ibh_total,ph_idx,user_id,ibh_rent_num,ibh_phone,ibh_nm,ibh_email,ibh_pay,ibh_request,reg_date) VALUES (item_buy_hist_seq.NEXTVAL,#{i_num},#{ibh_quan},#{ph_idx},#{user_id},#{ibh_rent_num},#{ibh_phone},#{ibh_nm},#{ibh_email},#{ibh_pay},#{ibh_request},SYSDATE)")
//	public void insertOrder(ItemOrderCommand itemOrderCommand, List<ItemOrderCommand> itemOrder);	//주문등록


	//public void insertOrder(ItemOrderCommand itemOrderCommand, List<ItemOrderCommand> itemOrder);	//주문등록
	public void updateOrder(ItemOrderCommand itemOrderCommand);	
	public void deleteOrder(Integer num);
	
	
	
	
	public void ItemRsrv(Map<String,Object> map);

	//마이페이지
	/*@Select("SELECT * FROM item_buy_hist_detail WHERE user_id=#{user_id}")
	public List<ItemOrderCommand> selectItemBuyHist(Map<String, Object> map);*/
	/*@Select("SELECT COUNT(*) FROM item_buy_hist_detail WHERE user_id=#{user_id}")
	public int selectItemBuyHistRowCount(String user_id);*/
}
