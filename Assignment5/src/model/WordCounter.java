package model;

public class WordCounter implements HashInterface<HashElement>{
	
	
	// HashTable array containing HashElement
	private HashElement[] hashTable;
	
	// Size of Table
	private int size;
	
	// Current Size of table
	private int currentSize = 0;
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
		int count = 0;
		for (int i = 0; i < hashTable.length; i++) {
			if (hashTable[i] != null) {
				count++;
			}
			else {
				count += 0;
			}
		}
		return count;
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
		
		while (true) {
			if (hashTable[keyHashCode] == null) {
				hashTable[keyHashCode] = key;
				++currentSize;
				break;
			}
			else if (hashTable[keyHashCode] != null)  {
				if (key.getWord().equals(hashTable[keyHashCode].getWord())) {
					hashTable[keyHashCode].increaseCount();
					break;
				}
				else {
					if (currentSize < size) {
						probe(keyHashCode, key);
						break;						
					}
					else  {
						System.out.println("Unique words exceed table size.");
						System.exit(0);
					}
				}
			}
		}
	}
	
	/**
	 * Using quadratic probing this method returns the hash value of the element or -1 if the table is full
	 * @param keyHashCode hash value to be probed 
	 * @return updated hashCode or -1 if table is full
	 */
	private void probe(int keyHashCode , HashElement key) {
		int newHashCode = 0;
		int t = 0;
		while (true) {
			if (newHashCode >= size) {
				++t;
				newHashCode = (int) (keyHashCode + Math.pow(t, 2)) % size;
			}
			if (hashTable[newHashCode] == null) {
				hashTable[newHashCode] = key;
				++currentSize;
				break;
			}
			else if (hashTable[newHashCode] != null) {
				if (key.getWord().equals(hashTable[newHashCode].getWord())) {
					hashTable[newHashCode].increaseCount();
					break;
				}
				else {
					if (currentSize < size) {
						++t;
						newHashCode = (int) (keyHashCode + Math.pow(t, 2)) % size;
					}
					else {
						System.out.println("table is full");
						System.exit(0);
					}
				}
			}
		}
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

