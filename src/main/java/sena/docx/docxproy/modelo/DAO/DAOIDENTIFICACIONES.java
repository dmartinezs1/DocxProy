package sena.docx.docxproy.modelo.DAO;

import sena.docx.docxproy.modelo.UT.Conexion;
import sena.docx.docxproy.modelo.VO.identificaciones;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("ALL")
public class DAOIDENTIFICACIONES extends Conexion {

    public List<identificaciones> listarIdentificaciones() throws Exception {
        List<identificaciones> identificaciones;
        identificaciones ide;
        ResultSet rs = null;
        String sql = "SELECT i.id_identificacion, i.tipo_identificacion FROM identificacion i " +
                "WHERE i.id_identificacion <> 4 " +
                "ORDER BY i.id_identificacion";

        try {
            this.conectar(false);
            rs = this.ejecutarOrdenDatos(sql);
            identificaciones = new ArrayList<>();
            while (rs.next() == true) {
                ide = new identificaciones();
                ide.setId_identificacion(rs.getInt("id_identificacion"));
                ide.setTipoIdentificacion(rs.getString("tipo_identificacion"));
                identificaciones.add(ide);
            }
            this.cerrar(true);
        } catch (Exception e) {
            throw e;
        } finally {
        }
        return identificaciones;
    }
}
