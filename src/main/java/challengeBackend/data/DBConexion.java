package challengeBackend.data;

import java.sql.*;

// Esta clase será la que se encargue de la conexión con la base de datos.

public class DBConexion {

    private static final String DB_NAME = "challenge";
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/" + DB_NAME;
    private static final String DB_USER = "postgres";
    private static final String DB_PWD = "1234";
    private static DBConexion dbConn;

    public static DBConexion getInstance(){
        if (dbConn == null) dbConn = new DBConexion();
        return dbConn;
    }

    public Connection conectar() throws SQLException{

        return DriverManager.getConnection(DB_URL, DB_USER, DB_PWD);
    }

    public static void desconectar(Connection conn) throws SQLException {
        conn.close();
    }
}
