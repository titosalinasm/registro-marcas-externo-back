package pe.gob.indecopi.repository;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import oracle.jdbc.OracleTypes;
import pe.gob.indecopi.bean.ClsDatosReniecBean;
import pe.gob.indecopi.bean.ClsFilterReniecBean;
import pe.gob.indecopi.util.ClsResultDAO;

@Repository
@Transactional
public class ClsReniecRepository implements Serializable, ClsReniecRepositoryI{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2634195547237384707L;

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

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
	public ClsResultDAO doConsultaReniec(ClsFilterReniecBean objReniec) {

		try {
		//procedure
		this.simpleJdbcCall=new SimpleJdbcCall(jdbcTemplate)
								.withSchemaName("USR_PIDE")
								.withCatalogName("PKG_CONSULTA_PIDE")
								.withProcedureName("SP_LST_CONSULTA_RENIEC")
								.declareParameters(
								new  SqlOutParameter("POUT_VC_MENSAJE", OracleTypes.VARCHAR ,"VARCHAR2"),
								new SqlOutParameter(
									"POUT_CUR_RENIEC", OracleTypes.CURSOR, new RowMapper<ClsDatosReniecBean>() {
										@Override
										public ClsDatosReniecBean mapRow(ResultSet rs, int rowNumber)
												throws SQLException {
											ClsDatosReniecBean resreniec = new ClsDatosReniecBean();
											resreniec.setVcReniecNombres(rs.getString("PRENOMBRES"));
											resreniec.setVcReniecApPaterno(rs.getString("APPRIMER"));
											resreniec.setVcReniecApMaterno(rs.getString("APSEGUNDO"));
											resreniec.setVcReniecDireccion(rs.getString("DIRECCION"));
											resreniec.setVcReniecEstadoCivil(rs.getString("ESTADOCIVIL"));
											resreniec.setVcReniecRestriccion(rs.getString("RESTRICCION"));
											resreniec.setVcReniecUbigeo(rs.getString("UBIGEO"));
											return resreniec;
										}

									}),
								new  SqlOutParameter("POUT_NU_ERROR", OracleTypes.NUMBER ,"NUMBER"),
								new  SqlOutParameter("POUT_VC_ERROR", OracleTypes.VARCHAR ,"VARCHAR2")
										);
		//param
		SqlParameterSource inParamMap = new MapSqlParameterSource()
										    .addValue("PIN_VC_DNI", objReniec.getVcDocIdentidad());
		//execute
		Map<String, Object> out = this.simpleJdbcCall.execute(inParamMap);
		
		//response
		
		objResultDAO.put("VC_MENSAJE_BUSQUEDA", out.get("POUT_VC_MENSAJE"));
		objResultDAO.put("pCUR_RENIEC", out.get("POUT_CUR_RENIEC"));
		objResultDAO.put("POUT_NU_ERROR", out.get("POUT_NU_ERROR"));
		objResultDAO.put("POUT_VC_ERROR", out.get("POUT_VC_ERROR"));

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return objResultDAO;
		
	}


}
