package it.polito.tdp.spellchecker.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class Dictionary {

	private Set<String> dictionary;
	private List<String> arrayList;
	private List<String> linkedList;
	
	public Dictionary() {
		
		dictionary = new HashSet<>();
		arrayList = new ArrayList<>();
		linkedList = new LinkedList<>();

	}
	
	public void loadDictionary(String language) {
		
		if(language.equals("Italiano")) {
			
			try {
			
				FileReader fr = new FileReader("src/main/resources/Italian.txt");
				BufferedReader br = new BufferedReader(fr);
				
				String word="";
				
				while( (word=br.readLine()) != null ) {
					
					dictionary.add(word);
					arrayList.add(word);
					linkedList.add(word);
					
				}
			
				br.close();
				
			} catch (IOException ioe) {
				
				System.out.println("Errore nella lettura del file");
				
			}
			
		} else if(language.equals("English")) {
			
			try {
				
				FileReader fr = new FileReader("src/main/resources/English.txt");
				BufferedReader br = new BufferedReader(fr);
				
				String word="";
				
				while( (word=br.readLine()) != null ) {
					
					dictionary.add(word);
					arrayList.add(word);
					linkedList.add(word);
					
				}
			
				br.close();
				
			} catch (IOException ioe) {
				
				System.out.println("Errore nella lettura del file");
				
			}
			
			
		}
		
	}
	
	public List<RichWord> spellCheckText (List<String> inputTextList) {
		
		
		List<RichWord> richWords = new ArrayList<>();

		RichWord rw = null;

		for(String s : inputTextList) {
			
			if(dictionary.contains(s)) {
				
				rw = new RichWord(s, true);
				
			} else {
				
				rw = new RichWord(s, false);
				
			}
			
			richWords.add(rw);

		}
		
		return richWords;
	}
	
	public List<RichWord> spellCheckTextLinear (List<String> inputTextList) {
		
		//LinkedList
		
		
		List<RichWord> richWords = new LinkedList<>();

		RichWord rw = null;
		
		for(String s : inputTextList) {
			
			boolean found = false;
			
			for(String ss : linkedList) {
				
				if(ss.equals(s)) {
					
					rw = new RichWord(s, true);
					found = true;
					break;

				}
				
			}
			
			if(!found) {
				
				rw = new RichWord(s, false);
				
			}
			
			richWords.add(rw);
			
		}
		
		return richWords;
		
		//ArrayList
		
		/*
		
		List<RichWord> richWordsA = new ArrayList<>();

		RichWord rwA = null;
		
		for(String s : inputTextList) {
			
			boolean found = false;
			
			for(String ss : arrayList) {
				
				if(ss.equals(s)) {
					
					rwA = new RichWord(s, true);
					found = true;
					break;

				}
				
			}
			
			if(!found) {
				
				rwA = new RichWord(s, false);
				
			}
			
			richWordsA.add(rwA);
	
			
		}
		
		
		
		return richWordsA;

		*/
		
		
	}
	
	
	public List<RichWord> spellCheckTextDichotomic (List<String> inputTextList) {
		
		//LinkedList
		
	
		List<RichWord> richWords = new LinkedList<>();

		RichWord rw = null;
		
		String middle = linkedList.get(linkedList.size()/2);
		
		List<String> temp = new LinkedList<String>(linkedList);
		
		for(String s : inputTextList) {
						
			boolean found = false;
			
			while(!found && (temp.size()/2>0)) {
				
				if(s.equals(middle)) {
					
					rw = new RichWord(s, true);
					found = true;
					
				} else if (s.compareTo(middle)<0) {
					
					temp = temp.subList(0, temp.indexOf(middle)-1);
					middle = temp.get(temp.size()/2);
					
				} else if (s.compareTo(middle)>0) {
					
					temp = temp.subList(temp.indexOf(middle)+1, temp.size()-1);
					middle = temp.get(temp.size()/2);
					
				}
			
			}
			
			if(!found) {
				rw = new RichWord(s, false);
			}
			
			richWords.add(rw);
		}
		
		return richWords;

		
		
		
		
		//ArrayList
		
		/*
		
		List<RichWord> richWordsA = new ArrayList<>();

		RichWord rwA = null;
		
		String middleA = linkedList.get(linkedList.size()/2);
		
		List<String> tempA = new LinkedList<String>(linkedList);
		
		for(String s : inputTextList) {
						
			boolean found = false;
			
			while(!found && (tempA.size()/2!=0)) {
				
				if(s.equals(middleA)) {
					
					rwA = new RichWord(s, true);
					found = true;
					
				} else if (s.compareTo(middleA)<0) {
					
					tempA = tempA.subList(0, tempA.indexOf(middleA)-1);
					middleA = tempA.get(tempA.size()/2);
					
				} else if (s.compareTo(middleA)>0) {
					
					tempA = tempA.subList(tempA.indexOf(middleA)+1, tempA.size()-1);
					middleA = tempA.get(tempA.size()/2);
					
				}
			
			}
			
			if(!found) {
				rwA = new RichWord(s, false);
			}
			
			richWordsA.add(rwA);
		}
		
		
		return richWordsA;
		
		*/
		
		
	}
	
}
