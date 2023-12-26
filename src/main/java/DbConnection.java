import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Logger;

public class DbConnection {
    private static Connection con;
    static Logger log = Logger.getLogger(DbConnection.class.getName());
    private static NamedParameterJdbcTemplate jdbcTemplate;
    private static void createNewJdbcTemplate() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl("jdbc:postgresql://localhost:5432/postgres2");
        dataSource.setUsername("postgres");
        dataSource.setPassword("ertxcs");
        jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }
    public static NamedParameterJdbcTemplate getConnection() throws SQLException {
        if (jdbcTemplate == null) {
            createNewJdbcTemplate();
        }
        return jdbcTemplate;
    }

}
