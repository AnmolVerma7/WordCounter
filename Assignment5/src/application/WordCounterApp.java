package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.Scanner;

import model.HashElement;
import model.WordCounter;

public class WordCounterApp {
	
	
	private static int totalWords = 0;
	private HashElement[] hasharray;
	
	public static void main(String[] args) throws FileNotFoundException {
		run();
	}
	
	/**
	 * Entry point to program
	 * @throws FileNotFoundException if file is not found
	 */
	private static void run() throws FileNotFoundException {
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
		wordCounter.tableInfo();
		wordCounter.fileInfo();
		wordCounter.reset();
	}
	
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
				++totalWords;
				
				// Store word in HashElement
				key = new HashElement(word);
				
				// Add word to HashTable
				table.put(key);
			}
		}
		fileReader.close();
	}
	
}

