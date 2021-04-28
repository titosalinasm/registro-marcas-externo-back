package pe.gob.indecopi.result;

import java.io.Serializable;

import pe.gob.indecopi.util.ClsErrorResult;

public class ClsValidaResult extends ClsErrorResult implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8737242825033038284L;
	
	private Integer nuValor;

	public Integer getNuValor() {
		return nuValor;
	}

	public void setNuValor(Integer nuValor) {
		this.nuValor = nuValor;
	}
	
	

}
