package com.tomgibara.fundament;

/**
 * Exposes a method that allows the elements of an indexed collection to be
 * swapped in-situ.
 *
 * @author Tom Gibara
 *
 */

public interface Transposable {

	/**
	 * Instructs the object to swap the elements at the designated positions.
	 * In the case that <code>i == j</code> then no change should occur.
	 *
	 * @param i the index of an item to be swapped
	 * @param j the index of an item to be swapped
	 */

	void transpose(int i, int j);

}
