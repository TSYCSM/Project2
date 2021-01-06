package com.tsycsm.agileoffice.common;

import java.io.UnsupportedEncodingException;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.stereotype.Component;

@Component
public class SecureManager {
		
	public String getSecureData(String password) {
		StringBuffer sb = new StringBuffer();
		
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			byte[] data= password.getBytes("UTF-8");
			byte[] hash = digest.digest(data);
			
			for(int i=0; i<hash.length;i++) {
				String hex = Integer.toHexString(0xff&hash[i]);
			
				if(hex.length() == 1) {
					sb.append("0");
				}
				sb.append(hex);
			}
			
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		return sb.toString();
	}
	
}










