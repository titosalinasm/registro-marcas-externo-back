package pe.gob.indecopi.bean;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ClsPagoBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3017578041233282114L;
	
	private String vcCodArancel;
	private Integer nuIdTipoPago;
	private String vcCodigoBanco;
	private String vcNroOperacion;
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd", timezone="PST")
	private Date dtFechaOperacion;
	
	public String getVcCodArancel() {
		return vcCodArancel;
	}
	public void setVcCodArancel(String vcCodArancel) {
		this.vcCodArancel = vcCodArancel;
	}
	public Integer getNuIdTipoPago() {
		return nuIdTipoPago;
	}
	public void setNuIdTipoPago(Integer nuIdTipoPago) {
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
	public Date getDtFechaOperacion() {
		return dtFechaOperacion;
	}
	public void setDtFechaOperacion(Date dtFechaOperacion) {
		this.dtFechaOperacion = dtFechaOperacion;
	}
	
	
	

}
