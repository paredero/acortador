package com.fjgarcia.acortador.business;

public interface IEncoder {
	public String encode(Long id);

	public Long decode(String nombreCorto);
}
