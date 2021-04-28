package pe.gob.indecopi.repository;

import java.io.Serializable;
import java.sql.Clob;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import oracle.jdbc.OracleTypes;
import pe.gob.indecopi.bean.ClsConfiguracionBean;
import pe.gob.indecopi.bean.ClsFiltroConfiguracionBean;
import pe.gob.indecopi.bean.ClsFiltroUbigeoBean;
import pe.gob.indecopi.bean.ClsUbigeoBean;
import pe.gob.indecopi.bean.ClsUbigeoMatchBean;
import pe.gob.indecopi.param.ClsConstantes;
import pe.gob.indecopi.util.ClsResultDAO;
import pe.gob.indecopi.util.ClsUtil;

@Repository
@Transactional
public class ClsUbigeoRepository implements Serializable, ClsUbigeoRepositoryI{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3789470206482678434L;
	private  Logger logger = Logger.getLogger(ClsUbigeoRepository.class);
	private SimpleJdbcCall simpleJdbcCall;

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
	
	private ClsUbigeoMatchBean objUbigeoM;
	
	@Override
	public ClsResultDAO doUbigeo(ClsFiltroUbigeoBean objFiltro) {

		try {
			//procedure
			this.simpleJdbcCall=new SimpleJdbcCall(jdbcTemplate)
									.withSchemaName(ClsConstantes.SCHEMA_USR_MARCAS)
									.withCatalogName(ClsConstantes.PKG_UBIGEO)
									.withProcedureName(ClsConstantes.SP_LST_UBIGEO)
									.declareParameters(
									new  SqlOutParameter("POUT_CUR_REGISTRO", OracleTypes.CURSOR ,
									new RowMapper<ClsUbigeoBean>() {

										@Override
										public ClsUbigeoBean mapRow(ResultSet rs, int rowNum)
												throws SQLException {
											ClsUbigeoBean objRespuesta=new ClsUbigeoBean();
											objRespuesta.setNuIdUbigeo(rs.getInt("nu_ubigeo"));
											objRespuesta.setVcDescripcion(rs.getString("VC_DESCRIPCION"));
									
											
											return objRespuesta;
										}
									})
									);
			//param
			SqlParameterSource inParamMap = new MapSqlParameterSource()
											    .addValue("PIN_NU_FLAG_TIPO", objFiltro.getNuIdTipo())
											    .addValue("PIN_NU_FLAG_PADRE", objFiltro.getNuIdPadre());
			//execute
			Map<String, Object> out = this.simpleJdbcCall.execute(inParamMap);
			
			//response
			objResultDAO.put("POUT_CUR_REGISTRO", out.get("POUT_CUR_REGISTRO"));
											    		
			
		}catch(Exception e) {
			logger.info(e);
			e.printStackTrace();
		}
		
		return objResultDAO;
	}
	
	@Override
	public ClsResultDAO doUbigeoMatch(String vcUbigeo) {

		try {
			//procedure
			this.simpleJdbcCall=new SimpleJdbcCall(jdbcTemplate)
									.withSchemaName(ClsConstantes.SCHEMA_USR_MARCAS)
									.withCatalogName(ClsConstantes.PKG_UBIGEO)
									.withProcedureName(ClsConstantes.SP_GET_DATOS_UBIGEO)
									.declareParameters(
									new  SqlOutParameter("POUT_CUR_UBIGEO_IND", OracleTypes.CURSOR ,
									new RowMapper<ClsUbigeoMatchBean>() {

										@Override
										public ClsUbigeoMatchBean mapRow(ResultSet rs, int rowNum)
												throws SQLException {
											ClsUbigeoMatchBean objRespuesta=new ClsUbigeoMatchBean();
											objRespuesta.setNuDepartamento(rs.getInt("NU_DEPARTAMENTO"));
											objRespuesta.setNuProvincia(rs.getInt("NU_PROVINCIA"));
											objRespuesta.setNuDistrito(rs.getInt("NU_DISTRITO"));
											
											objRespuesta.setVcDepartamento(rs.getString("VC_DEPARTAMENTO"));
											objRespuesta.setVcProvincia(rs.getString("VC_PROVINCIA"));
											objRespuesta.setVcDistrito(rs.getString("VC_DISTRITO"));
											
											return objRespuesta;
										}
									}),
									
	    		  					new  SqlOutParameter("POUT_NU_ERROR", OracleTypes.NUMBER ,"NUMBER"),
	    							new  SqlOutParameter("POUT_VC_ERROR", OracleTypes.VARCHAR ,"VARCHAR2")	
									);

			//param
			SqlParameterSource inParamMap = new MapSqlParameterSource()
											    .addValue("PIN_VC_UBIGEO", vcUbigeo);
			//execute
			Map<String, Object> out = this.simpleJdbcCall.execute(inParamMap);
			
			List<ClsUbigeoMatchBean> lstUbigeo=new ArrayList<ClsUbigeoMatchBean>();
			
			lstUbigeo=(List<ClsUbigeoMatchBean>)out.get("POUT_CUR_UBIGEO_IND");
			this.objUbigeoM=new ClsUbigeoMatchBean();
			if(lstUbigeo.size()>0) {
			for(int i=0; i< lstUbigeo.size(); i++) {
				this.setObjUbigeoM(lstUbigeo.get(i));
			}
			}
			
			//response
			  objResultDAO.put("OBJ_UBIGEO_MATCH",           objUbigeoM);
			  objResultDAO.put("POUT_NU_ERROR"	 , 				out.get("POUT_NU_ERROR"	 ));
			  objResultDAO.put("POUT_VC_ERROR"	 , 				out.get("POUT_VC_ERROR"	 ));
											    		
			
		}catch(Exception e) {
			logger.info(e);
			e.printStackTrace();
		}
		
		return objResultDAO;
	}

	public ClsUbigeoMatchBean getObjUbigeoM() {
		return objUbigeoM;
	}

	public void setObjUbigeoM(ClsUbigeoMatchBean objUbigeoM) {
		this.objUbigeoM = objUbigeoM;
	}
	
	
}
