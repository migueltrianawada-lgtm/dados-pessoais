package br.senac.tads.dsw.dados_pessoais;
//RuntimeException não obriga o codigo chamador a utilizar o try/catch
public class NaoEncontradoException extends RuntimeException{

	public NaoEncontradoException(String mensagem){

		super (mensagem);
	}

}
