package kr.spring.board.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import kr.spring.board.domain.BoardCommand;
import kr.spring.board.domain.BoardReplyCommand;

public interface BoardMapper {
	// �θ��
	public List<BoardCommand> selectList(Map<String, Object> map);
	public int selectRowCount(Map<String, Object> map);
	@Insert("INSERT INTO spboard (num,title,content,reg_date,uploadfile,filename,ip,id) VALUES (board_seq.nextval, #{title}, #{content}, SYSDATE, #{uploadfile}, #{filename}, #{ip}, #{id})")
	public void insert(BoardCommand board);
	@Select("SELECT * FROM spboard WHERE num=#{num}")
	public BoardCommand selectBoard(Integer num);
	@Update("UPDATE spboard SET hit=hit+1 WHERE num=#{num}")
	public void updateHit(Integer num);
	public void update(BoardCommand board);
	@Delete("DELETE FROM spboard WHERE num=#{num}")
	public void delete(Integer num);
	
	// ���
	public List<BoardReplyCommand> selectListReply(Map<String, Object> map);
	@Select("SELECT COUNT(*) FROM spboard_reply WHERE num=#{num}")
	public int selectRowCountReply(Map<String, Object> map);
	@Insert("INSERT INTO spboard_reply (re_num,re_content,re_date,re_ip,num,id) VALUES (reply_seq.nextval,#{re_content},sysdate,#{re_ip},#{num},#{id})")
	public void insertReply(BoardReplyCommand boardReply);
	public void updateReply(BoardReplyCommand boardReply);
	public void deleteReply(Integer re_num);
	// �θ�� ������ ����� �����ϸ� �θ�� ������ ��� ����
	@Delete("DELETE FROM spboard_reply WHERE num=#{num}")
	public void deleteReplyByNum(Integer num);
}
