package com.ssafy.vue.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;

import org.json.JSONObject;
import org.json.XML;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.vue.model.CommentDto;
import com.ssafy.vue.model.CommentParameterDto;
import com.ssafy.vue.model.FavoriteDto;
import com.ssafy.vue.model.HouseInfoDto;
import com.ssafy.vue.model.QnADto;
import com.ssafy.vue.model.QnAParameterDto;
import com.ssafy.vue.model.SidoGugunCodeDto;
import com.ssafy.vue.model.lnglatDTO;
import com.ssafy.vue.model.service.HouseMapService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.models.auth.In;

@RestController
@RequestMapping("/map")
@Api("Map 컨트롤러  API V1")
public class HouseMapController {

	private final Logger logger = LoggerFactory.getLogger(HouseMapController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	@Autowired
	private HouseMapService haHouseMapService;

	@ApiOperation(value = "시도 정보", notes = "전국의 시도를 반환한다.", response = List.class)
	@GetMapping("/sido")
	public ResponseEntity<List<SidoGugunCodeDto>> sido() throws Exception {
		logger.info("sido - 호출");
		return new ResponseEntity<List<SidoGugunCodeDto>>(haHouseMapService.getSido(), HttpStatus.OK);
	}

	@ApiOperation(value = "구군 정보", notes = "전국의 구군을 반환한다.", response = List.class)
	@GetMapping("/gugun")
	public ResponseEntity<List<SidoGugunCodeDto>> gugun(
			@RequestParam("sido") @ApiParam(value = "시도코드.", required = true) String sido) throws Exception {
		logger.info("gugun - 호출");
		return new ResponseEntity<List<SidoGugunCodeDto>>(haHouseMapService.getGugunInSido(sido), HttpStatus.OK);
	}

	@ApiOperation(value = "아파트 목록", notes = "지역코드와 매매계약월을 기준으로 아파트 목록을 반환한다.", response = List.class)
	@GetMapping(value = "/aptlist/{lawd_cd}/{deal_ymd}", produces = "application/json;charset=utf-8")
	public ResponseEntity<String> aptList(@PathVariable("lawd_cd") String lawdCd, @PathVariable("deal_ymd") String dealYmd) throws IOException {
		logger.info("sido - 호출");
		String serviceKey = "9Xo0vlglWcOBGUDxH8PPbuKnlBwbWU6aO7%2Bk3FV4baF9GXok1yxIEF%2BIwr2%2B%2F%2F4oVLT8bekKU%2Bk9ztkJO0wsBw%3D%3D";
		StringBuilder urlBuilder = new StringBuilder(
				"http://openapi.molit.go.kr/OpenAPI_ToolInstallPackage/service/rest/RTMSOBJSvc/getRTMSDataSvcAptTradeDev"); /*
																															 */
		urlBuilder.append("?" + URLEncoder.encode("serviceKey", "UTF-8")
				+ "=" + serviceKey);
		urlBuilder
				.append("&" + URLEncoder.encode("pageNo", "UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /* 페이지번호 */
		urlBuilder.append("&" + URLEncoder.encode("numOfRows", "UTF-8") + "="
				+ URLEncoder.encode("10", "UTF-8")); /* 한 페이지 결과 수 */
		urlBuilder.append(
				"&" + URLEncoder.encode("LAWD_CD", "UTF-8") + "=" + URLEncoder.encode(lawdCd, "UTF-8")); /* 지역코드 */
		urlBuilder.append(
				"&" + URLEncoder.encode("DEAL_YMD", "UTF-8") + "=" + URLEncoder.encode(dealYmd, "UTF-8")); /* 계약월 */
		URL url = new URL(urlBuilder.toString());
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Content-type", "application/json");
		System.out.println("Response code: " + conn.getResponseCode());
		BufferedReader rd;
		if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
			rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		} else {
			rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
		}
		StringBuilder sb = new StringBuilder();
		String line;
		while ((line = rd.readLine()) != null) {
			sb.append(line);
		}
		rd.close();
		conn.disconnect();
		System.out.println(sb.toString());
		JSONObject json = XML.toJSONObject(sb.toString());
		String jsonStr = json.toString();

		return new ResponseEntity<String>(jsonStr, HttpStatus.OK);
	}

	@GetMapping("/dong")
	public ResponseEntity<List<HouseInfoDto>> dong(@RequestParam("gugun") String gugun) throws Exception {
		return new ResponseEntity<List<HouseInfoDto>>(haHouseMapService.getDongInGugun(gugun), HttpStatus.OK);
	}
	
	@GetMapping("/apt")
	public ResponseEntity<List<HouseInfoDto>> apt(@RequestParam("dong") String dong) throws Exception {
		return new ResponseEntity<List<HouseInfoDto>>(haHouseMapService.getAptInDong(dong), HttpStatus.OK);
	}
	
	//지도에 뿌리기 위해 한개의 아파트에 대한 경도 위도를 가져옴
	@GetMapping("/info/{dongcode}")
	public ResponseEntity<List<lnglatDTO>> getListByDongAptName(@PathVariable("dongcode") String dongcode) throws Exception{
		logger.info("주소 호출"); 
		
		List<lnglatDTO> list = haHouseMapService.getLngLat(dongcode);
		//new kakao.maps.LatLng(position.lat, position.lng)
		//카카오맵에서 필요한 형식으로 바꿔주기
		for(lnglatDTO dto : list) {
			dto.setMapWord("new kakao.maps.LatLng("+dto.getLat()+", "+dto.getLng()+")");
		}
		return new ResponseEntity<List<lnglatDTO>>(list, HttpStatus.OK);
	}
	
	//하나의 아파트에 대한 거래 정보들 불러오기
	@GetMapping("/deals/{aptcode}")
	public ResponseEntity<List<HouseInfoDto>> getAptDeals(@PathVariable("aptcode") String aptcode) throws Exception{
		logger.info("거래정보 호출"); 
		return new ResponseEntity<List<HouseInfoDto>>(haHouseMapService.getAptDeals(aptcode), HttpStatus.OK);
	}
	
	//아파트 댓글 추가
	@PostMapping("/comment")
	public ResponseEntity<?> insertComment(@RequestBody CommentDto commentDto) throws Exception {
		logger.info("댓글 작성 호출");
//		logger.info("들어온 정보"+commentDto.toString());
		if(haHouseMapService.writeComment(commentDto)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
//	//좋아요 추가
//	@PostMapping("/favorite")
//	public ResponseEntity<?> favorite(@RequestBody FavoriteDto favoriteDto) {
//		logger.info("좋아요 작성 호출!!!>>>");
//		if (haHouseMapService.writeArticle(favoriteDto)) {
//			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
//		}
//		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
//	}
	
//	//찜했는지 확인
//	@GetMapping("/findfavorite")
//	public ResponseEntity<?> findfavorite(@RequestBody FavoriteDto favoriteDto) throws Exception {
//		logger.info("찜했는지 호출");
//		System.out.println("나호출!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
//		int result = 0;
//		result = haHouseMapService.findfavorite(favoriteDto);
//		return new ResponseEntity<Integer>(result, HttpStatus.OK);
//	}
	//찜했는지 확인
	@GetMapping("/findfavorite/{userid}/{deal_no}")
	public ResponseEntity<?> findfavorite(@PathVariable(value="userid") String userid, @PathVariable(value="deal_no") String deal_no) throws Exception {
		logger.info("찜했는지 호출");
		System.out.println("나호출!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		FavoriteDto favoriteDto = new FavoriteDto(userid, deal_no);
		int result = 0;
		result = haHouseMapService.findfavorite(favoriteDto);
		System.out.println(result);
		return new ResponseEntity<Integer>(result, HttpStatus.OK);
	}
	
	//Comment 목록 호출
	@GetMapping("/comment/{aptCode}")
	public ResponseEntity<List<CommentDto>> listComment(@PathVariable String aptCode) throws Exception {
		logger.info("comment listArticle - 호출");
		return new ResponseEntity<List<CommentDto>>(haHouseMapService.listComment(aptCode), HttpStatus.OK);
	}

}
