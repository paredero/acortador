package com.fjgarcia.acortador.business;

import static org.junit.Assert.assertEquals;

import javax.persistence.EntityManager;

import org.junit.Before;
import org.junit.Test;
import org.mockito.AdditionalAnswers;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.fjgarcia.acortador.entity.Url;

public class UrlManagerTest {
	@Mock
	EntityManager em;
	
	
	@Mock
	IEncoder encoder;
	
	@InjectMocks
	UrlManager urlManager;


	private Long decodedId;
	private String encodedId;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		Base62Encoder enc = new Base62Encoder();
		decodedId = 1L;
		encodedId = enc.encode(decodedId);
		Mockito.when(encoder.decode(encodedId)).thenReturn(enc.decode(encodedId));
		Mockito.when(encoder.encode(decodedId)).thenReturn(enc.encode(decodedId));
		Url url = new Url("http://www.google.es");
		url.setId(decodedId);
		Mockito.when(em.find(Url.class, decodedId)).thenReturn(url);
		Mockito.when(em.merge(Matchers.any(Url.class))).then(AdditionalAnswers.returnsFirstArg());
	}

	@Test
	public void testFindByNombreCorto() {
		String nombreCorto = encodedId;
		Url entity = urlManager.findByNombreCorto(nombreCorto);
		Url expectedEntity = new Url("http://www.google.es");
		expectedEntity.setId(decodedId);
		assertEquals(expectedEntity.getId(), entity.getId());
		assertEquals(expectedEntity.getUrl(), entity.getUrl());
	}

	@Test
	public void testSave() {
		String urlToSave = "www.google.es";
		Url entity = urlManager.save(urlToSave);
		Url expectedEntity = new Url("http://www.google.es");
		expectedEntity.setId(decodedId);
		assertEquals(expectedEntity.getUrl(), entity.getUrl());
	}
	
	@Test
	public void testGetNombreCorto() {
		Url urlToGet = new Url("www.google.es");
		urlToGet.setId(decodedId);
		String nombreCorto = urlManager.getNombreCorto(urlToGet);
		String expectedNombreCorto = encodedId;
		assertEquals(nombreCorto, expectedNombreCorto);
	}
	

	public String getNombreCorto(Url url) {
		Long id = url.getId();
		return encoder.encode(id);
	}

}
