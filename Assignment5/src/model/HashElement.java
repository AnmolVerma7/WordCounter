package model;

public class HashElement {

	// Key for the hash table
	private String word; 
	
	// Number of times word occurs in file
	private int count;

	/**
	 * Constructor for HashElement that takes in a word and sets count to 1
	 * @param Word to be set as the key
	 */
	public HashElement(String word) {
		this.word = word;
		this.count = 1;
	}
	
	/**
	 * Constructor for HashElement that takes in a word and count
	 * @param Word to be set as key
	 * @param Count to be set on word
	 */
	public HashElement(String word, int count) {
		this.word = word;
		this.count = count;
	}
	
	/**
	 * Gets the word of the element
	 * @return Word of the element
	 */
	public String getWord() {
		return word;
	}
	
	/**
	 * Sets the word of the element
	 * @param word that is going to be the new key of the element
	 */
	public void setWord(String word) {
		this.word = word;
	}
	
	/**
	 * Gets the count of the word
	 * @return Count of the word (times in occurs in file)
	 */
	public int getCount() {
		return count;
	}
	
	/**
	 * Sets the count of the HashElement
	 * @param Count to be set of the element
	 */
	public void setCount(int count) {
		this.count = count;
	}
	
	/**
	 * Increases the count data by 1
	 */
	public void increaseCount() {
		this.count++;
	}
	
	/**
	 * Overrides toString method to return a formatted string containing data
	 */
	public String toString() {
		return word + ":  " + count + "";
	}

}
