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

import static java.lang.Integer.parseInt;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class MappingTest {

	@Test
	public void testComposite() {
		Mapping<String, Integer> f = Mapping.fromFunction(String.class, Integer.class, s -> parseInt(s));
		Mapping<Integer, Integer> g = Mapping.fromFunction(Integer.class, Integer.class, i -> 2 * i + 1);
		Mapping<String, Integer> h = g.compose(f);

		assertEquals(String.class, h.domainType());
		assertEquals(Integer.class, h.rangeType());
		assertEquals(5, h.apply("2").intValue());
	}

	@Test
	public void testFromUnaryOperator() {
		Mapping<Integer, Integer> m = Mapping.fromUnaryOperator(Integer.class, x -> 2 * x);
		assertEquals(Integer.class, m.domainType());
		assertEquals(Integer.class, m.rangeType());
		assertEquals(4, m.apply(2).intValue());
	}
}
