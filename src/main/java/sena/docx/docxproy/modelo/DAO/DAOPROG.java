package sena.docx.docxproy.modelo.DAO;

import sena.docx.docxproy.modelo.UT.conexion1;
import sena.docx.docxproy.modelo.VO.empresa;
import sena.docx.docxproy.modelo.VO.programacion;
import sena.docx.docxproy.modelo.VO.sede;
import sena.docx.docxproy.modelo.VO.usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("all")
public class DAOPROG extends conexion1 {

    String sql;
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    conexion1 c = new conexion1();
    int row;

    public List listar(usuario usu) throws SQLException {

        System.out.println(usu.getId_usuario());
        List<programacion> progs = new ArrayList<>();
        sql = "SELECT P.idProgramacion, P.fechaInicioLabor, P.fechaFinLabor, " +
                "P.horaSalida, P.horaEntrada, " +
                "S.direccion, U.NOMBREUSUARIO, E.nombreEmpresa " +
                "FROM programacion P INNER JOIN usuario U " +
                "ON U.IDUSUARIO = P.usuario_id " +
                "INNER JOIN sede S " +
                "ON S.idSede = P.sede_id " +
                "INNER JOIN empresa E " +
                "ON E.id_empresa = P.empresa_id " +
                "WHERE U.IDUSUARIO = " + usu.getId_usuario() + " " +
                "ORDER BY P.idProgramacion";
        try {
            con = c.conectar1(); //Abriendo la conexión a la BD
            ps = con.prepareStatement(sql); //preparar sentencia
            rs = ps.executeQuery();//Ejeución de la sentencia guardar resultado en el resulset

            while (rs.next()) {
                programacion p = new programacion();
                p.setIdProgramacion(rs.getInt(1));
                p.setFechaInicioLabor(rs.getString(2));
                p.setFechaFinLabor(rs.getString(3));
                p.setHoraSalida(rs.getString(4));
                p.setHoraEntrada(rs.getString(5));
                p.setSede(new sede());
                p.getSede().setDireccion(rs.getString(6));
                p.setUsuario(new usuario());
                p.getUsuario().setNombreUsuario(rs.getString(7));
                p.setEmpresa(new empresa());
                p.getEmpresa().setNombreEmpresa(rs.getString(8));

                progs.add(p);
                System.out.println("Consulta exitosa" + ps);

            }

            ps.close();


        } catch (Exception e) {
            System.out.println("Consulta no exitosa" + e.getMessage());
        } finally {
            con.close();
        }
        return progs;
    }

    public List listarHS(sede se) throws SQLException {

        System.out.println(se.getIdSede());
        List<programacion> progs = new ArrayList<>();
        sql = "SELECT P.idProgramacion, P.fechaInicioLabor, P.fechaFinLabor, " +
                "P.horaSalida, P.horaEntrada, " +
                "S.direccion, U.NOMBREUSUARIO, E.nombreEmpresa " +
                "FROM programacion P INNER JOIN usuario U " +
                "ON U.IDUSUARIO = P.usuario_id " +
                "INNER JOIN sede S " +
                "ON S.idSede = P.sede_id " +
                "INNER JOIN empresa E " +
                "ON E.id_empresa = P.empresa_id " +
                "WHERE S.idSede = " + se.getIdSede() + " " +
                "ORDER BY P.idProgramacion";
        try {
            con = c.conectar1(); //Abriendo la conexión a la BD
            ps = con.prepareStatement(sql); //preparar sentencia
            rs = ps.executeQuery();//Ejeución de la sentencia guardar resultado en el resulset

            while (rs.next()) {
                programacion p = new programacion();
                p.setIdProgramacion(rs.getInt(1));
                p.setFechaInicioLabor(rs.getString(2));
                p.setFechaFinLabor(rs.getString(3));
                p.setHoraSalida(rs.getString(4));
                p.setHoraEntrada(rs.getString(5));
                p.setSede(new sede());
                p.getSede().setDireccion(rs.getString(6));
                p.setUsuario(new usuario());
                p.getUsuario().setNombreUsuario(rs.getString(7));
                p.setEmpresa(new empresa());
                p.getEmpresa().setNombreEmpresa(rs.getString(8));

                progs.add(p);
                System.out.println("Consulta exitosa" + ps);

            }

            ps.close();


        } catch (Exception e) {
            System.out.println("Consulta no exitosa" + e.getMessage());
        } finally {
            con.close();
        }
        return progs;
    }

    public int registrar(programacion pr) throws SQLException {

        sql = "INSERT INTO programacion (fechaInicioLabor, " +
                "fechaFinLabor, sede_id, usuario_id, empresa_id, horaSalida, horaEntrada)" +
                "VALUES (?,?,?,?,?,?,?)";
        try {
            con = c.conectar1();
            ps = con.prepareStatement(sql);
            ps.setString(1, pr.getFechaInicioLabor());
            ps.setString(2, pr.getFechaFinLabor());
            ps.setInt(3, pr.getSede().getIdSede());
            ps.setInt(4, pr.getUsuario().getId_usuario());
            ps.setInt(5, pr.getEmpresa().getId_empresa());
            ps.setString(6, pr.getHoraSalida());
            ps.setString(7, pr.getHoraEntrada());

            System.out.println(ps);
            ps.executeUpdate();
            ps.close();
            System.out.println("Se registró una programación");

        } catch (Exception e) {
            System.out.println("Error al registrar la programción" + e.getMessage());
        } finally {
            con.close();
        }
        return row;//Retorna cantidad de filas afectadas
    }

    public List<usuario> listarEmpleadosProgramacion() throws Exception {
        List<usuario> usuarios = new ArrayList<>();
        ResultSet rs = null;
        String sql = "select * from usuario WHERE IDCARGO = 2 AND ESTADO = 1 "
                + "ORDER BY IDUSUARIO";

        try {
            con=c.conectar1(); //Abriendo la conexi�n a la BD
            ps=con.prepareStatement(sql); //preparar sentencia
            rs=ps.executeQuery();//Ejeuci�n de la sentencia guardar resultado en el resulset

            while(rs.next()) {
                usuario usuario1= new usuario();
                usuario1.setId_usuario(rs.getInt("IDUSUARIO"));
                usuario1.setNombreUsuario(rs.getString("NOMBREUSUARIO"));

                usuarios.add(usuario1);
                System.out.println("Consulta exitosa");

            }

            ps.close();


        }catch(Exception e) {
            System.out.println("Consulta no exitosa" +e.getMessage());
        }
        finally {
            con.close();
        }
        return usuarios;
    }

}
