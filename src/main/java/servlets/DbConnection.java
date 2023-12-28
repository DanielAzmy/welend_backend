package servlets;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;

public class DbConnection {
    static Logger log = Logger.getLogger(DbConnection.class.getName());
    private static NamedParameterJdbcTemplate jdbcTemplate;
    private static Connection con;
    private static void createNewJdbcTemplate() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl("jdbc:postgresql://localhost:5432/welend");
        dataSource.setUsername("dodo");
        dataSource.setPassword("dodo");
        jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }
    public static NamedParameterJdbcTemplate getConnection() {
        if (jdbcTemplate == null) {
            createNewJdbcTemplate();
        }
        return jdbcTemplate;
    }


    private static void startConnection() {
        String url = "jdbc:postgresql://localhost:5432/welend";
        String user = "dodo";
        String pass = "dodo";
        try {
            Class.forName("org.postgresql.Driver");
            con = DriverManager.getConnection(url, user, pass);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
    public static Connection getNewConnection() throws SQLException {
        log.info("Enter getConnection function...");
        if (con == null || con.isClosed()) {
            startConnection();
        }
        return con;
    }

}
