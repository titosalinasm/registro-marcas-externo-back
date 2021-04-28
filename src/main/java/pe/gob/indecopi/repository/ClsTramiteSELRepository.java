package pe.gob.indecopi.repository;

import java.io.Serializable;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jdbc.support.oracle.SqlArrayValue;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import oracle.jdbc.OracleTypes;
import oracle.sql.ARRAY;
import oracle.sql.ArrayDescriptor;
import pe.gob.indecopi.bean.ClsArchivoBean;
import pe.gob.indecopi.bean.ClsFilterPagoBean;
import pe.gob.indecopi.bean.ClsFiltroProcedimientoBean;
import pe.gob.indecopi.bean.ClsFiltroTramitePagoBean;
import pe.gob.indecopi.bean.ClsPagoBean;
import pe.gob.indecopi.bean.ClsParametrosBean;
import pe.gob.indecopi.bean.ClsSolicitudBean;
import pe.gob.indecopi.bean.ClsTramiteSELBean;
import pe.gob.indecopi.bean.ClsUsuarioBean;
import pe.gob.indecopi.param.ClsConstantes;
import pe.gob.indecopi.util.ClsResultDAO;
import pe.gob.indecopi.util.ClsUtil;

@Repository
@Transactional("transactionManagerSEL")
public class ClsTramiteSELRepository implements Serializable, ClsTramiteSELRepositoryI{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2979164248472593922L;

	private  Logger logger = Logger.getLogger(ClsTramiteSELRepository.class);
	
	private SimpleJdbcCall simpleJdbcCall;

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	//DataSouser incio multiple
	  @Autowired
	  @Qualifier("dataSourceSEL")
	  private DataSource dataSourceSEL;
	  
	  @PostConstruct
	  private void init()
	  {
	    this.jdbcTemplate = new JdbcTemplate(this.dataSourceSEL);
	  }
	//DataSouser fin multiple
	
	@Autowired
	private ClsResultDAO objResultDAO;
	

	  @Override
	  public ClsResultDAO doUsuario( ClsUsuarioBean objUsuario)
	  {
		  logger.info("doUsuario()");
	    try
	    {
	   
	      this.simpleJdbcCall = new SimpleJdbcCall(this.jdbcTemplate)
	    		  					.withSchemaName(ClsConstantes.SCHEMA_USR_SEL)
	    		  					.withCatalogName(ClsConstantes.PKG_SEL_MARCAS)
	    		  					.withProcedureName(ClsConstantes.SP_GET_USUARIO)
	    		  					.declareParameters(	
	    		  							
	    		  					new  SqlOutParameter("POUT_NU_ID_TIPO_ORIGEN", OracleTypes.NUMBER ,"NUMBER"),
	    		  					new  SqlOutParameter("POUT_VC_ID_TIPO_ORIGEN", OracleTypes.VARCHAR ,"VARCHAR2"),
	    		  					new  SqlOutParameter("POUT_NU_ID_TIPO_DOCUMENTO", OracleTypes.NUMBER ,"NUMBER"),
	    		  					new  SqlOutParameter("POUT_VC_ID_TIPO_DOCUMENTO", OracleTypes.VARCHAR ,"VARCHAR2"),
	    		  					new  SqlOutParameter("POUT_VC_CORREO", OracleTypes.VARCHAR ,"VARCHAR2"),
	    		  					new  SqlOutParameter("POUT_VC_DOC_IDENTIDAD", OracleTypes.VARCHAR ,"VARCHAR2"),
	    		  					new  SqlOutParameter("POUT_VC_USUARIO", OracleTypes.VARCHAR ,"VARCHAR2"),
	    		  					new  SqlOutParameter("POUT_VC_NOMBRE_COMPLETO", OracleTypes.VARCHAR ,"VARCHAR2"),
	    		  					new  SqlOutParameter("POUT_NU_FLAG_ALTA", OracleTypes.NUMBER ,"NUMBER"),
	    		  					new  SqlOutParameter("POUT_NU_ID_USUARIO_SEL", OracleTypes.NUMBER ,"NUMBER"),
	    		  					
	    		  					
	    		  					new  SqlOutParameter("POUT_NU_ERROR", OracleTypes.NUMBER ,"NUMBER"),
	    							new  SqlOutParameter("POUT_VC_ERROR", OracleTypes.VARCHAR ,"VARCHAR2")	
	    		  					);


		      Map<String, Object> inParamMap = new HashMap();
		      inParamMap.put("PIN_VC_USUARIO"	, objUsuario.getVcUsuario());
		      
		      SqlParameterSource in = new MapSqlParameterSource(inParamMap);
		      Map<String, Object> out = simpleJdbcCall.execute(in);
	      
		      	objResultDAO.put("POUT_NU_ID_TIPO_ORIGEN", 		out.get("POUT_NU_ID_TIPO_ORIGEN"));
		      	objResultDAO.put("POUT_VC_ID_TIPO_ORIGEN", 		out.get("POUT_VC_ID_TIPO_ORIGEN"));
		      	objResultDAO.put("POUT_NU_ID_TIPO_DOCUMENTO", 	out.get("POUT_NU_ID_TIPO_DOCUMENTO"));
		      	objResultDAO.put("POUT_VC_ID_TIPO_DOCUMENTO", 	out.get("POUT_VC_ID_TIPO_DOCUMENTO"));
		      	objResultDAO.put("POUT_VC_CORREO", 				out.get("POUT_VC_CORREO"));
		      	objResultDAO.put("POUT_VC_DOC_IDENTIDAD", 		out.get("POUT_VC_DOC_IDENTIDAD"));
		      	objResultDAO.put("POUT_VC_USUARIO", 			out.get("POUT_VC_USUARIO"));
		      	objResultDAO.put("POUT_VC_NOMBRE_COMPLETO", 	out.get("POUT_VC_NOMBRE_COMPLETO"));
		      	objResultDAO.put("POUT_NU_FLAG_ALTA", 			out.get("POUT_NU_FLAG_ALTA"));
		      	objResultDAO.put("POUT_NU_ID_USUARIO_SEL", 		out.get("POUT_NU_ID_USUARIO_SEL"));
	      	
			  objResultDAO.put("POUT_NU_ERROR"	 , 				out.get("POUT_NU_ERROR"	 ));
			  objResultDAO.put("POUT_VC_ERROR"	 , 				out.get("POUT_VC_ERROR"	 ));
			
	    }
	    catch (Exception e)
	    {
	      logger.info(e);
	    }
	    return objResultDAO;
	  }
	  
	  @Override
	  public ClsResultDAO doValidarPago(ClsFilterPagoBean objTramitePago, ClsParametrosBean objParametro)
	  {
		  logger.info("doValidarPago() => Objeto: "+objTramitePago+" vcUserName="+objTramitePago.getVcUsuario());
		  System.out.print("doValidarPago");
	    try
	    {
	      this.simpleJdbcCall = new SimpleJdbcCall(this.jdbcTemplate)
	    		  					.withSchemaName(ClsConstantes.SCHEMA_USR_SEL)
	    		  					.withCatalogName(ClsConstantes.PKG_SEL_MARCAS)
	    		  					.withProcedureName(ClsConstantes.SP_GET_VALIDA_PAGO_MAR)
	    		  					.declareParameters(
	    		  					new SqlParameter("PIN_ARR_VC_COD_ARANCEL", OracleTypes.ARRAY, "STR_ARRAY"),
	    		  					
	    		  					new  SqlOutParameter("POUT_VC_NRO_ARANCEL", OracleTypes.VARCHAR ,"VARCHAR2"),
	    		  					new  SqlOutParameter("POUT_VC_NRO_OPERACION", OracleTypes.VARCHAR ,"VARCHAR2"),
	    		  					new  SqlOutParameter("POUT_VC_NRO_PASARELA"	, OracleTypes.VARCHAR ,"VARCHAR2"),
	    		  					new  SqlOutParameter("POUT_NU_EXISTE_SIA"	, OracleTypes.NUMBER ,"NUMBER"),
	    		  					new  SqlOutParameter("POUT_DT_OPERACION"	, OracleTypes.DATE ,"DATE"),
	    		  					new  SqlOutParameter("POUT_VC_COD_BANCO"	, OracleTypes.VARCHAR ,"VARCHAR2"),
	    		  					new  SqlOutParameter("POUT_VC_TIPODOCUMENTO", OracleTypes.VARCHAR ,"VARCHAR2"),
	    		  					new  SqlOutParameter("POUT_NU_ID_TIPO_PAGO"	, OracleTypes.NUMBER ,"NUMBER"),
	    		  					new  SqlOutParameter("POUT_NU_MONTO"		, OracleTypes.NUMBER ,"NUMBER"),
	    		  							
	    		  					new  SqlOutParameter("POUT_NU_ERROR", OracleTypes.NUMBER ,"NUMBER"),
	    							new  SqlOutParameter("POUT_VC_ERROR", OracleTypes.VARCHAR ,"VARCHAR2")	
	    		  					);
	      
	      Map<String, Object> inParamMap = new HashMap();
	      
	      String[] var_codArancel=new String[objParametro.getLstAranceles().size()];
	      
	      for(int i=0; i<objParametro.getLstAranceles().size(); i++) {
	    	  var_codArancel[i]=objParametro.getLstAranceles().get(i).getVcNroArancel();
	      }
	      
	      logger.info("Cod arancel: "+objTramitePago.getObjPago().getVcCodArancel());
	      logger.info("Tipo pago: "+objTramitePago.getObjPago().getNuIdTipoPago());
	      logger.info("Cod arancel: "+objTramitePago.getObjPago().getVcCodigoBanco());
	      logger.info("Codigo de vbanco: "+objTramitePago.getObjPago().getVcNroOperacion());
	      logger.info("fecha operacion: "+objTramitePago.getObjPago().getDtFechaOperacion());
	      
	      inParamMap.put("PIN_ARR_VC_COD_ARANCEL"		, new SqlArrayValue<String>(var_codArancel, "STR_ARRAY"));
	      inParamMap.put("PIN_NU_ID_TIPO_PAGO"		, objTramitePago.getObjPago().getNuIdTipoPago());
	      inParamMap.put("PIN_VC_COD_BANCO"			, objTramitePago.getObjPago().getVcCodigoBanco());
	      inParamMap.put("PIN_VC_NRO_OPERACION"		, objTramitePago.getObjPago().getVcNroOperacion());
	      inParamMap.put("PIN_DT_OPERACION"			, objTramitePago.getObjPago().getDtFechaOperacion());
	      inParamMap.put("PIN_VC_USUARIO"			, objTramitePago.getVcUsuario());
	      
	      SqlParameterSource in = new MapSqlParameterSource(inParamMap);
	      Map<String, Object> out = simpleJdbcCall.execute(in);
	      
	      	objResultDAO.put("POUT_VC_NRO_ARANCEL"	, out.get("POUT_VC_NRO_ARANCEL"	));
			objResultDAO.put("POUT_VC_NRO_OPERACION", out.get("POUT_VC_NRO_OPERACION"	));
			objResultDAO.put("POUT_VC_NRO_PASARELA"	, out.get("POUT_VC_NRO_PASARELA"	));
			objResultDAO.put("POUT_NU_EXISTE_SIA"	, out.get("POUT_NU_EXISTE_SIA"		));
			objResultDAO.put("POUT_DT_OPERACION"	, out.get("POUT_DT_OPERACION"		));
			objResultDAO.put("POUT_VC_COD_BANCO"	, out.get("POUT_VC_COD_BANCO"		));
			objResultDAO.put("POUT_VC_TIPODOCUMENTO", out.get("POUT_VC_TIPODOCUMENTO"	));
			objResultDAO.put("POUT_NU_ID_TIPO_PAGO"	, out.get("POUT_NU_ID_TIPO_PAGO"	));
			objResultDAO.put("POUT_NU_MONTO"		, out.get("POUT_NU_MONTO"			));
	      
			objResultDAO.put("POUT_NU_ERROR", out.get("POUT_NU_ERROR"));
			objResultDAO.put("POUT_VC_ERROR", out.get("POUT_VC_ERROR"));
	     
	    }
	    catch (Exception e)
	    {
	    	e.printStackTrace();
	      logger.info(e);
	    }
	    return objResultDAO;
	  }
	  
	  @Override
	  public ClsResultDAO doCompruebaPagos(ClsParametrosBean objParametros,ClsSolicitudBean objSolicitud)
	  {
		  System.out.println("tamanio de lista: "+objSolicitud.getLstPago().size());
	    try
	    {
	      this.simpleJdbcCall = new SimpleJdbcCall(this.jdbcTemplate)
	    		  					.withSchemaName(ClsConstantes.SCHEMA_USR_SEL)
	    		  					.withCatalogName(ClsConstantes.PKG_SEL_MARCAS)
	    		  					.withProcedureName(ClsConstantes.SP_DO_COMPRUEBA_PAGO)
	    		  					.declareParameters(
	    		  					new SqlParameter("PIN_ARR_VC_COD_ARANCEL"	, OracleTypes.ARRAY, "STR_ARRAY"),	
	    		  					new SqlParameter("PIN_ARR_NU_ID_TIPO_PAGO"	, OracleTypes.ARRAY, "NUM_ARRAY"),	
	    		  					new SqlParameter("PIN_ARR_VC_COD_BANCO"		, OracleTypes.ARRAY, "STR_ARRAY"),	
	    		  					new SqlParameter("PIN_ARR_VC_NRO_OPERACION"	, OracleTypes.ARRAY, "STR_ARRAY"),	
	    		  					new SqlParameter("PIN_ARR_DT_OPERACION"		, OracleTypes.ARRAY, "DATE_ARRAY"),	
	    		  						
	    		  					new  SqlOutParameter("POUT_NU_ERROR", OracleTypes.NUMBER ,"NUMBER"),
	    							new  SqlOutParameter("POUT_VC_ERROR", OracleTypes.VARCHAR ,"VARCHAR2")	
	    		  					);
	      
	        int nuTamanioLstPago=objSolicitud.getLstPago().size();
		  	String[]  var_vcCodArancel				= new String	[nuTamanioLstPago];
			Integer[] var_nuIdTipoPago				= new Integer	[nuTamanioLstPago];
			String[]  var_vcCodigoBanco				= new String	[nuTamanioLstPago];
			String[]  var_vcNroOperacion				= new String	[nuTamanioLstPago];
			java.sql.Date[] var_dtFechaOperacion	= new java.sql.Date[nuTamanioLstPago];
			
			for(int i=0; i<nuTamanioLstPago; i++) {
				ClsPagoBean objPagoBean=objSolicitud.getLstPago().get(i);
				System.out.print("nro de arancel: "+objPagoBean.getVcNroOperacion());
				var_vcCodArancel	 [i] = objPagoBean.getVcCodArancel();
				var_nuIdTipoPago	 [i] = objPagoBean.getNuIdTipoPago();
				var_vcCodigoBanco	 [i] = objPagoBean.getVcCodigoBanco();
				var_vcNroOperacion   [i] = objPagoBean.getVcNroOperacion();
				var_dtFechaOperacion [i] = objPagoBean.getDtFechaOperacion()!=null?new java.sql.Date(objPagoBean.getDtFechaOperacion().getTime()+1):null;
			}
	      
	      Map<String, Object> inParamMap = new HashMap();
	
          
          inParamMap.put("PIN_VC_USUARIO"			, objSolicitud.getVcUsuario());
	      inParamMap.put("PIN_NU_CANT_CLASES"		, objParametros.getNuCantClases());
	      inParamMap.put("PIN_NU_COSTO_P_CLASE"		, objParametros.getNuCostoPClase());
	      inParamMap.put("PIN_NU_COSTO_S_CLASE"		, objParametros.getNuCostoSClase());

	      inParamMap.put("PIN_ARR_VC_COD_ARANCEL"		, new SqlArrayValue<String>(var_vcCodArancel, "STR_ARRAY"));
	      inParamMap.put("PIN_ARR_NU_ID_TIPO_PAGO"		, new SqlArrayValue<Integer>(var_nuIdTipoPago, "NUM_ARRAY"));
	      inParamMap.put("PIN_ARR_VC_COD_BANCO"			, new SqlArrayValue<String>(var_vcCodigoBanco, "STR_ARRAY"));
	      inParamMap.put("PIN_ARR_VC_NRO_OPERACION"		, new SqlArrayValue<String>(var_vcNroOperacion, "STR_ARRAY"));
	      inParamMap.put("PIN_ARR_DT_OPERACION"			, new SqlArrayValue<java.sql.Date>(var_dtFechaOperacion, "DATE_ARRAY"));
	      
	      
	      SqlParameterSource in = new MapSqlParameterSource(inParamMap);
	      Map<String, Object> out = simpleJdbcCall.execute(in);
	      
			objResultDAO.put("POUT_NU_ERROR", out.get("POUT_NU_ERROR"));
			objResultDAO.put("POUT_VC_ERROR", out.get("POUT_VC_ERROR"));
	     
	    }
	    catch (Exception e)
	    {
	      logger.info(e);
	    }
	    return objResultDAO;
	  }

	  @Override
	  public ClsResultDAO doAviso( ClsFiltroProcedimientoBean objProcedimiento)
	  {
		  logger.info("doUsuario()");
	    try
	    {
	   
	      this.simpleJdbcCall = new SimpleJdbcCall(this.jdbcTemplate)
	    		  					.withSchemaName(ClsConstantes.SCHEMA_USR_SEL)
	    		  					.withCatalogName(ClsConstantes.PKG_SEL_AVISO)
	    		  					.withProcedureName(ClsConstantes.SP_LST_AVISO_VIGENTE)
	    		  					.declareParameters(	
	    		  							
	    		  					new  SqlOutParameter("POUT_NU_FLAG", OracleTypes.NUMBER ,"NUMBER"),
	    		  					new  SqlOutParameter("POUT_CL_AVISO", OracleTypes.CLOB ,"CLOB"),
	    		  					new  SqlOutParameter("POUT_NU_ERROR", OracleTypes.NUMBER ,"NUMBER"),
	    							new  SqlOutParameter("POUT_VC_ERROR", OracleTypes.VARCHAR ,"VARCHAR2")	
	    		  					);


		      Map<String, Object> inParamMap = new HashMap();
		      inParamMap.put("PIN_NU_ID_PROCEDIMIENTO"	, objProcedimiento.getNuIdProcedimiento());
		      
		      SqlParameterSource in = new MapSqlParameterSource(inParamMap);
		      Map<String, Object> out = simpleJdbcCall.execute(in);
	      
		      objResultDAO.put("POUT_NU_FLAG", 					out.get("POUT_NU_FLAG"	 ));
		      objResultDAO.put("POUT_CL_AVISO", 				out.get("POUT_CL_AVISO")!=null?ClsUtil.clobToString((Clob)out.get("POUT_CL_AVISO")):"");
			  objResultDAO.put("POUT_NU_ERROR"	 , 				out.get("POUT_NU_ERROR"	 ));
			  objResultDAO.put("POUT_VC_ERROR"	 , 				out.get("POUT_VC_ERROR"	 ));
			
	    }
	    catch (Exception e)
	    {
	      logger.info(e);
	      e.printStackTrace();
	    }
	    return objResultDAO;
	  }
	  
	  
	  @Override
	  public ClsResultDAO doTramite( ClsTramiteSELBean objTramite, 
			  						ClsSolicitudBean objSolicitud,
			  						ClsParametrosBean objParametros,
									 String vcUserName
									 )
	  {
		  logger.info("doTramite()");
	    try
	    {
	   
	      this.simpleJdbcCall = new SimpleJdbcCall(this.jdbcTemplate)
	    		  					.withSchemaName(ClsConstantes.SCHEMA_USR_SEL)
	    		  					.withCatalogName(ClsConstantes.PKG_SEL_MARCAS)
	    		  					.withProcedureName(ClsConstantes.SP_I_TRAMITE_MARCAS)
	    		  					.declareParameters(	
	    		  					
	    		  					new SqlParameter("PIN_ARR_VC_COD_ARANCEL"	, OracleTypes.ARRAY, "STR_ARRAY"),	
	    	    		  			new SqlParameter("PIN_ARR_NU_ID_TIPO_PAGO"	, OracleTypes.ARRAY, "NUM_ARRAY"),	
	    	    		  			new SqlParameter("PIN_ARR_VC_COD_BANCO"		, OracleTypes.ARRAY, "STR_ARRAY"),	
	    	    		  			new SqlParameter("PIN_ARR_VC_NRO_OPERACION"	, OracleTypes.ARRAY, "STR_ARRAY"),	
	    	    		  			new SqlParameter("PIN_ARR_DT_OPERACION"		, OracleTypes.ARRAY, "DATE_ARRAY"),	
	    	    		  						
	    	    		  					
	    		  					new  SqlOutParameter("POUT_NU_ERROR", OracleTypes.NUMBER ,"NUMBER"),
	    							new  SqlOutParameter("POUT_VC_ERROR", OracleTypes.VARCHAR ,"VARCHAR2")	
	    		  					);

		

	        int nuTamanioLstPago=objSolicitud.getLstPago().size();
		  	String[]  var_vcCodArancel				= new String	[nuTamanioLstPago];
			Integer[] var_nuIdTipoPago				= new Integer	[nuTamanioLstPago];
			String[]  var_vcCodigoBanco				= new String	[nuTamanioLstPago];
			String[]  var_vcNroOperacion				= new String	[nuTamanioLstPago];
			java.sql.Date[] var_dtFechaOperacion	= new java.sql.Date[nuTamanioLstPago];
			
			for(int i=0; i<nuTamanioLstPago; i++) {
				ClsPagoBean objPagoBean=objSolicitud.getLstPago().get(i);
				var_vcCodArancel	 [i] = objPagoBean.getVcCodArancel();
				var_nuIdTipoPago	 [i] = objPagoBean.getNuIdTipoPago();
				var_vcCodigoBanco	 [i] = objPagoBean.getVcCodigoBanco();
				var_vcNroOperacion   [i] = objPagoBean.getVcNroOperacion();
				var_dtFechaOperacion [i] = objPagoBean.getDtFechaOperacion()!=null?new java.sql.Date(objPagoBean.getDtFechaOperacion().getTime()+1):null;
			}
		  
		  Map<String, Object> inParamMap = new HashMap();
		  
		  //System.out.print("cuerpo del correo:"+objTramite.getClDetTramite());
	      
	      inParamMap.put("PIN_VC_USUARIO"				, vcUserName);
	      inParamMap.put("PIN_CL_DET_TRAMITE"			, objTramite.getClDetTramite());
	      inParamMap.put("PIN_NU_CANT_CLASES"		, objParametros.getNuCantClases());
	      inParamMap.put("PIN_NU_COSTO_P_CLASE"		, objParametros.getNuCostoPClase());
	      inParamMap.put("PIN_NU_COSTO_S_CLASE"		, objParametros.getNuCostoSClase());
	      //T_SEL_TRAMITE
	      inParamMap.put("PIN_NU_ID_PROCEDIMIENTO"		, objTramite.getNuIdProcedimiento());
	      inParamMap.put("PIN_VC_NRO_EXPEDIENTE"		, objTramite.getVcNroExpediente());
	      inParamMap.put("PIN_VC_ANIO_EXPEDIENTE"		, objTramite.getVcAnioExpediente());
	      inParamMap.put("PIN_VC_SIGLA_EXPEDIENTE"		, objTramite.getVcSiglaExpediente());
	      inParamMap.put("PIN_NU_ID_ESTADO_TRAMITE"		, objTramite.getNuIdEstadoTramite());
	      inParamMap.put("PIN_VC_COD_AREA_DESTINO"		, objTramite.getVcCodAreaDestino());
	      
	      //T_SEL_TRAMITE_DOC --PAGOS
	      inParamMap.put("PIN_ARR_VC_COD_ARANCEL"		, new SqlArrayValue<String>(var_vcCodArancel, "STR_ARRAY"));
	      inParamMap.put("PIN_ARR_NU_ID_TIPO_PAGO"		, new SqlArrayValue<Integer>(var_nuIdTipoPago, "NUM_ARRAY"));
	      inParamMap.put("PIN_ARR_VC_COD_BANCO"			, new SqlArrayValue<String>(var_vcCodigoBanco, "STR_ARRAY"));
	      inParamMap.put("PIN_ARR_VC_NRO_OPERACION"		, new SqlArrayValue<String>(var_vcNroOperacion, "STR_ARRAY"));
	      inParamMap.put("PIN_ARR_DT_OPERACION"			, new SqlArrayValue<java.sql.Date>(var_dtFechaOperacion, "DATE_ARRAY"));
	      
	      
	      SqlParameterSource in = new MapSqlParameterSource(inParamMap);
	      Map<String, Object> out = simpleJdbcCall.execute(in);
	      
	      	System.out.println("Error en tramite: "+out.get("POUT_VC_ERROR"));
			objResultDAO.put("POUT_NU_ERROR", out.get("POUT_NU_ERROR"));
			objResultDAO.put("POUT_VC_ERROR", out.get("POUT_VC_ERROR"));
			
			
			
	    }
	    catch (Exception e)
	    {
	      logger.info(e);
	    }
	    return objResultDAO;
	  }
	  
	  
	  
}
