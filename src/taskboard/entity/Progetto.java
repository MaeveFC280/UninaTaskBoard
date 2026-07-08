package taskboard.entity;

public class Progetto {
	private String codiceInvito;
	private String nome;
	private String descrizione;
	
	public Progetto(String codice, String nome, String desc) {
		this.setCodiceInvito(codice);
		this.setNome(nome);
		this.setDescrizione(desc);
	}

	
	//getter e setter
	public String getCodiceInvito() {
		return codiceInvito;
	}

	public void setCodiceInvito(String codiceInvito) {
		this.codiceInvito = codiceInvito;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	
	//overdrive per far apparrire correttamente il nome 
	@Override
	public String toString() {
		return getNome();
	}

}
