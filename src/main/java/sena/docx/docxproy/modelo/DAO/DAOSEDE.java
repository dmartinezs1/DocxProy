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
                ", E.nombreEmpresa " +
                "FROM sede S INNER JOIN empresa E " +
                "ON E.id_empresa = S.empresaCon_id " +
                "WHERE E.id_empresa = "+ emp.getId_empresa() + " " +
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


                users.add(s);
                System.out.println("Consulta exitosa"+ ps);

            }

            ps.close();


        } catch (Exception e) {
            System.out.println("Consulta no exitosa" + e.getMessage());
        } finally {
            con.close();
        }
        return users;
    }



}
