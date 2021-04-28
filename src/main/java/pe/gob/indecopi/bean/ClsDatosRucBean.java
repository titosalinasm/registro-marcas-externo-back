package pe.gob.indecopi.bean;

import java.io.Serializable;

public class ClsDatosRucBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer nuCodResultado;
	
	private String vcRazonSocial;
	
	private String vcCIIU;
	
	private String vcUbigeo;
	
	private String vcDireccion;
	
	public String getVcDireccion() {
		return vcDireccion;
	}

	public void setVcDireccion(String vcDireccion) {
		this.vcDireccion = vcDireccion;
	}


	public String getVcUbigeo() {
		return vcUbigeo;
	}

	public void setVcUbigeo(String vcUbigeo) {
		this.vcUbigeo = vcUbigeo;
	}

	public Integer getNuCodResultado() {
		return nuCodResultado;
	}

	public void setNuCodResultado(Integer nuCodResultado) {
		this.nuCodResultado = nuCodResultado;
	}

	public String getVcRazonSocial() {
		return vcRazonSocial;
	}

	public void setVcRazonSocial(String vcRazonSocial) {
		this.vcRazonSocial = vcRazonSocial;
	}

	public String getVcCIIU() {
		return vcCIIU;
	}

	public void setVcCIIU(String vcCIIU) {
		this.vcCIIU = vcCIIU;
	}
	
	

}
