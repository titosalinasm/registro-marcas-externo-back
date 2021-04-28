package pe.gob.indecopi.bean;

import java.io.Serializable;

public class ClsFiltroUbigeoBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5743063776471624987L;
	
	private Integer nuIdTipo;
	private Integer nuIdPadre;
	
	
	public Integer getNuIdTipo() {
		return nuIdTipo;
	}
	public void setNuIdTipo(Integer nuIdTipo) {
		this.nuIdTipo = nuIdTipo;
	}
	public Integer getNuIdPadre() {
		return nuIdPadre;
	}
	public void setNuIdPadre(Integer nuIdPadre) {
		this.nuIdPadre = nuIdPadre;
	}
	
	

}
