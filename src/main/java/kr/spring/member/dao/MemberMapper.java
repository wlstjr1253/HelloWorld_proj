package kr.spring.member.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import kr.spring.member.domain.MemberCommand;

public interface MemberMapper {
	@Insert("INSERT INTO user_auth (user_id) VALUES (#{user_id})")
	public void insert(MemberCommand member);
	@Insert("INSERT INTO user_info (user_id,user_nm,user_pw,user_email,user_phone) "
			+ " VALUES (#{user_id}, #{user_nm}, #{user_pw},#{user_email},#{user_phone})")
	public void insertDetail(MemberCommand member);
	
	@Select("SELECT * FROM user_auth m LEFT OUTER JOIN user_info d ON m.user_id = d.user_id WHERE m.user_id=#{user_id}")
	public MemberCommand selectMember(String user_id);
	@Select("SELECT * FROM user_info WHERE user_email=#{user_email}")
	public MemberCommand selectMemberByEmail(String user_email);
	
	
	@Update("UPDATE user_info SET user_nm=#{user_nm}, user_phone=#{user_phone}, user_email=#{user_email} WHERE user_id=#{user_id}")
	public void update(MemberCommand member);
	
	@Update("UPDATE user_info SET user_pw=#{user_pw} WHERE user_id=#{user_id}")
	public void updatePassword(MemberCommand member);
	
	@Update("UPDATE user_auth SET user_auth=0 WHERE user_id=#{user_id}")
	public void delete(String user_id);
	
	@Delete("DELETE FROM user_info WHERE user_id=#{user_id}")
	public void deleteDetail(String user_id);
	
	//가이드를 신청하면 신청 날짜를 sysdate 로 설정하고 
	//관리자 입장에서는 sysdate가 있는 사람들을 수락 하면 Y로 바꿔준다.
	
	//가이드 신청
	@Update("UPDATE user_info SET user_apply_dt=sysdate WHERE user_id=#{user_id}")
	public void applyGuide(String user_id);
	
	//가이드 수락
	@Update("UPDATE user_auth SET user_auth=2 WHERE user_id=#{user_id}")
	public void confirmGuide(String user_id);
	@Update("UPDATE user_info SET user_guide_apply='Y' WHERE user_id=#{user_id}")
	public void confirmGuideInfo(String user_id);
	//가이드 거부
	@Update("UPDATE user_info SET user_apply_dt=null WHERE user_id=#{user_id}")
	public void refuseGuideInfo(String user_id);
	
	//가이드 취소
	@Update("UPDATE user_auth SET user_auth=1 WHERE user_id=#{user_id}")
	public void cancelGuide(String user_id);
	@Update("UPDATE user_info SET user_guide_apply='N' WHERE user_id=#{user_id}")
	public void cancelGuideInfo(String user_id);
	
	//임시 비밀번호 발송
	@Update("UPDATE user_info SET user_pw=#{user_pw} WHERE user_id=#{user_id}")
	public void updateRandomPassword(MemberCommand member);
	
	//회원 목록
	public List<MemberCommand> selectList(Map<String, Object> map);
	public int selectRowCount(Map<String, Object> map);
	// 관리자 가이드 목록
	//어떻게 MemeberMapper.xml을 읽어오지 
	public List<MemberCommand> selectGuideList(Map<String, Object> map);
	public int selectGuideRowCount(Map<String, Object> map);
	
	//결제 내역
	//@Select("SELECT * FROM payment_hist WHERE user_id=#{user_id} ORDER BY ph_reg_dt DESC")
	public List<MemberCommand> selectPayHistory(Map<String, Object> map);
	@Select("SELECT COUNT(*) FROM payment_hist WHERE user_id=#{user_id}")
	public int selectPayHistoryRowCount(String user_id);
	
	//호텔 예매 내역
//	@Select("SELECT ST_NM, SRL_CHECK_IN_DT, SRL_CHECK_OUT_DT, ST_ADDR, USER_ID, SRL_TOTAL_PC "
//			+ "FROM STAYING_RSRV_LOG SRL LEFT OUTER JOIN STAYING_VW USING (SR_ID) WHERE user_id=#{user_id}")
	public List<MemberCommand> selectHotelList(Map<String, Object> map);
	@Select("SELECT COUNT(*) FROM STAYING_RSRV_LOG SRL LEFT OUTER JOIN STAYING_VW USING (SR_ID) WHERE user_id=#{user_id}")
	public int selectHotelRowCount(String user_id);
	
	//호텔 예매 내역
	/*@Select("SELECT FSI_START_NATION, FSI_START_CITY, FSI_PASS1_NATION, FSI_PASS1_CITY, FSI_PASS2_NATION, FSI_PASS2_CITY, FSI_ARRIVE_NATION, "
			+ "FSI_ARRIVE_CITY, FSI_START_DT, FSI_ARRIVE_DT, FR_RSRV_SEAT_TYPE, FI_NM, FR_TOTAL_PC "
					+ "FROM FLIGHT_RSRV_LOG FRL LEFT OUTER JOIN FLIGHT_INFO_VW USING (FSI_IDX) WHERE user_id=#{user_id}")*/
	public List<MemberCommand> selectFlightList(Map<String, Object> map);
	@Select("SELECT COUNT(*) FROM FLIGHT_RSRV_LOG FRL LEFT OUTER JOIN FLIGHT_INFO_VW USING (FSI_IDX) WHERE user_id=#{user_id}")
	public int selectFlightRowCount(String user_id);
}
