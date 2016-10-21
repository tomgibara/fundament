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
