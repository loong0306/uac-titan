package me.uac.utils;

import lombok.extern.slf4j.Slf4j;
import me.dragon.utils.CommUsualUtils;
import org.apache.commons.codec.digest.DigestUtils;
import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

@Slf4j
public class PasswordUtils {
	
	public static String encode(String salt, String pwd) {
		// 先用AES加密
		String AesSign = encodeByAES(salt + pwd);
		// 再用Md5加密
		String sign = DigestUtils.md5Hex(getContentBytes(AesSign,"UTF-8"));
		return sign;
	}

	public static final String PWD = "y#bl.95";

	/**
	 * 加密
	 * @param content 需要加密的内容
	 * @param password 加密密码
	 */
	private static byte[] encrypt(String content, String password) {
		try {
			KeyGenerator keyGen = KeyGenerator.getInstance("AES");
			SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
			secureRandom.setSeed(password.getBytes());
			keyGen.init(128, secureRandom);
			SecretKey secretKey = keyGen.generateKey();
			byte[] enCodeFormat = secretKey.getEncoded();
			SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
			// 创建密码器
			Cipher cipher = Cipher.getInstance("AES");
			byte[] byteContent = content.getBytes("utf-8");
			// 初始化
			cipher.init(Cipher.ENCRYPT_MODE, key);
			byte[] result = cipher.doFinal(byteContent);
			// 加密
			return result;
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 加密
	 * 需要加密的内容
	 * @param password 加密密码
	 */
	private static byte[] encryptBytes(byte[] byteContent, String password) {
		try {
			KeyGenerator kgen = KeyGenerator.getInstance("AES");
			SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
			secureRandom.setSeed(password.getBytes());
			kgen.init(128, secureRandom);
			SecretKey secretKey = kgen.generateKey();
			byte[] enCodeFormat = secretKey.getEncoded();
			SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
			// 创建密码器
			Cipher cipher = Cipher.getInstance("AES");
			// 初始化
			cipher.init(Cipher.ENCRYPT_MODE, key);
			byte[] result = cipher.doFinal(byteContent);
			// 加密
			return result;
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 解密
	 * @param content 待解密内容
	 * @param password 解密密钥
	 */
	private static byte[] decrypt(byte[] content, String password) {
		try {
			KeyGenerator kgen = KeyGenerator.getInstance("AES");
			SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
			secureRandom.setSeed(password.getBytes());
			kgen.init(128, secureRandom);
			SecretKey secretKey = kgen.generateKey();
			byte[] enCodeFormat = secretKey.getEncoded();
			SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
			// 创建密码器
			Cipher cipher = Cipher.getInstance("AES");
			// 初始化
			cipher.init(Cipher.DECRYPT_MODE, key);
			byte[] result = cipher.doFinal(content);
			// 加密
			return result;
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 将二进制转换成16进制
	 *
	 * @param buf
	 * @return
	 */
	private static String parseByte2HexStr(byte buf[]) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < buf.length; i++) {
			String hex = Integer.toHexString(buf[i] & 0xFF);
			if (hex.length() == 1) {
				hex = '0' + hex;
			}
			sb.append(hex.toUpperCase());
		}
		return sb.toString();
	}

	/**
	 * 将16进制转换为二进制
	 *
	 * @param hexStr
	 * @return
	 */
	private static byte[] parseHexStr2Byte(String hexStr) {
		if (hexStr.length() < 1) {
			return null;
		}
		byte[] result = new byte[hexStr.length() / 2];
		for (int i = 0; i < hexStr.length() / 2; i++) {
			int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
			int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2),
					16);
			result[i] = (byte) (high * 16 + low);
		}
		return result;
	}

	public static String decodeByAES(String str) {
		String res = null;
		try {
			byte[] desB = parseHexStr2Byte(str);
			byte[] dbs = decrypt(desB, PWD);
			res = new String(dbs);
		} catch (Exception e) {
			log.error("AES解密失败,请查看后台日志！");
			return "解密失败";
		}
		return res;
	}

	/**
	 * 加密，默认做三次AES加密，建议密码使用不一样的
	 */
	public static String encodeByAES(String str) {
		String res = null;
		try {
			byte[] zs = encrypt(str, PWD);
			res = parseByte2HexStr(zs);
		} catch (Exception e) {
			log.error("AES加密失败,请查看后台日志！");
			return "加密失败";
		}
		return res;
	}

	/**
	 * @param content
	 * @param charset
	 */
	private static byte[] getContentBytes(String content, String charset) {
		if (charset == null || "".equals(charset)) {
			return content.getBytes();
		}
		try {
			return content.getBytes(charset);
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException("MD5签名过程中出现错误,指定的编码集不对,您目前指定的编码集是:" + charset);
		}
	}

	public static void main(String[] args) {
		String password = "administrator";
		String encodeByAES = encodeByAES(password);
		String decodeByAES = decodeByAES(encodeByAES);
		System.out.println("加密后: " + encodeByAES);
		System.out.println("解密后: " + decodeByAES);
	}

}

