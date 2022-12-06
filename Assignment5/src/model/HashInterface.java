package model;

/**
 * Interface for word counter
 * @author Anmol Verma
 * @param <T> Type of elements the table is going to store
 */
public interface HashInterface<T> {
	
	int getHashCode(T key);
	
	void put(T key);
	
	T remove(T key);
	
	void reset();
	
	void printTable();
}
