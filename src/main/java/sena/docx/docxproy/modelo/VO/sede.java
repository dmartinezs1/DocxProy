package sena.docx.docxproy.modelo.VO;

@SuppressWarnings("ALL")
public class sede {
    private int idSede;
    private String direccion;
    private String nombreContacto;
    private int numeroContacto;
    private String correo;
    private empresa empresa;

    public int getIdSede() {
        return idSede;
    }

    public void setIdSede(int idSede) {
        this.idSede = idSede;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getNombreContacto() {
        return nombreContacto;
    }

    public void setNombreContacto(String nombreContacto) {
        this.nombreContacto = nombreContacto;
    }

    public int getNumeroContacto() {
        return numeroContacto;
    }

    public void setNumeroContacto(int numeroContacto) {
        this.numeroContacto = numeroContacto;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public sena.docx.docxproy.modelo.VO.empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(sena.docx.docxproy.modelo.VO.empresa empresa) {
        this.empresa = empresa;
    }
}
