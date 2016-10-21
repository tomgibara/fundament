/*
 * Copyright 2016 Tom Gibara
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0

 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
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
