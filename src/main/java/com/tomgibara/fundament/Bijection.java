package com.tomgibara.fundament;

import java.util.function.Function;

/**
 * A function that can is bijective, ie. both injective and surjective.
 *
 * @author Tom Gibara
 *
 * @param <T>
 *            the type of the input to the function
 * @param <R>
 *            the type of the result of the function
 */

public interface Bijection<T, R> extends Function<T, R> {

	/**
	 * Inverts the function defined by {@link #apply(Object)}.
	 *
	 * @param r
	 *            a value in the range of the function.
	 * @return the value in the domain that maps to the <code>r</code>
	 */

	T disapply(R r);

	default Bijection<R, T> inverse() {
		return new Bijection<R, T>() {
			@Override public T apply(R t)    { return Bijection.this.disapply(t); }
			@Override public R disapply(T t) { return Bijection.this.apply(t);    }
		};
	}

}
