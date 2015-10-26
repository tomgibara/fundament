package com.tomgibara.fundament;

/**
 * A basic functional interface for consuming values.
 * 
 * @author Tom Gibara
 *
 * @param <T>
 */

@FunctionalInterface
public interface Consumer<T> {

	/**
	 * Consumes the supplied value. Implementations that fail to accept a
	 * supplied value (including null) should throw an
	 * <code>IllegalArgumentException</code>.
	 * 
	 * @param value
	 *            the value to be consumed.
	 */
	void consume(T value);

}
