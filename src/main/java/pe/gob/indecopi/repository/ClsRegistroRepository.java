package pe.gob.indecopi.repository;

import java.io.Serializable;
import java.sql.Clob;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jdbc.support.oracle.SqlArrayValue;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;


import oracle.jdbc.OracleTypes;
import pe.gob.indecopi.bean.ClsArchivoBean;
import pe.gob.indecopi.bean.ClsClaseSolBean;
import pe.gob.indecopi.bean.ClsExpedienteBean;
import pe.gob.indecopi.bean.ClsParametrosBean;
import pe.gob.indecopi.bean.ClsPersonaBean;
import pe.gob.indecopi.bean.ClsPrioridadBean;
import pe.gob.indecopi.bean.ClsSolicitudBean;
import pe.gob.indecopi.bean.ClsTramiteSELBean;
import pe.gob.indecopi.exception.ClsException;
import pe.gob.indecopi.param.ClsConstantes;
import pe.gob.indecopi.util.ClsResultDAO;
import pe.gob.indecopi.util.ClsUtil;

@Repository
@Transactional
public class ClsRegistroRepository implements Serializable, ClsRegistroRepositoryI{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3789470206482678434L;
	private  Logger logger = Logger.getLogger(ClsRegistroRepository.class);
	
	
	private SimpleJdbcCall simpleJdbcCall;
	
	@Autowired
	private ClsExpedienteRepositoryI objConnExpe;

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	//DataSouser incio multiple
	  @Autowired
	  private DataSource dataSource;
	  
	  @PostConstruct
	  private void init()
	  {
	    this.jdbcTemplate = new JdbcTemplate(this.dataSource);
	  }
	//DataSouser fin multiple
	
	@Autowired
	private ClsResultDAO objResultDAO;
	
	
	@Autowired
	private ClsTramiteSELRepositoryI objConnSEL;
	
	@Autowired
	private ClsParametroRepositoryI objConnParam;
	
	@Override
	public ClsResultDAO doRegistro(ClsSolicitudBean objSolicitud) {

		try {
			//procedure
			this.simpleJdbcCall=new SimpleJdbcCall(jdbcTemplate)
									.withSchemaName(ClsConstantes.SCHEMA_USR_MARCAS)
									.withCatalogName(ClsConstantes.PKG_REG_MARCA)
									.withProcedureName(ClsConstantes.SP_DO_REG_MARCAS)
									.declareParameters(
									new SqlParameter("PIN_ARR_VC_RUTA_S",			 OracleTypes.ARRAY,  "STR_ARRAY"), 	
									new SqlParameter("PIN_ARR_VC_NOM_ORIGINAL_S", 	 OracleTypes.ARRAY,  "STR_ARRAY"), 
									new SqlParameter("PIN_ARR_VC_NOM_FINAL_S", 		 OracleTypes.ARRAY,  "STR_ARRAY"), 
									new SqlParameter("PIN_ARR_VC_EXTENSION_S", 		 OracleTypes.ARRAY,  "STR_ARRAY"), 
									new SqlParameter("PIN_ARR_NU_ID_TIPO_ARCHIVO_S", OracleTypes.ARRAY,  "NUM_ARRAY"),  
									new SqlParameter("PIN_ARR_NU_LONGITUD_S", 		 OracleTypes.ARRAY, "NUM_ARRAY"), 
                       
									new SqlParameter("PIN_ARR_NU_ID_CLASE", OracleTypes.ARRAY, "NUM_ARRAY"),	
									new SqlParameter("PIN_ARR_VC_CLASE"	  , OracleTypes.ARRAY, "STR_ARRAY"),
									
									new SqlParameter("PIN_ARR_VC_IDENTI_A"		, OracleTypes.ARRAY, "STR_ARRAY"),
									new SqlParameter("PIN_ARR_NU_FLAG_TODOS"	, OracleTypes.ARRAY, "NUM_ARRAY"),	
									new SqlParameter("PIN_ARR_VC_CLASES"		, OracleTypes.ARRAY, "STR_ARRAY"),	
									new SqlParameter("PIN_ARR_VC_NRO_SOLICITUD"	, OracleTypes.ARRAY, "STR_ARRAY"),
									new SqlParameter("PIN_ARR_DT_FECHA_PRIORID"	, OracleTypes.ARRAY, "DATE_ARRAY"),
									new SqlParameter("PIN_ARR_NU_ID_PAIS_P"		, OracleTypes.ARRAY, "NUM_ARRAY"),
									new SqlParameter("PIN_ARR_NU_ID_TIPO_PRIO"	, OracleTypes.ARRAY, "NUM_ARRAY"),
									new SqlParameter("PIN_ARR_VC_PROD_SERV"		, OracleTypes.ARRAY, "STR_ARRAY"),
									
									new SqlParameter("PIN_ARR_VC_IDENTI_B"			, OracleTypes.ARRAY, "STR_ARRAY"),
									new SqlParameter("PIN_ARR_VC_RUTA_P"			, OracleTypes.ARRAY, "STR_ARRAY"),	
									new SqlParameter("PIN_ARR_VC_NOM_ORIGINAL_P"	, OracleTypes.ARRAY, "STR_ARRAY"),	
									new SqlParameter("PIN_ARR_VC_NOM_FINAL_P"		, OracleTypes.ARRAY, "STR_ARRAY"),
									new SqlParameter("PIN_ARR_VC_EXTENSION_P"		, OracleTypes.ARRAY, "STR_ARRAY"),
									new SqlParameter("PIN_ARR_NU_ID_TIPO_ARCHIVO_P"	, OracleTypes.ARRAY, "NUM_ARRAY"),
									new SqlParameter("PIN_ARR_NU_LONGITUD_P"		, OracleTypes.ARRAY, "NUM_ARRAY"),	
									
									new SqlParameter("PIN_ARR_VC_IDENTI_C"	  	  ,   OracleTypes.ARRAY, "STR_ARRAY"),
									new SqlParameter("PIN_ARR_NU_TIPO_ORIGEN"	  ,   OracleTypes.ARRAY, "NUM_ARRAY"),
									new SqlParameter("PIN_ARR_NU_TIPO_PERSONA"	  ,   OracleTypes.ARRAY, "NUM_ARRAY"),
									new SqlParameter("PIN_ARR_NU_TIPO_DOCUMENTO"  ,   OracleTypes.ARRAY, "NUM_ARRAY"),
									new SqlParameter("PIN_ARR_VC_DOC_IDENTIDAD" 	, OracleTypes.ARRAY, "STR_ARRAY"),
									new SqlParameter("PIN_ARR_VC_CORREO"		 	, OracleTypes.ARRAY, "STR_ARRAY"),
									new SqlParameter("PIN_ARR_VC_NOMBRES"		  ,   OracleTypes.ARRAY, "STR_ARRAY"),
									new SqlParameter("PIN_ARR_VC_AP_PATERNO"		, OracleTypes.ARRAY, "STR_ARRAY"),
									new SqlParameter("PIN_ARR_VC_AP_MATERNO"		, OracleTypes.ARRAY, "STR_ARRAY"),
									new SqlParameter("PIN_ARR_VC_RAZON_SOCI"		, OracleTypes.ARRAY, "STR_ARRAY"),
									new SqlParameter("PIN_ARR_NU_ID_GENERO"		  ,   OracleTypes.ARRAY, "NUM_ARRAY"),
									new SqlParameter("PIN_ARR_NU_ID_PAIS"		  ,   OracleTypes.ARRAY, "NUM_ARRAY"),
									new SqlParameter("PIN_ARR_NU_ID_PAIS_ORI"     ,   OracleTypes.ARRAY, "NUM_ARRAY"),
									new SqlParameter("PIN_ARR_NU_ID_DEPAR"		  ,   OracleTypes.ARRAY, "NUM_ARRAY"),
									new SqlParameter("PIN_ARR_NU_PROVINCI"		  ,   OracleTypes.ARRAY, "NUM_ARRAY"),
									new SqlParameter("PIN_ARR_NU_DISTRITO"		  ,   OracleTypes.ARRAY, "NUM_ARRAY"),
									new SqlParameter("PIN_ARR_VC_DIRECCION"		  ,   OracleTypes.ARRAY, "STR_ARRAY"),
									new SqlParameter("PIN_ARR_VC_TELEFONO"		  ,   OracleTypes.ARRAY, "STR_ARRAY"),
									new SqlParameter("PIN_ARR_NU_FLAG_TITU_REPRE" ,   OracleTypes.ARRAY, "NUM_ARRAY"),
									new SqlParameter("PIN_ARR_NU_FLAG_SOLICI"	  ,   OracleTypes.ARRAY, "NUM_ARRAY"),
									new SqlParameter("PIN_ARR_VC_NRO_EXP_REP"		  ,   OracleTypes.ARRAY, "STR_ARRAY"),
									new SqlParameter("PIN_ARR_VC_NRO_SOL_REP"		  ,   OracleTypes.ARRAY, "STR_ARRAY"),
									
									new SqlParameter("PIN_ARR_VC_IDENTI_D"	  			,   OracleTypes.ARRAY, "STR_ARRAY"),
									new SqlParameter("PIN_ARR_VC_RUTA_PE"	  			,   OracleTypes.ARRAY, "STR_ARRAY"),
									new SqlParameter("PIN_ARR_VC_NOM_ORIGINAL_PE"		,   OracleTypes.ARRAY, "STR_ARRAY"),
									new SqlParameter("PIN_ARR_VC_NOM_FINAL_PE"	  		,   OracleTypes.ARRAY, "STR_ARRAY"),
									new SqlParameter("PIN_ARR_VC_EXTENSION_PE"	  		,   OracleTypes.ARRAY, "STR_ARRAY"),
									new SqlParameter("PIN_ARR_NU_ID_TIPO_ARCHIVO_PE"	,   OracleTypes.ARRAY, "NUM_ARRAY"),
									new SqlParameter("PIN_ARR_NU_LONGITUD_PE"	  		,   OracleTypes.ARRAY, "NUM_ARRAY"),
								
									
									new  SqlOutParameter("POUT_CL_CUERPO_CORREO", OracleTypes.CLOB ,"CLOB"),
									new  SqlOutParameter("POUT_NU_NRO_EXPEDIENTE", OracleTypes.NUMBER ,"NUMBER"),
									new  SqlOutParameter("POUT_VC_AREA_EXPEDIENTE", OracleTypes.VARCHAR ,"VARCHAR2"),
									new  SqlOutParameter("POUT_NU_ANIO_EXPEDIENTE", OracleTypes.NUMBER ,"NUMBER"),
			                        new  SqlOutParameter("POUT_NU_ESTADO_REGISTRO", OracleTypes.NUMBER ,"NUMBER"),
			                        new  SqlOutParameter("POUT_NU_PROCEDIMIENTO", OracleTypes.NUMBER ,"NUMBER"),
									
									new  SqlOutParameter("POUT_NU_ERROR", OracleTypes.NUMBER ,"NUMBER"),
									new  SqlOutParameter("POUT_VC_ERROR", OracleTypes.VARCHAR ,"VARCHAR2")
									);
			
			//ARCHIVOS DE MI SIGNO
			int nuTamanioLstArchSigno=objSolicitud.getObjDatosSigno().getLstArchivosSigno().size();
			String[]  var_vcRuta			= new String[nuTamanioLstArchSigno];
			String[]  var_vcNomOriginal     = new String[nuTamanioLstArchSigno];
			String[]  var_vcNomFinal        = new String[nuTamanioLstArchSigno];
			String[]  var_vcExtension       = new String[nuTamanioLstArchSigno];
			Integer[] var_nuIdTipoArchivo   = new Integer[nuTamanioLstArchSigno];
			Long[] var_nuLong            	= new Long[nuTamanioLstArchSigno];
			
			for (int i=0; i<nuTamanioLstArchSigno; i++) {
				ClsArchivoBean objArchivo=objSolicitud.getObjDatosSigno().getLstArchivosSigno().get(i);
				var_vcRuta			  [i]= objArchivo.getVcRuta();
				var_vcNomOriginal     [i]= objArchivo.getVcNomOriginal();
				var_vcNomFinal        [i]= objArchivo.getVcNomFinal();
				var_vcExtension       [i]= objArchivo.getVcExtension();
				var_nuIdTipoArchivo   [i]= objArchivo.getNuIdTipoArchivo();
				var_nuLong            [i]= objArchivo.getNuLong();
			}
			
			//CLASES
			int nuTaminioLstClase=objSolicitud.getLstClases().size();
			Integer[] var_nuIdClase = new Integer[nuTaminioLstClase];
			String[]  var_vcClase   = new String[nuTaminioLstClase];
			
			for(int i=0; i<nuTaminioLstClase; i++){
				ClsClaseSolBean objClases=objSolicitud.getLstClases().get(i);
				var_nuIdClase[i]=objClases.getNuIdClase();
				var_vcClase	 [i]=objClases.getVcClase();
			}
			
			//prioridad extranjera
			int nuTamanioPrioridad=objSolicitud.getObjPrioridadExtr().getLstPrioridad().size();
			String[] var_nuIdentificadorA= new String[nuTamanioPrioridad];
			Integer[] var_nuFlagTodos      =new Integer[nuTamanioPrioridad];
		    String[] var_vcNombreClases   =new String[nuTamanioPrioridad];
		    String[] var_vcClases         =new String[nuTamanioPrioridad];
		    String[] var_vcNroSolicitud   =new String[nuTamanioPrioridad];
		    java.sql.Date[] var_dtFechaPrioridad =new java.sql.Date[nuTamanioPrioridad];
		    Integer[] var_nuIdUbigeoPais   =new Integer[nuTamanioPrioridad];
		    Integer[] var_nuTipoPrioridad  =new Integer[nuTamanioPrioridad];
		    String[] var_vcTipoPrioridad  =new String[nuTamanioPrioridad];
		    String[] var_vcProductoServicio=new String[nuTamanioPrioridad];
		    
		    String[]  var_nuIdentificadorB  ;
		    String[]  var_vcRuta_p		    ;
			String[]  var_vcNomOriginal_p   ;
			String[]  var_vcNomFinal_p      ;
			String[]  var_vcExtension_p     ;
			Integer[] var_nuIdTipoArchivo_p ;
			Long[]    var_nuLong_p          ;
			
			List<String>   ar_nuIdentificadorB  = new  ArrayList<String>();
			List<String>   ar_vcRuta_p		   = new  ArrayList<String>();
		    List<String>   ar_vcNomOriginal_p  = new  ArrayList<String>();
		    List<String>   ar_vcNomFinal_p     = new  ArrayList<String>();
			List<String>   ar_vcExtension_p    = new  ArrayList<String>();
			List<Integer>  ar_nuIdTipoArchivo_p= new  ArrayList<Integer>();
			List<Long>     ar_nuLong_p         = new  ArrayList<Long>();
			
			for(int i=0; i<nuTamanioPrioridad; i++){
				ClsPrioridadBean objPrioridad=objSolicitud.getObjPrioridadExtr().getLstPrioridad().get(i);
				var_nuIdentificadorA[i]=ClsUtil.doGenerarNombreArchivoAleatorio();
			    var_nuFlagTodos     [i]= objPrioridad.getNuFlagTodos();
				var_vcNombreClases  [i]= objPrioridad.getVcNombreClases();
				System.out.println("repository objPrioridad.getVcClases(): "+objPrioridad.getVcClases());
				var_vcClases        [i]= objPrioridad.getVcClases();
				var_vcNroSolicitud  [i]= objPrioridad.getVcNroSolicitud();
				var_dtFechaPrioridad[i]= objPrioridad.getDtFechaPrioridad()!=null?new java.sql.Date(objPrioridad.getDtFechaPrioridad().getTime()+1):null;
				var_nuIdUbigeoPais  [i]= objPrioridad.getNuIdUbigeoPais();
				var_nuTipoPrioridad [i]= objPrioridad.getNuTipoPrioridad();
				var_vcTipoPrioridad [i]= objPrioridad.getVcTipoPrioridad();
				var_vcProductoServicio[i]=objPrioridad.getVcProductoServicio();
				int nuTamanioArchP=objPrioridad.getLstArchivoPrioridad().size();
				for(int j=0; j<nuTamanioArchP; j++) {
					ClsArchivoBean objArchivo=objPrioridad.getLstArchivoPrioridad().get(j);
					ar_nuIdentificadorB	  .add(var_nuIdentificadorA[i]);
					ar_vcRuta_p		      .add(objArchivo.getVcRuta()         );
					ar_vcNomOriginal_p    .add(objArchivo.getVcNomOriginal()  );
					ar_vcNomFinal_p       .add(objArchivo.getVcNomFinal()     );
					ar_vcExtension_p      .add(objArchivo.getVcExtension()    );
					ar_nuIdTipoArchivo_p  .add(objArchivo.getNuIdTipoArchivo());
					ar_nuLong_p           .add(objArchivo.getNuLong()         );
				}
				
			}
			
			var_nuIdentificadorB =new  String[ar_nuIdentificadorB.size()];
			var_vcRuta_p		 = new String[ar_nuIdentificadorB.size()];
			var_vcNomOriginal_p  = new String[ar_nuIdentificadorB.size()];
			var_vcNomFinal_p     = new String[ar_nuIdentificadorB.size()];
			var_vcExtension_p    = new String[ar_nuIdentificadorB.size()];
			var_nuIdTipoArchivo_p= new Integer[ar_nuIdentificadorB.size()];                         
			var_nuLong_p         = new Long[ar_nuIdentificadorB.size()];
			
			var_nuIdentificadorB = ar_nuIdentificadorB	 .toArray(var_nuIdentificadorB );
			var_vcRuta_p		 = ar_vcRuta_p		     .toArray(var_vcRuta_p		   );
			var_vcNomOriginal_p  = ar_vcNomOriginal_p    .toArray(var_vcNomOriginal_p  );
			var_vcNomFinal_p     = ar_vcNomFinal_p       .toArray(var_vcNomFinal_p     );
			var_vcExtension_p    = ar_vcExtension_p      .toArray(var_vcExtension_p    );
			var_nuIdTipoArchivo_p= ar_nuIdTipoArchivo_p  .toArray(var_nuIdTipoArchivo_p);
			var_nuLong_p         = ar_nuLong_p           .toArray(var_nuLong_p         );
			
			
			//Persona
			int nuTamanioLstPersona=objSolicitud.getLstPersona().size();
			String[]  var_vcIdentificadorC =new String[nuTamanioLstPersona];
			String[]  var_vcNombres        =new String[nuTamanioLstPersona];
		    String[]  var_vcApPaterno      =new String[nuTamanioLstPersona];
		    String[]  var_vcApMaterno      =new String[nuTamanioLstPersona];
		    String[]  var_vcRazonSocial    =new String[nuTamanioLstPersona];
		    Integer[] var_nuIdTipoOrigen   =new Integer[nuTamanioLstPersona];
		    Integer[] var_nuIdTipoPersona  =new Integer[nuTamanioLstPersona];
		    Integer[] var_nuIdTipoDocumento=new Integer[nuTamanioLstPersona];
		    String[]  var_vcDocIdentidad   =new String[nuTamanioLstPersona];
		    Integer[] var_nuIdPais         =new Integer[nuTamanioLstPersona];
		    Integer[] var_nuIdPaisOri         =new Integer[nuTamanioLstPersona];
		    Integer[] var_nuDepartamento   =new Integer[nuTamanioLstPersona];
		    Integer[] var_nuProvincia      =new Integer[nuTamanioLstPersona];
		    Integer[] var_nuDistrito       =new Integer[nuTamanioLstPersona];
		    String[]  var_vcDireccion       =new String[nuTamanioLstPersona];
		    String[]  var_vcCorreo          =new String[nuTamanioLstPersona];
		    String[]  var_vcTelefono        =new String[nuTamanioLstPersona];
		    Integer[] var_nuFlagSolicitante    =new Integer[nuTamanioLstPersona];
		    Integer[] var_nuFlagTituRepre      =new Integer[nuTamanioLstPersona];
		    Integer[] var_nuGenero			   =new Integer[nuTamanioLstPersona];
		    
		    String[]  var_vcNroExpediente        =new String[nuTamanioLstPersona];
		    String[]  var_vcNroSolRealizado      =new String[nuTamanioLstPersona];
		    
		    //Archivos de representantes
		    String[]  var_vcIdentificadorD  ;
		    String[]  var_vcRuta_pe		    ;
			String[]  var_vcNomOriginal_pe   ;
			String[]  var_vcNomFinal_pe     ;
			String[]  var_vcExtension_pe     ;
			Integer[] var_nuIdTipoArchivo_pe ;
			Long[]    var_nuLong_pe          ;
			
			List<String>   ar_vcIdentificadorD  = new  ArrayList<String>();
			List<String>   ar_vcRuta_pe		   = new  ArrayList<String>();
		    List<String>   ar_vcNomOriginal_pe  = new  ArrayList<String>();
		    List<String>   ar_vcNomFinal_pe    = new  ArrayList<String>();
			List<String>   ar_vcExtension_pe    = new  ArrayList<String>();
			List<Integer>  ar_nuIdTipoArchivo_pe= new  ArrayList<Integer>();
			List<Long>     ar_nuLong_pe         = new  ArrayList<Long>();
		    
		    
			for(int i=0; i<nuTamanioLstPersona; i++) {
				ClsPersonaBean objPersona= objSolicitud.getLstPersona().get(i);
				var_vcIdentificadorC	  [i]=ClsUtil.doGenerarNombreArchivoAleatorio();
				var_vcNombres         [i]=objPersona.getVcNombres();
				var_vcApPaterno       [i]=objPersona.getVcApPaterno();
				var_vcApMaterno       [i]=objPersona.getVcApMaterno();
				var_vcRazonSocial     [i]=objPersona.getVcRazonSocial();
				var_nuIdTipoOrigen    [i]=objPersona.getNuIdTipoOrigen();
				var_nuIdTipoPersona   [i]=objPersona.getNuIdTipoPersona();
				var_nuIdTipoDocumento [i]=objPersona.getNuIdTipoDocumento();
				var_vcDocIdentidad    [i]=objPersona.getVcDocIdentidad();
				var_nuIdPaisOri		  [i]=objPersona.getNuIdPaisOri();
				var_nuIdPais          [i]=objPersona.getNuIdPais();
				var_nuDepartamento    [i]=objPersona.getNuDepartamento();
				var_nuProvincia       [i]=objPersona.getNuProvincia();
				var_nuDistrito        [i]=objPersona.getNuDistrito();
				var_vcDireccion        [i]=objPersona.getVcDireccion();
				var_vcCorreo           [i]=objPersona.getVcCorreo();
				var_vcTelefono         [i]=objPersona.getVcTelefono();
				var_nuFlagSolicitante     [i]=objPersona.getNuFlagSolicitante();
				var_nuFlagTituRepre       [i]=objPersona.getNuFlagTituRepre();
				var_nuGenero			  [i]=objPersona.getNuGenero();
				var_vcNroExpediente		[i]=objPersona.getVcNroExpediente();
				var_vcNroSolRealizado   [i]=objPersona.getVcNroSolicitud();
				
				int nuTamanioArchPe=objPersona.getLstArchivoRepresentante().size();
				for(int j=0; j<nuTamanioArchPe; j++) {
					
					ClsArchivoBean objArchivo=objPersona.getLstArchivoRepresentante().get(j);
					ar_vcIdentificadorD	   .add(var_vcIdentificadorC	  	[i]);
					ar_vcRuta_pe		   .add(objArchivo.getVcRuta()         );
					ar_vcNomOriginal_pe    .add(objArchivo.getVcNomOriginal()  );
					ar_vcNomFinal_pe       .add(objArchivo.getVcNomFinal()     );
					ar_vcExtension_pe      .add(objArchivo.getVcExtension()    );
					ar_nuIdTipoArchivo_pe  .add(objArchivo.getNuIdTipoArchivo());
					ar_nuLong_pe           .add(objArchivo.getNuLong()         );
				}	
			}
			
			var_vcIdentificadorD =new  String[ar_vcIdentificadorD.size()];
			var_vcRuta_pe		 = new String[ar_vcIdentificadorD.size()];
			var_vcNomOriginal_pe  = new String[ar_vcIdentificadorD.size()];
			var_vcNomFinal_pe     = new String[ar_vcIdentificadorD.size()];
			var_vcExtension_pe    = new String[ar_vcIdentificadorD.size()];
			var_nuIdTipoArchivo_pe= new Integer[ar_vcIdentificadorD.size()];                         
			var_nuLong_pe         = new Long[ar_vcIdentificadorD.size()];
			
			var_vcIdentificadorD = ar_vcIdentificadorD	 .toArray(var_vcIdentificadorD );
			var_vcRuta_pe		 = ar_vcRuta_pe		     .toArray(var_vcRuta_pe		   );
			var_vcNomOriginal_pe  = ar_vcNomOriginal_pe    .toArray(var_vcNomOriginal_pe  );
			var_vcNomFinal_pe     = ar_vcNomFinal_pe       .toArray(var_vcNomFinal_pe     );
			var_vcExtension_pe    = ar_vcExtension_pe      .toArray(var_vcExtension_pe    );
			var_nuIdTipoArchivo_pe= ar_nuIdTipoArchivo_pe  .toArray(var_nuIdTipoArchivo_pe);
			var_nuLong_pe         = ar_nuLong_pe           .toArray(var_nuLong_pe         );
			
			
			
			 Map<String, Object> inParamMap = new HashMap();
			 
			
		     inParamMap.put("PIN_VC_USUARIO"			, objSolicitud.getVcUsuario());
		     inParamMap.put("PIN_VC_HOSTNAME"			, objSolicitud.getVcHostName());
		     
		     inParamMap.put("PIN_NU_FLAG_ASESORIA"		, objSolicitud.getObjAsesoria().getNuFlagAsesoria());
		     inParamMap.put("PIN_VC_NRO_ASESORIA"		, objSolicitud.getObjAsesoria().getVcNroAsesoria());
		     //MI SIGNO	     
		     inParamMap.put("PIN_NU_ID_TIPO_SOLICITUD"	, objSolicitud.getObjDatosSigno().getNuIdTipoSolicitud());
		     inParamMap.put("PIN_VC_DENOMINACION"		, objSolicitud.getObjDatosSigno().getVcDenominacion());
		     inParamMap.put("PIN_NU_FLAG_INT_REAL"		, objSolicitud.getObjDatosSigno().getNuFlagInteresReal());
		     inParamMap.put("PIN_VC_INT_NRO_EXPE"		, objSolicitud.getObjDatosSigno().getVcNroExpediente());
		     inParamMap.put("PIN_NU_FLAG_DER_PREF"		, objSolicitud.getObjDatosSigno().getNuFlaDerechoPreferente());
		     inParamMap.put("PIN_VC_DER_NRO_CERTI"		, objSolicitud.getObjDatosSigno().getVcNroCertificado());
		     inParamMap.put("PIN_NU_ID_TIPO_SIGNO"		, objSolicitud.getObjDatosSigno().getNuIdTipoSigno());
		     inParamMap.put("PIN_NU_FLAG_REIN_COL"		, objSolicitud.getObjDatosSigno().getNuFlagReinvindicaCol());
		     
		     inParamMap.put("PIN_VC_CERTIFICADO_LEMA"			, objSolicitud.getObjDatosSigno().getVcCertificadoLema());
		     inParamMap.put("PIN_VC_NRO_EXPEDIENTE_LEMA"		, objSolicitud.getObjDatosSigno().getVcNroExpedienteLema());
		     inParamMap.put("PIN_NU_CLASE_LEMA"					, objSolicitud.getObjDatosSigno().getNuClaseLema());
		     inParamMap.put("PIN_DT_FEHA_NOM_COME"				, objSolicitud.getObjDatosSigno().getVcFechaPrimerUsoNombreComercial()!=null?new java.sql.Date(objSolicitud.getObjDatosSigno().getVcFechaPrimerUsoNombreComercial().getTime()+1):null);
		     inParamMap.put("PIN_VC_NRO_EXPE_NOM_COME"			, objSolicitud.getObjDatosSigno().getVcNroExpedienteNombreComercial());
		     
		     
		     
		     //ARCHIVOS DE LOS SIGNOS
		     inParamMap.put("PIN_ARR_VC_RUTA_S"				, new SqlArrayValue<String>(var_vcRuta, "STR_ARRAY"));
		     inParamMap.put("PIN_ARR_VC_NOM_ORIGINAL_S"		, new SqlArrayValue<String>(var_vcNomOriginal, "STR_ARRAY"));
		     inParamMap.put("PIN_ARR_VC_NOM_FINAL_S"		, new SqlArrayValue<String>(var_vcNomFinal, "STR_ARRAY"));
		     inParamMap.put("PIN_ARR_VC_EXTENSION_S"		, new SqlArrayValue<String>(var_vcExtension, "STR_ARRAY"));
		     inParamMap.put("PIN_ARR_NU_ID_TIPO_ARCHIVO_S"	, new SqlArrayValue<Integer>(var_nuIdTipoArchivo, "NUM_ARRAY"));
		     inParamMap.put("PIN_ARR_NU_LONGITUD_S"			, new SqlArrayValue<Long>(var_nuLong, "NUM_ARRAY"));

		     //CLASE
		     inParamMap.put("PIN_ARR_NU_ID_CLASE"			, new SqlArrayValue<Integer>(var_nuIdClase, "NUM_ARRAY"));
		     inParamMap.put("PIN_ARR_VC_CLASE"				, new SqlArrayValue<String>(var_vcClase, "STR_ARRAY"));
		    
		     //PRIORIDAD EXTRANJERA
		     inParamMap.put("PIN_NU_FLAG_PRIO_EXTRA"	, objSolicitud.getObjPrioridadExtr().getNuFlagPrioridadExtr());
		     inParamMap.put("PIN_ARR_VC_IDENTI_A"		, new SqlArrayValue<String>(var_nuIdentificadorA, "STR_ARRAY"));
		     inParamMap.put("PIN_ARR_NU_FLAG_TODOS"		, new SqlArrayValue<Integer>(var_nuFlagTodos, "NUM_ARRAY"));
		     inParamMap.put("PIN_ARR_VC_CLASES"			, new SqlArrayValue<String>(var_vcClases, "STR_ARRAY"));
		     inParamMap.put("PIN_ARR_VC_NRO_SOLICITUD"	, new SqlArrayValue<String>(var_vcNroSolicitud, "STR_ARRAY"));
		     inParamMap.put("PIN_ARR_DT_FECHA_PRIORID"	, new SqlArrayValue<Date>(var_dtFechaPrioridad, "DATE_ARRAY"));
		     inParamMap.put("PIN_ARR_NU_ID_PAIS_P"		, new SqlArrayValue<Integer>(var_nuIdUbigeoPais, "NUM_ARRAY"));
		     inParamMap.put("PIN_ARR_NU_ID_TIPO_PRIO"	, new SqlArrayValue<Integer>(var_nuTipoPrioridad, "NUM_ARRAY"));
		     inParamMap.put("PIN_ARR_VC_PROD_SERV"		, new SqlArrayValue<String>(var_vcProductoServicio, "STR_ARRAY"));
		    
		     //ARCHIVOS DE PRIORIDAD EXTRANJERA
		     inParamMap.put("PIN_ARR_VC_IDENTI_B"			, new SqlArrayValue<String>(var_nuIdentificadorB, "STR_ARRAY"));
		     inParamMap.put("PIN_ARR_VC_RUTA_P"				, new SqlArrayValue<String>(var_vcRuta_p, "STR_ARRAY"));
		     inParamMap.put("PIN_ARR_VC_NOM_ORIGINAL_P"		, new SqlArrayValue<String>(var_vcNomOriginal_p, "STR_ARRAY"));
		     inParamMap.put("PIN_ARR_VC_NOM_FINAL_P"		, new SqlArrayValue<String>(var_vcNomFinal_p, "STR_ARRAY"));
		     inParamMap.put("PIN_ARR_VC_EXTENSION_P"		, new SqlArrayValue<String>(var_vcExtension_p, "STR_ARRAY"));
		     inParamMap.put("PIN_ARR_NU_ID_TIPO_ARCHIVO_P"	, new SqlArrayValue<Integer>(var_nuIdTipoArchivo_p, "NUM_ARRAY"));
		     inParamMap.put("PIN_ARR_NU_LONGITUD_P"			, new SqlArrayValue<Long>(var_nuLong_p, "NUM_ARRAY"));
		     
		     //DATOS DE LA PERSONA
		     inParamMap.put("PIN_ARR_VC_IDENTI_C" 	 	 , new SqlArrayValue<String>(var_vcIdentificadorC, "STR_ARRAY"));
		     inParamMap.put("PIN_ARR_NU_TIPO_ORIGEN"	 , new SqlArrayValue<Integer>(var_nuIdTipoOrigen, "NUM_ARRAY"));
		     inParamMap.put("PIN_ARR_NU_TIPO_PERSONA"	 , new SqlArrayValue<Integer>(var_nuIdTipoPersona, "NUM_ARRAY"));
		     inParamMap.put("PIN_ARR_NU_TIPO_DOCUMENTO"  , new SqlArrayValue<Integer>(var_nuIdTipoDocumento, "NUM_ARRAY"));
		     inParamMap.put("PIN_ARR_VC_DOC_IDENTIDAD" 	 , new SqlArrayValue<String>(var_vcDocIdentidad, "STR_ARRAY"));
		     inParamMap.put("PIN_ARR_VC_CORREO"		 	 , new SqlArrayValue<String>(var_vcCorreo, "STR_ARRAY"));
		     inParamMap.put("PIN_ARR_VC_NOMBRES"		 , new SqlArrayValue<String>(var_vcNombres, "STR_ARRAY"));
		     inParamMap.put("PIN_ARR_VC_AP_PATERNO"		 , new SqlArrayValue<String>(var_vcApPaterno, "STR_ARRAY"));
		     inParamMap.put("PIN_ARR_VC_AP_MATERNO"		 , new SqlArrayValue<String>(var_vcApMaterno, "STR_ARRAY"));
		     inParamMap.put("PIN_ARR_VC_RAZON_SOCI"		 , new SqlArrayValue<String>(var_vcRazonSocial, "STR_ARRAY"));
		     inParamMap.put("PIN_ARR_NU_ID_GENERO"		 , new SqlArrayValue<Integer>(var_nuGenero, "NUM_ARRAY"));
		     inParamMap.put("PIN_ARR_NU_ID_PAIS"		 , new SqlArrayValue<Integer>(var_nuIdPais, "NUM_ARRAY"));
		     inParamMap.put("PIN_ARR_NU_ID_PAIS_ORI"	 , new SqlArrayValue<Integer>(var_nuIdPaisOri, "NUM_ARRAY"));
		     inParamMap.put("PIN_ARR_NU_ID_DEPAR"		 , new SqlArrayValue<Integer>(var_nuDepartamento, "NUM_ARRAY"));
		     inParamMap.put("PIN_ARR_NU_PROVINCI"		 , new SqlArrayValue<Integer>(var_nuProvincia, "NUM_ARRAY"));
		     inParamMap.put("PIN_ARR_NU_DISTRITO"		 , new SqlArrayValue<Integer>(var_nuDistrito, "NUM_ARRAY"));
		     inParamMap.put("PIN_ARR_VC_DIRECCION"		 , new SqlArrayValue<String>(var_vcDireccion, "STR_ARRAY"));
		     inParamMap.put("PIN_ARR_VC_TELEFONO"		 , new SqlArrayValue<String>(var_vcTelefono, "STR_ARRAY"));
		     inParamMap.put("PIN_ARR_NU_FLAG_TITU_REPRE" , new SqlArrayValue<Integer>(var_nuFlagTituRepre, "NUM_ARRAY"));
		     inParamMap.put("PIN_ARR_NU_FLAG_SOLICI"	 , new SqlArrayValue<Integer>(var_nuFlagSolicitante, "NUM_ARRAY"));
		     inParamMap.put("PIN_ARR_VC_NRO_EXP_REP"	 , new SqlArrayValue<String>(var_vcNroExpediente, "STR_ARRAY"));
		     inParamMap.put("PIN_ARR_VC_NRO_SOL_REP"	 , new SqlArrayValue<String>(var_vcNroSolRealizado, "STR_ARRAY"));
		     //ARCHIVOS DE LOS REPRESENTANTES LEGALES
		     inParamMap.put("PIN_ARR_VC_IDENTI_D"			, new SqlArrayValue<String>(var_vcIdentificadorD, "STR_ARRAY"));
		     inParamMap.put("PIN_ARR_VC_RUTA_PE"				, new SqlArrayValue<String>(var_vcRuta_pe, "STR_ARRAY"));
		     inParamMap.put("PIN_ARR_VC_NOM_ORIGINAL_PE"		, new SqlArrayValue<String>(var_vcNomOriginal_pe, "STR_ARRAY"));
		     inParamMap.put("PIN_ARR_VC_NOM_FINAL_PE"		, new SqlArrayValue<String>(var_vcNomFinal_pe, "STR_ARRAY"));
		     inParamMap.put("PIN_ARR_VC_EXTENSION_PE"		, new SqlArrayValue<String>(var_vcExtension_pe, "STR_ARRAY"));
		     inParamMap.put("PIN_ARR_NU_ID_TIPO_ARCHIVO_PE"	, new SqlArrayValue<Integer>(var_nuIdTipoArchivo_pe, "NUM_ARRAY"));
		     inParamMap.put("PIN_ARR_NU_LONGITUD_PE"			, new SqlArrayValue<Long>(var_nuLong_pe, "NUM_ARRAY"));
		     
		     
		     
		     
			//execute
			Map<String, Object> out = this.simpleJdbcCall.execute(inParamMap);
			
			//response
			objResultDAO.put("POUT_NU_ID_SOLICITUD", 	out.get("POUT_NU_ID_SOLICITUD"));
			objResultDAO.put("POUT_NU_ERROR", 			out.get("POUT_NU_ERROR"));
			objResultDAO.put("POUT_VC_ERROR", 			out.get("POUT_VC_ERROR"));
			
			System.out.println("vcError: "+out.get("POUT_VC_ERROR"));
			System.out.println("NUM SOLI: "+Integer.parseInt(out.get("POUT_NU_ID_SOLICITUD")+""));
			System.out.println("COD ERROR: "+Integer.parseInt(out.get("POUT_NU_ERROR")+""));
			

			if(Integer.parseInt(out.get("POUT_NU_ERROR")+"")==0) { //CREA LA SOLICITUD
				ClsExpedienteBean objExpe= new ClsExpedienteBean();
				objExpe.setNuIdSolicitud(Integer.parseInt(out.get("POUT_NU_ID_SOLICITUD")+""));
				
				objResultDAO=objConnExpe.doExpediente(); 
				if(Integer.parseInt(out.get("POUT_NU_ERROR")+"")==0) {//SE CREO EL EXPEDIENTE
					objExpe.setNuNroExpediente(Integer.parseInt(objResultDAO.get("POUT_NU_NRO_EXPEDIENTE")+""));
					objExpe.setNuAnioExpediente(Integer.parseInt(objResultDAO.get("POUT_NU_ANIO_EXPEDIENTE")+""));
					objExpe.setVcAreaExpediente(objResultDAO.get("POUT_VC_AREA_EXPEDIENTE")+"");
					System.out.println("objExpe.getNuNroExpediente():"+objExpe.getNuNroExpediente());
					
					objResultDAO=this.inExpediente(objExpe, objSolicitud);
					System.out.println("mensaje de error:"+objResultDAO.get("POUT_VC_ERROR"));
					if(Integer.parseInt(objResultDAO.get("POUT_NU_ERROR")+"")==0) {//SE GUARDA EL EXPEDIENTE EN USR_MARCAS
						System.out.println("hasta lleg??");
					
					objResultDAO=this.doMigraSSE(objExpe, objSolicitud);
					if(Integer.parseInt(objResultDAO.get("POUT_NU_ERROR")+"")==0) {//SI SE MIGRO CORRECTAMENTE
					
						ClsTramiteSELBean objTramite=new ClsTramiteSELBean();
						
						objTramite.setClDetTramite(			ClsUtil.clobToString((Clob)objResultDAO.get("POUT_CL_CUERPO_CORREO")));
						objTramite.setVcNroExpediente(		objResultDAO.get("POUT_NU_NRO_EXPEDIENTE")+"");
						objTramite.setVcSiglaExpediente(	""+objResultDAO.get("POUT_VC_AREA_EXPEDIENTE"));
						objTramite.setVcAnioExpediente(		""+objResultDAO.get("POUT_NU_ANIO_EXPEDIENTE"));
						objTramite.setNuIdEstadoTramite(	Integer.parseInt(""+objResultDAO.get("POUT_NU_ESTADO_REGISTRO")));
						objTramite.setNuIdProcedimiento(	Integer.parseInt(""+objResultDAO.get("POUT_NU_PROCEDIMIENTO")));
						
						ClsParametrosBean objParam=new ClsParametrosBean();
						objResultDAO=objConnParam.doParametro();
						objParam=(ClsParametrosBean)objResultDAO.get("POUT_PARAMETRO");
						objParam.setNuCantClases(objSolicitud.getLstClases().size());
			
						
						objResultDAO=objConnSEL.doTramite(objTramite, objSolicitud, objParam, objSolicitud.getVcUsuario());
						if(Integer.parseInt(objResultDAO.get("POUT_NU_ERROR")+"")==0) {//SI SE MIGRO CORRECTAMENTE
							System.out.println("Se registro correctamente");
						}else {
							throw new ClsException(objResultDAO.get("POUT_VC_ERROR")+"", new Throwable("ERROR"));
						}
					
					}else {
						
						throw new ClsException(objResultDAO.get("POUT_VC_ERROR")+"", new Throwable("ERROR"));
					}
					}else {
						throw new ClsException(objResultDAO.get("POUT_VC_ERROR")+"", new Throwable("ERROR"));
					}
				}else {
					throw new ClsException(objResultDAO.get("POUT_VC_ERROR")+"", new Throwable("ERROR"));
				}
				
			}else {
				throw new ClsException(objResultDAO.get("POUT_VC_ERROR")+"", new Throwable("ERROR"));
			}
			
								    		
		}catch(Exception e) {
			e.printStackTrace();
			e.getMessage();
			throw new ClsException(e+"", new Throwable("ERROR"));
		}
		
		return objResultDAO;
	}
	
	
	@Override
	public ClsResultDAO inExpediente(ClsExpedienteBean objExpediente, ClsSolicitudBean objSolicitud) {

		try {
			//procedure
			this.simpleJdbcCall=new SimpleJdbcCall(jdbcTemplate)
									.withSchemaName(ClsConstantes.SCHEMA_USR_MARCAS)
									.withCatalogName(ClsConstantes.PKG_REG_MARCA)
									.withProcedureName(ClsConstantes.SP_I_EXPEDIENTE)
									.declareParameters(
									
									new  SqlOutParameter("POUT_NU_ERROR", OracleTypes.NUMBER ,"NUMBER"),
									new  SqlOutParameter("POUT_VC_ERROR", OracleTypes.VARCHAR ,"VARCHAR2")
									);
			
			
			
			 Map<String, Object> inParamMap = new HashMap();
			 
		     inParamMap.put("PIN_VC_USUARIO"			, objSolicitud.getVcUsuario());
		     inParamMap.put("PIN_VC_HOSTNAME"			, objSolicitud.getVcHostName());

		     inParamMap.put("PIN_NU_ID_SOLICITUD"			, objExpediente.getNuIdSolicitud());
		     inParamMap.put("PIN_NU_NRO_EXPEDIENTE"			, objExpediente.getNuNroExpediente());
		     inParamMap.put("PIN_NU_ANIO_EXPEDIENTE"		, objExpediente.getNuAnioExpediente());
		     inParamMap.put("PIN_VC_AREA_EXPEDIENTE"		, objExpediente.getVcAreaExpediente());
		     
			//execute
			Map<String, Object> out = this.simpleJdbcCall.execute(inParamMap);
			
			//response
			
			objResultDAO.put("POUT_NU_ERROR", 			out.get("POUT_NU_ERROR"));
			objResultDAO.put("POUT_VC_ERROR", 			out.get("POUT_VC_ERROR"));
								    		
		}catch(Exception e) {
			e.printStackTrace();
			throw new ClsException(e+"", new Throwable("ERROR1"));
		}
		
		return objResultDAO;
	}
	
	@Override
	public ClsResultDAO doMigraSSE(ClsExpedienteBean objExpediente, ClsSolicitudBean objSolicitud) {

		try {
			//procedure
			this.simpleJdbcCall=new SimpleJdbcCall(jdbcTemplate)
									.withSchemaName(ClsConstantes.SCHEMA_USR_MARCAS)
									.withCatalogName(ClsConstantes.PKG_REG_MARCA)
									.withProcedureName(ClsConstantes.SP_DO_MIGRA_MAR_SSE)
									.declareParameters(
								    new  SqlOutParameter("POUT_CL_CUERPO_CORREO", OracleTypes.CLOB ,"CLOB"),
									new  SqlOutParameter("POUT_NU_NRO_EXPEDIENTE", OracleTypes.VARCHAR ,"VARCHAR2"),     
									new  SqlOutParameter("POUT_NU_ERROR", OracleTypes.NUMBER ,"NUMBER"),
									new  SqlOutParameter("POUT_VC_ERROR", OracleTypes.VARCHAR ,"VARCHAR2")
									);
			
			
			
			 Map<String, Object> inParamMap = new HashMap();
			 
		     inParamMap.put("PIN_VC_USUARIO"			, objSolicitud.getVcUsuario());
		     inParamMap.put("PIN_VC_HOSTNAME"			, objSolicitud.getVcHostName());

		     inParamMap.put("PIN_NU_ID_SOLICITUD"			, objExpediente.getNuIdSolicitud());
		     
			//execute
			Map<String, Object> out = this.simpleJdbcCall.execute(inParamMap);
			System.out.println("DAO Migra: "+objExpediente.getNuIdSolicitud());
			
			//response
			
            objResultDAO.put("POUT_CL_CUERPO_CORREO", 			out.get("POUT_CL_CUERPO_CORREO"));
 			objResultDAO.put("POUT_NU_NRO_EXPEDIENTE", 			out.get("POUT_NU_NRO_EXPEDIENTE"));
 			
 			objResultDAO.put("POUT_VC_AREA_EXPEDIENTE", 			out.get("POUT_VC_AREA_EXPEDIENTE"));
 			objResultDAO.put("POUT_NU_ANIO_EXPEDIENTE", 			out.get("POUT_NU_ANIO_EXPEDIENTE"));
 			objResultDAO.put("POUT_NU_ESTADO_REGISTRO", 			out.get("POUT_NU_ESTADO_REGISTRO"));
 			objResultDAO.put("POUT_NU_PROCEDIMIENTO", 			out.get("POUT_NU_PROCEDIMIENTO"));
 			
			objResultDAO.put("POUT_NU_ERROR", 			out.get("POUT_NU_ERROR"));
			objResultDAO.put("POUT_VC_ERROR", 			out.get("POUT_VC_ERROR"));
								    		
		}catch(Exception e) {
			e.printStackTrace();
			throw new ClsException(e+"", new Throwable("ERROR1"));
		}
		
		return objResultDAO;
	}
	
	
	
}
