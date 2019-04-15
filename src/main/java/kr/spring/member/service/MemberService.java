package kr.spring.member.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Update;

import kr.spring.member.domain.MemberCommand;

public interface MemberService {
	//ȸ�� ����
	public void insert(MemberCommand member);
	//ȸ�� ��
	public MemberCommand selectMember(String user_id);
	//ȸ�� �� by Email
	public MemberCommand selectMemberByEmail(String user_email);
	//ȸ�� ���� ����
	public void update(MemberCommand member);
	//��й�ȣ ���� 
	public void updatePassword(MemberCommand member);
	//Ż��
	public void delete(String user_id);
	//���̵� ��û
	public void applyGuide(String user_id);
	//���̵� ����
	public void confirmGuide(String user_id);
	//���̵� ����
	public void refuseGuide(String user_id);
	//���̵� ���
	public void cancelGuide(String user_id);
	//�ӽ� ��й�ȣ �߼�
	public void updateRandomPassword(MemberCommand member);
	
	//ȸ�����
	public List<MemberCommand> selectList(Map<String, Object> map);
	public int selectRowCount(Map<String, Object> map);
	//���̵� ��û ȸ�����
	public List<MemberCommand> selectGuideList(Map<String, Object> map);
	public int selectGuideRowCount(Map<String, Object> map);
	
	//���� ����
	public List<MemberCommand> selectPayHist(Map<String, Object> map);
	public int selectPayHistRowCount(String user_id);
}
