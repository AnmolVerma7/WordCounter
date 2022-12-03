package model;

public class WordCounter implements HashInterface<HashElement>{
	
	
	// HashTable array containing HashElement
	private HashElement[] hashTable;
	
	// Size of Table
	private int size;
	
	// Number Unique words
	private int uniqueWords = 0;
	
	
	/**
	 * Constructor for Word Counter
	 * @param TableSize given by user
	 */
	public WordCounter(int size) {
		this.size = size;
		hashTable= new HashElement[size];
		reset();
	}
	/**
	 * Returns HashCode depending on the "word" using a HashFunction
	 * @return HashCode of key
	 */
	public int getHashCode(HashElement key) {
		return key.hashFunction() % size;
	}
	
	/**
	 * Gets the amount of unique words in table
	 * @return Integer value of the amount of unique words
	 */
	public int getUniqueWords() {
		return this.uniqueWords;
	}
	
	/**
	 * Gets the most common element in the array
	 * @return Element with the highest count
	 */
	public HashElement getCommonElement() {
		HashElement key = null;
		int maxVal = 0;
		for (HashElement element : hashTable) {
			if (element != null) {
				if (maxVal < element.getCount()) {
					maxVal = element.getCount();
					key = element;
				}
			}
			else {
				continue;
			}
		}
		return key;
	}
	
	/**
	 * Maps key to a hashCode in the hashTable 
	 * If hash is null, key will be added at that hash
	 * If word of key matches the spot that is full, it will increment the count for the word
	 */
	public void put(HashElement key) {
		int keyHashCode = getHashCode(key);
		
		// Map (key to keyhashCode) into table using probing
		
		// if element at keyHashCode is null,  add the key to the spot
		if (hashTable[keyHashCode] == null) {
			hashTable[keyHashCode] = key;
			++this.uniqueWords;
		}
		// else if the element is not null...check if the word of the key matches the word of the table element 
		else if (key.getWord().equals(hashTable[keyHashCode].getWord())) {
			hashTable[keyHashCode].increaseCount();
		}
		else {
			// Get a hashCode returned of the spot that is empty or -1 if full
			int probedHash = probe(keyHashCode);
			
			// if the value is -1 table is full
			if (probedHash == -1) {
				return;
			}
			// else it finds a spot and maps key to found hashCode
			else {
				hashTable[probedHash] = key;
				++this.uniqueWords;
			}
		} 	
	}
	
	/**
	 * Using quadratic probing this method returns the hash value of the element or -1 if the table is full
	 * @param keyHashCode hash value to be probed 
	 * @return updated hashCode or -1 if table is full
	 */
	private int probe(int keyHashCode) {
		int probeHash = keyHashCode;
		for (int a = 0; a < size; a++) {
			int i = a + 1;
			keyHashCode = (probeHash + (i * i)) % size;
			
			if (hashTable[keyHashCode] == null) {
				return keyHashCode;
			}
		}
		return -1;
	}
	
	/**
	 * Removes element from WordCounter table and makes element null
	 * @param Key to be removed
	 * @return Element that has been removed
	 */
	public HashElement remove(HashElement key) {
		HashElement temp = null;
		int hashIndex = getHashCode(key);
		for (int i = hashIndex; i < hashTable.length; i++) {
			if (hashIndex == getHashCode(hashTable[i])) {
				temp = hashTable[i];
				hashTable[i] = null;
				return temp;
			}
			else {
				return temp;
			}
		}
		return temp;
	}
	
	/**
	 * Resets every element in the hashTable with null
	 */
	public void reset() {
		for (int i = 0; i < hashTable.length; i++) {
			hashTable[i] = null;
		}
	}
	
	/**
	 * Prints the table
	 */
	public void printTable() {
		for (int i = 0; i < hashTable.length; i++) {
			if (hashTable[i] != null) {
				System.out.println(hashTable[i]);				
			}
			else continue;
		}
	}

}

