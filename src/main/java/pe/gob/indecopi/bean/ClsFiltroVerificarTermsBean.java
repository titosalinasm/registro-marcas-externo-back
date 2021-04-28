package pe.gob.indecopi.bean;

import java.io.Serializable;

public class ClsFiltroVerificarTermsBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String vcUsuario;  
	private Integer nuFlagAceptar;
	private String vcIpUsuario;
	private Integer nuIdProcedimiento;
	private Integer nuIdUsuarioSel;
	
	public ClsFiltroVerificarTermsBean() {
		// TODO Auto-generated constructor stub
	}

	public String getVcUsuario() {
		return vcUsuario;
	}

	public void setVcUsuario(String vcUsuario) {
		this.vcUsuario = vcUsuario;
	}


	public Integer getNuFlagAceptar() {
		return nuFlagAceptar;
	}

	public void setNuFlagAceptar(Integer nuFlagAceptar) {
		this.nuFlagAceptar = nuFlagAceptar;
	}

	public String getVcIpUsuario() {
		return vcIpUsuario;
	}

	public void setVcIpUsuario(String vcIpUsuario) {
		this.vcIpUsuario = vcIpUsuario;
	}

	public Integer getNuIdProcedimiento() {
		return nuIdProcedimiento;
	}

	public void setNuIdProcedimiento(Integer nuIdProcedimiento) {
		this.nuIdProcedimiento = nuIdProcedimiento;
	}

	public Integer getNuIdUsuarioSel() {
		return nuIdUsuarioSel;
	}

	public void setNuIdUsuarioSel(Integer nuIdUsuarioSel) {
		this.nuIdUsuarioSel = nuIdUsuarioSel;
	}
	
	

}
