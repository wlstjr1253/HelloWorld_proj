package kr.spring.tour_info.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import kr.spring.tour_info.domain.Tour_infoApplyCommand;
import kr.spring.tour_info.domain.Tour_infoCommand;
import kr.spring.tour_info.domain.Tour_infoReplyCommand;
   
public interface Tour_infoMapper {
     public List<Tour_infoCommand> selectList(Map<String,Object> map);
     public int selectRowCount(Map<String,Object> map);
     @Insert("INSERT INTO tour_info (ti_nm,ti_start_day,ti_end_day,ti_min_pp,"
     		+ "ti_max_pp,ti_pickup_place,ti_pickup_time,uploadfile,ti_pc,ti_content,user_id,ti_img,reg_date) "
     		+ "VALUES (#{ti_nm},#{ti_start_day},#{ti_end_day},#{ti_min_pp},"
     		+ "#{ti_max_pp},#{ti_pickup_place},#{ti_pickup_time},#{uploadfile},#{ti_pc},#{ti_content},#{user_id},#{ti_img},SYSDATE)")
     public void insert(Tour_infoCommand tour_info);
     @Select("SELECT * FROM tour_info WHERE ti_id=#{ti_id}")
     public Tour_infoCommand selectTour_info(Integer ti_id);
     
     public void update(Tour_infoCommand tour_info);
     @Delete("DELETE FROM tour_info WHERE ti_id=#{ti_id}")
     public void delete(Integer ti_id);
     
     //���
     public List<Tour_infoReplyCommand> selectListReply(Map<String,Object> map);
     @Select("SELECT COUNT(*) FROM tour_reply WHERE ti_id=#{ti_id}")
     public int selectRowCountReply(Map<String,Object> map);
     @Insert("INSERT INTO tour_reply (tr_content,tr_date,ti_id,user_id) VALUES (#{tr_content},sysdate,#{ti_id},#{user_id})")
     public void insertReply(Tour_infoReplyCommand tour_infoReply);
     @Update("UPDATE tour_reply SET tr_content=#{tr_content} WHERE tr_idx=#{tr_idx}")
     public void updateReply(Tour_infoReplyCommand tour_infoReply);
     @Delete("DELETE FROM tour_reply WHERE tr_idx=#{tr_idx}")
     public void deleteReply(Integer tr_idx);
     
     //�θ�� ���� �� ��� �����ϸ� �θ�� ���� �� ��� ����
     @Delete("DELETE FROM tour_reply WHERE ti_id=#{ti_id}")
     public void deleteReplyByNum(Integer ti_id);
     
     //���� ��û
     public List<Tour_infoApplyCommand> selectListApply(Map<String,Object> map);
     @Select("SELECT COUNT(*) FROM tour_apply_hist WHERE user_id=#{user_id}")
     public int selectRowCountApply(Map<String,Object> map);
     /*public Tour_infoApplyCommand selectTour_info(Integer ta_idx);*/
     @Insert("INSERT INTO tour_apply_hist (user_id,ti_id,ti_state,ti_reg_dt) VALUES (#{user_id},#{ti_id},#{ti_state},SYSDATE)")
     public void insertApply(Tour_infoApplyCommand tour_infoApply);
     @Delete("DELETE FROM tour_apply_hist WHERE ta_idx=#{ta_idx}")
     public void deleteApply(Integer ta_idx);
     
}
