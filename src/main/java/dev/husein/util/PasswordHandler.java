package dev.husein.util;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import org.apache.commons.codec.binary.Hex;

public class PasswordHandler {
	public static String hashPassword(String password) {
		try {
			String saltStr = "1234";
			byte[] salt = saltStr.getBytes();
			System.out.println();
			KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 65536, 128);
			SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
			byte[] hashByteArray = factory.generateSecret(spec).getEncoded();
			return Hex.encodeHexString(hashByteArray);
		} catch (NoSuchAlgorithmException nsae) {
			System.err.println(nsae.getMessage());
		} catch (InvalidKeySpecException ikse) {
			System.err.println(ikse.getMessage());
		}
		
		return null;
	}

}
