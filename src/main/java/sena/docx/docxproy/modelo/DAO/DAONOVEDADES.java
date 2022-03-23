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

}
