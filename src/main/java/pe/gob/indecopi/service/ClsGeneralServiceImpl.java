package pe.gob.indecopi.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.gob.indecopi.bean.ClsClaseBean;
import pe.gob.indecopi.bean.ClsFiltroClaseBean;
import pe.gob.indecopi.bean.ClsFiltroValidacionBean;
import pe.gob.indecopi.bean.ClsGeneroBean;
import pe.gob.indecopi.bean.ClsNizaBean;
import pe.gob.indecopi.bean.ClsPaisesBean;
import pe.gob.indecopi.bean.ClsPersoneriaBean;
import pe.gob.indecopi.bean.ClsTipoDocumentoBean;
import pe.gob.indecopi.bean.ClsTipoPresentacionBean;
import pe.gob.indecopi.bean.ClsTipoPrioridadBean;
import pe.gob.indecopi.bean.ClsTipoSolicitud;
import pe.gob.indecopi.exception.ClsException;
import pe.gob.indecopi.repository.ClsGeneralRepositoryI;
import pe.gob.indecopi.repository.ClsNizaRepositoryI;
import pe.gob.indecopi.result.ClsClaseResult;
import pe.gob.indecopi.result.ClsLstGeneralResult;
import pe.gob.indecopi.result.ClsValidaResult;
import pe.gob.indecopi.util.ClsResultDAO;

@Service
public class ClsGeneralServiceImpl implements Serializable, ClsGeneralService {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4583204397936297279L;
	
	@Autowired
	private ClsGeneralRepositoryI objConn;
	
	@Autowired
	private ClsNizaRepositoryI objConnNiza;
	
	@Autowired
	private ClsResultDAO objResultDAO;

	@Override
	public ClsLstGeneralResult doGeneral() {
	ClsLstGeneralResult objResult=new ClsLstGeneralResult();
		try {
			objResultDAO=objConn.doLstGeneral();	
			objResult.setLstTipoSolicitud((List<ClsTipoSolicitud>)objResultDAO.get("POUT_CUR_TIPO_SOLIC"));
			objResult.setLstTipoPresentacion((List<ClsTipoPresentacionBean>)objResultDAO.get("POUT_CUR_TIPO_SIGNO"));
			objResult.setLstGenero((List<ClsGeneroBean>)objResultDAO.get("POUT_CUR_GENERO"));
			objResult.setLstTipoPrioridad((List<ClsTipoPrioridadBean>)objResultDAO.get("POUT_CUR_TIPO_PRIORIDAD"));
			objResult.setLstTipodocumento((List<ClsTipoDocumentoBean>)objResultDAO.get("POUT_CUR_TIPO_DOCUMENTO"));
			objResult.setLstPaises((List<ClsPaisesBean>)objResultDAO.get("POUT_CUR_PAISES"));
			objResult.setLstPersoneria((List<ClsPersoneriaBean>)objResultDAO.get("POUT_CUR_PERSONERIA"));
		//	POUT_CUR_PERSONERIA
	
			}catch(Exception e) {
				e.printStackTrace();
				throw new ClsException(e+"", new Throwable("ERROR"));
			}
		
		return objResult;
	}
	
	
	@Override
	public ClsClaseResult dolstClase(ClsFiltroClaseBean objFiltroClase) {
		ClsClaseResult objResult=new ClsClaseResult();
		String vcBuscar="";
		vcBuscar=objFiltroClase.getVcParaBusq();
		
	try {
		
		if(vcBuscar.length()==0) {
			objResultDAO=objConn.doLstClase(objFiltroClase);
		}else {
			objResultDAO=objConnNiza.doLstClaseNiza(objFiltroClase);
		}
		
		objResult.setLstClase((List<ClsNizaBean>)objResultDAO.get("POUT_CUR_CLASE"));

	}catch(Exception e) {
		e.printStackTrace();
		throw new ClsException(e+"", new Throwable("ERROR"));
	}
	
	return objResult;
	}
	
	@Override
	public ClsValidaResult doValidarExpediente(ClsFiltroValidacionBean objFiltro) {
		ClsValidaResult objResult=new ClsValidaResult();
		
	try {
		
		objResultDAO=objConn.doValidaExpediente(objFiltro);
		
		objResult.setNuValor(Integer.parseInt(objResultDAO.get("POUT_NU_VALOR")+""));
		objResult.setNuError(0L);

	}catch(Exception e) {
		objResult.setNuError(-1L);
		objResult.setVcError(e.getMessage());
		e.printStackTrace();
		throw new ClsException(e+"", new Throwable("ERROR"));
	}
	
	return objResult;
	}
	
	@Override
	public ClsValidaResult doValidarCertificado(ClsFiltroValidacionBean objFiltro) {
		ClsValidaResult objResult=new ClsValidaResult();
		
	try {
		
		objResultDAO=objConn.doValidaCertificado(objFiltro);
		
		objResult.setNuValor(Integer.parseInt(objResultDAO.get("POUT_NU_VALOR")+""));
		objResult.setNuError(0L);

	}catch(Exception e) {
		objResult.setNuError(-1L);
		objResult.setVcError(e.getMessage());
		e.printStackTrace();
		throw new ClsException(e+"", new Throwable("ERROR"));
	}
	
	return objResult;
	}


}
