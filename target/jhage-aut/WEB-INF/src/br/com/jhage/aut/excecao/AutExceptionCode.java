package br.com.jhage.aut.excecao;

import java.util.ArrayList;
import java.util.List;

public enum AutExceptionCode {
	
	ERRRO_ACAO_ABRUPTA("Erro Acao Abrupta"),
	ERRO_AO_RECUPERAR_OBJETO_DO_BANCO_DE_DADOS("Erro ao Recuperar Objeto do Banco de Dados"),
	ERRO_AO_CRIPTOGRAFAR("Erro Ao Criptografar"),
	ERRO_AO_CONVERTER_DATA_PARA_CARACTER("Erro ao Converter Data para Caracter"),
	ERRO_AO_ENCONTRAR_O_ULTIMO_DIA_DO_MES("Erro ao Encontar o Ultimo dia do mes"),
	ERRO_AO_SOMAR_MES("Erro ao Somar Mes"),
	ERRO_AO_ENCONTRAR_O_PRIMEIRO_DIA_DO_MES("Erro ao Encontrar o Primeiro dia do mes"),
	ERRO_AO_FORMATAR_DATA_PARA_PADRAO("Erro ao Formatar Data para Padrao"),
	ERRO_AO_ValidarHelper_SE_HA_NAO_DIGITOS_NA_STRING("Erro ao Validar se ha nao digitos na string"),
	ERRO_AO_ValidarHelper_SE_HA_NAO_DIGITO_NAO_LETRA_NAO_UNDERSCORE_NA_STRING("Erro ao Validar se ha nao Digitos nao letra Underscore na String"),
	ERRO_AO_ValidarHelper_STRING_SE_CONTEM_APENAS_NUMEROS("Erro ao Validar String se Contem Apenas Numeros"),
	EMAIL_JA_EXISTENTE("E-mail já existente"),
	USER_SENHA_INVALIDO("Usuário e/ou senha inválidos"),
	USER_NAO_AUTORIZADO("Usuário nâo autorizado");
	

	private final String value;

	AutExceptionCode(final String v) {
		this.value = v;
	}

	public String value() {
		return this.value;
	}

	public static AutExceptionCode fromValue(final String v) {
		for (final AutExceptionCode c : AutExceptionCode.values()) {
			if (c.value.equals(v)) {
				return c;
			}
		}
		throw new IllegalArgumentException(v);
	}

	public static List<AutExceptionCode> list() {
		final List<AutExceptionCode> result = new ArrayList<AutExceptionCode>();
		for (final AutExceptionCode t : AutExceptionCode.values()) {
			result.add(t);
		}
		return result;
	}
}
