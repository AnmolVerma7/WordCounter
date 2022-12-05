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
	private static void readTextFile(String fileName, WordCounter table) throws FileNotFoundException {
		File file = new File(fileName);		
		Scanner fileReader = new Scanner(file);
		
		String word;
		HashElement key;
		
		if (file.exists()) {
			while (fileReader.hasNextLine()) {
				
				// Remove all punctuation and turn to lowercase
				word = fileReader.next().replaceAll("\\p{Punct}", "").toLowerCase();
				
				// Store word in HashElement
				key = new HashElement(word);
				++totalWords;
				table.put(key);
			}
		}
		fileReader.close();
	}
	
	private static void printTableInfo(WordCounter table) {
		System.out.println("\nMost common word in the table: \"" 
				+ table.getCommonElement().getWord().toUpperCase() 
				+ "\" Count: " +  table.getCommonElement().getCount() + "\n");
		System.out.println("Number of words in table: " + totalWords + "\n");
	}
	
	private static void printFileInfo(WordCounter table) {
		System.out.println("Number of distinct words in table: " + table.getUniqueWords() + "\n");
		System.out.println("Number of words in file: " + totalWords + "\n");
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
		
		readTextFile(filePath, wordCounter);
		if (tableSize >= wordCounter.getUniqueWords()) {
			printTableInfo(wordCounter);
			printFileInfo(wordCounter);	
		}
		else{
			System.out.println("Table size is not big enough for the amount of unique words");
			System.exit(0);	
			
		}
			
	}
	
	
	
}

