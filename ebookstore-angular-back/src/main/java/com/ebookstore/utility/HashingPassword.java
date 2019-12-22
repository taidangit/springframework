package com.ebookstore.utility;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Random;

public class HashingPassword {
	private static final String SALT ="salt";
	
	public static String encrypt(String originPassword) {
		String result=null;
		byte[] salt = SALT.getBytes();
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-512");
			md.update(salt);
			byte[] hashPass = md.digest(originPassword.getBytes(StandardCharsets.US_ASCII));
			result = Base64.getEncoder().encodeToString(hashPass).substring(0, 32);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public static String randomPassword() {
	String SALTCHARS = "ABCEFGHIJKLMNOPQRSTUVWXYZ1234567890";
		StringBuilder salt = new StringBuilder();
		Random rd = new Random();

		while (salt.length()<18) {
			int index= (int) (rd.nextFloat()*SALTCHARS.length());
			salt.append(SALTCHARS.charAt(index));
		}
		String saltStr = salt.toString();
		return saltStr;
	}
	
}
