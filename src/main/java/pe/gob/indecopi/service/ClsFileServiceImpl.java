package pe.gob.indecopi.service;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import pe.gob.indecopi.bean.ClsParametrosBean;
import pe.gob.indecopi.exception.ClsException;
import pe.gob.indecopi.repository.ClsParametroRepositoryI;
import pe.gob.indecopi.repository.ClsTramiteSELRepositoryI;
import pe.gob.indecopi.result.ClsDescargarResult;
import pe.gob.indecopi.result.ClsFileUploadResult;
import pe.gob.indecopi.util.ClsConfigFilePath;
import pe.gob.indecopi.util.ClsMediaTypeUtils;
import pe.gob.indecopi.util.ClsResultDAO;
import pe.gob.indecopi.util.ClsUtil;

@Service
public class ClsFileServiceImpl implements Serializable, ClsFileService{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Logger logger = Logger.getLogger(ClsFileServiceImpl.class);
	
	@Autowired
	ClsConfigFilePath propiedades;
	
	@Autowired
	private ClsParametroRepositoryI objConnParam;
	
	
	@Autowired
	private ClsResultDAO objResultDAO;
	
	@Autowired
	private ServletContext servletContext;
	
public 	ClsFileUploadResult doFileUpload(MultipartFile fileData) {
	ClsFileUploadResult objFileUpload=new ClsFileUploadResult();
	try {
		if(fileData.getSize()<=1024*1024*10) {
			
			ClsParametrosBean objParam=new ClsParametrosBean();
			objResultDAO=objConnParam.doParametro();
			objParam=(ClsParametrosBean)objResultDAO.get("POUT_PARAMETRO");
			
		String vcRutaTmp=objParam.getVcRutaTemporal();
		String vcNomGenerado="doc_"+ClsUtil.doGenerarNombreArchivoAleatorio();
		//Extension
		StringBuilder sb1=new StringBuilder(fileData.getOriginalFilename());
		String vcExtensionTmp=sb1.reverse().toString().split("\\.")[0];
		StringBuilder sb2=new StringBuilder(vcExtensionTmp);
		String vcExtension=sb2.reverse().toString();
		
		String vcNomOriginal=fileData.getOriginalFilename().substring(0,fileData.getOriginalFilename().lastIndexOf("."));
		
		Long   nuLong=fileData.getSize();
	
		//Path para el archivo
		Path filePath = Paths.get(vcRutaTmp+vcNomGenerado+"."+vcExtension);
		
		Files.copy(fileData.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
		
		objFileUpload.getObjArchivo().setVcRuta(vcRutaTmp);
		objFileUpload.getObjArchivo().setVcNomOriginal(vcNomOriginal);
		objFileUpload.getObjArchivo().setVcNomFinal(vcNomGenerado);
		objFileUpload.getObjArchivo().setVcExtension(vcExtension);
		objFileUpload.getObjArchivo().setNuLong(nuLong);
		objFileUpload.getObjArchivo().setNuIdRepositorio(1);
		}else {
			//objFileUpload.setObjArchivo(null);
			//objFileUpload.setNuError(-1L);
			
			if((fileData.getSize()/(1024*1024))==10){ 
				throw new ClsException("Lo sentimos su archivo es demasiado grande. Máximo 10MB , actualmente tiene "+(fileData.getSize()/(1024*1024))+"MB", new Throwable("ERROR"));
			//objFileUpload.setVcError("Lo sentimos su archivo es demasiado grande. Máximo 10MB , actualmente tiene "+(fileData.getSize()/(1024*1024))+"MB");
			}else {
				//objFileUpload.setVcError("Lo sentimos su archivo es demasiado grande. Máximo 10MB");
				throw new ClsException("Lo sentimos su archivo es demasiado grande. Máximo 10MB", new Throwable("ERROR"));
			}
		}
		
	}catch(Exception e) {
		e.printStackTrace();
		//objFileUpload.setObjArchivo(null);
		//objFileUpload.setNuError(-1L);
		throw new ClsException(""+e, new Throwable("ERROR"));
	}
	
	return objFileUpload;

}


public ClsDescargarResult doDowloadFile(HttpServletResponse resonse,
										String fileName){
	  logger.info("doDowloadFile()");
	  ClsDescargarResult objRespuesta=new ClsDescargarResult();
	  objRespuesta.setNuError(0L);
	  objRespuesta.setVcError("");
	  try {
	  MediaType mediaType = ClsMediaTypeUtils.getMediaTypeForFileName(this.servletContext, fileName);
      logger.info("fileName: " + fileName);
      logger.info("mediaType: " + mediaType);
      
      File file = new File(System.getProperty("java.io.tmpdir")+ fileName);
  
      String vcExtension=file.getName().split("\\.")[1];
      
   // Content-Type
      //ejemplo
      // application/pdf
      resonse.setContentType(mediaType.getType());
      
   // Content-Disposition
      resonse.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" 
    		  											  +"INDECOPI-"+ClsUtil.doGenerarNombreArchivoAleatorio() //Nombre generado
    		  											  +"."+vcExtension); //Extension

      // Content-Length
      resonse.setContentLength((int) file.length());

      
      BufferedInputStream inStream;
      BufferedOutputStream outStream;

		inStream = new BufferedInputStream(new FileInputStream(file));
		outStream = new BufferedOutputStream(resonse.getOutputStream());
	      byte[] buffer = new byte[1024];
	      int bytesRead = 0;
	      while ((bytesRead = inStream.read(buffer)) != -1) {
	          outStream.write(buffer, 0, bytesRead);
	      	}
	      outStream.flush();
	      inStream.close();
	      
	  }catch( Exception e) {
		  objRespuesta.setNuError(-1L);
		  objRespuesta.setVcError("Error "+e);
	      }
	      
	      return objRespuesta;

}
	

}
