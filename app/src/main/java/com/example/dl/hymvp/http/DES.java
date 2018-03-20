package com.example.dl.hymvp.http;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

public class DES {
	
	public static String decode(String str, String seed){
		DES des = new DES(seed);
		String deCode = str;
		try {
			if(str != null)
				deCode = des.getDesString(str);
			else if(deCode == null || deCode.length() == 0)
				deCode = str;
		} catch (Exception e) {
			deCode=str;
		}
        return deCode;
	}
	
	public static String encode(String str, String seed){
		DES des = new DES(seed);
        return des.getEncString(str);
	}
	
	
	Key key;

	public DES(String str) {
		setKey(str);// 生成密匙
	}

//	public DES() {
//		setKey();
//	}

	/**
	 * 根据参数生成KEY
	 */
	private void setKey(String strKey) {
		try {
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
			byte[] androidKey =getRawKey(strKey);
			DESKeySpec keySpec = new DESKeySpec(androidKey);
			this.key = keyFactory.generateSecret(keySpec);
		} catch (Exception e) {

		}	}
	
	private byte[] getRawKey(String seed){
		if (seed != null) {
			byte[] bs = seed.getBytes();
			return new byte[]{bs[6],bs[3],bs[5],bs[2],bs[10],bs[4],bs[8],bs[2]};
		}
		return new byte[]{ 82, -65, 35, 88, -87, 105, 72, -34 };
	}
	
	private void setKey(){
		try {
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
			byte[] androidKey = new byte[]{ 82, -65, 35, 88, -87, 105, 72, -34 };
			DESKeySpec keySpec = new DESKeySpec(androidKey);
			this.key = keyFactory.generateSecret(keySpec);
		} catch (Exception e) {

		}
	}

	/**
	 * 加密String明文输入,String密文输出
	 */
	private String getEncString(String strMing) {
		byte[] byteMi = null;
		byte[] byteMing = null;
		String strMi = "";
		try {
			byteMing = strMing.getBytes("UTF8");
			byteMi = this.getEncCode(byteMing);
			strMi = MyBase64.encodeBytes(byteMi);
		} catch (Exception e) {
		} finally {
			byteMi = null;
		}
		return strMi;
	}

	/**
	 * 解密 以String密文输入,String明文输出
	 * 
	 * @param strMi
	 * @return
	 */
	private String getDesString(String strMi) {
		byte[] byteMing = null;
		byte[] byteMi = null;
		String strMing = strMi;
		try {
			byteMi = MyBase64.decode(strMi);
			if(byteMi.length >0){
			byteMing = this.getDesCode(byteMi);
			strMing = new String(byteMing, "UTF8");
			}else{
				strMing = strMi;
			}
		} catch (Exception e) {
			strMing = strMi;
		} finally {
			byteMing = null;
			byteMi = null;
		}
		return strMing;
	}

	/**
	 * 加密以byte[]明文输入,byte[]密文输出
	 * 
	 * @param byteS
	 * @return
	 */
	private byte[] getEncCode(byte[] byteS) {
		byte[] byteFina = null;
		Cipher cipher;
		try {
			cipher = Cipher.getInstance("DES");
			cipher.init(Cipher.ENCRYPT_MODE, key);
			byteFina = cipher.doFinal(byteS);
		} catch (Exception e) {
		} finally {
			cipher = null;
		}
		return byteFina;
	}

	/**
	 * 解密以byte[]密文输入,以byte[]明文输出
	 * 
	 * @param byteD
	 * @return
	 */
	private byte[] getDesCode(byte[] byteD) {
		Cipher cipher;
		byte[] byteFina = null;
		try {
			cipher = Cipher.getInstance("DES");
			cipher.init(Cipher.DECRYPT_MODE, key);
			byteFina = cipher.doFinal(byteD);
		} catch (Exception e) {
		} finally {
			cipher = null;
		}
		return byteFina;
	}

	private String filter(String str){
		String newString = "";
		try {
			newString = URLDecoder.decode(str, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			newString = "";
		}
		return newString;
	}
}