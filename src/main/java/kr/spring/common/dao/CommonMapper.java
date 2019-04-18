package kr.spring.common.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import kr.spring.common.domain.NationCityCommand;

public interface CommonMapper {
 
	// 시연 때 편함을 위해 등록되어 있는 도쿄와 파리만 위로 정렬
	@Select("SELECT * FROM NATION_CITY ORDER BY DECODE(NC_CD, 'TYO', 1, 'PAR', 2, NC_NATION, NC_CITY)")
	public List<NationCityCommand> selectNCList();
}
