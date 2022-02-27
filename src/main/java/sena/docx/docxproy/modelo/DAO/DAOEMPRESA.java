package sena.docx.docxproy.modelo.DAO;

import sena.docx.docxproy.modelo.UT.Conexion;
import sena.docx.docxproy.modelo.VO.cargo;
import sena.docx.docxproy.modelo.VO.empresa;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("ALL")
public class DAOEMPRESA extends Conexion {

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

    public List<empresa> listarEmpresasSede() throws Exception {
        List<empresa> empresas;
        empresa emp;
        ResultSet rs = null;
        String sql = "SELECT E.id_empresa, E.nombreEmpresa FROM empresa E "
                + "ORDER BY E.id_empresa";

        try {
            this.conectar(false);
            rs = this.ejecutarOrdenDatos(sql);
            empresas = new ArrayList<>();
            while (rs.next() == true) {
                emp = new empresa();
                emp.setId_empresa(rs.getInt("id_empresa"));
                emp.setNombreEmpresa(rs.getString("nombreEmpresa"));
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
        sql = "INSERT INTO empresa (nombreEmpresa, estado, telefono, direccion, correoEmpresarial, nombreContacto, telefonoContacto) "
                + "VALUES ('" + emp.getNombreEmpresa() + "', "
                + (emp.isEstado() == true ? "1" : "0") + ", "
                + emp.getTelefono() + ", '"
                + emp.getDireccion() + "', '"
                + emp.getCorreoEmpresarial() + "', '"
                + emp.getNombreContacto() + "', "
                + emp.getTelefonoContacto()
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
                + ", telefono = " + emps.getTelefono() + ", "
                + "direccion = '" + emps.getDireccion() + "', "
                + "correoEmpresarial = '" + emps.getCorreoEmpresarial() + "', "
                + "nombreContacto = '" + emps.getNombreContacto() + "', "
                + "telefonoContacto = "+ emps.getTelefonoContacto()
                + " WHERE id_empresa = " + emps.getId_empresa();

        try {
            this.conectar(false);
            this.ejecutarOrden(sql);
            this.cerrar(true);
        } catch (Exception e) {
            System.out.println(e);
            this.cerrar(false);
            throw e;
        }
    }

    public empresa leerEmpresa(empresa emp) throws Exception {
        empresa emps = null;
        ResultSet rs = null;
        String sql = "SELECT U.id_empresa, U.nombreEmpresa, U.telefono, U.direccion, U.correoEmpresarial, U.nombreContacto, U.telefonoContacto, U.ESTADO "
                + "FROM empresa U WHERE U.id_empresa = " + emp.getId_empresa();

        try {
            this.conectar(false);
            rs = this.ejecutarOrdenDatos(sql);
            if (rs.next() == true) {
                emps = new empresa();
                emps.setId_empresa(rs.getInt("id_empresa"));
                emps.setNombreEmpresa(rs.getString("nombreEmpresa"));
                emps.setEstado(rs.getBoolean("estado"));
                emps.setTelefono(rs.getInt("telefono"));
                emps.setDireccion(rs.getString("direccion"));
                emps.setCorreoEmpresarial(rs.getString("correoEmpresarial"));
                emps.setNombreContacto(rs.getString("nombreContacto"));
                emps.setTelefonoContacto(rs.getInt("telefonoContacto"));
            }
            this.cerrar(true);
        } catch (Exception e) {
            System.out.println(e);
            this.cerrar(false);
            throw e;

        } finally {
        }
        return emps;
    }

    public void cambiarVigenciaEmp(empresa emps) throws Exception {
        String sql = "UPDATE empresa SET estado = "
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

    public void eliminarEmpresa(empresa emp) throws Exception {
        String sql = "DELETE FROM empresa"
                + " WHERE id_empresa = " + emp.getId_empresa();
        try {
            this.conectar(false);
            this.ejecutarOrden(sql);
            this.cerrar(true);
        } catch (Exception e) {
            this.cerrar(false);
            throw e;
        }
    }
}
