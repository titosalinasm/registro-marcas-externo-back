package pe.gob.indecopi.bean;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ClsFiltroTramitePagoBean  implements Serializable{

	private static final long serialVersionUID = 1L;
	  private String vcCodArancel;
	  private Long nuIdTipoPago;
	  private String vcCodigoBanco;
	  private String vcNroOperacion;
	  @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd", timezone="PST")
	  private Date dtFechaOperacion;
	public ClsFiltroTramitePagoBean() {
		// TODO Auto-generated constructor stub
	}

	public Date getDtFechaOperacion() {
		return dtFechaOperacion;
	}

	public void setDtFechaOperacion(Date dtFechaOperacion) {
		this.dtFechaOperacion = dtFechaOperacion;
	}

	public String getVcCodArancel() {
		return vcCodArancel;
	}

	public void setVcCodArancel(String vcCodArancel) {
		this.vcCodArancel = vcCodArancel;
	}


	public Long getNuIdTipoPago() {
		return nuIdTipoPago;
	}
	public void setNuIdTipoPago(Long nuIdTipoPago) {
		this.nuIdTipoPago = nuIdTipoPago;
	}
	public String getVcCodigoBanco() {
		return vcCodigoBanco;
	}
	public void setVcCodigoBanco(String vcCodigoBanco) {
		this.vcCodigoBanco = vcCodigoBanco;
	}

	public String getVcNroOperacion() {
		return vcNroOperacion;
	}
	public void setVcNroOperacion(String vcNroOperacion) {
		this.vcNroOperacion = vcNroOperacion;
	}
	
	
}
