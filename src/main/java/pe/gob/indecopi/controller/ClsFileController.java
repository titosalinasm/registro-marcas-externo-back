package pe.gob.indecopi.controller;

import java.io.IOException;
import java.io.Serializable;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import pe.gob.indecopi.result.ClsDescargarResult;
import pe.gob.indecopi.result.ClsFileUploadResult;
import pe.gob.indecopi.service.ClsFileService;

@CrossOrigin(origins ="*", allowedHeaders = "*", maxAge = 3600)

@RestController
//@CrossOrigin(origins={"*"}, maxAge=3600L)
@RequestMapping({"/file"})
public class ClsFileController implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5890709964527809913L;

	public Logger logger = Logger.getLogger(ClsFileController.class);
	
	@Autowired
	private ClsFileService objConnFileService;

	@RequestMapping(method = RequestMethod.POST, path = "/uploadfile",  produces = "application/json")
	public @ResponseBody ResponseEntity<ClsFileUploadResult> doFileUpload(@RequestParam("fileData") MultipartFile fileData) {
		logger.info("doFileUpload()");
		return ResponseEntity.ok().body(objConnFileService.doFileUpload(fileData)) ;
	}
	
	@RequestMapping(method = RequestMethod.GET, path = "/downloadfile")
	public @ResponseBody ClsDescargarResult doDowloadFile(HttpServletResponse resonse, @RequestParam(defaultValue = "fileName") String fileName) throws IOException {
		logger.info("doDowloadFileTmp()");
		return objConnFileService.doDowloadFile(resonse,  fileName);
	}

}
