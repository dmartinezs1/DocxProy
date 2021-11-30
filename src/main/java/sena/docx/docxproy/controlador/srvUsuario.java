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
@WebServlet(name = "srvUsuario", urlPatterns = {"/srvUsuario"})
public class srvUsuario extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String accion = request.getParameter("accion");
        try {
            if (accion != null) {
                switch (accion) {
                    case "verificar":
                        verificar(request, response);
                        break;
                    case "cerrar":
                        cerrarsession(request, response);
                        break;
                    case "listarUsuarios":
                        listarUsuarios(request, response);
                        break;
                    case "listarEmpleados":
                        listarEmpleados(request, response);
                        break;
                    case "listarEmpresas":
                        listarEmpresas(request, response);
                        break;
                    case "nuevoUsuario":
                        presentarFormulario(request, response);
                        break;
                    case "nuevoEmpleado":
                        presentarFormularioSup(request, response);
                        break;
                    case "nuevaEmpresa":
                        presentarFormularioEmp(request, response);
                        break;
                    case "abrirPassword":
                        presentarPassword(request, response);
                        break;
                    case "registrarUsuario":
                        registrarUsuario(request, response);
                        break;
                    case "registrarEmpleado":
                        registrarEmpleado(request, response);
                        break;
                    case "registrarEmpresa":
                        registrarEmpresa(request, response);
                        break;
                    case "leerUsuario":
                        presentarUsuario(request, response);
                        break;
                    case "leerEmpleado":
                        presentarEmpleado(request, response);
                        break;
                    case "leerEmpresa":
                        presentarEmpresa(request, response);
                        break;
                    case "actualizarUsuario":
                        actualizarUsuario(request, response);
                        break;
                    case "actualizarEmpleado":
                        actualizarEmpleado(request, response);
                        break;
                    case "actualizarEmpresa":
                        actualizarEmpresa(request, response);
                        break;
                    case "actualizarPassword":
                        actPassword(request, response);
                        break;
                    case "eliminarUsuario":
                        eliminarUsuario(request, response);
                        break;
                    case "eliminarEmpleado":
                        eliminarEmpleado(request, response);
                        break;
                    case "eliminarEmpresa":
                        eliminarEmpresa(request, response);
                        break;
                    default:
                        response.sendRedirect("identificar.jsp");
                }
            } else if (request.getParameter("cambiar") != null) {
                cambiarEstado(request, response);
            } else if (request.getParameter("cambiarEmp") != null) {
                cambiarEstadoEmp(request, response);
            } else if (request.getParameter("cambiarEmpresa") != null) {
                cambiarEstadoEmpresa(request, response);
            } else {
                response.sendRedirect("identificar.jsp");
            }
        } catch (Exception e) {
            try {
                this.getServletConfig().getServletContext().getRequestDispatcher("/mensaje.jsp").forward(request, response);

            } catch (Exception ex) {
                System.out.println("Error" + e.getMessage());
            }
        }

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private void verificar(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession sesion;
        DAOUSUARIO dao;
        usuario usuario;
        usuario = this.obtenerUsuario(request);

        dao = new DAOUSUARIO();
        usuario = dao.identificar(usuario);
        if (usuario != null && usuario.getCargo().getNombreCargo().equals("ADMINISTRADOR")) {
            sesion = request.getSession();
            sesion.setAttribute("administrador", usuario);
            request.setAttribute("msje", "Bienvenido al sistema");
            this.getServletConfig().getServletContext().getRequestDispatcher("/vistas/Administrador/formAdministrador.jsp").forward(request, response);
        } else if (usuario != null && usuario.getCargo().getNombreCargo().equals("EMPLEADO")) {
            sesion = request.getSession();
            sesion.setAttribute("empleado", usuario);
            this.getServletConfig().getServletContext().getRequestDispatcher("/vistas/Empleado/formEmpleado.jsp").forward(request, response);
        } else if (usuario != null && usuario.getCargo().getNombreCargo().equals("SUPERVISOR")) {
            sesion = request.getSession();
            sesion.setAttribute("supervisor", usuario);
            this.getServletConfig().getServletContext().getRequestDispatcher("/vistas/Supervisor/formSupervisor.jsp").forward(request, response);
        }
        else {request.setAttribute("msje", "Credenciales Incorrectas");
            request.getRequestDispatcher("identificar.jsp").forward(request, response);
        }

    }

    private void cerrarsession(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession sesion = request.getSession();
        sesion.setAttribute("usuario", null);
        sesion.invalidate();
        response.sendRedirect("identificar.jsp");

    }

    private usuario obtenerUsuario(HttpServletRequest request) {
        usuario u = new usuario();
        u.setNombreUsuario(request.getParameter("txtUsu"));
        u.setClave(request.getParameter("txtPass"));
        return u;
    }

    private usuario obtenerPass(HttpServletRequest request) {
        usuario u = new usuario();
        u.setClave(request.getParameter("txtPass"));
        return u;
    }

    private void listarUsuarios(HttpServletRequest request, HttpServletResponse response) {
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
        }
    }

    private void listarEmpleados(HttpServletRequest request, HttpServletResponse response) {
        DAOUSUARIO dao = new DAOUSUARIO();
        List<usuario> usus = null;
        try {
            usus = dao.listarEmpleados();
            request.setAttribute("empleados", usus);

        } catch (Exception e) {
            request.setAttribute("msje", "No se pudo listar los usuarios" + e.getMessage());
        } finally {
            dao = null;
        }
        try {
            this.getServletConfig().getServletContext()
                    .getRequestDispatcher("/vistas/Supervisor/listarEmpleados.jsp").forward(request, response);
        } catch (Exception ex) {
            request.setAttribute("msje", "No se puedo realizar la petición" + ex.getMessage());
        }
    }

    private void listarEmpresas(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("listó empresas");
        DAOEMPRESA dao = new DAOEMPRESA();
        List<empresa> emp = null;
        try {
            emp = dao.listarEmpresas();
            request.setAttribute("empresas", emp);

        } catch (Exception e) {
            request.setAttribute("msje", "No se pudieron listar las empresas" + e.getMessage());
        } finally {
            dao = null;
        }
        try {
            this.getServletConfig().getServletContext()
                    .getRequestDispatcher("/vistas/Administrador/listarEmpresas.jsp").forward(request, response);
        } catch (Exception ex) {
            request.setAttribute("msje", "No se puedo realizar la petición" + ex.getMessage());
        }
    }

    private void presentarFormulario(HttpServletRequest request, HttpServletResponse response) {
        try {
            this.cargarCargos(request);
            this.getServletConfig().getServletContext()
                    .getRequestDispatcher("/vistas/Administrador/nuevoUsuarioAdmin.jsp").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("msje", "No se pudo cargar la vista");
        }
    }

    private void presentarFormularioEmp(HttpServletRequest request, HttpServletResponse response) {
        try {
            this.getServletConfig().getServletContext()
                    .getRequestDispatcher("/vistas/Administrador/nuevaEmpresa.jsp").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("msje", "No se pudo cargar la vista");
        }
    }

    private void presentarFormularioSup(HttpServletRequest request, HttpServletResponse response) {
        try {
            this.getServletConfig().getServletContext()
                    .getRequestDispatcher("/vistas/Supervisor/nuevoEmpleado.jsp").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("msje", "No se pudo cargar la vista");
        }
    }

    private void presentarPassword(HttpServletRequest request, HttpServletResponse response){
        try {
            this.getServletConfig().getServletContext()
                    .getRequestDispatcher("/vistas/Administrador/changePass.jsp").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("msje", "No se pudo cargar la vista");
        }
    }

    private void cargarCargos(HttpServletRequest request) {
        DAOCARGO dao = new DAOCARGO();
        List<cargo> car = null;
        try {
            car = dao.listarCargos();
            request.setAttribute("cargos", car);
        } catch (Exception e) {
            request.setAttribute("msje", "No se pudo cargar los cargos :( " + e.getMessage());
        } finally {
            car = null;
            dao = null;
        }
    }

    private void registrarUsuario(HttpServletRequest request, HttpServletResponse response) {
        DAOUSUARIO daoUsu;
        usuario usu = null;
        cargo carg;
        if (request.getParameter("txtNombre") != null
                && request.getParameter("txtClave") != null
                && request.getParameter("cboCargo") != null) {

            usu = new usuario();
            usu.setNombreUsuario(request.getParameter("txtNombre"));
            usu.setClave(request.getParameter("txtClave"));
            carg = new cargo();
            carg.setCodigo(Integer.parseInt(request.getParameter("cboCargo")));
            usu.setCargo(carg);
            if (request.getParameter("chkEstado") != null) {
                usu.setEstado(true);
            } else {
                usu.setEstado(false);
            }
            daoUsu = new DAOUSUARIO();
            try {
                daoUsu.registrarUsuarios(usu);
                response.sendRedirect("srvUsuario?accion=listarUsuarios");
            } catch (Exception e) {
                request.setAttribute("msje",
                        "No se pudo registrar el usuario" + e.getMessage());
                request.setAttribute("usuario", usu);
                this.presentarFormulario(request, response);
            }
        }
    }

    private void registrarEmpleado(HttpServletRequest request, HttpServletResponse response) {
        DAOUSUARIO daoUsu;
        usuario usu = null;
        if (request.getParameter("txtNombre") != null
                && request.getParameter("txtClave") != null) {
            usu = new usuario();
            usu.setNombreUsuario(request.getParameter("txtNombre"));
            usu.setClave(request.getParameter("txtClave"));
            if (request.getParameter("chkEstado") != null) {
                usu.setEstado(true);
            } else {
                usu.setEstado(false);
            }
            daoUsu = new DAOUSUARIO();
            try {
                daoUsu.registrarEmpleado(usu);
                response.sendRedirect("srvUsuario?accion=listarEmpleados");
            } catch (Exception e) {
                request.setAttribute("msje",
                        "No se pudo registrar el usuario" + e.getMessage());
                request.setAttribute("usuario", usu);
                this.presentarFormularioSup(request, response);
            }
        }
    }

    private void registrarEmpresa(HttpServletRequest request, HttpServletResponse response) {
        DAOEMPRESA daoempresa;
        empresa emp = null;
        System.out.println("llegó al servlet");
        if (request.getParameter("txtNombre") != null
                && request.getParameter("txtTelefono") != null
                && request.getParameter("txtDireccion") != null
                && request.getParameter("txtCorreo") != null
                && request.getParameter("txtNombreContacto") != null
                && request.getParameter("txtTelefonoContacto") != null) {
            emp = new empresa();
            emp.setNombreEmpresa(request.getParameter("txtNombre"));
            if (request.getParameter("chkEstado") != null) {
                emp.setEstado(true);
            } else {
                emp.setEstado(false);
            }
            emp.setTelefono(Integer.parseInt(request.getParameter("txtTelefono")));
            emp.setDireccion(request.getParameter("txtDireccion"));
            emp.setCorreoEmpresarial(request.getParameter("txtCorreo"));
            emp.setNombreContacto(request.getParameter("txtNombreContacto"));
            emp.setTelefonoContacto(Integer.parseInt(request.getParameter("txtTelefonoContacto")));

            daoempresa = new DAOEMPRESA();
            try {
                daoempresa.registrarEmpresa(emp);
                response.sendRedirect("srvUsuario?accion=listarEmpresas");
            } catch (Exception e) {
                request.setAttribute("msje",
                        "No se pudo registrar el usuario" + e.getMessage());
                request.setAttribute("empresa", emp);
                this.presentarFormularioEmp(request, response);
                System.out.println(e);
            }
        }
    }

    private void presentarUsuario(HttpServletRequest request, HttpServletResponse response) {
        DAOUSUARIO dao;
        usuario usus;
        if (request.getParameter("cod") != null) {
            usus = new usuario();
            usus.setId_usuario(Integer.parseInt(request.getParameter("cod")));

            dao = new DAOUSUARIO();
            try {
                usus = dao.leerUsuario(usus);
                if (usus != null) {
                    request.setAttribute("usuario", usus);
                } else {
                    request.setAttribute("msje", "No se encontró el usuario");
                }
            } catch (Exception e) {
                request.setAttribute("msje", "No se pudo acceder a la base de datos" + e.getMessage());
            }
        } else {
            request.setAttribute("msje", "No se tiene el parámetro necesario");
        }
        try {
            this.cargarCargos(request);
            this.getServletConfig().getServletContext().
                    getRequestDispatcher("/vistas/Administrador/actualizarUsuario.jsp"
                    ).forward(request, response);
        } catch (Exception e) {
            request.setAttribute("msje", "No se pudo realizar la operacion" + e.getMessage());
        }
    }

    private void presentarEmpleado(HttpServletRequest request, HttpServletResponse response) {
        DAOUSUARIO dao;
        usuario usus;
        if (request.getParameter("cod") != null) {
            usus = new usuario();
            usus.setId_usuario(Integer.parseInt(request.getParameter("cod")));

            dao = new DAOUSUARIO();
            try {
                usus = dao.leerEmpleado(usus);
                if (usus != null) {
                    request.setAttribute("usuario", usus);
                } else {
                    request.setAttribute("msje", "No se encontró el empleado");
                }
            } catch (Exception e) {
                request.setAttribute("msje", "No se pudo acceder a la base de datos" + e.getMessage());
            }
        } else {
            request.setAttribute("msje", "No se tiene el parámetro necesario");
        }
        try {
            this.getServletConfig().getServletContext().
                    getRequestDispatcher("/vistas/Supervisor/actualizarEmpleado.jsp"
                    ).forward(request, response);
        } catch (Exception e) {
            request.setAttribute("msje", "No se pudo realizar la operacion" + e.getMessage());
        }
    }

    private void presentarEmpresa(HttpServletRequest request, HttpServletResponse response) {
        DAOEMPRESA daoempresa;
        empresa emps;
        if (request.getParameter("cod") != null) {
            emps = new empresa();
            emps.setId_empresa(Integer.parseInt(request.getParameter("cod")));

            daoempresa = new DAOEMPRESA();
            try {
                emps = daoempresa.leerEmpresa(emps);
                if (emps != null) {
                    request.setAttribute("empresa", emps);
                } else {
                    request.setAttribute("msje", "No se encontró el empleado");
                    System.out.println("msje");
                }
            } catch (Exception e) {
                request.setAttribute("msje", "No se pudo acceder a la base de datos" + e.getMessage());
                System.out.println(e);
            }
        } else {
            request.setAttribute("msje", "No se tiene el parámetro necesario");
        }
        try {
            this.getServletConfig().getServletContext().
                    getRequestDispatcher("/vistas/Administrador/actualizarEmpresa.jsp"
                    ).forward(request, response);
        } catch (Exception e) {
            request.setAttribute("msje", "No se pudo realizar la operacion" + e.getMessage());
            System.out.println(e);
        }
    }

    private void actualizarUsuario(HttpServletRequest request, HttpServletResponse response) {
        DAOUSUARIO daoUsu;
        usuario usus = null;
        cargo car;

        if (request.getParameter("hCodigo") != null
                && request.getParameter("txtNombre") != null
                && request.getParameter("txtClave") != null
                && request.getParameter("cboCargo") != null) {

            usus = new usuario();
            usus.setId_usuario(Integer.parseInt(request.getParameter("hCodigo")));
            usus.setNombreUsuario(request.getParameter("txtNombre"));
            usus.setClave(request.getParameter("txtClave"));
            car = new cargo();
            car.setCodigo(Integer.parseInt(request.getParameter("cboCargo")));
            usus.setCargo(car);
            if (request.getParameter("chkEstado") != null) {
                usus.setEstado(true);
            } else {
                usus.setEstado(false);
            }
            daoUsu = new DAOUSUARIO();
            try {
                daoUsu.actualizarUsuarios(usus);
                response.sendRedirect("srvUsuario?accion=listarUsuarios");
            } catch (Exception e) {
                request.setAttribute("msje",
                        "No se pudo actualizar el usuario" + e.getMessage());
                request.setAttribute("usuario", usus);
                System.out.println(e);
            }
            try {
                this.cargarCargos(request);
                this.getServletConfig().getServletContext().
                        getRequestDispatcher("/vistas/Administrador/actualizarUsuario.jsp"
                        ).forward(request, response);
            } catch (Exception ex) {
                request.setAttribute("msje", "No se pudo realizar la operacion" + ex.getMessage());
                System.out.println(ex);
            }
        }
    }

    private void actualizarEmpleado(HttpServletRequest request, HttpServletResponse response) {
        DAOUSUARIO daoUsu;
        usuario usus = null;
        cargo car;

        if (request.getParameter("hCodigo") != null
                && request.getParameter("txtNombre") != null
                && request.getParameter("txtClave") != null){

            usus = new usuario();
            usus.setId_usuario(Integer.parseInt(request.getParameter("hCodigo")));
            usus.setNombreUsuario(request.getParameter("txtNombre"));
            usus.setClave(request.getParameter("txtClave"));
            if (request.getParameter("chkEstado") != null) {
                usus.setEstado(true);
            } else {
                usus.setEstado(false);
            }
            daoUsu = new DAOUSUARIO();
            try {
                daoUsu.actualizarEmpleados(usus);
                response.sendRedirect("srvUsuario?accion=listarEmpleados");
            } catch (Exception e) {
                request.setAttribute("msje",
                        "No se pudo actualizar el usuario" + e.getMessage());
                request.setAttribute("usuario", usus);
            }
            try {
                this.getServletConfig().getServletContext().
                        getRequestDispatcher("/vistas/Supervisor/actualizarEmpleado.jsp"
                        ).forward(request, response);
            } catch (Exception ex) {
                request.setAttribute("msje", "No se pudo realizar la operacion" + ex.getMessage());
            }
        }
    }

    private void actPassword (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DAOUSUARIO dao;
        usuario usus = null;

        if(request.getParameter("id")!=null
            && request.getParameter("passnew")!=null) {
            usus = new usuario();
            usus.setId_usuario(Integer.parseInt(request.getParameter("id")));
            usus.setClave(request.getParameter("passnew"));
        }
        dao = new DAOUSUARIO();
        try {
            dao.changePassword(usus);
            request.getRequestDispatcher("srvUsuario?accion=cerrar").forward(request, response);
        }catch(Exception e) {
            System.out.println("error al cambiar password "+e.getMessage());
        }

    }

    private void eliminarUsuario(HttpServletRequest request, HttpServletResponse response) {
        DAOUSUARIO dao = new DAOUSUARIO();
        usuario usus = new usuario();
        if (request.getParameter("cod") != null) {
            usus.setId_usuario(Integer.parseInt(request.getParameter("cod")));
            try {
                dao.eliminarUsuario(usus);
                response.sendRedirect("srvUsuario?accion=listarUsuarios");
            } catch (Exception e) {
                request.setAttribute("msje", "No se pudo acceder a la base de datos" + e.getMessage());
            }
        } else {
            request.setAttribute("msje", "No se encontro el usuario");
        }
    }

    private void eliminarEmpleado(HttpServletRequest request, HttpServletResponse response) {
        DAOUSUARIO dao = new DAOUSUARIO();
        usuario usus = new usuario();
        if (request.getParameter("cod") != null) {
            usus.setId_usuario(Integer.parseInt(request.getParameter("cod")));
            try {
                dao.eliminarEmpleado(usus);
                response.sendRedirect("srvUsuario?accion=listarEmpleados");
            } catch (Exception e) {
                request.setAttribute("msje", "No se pudo acceder a la base de datos" + e.getMessage());
            }
        } else {
            request.setAttribute("msje", "No se encontro el usuario");
        }
    }

    private void eliminarEmpresa(HttpServletRequest request, HttpServletResponse response) {
        DAOEMPRESA dao = new DAOEMPRESA();
        empresa emps = new empresa();
        if (request.getParameter("cod") != null) {
            emps.setId_empresa(Integer.parseInt(request.getParameter("cod")));
            try {
                dao.eliminarEmpresa(emps);
                response.sendRedirect("srvUsuario?accion=listarEmpresa");
            } catch (Exception e) {
                request.setAttribute("msje", "No se pudo acceder a la base de datos" + e.getMessage());
            }
        } else {
            request.setAttribute("msje", "No se encontro el usuario");
        }
    }

    private void cambiarEstado(HttpServletRequest request, HttpServletResponse response) {
        DAOUSUARIO dao;
        usuario usu;
        try {
            dao = new DAOUSUARIO();
            usu = new usuario();

            if (request.getParameter("cambiar").equals("activar")) {
                usu.setEstado(true);
            } else {
                usu.setEstado(false);
            }

            if (request.getParameter("cod") != null) {
                usu.setId_usuario(Integer.parseInt(request.getParameter("cod")));
                dao.cambiarVigencia(usu);
            } else {
                request.setAttribute("msje", "No se obtuvo el id del usuario");
            }

        } catch (Exception e) {
            request.setAttribute("msje", e.getMessage());
        }
        this.listarUsuarios(request, response);
    }

    private void cambiarEstadoEmp(HttpServletRequest request, HttpServletResponse response) {
        DAOUSUARIO dao;
        usuario usu;
        try {
            dao = new DAOUSUARIO();
            usu = new usuario();

            if (request.getParameter("cambiarEmp").equals("activar")) {
                usu.setEstado(true);
            } else {
                usu.setEstado(false);
            }

            if (request.getParameter("cod") != null) {
                usu.setId_usuario(Integer.parseInt(request.getParameter("cod")));
                dao.cambiarVigencia(usu);
            } else {
                request.setAttribute("msje", "No se obtuvo el id del usuario");
            }

        } catch (Exception e) {
            request.setAttribute("msje", e.getMessage());
        }
        this.listarEmpleados(request, response);
    }

    private void cambiarEstadoEmpresa(HttpServletRequest request, HttpServletResponse response) {
        DAOEMPRESA dao;
        empresa emps;
        try {
            dao = new DAOEMPRESA();
            emps = new empresa();

            if (request.getParameter("cambiarEmpresa").equals("activar")) {
                emps.setEstado(true);
            } else {
                emps.setEstado(false);
            }

            if (request.getParameter("cod") != null) {
                emps.setId_empresa(Integer.parseInt(request.getParameter("cod")));
                dao.cambiarVigenciaEmp(emps);
            } else {
                request.setAttribute("msje", "No se obtuvo el id del usuario");
            }

        } catch (Exception e) {
            request.setAttribute("msje", e.getMessage());
        }
        this.listarEmpresas(request, response);
    }

    private void actualizarEmpresa(HttpServletRequest request, HttpServletResponse response) {
        DAOEMPRESA daoEmp;
        empresa emps = null;

        if (request.getParameter("hCodigo") != null
                && request.getParameter("txtNombre") != null
                && request.getParameter("txtTelefono") != null
                && request.getParameter("txtDireccion") != null
                && request.getParameter("txtCorreoEmpresarial") != null
                && request.getParameter("txtNombreContacto") != null
                && request.getParameter("txtTelefonoContacto") != null){
            emps = new empresa();
            emps.setId_empresa(Integer.parseInt(request.getParameter("hCodigo")));
            emps.setNombreEmpresa(request.getParameter("txtNombre"));
            emps.setTelefono(Integer.parseInt(request.getParameter("txtTelefono")));
            emps.setDireccion(request.getParameter("txtDireccion"));
            emps.setCorreoEmpresarial(request.getParameter("txtCorreoEmpresarial"));
            emps.setNombreContacto(request.getParameter("txtNombreContacto"));
            emps.setTelefonoContacto(Integer.parseInt(request.getParameter("txtTelefonoContacto")));
            if (request.getParameter("chkEstado") != null) {
                emps.setEstado(true);
            } else {
                emps.setEstado(false);
            }
            daoEmp = new DAOEMPRESA();
            try {
                daoEmp.actualizarEmpresa(emps);
                response.sendRedirect("srvUsuario?accion=listarEmpresas");
            } catch (Exception e) {
                request.setAttribute("msje",
                        "No se pudo actualizar el usuario" + e.getMessage());
                request.setAttribute("empresa", emps);
            }
            try {
                this.getServletConfig().getServletContext().
                        getRequestDispatcher("/vistas/Administrador/actualizarEmpresa.jsp"
                        ).forward(request, response);
            } catch (Exception ex) {
                request.setAttribute("msje", "No se pudo realizar la operacion" + ex.getMessage());
            }
        }
    }
}
