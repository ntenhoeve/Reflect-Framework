package nth.reflect.infra.dataaccess.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nth.reflect.fw.layer5provider.reflection.ReflectionProvider;
import nth.reflect.fw.layer5provider.reflection.info.classinfo.DomainClassInfo;
import nth.reflect.fw.layer5provider.reflection.info.property.PropertyInfo;

public abstract class SqlRepository {
	protected Connection connection;

	public abstract SqlDatabaseConfig getSqlDatabaseConfig();

	public Statement executeSQL(String sql) throws Exception {
		try {
			Statement statement = createConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			statement.execute(sql);
			if (sql.startsWith("select")) {
			} else {
			}
			// if (!connection.getAutoCommit()) {
			// // commit manualy because auto commit is disabled
			// connection.commit();
			// }
			return statement;
		} catch (SQLException e) {
			throw new Exception("Failed to execute SQL command:\r" + sql, e);
		}
	}

	protected Connection createConnection() {
		if (connection == null) {
			SqlDatabaseConfig sqlDatabaseConfig = getSqlDatabaseConfig();
			try {
				Class.forName(sqlDatabaseConfig.getDriverClass().getCanonicalName());// load
																						// driver
																						// class
				connection = DriverManager.getConnection(sqlDatabaseConfig.getUrl(), sqlDatabaseConfig.getUserName(),
						sqlDatabaseConfig.getPassword());
			} catch (SQLException exception) {
				throw new IllegalStateException(
						"Failed to connect to the database using the update user. Please contact your DBA.", exception);
			} catch (ClassNotFoundException e) {
				throw new IllegalStateException(
						"Could not find database driver: " + sqlDatabaseConfig.getDriverClass().getCanonicalName(), e);
			}
		}
		return connection;
	}

	public void close() {
		if (connection != null) {
			try {
				connection.close();
				connection = null;
			} catch (SQLException dbNotClosingSQLException) {
				System.err.println("Failed to close JDBC connection " + dbNotClosingSQLException);
			}
		}
	}

	// private String getResultString(String sql) throws Exception {
	// Statement statement = executeSQL(sql);
	// ResultSet resultSet = statement.getResultSet();
	// resultSet.next();
	// return resultSet.getString(1);
	// }

	public List<?> getResultList(String sql, DomainObjectFactory domainObjectFactory) throws Exception {
		Statement statement = executeSQL(sql);
		ResultSet resultSet = statement.getResultSet();
		List<Object> domainObjects = new ArrayList<Object>();

		ResultSetMetaData meta = resultSet.getMetaData();
		int numColumns = meta.getColumnCount();
		while (resultSet.next()) {
			HashMap<String, Object> record = new HashMap<String, Object>();
			for (int columnNr = 1; columnNr < numColumns + 1; columnNr++) {
				String columnName = meta.getColumnName(columnNr);
				Object value = resultSet.getObject(columnNr);
				record.put(columnName, value);
			}
			Object domainObject = domainObjectFactory.createDomainObject(record);
			domainObjects.add(domainObject);
		}
		return domainObjects;
	}

	public List<?> getResultList(ReflectionProvider reflectionProvider, String sql, Class<?> domainClass)
			throws Exception {
		Statement statement = executeSQL(sql);
		ResultSet resultSet = statement.getResultSet();
		List<Object> results = new ArrayList<Object>();

		Map<String, PropertyInfo> propertyInfos = new HashMap<String, PropertyInfo>();
		ResultSetMetaData meta = resultSet.getMetaData();
		int numColumns = meta.getColumnCount();
		DomainClassInfo domainClassInfo = reflectionProvider.getDomainClassInfo(domainClass);
		for (int columnNr = 1; columnNr < numColumns + 1; columnNr++) {
			String columnName = meta.getColumnName(columnNr);
			for (PropertyInfo propertyInfo : domainClassInfo.getPropertyInfosSorted()) {
				if (propertyInfo.getSimpleName().equalsIgnoreCase(columnName)) {
					propertyInfos.put(columnName, propertyInfo);
					break;
				}
			}
		}

		while (resultSet.next()) {
			Object domainObject = domainClass.newInstance();
			for (int columnNr = 1; columnNr < numColumns + 1; columnNr++) {
				String columnName = meta.getColumnName(columnNr);
				Object value = resultSet.getObject(columnNr);
				PropertyInfo propertyInfo = propertyInfos.get(columnName);
				if (propertyInfo == null) {
					throw new CouldNotFindPropertyException(columnName, domainClass);
				}
				try {
					propertyInfo.setValue(domainObject, value);
					// } catch (IllegalArgumentException e) {
					// throw new RuntimeException("Property type of property: "
					// +
					// domainClass.getCanonicalName()+"."+propertyInfo.getName()+"
					// must be of type"+value.getClass().getCanonicalName());
				} catch (Exception e) {
					throw new CouldNotSetPropertyException(domainClass, propertyInfo, e);
				}
			}
			results.add(domainObject);
		}
		return results;
	}

	public Map<String, Object> getResultMap(String sql) throws Exception {
		Statement statement = executeSQL(sql);
		ResultSet resultSet = statement.getResultSet();
		Map<String, Object> results = new HashMap<String, Object>();
		while (resultSet.next()) {
			results.put(resultSet.getString(1), resultSet.getObject(2));
		}
		return results;
	}

}
