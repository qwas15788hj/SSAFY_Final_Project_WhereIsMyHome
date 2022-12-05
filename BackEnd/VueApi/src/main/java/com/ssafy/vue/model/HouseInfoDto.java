package com.ssafy.vue.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "HouseInfoDto : 아파트정보", description = "아파트의 상세 정보를 나타낸다.")
public class HouseInfoDto {

	//아파트코드
	private String aptcode;
	//아파트 이름
	private String apartmentname;
	//거래번호
	private String no;
	//거래금액
	private String dealAmount;
	//면적
	private String area;
	//층
	private String floor;
	//건설년도
	private String buildYear;
	//거래년도
	private String dealYear;
	//거래월
	private String dealMonth;
	//거래일
	private String dealDay;
	private String fullDay;
	public String getAptcode() {
		return aptcode;
	}
	public void setAptcode(String aptcode) {
		this.aptcode = aptcode;
	}
	public String getApartmentname() {
		return apartmentname;
	}
	public void setApartmentname(String apartmentname) {
		this.apartmentname = apartmentname;
	}
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}
	public String getDealAmount() {
		return dealAmount;
	}
	public void setDealAmount(String dealAmount) {
		this.dealAmount = dealAmount;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getFloor() {
		return floor;
	}
	public void setFloor(String floor) {
		this.floor = floor;
	}
	public String getBuildYear() {
		return buildYear;
	}
	public void setBuildYear(String buildYear) {
		this.buildYear = buildYear;
	}
	public String getDealYear() {
		return dealYear;
	}
	public void setDealYear(String dealYear) {
		this.dealYear = dealYear;
	}
	public String getDealMonth() {
		return dealMonth;
	}
	public void setDealMonth(String dealMonth) {
		this.dealMonth = dealMonth;
	}
	public String getDealDay() {
		return dealDay;
	}
	public void setDealDay(String dealDay) {
		this.dealDay = dealDay;
	}
	public String getFullDay() {
		return fullDay;
	}
	public void setFullDay(String fullDay) {
		this.fullDay = fullDay;
	}
}
