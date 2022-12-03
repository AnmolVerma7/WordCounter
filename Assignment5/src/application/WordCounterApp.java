package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.Scanner;

import model.HashElement;
import model.WordCounter;

public class WordCounterApp {
	
	private static int totalWords = 0;
	
	/**
	 * Reads word from text file and adds to WordCounter
	 * @param fileName Name of file to be read
	 * @param table Table to add key to
	 * @param sizeOfTable Size
	 * @throws FileNotFoundException
	 */
	private static void readTextFile(String fileName, WordCounter table, int tableSize) throws FileNotFoundException {
		File file = new File(fileName);		
		Scanner fileReader = new Scanner(file);
		
		String word;
		HashElement key;
		
		if (file.exists()) {
			while (fileReader.hasNextLine()) {
				
				// Remove all punctuation and turn to lowercase
				word = fileReader.next().replaceAll("\\p{Punct}", "").toLowerCase();
				++totalWords;
				
				// Store word in HashElement
				key = new HashElement(word);
				
				table.put(key);
			}
		}
		fileReader.close();
	}
	
	private static void printTableInfo(WordCounter table) {
		System.out.println("The most common word in the table is: \"" 
				+ table.getCommonElement().getWord().toUpperCase() 
				+ "\" with a count of: " +  table.getCommonElement().getCount());
		System.out.println("Number of words in table:             " + totalWords);
	}
	
	private static void printFileInfo(WordCounter table) {
		System.out.println("Number of distinct words in table:    " + table.getUniqueWords());
		System.out.println("Number of words in file:              " + totalWords);
	}

	public static void main(String[] args) throws FileNotFoundException {
		Scanner input = new Scanner(System.in);
		String filePath = "res/";
		int tableSize;
		WordCounter wordCounter;
		
		System.out.print("Enter text file in format \"filename.txt\" : ");
		filePath += input.next();
		
		System.out.print("Enter size of table: ");
		tableSize = input.nextInt();
		wordCounter = new WordCounter(tableSize);
		input.close();
		
		readTextFile(filePath, wordCounter, tableSize);
		
		if (wordCounter.getUniqueWords() < tableSize ) {
		
		}
		else {
			System.out.println("Table size is not big enough for the amount of unique words");
			System.exit(0);		
		}
		printTableInfo(wordCounter);
		printFileInfo(wordCounter);	
	}
	
	
	
}

