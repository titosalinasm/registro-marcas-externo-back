package pe.gob.indecopi.bean;

import java.io.Serializable;
import java.math.BigDecimal;

public class ClsCostoClaseBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6482867199822865577L;
	
	private Integer nuCodInterno;
	private BigDecimal nuCostoArancel;
	
	public Integer getNuCodInterno() {
		return nuCodInterno;
	}
	public void setNuCodInterno(Integer nuCodInterno) {
		this.nuCodInterno = nuCodInterno;
	}
	public BigDecimal getNuCostoArancel() {
		return nuCostoArancel;
	}
	public void setNuCostoArancel(BigDecimal nuCostoArancel) {
		this.nuCostoArancel = nuCostoArancel;
	}

	
	
	
}
