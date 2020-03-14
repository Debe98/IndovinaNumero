package it.polito.tdp.indovinanumero.model;

public class Model {
	
	private final int NMAX;
	private final int TMAX;
	private int segreto;
	private int tentativiFatti;
	private boolean inGioco;
	
	public Model() {
		super();
		NMAX = 100;
		TMAX = 8;
		inGioco = false;
	}
	
	public void nuovaPartita() {
		this.segreto = (int)(Math.random() * NMAX) + 1;
    	this.tentativiFatti = 0;
    	this.inGioco = true;
	}
	
	public int nuovaProva(String ts) {
    	int tentativo;
    	try {
    		tentativo = Integer.parseInt(ts);
    	} catch (NumberFormatException e) {
    		return -5;
    		//txtRisultato.appendText("Devi inserire un numero!\n");
    	}
    	
    	this.tentativiFatti ++;
    	
    	
    	if(tentativo == this.segreto) {
    		//HO INDOVINATO!
    		//txtRisultato.appendText("HAI VINTO!!! Hai utilizzato " + this.tentativiFatti + " tentativi!");
    		this.inGioco = false;
    		return 0;
    	}
    	
    	if(tentativiFatti == TMAX) {
    		//Ho esaurito i tentativi -> HO PERSO
    		//txtRisultato.appendText("HAI PERSO!!! Il numero segreto era: " + this.segreto);
    		this.inGioco = false;
    		return 5;
    	}
    	
    	//informare l'utente se il tentativo è troppo alto o troppo basso
    	if(tentativo < this.segreto) 
    		//txtRisultato.appendText("Tentativo troppo BASSO \n");
    		return -1;
    	else
    		//txtRisultato.appendText("Tentativo troppo ALTO \n");
    		return 1;
	}
	
	public String getTentativiRimasti() {
		return Integer.toString(TMAX-tentativiFatti);
	}
	
	public int getNumeroSegreto() {
		return segreto;
	}
	
	public int getTMAX() {
		return TMAX;
	}

	public int getTentativiFatti() {
		return tentativiFatti;
	}

	public void finePartita() {
		this.inGioco = false;
	}
	
	
	
}
