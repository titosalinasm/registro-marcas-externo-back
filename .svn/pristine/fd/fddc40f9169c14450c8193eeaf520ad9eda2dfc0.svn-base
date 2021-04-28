package pe.gob.indecopi.service;

import java.io.Serializable;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

import pe.gob.indecopi.bean.ClsConfiguracionBean;
import pe.gob.indecopi.bean.ClsFiltroConfiguracionBean;
import pe.gob.indecopi.bean.ClsFiltroUbigeoBean;
import pe.gob.indecopi.bean.ClsUbigeoBean;
import pe.gob.indecopi.exception.ClsException;
import pe.gob.indecopi.repository.ClsConfiguracionRepositoryI;
import pe.gob.indecopi.repository.ClsUbigeoRepositoryI;
import pe.gob.indecopi.result.ClsConfiguracionResult;
import pe.gob.indecopi.result.ClsUbigeoResult;
import pe.gob.indecopi.util.ClsResultDAO;

@Service
public class ClsUbigeoServiceImpl implements Serializable, ClsUbigeoServiceI {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1945914613806716575L;
	
	
	public Logger logger = Logger.getLogger(ClsUbigeoServiceImpl.class);
	
	@Autowired
	private ClsUbigeoRepositoryI objConnRepo;

	@Autowired
	private ClsResultDAO objResultDAO;
	
	
	@Override
	public ClsUbigeoResult doUbigeo(ClsFiltroUbigeoBean objFiltro) {
		//logger.info("doConsultaTerminos(filro) => "+objFiltroTerminos.getVcUsuario());

		ClsUbigeoResult objConfigResult=new ClsUbigeoResult();
		try {

			objResultDAO=objConnRepo.doUbigeo(objFiltro);
			
			objConfigResult.setLstUbigeo((List<ClsUbigeoBean>)objResultDAO.get("POUT_CUR_REGISTRO"));
		
		}catch(Exception e) {
			e.printStackTrace();
			throw new ClsException(e+"", new Throwable("ERROR"));
		
		}
		
		return objConfigResult;
		
	}

}
