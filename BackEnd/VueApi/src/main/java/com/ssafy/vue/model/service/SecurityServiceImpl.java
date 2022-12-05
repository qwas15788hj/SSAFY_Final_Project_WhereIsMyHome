package com.ssafy.vue.model.service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SecurityServiceImpl implements SecurityService{
	
	private MessageDigest mod;
	
	public SecurityServiceImpl() throws NoSuchAlgorithmException {
		mod =  MessageDigest.getInstance("SHA-256");
	}
	
	
	@Override
	public String Encryption(String password,String saltv) {
		System.out.println("비밀번호 암호화 호출!!!!>>>>>>>>>>>");
		String value=password+  saltv;
		mod.update(value.getBytes());
		byte data[] = mod.digest();
		StringBuilder sb = new StringBuilder();
		for(byte d : data) {
			sb.append(String.format("%02x", d));
		}
		return sb.toString();
	}

}