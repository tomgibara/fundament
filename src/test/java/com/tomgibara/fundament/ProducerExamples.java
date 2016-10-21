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
