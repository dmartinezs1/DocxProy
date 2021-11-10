package sena.docx.docxproy.modelo;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DAOUSUARIO extends Conexion {
    public usuario identificar(usuario user) throws Exception {
        usuario usu = null;

        ResultSet rs = null;
        String sql = "SELECT U.IDUSUARIO, C.NOMBRECARGO FROM USUARIO U "
                + "INNER JOIN CARGO C ON U.IDCARGO = C.IDCARGO "
                + "WHERE U.ESTADO = 1 AND U.NOMBREUSUARIO = '" + user.getNombreUsuario() + "' "
                + "AND U.CLAVE = '" + user.getClave() + "'";

        try {
            this.conectar(false);
            rs = this.ejecutarOrdenDatos(sql);

            if (rs.next() == true) {
                usu = new usuario();
                usu.setId_usuario(rs.getInt("IDUSUARIO"));
                usu.setNombreUsuario(user.getNombreUsuario());
                usu.setCargo(new cargo());
                usu.getCargo().setNombreCargo(rs.getString("NOMBRECARGO"));
                usu.setEstado(true);
            }
            rs.close();

        } catch (Exception e) {
            System.out.println("Error" + e.getMessage());
        } finally {
            this.cerrar(false);
        }
        return usu;
    }

    public List<usuario> listarUsuarios() throws Exception {
        List<usuario> usuarios;
        usuario usu;
        ResultSet rs = null;
        String sql = "SELECT U.IDUSUARIO, U.NOMBREUSUARIO, U.ESTADO, C.NOMBRECARGO "
                + "FROM usuario U INNER JOIN cargo C "
                + "ON C.IDCARGO = U.IDCARGO "
                + "ORDER BY U.IDUSUARIO";

        try {
            this.conectar(false);
            rs = this.ejecutarOrdenDatos(sql);
            usuarios = new ArrayList<>();
            while (rs.next() == true) {
                usu = new usuario();
                usu.setId_usuario(rs.getInt("IDUSUARIO"));
                usu.setNombreUsuario(rs.getString("NOMBREUSUARIO"));
                usu.setEstado(rs.getBoolean("ESTADO"));
                usu.setCargo(new cargo());
                usu.getCargo().setNombreCargo(rs.getString("NOMBRECARGO"));
                usuarios.add(usu);
            }
            this.cerrar(true);
        } catch (Exception e) {
            throw e;
        } finally {
        }
        return usuarios;
    }
}
