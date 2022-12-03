package model;

public class WordCounter implements HashInterface<HashElement>{
	
	// Array of HashElements
	private static HashElement[] table;
	
	// Size of table given by user
	private int sizeOfTable;
	
	// Current size of table (contains null elements)
	private int currentSize;
	
	// Number of unique words in table
	private int uniqueWords;
	
	// Total words
	private int totalWords;
	
	/**
	 * Constructor for Word Counter
	 * @param TableSize given by user
	 */
	public WordCounter(int sizeOfTable) {
		this.sizeOfTable = sizeOfTable;
		this.currentSize = 0;
		this.uniqueWords = 0;
		table = new HashElement[sizeOfTable];
	}
	
	/**
	 * Returns HashCode depending on the "word" using a HashFunction
	 * @return HashCode of key
	 */
	public int getHashCode(HashElement key) {
		String word = key.getWord();
		return hashFunction(word) % sizeOfTable;
	}

	/**
	 * Uses the Hash Function to see where the given HashElement will go
	   in the HashTable based on the key which is String word in HashElement
	 */
	public void put(HashElement key) {
		int hashCode = getHashCode(key);
		int i = 1;
		
		while (true) {
			if (table[hashCode] == null) {
				table[hashCode] = key;
				++currentSize;
				++uniqueWords;
				++totalWords;
				break;
			}
			
			else if (key.getWord().equals(table[hashCode].getWord())) {
				table[hashCode].increaseCount();
				++totalWords;
				break;
			}
			
			// Quadratically probes for new index(hashcode)
			if (hashCode == getHashCode(table[hashCode]) || hashCode != getHashCode(table[hashCode])) {
				hashCode = (int) (hashCode + Math.pow(i++, 2)) % sizeOfTable;
			}
			else {
				++uniqueWords;
				++totalWords;
				break;
			}
			
			// If current size equals size of table AND the hashCode matches the hashCode in the table, break
			if (currentSize == sizeOfTable && hashCode == getHashCode(table[hashCode]) ) {
				
				break;
			}
		}
	}
	
	/**
	 * Takes in a word and returns an integer hash value for the word
	 * @param Word that needs a hashValue assigned to it
	 * @return HashValue of word
	 */
	public int hashFunction(String word) {
		int hash = 0;
		int u = 0;
		int a = 0;
		
		for (int i = 0; i < word.length(); i++) {
			u = word.charAt(i);
			a = (int) Math.pow(2, i);
			hash += (u * a);
		}
		
		hash = hash % sizeOfTable;
		return hash;
	}

	/**
	 * Removes element from WordCounter table and makes element null
	 * @param Key to be removed
	 * @return Element that has been removed
	 */
	public HashElement remove(HashElement key) {
		HashElement temp = null;
		int hashIndex = getHashCode(key);
		for (int i = hashIndex; i < table.length; i++) {
			if (hashIndex == getHashCode(table[i])) {
				temp = table[i];
				table[i] = null;
				return temp;
			}
			else {
				return temp;
			}
		}
		return temp;
	}

	/**
	 * Resets every element in table to null
	 */
	public void reset() {
		for (int i = 0; i < table.length; i++) {
			table[i] = null;
		}
	}

	/**
	 * Prints table.
	 * If table size equals or is less than unique word count it will print an error
	 * Otherwise if table is greater than unique word count, method will print formatted elements
	 */
	public void printTable() {
		if (this.sizeOfTable <= uniqueWords) {
			System.out.println("\nTable size should be greater than the amount of unique words. \nExiting...");
		}
		else {
			for (HashElement element: table) {
				if (element != null) {
					System.out.println(element.toString());
				}
				else {					
					continue;
				}
			}
			System.out.println("\nCounting complete!");
		}
	}
	
	/**
	 * Print most common word in table and total number of words in table OR sum of all counts for all words in table
	 */
	public void tableInfo() {
		int maxVal = 0;
		int wordsInTable = 0;
		String word = null;
		
		if (sizeOfTable <= uniqueWords) {
			System.out.println("\nTable size should be greater than the amount of unique words. \nExiting...");
		}
		else {
			for (HashElement element : table) {
				if (element != null) {
					wordsInTable += element.getCount();
					if (maxVal < element.getCount()) {
						maxVal = element.getCount();
						word = element.getWord();
					}
				}
				else {
					continue;
				}
			}
			System.out.println("\nThe most common word is: \"" + word.toUpperCase() +  "\" with a count of: " + maxVal + "\n");
			System.out.println("The total number of words in the table (sum of all occurences) is: " + wordsInTable + "\n");
		}
		
    }

	/**
	 * Prints total of distinct words in file, and total words in input file
	 */
	public void fileInfo() {
		if (this.sizeOfTable <= uniqueWords) {
			return;
		}
		else {
			System.out.println("Number of distinct words in file: " + uniqueWords + "\n");
			System.out.println("Total number of words in input file: " + totalWords + "\n");
		}
	}		
}

