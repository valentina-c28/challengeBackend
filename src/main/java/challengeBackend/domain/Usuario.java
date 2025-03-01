package challengeBackend.domain;

import java.sql.Date;

public class Usuario {

    // atributos
    private int id;
    private String nombre;
    private String email;
    private Date fecha_registro;

    // Constructor
    public Usuario(int id, String nombre, String email, Date fecha_registro) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.fecha_registro = fecha_registro;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getFecha_registro() {
        return fecha_registro;
    }

    public void setFecha_registro(Date fecha_registro) {
        this.fecha_registro = fecha_registro;
    }
}
