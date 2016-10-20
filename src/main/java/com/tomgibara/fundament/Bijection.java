package com.tomgibara.fundament;

import java.util.function.Function;
import java.util.function.UnaryOperator;

/**
 * A mapping that is bijective, ie. both injective and surjective.
 *
 * @author Tom Gibara
 *
 * @param <T>
 *            the type of the input to the function
 * @param <R>
 *            the type of the result of the function
 * @see Mapping
 */

public interface Bijection<T, R> extends Mapping<T, R> {

	/**
	 * The identity bijection on a specified domain. Applying the bijection to
	 * any value will return the same value. The domain is assumed not to
	 * contain null.
	 *
	 * @param domainType
	 *            the domain of the bijection
	 * @return an identity bijection over the specified domain
	 */

	public static <T> Bijection<T, T> identity(Class<T> domainType) {
		if (domainType == null) throw new IllegalArgumentException("null domainType");
		return new Bijection<T, T>() {
			@Override public Class<T> domainType()     { return domainType; }
			@Override public Class<T> rangeType()      { return domainType; }
			@Override public T apply(T t)              { return t;          }
			@Override public T disapply(T t)           { return t;          }
			@Override public Bijection<T, T> inverse() { return this;       }

			@Override public <S> Bijection<S, T> compose(Bijection<S, T> before) { return before; }
			@Override public <S> Mapping<S, T> compose(Mapping<S, T> before)     { return before; }
		};
	}

	/**
	 * Generates a bijection from a pair of functions.
	 *
	 * @param domainType
	 *            the type returned by {@link #disapply(Object)}
	 * @param rangeType
	 *            the type returned by {@link #apply(Object)}
	 * @param fn
	 *            the function that maps from the domain to the range
	 * @param inv
	 *            the function that maps from the range to the domain
	 * @return the function pair as a bijection
	 */

	static <T,R> Bijection<T,R> fromFunctions(Class<T> domainType, Class<R> rangeType, Function<T, R> fn, Function<R, T> inv) {
		if (domainType == null) throw new IllegalArgumentException("null domainType");
		if (rangeType == null) throw new IllegalArgumentException("null rangeType");
		if (fn == null) throw new IllegalArgumentException("null fn");
		if (inv == null) throw new IllegalArgumentException("null inv");
		return new Bijection<T,R>() {
			@Override public R apply(T t)          { return fn.apply(t);  }
			@Override public T disapply(R r)       { return inv.apply(r); }
			@Override public Class<T> domainType() { return domainType;   }
			@Override public Class<R> rangeType()  { return rangeType;    }
		};
	}

	/**
	 * Generates a bijection from a pair of unary operators.
	 *
	 * @param domainType
	 *            the domain of the operators
	 * @param op
	 *            provides the implementation of {@link #apply(Object)}
	 * @param inv
	 *            provides the implementation of {@link #disapply(Object)}
	 * @return the function pair as a bijection
	 */

	static <T> Bijection<T,T> fromUnaryOperators(Class<T> domainType, UnaryOperator<T> fn, UnaryOperator<T> inv) {
		if (domainType == null) throw new IllegalArgumentException("null domainType");
		if (fn == null) throw new IllegalArgumentException("null fn");
		if (inv == null) throw new IllegalArgumentException("null inv");
		return new Bijection<T,T>() {
			@Override public T apply(T t)          { return fn.apply(t);  }
			@Override public T disapply(T r)       { return inv.apply(r); }
			@Override public Class<T> domainType() { return domainType;   }
			@Override public Class<T> rangeType()  { return domainType;   }
		};
	}

	/**
	 * Generates a bijection from a mapping using the supplied inverse function
	 *
	 * @param mapping
	 *            a mapping
	 * @param inv
	 *            a function that inverts {@link #apply(Object)}
	 * @return the mapping as a bijection
	 */

	static <T,R> Bijection<T,R> fromMapping(Mapping<T,R> mapping, Function<R,T> inv) {
		if (mapping == null) throw new IllegalArgumentException("null mapping");
		if (inv == null) throw new IllegalArgumentException("null inv");
		return new Bijection<T,R>() {
			@Override public R apply(T t)                   { return mapping.apply(t);        }
			@Override public T disapply(R r)                { return inv.apply(r);            }
			@Override public Class<T> domainType()          { return mapping.domainType();    }
			@Override public Class<R> rangeType()           { return mapping.rangeType();     }
			@Override public boolean isInDomain(Object obj) { return mapping.isInDomain(obj); }
		};
	}

	/**
	 * Evaluates whether the supplied object is a valid parameter for the
	 * {@link #disapply(Object)} method.
	 *
	 * @param obj
	 *            the value being tested, may be null
	 * @return true if the inverse function may be applied to the value
	 */

	default boolean isInRange(Object obj) { return rangeType().isInstance(obj); }

	/**
	 * Inverts the function defined by {@link #apply(Object)}.
	 *
	 * @param r
	 *            a value in the range of the function.
	 * @return the value in the domain that maps to the <code>r</code>
	 */

	T disapply(R r);

	/**
	 * Composes two bijections into a single bijection.
	 *
	 * @param before
	 *            the mapping to be applied first
	 * @return the composite mapping
	 * @throws IllegalArgumentException
	 *             if the range-type of the before bijection does not equal the
	 *             domain-type of this bijection
	 */

	public default <S> Bijection<S, R> compose(Bijection<S, T> before) {
		if (before == null) throw new IllegalArgumentException("null before");
		if (before.rangeType() != this.domainType()) throw new IllegalArgumentException("mismatched domain and range");
		return new Bijection<S, R>() {
			@Override public R apply(S s)                   { return Bijection.this.apply(before.apply(s));              }
			@Override public S disapply(R r)                { return before.disapply(Bijection.this.disapply(r));        }
			@Override public Bijection<R, S> inverse()      { return before.inverse().compose(Bijection.this.inverse()); }
			@Override public Class<S> domainType()          { return before.domainType();                                }
			@Override public Class<R> rangeType()           { return Bijection.this.rangeType();                         }
			@Override public boolean isInDomain(Object obj) { return before.isInDomain(obj);                             }
			@Override public boolean isInRange(Object obj)  { return Bijection.this.isInRange(obj);                      }
		};
	}

	/**
	 * A bijection that inverts this bijection. The composite of a bijection
	 * with its inverse should be equivalent to the identity bijection on its
	 * domain.
	 *
	 * @return a bijective inverse
	 */

	default Bijection<R, T> inverse() {
		return new Bijection<R, T>() {
			@Override public T apply(R t)                   { return Bijection.this.disapply(t);     }
			@Override public R disapply(T t)                { return Bijection.this.apply(t);        }
			@Override public Bijection<T,R> inverse()       { return Bijection.this;                 }
			@Override public Class<R> domainType()          { return Bijection.this.rangeType();     }
			@Override public Class<T> rangeType()           { return Bijection.this.domainType();    }
			@Override public boolean isInDomain(Object obj) { return Bijection.this.isInRange(obj);  }
			@Override public boolean isInRange(Object obj)  { return Bijection.this.isInDomain(obj); }
		};
	}

}
