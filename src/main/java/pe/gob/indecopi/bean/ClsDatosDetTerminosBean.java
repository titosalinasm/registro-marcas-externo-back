package pe.gob.indecopi.bean;

import java.io.Serializable;
import java.math.BigDecimal;

public class ClsDatosDetTerminosBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private BigDecimal nuItem;
	private String vcDescTermino;
	
	public ClsDatosDetTerminosBean() {
		// TODO Auto-generated constructor stub
	}


	public BigDecimal getNuItem() {
		return nuItem;
	}

	public void setNuItem(BigDecimal nuItem) {
		this.nuItem = nuItem;
	}


	public String getVcDescTermino() {
		return vcDescTermino;
	}

	public void setVcDescTermino(String vcDescTermino) {
		this.vcDescTermino = vcDescTermino;
	}
	

}
