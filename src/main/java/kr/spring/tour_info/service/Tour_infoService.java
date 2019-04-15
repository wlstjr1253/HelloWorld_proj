package kr.spring.tour_info.service;

import java.util.List;
import java.util.Map;

import kr.spring.board.domain.BoardReplyCommand;
import kr.spring.tour_info.domain.Tour_infoApplyCommand;
import kr.spring.tour_info.domain.Tour_infoCommand;
import kr.spring.tour_info.domain.Tour_infoReplyCommand;

public interface Tour_infoService {
	//�θ� ��
	public List<Tour_infoCommand> selectList(Map<String,Object> map);
	public int selectRowCount(Map<String,Object> map);
	public void insert(Tour_infoCommand tour_info);
	public Tour_infoCommand selectTour_info(Integer ti_id);
	public void update(Tour_infoCommand tour_info);
	public void delete(Integer ti_id);
    
	//���
	public List<Tour_infoReplyCommand> selectListReply(Map<String,Object> map);
    public int selectRowCountReply(Map<String, Object> map);
    public void insertReply(Tour_infoReplyCommand tour_infoReply);
    public void updateReply(Tour_infoReplyCommand tour_infoReply);
	public void deleteReply(Integer tr_idx);
	// �θ�� ������ ����� �����ϸ� �θ�� ������ ��� ����
	public void deleteReplyByNum(Integer ti_id);
  
	//���� ��û
	public List<Tour_infoApplyCommand> selectListApply(Map<String,Object> map);
	public int selectRowCountApply(Map<String,Object> map);
	public void insertApply(Tour_infoApplyCommand tour_infoApply);
	public void deleteApply(Integer ta_idx);
	
	//��ϱ� ���� �� ��û ���� ����
	public void deleteApplyByNum(Integer ti_id);

}
