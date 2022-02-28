package sena.docx.docxproy.modelo.VO;

@SuppressWarnings("ALL")
public class programacion {

    private int idProgramacion;
    private String fechaInicioLabor;
    private String fechaFinLabor;
    private empresa empresa;
    private sede sede;
    private usuario usuario;
    private String horaSalida;
    private String horaEntrada;

    public int getIdProgramacion() {
        return idProgramacion;
    }

    public void setIdProgramacion(int idProgramacion) {
        this.idProgramacion = idProgramacion;
    }

    public String getFechaInicioLabor() {
        return fechaInicioLabor;
    }

    public void setFechaInicioLabor(String fechaInicioLabor) {
        this.fechaInicioLabor = fechaInicioLabor;
    }

    public String getFechaFinLabor() {
        return fechaFinLabor;
    }

    public void setFechaFinLabor(String fechaFinLabor) {
        this.fechaFinLabor = fechaFinLabor;
    }

    public sena.docx.docxproy.modelo.VO.empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(sena.docx.docxproy.modelo.VO.empresa empresa) {
        this.empresa = empresa;
    }

    public sena.docx.docxproy.modelo.VO.sede getSede() {
        return sede;
    }

    public void setSede(sena.docx.docxproy.modelo.VO.sede sede) {
        this.sede = sede;
    }

    public sena.docx.docxproy.modelo.VO.usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(sena.docx.docxproy.modelo.VO.usuario usuario) {
        this.usuario = usuario;
    }

    public String getHoraSalida() {
        return horaSalida;
    }

    public void setHoraSalida(String horaSalida) {
        this.horaSalida = horaSalida;
    }

    public String getHoraEntrada() {
        return horaEntrada;
    }

    public void setHoraEntrada(String horaEntrada) {
        this.horaEntrada = horaEntrada;
    }
}
