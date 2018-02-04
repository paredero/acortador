package com.fjgarcia.acortador.boundary;

import com.fjgarcia.acortador.business.UrlManager;
import com.fjgarcia.acortador.entity.Url;
import java.net.URI;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

@Stateless
@Path("/")
public class UrlResource {
	@Inject
    UrlManager manager;

	@Path("{url}")
	@GET
	public Response find(@PathParam("url") String url) {
		Url urlAcortada = manager.findByNombreCorto(url);
		URI uri = URI.create(urlAcortada.getUrl());
		return Response.seeOther(uri).build();
	}
		
	@POST
	@Produces({MediaType.APPLICATION_JSON})
    public Response save(@FormParam("absoluteUrl") String absoluteUrl, @Context UriInfo info) {
		Url saved = this.manager.save(absoluteUrl);
		return Response.ok(new UrlAcortada(info.getAbsolutePathBuilder().path("/" + this.manager.getNombreCorto(saved)).build().toString())).build();
    }	
}
