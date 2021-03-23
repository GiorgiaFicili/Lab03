package it.polito.tdp.spellchecker;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.spellchecker.model.Dictionary;
import it.polito.tdp.spellchecker.model.RichWord;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class FXMLController {

	private Dictionary model;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> boxLanguage;
    
    @FXML
    private TextArea txtResult;
    
    @FXML
    private TextArea txtInput;
    
    @FXML
    private Label txtErrori;

    @FXML
    private Label txtTempo;
    
    @FXML
    private Button btnSpellCheck;
    
    @FXML
    private Button btnClearText;

    @FXML
    void doClearText(ActionEvent event) {

    	this.txtInput.clear();
    	this.txtResult.clear();
    	
    }
    
    @FXML
    void doLanguage(ActionEvent event) {

    	this.btnSpellCheck.setDisable(false);
    	
    }

    @FXML
    void doSpellCheck(ActionEvent event) {

		long start = System.nanoTime();

		if(this.txtInput.getText()=="") {
			this.txtResult.setText("Devi inserire qualche parola");
		} else {
		
	    	String language = this.boxLanguage.getValue();
	    	
	    	String input = this.txtInput.getText().toLowerCase();
	    	input = input.replaceAll("[.,\\/#!?$%\\^&\\*;:{}=\\-_`~()\\[\\]\"]", "");
	    	
	    	String[] w = input.split(" ");
	    	List<String> words = new ArrayList<>();
	    	
	    	for(String s : w) {
	    		
	    		words.add(s);
	    		
	    	}
	    	
	    	model.loadDictionary(language);
	    	
	    	//List<RichWord> rw = model.spellCheckText(words);
	    	//List<RichWord> rw = model.spellCheckTextLinear(words);
	    	List<RichWord> rw = model.spellCheckTextDichotomic(words);
	    	
	    	String s = "";
	    	int count = 0;
	    	
	    	for(RichWord r : rw) {
	    		
	    		if(!r.isCorretta()) {
	    			s = s + r.toString() + "\n";
	    			count++;
	    		}
	    		
	    	}
	    	
			long stop = System.nanoTime();
	
	    	this.txtResult.setText(s);
	    	this.txtErrori.setText("The text contains "+count+" errors");
	    	this.txtErrori.setVisible(true);
	    	this.txtTempo.setText("Spell check completed in "+(stop-start)*(Math.pow(10, -9)) +" seconds");
	    	this.txtTempo.setVisible(true);
	    	this.btnClearText.setDisable(false);
	    	
	    	//Ricerca lineare
	    	
	    	List<RichWord> rwL = model.spellCheckTextLinear(words);

		
		}
    }
    
    public void setModel(Dictionary model) {
    	this.model = model;
    	this.boxLanguage.getItems().addAll("English", "Italiano");
    	
    }

    @FXML
    void initialize() {
        assert boxLanguage != null : "fx:id=\"boxLanguage\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtInput != null : "fx:id=\"txtInput\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtErrori != null : "fx:id=\"txtErrori\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtTempo != null : "fx:id=\"txtTempo\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnSpellCheck != null : "fx:id=\"btnSpellCheck\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnClearText != null : "fx:id=\"btnClearText\" was not injected: check your FXML file 'Scene.fxml'.";
    }
}
