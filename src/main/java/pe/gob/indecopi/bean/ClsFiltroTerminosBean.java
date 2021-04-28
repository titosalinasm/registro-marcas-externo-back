package pe.gob.indecopi.bean;

import java.io.Serializable;

public class ClsFiltroTerminosBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String vcUsuario;
	private Integer nuIdProcedimiento;
	private Integer nuIdUsuarioSel;
	
	public ClsFiltroTerminosBean()  {
		// TODO Auto-generated constructor stub
	}
	
	public String getVcUsuario() {
		return vcUsuario;
	}
	public void setVcUsuario(String vcUsuario) {
		this.vcUsuario = vcUsuario;
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
