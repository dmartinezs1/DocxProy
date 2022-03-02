package sena.docx.docxproy.modelo.DAO;

import sena.docx.docxproy.modelo.UT.conexion1;
import sena.docx.docxproy.modelo.VO.cargo;
import sena.docx.docxproy.modelo.VO.empresa;
import sena.docx.docxproy.modelo.VO.sede;
import sena.docx.docxproy.modelo.VO.usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("all")
public class DAOSEDE extends conexion1 {

    String sql;
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    conexion1 c = new conexion1();
    int row;

    public List listar(empresa emp) throws SQLException {

        System.out.println(emp.getId_empresa());
        List<sede> users = new ArrayList<>();
        sql = "SELECT S.idSede, S.direccion, S.nombreContacto, S.numeroContacto, S.correoElectronico" +
                ", E.nombreEmpresa, E.id_empresa " +
                "FROM sede S INNER JOIN empresa E " +
                "ON E.id_empresa = S.empresaCon_id " +
                "WHERE E.id_empresa = " + emp.getId_empresa() + " " +
                "ORDER BY S.idSede";
        try {
            con = c.conectar1(); //Abriendo la conexión a la BD
            ps = con.prepareStatement(sql); //preparar sentencia
            rs = ps.executeQuery();//Ejeución de la sentencia guardar resultado en el resulset

            while (rs.next()) {
                sede s = new sede();
                s.setIdSede(rs.getInt(1));
                s.setDireccion(rs.getString(2));
                s.setNombreContacto(rs.getString(3));
                s.setNumeroContacto(rs.getInt(4));
                s.setCorreo(rs.getString(5));
                s.setEmpresa(new empresa());
                s.getEmpresa().setNombreEmpresa(rs.getString(6));
                s.getEmpresa().setId_empresa(rs.getInt(7));


                users.add(s);
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

    public int registrar(sede se) throws SQLException {

        sql = "INSERT INTO sede (direccion, nombreContacto, numeroContacto, correoElectronico, empresaCon_id) "
                + "VALUES (?,?,?,?,?)";
        try {
            con = c.conectar1();
            ps = con.prepareStatement(sql);
            ps.setString(1, se.getDireccion());
            ps.setString(2, se.getNombreContacto());
            ps.setInt(3, se.getNumeroContacto());
            ps.setString(4, se.getCorreo());
            ps.setInt(5, se.getEmpresa().getId_empresa());

            System.out.println(ps);
            ps.executeUpdate();
            ps.close();
            System.out.println("Se registró una sede");

        } catch (Exception e) {
            System.out.println("Error al registrar la sede" + e.getMessage());
        } finally {
            con.close();
        }
        return row;//Retorna cantidad de filas afectadas
    }

    public sede leerSede(sede se) throws Exception {

        sede sedes = new sede();
        String sql = "SELECT S.idSede, S.direccion, S.nombreContacto, S.numeroContacto, S.correoElectronico, S.empresaCon_id"
                + " FROM sede S WHERE S.idSede = " + se.getIdSede();

        try {
            con = c.conectar1(); //Abriendo la conexión a la BD
            ps = con.prepareStatement(sql); //preparar sentencia
            rs = ps.executeQuery();//Ejecución de la sentencia guardar resultado en el resultset

            while (rs.next()) {
                sedes.setIdSede(rs.getInt(1));
                sedes.setDireccion(rs.getString(2));
                sedes.setNombreContacto(rs.getString(3));
                sedes.setNumeroContacto(rs.getInt(4));
                sedes.setCorreo(rs.getString(5));
                sedes.setEmpresa(new empresa());
                sedes.getEmpresa().setId_empresa(rs.getInt(6));

                System.out.println("Consulta exitosa" + ps);

            }

            ps.close();


        } catch (Exception e) {
            System.out.println("Consulta no exitosa" + e.getMessage());
        } finally {
            con.close();
        }
        return sedes;
    }

    public sede leerDatosSede(sede se) throws Exception {

        sede sedes = new sede();
        String sql = "SELECT S.idSede, S.direccion, S.empresaCon_id, E.nombreEmpresa " +
                " FROM sede S INNER JOIN empresa E " +
                "ON S.empresaCon_id = E.id_empresa " +
                "WHERE S.idSede = " + se.getIdSede();

        try {
            con = c.conectar1(); //Abriendo la conexión a la BD
            ps = con.prepareStatement(sql); //preparar sentencia
            rs = ps.executeQuery();//Ejecución de la sentencia guardar resultado en el resultset

            while (rs.next()) {
                sedes.setIdSede(rs.getInt(1));
                sedes.setDireccion(rs.getString(2));
                sedes.setEmpresa(new empresa());
                sedes.getEmpresa().setId_empresa(rs.getInt(3));
                sedes.getEmpresa().setNombreEmpresa(rs.getString(4));

                System.out.println("Consulta exitosa" + ps);

            }

            ps.close();


        } catch (Exception e) {
            System.out.println("Consulta no exitosa" + e.getMessage());
        } finally {
            con.close();
        }
        return sedes;
    }

    public int actualizarSede(sede se) throws SQLException{
        sql = "UPDATE sede SET direccion=?, nombreContacto=?, numeroContacto=?, " +
              "correoElectronico=?, empresaCon_id=? " +
              "WHERE idSede ="+se.getIdSede();
        try{
            con= c.conectar1();
            ps=con.prepareStatement(sql);
            ps.setString(1,se.getDireccion());
            ps.setString(2,se.getNombreContacto());
            ps.setInt(3,se.getNumeroContacto());
            ps.setString(4,se.getCorreo());
            ps.setInt(5,se.getEmpresa().getId_empresa());

            System.out.println(ps);
            ps.executeUpdate();
            ps.close();
            System.out.println("Se cambió la sede");

        }catch (Exception e){
            System.out.println("no se pudo actualizar la sede "+e.getMessage());
        }finally {
            con.close();
        }
        return row;
    }

    public int eliminar(sede se) throws SQLException {
        sql = "DELETE FROM sede WHERE idSede=" + se.getIdSede();
        try {
            con = c.conectar1(); //Abriendo la conexión a la BD
            ps = con.prepareStatement(sql); //preparar sentencia

            System.out.println(ps);
            ps.executeUpdate();
            ps.close();
            System.out.println("Se eliminó una sede");

        } catch (Exception e) {
            System.out.println("Error al eliminar la sede " + e.getMessage());
        } finally {
            con.close();
        }
        return row;
    }


}
