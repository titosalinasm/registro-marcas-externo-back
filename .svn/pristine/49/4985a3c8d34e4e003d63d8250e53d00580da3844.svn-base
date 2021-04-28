package pe.gob.indecopi.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.gob.indecopi.bean.ClsDatosReniecBean;
import pe.gob.indecopi.bean.ClsFilterReniecBean;
import pe.gob.indecopi.bean.ClsFilterRucBean;
import pe.gob.indecopi.bean.ClsUbigeoMatchBean;
import pe.gob.indecopi.repository.ClsReniecRepositoryI;
import pe.gob.indecopi.repository.ClsSunatRepositoryI;
import pe.gob.indecopi.repository.ClsUbigeoRepositoryI;
import pe.gob.indecopi.result.ClsRespuestaReniecResult;
import pe.gob.indecopi.result.ClsRucResult;
import pe.gob.indecopi.util.ClsResultDAO;

@Service
public class ClsConsultaReniecServiceImpl implements Serializable,  ClsConsultaReniecService{

	/**
	 * 
	 */
	private static final long serialVersionUID = 987157533175607368L;

	@Autowired
	private ClsReniecRepositoryI objConnReniecRepo;
	
	@Autowired
	private ClsSunatRepositoryI objConnRucRepo;
	
	
	
	
	@Autowired
	private ClsUbigeoRepositoryI objConnUb;

	@Autowired
	private ClsResultDAO objResultDAO;

	public Object  doConsultaReniec(ClsFilterReniecBean objFiltroReniec) {
		
		Object resultado=new Object();
		
		ClsRespuestaReniecResult objResultReniec = new ClsRespuestaReniecResult();//reniec		
		ClsRucResult objResult= new ClsRucResult(); //pide
		
		try {
			
	if(objFiltroReniec.getVcDocIdentidad().length()==8) {
			
		if (objFiltroReniec.getVcDocIdentidad() != null) {
			
			objResultDAO = objConnReniecRepo.doConsultaReniec(objFiltroReniec);

			objResultReniec.setLstResultReniec((List<ClsDatosReniecBean>) objResultDAO.get("pCUR_RENIEC"));
			objResultReniec.setVcMensajeBusqueda(objResultDAO.get("VC_MENSAJE_BUSQUEDA")+"");
			
			objResultReniec.setNuError(Integer.parseInt(objResultDAO.get("POUT_NU_ERROR")+""));
			System.out.print("Codigo de error: "+Integer.parseInt(objResultDAO.get("POUT_NU_ERROR")+""));
			//objResultReniec.setNuError(-1);
			objResultReniec.setVcError(objResultDAO.get("POUT_VC_ERROR")+"");
			
			if(objResultReniec.getLstResultReniec()!=null) {
			if(objResultReniec.getLstResultReniec().get(0).getVcReniecUbigeo()!=null) {
				
				objResultDAO=objConnUb.doUbigeoMatch(objResultReniec.getLstResultReniec().get(0).getVcReniecUbigeo());
				
				objResultReniec.setObjUbigeoInd((ClsUbigeoMatchBean) objResultDAO.get("OBJ_UBIGEO_MATCH"));
			}
			}
	

		} else {
			objResultReniec.setNuError(-1);
			objResultReniec.setVcError("Debe ingresar un documento válido");
		}
		
		resultado= objResultReniec;
		
		}
	
	//RUC

	if(objFiltroReniec.getVcDocIdentidad().length()==11) {
		
		ClsFilterRucBean objFilterRuc=new ClsFilterRucBean();
		
		objFilterRuc.setVcRuc(objFiltroReniec.getVcDocIdentidad());
		
		objResultDAO =objConnRucRepo.doConsultaRuc(objFilterRuc);
		
		objResult.getObjDatosRuc().setNuCodResultado(Integer.parseInt(""+objResultDAO.get("PNU_COD_RESULTADO")));
		objResult.getObjDatosRuc().setVcCIIU(""+objResultDAO.get("PVC_CIIU"));
		objResult.getObjDatosRuc().setVcRazonSocial(""+objResultDAO.get("PVC_RAZON_SOCIAL"));
		objResult.getObjDatosRuc().setVcUbigeo(""+objResultDAO.get("POUT_VC_UBIGEO"));
		objResult.getObjDatosRuc().setVcDireccion(""+objResultDAO.get("POUT_VC_DIREC"));
		System.out.print(objResult.getObjDatosRuc().getVcRazonSocial());

		objResult.setNuError(Integer.parseInt(objResultDAO.get("POUT_NU_ERROR")+""));
		objResult.setVcError(objResultDAO.get("POUT_VC_ERROR")+"");
		
		if(objResult.getObjDatosRuc()!=null) {
		if(objResult.getObjDatosRuc().getVcUbigeo()!=null) {
			objResultDAO=objConnUb.doUbigeoMatch(objResult.getObjDatosRuc().getVcUbigeo());
			objResult.setObjUbigeoInd((ClsUbigeoMatchBean) objResultDAO.get("OBJ_UBIGEO_MATCH"));
		}
		}
		
		
		resultado= objResult;
	}
	
		
		}catch(Exception e) {
			objResultReniec.setNuError(-1);
			objResultReniec.setVcError(e+"");
			e.printStackTrace();
		}
		
		
		return resultado;
	}

}
