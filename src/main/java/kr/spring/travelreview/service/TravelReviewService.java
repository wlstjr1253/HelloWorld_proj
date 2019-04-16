package kr.spring.travelreview.service;

import java.util.List;
import java.util.Map;


import kr.spring.travelreview.domain.TravelReviewCommand;

public interface TravelReviewService {
	// 부모글
	public List<TravelReviewCommand> selectList(Map<String, Object> map);
	public int selectRowCount(Map<String, Object> map);
	public void insert(TravelReviewCommand review);
	public TravelReviewCommand selectBoard(Integer num);
	public void updateHit(Integer num);
	public void update(TravelReviewCommand review);
	public void delete(Integer num);
	
	// main 호출
	public List<TravelReviewCommand> selectMainList();
}
