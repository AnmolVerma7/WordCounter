package model;

public interface HashInterface<T> {
	
	int getHashCode(T key);
	
	void put(T key);
	
	T remove(T key);
	
	void reset();
	
	void printTable();
}
