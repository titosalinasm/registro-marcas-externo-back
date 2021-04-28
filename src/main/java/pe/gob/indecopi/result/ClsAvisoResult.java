package pe.gob.indecopi.result;

import java.io.Serializable;

import pe.gob.indecopi.util.ClsErrorResult;

public class ClsAvisoResult extends ClsErrorResult implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6151835231848678672L;

	private Integer nuFlagAviso;
	private String clAviso;

	public Integer getNuFlagAviso() {
		return nuFlagAviso;
	}

	public void setNuFlagAviso(Integer nuFlagAviso) {
		this.nuFlagAviso = nuFlagAviso;
	}

	public String getClAviso() {
		return clAviso;
	}

	public void setClAviso(String clAviso) {
		this.clAviso = clAviso;
	}
	
}
