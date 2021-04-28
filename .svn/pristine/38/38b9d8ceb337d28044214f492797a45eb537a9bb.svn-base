package pe.gob.indecopi.service;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.gob.indecopi.bean.ClsDatosDetTerminosBean;
import pe.gob.indecopi.bean.ClsFiltroTerminosBean;
import pe.gob.indecopi.bean.ClsFiltroVerificarTermsBean;
import pe.gob.indecopi.repository.ClsTerminosRepository;
import pe.gob.indecopi.repository.ClsTerminosRepositoryI;
import pe.gob.indecopi.result.ClsTerminosResult;
import pe.gob.indecopi.result.ClsVerifTermsResult;
import pe.gob.indecopi.util.ClsResultDAO;

@Service
public class ClsTerminosServiceImpl implements  ClsTerminosService, Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7568820726176511928L;

	public Logger logger = Logger.getLogger(ClsTerminosServiceImpl.class);

	@Autowired
	private ClsTerminosRepositoryI objConnTermRepository;

	@Autowired
	private ClsResultDAO objResultDAO;
	
	@SuppressWarnings("unchecked")
	@Override
	public ClsTerminosResult doConsultaTerminos(ClsFiltroTerminosBean objFiltroTerminos) {
		logger.info("doConsultaTerminos(filro) => "+objFiltroTerminos.getVcUsuario());
		ClsTerminosResult objRespTerminos=new ClsTerminosResult();
		try {
		if(objFiltroTerminos.getNuIdProcedimiento()!=null) {
			objResultDAO=objConnTermRepository.doConsultaTerminos(objFiltroTerminos);
			
			objRespTerminos.setLstTerminos((List<ClsDatosDetTerminosBean>)objResultDAO.get("pOUT_CUR_TERMINOS"));
			
			objRespTerminos.setNuError(new Long(Integer.parseInt(objResultDAO.get("pOUT_NU_CODIGO_ERROR")+"")));
			objRespTerminos.setVcError((String)objResultDAO.get("pOUT_VC_MENSAJE_ERROR"));
			
		}else {
			objRespTerminos.setNuError(-1L);
			objRespTerminos.setVcError("Oh, Algo salió mal :(");
			
		}
		}catch(Exception e) {
			logger.info(e);
		}
		
		return objRespTerminos;
		
	}
	
	@SuppressWarnings("unchecked") 
	@Override
	public ClsVerifTermsResult doVerificarTerminos(ClsFiltroVerificarTermsBean objVerificarTerms) {
		logger.info("doVerificarTerminos() => "
	    +"vcUsuario="+objVerificarTerms.getVcUsuario() 
		+" nuFlagAceptar="+objVerificarTerms.getNuFlagAceptar()
		+" vcIpUsuario="+objVerificarTerms.getVcIpUsuario());
		ClsVerifTermsResult objRespVerifTerms = new ClsVerifTermsResult();
		try {
			if (objVerificarTerms.getNuIdProcedimiento() != null) {
				String	flag=objConnTermRepository.doVerificarTerminos(objVerificarTerms);
				objRespVerifTerms.setNuFlagVerificar(Integer.parseInt(flag));
				if(objRespVerifTerms.getNuFlagVerificar()==0) {
					ClsFiltroTerminosBean objFiltro=new ClsFiltroTerminosBean();
					objFiltro.setNuIdProcedimiento(objVerificarTerms.getNuIdProcedimiento());
					objResultDAO=objConnTermRepository.doConsultaTerminos(objFiltro);
					objRespVerifTerms.setLstTerminos((List<ClsDatosDetTerminosBean>)objResultDAO.get("pOUT_CUR_TERMINOS"));
					objRespVerifTerms.setNuError(new Long(Integer.parseInt(objResultDAO.get("pOUT_NU_CODIGO_ERROR")+"")));
					objRespVerifTerms.setVcError((String)objResultDAO.get("pOUT_VC_MENSAJE_ERROR"));
				}else {
					objRespVerifTerms.setNuError(0L);
					objRespVerifTerms.setVcError("");
				}
				
				
			} else {
				objRespVerifTerms.setNuError(-1L);
				objRespVerifTerms.setVcError("Oh, Algo salió mal :(");	
			}
		}catch(Exception e) {
			logger.info(e);
		}
		
		return objRespVerifTerms;
	}
	
	@SuppressWarnings("unchecked") 
	@Override
	public ClsVerifTermsResult doAceptaTerminos(ClsFiltroTerminosBean objFiltroTerminos) {
		logger.info("doVerificarTerminos() => ");
		ClsVerifTermsResult objRespVerifTerms = new ClsVerifTermsResult();
		try {
				String	flag=objConnTermRepository.saveAceptacionConsideracion(objFiltroTerminos);
				
				objRespVerifTerms.setNuFlagVerificar(Integer.parseInt(flag));
				if(objRespVerifTerms.getNuFlagVerificar()==0) {
					ClsFiltroTerminosBean objFiltro=new ClsFiltroTerminosBean();
					objFiltro.setNuIdProcedimiento(objFiltroTerminos.getNuIdProcedimiento());
					objResultDAO=objConnTermRepository.doConsultaTerminos(objFiltro);
					objRespVerifTerms.setLstTerminos((List<ClsDatosDetTerminosBean>)objResultDAO.get("pOUT_CUR_TERMINOS"));
					objRespVerifTerms.setNuError(new Long(Integer.parseInt(objResultDAO.get("pOUT_NU_CODIGO_ERROR")+"")));
					objRespVerifTerms.setVcError((String)objResultDAO.get("pOUT_VC_MENSAJE_ERROR"));
				}else {
					objRespVerifTerms.setNuError(0L);
					objRespVerifTerms.setVcError("");
					}
 
		}catch(Exception e) {
			logger.info(e);
		}
		
		return objRespVerifTerms;
	}

}
