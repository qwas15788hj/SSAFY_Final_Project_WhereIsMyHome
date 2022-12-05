package com.ssafy.vue.model.service;

import java.util.List;

import com.ssafy.vue.model.CommentDto;
import com.ssafy.vue.model.CommentParameterDto;
import com.ssafy.vue.model.FavoriteDto;
import com.ssafy.vue.model.HouseInfoDto;
import com.ssafy.vue.model.QnADto;
import com.ssafy.vue.model.QnAParameterDto;
import com.ssafy.vue.model.SidoGugunCodeDto;
import com.ssafy.vue.model.lnglatDTO;

public interface HouseMapService {

	List<SidoGugunCodeDto> getSido() throws Exception;
	List<SidoGugunCodeDto> getGugunInSido(String sido) throws Exception;
	List<HouseInfoDto> getDongInGugun(String gugun) throws Exception;
	List<HouseInfoDto> getAptInDong(String dong) throws Exception;
	
	List<lnglatDTO> getLngLat(String dongcode) throws Exception;
	List<HouseInfoDto> getAptDeals(String aptcode) throws Exception;
	
	boolean writeComment(CommentDto commentDto) throws Exception;
//	boolean writeArticle(FavoriteDto favoriteDto) throws Exception;
	int findfavorite(FavoriteDto favoriteDto) throws Exception;
	public List<CommentDto> listComment(String aptCode) throws Exception;
}
