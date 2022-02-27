package sena.docx.docxproy.modelo.VO;

public class documentos {
    private String nombre;
    private String archivo;

    public documentos() {
    }

    public documentos(String nombre, String archivo) {
        this.nombre = nombre;
        this.archivo = archivo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getArchivo() {
        return archivo;
    }

    public void setArchivo(String archivo) {
        this.archivo = archivo;
    }
}
