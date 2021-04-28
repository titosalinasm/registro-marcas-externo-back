package pe.gob.indecopi.util;

import java.io.Serializable;
import java.math.BigDecimal;

public class ClsErrorResult implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -734303127991543550L;
	private Long nuError;
	private String vcError;
	public ClsErrorResult() {
		// TODO Auto-generated constructor stub
	}



	public Long getNuError() {
		return nuError;
	}

	public void setNuError(Long nuError) {
		this.nuError = nuError;
	}

	public String getVcError() {
		return vcError;
	}
	
	public void setVcError(String vcError) {
		this.vcError = vcError;
	}

	
}
