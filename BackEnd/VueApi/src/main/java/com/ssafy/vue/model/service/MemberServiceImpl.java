package com.ssafy.vue.model.service;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.vue.model.MemberDto;
import com.ssafy.vue.model.mapper.MemberMapper;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	private SqlSession sqlSession;

	@Override
	public MemberDto login(MemberDto memberDto) throws Exception {
		if (memberDto.getUserid() == null || memberDto.getUserpwd() == null) {
			System.out.println("로그인 서비스 null 호출!!!>>>");
			return null;			
		}
		System.out.println("로그인 서비스 null 안호출!!!>>>>>>>>");
		return sqlSession.getMapper(MemberMapper.class).login(memberDto);
	}

	@Override
	public MemberDto userInfo(String userid) throws Exception {
		return sqlSession.getMapper(MemberMapper.class).userInfo(userid);
	}

	@Override
	public void saveRefreshToken(String userid, String refreshToken) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		map.put("userid", userid);
		map.put("token", refreshToken);
		sqlSession.getMapper(MemberMapper.class).saveRefreshToken(map);
	}

	@Override
	public Object getRefreshToken(String userid) throws Exception {
		return sqlSession.getMapper(MemberMapper.class).getRefreshToken(userid);
	}

	@Override
	public void deleRefreshToken(String userid) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		map.put("userid", userid);
		map.put("token", null);
		sqlSession.getMapper(MemberMapper.class).deleteRefreshToken(map);
	}

	@Override
	public int insert(MemberDto memberDto) throws Exception {
		System.out.println("회원 가입 서비스 호출!!!>>>" + memberDto.toString());
		return sqlSession.getMapper(MemberMapper.class).register(memberDto);
	}
	
	@Override
	public int checkId(MemberDto memberDto) throws Exception {
		return sqlSession.getMapper(MemberMapper.class).checkId(memberDto);
	}

	@Override
	public int delete(String userid) throws Exception {
		return sqlSession.getMapper(MemberMapper.class).delete(userid);
	}

	@Override
	public int update(MemberDto memberDto) throws Exception {
		return sqlSession.getMapper(MemberMapper.class).update(memberDto);
	}

}
