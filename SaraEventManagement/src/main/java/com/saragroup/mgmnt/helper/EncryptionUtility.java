package com.saragroup.mgmnt.helper;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

import com.saragroup.mgmnt.exception.EventServiceException;

public class EncryptionUtility {
	
	private static String message="Aditya";
	private static String encryptedMsg="";
	
	private static byte[] hexToBytes(char[] hex){
		int length = hex.length/2;
		byte[] raw = new byte[length];
		for (int i = 0; i < raw.length; i++) {
			int high = Character.digit(hex[i*2], 16);
			int low = Character.digit(hex[i*2+1], 16);
			
			int value= (high << 4) | low;
			if(value > 127)
				value -=256;
			raw[i] = (byte) value;
		}
		return raw;
	}
	
	private static byte[] hexToBytes(String hex){
		return hexToBytes(hex.toCharArray());
	}
	
	private static String asHex(byte buf[]){
		StringBuffer sb = new StringBuffer(buf.length*2);
		for (int i = 0; i < buf.length; i++) {
			if(((int)buf[i] & 0xff)< 0x10)
				sb.append("0");
			sb.append(Long.toString((int) buf[i] & 0xff,16));
		}
		return sb.toString();
	}
	public static void main(String[] args) {
		
			try {
				encryptedMsg=encrypt(message);
				System.out.println("decrypted back:  "+decrypt(encryptedMsg));
			} catch (EventServiceException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			

	}
	
	public static String encrypt(String message) throws EventServiceException{
		byte[] msg_bytes = message.getBytes();
		byte[] encrypted;
		
		try {
			KeyGenerator kygen =KeyGenerator.getInstance("AES");
			kygen.init(256);
			String strKey = "CustomEncryptionKeyGenerationKeySaraGroupTestData";
			byte[] skeyspec = hexToBytes(strKey);
			//skeyspec is the key to encrypt and decrypt
			SecretKeySpec sKey = new SecretKeySpec(skeyspec, "AES");
			//Generate the secret key specs
			
			byte[] raw = sKey.getEncoded();
			SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
			//Instantiate cipher
			Cipher cipher = Cipher.getInstance("AES");
			cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
			
			encrypted = cipher.doFinal(msg_bytes);
		} catch (Exception e) {
			throw new EventServiceException("USER_ENCRYPTION_FAILED");
		} 
		
		
		return asHex(encrypted);
		
	}
	
	public static String decrypt(String message) throws EventServiceException {
		String originalString;
		try {
			KeyGenerator kgen = KeyGenerator.getInstance("AES");
			kgen.init(256);
			String strKey = "CustomEncryptionKeyGenerationKeySaraGroupTestData";
			byte[] skeyspec = hexToBytes(strKey);
			
			SecretKeySpec sKey =  new SecretKeySpec(skeyspec, "AES");
			//Generate thesecret key specs
			byte[] raw = sKey.getEncoded();
			SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
			//instantiate the cipher
			Cipher cipher = Cipher.getInstance("AES");
			cipher.init(Cipher.DECRYPT_MODE, skeySpec);
			System.out.println(message);
			
			byte[] encryted = hexToBytes(message);
			byte[] original = cipher.doFinal(encryted);
			originalString = new String(original);
		} catch (Exception e) {
			throw new EventServiceException("USER_ENCRYPTION_FAILED");
		}
		return originalString;
	}
}
