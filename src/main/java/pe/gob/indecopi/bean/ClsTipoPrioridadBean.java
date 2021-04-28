package pe.gob.indecopi.bean;

import java.io.Serializable;

public class ClsTipoPrioridadBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 425575972674516655L;
	
	private Integer nuIdPrioridad;
	private String vcDescripcion;
	
	
	public Integer getNuIdPrioridad() {
		return nuIdPrioridad;
	}
	public void setNuIdPrioridad(Integer nuIdPrioridad) {
		this.nuIdPrioridad = nuIdPrioridad;
	}
	public String getVcDescripcion() {
		return vcDescripcion;
	}
	public void setVcDescripcion(String vcDescripcion) {
		this.vcDescripcion = vcDescripcion;
	}
	

}
