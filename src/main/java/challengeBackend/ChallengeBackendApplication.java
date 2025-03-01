package challengeBackend;

import challengeBackend.data.DBAdministracion;
import challengeBackend.data.DBConexion;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.sql.Connection;
import java.sql.SQLException;

import static challengeBackend.data.DBConexion.desconectar;


@SpringBootApplication
public class ChallengeBackendApplication {

	public static void main(String[] args) {
		DBAdministracion db = DBAdministracion.getInstance();
        try {
            Connection con = DBConexion.getInstance().conectar();
			if(db.crearTabla())
			{
				db.inicializarTabla();
			}
			desconectar(con);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        SpringApplication.run(ChallengeBackendApplication.class, args);
	}

}
