package sena.docx.docxproy.modelo.DAO;

import sena.docx.docxproy.modelo.UT.Conexion;
import sena.docx.docxproy.modelo.VO.tipoNovedad;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


@SuppressWarnings("all")
public class DAOTIPOSNOVEDADES extends Conexion {

    public List<tipoNovedad> listarTiposNovedades() throws Exception {
        List<tipoNovedad> tiposNovedades;
        tipoNovedad tNovedad;
        ResultSet rs = null;
        String sql = "SELECT TN.idTipoNovedad, TN.descripcion FROM tipo_novedad TN " +
                "ORDER BY TN.idTipoNovedad";

        try {
            this.conectar(false);
            rs = this.ejecutarOrdenDatos(sql);
            tiposNovedades = new ArrayList<>();
            while (rs.next() == true) {
                tNovedad = new tipoNovedad();
                tNovedad.setIdTipoNovedad(rs.getInt("idTipoNovedad"));
                tNovedad.setDescripcionNovedad(rs.getString("descripcion"));
                tiposNovedades.add(tNovedad);
            }
            this.cerrar(true);
        } catch (Exception e) {
            throw e;
        } finally {
        }
        return tiposNovedades;
    }
}
