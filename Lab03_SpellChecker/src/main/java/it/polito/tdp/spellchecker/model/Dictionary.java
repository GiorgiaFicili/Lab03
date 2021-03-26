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

	}
	
	public void loadDictionary(String language) {
		
		dictionary = new HashSet<>();
		arrayList = new ArrayList<>();
		linkedList = new LinkedList<>();
		
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

		
		for(String s : inputTextList) {
			
			boolean found = false;

			int min = 0;
			int max = linkedList.size()-1;
			int middle = linkedList.size()/2;
			
			while(min<=max) {
				
				if(s.equals(linkedList.get(middle))) {
					rw = new RichWord(s,true);
					found = true;
					break;
				} else if (s.compareTo(linkedList.get(middle))<0){
					max = middle-1;
					middle = (max+min)/2;
				} else if (s.compareTo(linkedList.get(middle))>0) {
					min = middle+1;
					middle = (max+min)/2;
				}
					
			}
			
			if(!found) {
				rw = new RichWord(s,false);
			}
			
			richWords.add(rw);
			
		}
		
		return richWords;
		
		
	
		
		
		//ArrayList
		
		/*
		
		List<RichWord> richWords = new LinkedList<>();

		RichWord rw = null;

		
		for(String s : inputTextList) {
			
			boolean found = false;

			int min = 0;
			int max = arrayList.size()-1;
			int middle = arrayList.size()/2;
			
			while(min!=max) {
				
				if(s.equals(arrayList.get(middle))) {
					rw = new RichWord(s,true);
					found = true;
					break;
				} else if (s.compareTo(arrayList.get(middle))<0){
					max = middle-1;
					middle = (max+min)/2;
				} else if (s.compareTo(arrayList.get(middle))>0) {
					min = middle+1;
					middle = (max+min)/2;
				}
					
			}
			
			if(!found) {
				rw = new RichWord(s,false);
			}
			
			richWords.add(rw);
			
		}
		
		return richWords;
		
		
		*/
		
		
	}
	
}
