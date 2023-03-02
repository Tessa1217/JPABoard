package com.vuejpa.demo.common.util;

import java.security.Key;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;



public class Aes256Utils {
	
	private static final String ALGORITHM = "AES/CBC/PKCS5PADDING";
	private static final byte[] KEY = "MySuperSecretKey".getBytes();
	private static byte[] iv = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
    private static IvParameterSpec ivspec = new IvParameterSpec(iv);
	
	public static String encode(String encodeValue) {
		Key key = new SecretKeySpec(KEY, "AES");
		try {
			Cipher c = Cipher.getInstance(ALGORITHM);
			c.init(Cipher.ENCRYPT_MODE, key, ivspec);
			return new String(Base64.getEncoder().encode(c.doFinal(encodeValue.getBytes())));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
	
	public static String decode(String decodeValue) {
		Key key = new SecretKeySpec(KEY, "AES");
		try {
			Cipher c = Cipher.getInstance(ALGORITHM);
			c.init(Cipher.DECRYPT_MODE, key, ivspec);
			return new String(c.doFinal(Base64.getDecoder().decode(decodeValue)));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
	
}
