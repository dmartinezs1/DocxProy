package sena.docx.docxproy.modelo.DAO;

import sena.docx.docxproy.modelo.UT.conexion1;
import sena.docx.docxproy.modelo.VO.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


@SuppressWarnings("all")
public class DAONOVEDADES extends conexion1 {

    String sql;
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    conexion1 c = new conexion1();
    int row;

    public List listar(sena.docx.docxproy.modelo.VO.usuario usu) throws SQLException {

        System.out.println(usu.getId_usuario());
        List<novedades> novedades = new ArrayList<>();
        sql = "SELECT N.idNovedades, N.fechaNovedad, U.NOMBREUSUARIO, TN.descripcion, E.nombreEmpresa " +
                "FROM novedades N INNER JOIN usuario U " +
                "ON U.IDUSUARIO = N.empleado_id " +
                "INNER JOIN tipo_novedad TN " +
                "ON TN.idTipoNovedad = N.tipoNovedad_id " +
                "INNER JOIN empresa E " +
                "ON E.id_empresa = N.empresa_id " +
                "WHERE U.IDUSUARIO = " + usu.getId_usuario() + " " +
                "ORDER BY N.idNovedades";
        try {
            con = c.conectar1(); //Abriendo la conexión a la BD
            ps = con.prepareStatement(sql); //preparar sentencia
            rs = ps.executeQuery();//Ejeución de la sentencia guardar resultado en el resulset

            while (rs.next()) {
                novedades n = new novedades();
                n.setIdNovedades(rs.getInt(1));
                n.setFechaNovedad(rs.getString(2));
                n.setEmpleado(new usuario());
                n.getEmpleado().setNombreUsuario(rs.getString(3));
                n.setTipoNovedad(new tipoNovedad());
                n.getTipoNovedad().setDescripcionNovedad(rs.getString(4));
                n.setEmpresa(new empresa());
                n.getEmpresa().setNombreEmpresa(rs.getString(5));
                novedades.add(n);
                System.out.println("Consulta exitosa" + ps);

            }

            ps.close();


        } catch (Exception e) {
            System.out.println(ps);
            System.out.println("Consulta no exitosa" + e.getMessage());
        } finally {
            con.close();
        }
        return novedades;
    }

    public int registrar(novedades nov) throws SQLException {

        sql = "INSERT INTO novedades (fechaNovedad, " +
                "detallesNovedad, tipoNovedad_id, empleado_id, empresa_id)" +
                "VALUES (?,?,?,?,?)";
        try {
            con = c.conectar1();
            ps = con.prepareStatement(sql);
            ps.setString(1, nov.getFechaNovedad());
            ps.setString(2, nov.getDetallesNovedad());
            ps.setInt(3, nov.getTipoNovedad().getIdTipoNovedad());
            ps.setInt(4, nov.getEmpleado().getId_usuario());
            ps.setInt(5, nov.getEmpresa().getId_empresa());
            System.out.println(ps);
            ps.executeUpdate();
            ps.close();
            System.out.println("Se registró una novedad");

        } catch (Exception e) {
            System.out.println("Error al registrar la novedad" + e.getMessage());
        } finally {
            con.close();
        }
        return row;//Retorna cantidad de filas afectadas
    }

    public novedades leerNovedades(novedades nov) throws Exception {

        novedades novedades = new novedades();
        String sql = "SELECT N.idNovedades, N.fechaNovedad, N.detallesNovedad, " +
                "N.tipoNovedad_id, N.empleado_id, N.empresa_id, " +
                "TN.descripcion, U.NOMBREUSUARIO, E.nombreEmpresa " +
                "FROM novedades N " +
                "INNER JOIN empresa E ON " +
                "N.empresa_id = E.id_empresa " +
                "INNER JOIN usuario U ON " +
                "N.empleado_id = U.IDUSUARIO " +
                "INNER JOIN tipo_novedad TN ON " +
                "N.tipoNovedad_id = TN.idTipoNovedad " +
                "WHERE N.idNovedades ="+ nov.getIdNovedades();

        try {
            con = c.conectar1(); //Abriendo la conexión a la BD
            ps = con.prepareStatement(sql); //preparar sentencia
            rs = ps.executeQuery();//Ejecución de la sentencia guardar resultado en el resultset

            while (rs.next()) {
                novedades.setIdNovedades(rs.getInt(1));
                novedades.setFechaNovedad(rs.getString(2));
                novedades.setDetallesNovedad(rs.getString(3));
                novedades.setTipoNovedad(new tipoNovedad());
                novedades.getTipoNovedad().setIdTipoNovedad(rs.getInt(4));
                novedades.setEmpleado(new usuario());
                novedades.getEmpleado().setId_usuario(rs.getInt(5));
                novedades.setEmpresa(new empresa());
                novedades.getEmpresa().setId_empresa(rs.getInt(6));
                novedades.getTipoNovedad().setDescripcionNovedad(rs.getString(7));
                novedades.getEmpleado().setNombreUsuario(rs.getString(8));
                novedades.getEmpresa().setNombreEmpresa(rs.getString(9));

                System.out.println("Consulta exitosa" + ps);

            }

            ps.close();


        } catch (Exception e) {
            System.out.println("Consulta no exitosa" + e.getMessage());
        } finally {
            con.close();
        }
        return novedades;
    }

    public int actualizarNovedad(novedades nov) throws SQLException{
        System.out.println("entró a actualizar programacion");
        sql =   "UPDATE novedades SET fechaNovedad=?, detallesNovedad=?, tipoNovedad_id=?, " +
                "empleado_id=?, empresa_id=? " +
                "WHERE idNovedades ="+ nov.getIdNovedades();
        try{
            con= c.conectar1();
            ps=con.prepareStatement(sql);
            ps.setString(1,nov.getFechaNovedad());
            ps.setString(2,nov.getDetallesNovedad());
            ps.setInt(3,nov.getTipoNovedad().getIdTipoNovedad());
            ps.setInt(4,nov.getEmpleado().getId_usuario());
            ps.setInt(5,nov.getEmpresa().getId_empresa());

            System.out.println(ps);
            ps.executeUpdate();
            ps.close();
            System.out.println("Se cambió la novedad");

        }catch (Exception e){
            System.out.println("no se pudo actualizar la novedad "+e.getMessage());
        }finally {
            con.close();
        }
        return row;
    }

    public int eliminar(novedades nov) throws SQLException {
        sql = "DELETE FROM novedades WHERE idNovedades=" + nov.getIdNovedades();
        try {
            con = c.conectar1(); //Abriendo la conexión a la BD
            ps = con.prepareStatement(sql); //preparar sentencia

            System.out.println(ps);
            ps.executeUpdate();
            ps.close();
            System.out.println("Se eliminó la novedad");

        } catch (Exception e) {
            System.out.println("Error al eliminar la novedad " + e.getMessage());
        } finally {
            con.close();
        }
        return row;
    }

}
