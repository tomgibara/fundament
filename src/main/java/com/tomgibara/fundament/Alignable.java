package com.tomgibara.fundament;

/**
 * Some data stores may enter unaligned states that reduces their performance.
 * This interface allows client code to recover alignment at the possible
 * expense of creating a copy of the data.
 *
 * @author Tom Gibara
 */

public interface Alignable<T> {

	/**
	 * Whether the object's data are aligned.
	 *
	 * @return true if aligned, false otherwise
	 */
	boolean isAligned();

	/**
	 * A copy of the object which is guaranteed to contain aligned data.
	 *
	 * @return an aligned copy
	 */
	T alignedCopy();

	/**
	 * An aligned instance of the object. This may be the object itself, if it
	 * is already aligned, or an aligned copy.
	 *
	 * @return the aligned object or an aligned copy
	 */
	@SuppressWarnings("unchecked")
	default T aligned() {
		return isAligned() ? (T) this : this.alignedCopy();
	}
}
