package pe.gob.indecopi.result;

import java.io.Serializable;

import pe.gob.indecopi.util.ClsErrorResult;

public class ClsDescargarResult extends ClsErrorResult implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4750071539293736339L;
	private String  vcResponseType;

	public ClsDescargarResult() {
		// TODO Auto-generated constructor stub
	}
	
	public String getVcResponseType() {
		return vcResponseType;
	}

	public void setVcResponseType(String vcResponseType) {
		this.vcResponseType = vcResponseType;
	}

}
