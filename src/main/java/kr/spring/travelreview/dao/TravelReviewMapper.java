package kr.spring.travelreview.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import kr.spring.travelreview.domain.TravelReviewCommand;

public interface TravelReviewMapper {
	public List<TravelReviewCommand> selectList(Map<String, Object> map);
	public int selectRowCount(Map<String, Object> map);
	@Insert("INSERT INTO travel_review (tr_title,tr_content,tr_reg_date,tr_img,user_id,pi_id,ti_id) "
			+ "VALUES (#{tr_title}, #{tr_content}, SYSDATE, #{tr_img}, #{user_id}, #{pi_id}, #{ti_id})")
	public void insert(TravelReviewCommand review);
	@Select("SELECT * FROM travel_review WHERE tr_idx=#{tr_idx}")
	public TravelReviewCommand selectBoard(Integer review);
	@Update("UPDATE travel_review SET tr_hit=tr_hit+1 WHERE tr_idx=#{tr_idx}")
	public void updateHit(Integer tr_idx);
	public void update(TravelReviewCommand review);
	@Delete("DELETE FROM travel_review WHERE tr_idx=#{tr_idx}")
	public void delete(Integer review);
	
	// main ȣ��
	@Select("SELECT * FROM (SELECT * FROM TRAVEL_REVIEW ORDER BY TR_IDX DESC) WHERE ROWNUM < 6")
	public List<TravelReviewCommand> selectMainList();
}
