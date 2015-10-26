package com.tomgibara.fundament;

import java.util.Properties;

import org.junit.Assert;
import org.junit.Test;

public class ProducerExamples {

	private static Properties newProperties() {
		return new Properties();
	}

	private static class ExampleProducer implements Producer<Properties> {

		@Override
		public Properties produce() { return new Properties(); }

	}

	@Test
	public void examples() {
		Producer<Properties> p1 = Properties::new;
		Producer<Properties> p2 = () -> new Properties();
		Producer<Properties> p3 = ProducerExamples::newProperties;
		Producer<Properties> p4 = new ExampleProducer();
		Producer<Properties> p5 = new Producer<Properties>() {
			@Override public Properties produce() { return new Properties(); }
		};

		Assert.assertNotNull(p1.produce());
		Assert.assertNotNull(p2.produce());
		Assert.assertNotNull(p3.produce());
		Assert.assertNotNull(p4.produce());
		Assert.assertNotNull(p5.produce());
	}
}
