package kr.spring.member.service;

import java.util.List;
import java.util.Map;

import kr.spring.member.domain.MemberCommand;

public interface MemberService {
	//회원 가입
	public void insert(MemberCommand member);
	//회원 상세
	public MemberCommand selectMember(String user_id);
	//회원 상세 by Email
	public MemberCommand selectMemberByEmail(String user_email);
	//회원 정보 수정
	public void update(MemberCommand member);
	//비밀번호 수정 
	public void updatePassword(MemberCommand member);
	//탈퇴
	public void delete(String user_id);
	//가이드 신청
	public void applyGuide(String user_id);
	//가이드 수락
	public void confirmGuide(String user_id);
	//가이드 거절
	public void refuseGuide(String user_id);
	//가이드 취소
	public void cancelGuide(String user_id);
	//임시 비밀번호 발송
	public void updateRandomPassword(MemberCommand member);
	
	//회원목록
	public List<MemberCommand> selectList(Map<String, Object> map);
	public int selectRowCount(Map<String, Object> map);
	//가이드 신청 회원목록
	public List<MemberCommand> selectGuideList(Map<String, Object> map);
	public int selectGuideRowCount(Map<String, Object> map);
	
	//결제 내역
	public List<MemberCommand> selectPayHistory(Map<String, Object> map);
	public int selectPayHistoryRowCount(String user_id);
	
	//호텔 내역
	public List<MemberCommand> selectHotelList(Map<String, Object> map);
	public int selectHotelListRowCount(String user_id);
	
	//호텔 내역
	public List<MemberCommand> selectFlightList(Map<String, Object> map);
	public int selectFlightListRowCount(String user_id);
}
