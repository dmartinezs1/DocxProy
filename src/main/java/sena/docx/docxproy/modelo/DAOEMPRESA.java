package sena.docx.docxproy.modelo;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("ALL")
public class DAOEMPRESA extends Conexion{

    public List<empresa> listarEmpresas() throws Exception {
        List<empresa> empresas;
        empresa emp;
        ResultSet rs = null;
        String sql = "SELECT id_empresa, nombreEmpresa, estado "
                + "FROM empresa "
                + "ORDER BY id_empresa";

        try {
            this.conectar(false);
            rs = this.ejecutarOrdenDatos(sql);
            empresas = new ArrayList<>();
            while (rs.next() == true) {
                emp = new empresa();
                emp.setId_empresa(rs.getInt("id_empresa"));
                emp.setNombreEmpresa(rs.getString("nombreEmpresa"));
                emp.setEstado(rs.getBoolean("estado"));
                empresas.add(emp);
            }
            this.cerrar(true);
        } catch (Exception e) {
            throw e;
        } finally {
        }
        return empresas;
    }
}
