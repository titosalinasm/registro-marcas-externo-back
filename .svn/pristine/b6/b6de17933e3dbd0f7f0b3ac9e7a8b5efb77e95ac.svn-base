package pe.gob.indecopi.service;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.gob.indecopi.bean.ClsFilterRucBean;
import pe.gob.indecopi.bean.ClsUbigeoMatchBean;
import pe.gob.indecopi.repository.ClsSunatRepositoryI;
import pe.gob.indecopi.repository.ClsUbigeoRepositoryI;
import pe.gob.indecopi.result.ClsRucResult;
import pe.gob.indecopi.util.ClsResultDAO;

@Service
public class ClsSunatServiceImpl implements Serializable, ClsSunatServiceI {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8132116538062221347L;

	@Autowired
	private ClsSunatRepositoryI objConnReniecRepo;

	@Autowired
	private ClsUbigeoRepositoryI objConnUb;
	
	@Autowired
	private ClsResultDAO objResultDAO;

	public ClsRucResult doConsultaRuc(ClsFilterRucBean objFilterRuc) {
		ClsRucResult objResult= new ClsRucResult();
		try {
		if (objFilterRuc.getVcRuc()!= null) {
			
			objResultDAO =objConnReniecRepo.doConsultaRuc(objFilterRuc);
			
			objResult.getObjDatosRuc().setNuCodResultado(Integer.parseInt(""+objResultDAO.get("PNU_COD_RESULTADO")));
			objResult.getObjDatosRuc().setVcCIIU(""+objResultDAO.get("PVC_CIIU"));
			objResult.getObjDatosRuc().setVcRazonSocial(""+objResultDAO.get("PVC_RAZON_SOCIAL"));
			objResult.getObjDatosRuc().setVcUbigeo(""+objResultDAO.get("POUT_VC_UBIGEO"));
			objResult.getObjDatosRuc().setVcDireccion(""+objResultDAO.get("POUT_VC_DIREC"));
			

			objResult.setNuError(Integer.parseInt(objResultDAO.get("POUT_NU_ERROR")+""));
			objResult.setVcError(objResultDAO.get("POUT_VC_ERROR")+"");
			
			if(objResult.getObjDatosRuc().getVcUbigeo()!=null) {
				objResultDAO=objConnUb.doUbigeoMatch(objResult.getObjDatosRuc().getVcUbigeo());
				objResult.setObjUbigeoInd((ClsUbigeoMatchBean) objResultDAO.get("OBJ_UBIGEO_MATCH"));
			}
			
			
			if(objResult.getNuError()==200) {
				if(objResult.getObjDatosRuc().getNuCodResultado()==0) {
					objResult.setNuError(0);
					objResult.setVcError("");
					
				}else {
					objResult.setNuError(-1);
					objResult.setVcError("No se encontraron resultados");
				}
				
			}else {
				objResult.setNuError(Integer.parseInt(objResultDAO.get("POUT_NU_ERROR")+""));
				objResult.setVcError(objResultDAO.get("POUT_VC_ERROR")+"");
			}
			

			return objResult;
		} else {

			objResult.setNuError(-1);
			objResult.setVcError("No se ha ingresado ningún RUC");
			return objResult;
		}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return objResult;
	}
}
