package challengeBackend.data;

import challengeBackend.domain.Usuario;

import java.sql.*;
import java.util.ArrayList;

// Esta clase contiene todos los métodos que van a interactuar con la base de datos.

public class DBAdministracion {

    private static Statement query;
    private static DBAdministracion dbAdmin;
    private static PreparedStatement statement;
    private static ResultSet res;

    public static DBAdministracion getInstance()
    {
        if (DBAdministracion.dbAdmin == null) DBAdministracion.dbAdmin = new DBAdministracion();
        return DBAdministracion.dbAdmin;
    }

    // El siguiente método creará la tabla de usuarios en caso de que no exista previamente.
    public boolean crearTabla () {

        try {
            Connection conn = DBConexion.getInstance().conectar();
            query = conn.createStatement();

            query.execute("CREATE TABLE IF NOT EXISTS usuarios ("
                    + "id SERIAL PRIMARY KEY,"
                    + "nombre VARCHAR(50) NOT NULL,"
                    + "email VARCHAR(100) UNIQUE NOT NULL,"
                    + "fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP)");

            return true;

        } catch (SQLException ex) {
            //Si hay un problema con la creación de tablas el método retorna false
            ex.printStackTrace();
        }
        return false;
    }

    // En caso de que la base de datos se encuentre vacía, se inicializará con los usuarios del challenge de Base de datos para poder hacer el testeo.
    public boolean inicializarTabla () {

        try{
            Connection conn = DBConexion.getInstance().conectar();
            query = conn.createStatement();
            statement = conn.prepareStatement("SELECT COUNT (*) FROM usuarios");
            res = statement.executeQuery();
            res.next();

            if (String.valueOf(res.getObject(1)).equals("0"))
            {
                return query.execute("INSERT INTO usuarios (nombre, email) VALUES"
                        + "('Juan Pérez', 'juan.perez@example.com'),"
                        + "('Ana García', 'ana.garcia@example.com'),"
                        + "('Luis Rodríguez', 'luis.rodriguez@example.com'),"
                        + "('María Fernández', 'maria.fernandez@example.com'),"
                        + "('Carlos López', 'carlos.lopez@example.com');");
            }

        } catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        return false;
    }

    // El siguiente método devuelve una lista que contiene los usuarios en la base de datos.
    public ArrayList<Usuario> obtenerUsuarios () {
        ArrayList<Usuario> usuarios = new ArrayList<>();

        try {
            Connection conn = DBConexion.getInstance().conectar();
            statement = conn.prepareStatement("SELECT * FROM usuarios");
            res = statement.executeQuery();

            while (res.next())
            {
                usuarios.add(new Usuario(res.getInt("id"), res.getString("nombre"), res.getString("email"), res.getDate("fecha_creacion")));
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return usuarios;
    }

    // Este método será el que se encargue de buscar un ID pasado por parámetro.
    public boolean buscarID (int id) {
        ResultSet res;
        try {
            Connection conn = DBConexion.getInstance().conectar();
            statement = conn.prepareStatement("SELECT * FROM usuarios WHERE id = " + id);

            res = statement.executeQuery();
            if (res.next())
            {
                if (res.getInt("id") == id) return true;
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    // Este método realiza la inserción de un usuario a la base de datos.
    public boolean ingresarUsuario (Usuario u) {
        boolean res;
        try {
            Connection conn = DBConexion.getInstance().conectar();
            query = conn.createStatement();

            res = query.execute("INSERT INTO usuarios VALUES (DEFAULT, '"+u.getNombre()+"', '"+u.getEmail()+"', DEFAULT)");

            if (!res) return true;

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    // Este método realiza la eliminación de un usuario de la base de datos.
    public boolean eliminarUsuario (int id) {
        boolean res;
        try {
            Connection conn = DBConexion.getInstance().conectar();
            query = conn.createStatement();
            res = query.execute("DELETE FROM usuarios WHERE id = " +id);

            // Debido a que la sentencia Delete devuelve falso, incluso aunque realice la eliminación, solo se devolverá verdadero cuando el valor
            // de la variable "res" sea falso.
            if (!res) return true;

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return false;
    }

}
