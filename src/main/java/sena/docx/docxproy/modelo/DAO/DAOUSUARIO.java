package sena.docx.docxproy.modelo.DAO;

import sena.docx.docxproy.modelo.UT.Conexion;
import sena.docx.docxproy.modelo.VO.cargo;
import sena.docx.docxproy.modelo.VO.contrasena;
import sena.docx.docxproy.modelo.VO.identificaciones;
import sena.docx.docxproy.modelo.VO.usuario;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("ALL")
public class DAOUSUARIO extends Conexion {

    public usuario identificar(usuario user) throws Exception {
        usuario usu = null;
        ResultSet rs = null;
        String sql = "SELECT U.NOMBREUSUARIO, U.IDUSUARIO, U.CLAVE, C.NOMBRECARGO FROM USUARIO U "
                + "INNER JOIN CARGO C ON U.IDCARGO = C.IDCARGO "
                + "WHERE U.ESTADO = 1 AND U.NUMEROIDENTIFICACION = '" + user.getNumeroIdentificacion() + "' "
                + "AND U.CLAVE = '" + getMD5(user.getClave()) + "'";
        try {
            this.conectar(false);
            rs = this.ejecutarOrdenDatos(sql);
            if (rs.next() == true) {
                usu = new usuario();
                usu.setId_usuario(rs.getInt("IDUSUARIO"));
                usu.setNombreUsuario(rs.getString("NOMBREUSUARIO"));
                usu.setNumeroIdentificacion(user.getNumeroIdentificacion());
                usu.setCargo(new cargo());
                usu.getCargo().setNombreCargo(rs.getString("NOMBRECARGO"));
                usu.setEstado(true);
                usu.setClave(getMD5(rs.getString("CLAVE")));
            }
            rs.close();
        } catch (Exception e) {
            System.out.println("Error" + e.getMessage());
        } finally {
            this.cerrar(false);
        }
        return usu;
    }

    public String getMD5(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] encBytes = md.digest(input.getBytes());
            BigInteger numero = new BigInteger(1, encBytes);
            String encString = numero.toString(16);
            while (encString.length() < 32) {
                encString = "0"+encString;
            }
            return encString;
        } catch (Exception e) {
            System.out.println("enctriptacion error "+e);
            throw new RuntimeException(e);
        }
    }

    public List<usuario> listarUsuarios() throws Exception {
        List<usuario> usuarios;
        usuario usu;
        ResultSet rs = null;
        String sql = "SELECT U.NUMEROIDENTIFICACION, U.IDUSUARIO, U.NOMBREUSUARIO, U.CLAVE, U.ESTADO, "
                + "C.NOMBRECARGO, I.tipo_identificacion "
                + "FROM usuario U INNER JOIN cargo C "
                + "ON C.IDCARGO = U.IDCARGO "
                + "INNER JOIN identificacion I "
                + "ON I.id_identificacion = U.TIPOIDENTIFICACION "
                + "ORDER BY U.IDUSUARIO";

        try {
            this.conectar(false);
            rs = this.ejecutarOrdenDatos(sql);
            usuarios = new ArrayList<>();
            while (rs.next() == true) {
                usu = new usuario();
                usu.setId_usuario(rs.getInt("IDUSUARIO"));
                usu.setNombreUsuario(rs.getString("NOMBREUSUARIO"));
                usu.setNumeroIdentificacion(Integer.parseInt(rs.getString("NUMEROIDENTIFICACION")));
                usu.setEstado(rs.getBoolean("ESTADO"));
                usu.setCargo(new cargo());
                usu.getCargo().setNombreCargo(rs.getString("NOMBRECARGO"));
                usu.setId_identificacion(new identificaciones());
                usu.getId_identificacion().setTipoIdentificacion(rs.getString("tipo_identificacion"));
                usuarios.add(usu);
            }
            this.cerrar(true);
        } catch (Exception e) {
            throw e;
        } finally {
        }
        return usuarios;
    }

    public List<usuario> listarEmpleados() throws Exception {
        List<usuario> empleados;
        usuario usu;
        ResultSet rs = null;
        String sql = "SELECT U.NUMEROIDENTIFICACION, U.IDUSUARIO, U.NOMBREUSUARIO, U.CLAVE, U.ESTADO, "
                + "C.NOMBRECARGO, I.tipo_identificacion "
                + "FROM usuario U INNER JOIN cargo C "
                + "ON C.IDCARGO = U.IDCARGO "
                + "INNER JOIN identificacion I "
                + "ON I.id_identificacion = U.TIPOIDENTIFICACION "
                + "WHERE C.IDCARGO = 2 "
                + "ORDER BY U.IDUSUARIO";

        try {
            this.conectar(false);
            rs = this.ejecutarOrdenDatos(sql);
            empleados = new ArrayList<>();
            while (rs.next() == true) {
                usu = new usuario();
                usu.setId_usuario(rs.getInt("IDUSUARIO"));
                usu.setNombreUsuario(rs.getString("NOMBREUSUARIO"));
                usu.setNumeroIdentificacion(Integer.parseInt(rs.getString("NUMEROIDENTIFICACION")));
                usu.setEstado(rs.getBoolean("ESTADO"));
                usu.setCargo(new cargo());
                usu.getCargo().setNombreCargo(rs.getString("NOMBRECARGO"));
                usu.setId_identificacion(new identificaciones());
                usu.getId_identificacion().setTipoIdentificacion(rs.getString("tipo_identificacion"));
                empleados.add(usu);
            }
            this.cerrar(true);
        } catch (Exception e) {
            throw e;
        } finally {
        }
        return empleados;
    }


    public List<usuario> listarEmpleadosRep() throws Exception {
        List<usuario> empleados;
        usuario usu;
        ResultSet rs = null;
        String sql = "SELECT U.IDUSUARIO, U.NOMBREUSUARIO, U.CORREOUS, U.ESTADO, C.NOMBRECARGO "
                + "FROM usuario U INNER JOIN cargo C "
                + "ON C.IDCARGO = U.IDCARGO "
                + "WHERE C.IDCARGO = 2 "
                + "ORDER BY U.IDUSUARIO";

        try {
            this.conectar(false);
            rs = this.ejecutarOrdenDatos(sql);
            empleados = new ArrayList<>();
            while (rs.next() == true) {
                usu = new usuario();
                usu.setId_usuario(rs.getInt("IDUSUARIO"));
                usu.setNombreUsuario(rs.getString("NOMBREUSUARIO"));
                usu.setEstado(rs.getBoolean("ESTADO"));
                usu.setCargo(new cargo());
                usu.getCargo().setNombreCargo(rs.getString("NOMBRECARGO"));
                empleados.add(usu);
            }
            this.cerrar(true);
        } catch (Exception e) {
            throw e;
        } finally {
        }
        return empleados;
    }

    public void registrarUsuarios(usuario usu) throws Exception {
        contrasena contra = new contrasena();
        String sql;
        sql = "INSERT INTO Usuario (NOMBREUSUARIO, CLAVE, ESTADO, IDCARGO) "
                + "VALUES ('" + usu.getNombreUsuario() + "', '"
                + contra.getPassword() + "', "
                + (usu.isEstado() == true ? "1" : "0")
                + ", " + usu.getCargo().getCodigo() + ")";
        try {
            this.conectar(false);
            this.ejecutarOrden(sql);
            this.cerrar(true);
        } catch (Exception e) {
            this.cerrar(false);
            throw e;
        }
    }

    public void registrarEmpleado(usuario usu) throws Exception {
        String sql;
        sql = "INSERT INTO Usuario (NOMBREUSUARIO, CLAVE, ESTADO, IDCARGO) "
                + "VALUES ('" + usu.getNombreUsuario() + "', '"
                + usu.getClave() + "', "
                + (usu.isEstado() == true ? "1" : "0")
                + ", 2)";
        try {
            this.conectar(false);
            this.ejecutarOrden(sql);
            this.cerrar(true);
        } catch (Exception e) {
            this.cerrar(false);
            throw e;
        }
    }

    public usuario leerUsuario(usuario usu) throws Exception {
        usuario usus = null;
        ResultSet rs = null;
        String sql = "SELECT U.IDUSUARIO, U.NOMBREUSUARIO, U.CORREOUS, U.CLAVE, U.ESTADO, U.IDCARGO, U.NUMEROIDENTIFICACION, U.TIPOIDENTIFICACION "
                + "FROM usuario U WHERE U.IDUSUARIO = " + usu.getId_usuario();

        try {
            this.conectar(false);
            rs = this.ejecutarOrdenDatos(sql);
            if (rs.next() == true) {
                usus = new usuario();
                usus.setId_usuario(rs.getInt("IDUSUARIO"));
                usus.setNombreUsuario(rs.getString("NOMBREUSUARIO"));
                usus.setCorreoUsuario(rs.getString("CORREOUS"));
                usus.setClave(rs.getString("CLAVE"));
                usus.setEstado(rs.getBoolean("ESTADO"));
                usus.setNumeroIdentificacion(rs.getInt("NUMEROIDENTIFICACION"));
                usus.setCargo(new cargo());
                usus.getCargo().setCodigo(rs.getInt("IDCARGO"));
                usus.setId_identificacion(new identificaciones());
                usus.getId_identificacion().setId_identificacion(rs.getInt("TIPOIDENTIFICACION"));
            }
            this.cerrar(true);
        } catch (Exception e) {
            this.cerrar(false);
            throw e;
        } finally {
        }
        return usus;
    }

    public usuario leerEmpleado(usuario usu) throws Exception {
        usuario usus = null;
        ResultSet rs = null;
        String sql = "SELECT U.IDUSUARIO, U.NOMBREUSUARIO, U.CORREOUS, U.CLAVE, U.ESTADO, U.IDCARGO, U.NUMEROIDENTIFICACION, U.TIPOIDENTIFICACION "
                + "FROM usuario U WHERE U.IDUSUARIO = " + usu.getId_usuario();

        try {
            this.conectar(false);
            rs = this.ejecutarOrdenDatos(sql);
            if (rs.next() == true) {
                usus = new usuario();
                usus.setId_usuario(rs.getInt("IDUSUARIO"));
                usus.setNombreUsuario(rs.getString("NOMBREUSUARIO"));
                usus.setCorreoUsuario(rs.getString("CORREOUS"));
                usus.setClave(rs.getString("CLAVE"));
                usus.setEstado(rs.getBoolean("ESTADO"));
                usus.setNumeroIdentificacion(rs.getInt("NUMEROIDENTIFICACION"));
                usus.setCargo(new cargo());
                usus.getCargo().setCodigo(rs.getInt("IDCARGO"));
                usus.setId_identificacion(new identificaciones());
                usus.getId_identificacion().setId_identificacion(rs.getInt("TIPOIDENTIFICACION"));
            }
            this.cerrar(true);
        } catch (Exception e) {
            this.cerrar(false);
            throw e;
        } finally {
        }
        return usus;
    }

    public void actualizarUsuarios(usuario usu) throws Exception {
        String sql = "UPDATE usuario SET NOMBREUSUARIO = '"
                + usu.getNombreUsuario() + "', CORREOUS = '"+ usu.getCorreoUsuario() +"'," +
                " ESTADO = "
                + (usu.isEstado() == true ? "1" : "0")
                + ", IDCARGO = "
                + usu.getCargo().getCodigo()
                + ", TIPOIDENTIFICACION = "
                + usu.getId_identificacion().getId_identificacion()
                + ", NUMEROIDENTIFICACION = "
                + usu.getNumeroIdentificacion()
                + " WHERE IDUSUARIO = " + usu.getId_usuario();
        try {
            this.conectar(false);
            this.ejecutarOrden(sql);
            this.cerrar(true);
        } catch (Exception e) {
            this.cerrar(false);
            throw e;
        }
    }

    public void actualizarEmpleados(usuario usu) throws Exception {
        String sql = "UPDATE usuario SET NOMBREUSUARIO = '"
                + usu.getNombreUsuario() + "', CORREOUS = '"+ usu.getCorreoUsuario() +"'," +
                " ESTADO = "
                + (usu.isEstado() == true ? "1" : "0")
                + ", TIPOIDENTIFICACION = "
                + usu.getId_identificacion().getId_identificacion()
                + ", NUMEROIDENTIFICACION = "
                + usu.getNumeroIdentificacion()
                + " WHERE IDUSUARIO = " + usu.getId_usuario();
        try {
            this.conectar(false);
            this.ejecutarOrden(sql);
            this.cerrar(true);
        } catch (Exception e) {
            this.cerrar(false);
            throw e;
        }
    }

    public void eliminarUsuario(usuario usu) throws Exception {
        String sql = "DELETE FROM USUARIO"
                + " WHERE IDUSUARIO = " + usu.getId_usuario();
        try {
            this.conectar(false);
            this.ejecutarOrden(sql);
            this.cerrar(true);
        } catch (Exception e) {
            this.cerrar(false);
            throw e;
        }
    }

    public void eliminarEmpleado(usuario usu) throws Exception {
        String sql = "DELETE FROM USUARIO"
                + " WHERE IDUSUARIO = " + usu.getId_usuario();
        try {
            this.conectar(false);
            this.ejecutarOrden(sql);
            this.cerrar(true);
        } catch (Exception e) {
            this.cerrar(false);
            throw e;
        }
    }

    public void cambiarVigencia(usuario usus) throws Exception {
        String sql = "UPDATE usuario SET estado = "
                + (usus.isEstado() == true ? "1" : "0")
                + " WHERE idusuario = " + usus.getId_usuario();
        try {
            this.conectar(false);
            this.ejecutarOrden(sql);
            this.cerrar(true);
        } catch (Exception e) {
            this.cerrar(false);
            throw e;
        }
    }

    public void changePassword(usuario usu) throws Exception {
        String sql = "UPDATE usuario SET CLAVE = '"
                + getMD5(usu.getClave())
                + "' WHERE IDUSUARIO = " + usu.getId_usuario();
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
