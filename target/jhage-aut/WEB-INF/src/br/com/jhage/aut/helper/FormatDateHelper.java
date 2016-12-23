package br.com.jhage.aut.helper;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import br.com.jhage.aut.excecao.AutException;
import br.com.jhage.aut.excecao.AutExceptionCode;

/***
 * 
 * @author Alexsander Melo
 * @since 09/01/2013
 * @version 1.0 Classe responsavel por fomata��o e valida��o de datas
 */
final public class FormatDateHelper {

	private static SimpleDateFormat formatoPadraoDaData = null;
	private static final String FORMAT_DEFAULT = "dd/MM/yyyy";
	
	public static String converterDataParaCaracter(final Date date) throws AutException {
			
			return FormatDateHelper.converterDataParaCaracter(date, FORMAT_DEFAULT);
	}

	public static String converterDataParaCaracter(final Date date, final String value) throws AutException {

		try {
			FormatDateHelper.inserirFormatoPadraoDaData(value);
			return FormatDateHelper.formatoPadraoDaData.format(date).toString();
		} catch (final Exception e) {
			throw new AutException(e, AutExceptionCode.ERRO_AO_CONVERTER_DATA_PARA_CARACTER);
		}
	}

	public static boolean isMesmadata(final Date date1, final Date date2) throws AutException {

		final Date primeiraData = FormatDateHelper.formatarDataParaPadrao(date1, "dd/MM/yyyy");
		final Date segundaData = FormatDateHelper.formatarDataParaPadrao(date2, "dd/MM/yyyy");
		return primeiraData.compareTo(segundaData) == 0;
	}

	public static Date formatarDataParaPadrao(final String date, final String value) throws AutException {

		try {
			FormatDateHelper.inserirFormatoPadraoDaData(value);
			return FormatDateHelper.formatoPadraoDaData.parse(date);
		} catch (final Exception e) {
			throw new AutException(e, AutExceptionCode.ERRO_AO_FORMATAR_DATA_PARA_PADRAO);
		}
	}
	
	public static Date formatarDataParaPadrao(final Date date, final String value) throws AutException {

		try {
			FormatDateHelper.inserirFormatoPadraoDaData("dd/MM/yyyy");
			return FormatDateHelper.formatoPadraoDaData.parse(FormatDateHelper.formatoPadraoDaData.format(date));
		} catch (final Exception e) {
			throw new AutException(e, AutExceptionCode.ERRO_AO_FORMATAR_DATA_PARA_PADRAO);
		}
	}

	private static void inserirFormatoPadraoDaData(final String formato) {
		FormatDateHelper.formatoPadraoDaData = new SimpleDateFormat(formato, new Locale("pt", "BR"));
	}

}
