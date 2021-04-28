package pe.gob.indecopi.service;

import java.io.Serializable;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

import pe.gob.indecopi.bean.ClsConfiguracionBean;
import pe.gob.indecopi.bean.ClsFiltroConfiguracionBean;
import pe.gob.indecopi.repository.ClsConfiguracionRepositoryI;
import pe.gob.indecopi.result.ClsConfiguracionResult;
import pe.gob.indecopi.util.ClsResultDAO;

@Service
public class ClsConfiguracionServiceImpl implements Serializable, ClsConfiguracionService {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1945914613806716575L;
	
	
	public Logger logger = Logger.getLogger(ClsConfiguracionServiceImpl.class);
	
	@Autowired
	private ClsConfiguracionRepositoryI objConnConfig;

	@Autowired
	private ClsResultDAO objResultDAO;
	
	
	@Override
	public ClsConfiguracionResult doConfiguracion(ClsFiltroConfiguracionBean objConfiguracion) {
		//logger.info("doConsultaTerminos(filro) => "+objFiltroTerminos.getVcUsuario());

		ClsConfiguracionResult objConfigResult=new ClsConfiguracionResult();
		try {

			objResultDAO=objConnConfig.doConfiguracion(objConfiguracion);
			
			objConfigResult.setLstConfiguracion((List<ClsConfiguracionBean>)objResultDAO.get("POUT_CUR_CONFIGURACION"));
			
			objConfigResult.setNuError(new Long(Integer.parseInt(objResultDAO.get("POUT_NU_ERROR")+"")));
			objConfigResult.setVcError((String)objResultDAO.get("POUT_VC_ERROR"));


		
		}catch(Exception e) {
			System.out.println(e);
			e.printStackTrace();
			logger.info(e);
		}
		
		return objConfigResult;
		
	}

}
