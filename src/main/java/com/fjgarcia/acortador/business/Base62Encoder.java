package com.fjgarcia.acortador.business;

import javax.ejb.Stateless;

@Base62
@Stateless
public class Base62Encoder implements IEncoder {
	public static final String ALPHABET = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
	public static final int BASE = ALPHABET.length();

	@Override
	public String encode(Long id) {
		StringBuilder str = new StringBuilder();
		while (id > 0) {
			str.insert(0, ALPHABET.charAt((int) (id % BASE)));
			id = id / BASE;
		}
		return str.toString();
	}

	@Override
	public Long decode(String nombreCorto) {
		Long num = 0L;
		for (int i = 0; i < nombreCorto.length(); i++) {
			num = num * BASE + ALPHABET.indexOf(nombreCorto.charAt(i));
		}
		return num;
	}

}
