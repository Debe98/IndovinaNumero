package it.polito.tdp.indovinanumero;

import it.polito.tdp.indovinanumero.model.*;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class FXMLController {
	
	private Model model;

    @FXML
    private TextArea txtRisultato;

    @FXML
    private Button btnNuova;

    @FXML
    private TextField txtRimasti;

    @FXML
    private GridPane layoutTentativo;

    @FXML
    private TextField txtTentativi;

    @FXML
    private Button btnProva;

    @FXML
    void doNuova(ActionEvent event) {
    	//gestione dell'inizio di una nuova partita - Logica del gioco
    	model.nuovaPartita();
    	
    	//gestione dell'interfaccia
    	layoutTentativo.setDisable(false);
    	txtRisultato.clear();
    	txtRimasti.setText(model.getTentativiRimasti());

    }

    @FXML
    void doTentativo(ActionEvent event) {
    	//leggere l'input dell'utente
    	String ts = txtTentativi.getText();
    	
    	int ritorno = model.nuovaProva(ts);
    	
    	if (ritorno == -5) {
    		txtRisultato.appendText("Devi Inserire un numero!");
    		return;
    	}
    	
    	
    	if (ritorno == 0) {
    		//HO INDOVINATO!
    		txtRisultato.appendText("HAI VINTO!!! Hai utilizzato " + model.getTentativiFatti() + " tentativi!");
    		layoutTentativo.setDisable(true);
    		model.finePartita();
    		return;
    	}
    	
    	if(ritorno == 5) {
    		//Ho esaurito i tentativi -> HO PERSO
    		txtRisultato.appendText("HAI PERSO!!! Il numero segreto era: " + model.getNumeroSegreto());
    		layoutTentativo.setDisable(true);
    		model.finePartita();
    		return;
    	}
    	
    	//informare l'utente se il tentativo è troppo alto o troppo basso
    	if(ritorno == -1)
    		txtRisultato.appendText("Tentativo troppo BASSO \n");
    	else if (ritorno == 1)
    		txtRisultato.appendText("Tentativo troppo ALTO \n");
    	
    	txtRimasti.setText(model.getTentativiRimasti());
    }

    @FXML
    void initialize() {
        assert txtRisultato != null : "fx:id=\"txtRisultato\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnNuova != null : "fx:id=\"btnNuova\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtRimasti != null : "fx:id=\"txtRimasti\" was not injected: check your FXML file 'Scene.fxml'.";
        assert layoutTentativo != null : "fx:id=\"layoutTentativo\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtTentativi != null : "fx:id=\"txtTentativi\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnProva != null : "fx:id=\"btnProva\" was not injected: check your FXML file 'Scene.fxml'.";

    }
    
    public void setModel(Model model) {
    	this.model = model;
    }
}
