package taskboard.boundary;

import taskboard.entity.Progetto;

public class ReportControl {

	public int contaAttivita(Progetto p){
		return 14;
	}
	
	public int contaNONIniziati(Progetto p){
		return 2;
	}
	
	public int contaInCorso(Progetto p){
		return 7;
	}

	
	public int contaCompletati(Progetto p){
		return 5;
	}

	public int contaSviluppo(Progetto p){
		return 0;
	}

	public int statisticheStudente(Progetto p){
		return 0;
	}

}
