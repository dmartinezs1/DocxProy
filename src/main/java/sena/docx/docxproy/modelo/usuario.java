package sena.docx.docxproy.modelo;

public class usuario {
    private int id_usuario;
    private String nombreUsuario;
    private String correoUsuario;
    private String clave;
    private boolean estado;
    private cargo cargo;

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getCorreoUsuario() {
        return correoUsuario;
    }

    public void setCorreoUsuario(String correoUsuario) {
        this.correoUsuario = correoUsuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public sena.docx.docxproy.modelo.cargo getCargo() {
        return cargo;
    }

    public void setCargo(sena.docx.docxproy.modelo.cargo cargo) {
        this.cargo = cargo;
    }

    public String getCargoRep() {
        return this.cargo != null ? this.cargo.getNombreCargo() : "--";
    }

    public String getEstadoUsuarioRep() {
        return this.estado ? "Activo" : "Inactivo";
    }
}
