package it.polito.tdp.spellchecker.model;

public class RichWord {

	private String parolaInput;
	private boolean corretta;
	
	public RichWord(String parolaInput, boolean corretta) {
		this.parolaInput = parolaInput;
		this.corretta = corretta;
	}

	public String getParolaInput() {
		return parolaInput;
	}

	public void setParolaInput(String parolaInput) {
		this.parolaInput = parolaInput;
	}

	public boolean isCorretta() {
		return corretta;
	}

	public void setCorretta(boolean corretta) {
		this.corretta = corretta;
	}

	@Override
	public String toString() {
		return this.parolaInput;
	}
	
	
}
