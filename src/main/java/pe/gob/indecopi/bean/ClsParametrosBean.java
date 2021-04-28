package pe.gob.indecopi.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ClsParametrosBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8158896004177930213L;
	
	private List<ClsArancelesBean> lstAranceles;
	private BigDecimal  nuCostoPClase;
	private BigDecimal  nuCostoSClase;
	private Integer nuCantClases;
	
	private String vcRutaGeneral;
	private String vcRutaTemporal;
	private String vcRutaFinal;
	
	public String getVcRutaGeneral() {
		return vcRutaGeneral;
	}

	public void setVcRutaGeneral(String vcRutaGeneral) {
		this.vcRutaGeneral = vcRutaGeneral;
	}

	public String getVcRutaTemporal() {
		return vcRutaTemporal;
	}

	public void setVcRutaTemporal(String vcRutaTemporal) {
		this.vcRutaTemporal = vcRutaTemporal;
	}

	public String getVcRutaFinal() {
		return vcRutaFinal;
	}

	public void setVcRutaFinal(String vcRutaFinal) {
		this.vcRutaFinal = vcRutaFinal;
	}

	public ClsParametrosBean() {
		this.setLstAranceles(new ArrayList<ClsArancelesBean>());
	}

	public List<ClsArancelesBean> getLstAranceles() {
		return lstAranceles;
	}

	public void setLstAranceles(List<ClsArancelesBean> lstAranceles) {
		this.lstAranceles = lstAranceles;
	}



	public BigDecimal getNuCostoSClase() {
		return nuCostoSClase;
	}

	public void setNuCostoSClase(BigDecimal nuCostoSClase) {
		this.nuCostoSClase = nuCostoSClase;
	}

	public Integer getNuCantClases() {
		return nuCantClases;
	}

	public void setNuCantClases(Integer nuCantClases) {
		this.nuCantClases = nuCantClases;
	}

	public BigDecimal getNuCostoPClase() {
		return nuCostoPClase;
	}

	public void setNuCostoPClase(BigDecimal nuCostoPClase) {
		this.nuCostoPClase = nuCostoPClase;
	}
	
	

}
