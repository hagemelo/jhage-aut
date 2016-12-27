package br.com.jhage.aut.helper;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final  class ValidarHelper{

	private static Pattern padrao = null;
	private static Matcher matcher = null;

	public static boolean enulo(final Object object){
		
		if (object == null) {
			return true;
		}
		return false;
	}

	public static boolean naoENuloENaoEVazio(final String... object) {
		
		boolean result = true;
		if (ValidarHelper.enulo(object)) {
			result = false;
		} else {
			for (final String obj : object) {
				result = result && !ValidarHelper.enulo(obj) && !obj.isEmpty();
			}
		}
		return result;
	}

}
