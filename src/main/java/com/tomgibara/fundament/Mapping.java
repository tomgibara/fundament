package com.tomgibara.fundament;

import java.util.function.Function;
import java.util.function.UnaryOperator;

/**
 * Enriches the java <code>Function</code> type with information about the
 * functions domain and range.
 *
 * @author Tom Gibara
 *
 * @param <T>
 *            the type of the input to the function
 * @param <R>
 *            the type of the result of the function
 * @see Bijection
 */

public interface Mapping<T, R> extends Function<T, R> {

	/**
	 * Defines a mapping from a domain to itself with a unary operator.
	 *
	 * @param domainType
	 *            the domain of the operator
	 * @param op
	 *            a unary operator
	 * @return a mapping based on the unary operator
	 */

	static <T> Mapping<T,T> fromUnaryOperator(Class<T> domainType, UnaryOperator<T> op) {
		if (domainType == null) throw new IllegalArgumentException("null domainType");
		if (op == null) throw new IllegalArgumentException("null op");
		return new Mapping<T, T>() {
			@Override public T apply(T t)          { return op.apply(t); }
			@Override public Class<T> domainType() { return domainType;  }
			@Override public Class<T> rangeType()  { return domainType;  }
		};
	}

	/**
	 * Creates a mapping from a function. The returned mapping will use the
	 * default implementation of {@link #isInDomain(Object)}.
	 *
	 * @param domainType
	 *            the type of the function variable
	 * @param rangeType
	 *            the type of the function return
	 * @param fn
	 *            a function over the specified types
	 * @return a mapping from the domain to the range defined by the supplied
	 *         function
	 */

	static <T,R> Mapping<T, R> fromFunction(Class<T> domainType, Class<R> rangeType, Function<T, R> fn) {
		if (domainType == null) throw new IllegalArgumentException("null domainType");
		if (rangeType == null) throw new IllegalArgumentException("null rangeType");
		if (fn == null) throw new IllegalArgumentException("null fn");
		return new Mapping<T, R>() {
			@Override public R apply(T t)          { return fn.apply(t); }
			@Override public Class<T> domainType() { return domainType;  }
			@Override public Class<R> rangeType()  { return rangeType;   }
		};
	}

	/**
	 * The type of object passed into the {@link #apply(Object)} method.
	 *
	 * @return the type of the function variable, never null
	 */

	Class<T> domainType();

	/**
	 * The type of object returned by the {@link #apply(Object)} method.
	 *
	 * @return the type of the function result, never null
	 */

	Class<R> rangeType();

	/**
	 * Evaluates whether the supplied object is a valid parameter for the
	 * {@link #apply(Object)} method.
	 *
	 * @param obj
	 *            the value being tested, may be null
	 * @return true if the function may be applied to the value
	 */

	default boolean isInDomain(Object obj) { return domainType().isInstance(obj); }

	/**
	 * Composes two mappings into a single mapping, first applying the 'before'
	 * mapping, and then this mapping.
	 *
	 * @param before
	 *            the mapping to be applied first
	 * @return the composite mapping
	 * @throws IllegalArgumentException
	 *             if the range type of the before mapping cannot be assigned to
	 *             the domain type of this mapping
	 */

	public default <S> Mapping<S, R> compose(Mapping<S, T> before) {
		if (before == null) throw new IllegalArgumentException("null before");
		if (!domainType().isAssignableFrom(before.rangeType())) throw new IllegalArgumentException("mismatched domain and range");
		return new Mapping<S, R>() {
			@Override public R apply(S s)                   { return Mapping.this.apply(before.apply(s)); }
			@Override public Class<S> domainType()          { return before.domainType();                 }
			@Override public Class<R> rangeType()           { return Mapping.this.rangeType();            }
			@Override public boolean isInDomain(Object obj) { return before.isInDomain(obj);              }
		};
	}
}
