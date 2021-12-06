package sena.docx.docxproy.controlador;

import sena.docx.docxproy.modelo.*;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@SuppressWarnings("ALL")
public class srvAdministrador extends HttpServlet {

    public void listarUsuarios(HttpServletRequest request, HttpServletResponse response) {
        DAOUSUARIO dao = new DAOUSUARIO();
        List<usuario> usus = null;
        try {
            usus = dao.listarUsuarios();
            request.setAttribute("usuarios", usus);

        } catch (Exception e) {
            request.setAttribute("msje", "No se pudo listar los usuarios" + e.getMessage());
        } finally {
            dao = null;
        }
        try {
            this.getServletConfig().getServletContext()
                    .getRequestDispatcher("/vistas/Administrador/listaUsuariosAdmin.jsp").forward(request, response);
        } catch (Exception ex) {
            request.setAttribute("msje", "No se puedo realizar la petición" + ex.getMessage());
            System.out.println("no se pudo cargar la vista porque "+ex);
        }
    }

}
