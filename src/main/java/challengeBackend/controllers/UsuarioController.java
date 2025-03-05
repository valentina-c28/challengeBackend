package challengeBackend.controllers;

import challengeBackend.data.DBAdministracion;
import challengeBackend.domain.Usuario;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class UsuarioController {

    private ArrayList<Usuario> usuarios = new ArrayList<>();
    DBAdministracion db = DBAdministracion.getInstance();

    // La lista de usuarios se obtendrá a través de localhost:8080/users
    @GetMapping("/users")
    public ResponseEntity<ArrayList<Usuario>> getUsuarios() {

        // la lista usuarios será la que reciba la lista creada en obtenerUsuarios(), para poder mostrar la información en la base de datos.
        usuarios = db.obtenerUsuarios();
        return ResponseEntity.ok(usuarios);
    }

    // Este método recibe el id del usuario a través de localhost:8080/users/{id} y lo elimina
    @DeleteMapping("/users/{id}")
    public ResponseEntity<String> deleteUsuario (@PathVariable int id) {

        if(db.buscarID(id)) { // antes de hacer la eliminación se deberá llamar al método buscarID para corroborar que se encuentre en la base de datos.

            if (db.eliminarUsuario(id)) {

                // en caso de remover el usuario exitosamente, se devolverá un mensaje de éxito.
                return ResponseEntity.ok("200 OK: El usuario se eliminó exitosamente");
            }
        }
        // en caso de no encontrar al usuario, devolverá un mensaje de error.
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("404 NOT FOUND: El usuario correspondiente a ese ID no existe");
    }

    // Este método recibe un usuario nuevo y lo agrega a la lista de usuarios
    @PostMapping("/users")
    public ResponseEntity<String> postUsuario (@RequestBody Usuario u) {

        if (u.getEmail().isBlank() || u.getNombre().isBlank()) {

            // En caso de que el email o el nombre se encuentren vacíos, se enviará un código de error.
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("400 BAD REQUEST: Los campos de email y nombre son obligatorios");
        } else {
            if(!db.ingresarUsuario(u)) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("500 INTERNAL SERVER ERROR: No se pudo guardar la infromación");
            } // Si no hay ningún problema se realizará la inserción del usuario.
        }
        // En caso de que el usuario se guarde exitosamente, se enviara un código de éxito.
        return ResponseEntity.status(HttpStatus.CREATED).body("201 CREATED: El usuario se guardó exitosamente");
    }
}
