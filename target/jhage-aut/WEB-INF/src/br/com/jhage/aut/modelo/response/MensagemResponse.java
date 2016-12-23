package br.com.jhage.aut.modelo.response;

public class MensagemResponse {

private String mensagem;
	
	MensagemResponse(){	}
	
	public MensagemResponse(String mensagem){
		
		this.mensagem = mensagem;
	}

	public String getMensagem() {
		return mensagem;
	}

	
}
