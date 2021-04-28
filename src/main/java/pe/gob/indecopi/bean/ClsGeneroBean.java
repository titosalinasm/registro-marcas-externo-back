package pe.gob.indecopi.bean;

import java.io.Serializable;

public class ClsGeneroBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8949584194740632976L;
	private Integer nuIdGenero;
	private String vcDescripcion;
	
	public Integer getNuIdGenero() {
		return nuIdGenero;
	}
	public void setNuIdGenero(Integer nuIdGenero) {
		this.nuIdGenero = nuIdGenero;
	}
	public String getVcDescripcion() {
		return vcDescripcion;
	}
	public void setVcDescripcion(String vcDescripcion) {
		this.vcDescripcion = vcDescripcion;
	}
	

}
