package pe.gob.indecopi.bean;

import java.io.Serializable;
import java.util.Map;

public class ClsConfiguracionBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5595607084769316747L;
	
	private String vcCodConfiguracion;
	private Integer nuFlagTipo;
	private String vcDesConfiguracion;
	private Integer nuValor1;
	private Integer nuValor2;
	private Integer nuValor3;
	private String vcValor1;
	private String vcValor2;
	private String vcValor3;
	private Map<?,?> clValor1;
	
	
	public Integer getNuFlagTipo() {
		return nuFlagTipo;
	}
	public void setNuFlagTipo(Integer nuFlagTipo) {
		this.nuFlagTipo = nuFlagTipo;
	}
	public String getVcCodConfiguracion() {
		return vcCodConfiguracion;
	}
	public void setVcCodConfiguracion(String vcCodConfiguracion) {
		this.vcCodConfiguracion = vcCodConfiguracion;
	}
	public String getVcDesConfiguracion() {
		return vcDesConfiguracion;
	}
	public void setVcDesConfiguracion(String vcDesConfiguracion) {
		this.vcDesConfiguracion = vcDesConfiguracion;
	}
	public Integer getNuValor1() {
		return nuValor1;
	}
	public void setNuValor1(Integer nuValor1) {
		this.nuValor1 = nuValor1;
	}
	public Integer getNuValor2() {
		return nuValor2;
	}
	public void setNuValor2(Integer nuValor2) {
		this.nuValor2 = nuValor2;
	}
	public Integer getNuValor3() {
		return nuValor3;
	}
	public void setNuValor3(Integer nuValor3) {
		this.nuValor3 = nuValor3;
	}
	public String getVcValor1() {
		return vcValor1;
	}
	public void setVcValor1(String vcValor1) {
		this.vcValor1 = vcValor1;
	}
	public String getVcValor2() {
		return vcValor2;
	}
	public void setVcValor2(String vcValor2) {
		this.vcValor2 = vcValor2;
	}
	public String getVcValor3() {
		return vcValor3;
	}
	public void setVcValor3(String vcValor3) {
		this.vcValor3 = vcValor3;
	}
	public Map<?, ?> getClValor1() {
		return clValor1;
	}
	public void setClValor1(Map<?, ?> clValor1) {
		this.clValor1 = clValor1;
	}

	
}
