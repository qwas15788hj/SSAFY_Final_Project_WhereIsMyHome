package com.ssafy.vue.model.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.vue.model.CommentDto;
import com.ssafy.vue.model.CommentParameterDto;
import com.ssafy.vue.model.FavoriteDto;
import com.ssafy.vue.model.HouseInfoDto;
import com.ssafy.vue.model.SidoGugunCodeDto;
import com.ssafy.vue.model.lnglatDTO;

@Mapper
public interface HouseMapMapper {

	List<SidoGugunCodeDto> getSido() throws SQLException;
	List<SidoGugunCodeDto> getGugunInSido(String sido) throws SQLException;
	List<HouseInfoDto> getDongInGugun(String gugun) throws SQLException;
	List<HouseInfoDto> getAptInDong(String dong) throws SQLException;
	
	List<lnglatDTO> getLngLat(String dongcode) throws Exception;
	List<HouseInfoDto> getAptDeals(String aptcode) throws Exception;
	
	boolean writeComment(CommentDto commentDto) throws Exception;
	int findfavorite(FavoriteDto favoriteDto) throws SQLException;
	public List<CommentDto> listComment(String aptCode) throws SQLException;
	
}
