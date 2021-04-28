package pe.gob.indecopi.result;


import java.io.Serializable;

import pe.gob.indecopi.bean.ClsTramitePagoBean;
import pe.gob.indecopi.util.ClsErrorResult;

public class ClsValidaTramitePagoResult extends ClsErrorResult implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7212564804907343712L;
	private ClsTramitePagoBean objPago;
	public ClsValidaTramitePagoResult() {
		this.setObjPago(new ClsTramitePagoBean());
	}
	public ClsTramitePagoBean getObjPago() {
		return objPago;
	}
	public void setObjPago(ClsTramitePagoBean objPago) {
		this.objPago = objPago;
	}

}
