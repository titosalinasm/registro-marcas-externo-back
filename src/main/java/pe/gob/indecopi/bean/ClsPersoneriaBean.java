package pe.gob.indecopi.bean;

import java.io.Serializable;

public class ClsPersoneriaBean  implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5084307031144364401L;
	private Integer nuIdPersoneria;
	private String vcDescripcion;
	
	public Integer getNuIdPersoneria() {
		return nuIdPersoneria;
	}
	public void setNuIdPersoneria(Integer nuIdPersoneria) {
		this.nuIdPersoneria = nuIdPersoneria;
	}
	public String getVcDescripcion() {
		return vcDescripcion;
	}
	public void setVcDescripcion(String vcDescripcion) {
		this.vcDescripcion = vcDescripcion;
	}
	
	
	
}
