package pe.gob.indecopi.result;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import pe.gob.indecopi.bean.ClsDatosDetTerminosBean;
import pe.gob.indecopi.util.ClsErrorResult;

public class ClsVerifTermsResult extends ClsErrorResult implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6720552143800877890L;
	private Integer nuFlagVerificar;
	private List<ClsDatosDetTerminosBean> lstTerminos;

	public ClsVerifTermsResult() {
		this.setLstTerminos(new ArrayList<ClsDatosDetTerminosBean>());
	}

	public Integer getNuFlagVerificar() {
		return nuFlagVerificar;
	}

	public void setNuFlagVerificar(Integer nuFlagVerificar) {
		this.nuFlagVerificar = nuFlagVerificar;
	}

	public List<ClsDatosDetTerminosBean> getLstTerminos() {
		return lstTerminos;
	}

	public void setLstTerminos(List<ClsDatosDetTerminosBean> lstTerminos) {
		this.lstTerminos = lstTerminos;
	}

}
