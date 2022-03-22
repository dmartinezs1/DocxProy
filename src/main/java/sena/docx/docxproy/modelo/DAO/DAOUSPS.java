package sena.docx.docxproy.modelo.DAO;

import sena.docx.docxproy.modelo.UT.conexion1;
import sena.docx.docxproy.modelo.VO.cargo;
import sena.docx.docxproy.modelo.VO.contrasena;
import sena.docx.docxproy.modelo.VO.usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("ALL")
public class DAOUSPS {

    String sql;
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    int row;
    conexion1 c = new conexion1();
    contrasena contra = new contrasena();

    public int validarCorreo(String nombreUsuario) throws Exception {

        usuario u = new usuario();
        int total = 0;

        sql = "SELECT COUNT(*) AS cantidad from usuario WHERE NOMBREUSUARIO=?";
        try {
            con = c.conectar1();
            ps = con.prepareStatement(sql);
            ps.setString(1, nombreUsuario);
            rs = ps.executeQuery();
            while (rs.next()) {
                total = rs.getInt("cantidad");
            }
            System.out.println(ps);
            ps.close();
            System.out.println("El total de registros que coinciden es " + total);
        } catch (Exception e) {
            System.out.println("Error en la consulta " + e.getMessage());
        } finally {
            con.close();
        }
        return total;
    }

    public List listar() throws SQLException {

        List<usuario> users = new ArrayList<>();
        sql = "SELECT U.IDUSUARIO, U.NOMBREUSUARIO, U.CORREOUS, U.ESTADO, C.NOMBRECARGO " +
                "FROM usuario U INNER JOIN cargo C " +
                "ON C.IDCARGO = U.IDCARGO " +
                "WHERE C.IDCARGO = 2 " +
                "ORDER BY U.IDUSUARIO";
        try {
            con = c.conectar1(); //Abriendo la conexión a la BD
            ps = con.prepareStatement(sql); //preparar sentencia
            rs = ps.executeQuery();//Ejeución de la sentencia guardar resultado en el resulset

            while (rs.next()) {
                usuario u = new usuario();
                u.setId_usuario(rs.getInt(1));
                u.setNombreUsuario(rs.getString(2));
                u.setCorreoUsuario(rs.getString(3));
                u.setEstado(rs.getBoolean(4));
                u.setCargo(new cargo());
                u.getCargo().setNombreCargo(rs.getString(5));

                users.add(u);
                System.out.println("Consulta exitosa" + ps);

            }

            ps.close();


        } catch (Exception e) {
            System.out.println("Consulta no exitosa" + e.getMessage());
        } finally {
            con.close();
        }
        return users;
    }

    public int registrar(usuario u) throws SQLException {

        sql = "INSERT INTO usuario (NOMBREUSUARIO, CORREOUS, CLAVE, ESTADO, IDCARGO, TIPOIDENTIFICACION, " +
                " NUMEROIDENTIFICACION) "
                + "VALUES (?,?,?,?,?,?,?)";
        try {
            con = c.conectar1();
            ps = con.prepareStatement(sql);
            ps.setString(1, u.getNombreUsuario());
            ps.setString(2, u.getCorreoUsuario());
            ps.setString(3, u.getClave());
            ps.setBoolean(4, u.isEstado());
            ps.setInt(5, u.getCargo().getCodigo());
            ps.setInt(6, u.getId_identificacion().getId_identificacion());
            ps.setInt(7,u.getNumeroIdentificacion());

            System.out.println(ps);
            ps.executeUpdate();
            ps.close();
            System.out.println("Se registró un usuario");

        } catch (Exception e) {
            System.out.println("Error al registrar el usuario" + e.getMessage());
        } finally {
            con.close();
        }
        return row;//Retorna cantidad de filas afectadas
    }

    public int registrarEmpleado(usuario u) throws SQLException {

        sql = "INSERT INTO usuario (NOMBREUSUARIO, CORREOUS, CLAVE, ESTADO, IDCARGO, TIPOIDENTIFICACION, " +
                " NUMEROIDENTIFICACION) "
                + "VALUES (?,?,?,?,?,?,?)";
        try {
            con = c.conectar1();
            ps = con.prepareStatement(sql);
            ps.setString(1, u.getNombreUsuario());
            ps.setString(2, u.getCorreoUsuario());
            ps.setString(3, u.getClave());
            ps.setBoolean(4, u.isEstado());
            ps.setInt(5, u.getCargo().getCodigo());
            ps.setInt(6, u.getId_identificacion().getId_identificacion());
            ps.setInt(7,u.getNumeroIdentificacion());

            System.out.println(ps);
            ps.executeUpdate();
            ps.close();
            System.out.println("Se registró un usuario");

        } catch (Exception e) {
            System.out.println("Error al registrar el usuario" + e.getMessage());
        } finally {
            con.close();
        }
        return row;//Retorna cantidad de filas afectadas
    }

    public usuario CertificadoLaboral(int idusuario) throws SQLException {
        usuario u = new usuario();
        sql = "SELECT U.IDUSUARIO, U.NOMBREUSUARIO, C.NOMBRECARGO FROM usuario U " +
                "INNER JOIN cargo C " +
                "ON U.IDCARGO = C.IDCARGO " +
                "WHERE IDUSUARIO=?";

        try {
            con = c.conectar1();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idusuario);
            rs = ps.executeQuery();
                u.setId_usuario(rs.getInt(1));
                u.setNombreUsuario(rs.getString(2));
                u.setCargo(new cargo());
                u.getCargo().setNombreCargo(rs.getString(3));
            ps.close();
            System.out.println("Se encontró el Usuario");
        } catch (Exception e) {
            System.out.println("Se encontró el Usuario" + e.getMessage());
        } finally {
            con.close();
        }
        return u;
    }
}
