package pe.gob.indecopi.service;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Clob;
import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.gob.indecopi.bean.ClsFilterPagoBean;
import pe.gob.indecopi.bean.ClsFiltroProcedimientoBean;
import pe.gob.indecopi.bean.ClsParametrosBean;
import pe.gob.indecopi.repository.ClsParametroRepositoryI;
import pe.gob.indecopi.repository.ClsTramiteSELRepositoryI;
import pe.gob.indecopi.result.ClsAvisoResult;
import pe.gob.indecopi.result.ClsValidaTramitePagoResult;
import pe.gob.indecopi.util.ClsResultDAO;
import pe.gob.indecopi.util.ClsUtil;

@Service
public class ClsTramiteSELServiceImpl implements Serializable, ClsTramiteSELService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1547704684501421556L;

	private  Logger logger = Logger.getLogger(ClsTramiteSELServiceImpl.class);
	
	@Autowired
	private ClsParametroRepositoryI objConnParam;
	
@Autowired
private ClsTramiteSELRepositoryI objConnTramitePagoRepo;

@Autowired
private ClsResultDAO objResultDAO;

public ClsValidaTramitePagoResult doSaveTramitePago(ClsFilterPagoBean objTramitePago) {
	ClsValidaTramitePagoResult objTramitePagoResult=new ClsValidaTramitePagoResult();
	try {
		
		ClsParametrosBean objParam=new ClsParametrosBean();
		objResultDAO=objConnParam.doParametro();
		objParam=(ClsParametrosBean)objResultDAO.get("POUT_PARAMETRO");
		
		System.out.print("ruta archivo: "+objParam.getVcRutaFinal());
		objResultDAO=objConnTramitePagoRepo.doValidarPago(objTramitePago, objParam);
	

	objTramitePagoResult.setNuError(new Long(Integer.parseInt(objResultDAO.get("POUT_NU_ERROR")+"")));
	objTramitePagoResult.setVcError(objResultDAO.get("POUT_VC_ERROR")+"");

	if(objTramitePagoResult.getNuError()==0L) {
		//
		objTramitePagoResult.getObjPago().setVcCodArancel((String)objResultDAO.get("POUT_VC_NRO_ARANCEL"));
		objTramitePagoResult.getObjPago().setVcNroOperacion((String)objResultDAO.get("POUT_VC_NRO_OPERACION"));
		objTramitePagoResult.getObjPago().setVcNroPasarela((String)objResultDAO.get("POUT_VC_NRO_PASARELA"));
		objTramitePagoResult.getObjPago().setNuExisteSIA(objResultDAO.get("POUT_NU_EXISTE_SIA")==null ? null:Integer.parseInt(objResultDAO.get("POUT_NU_EXISTE_SIA")+""));
		objTramitePagoResult.getObjPago().setDtFechaOperacion((Date)objResultDAO.get("POUT_DT_OPERACION"));
		objTramitePagoResult.getObjPago().setVcCodBanco((String)objResultDAO.get("POUT_VC_COD_BANCO"));
		objTramitePagoResult.getObjPago().setVcTipoDocumento((String)objResultDAO.get("POUT_VC_TIPODOCUMENTO"));
		objTramitePagoResult.getObjPago().setNuIdTipoPago(objResultDAO.get("POUT_NU_ID_TIPO_PAGO")==null ? null : Integer.parseInt(objResultDAO.get("POUT_NU_ID_TIPO_PAGO")+""));
		objTramitePagoResult.getObjPago().setNuMonto((BigDecimal)(objResultDAO.get("POUT_NU_MONTO")));
	}else {
		objTramitePagoResult.setObjPago(null);
	}
	
	}catch (Exception e) {
		objTramitePagoResult.setObjPago(null);
		objTramitePagoResult.setNuError(-1L);
		objTramitePagoResult.setVcError("Error: "+e);
		logger.info(""+e.getMessage());
		e.printStackTrace();
	}
	return objTramitePagoResult;
}

public ClsAvisoResult doAviso(ClsFiltroProcedimientoBean objProcedimiento) {
	ClsAvisoResult objAviso=new ClsAvisoResult();
	try {
	logger.info("hola: "+objProcedimiento.getNuIdProcedimiento());
	objResultDAO=objConnTramitePagoRepo.doAviso(objProcedimiento);	
	
	objAviso.setNuFlagAviso(Integer.parseInt(objResultDAO.get("POUT_NU_FLAG")+""));
	objAviso.setClAviso(objResultDAO.get("POUT_CL_AVISO")+"");
	objAviso.setNuError(new Long(Integer.parseInt(objResultDAO.get("POUT_NU_ERROR")+"")));
	objAviso.setVcError(objResultDAO.get("POUT_VC_ERROR")+"");

		}catch (Exception e) {
	    objAviso.setClAviso(null);
		objAviso.setNuError(-1L);
		objAviso.setVcError("Error: "+e);
		e.printStackTrace();
	}
	
	return objAviso;
}



}
