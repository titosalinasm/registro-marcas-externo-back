package pe.gob.indecopi.service;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;

import pe.gob.indecopi.result.ClsDescargarResult;
import pe.gob.indecopi.result.ClsFileUploadResult;

public interface  ClsFileService {
	public 	ClsFileUploadResult doFileUpload(MultipartFile fileData);
	public ClsDescargarResult doDowloadFile(HttpServletResponse resonse,
			String fileName);
}
