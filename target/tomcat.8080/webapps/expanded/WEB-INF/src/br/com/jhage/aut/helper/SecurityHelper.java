package br.com.jhage.aut.helper;

import java.io.UnsupportedEncodingException;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;
import java.util.UUID;

import br.com.jhage.aut.excecao.AutException;
import br.com.jhage.aut.excecao.AutExceptionCode;

/**
 * 
 * @author Alexsander Melo
 * @since 17/12/2016
 *
 */

final public class SecurityHelper {

	public static String criptografar(final String value)	throws AutException {

		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("SHA-1");
			md.reset();
			return byteToHex(md.digest(value.getBytes("UTF-8")));
			
		} catch (final NoSuchAlgorithmException e) {
			throw new AutException(e,
					AutExceptionCode.ERRO_AO_CRIPTOGRAFAR);
			
		} catch (final UnsupportedEncodingException e) {
			throw new AutException(e,
					AutExceptionCode.ERRO_AO_CRIPTOGRAFAR);
			
		} catch (final Exception e) {
			throw new AutException(e,
					AutExceptionCode.ERRO_AO_CRIPTOGRAFAR);
			
		}
	}

	public static String getToken(){
		
		 UUID uuid = UUID.randomUUID();
	     return uuid.toString();
	}
	
	protected static String byteToHex(final byte[] hash) throws Exception {
		
		@SuppressWarnings("resource")
		final Formatter formatter = new Formatter();
		for (final byte b : hash) {
			formatter.format("%02x", b);
		}
		return formatter.toString();
	}
	
	
	
	
	

}
