package kr.spring.common.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import kr.spring.common.domain.NationCityCommand;

public interface CommonMapper {
 
	// �ÿ� �� ������ ���� ��ϵǾ� �ִ� ����� �ĸ��� ���� ����
	@Select("SELECT * FROM NATION_CITY ORDER BY DECODE(NC_CD, 'TYO', 1, 'PAR', 2, NC_NATION, NC_CITY)")
	public List<NationCityCommand> selectNCList();
}
