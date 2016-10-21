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
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Assert;
import org.junit.Test;

public class BijectionTest {

	final Bijection<String, Integer> f = new Bijection<String, Integer>() {
		@Override public Class<Integer> rangeType() { return Integer.class; }
		@Override public Class<String> domainType() { return String.class;  }
		@Override public Integer apply(String t)    { return parseInt(t);   }
		@Override public String disapply(Integer r) { return r.toString();  }
		@Override public boolean isInDomain(Object obj) {
			if (!(obj instanceof String)) return false;
			String str = (String) obj;
			return str.matches("0|(:?[1-9][0-9]*)");
		}
		@Override
		public boolean isInRange(Object obj) {
			if (!(obj instanceof Integer)) return false;
			int i = (Integer) obj;
			return i >= 0;
		}
	};

	final Bijection<Integer, Integer> g = new Bijection<Integer, Integer>() {
		@Override public Class<Integer> rangeType()  { return Integer.class; }
		@Override public Class<Integer> domainType() { return Integer.class; }
		@Override public Integer apply(Integer i)    { return i * 2 + 1;     }
		@Override public Integer disapply(Integer i) { return (i - 1) / 2;   }
		@Override public boolean isInDomain(Object obj) {
			return (obj instanceof Integer);
		}
		@Override
		public boolean isInRange(Object obj) {
			if (!(obj instanceof Integer)) return false;
			int i = (Integer) obj;
			return (i & 1) == 1;
		}
	};

	@Test
	public void testComposite() {
		// test composite
		Bijection<String, Integer> h = g.compose(f);

		// test two-way mapping
		assertEquals(3, h.apply("1").intValue());
		assertEquals("1", h.disapply(3));
		// test types
		Assert.assertEquals(String.class, h.domainType());
		Assert.assertEquals(Integer.class, h.rangeType());
		// test range and domain
		assertTrue(h.isInDomain("40"));
		assertTrue(h.isInDomain("0"));
		assertFalse(h.isInDomain("-5"));
		assertFalse(h.isInDomain(null));
		assertFalse(h.isInDomain(new Object()));
		assertTrue(h.isInRange(1));
		assertTrue(h.isInRange(-1)); // even though not actually attainable in composite
		assertFalse(h.isInRange(new Object()));
		assertFalse(h.isInRange(2));

		// test inverse
		Bijection<Integer, String> i = h.inverse();

		// test two-way mapping
		assertEquals("1", i.apply(3));
		assertEquals(3, i.disapply("1").intValue());
		// test types
		Assert.assertEquals(Integer.class, i.domainType());
		Assert.assertEquals(String.class, i.rangeType());
		// test range and domain
		assertTrue(i.isInRange("40"));
		assertTrue(i.isInRange("0"));
		assertFalse(i.isInRange("-5"));
		assertFalse(i.isInRange(null));
		assertFalse(i.isInRange(new Object()));
		assertTrue(i.isInDomain(1));
		assertTrue(i.isInDomain(-1)); // even though not actually attainable in composite
		assertFalse(i.isInDomain(new Object()));
		assertFalse(i.isInDomain(2));

	}

	@Test
	public void testIdentity() {
		Bijection<String, String> i = Bijection.identity(String.class);
		assertEquals("x", i.apply("x"));
		assertEquals("x", i.disapply("x"));
		Bijection<Integer, String> c = i.compose(f.inverse());
		assertEquals(f.inverse().apply(3), c.apply(3));
	}
}
