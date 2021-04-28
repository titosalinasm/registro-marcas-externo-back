package pe.gob.indecopi.bean;

import java.io.Serializable;

public class ClsFilterPagoBean  implements Serializable{
	
	private static final long serialVersionUID = 7686346817340020193L;
	
	private String vcUsuario;
	private ClsFiltroTramitePagoBean objPago;
	
	public ClsFilterPagoBean() {
		// TODO Auto-generated constructor stub
	}

	public String getVcUsuario() {
		return vcUsuario;
	}

	public void setVcUsuario(String vcUsuario) {
		this.vcUsuario = vcUsuario;
	}

	public ClsFiltroTramitePagoBean getObjPago() {
		return objPago;
	}

	public void setObjPago(ClsFiltroTramitePagoBean objPago) {
		this.objPago = objPago;
	}

}
