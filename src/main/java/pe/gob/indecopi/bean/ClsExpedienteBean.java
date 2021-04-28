package pe.gob.indecopi.bean;

import java.io.Serializable;

public class ClsExpedienteBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4541850913296090710L;
	
	private Integer nuIdSolicitud;
	private Integer nuNroExpediente;
	private Integer nuAnioExpediente;
	private String vcAreaExpediente;
	
	public Integer getNuIdSolicitud() {
		return nuIdSolicitud;
	}
	public void setNuIdSolicitud(Integer nuIdSolicitud) {
		this.nuIdSolicitud = nuIdSolicitud;
	}
	public Integer getNuNroExpediente() {
		return nuNroExpediente;
	}
	public void setNuNroExpediente(Integer nuNroExpediente) {
		this.nuNroExpediente = nuNroExpediente;
	}
	public Integer getNuAnioExpediente() {
		return nuAnioExpediente;
	}
	public void setNuAnioExpediente(Integer nuAnioExpediente) {
		this.nuAnioExpediente = nuAnioExpediente;
	}
	public String getVcAreaExpediente() {
		return vcAreaExpediente;
	}
	public void setVcAreaExpediente(String vcAreaExpediente) {
		this.vcAreaExpediente = vcAreaExpediente;
	}
	
	

}
