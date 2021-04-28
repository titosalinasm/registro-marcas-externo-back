package pe.gob.indecopi.service;

import java.io.Serializable;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.gob.indecopi.bean.ClsFiltroTramitePagoBean;
import pe.gob.indecopi.bean.ClsUsuarioBean;
import pe.gob.indecopi.repository.ClsTramiteSELRepositoryI;
import pe.gob.indecopi.result.ClsUsuarioResult;
import pe.gob.indecopi.result.ClsValidaTramitePagoResult;
import pe.gob.indecopi.util.ClsResultDAO;

@Service
public class ClsUsuarioServiceImpl implements ClsUsuarioService, Serializable{
/**
	 * 
	 */
	private static final long serialVersionUID = -934672257655321018L;

private  Logger logger = Logger.getLogger(ClsUsuarioServiceImpl.class);
	
@Autowired
private ClsTramiteSELRepositoryI objConnTramiteSEL;

@Autowired
private ClsResultDAO objResultDAO;

public ClsUsuarioResult doUsuario(ClsUsuarioBean objUsuario) {
	ClsUsuarioResult objUsuarioRes=new ClsUsuarioResult();
	try {
		
		objResultDAO=objConnTramiteSEL.doUsuario(objUsuario);
		
		objUsuarioRes.getObjUsuario().setNuIdTipoOrigen(		Integer.parseInt(""+objResultDAO.get("POUT_NU_ID_TIPO_ORIGEN")));
		objUsuarioRes.getObjUsuario().setVcIdTipoOrigen(		(String)objResultDAO.get("POUT_VC_ID_TIPO_ORIGEN"));
		objUsuarioRes.getObjUsuario().setNuIdTipoDocumento(		Integer.parseInt(""+objResultDAO.get("POUT_NU_ID_TIPO_DOCUMENTO")));
		objUsuarioRes.getObjUsuario().setVcIdTipoDocumento(		(String)objResultDAO.get("POUT_VC_ID_TIPO_DOCUMENTO"));
		objUsuarioRes.getObjUsuario().setVcCorreo(				(String)objResultDAO.get("POUT_VC_CORREO"));
		objUsuarioRes.getObjUsuario().setVcDocIdentidad(		(String)objResultDAO.get("POUT_VC_DOC_IDENTIDAD"));
		objUsuarioRes.getObjUsuario().setVcUsuario(				(String)objResultDAO.get("POUT_VC_USUARIO"));
		objUsuarioRes.getObjUsuario().setVcNombreCompleto(		(String)objResultDAO.get("POUT_VC_NOMBRE_COMPLETO"));
		objUsuarioRes.getObjUsuario().setNuFlagAlta(			Integer.parseInt(""+objResultDAO.get("POUT_NU_FLAG_ALTA")));
		objUsuarioRes.getObjUsuario().setNuIdUsuarioSel(        Integer.parseInt(""+objResultDAO.get("POUT_NU_ID_USUARIO_SEL")));
		
		objUsuarioRes.setNuError(new Long(Integer.parseInt(objResultDAO.get("POUT_NU_ERROR")+"")));
		objUsuarioRes.setVcError(objResultDAO.get("POUT_VC_ERROR")+"");
		
	}catch (Exception e) {
		logger.info(e);
		objUsuarioRes.setObjUsuario(null);
		objUsuarioRes.setNuError(-1L);
		objUsuarioRes.setVcError(""+e);
	}
	return objUsuarioRes;
	}
}
