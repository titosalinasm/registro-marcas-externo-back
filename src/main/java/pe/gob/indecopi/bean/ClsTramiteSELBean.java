package pe.gob.indecopi.bean;

import java.io.Serializable;
import java.sql.Clob;

public class ClsTramiteSELBean  implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private Integer nuIdProcedimiento;
	private String vcNroExpediente;
	private String vcAnioExpediente;
	private String vcSiglaExpediente;
	private Integer nuIdEstadoTramite;
	private String vcCodAreaDestino;
	private String clDetTramite;
	
	public ClsTramiteSELBean() {
		// TODO Auto-generated constructor stub
	}
	

	public Integer getNuIdEstadoTramite() {
		return nuIdEstadoTramite;
	}

	public void setNuIdEstadoTramite(Integer nuIdEstadoTramite) {
		this.nuIdEstadoTramite = nuIdEstadoTramite;
	}


	public String getClDetTramite() {
		return clDetTramite;
	}


	public void setClDetTramite(String clDetTramite) {
		this.clDetTramite = clDetTramite;
	}


	public Integer getNuIdProcedimiento() {
		return nuIdProcedimiento;
	}
	public void setNuIdProcedimiento(Integer nuIdProcedimiento) {
		this.nuIdProcedimiento = nuIdProcedimiento;
	}
	public String getVcNroExpediente() {
		return vcNroExpediente;
	}
	public void setVcNroExpediente(String vcNroExpediente) {
		this.vcNroExpediente = vcNroExpediente;
	}
	public String getVcAnioExpediente() {
		return vcAnioExpediente;
	}
	public void setVcAnioExpediente(String vcAnioExpediente) {
		this.vcAnioExpediente = vcAnioExpediente;
	}
	public String getVcSiglaExpediente() {
		return vcSiglaExpediente;
	}
	public void setVcSiglaExpediente(String vcSiglaExpediente) {
		this.vcSiglaExpediente = vcSiglaExpediente;
	}
	public String getVcCodAreaDestino() {
		return vcCodAreaDestino;
	}
	public void setVcCodAreaDestino(String vcCodAreaDestino) {
		this.vcCodAreaDestino = vcCodAreaDestino;
	}

}
