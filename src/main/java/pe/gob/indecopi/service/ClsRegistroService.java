package pe.gob.indecopi.service;

import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Clob;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import oracle.jdbc.OracleTypes;
import pe.gob.indecopi.bean.ClsArchivoBean;
import pe.gob.indecopi.bean.ClsClaseSolBean;
import pe.gob.indecopi.bean.ClsParametrosBean;
import pe.gob.indecopi.bean.ClsPosicionBean;
import pe.gob.indecopi.bean.ClsPrioridadBean;
import pe.gob.indecopi.bean.ClsPrioridadExtBean;
import pe.gob.indecopi.bean.ClsSolicitudBean;
import pe.gob.indecopi.bean.ClsTramiteSELBean;
import pe.gob.indecopi.exception.ClsException;
import pe.gob.indecopi.param.ClsConstantes;
import pe.gob.indecopi.repository.ClsParametroRepositoryI;
import pe.gob.indecopi.repository.ClsRegistroRepositoryI;
import pe.gob.indecopi.repository.ClsTramiteSELRepositoryI;
import pe.gob.indecopi.result.ClsLstGeneralResult;
import pe.gob.indecopi.result.ClsRegistroResult;
import pe.gob.indecopi.util.ClsResultDAO;
import pe.gob.indecopi.util.ClsUtil;


@Service
public class ClsRegistroService implements Serializable, ClsRegistroServiceI{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4583204397936297279L;
	
	@Autowired
	private ClsRegistroRepositoryI objConn;
	
	@Autowired
	private ClsTramiteSELRepositoryI objConnSEL;
	
	@Autowired
	private ClsParametroRepositoryI objConnParam;
	
	@Autowired
	private ClsResultDAO objResultDAO;
	
	@Override
	public Object doRegistro(ClsSolicitudBean objSolicitud) {
		//ClsTramiteSELBean objTramite=new ClsTramiteSELBean();
		
		ClsRegistroResult objResult=new ClsRegistroResult();

		try {
			
			
			
			
			ClsParametrosBean objParam=new ClsParametrosBean();
			objResultDAO=objConnParam.doParametro();
			objParam=(ClsParametrosBean)objResultDAO.get("POUT_PARAMETRO");
			objParam.setNuCantClases(objSolicitud.getLstClases().size());
			
			objResultDAO=objConnSEL.doCompruebaPagos(objParam, objSolicitud);
		    if(Integer.parseInt(objResultDAO.get("POUT_NU_ERROR")+"")==0) {
				objResultDAO=objConn.doRegistro(objSolicitud);
				
				/*
				objTramite.setClDetTramite(""+objResultDAO.get("POUT_CL_CUERPO_CORREO"));
				objTramite.setVcNroExpediente(objResultDAO.get("POUT_NU_NRO_EXPEDIENTE")+"");
				objTramite.setVcSiglaExpediente(""+objResultDAO.get("POUT_VC_AREA_EXPEDIENTE"));
				objTramite.setVcAnioExpediente(""+objResultDAO.get("POUT_NU_ANIO_EXPEDIENTE"));
				objTramite.setNuIdEstadoTramite(Integer.parseInt(""+objResultDAO.get("POUT_NU_ESTADO_REGISTRO")));
				objTramite.setNuIdProcedimiento(Integer.parseInt(""+objResultDAO.get("POUT_NU_PROCEDIMIENTO")));
				
				objResultDAO=objConnSEL.doTramite(objTramite, objSolicitud, objParam, objSolicitud.getVcUsuario());
				*/
				
				if(Integer.parseInt(objResultDAO.get("POUT_NU_ERROR")+"")==0) {
				objResult.setNuExpediente(Integer.parseInt(objResultDAO.get("POUT_NU_NRO_EXPEDIENTE")+""));
				objResult.setNuAnio(	  Integer.parseInt(""+objResultDAO.get("POUT_NU_ANIO_EXPEDIENTE")));
				objResult.setVcArea(	  ClsConstantes.vcArea);
				}else {
					throw new ClsException(""+objResultDAO.get("POUT_VC_ERROR"), new Throwable("-1"));
				}
				
		    }else {
		    	throw new ClsException(""+objResultDAO.get("POUT_VC_ERROR"), new Throwable("-1"));
		    }
			
			
		}catch (Exception e) {
			throw new ClsException(""+objResultDAO.get("POUT_VC_ERROR"), new Throwable("-1"));
			}
		
		return objResult;
	}
	
	
	@Override
	public Object doRegistr2(ClsSolicitudBean objSolicitud) {
		
		ClsRegistroResult objResult=new ClsRegistroResult();

		try {
			
			ClsParametrosBean objParam=new ClsParametrosBean();
			objResultDAO=objConnParam.doParametro();
			objParam=(ClsParametrosBean)objResultDAO.get("POUT_PARAMETRO");
			objParam.setNuCantClases(objSolicitud.getLstClases().size());
			
			System.out.println("Haciendo comprobacion...");
			objResultDAO=objConnSEL.doCompruebaPagos(objParam, objSolicitud);

			
		    if(Integer.parseInt(objResultDAO.get("POUT_NU_ERROR")+"")==0) {
		    	System.out.println("Comprobado...");
		    	
		    	//INICIO Proceso de archivos en base64
		    	
		    	//Archivos de mi signo
		    	for(int i=0; i<objSolicitud.getObjDatosSigno().getLstArchivosSigno().size(); i++) {
		    		
		    		ClsArchivoBean objArchivoSigno =new ClsArchivoBean();
		    		objArchivoSigno=objSolicitud.getObjDatosSigno().getLstArchivosSigno().get(i);
		    		
					String vVcFileName = "";
					String vVcFileNameOriginal = "";
					String vVcExtension = "";
					String vVcUriDescarga = "";
					int vNuNroPaginas = 0;
					
		    		//logger.info("SERVICE: doValidarArchivo()... Decodificando cada archivo entrante de Base64");
		    		String vVcDataDoc = objArchivoSigno.getVcFileBase64().toString();
					List<String> list = Stream.of(vVcDataDoc.split(",")).collect(Collectors.toList());
					
					vVcExtension = FilenameUtils.getExtension(objArchivoSigno.getVcNomOriginal());
					vVcFileNameOriginal = objArchivoSigno.getVcNomOriginal();
					vVcFileName = "doc_" + ClsUtil.doGenerarNombreArchivoAleatorio().toString() + "." + vVcExtension;
					
					//logger.info("SERVICE: doValidarArchivo()... Nombre original: " + vVcFileNameOriginal);
					//logger.info("SERVICE: doValidarArchivo()... Nombre generado: " + vVcFileName);

					Path vVcRutaFile = Paths.get(objParam.getVcRutaTemporal(), vVcFileName);

					//logger.info("SERVICE: doValidarArchivo()... Verifica si el archivo ya existe");
					if (Files.exists(vVcRutaFile)) {
						vVcFileName = "doc_" + ClsUtil.doGenerarNombreArchivoAleatorio().toString() + "." + vVcExtension;
						vVcRutaFile = Paths.get(objParam.getVcRutaTemporal(), vVcFileName);
						//logger.info("SERVICE: doValidarArchivo()... Nuevo nombre generado: " + vVcFileName);
					}
					//logger.info("SERVICE: doValidarArchivo()... Fin de verificación existencia del archivo.");
					
					//logger.info("SERVICE: doValidarArchivo()... Iniciando escritura del archivo en disco");
					byte[] vVcArchivoDecode = Base64.getDecoder().decode(list.get(1));
					Files.write(vVcRutaFile, vVcArchivoDecode);
					//logger.info("SERVICE: doValidarArchivo()... Finalizando escritura del archivo en disco");

					//vVcUriDescarga = ServletUriComponentsBuilder.fromCurrentContextPath().path("/general/downloadfile/")
					//		.path(vVcFileName).toUriString();
					objSolicitud.getObjDatosSigno().getLstArchivosSigno().get(i).setVcExtension(vVcExtension);
					objSolicitud.getObjDatosSigno().getLstArchivosSigno().get(i).setVcNomFinal(vVcFileName);
					objSolicitud.getObjDatosSigno().getLstArchivosSigno().get(i).setVcRuta(objParam.getVcRutaTemporal());
		    	}
		    	
		    	//prioridad extranjera
		    	ClsPrioridadExtBean objPrioridadFinal= new ClsPrioridadExtBean();
		    	List<ClsPosicionBean> lstPosicion= new ArrayList<ClsPosicionBean>();
		    	//int k=0;
		    	System.out.println("objPrioridadFinal.getLstPrioridad().size(): "+objPrioridadFinal.getLstPrioridad().size());
		    	System.out.println("objSolicitud.getObjPrioridadExtr().getLstPrioridad().size(): "+objSolicitud.getObjPrioridadExtr().getLstPrioridad().size());
		    	System.out.println("objSolicitud.getLstClases().size(): "+objSolicitud.getLstClases().size());
		    	/*
		    	for(int i=0; i<objSolicitud.getObjPrioridadExtr().getLstPrioridad().size(); i++) {
		    		if(objSolicitud.getObjPrioridadExtr().getLstPrioridad().get(i).getNuFlagTodos()==1) {
			    		//prioridad para todas las clases
		    			for(int j=0; j<objSolicitud.getLstClases().size(); j++) {
		    				
		    				objPrioridadFinal.setNuFlagPrioridadExtr(1);
		    				objPrioridadFinal.getLstPrioridad().add(objSolicitud.getObjPrioridadExtr().getLstPrioridad().get(i));
		    				
		    				//System.out.println("for objPrioridadFinal.getLstPrioridad().size(): "+objPrioridadFinal.getLstPrioridad().size());
		    				//System.out.println("objSolicitud.getLstClases().get(j).getNuIdClase().toString(): "+objSolicitud.getLstClases().get(j).getNuIdClase().toString());
		    				int z=objPrioridadFinal.getLstPrioridad().size()-1;
		    				objPrioridadFinal.getLstPrioridad().get(z).setVcClases(objSolicitud.getLstClases().get(j).getNuIdClase().toString());
		    				
		    				objPrioridadFinalFinal.getLstPrioridad().add(objPrioridadFinal.getLstPrioridad().get(z));
		    				System.out.println("objPrioridadFinalFinal.getLstPrioridad().get(z).getVcClases(): "+objPrioridadFinalFinal.getLstPrioridad().get(z).getVcClases());
		    				
		    			}
			    	}else {
		    			
			    		objPrioridadFinal.setNuFlagPrioridadExtr(0);
			    		objPrioridadFinalFinal.getLstPrioridad().add(objSolicitud.getObjPrioridadExtr().getLstPrioridad().get(i));
		    		}
		    	}
		    	*/

		    	for(ClsPrioridadBean objPrioridad : objSolicitud.getObjPrioridadExtr().getLstPrioridad()) {
		    		if(objPrioridad.getNuFlagTodos()==1) {
			    		//prioridad para todas las clases
		    			for(ClsClaseSolBean objClaseSol : objSolicitud.getLstClases()) {
		    				
		    				objPrioridadFinal.setNuFlagPrioridadExtr(1);
		    				objPrioridadFinal.getLstPrioridad().add(objPrioridad);

		    				int z=objPrioridadFinal.getLstPrioridad().size()-1;
		    				objPrioridadFinal.getLstPrioridad().get(z).setVcClases(objClaseSol.getNuIdClase().toString());
		    				objPrioridadFinal.getLstPrioridad().set(z, objPrioridadFinal.getLstPrioridad().get(z));
		    				ClsPosicionBean objPosicion=new ClsPosicionBean();
		    				objPosicion.setNuPosicion(z);
		    				objPosicion.setVcValor(objClaseSol.getNuIdClase().toString());
		    				lstPosicion.add(objPosicion);
		    				
		    				//System.out.println("for objPrioridadFinal.getLstPrioridad().size(): "+objPrioridadFinal.getLstPrioridad().size());
		    				//System.out.println("objSolicitud.getLstClases().get(j).getNuIdClase().toString(): "+objSolicitud.getLstClases().get(j).getNuIdClase().toString());
		    	
		    				
		    				//objPrioridadFinalFinal.getLstPrioridad().add(objPrioridadFinal.getLstPrioridad().get(z));
		    				System.out.println("objPrioridadFinalFinal.getLstPrioridad().get(z).getVcClases(): "+objPrioridadFinal.getLstPrioridad().get(z).getVcClases());
		    				
		    			}
			    	}else {
			    		objPrioridadFinal.setNuFlagPrioridadExtr(0);
			    		objPrioridadFinal.getLstPrioridad().add(objPrioridad);
		    		}
		    	}
		        int k=0;
		    	for(ClsPrioridadBean objPrioridad : objPrioridadFinal.getLstPrioridad()) {
		    	 for(int i=0; i<lstPosicion.size(); i++) {
		    		 System.out.println("compare: "+k+"=="+lstPosicion.get(i).getNuPosicion());
		    		 if(k==lstPosicion.get(i).getNuPosicion()) {
		    			 objPrioridad.setVcClases(lstPosicion.get(i).getVcValor());
		    		 }
		    		 
		    	 }
		    	 k++;
		    	}
		    	
            

		    	
		    for(int i=0; i<objPrioridadFinal.getLstPrioridad().size(); i++) {
		    	System.out.println(" valid objPrioridadFinal.getLstPrioridad().get(i).getVcClases(): "+objPrioridadFinal.getLstPrioridad().get(i).getVcClases());
		    }
		    	
		    	//Archivos prioridad extranjera
		    	objSolicitud.setObjPrioridadExtr(objPrioridadFinal);
		    	
		    	for(int i=0; i<objSolicitud.getObjPrioridadExtr().getLstPrioridad().size(); i++) {
		    		
		    		for(int j=0; j<objSolicitud.getObjPrioridadExtr().getLstPrioridad().get(i).getLstArchivoPrioridad().size(); j++) {
		    		
		    		ClsArchivoBean objArchivoPrioExtranjera =new ClsArchivoBean();
		    		objArchivoPrioExtranjera=objSolicitud.getObjPrioridadExtr().getLstPrioridad().get(i).getLstArchivoPrioridad().get(j);
		    		
					String vVcFileName = "";
					String vVcFileNameOriginal = "";
					String vVcExtension = "";
					String vVcUriDescarga = "";
					int    vNuNroPaginas = 0;
					
		    		//logger.info("SERVICE: doValidarArchivo()... Decodificando cada archivo entrante de Base64");
		    		String vVcDataDoc = objArchivoPrioExtranjera.getVcFileBase64().toString();
					List<String> list = Stream.of(vVcDataDoc.split(",")).collect(Collectors.toList());
					
					vVcExtension = FilenameUtils.getExtension(objArchivoPrioExtranjera.getVcNomOriginal());
					vVcFileNameOriginal = objArchivoPrioExtranjera.getVcNomOriginal();
					vVcFileName = "doc_" + ClsUtil.doGenerarNombreArchivoAleatorio().toString() + "." + vVcExtension;
					
					//logger.info("SERVICE: doValidarArchivo()... Nombre original: " + vVcFileNameOriginal);
					//logger.info("SERVICE: doValidarArchivo()... Nombre generado: " + vVcFileName);

					Path vVcRutaFile = Paths.get(objParam.getVcRutaFinal(), vVcFileName);

					//logger.info("SERVICE: doValidarArchivo()... Verifica si el archivo ya existe");
					if (Files.exists(vVcRutaFile)) {
						vVcFileName = "doc_" + ClsUtil.doGenerarNombreArchivoAleatorio().toString() + "." + vVcExtension;
						vVcRutaFile = Paths.get(objParam.getVcRutaFinal(), vVcFileName);
						//logger.info("SERVICE: doValidarArchivo()... Nuevo nombre generado: " + vVcFileName);
					}
					//logger.info("SERVICE: doValidarArchivo()... Fin de verificación existencia del archivo.");
					
					//logger.info("SERVICE: doValidarArchivo()... Iniciando escritura del archivo en disco");
					byte[] vVcArchivoDecode = Base64.getDecoder().decode(list.get(1));
					Files.write(vVcRutaFile, vVcArchivoDecode);
					//logger.info("SERVICE: doValidarArchivo()... Finalizando escritura del archivo en disco");

					//vVcUriDescarga = ServletUriComponentsBuilder.fromCurrentContextPath().path("/general/downloadfile/")
					//		.path(vVcFileName).toUriString();
					objSolicitud.getObjPrioridadExtr().getLstPrioridad().get(i).getLstArchivoPrioridad().get(j).setVcExtension(vVcExtension);
					objSolicitud.getObjPrioridadExtr().getLstPrioridad().get(i).getLstArchivoPrioridad().get(j).setVcNomFinal(vVcFileName);
					objSolicitud.getObjPrioridadExtr().getLstPrioridad().get(i).getLstArchivoPrioridad().get(j).setVcRuta(objParam.getVcRutaFinal());
					
		    		}
		    	}
		    	
		    	
		    	//Archivos de representantes
		    	for(int i=0; i<objSolicitud.getLstPersona().size(); i++) {
		    		
		    		for(int j=0; j<objSolicitud.getLstPersona().get(i).getLstArchivoRepresentante().size(); j++) {
		    		
		    		ClsArchivoBean objArchivoRepresentante =new ClsArchivoBean();
		    		objArchivoRepresentante=objSolicitud.getLstPersona().get(i).getLstArchivoRepresentante().get(j);
		    		
					String vVcFileName = "";
					String vVcFileNameOriginal = "";
					String vVcExtension = "";
					String vVcUriDescarga = "";
					int    vNuNroPaginas = 0;
					
		    		//logger.info("SERVICE: doValidarArchivo()... Decodificando cada archivo entrante de Base64");
		    		String vVcDataDoc = objArchivoRepresentante.getVcFileBase64().toString();
					List<String> list = Stream.of(vVcDataDoc.split(",")).collect(Collectors.toList());
					
					vVcExtension = FilenameUtils.getExtension(objArchivoRepresentante.getVcNomOriginal());
					vVcFileNameOriginal = objArchivoRepresentante.getVcNomOriginal();
					vVcFileName = "doc_" + ClsUtil.doGenerarNombreArchivoAleatorio().toString() + "." + vVcExtension;
					
					//logger.info("SERVICE: doValidarArchivo()... Nombre original: " + vVcFileNameOriginal);
					//logger.info("SERVICE: doValidarArchivo()... Nombre generado: " + vVcFileName);

					Path vVcRutaFile = Paths.get(objParam.getVcRutaFinal(), vVcFileName);

					//logger.info("SERVICE: doValidarArchivo()... Verifica si el archivo ya existe");
					if (Files.exists(vVcRutaFile)) {
						vVcFileName = "doc_" + ClsUtil.doGenerarNombreArchivoAleatorio().toString() + "." + vVcExtension;
						vVcRutaFile = Paths.get(objParam.getVcRutaFinal(), vVcFileName);
						//logger.info("SERVICE: doValidarArchivo()... Nuevo nombre generado: " + vVcFileName);
					}
					//logger.info("SERVICE: doValidarArchivo()... Fin de verificación existencia del archivo.");
					
					//logger.info("SERVICE: doValidarArchivo()... Iniciando escritura del archivo en disco");
					byte[] vVcArchivoDecode = Base64.getDecoder().decode(list.get(1));
					Files.write(vVcRutaFile, vVcArchivoDecode);
					//logger.info("SERVICE: doValidarArchivo()... Finalizando escritura del archivo en disco");

					objSolicitud.getLstPersona().get(i).getLstArchivoRepresentante().get(j).setVcExtension(vVcExtension);
					objSolicitud.getLstPersona().get(i).getLstArchivoRepresentante().get(j).setVcNomFinal(vVcFileName);
					objSolicitud.getLstPersona().get(i).getLstArchivoRepresentante().get(j).setVcRuta(objParam.getVcRutaFinal());
					
		    		}
		    	}
		    	
		     	//FIN Proceso de archivos en base64
		    	
				objResultDAO=objConn.doRegistro(objSolicitud);
				
				if(Integer.parseInt(objResultDAO.get("POUT_NU_ERROR")+"")==0) {
				objResult.setNuExpediente(Integer.parseInt(objResultDAO.get("POUT_NU_NRO_EXPEDIENTE")+""));
				objResult.setNuAnio(	  Integer.parseInt(""+objResultDAO.get("POUT_NU_ANIO_EXPEDIENTE")));
				objResult.setVcArea(	  ClsConstantes.vcArea);
				}else {
					throw new ClsException(""+objResultDAO.get("POUT_VC_ERROR"), new Throwable("-1"));
				}
				
		    }else {
		    	throw new ClsException(""+objResultDAO.get("POUT_VC_ERROR"), new Throwable("-1"));
		    }
			
			
		}catch (Exception e) {
			e.printStackTrace();
			throw new ClsException(""+objResultDAO.get("POUT_VC_ERROR"), new Throwable("-1"));
			}
		
		return objResult;
	}
	
	public void doPrioridad() {
		
	}
	
}
