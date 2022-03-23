package sena.docx.docxproy.modelo.VO;

@SuppressWarnings("ALL")
public class novedades {

    private int idNovedades;
    private String fechaNovedad;
    private String detallesNovedad;
    private tipoNovedad TipoNovedad;
    private usuario Empleado;
    private empresa Empresa;

    public int getIdNovedades() {
        return idNovedades;
    }

    public void setIdNovedades(int idNovedades) {
        this.idNovedades = idNovedades;
    }

    public String getFechaNovedad() {
        return fechaNovedad;
    }

    public void setFechaNovedad(String fechaNovedad) {
        this.fechaNovedad = fechaNovedad;
    }

    public String getDetallesNovedad() {
        return detallesNovedad;
    }

    public void setDetallesNovedad(String detallesNovedad) {
        this.detallesNovedad = detallesNovedad;
    }

    public tipoNovedad getTipoNovedad() {
        return TipoNovedad;
    }

    public void setTipoNovedad(tipoNovedad tipoNovedad) {
        TipoNovedad = tipoNovedad;
    }

    public usuario getEmpleado() {
        return Empleado;
    }

    public void setEmpleado(usuario empleado) {
        Empleado = empleado;
    }

    public empresa getEmpresa() {
        return Empresa;
    }

    public void setEmpresa(empresa empresa) {
        Empresa = empresa;
    }
}
