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

    public void registrarEmpresa(empresa emp) throws Exception {
        String sql;
        sql = "INSERT INTO empresa (nombreEmpresa, estado) "
                + "VALUES ('" + emp.getNombreEmpresa() + "', "
                + (emp.isEstado() == true ? "1" : "0")
                + ")";
        try {
            this.conectar(false);
            this.ejecutarOrden(sql);
            this.cerrar(true);
        } catch (Exception e) {
            this.cerrar(false);
            throw e;
        }
    }

    public void actualizarEmpresa(empresa emps) throws Exception {
        String sql = "UPDATE empresa SET nombreEmpresa = '"
                + emps.getNombreEmpresa() + "', estado = "
                + (emps.isEstado() == true ? "1" : "0")
                + " WHERE id_empresa = " + emps.getId_empresa();
        try {
            this.conectar(false);
            this.ejecutarOrden(sql);
            this.cerrar(true);
        } catch (Exception e) {
            this.cerrar(false);
            throw e;
        }
    }

    public empresa leerEmpresa(empresa emp) throws Exception {
        empresa emps = null;
        ResultSet rs = null;
        String sql = "SELECT U.id_empresa, U.nombreEmpresa, U.ESTADO "
                + "FROM empresa U WHERE U.id_empresa = " + emp.getId_empresa();

        try {
            this.conectar(false);
            rs = this.ejecutarOrdenDatos(sql);
            if (rs.next() == true) {
                emps = new empresa();
                emps.setId_empresa(rs.getInt("id_empresa"));
                emps.setNombreEmpresa(rs.getString("nombreEmpresa"));
                emps.setEstado(rs.getBoolean("estado"));
            }
            this.cerrar(true);
        } catch (Exception e) {
            this.cerrar(false);
            throw e;
        } finally {
        }
        return emps;
    }
}
