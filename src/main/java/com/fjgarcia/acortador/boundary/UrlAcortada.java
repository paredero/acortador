package com.fjgarcia.acortador.boundary;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class UrlAcortada {
private String urlAcortada;

public UrlAcortada(String urlAcortada) {
	super();
	this.urlAcortada = urlAcortada;
}

public UrlAcortada() {
}

public String getUrlAcortada() {
	return this.urlAcortada;
}

public void setUrlAcortada(String urlAcortada) {
	this.urlAcortada = urlAcortada;
}

}
