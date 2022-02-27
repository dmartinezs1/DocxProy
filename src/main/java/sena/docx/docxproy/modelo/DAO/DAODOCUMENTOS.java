package sena.docx.docxproy.modelo.DAO;

import sena.docx.docxproy.modelo.UT.conexion1;
import sena.docx.docxproy.modelo.VO.documentos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DAODOCUMENTOS extends conexion1 {

    String sql;
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    int row;
    conexion1 c = new conexion1();

    public int addArchivos(documentos docs) throws SQLException {

        sql = "insert into documentos values (null, ?, ?)";

        try {
            con = c.conectar1();
            ps = con.prepareStatement(sql);

            ps.setString(1, docs.getNombre());
            ps.setString(2, docs.getArchivo());

            ps.executeUpdate();
            con.close();
            ps.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return row;
    }

}
