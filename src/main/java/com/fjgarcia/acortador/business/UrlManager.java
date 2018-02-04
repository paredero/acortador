package com.fjgarcia.acortador.business;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.fjgarcia.acortador.entity.Url;

@Stateless
public class UrlManager {
	@PersistenceContext
	EntityManager em;
	
	
	@Inject
	@Base62
	IEncoder encoder;

	public Url findByNombreCorto(String nombreCorto) {
		Long id = this.encoder.decode(nombreCorto);
		return this.em.find(Url.class, id);
	}

	public Url save(String absoluteUrl) {
		if (!absoluteUrl.startsWith("http")) {
			absoluteUrl = "http://"+absoluteUrl;
		}
		return this.em.merge(new Url(absoluteUrl));
	}

	public String getNombreCorto(Url url) {
		Long id = url.getId();
		return encoder.encode(id);
	}
}
