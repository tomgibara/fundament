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
