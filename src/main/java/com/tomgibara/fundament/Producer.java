package com.tomgibara.fundament;

/**
 * A basic functional interface for producing values. Similar in spirit to a
 * zero argument function; a <code>Function</code> on <code>Void</code> but
 * without the mandatory null argument.
 * 
 * @author Tom Gibara
 *
 * @param <T> the type of value produced
 */

@FunctionalInterface
public interface Producer<T> {

	/**
	 * Produces a value.
	 * 
	 * @return the value produced.
	 */
	T produce();
}
