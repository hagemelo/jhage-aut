package br.com.jhage.aut.dto;

/**
 * 
 * @author Alexanser Melo
 *
 */
public class MensagemDto extends DefaultDto{
	
	private String mensagem;
	
	public MensagemDto(){
		
	}
	
	public MensagemDto(String mensagem){
		
		this.setMensagem(mensagem);
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
	
}
