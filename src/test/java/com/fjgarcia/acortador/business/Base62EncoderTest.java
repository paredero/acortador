package com.fjgarcia.acortador.business;

import static org.junit.Assert.*;

import org.junit.Test;

public class Base62EncoderTest {

	@Test
	public void encodeDecodeTest() {
		Base62Encoder encoder = new Base62Encoder();
		for (long numberToEncode = 1; numberToEncode < 200; numberToEncode++) {
			String encodedNumber = encoder.encode(numberToEncode);
			Long decodedNumber = encoder.decode(encodedNumber);
			assertEquals(new Long(numberToEncode),decodedNumber);
		}

	}
}
