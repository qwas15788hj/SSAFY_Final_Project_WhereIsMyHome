package com.ssafy.vue.model.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.vue.model.CommentDto;
import com.ssafy.vue.model.CommentParameterDto;
import com.ssafy.vue.model.FavoriteDto;
import com.ssafy.vue.model.HouseInfoDto;
import com.ssafy.vue.model.QnADto;
import com.ssafy.vue.model.QnAParameterDto;
import com.ssafy.vue.model.SidoGugunCodeDto;
import com.ssafy.vue.model.lnglatDTO;
import com.ssafy.vue.model.mapper.HouseMapMapper;
import com.ssafy.vue.model.mapper.QnAMapper;

@Service
public class HouseMapServiceImpl implements HouseMapService {
	
	@Autowired
	private SqlSession sqlSession;

	@Override
	public List<SidoGugunCodeDto> getSido() throws Exception {
		return sqlSession.getMapper(HouseMapMapper.class).getSido();
	}

	@Override
	public List<SidoGugunCodeDto> getGugunInSido(String sido) throws Exception {
		return sqlSession.getMapper(HouseMapMapper.class).getGugunInSido(sido);
	}

	@Override
	public List<HouseInfoDto> getDongInGugun(String gugun) throws Exception {
		return sqlSession.getMapper(HouseMapMapper.class).getDongInGugun(gugun);
	}

	@Override
	public List<HouseInfoDto> getAptInDong(String dong) throws Exception {
		return sqlSession.getMapper(HouseMapMapper.class).getAptInDong(dong);
	}

	@Override
	public List<lnglatDTO> getLngLat(String dongcode) throws Exception {
		return sqlSession.getMapper(HouseMapMapper.class).getLngLat(dongcode);
	}

	@Override
	public List<HouseInfoDto> getAptDeals(String aptcode) throws Exception {
		return sqlSession.getMapper(HouseMapMapper.class).getAptDeals(aptcode);
	}

	@Override
	public boolean writeComment(CommentDto commentDto) throws Exception {
		return sqlSession.getMapper(HouseMapMapper.class).writeComment(commentDto);
	}

	@Override
	public int findfavorite(FavoriteDto favoriteDto) throws Exception {
		return sqlSession.getMapper(HouseMapMapper.class).findfavorite(favoriteDto);
	}

	@Override
	public List<CommentDto> listComment(String aptCode) throws Exception {
//		int start = commentParameterDto.getPg() == 0 ? 0 : (commentParameterDto.getPg() - 1) * commentParameterDto.getSpp();
//		commentParameterDto.setStart(start);
		return sqlSession.getMapper(HouseMapMapper.class).listComment(aptCode);
	}
	
}
