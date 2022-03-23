package sena.docx.docxproy.controlador;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanArrayDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import sena.docx.docxproy.modelo.DAO.*;
import sena.docx.docxproy.modelo.UT.Configmail;
import sena.docx.docxproy.modelo.VO.*;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.io.File;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@SuppressWarnings("ALL")
@MultipartConfig
@WebServlet(name = "srvUsuario", urlPatterns = {"/srvUsuario"})
public class srvUsuario extends HttpServlet {

    private String host;
    private String puerto;
    private String remitente;
    private String password;
    private String pathFiles = "C:\\Users\\Daniel\\Desktop\\ProyectoGrado\\DocxProy\\src\\main\\webapp\\files";
    private File uploads = new File(pathFiles);
    private String[] extensiones = {".pdf", ".rar", ".zip"};

    DAOUSUARIO daousuario = new DAOUSUARIO();

    public void init() {
        ServletContext contexto = getServletContext();
        host = contexto.getInitParameter("host");
        puerto = contexto.getInitParameter("puerto");
        remitente = contexto.getInitParameter("remitente");
        password = contexto.getInitParameter("password");
    }

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
                    case "listarEmpresasSupervisor":
                        listarEmpresasSupervisor(request, response);
                        break;
                    case "listarSedes":
                        listarSedes(request, response);
                        break;
                    case "listarSedesSupervisor":
                        listarSedesSupervisor(request, response);
                        break;
                    case "listarHS":
                        listarHorariosSede(request, response);
                        break;
                    case "listarHSsupervisor":
                        listarHorariosSedeSupervisor(request, response);
                        break;
                    case "CertificadoLaboral":
                        CertificadoLaboral(request, response);
                        break;
                    case "nuevoUsuario":
                        presentarFormulario(request, response);
                        break;
                    case "nuevaSede":
                        presentarFormularioSede(request, response);
                        break;
                    case "registrarProgramacion":
                        presentarFormularioProgramacion(request, response);
                        break;
                    case "registrarProgramacionSupervisor":
                        presentarFormularioProgramacionSupervisor(request, response);
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
                    case "abrirPasswordEmp":
                        presentarPasswordEmp(request, response);
                        break;
                    case "listarHorariosAdm":
                        listarHorariosAdm(request, response);
                        break;
                    case "listarNovedadesAdmin":
                        listarNovedadesAdmin(request, response);
                        break;
                    case "listarProgramacionesEmpleado":
                        listarProgramacionesEmpleado(request, response);
                        break;
                    case "abrirPasswordSup":
                        presentarPasswordSup(request, response);
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
                    case "registrarSede":
                        registrarSede(request, response);
                        break;
                    case "registrarHorario":
                        registrarHorario(request, response);
                        break;
                    case "registrarHorarioSupervisor":
                        registrarHorarioSupervisor(request, response);
                        break;
                    case "leerUsuario":
                        presentarUsuario(request, response);
                        break;
                    case "editarHorarioHS":
                        presentarProgramacion(request, response);
                        break;
                    case "editarHorarioHSsupervisor":
                        presentarProgramacionSupervisor(request, response);
                        break;
                    case "leerSede":
                        presentarSede(request, response);
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
                    case "actualizarProgramacion":
                        actualizarProgramacion(request, response);
                        break;
                    case "actualizarProgramacionSupervisor":
                        actualizarProgramacionSupervisor(request, response);
                        break;
                    case "actualizarSede":
                        actualizarSede(request, response);
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
                    case "eliminarSede":
                        eliminarSede(request, response);
                        break;
                    case "eliminarProgramacion":
                        eliminarProgramacion(request, response);
                        break;
                    case "validarCorreo":
                        validarCorreo(request, response);
                        break;
                    case "reporteEmpleados":
                        reporteEmpleados(request, response);
                        break;
                    case "presentarDocumento":
                        presentarDocumento(request, response);
                        break;
                    case "registrarDocumento":
                        saveDocument(request, response);
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

    private void registrarHorario(HttpServletRequest request, HttpServletResponse response) {
        DAOPROG daoprog;
        programacion programacion = null;
        sede sede;
        empresa emp;
        usuario usuario;
        if (request.getParameter("txtFechaInicioLabor") != null
                && request.getParameter("txtFechaFinLabor") != null
                && request.getParameter("txtHoraInicioLabor") != null
                && request.getParameter("txtHoraFinLabor") != null
                && request.getParameter("txtIdEmpresa") != null
                && request.getParameter("txtIdSede") != null
                && request.getParameter("cboEmpleado") != null) {

            programacion = new programacion();
            programacion.setFechaInicioLabor(request.getParameter("txtFechaInicioLabor"));
            programacion.setFechaFinLabor(request.getParameter("txtFechaFinLabor"));
            programacion.setHoraEntrada(request.getParameter("txtHoraInicioLabor"));
            programacion.setHoraSalida(request.getParameter("txtHoraFinLabor"));
            emp = new empresa();
            emp.setId_empresa(Integer.parseInt(request.getParameter("txtIdEmpresa")));
            sede = new sede();
            sede.setIdSede(Integer.parseInt(request.getParameter("txtIdSede")));
            programacion.setEmpresa(emp);
            programacion.setSede(sede);
            usuario = new usuario();
            usuario.setId_usuario(Integer.parseInt(request.getParameter("cboEmpleado")));
            programacion.setUsuario(usuario);

            int codSede = Integer.parseInt(request.getParameter("txtIdSede"));

            daoprog = new DAOPROG();
            try {
                daoprog.registrar(programacion);
                System.out.println("registró programación");
                response.sendRedirect("srvUsuario?accion=listarHS&cod="+codSede);
            } catch (Exception e) {
                request.setAttribute("msje",
                        "No se pudo registrar la programación" + e.getMessage());
                request.setAttribute("programacion", programacion);
                this.presentarFormularioProgramacion(request, response);
            }
        }
    }

    private void registrarHorarioSupervisor(HttpServletRequest request, HttpServletResponse response) {
        DAOPROG daoprog;
        programacion programacion = null;
        sede sede;
        empresa emp;
        usuario usuario;
        if (request.getParameter("txtFechaInicioLabor") != null
                && request.getParameter("txtFechaFinLabor") != null
                && request.getParameter("txtHoraInicioLabor") != null
                && request.getParameter("txtHoraFinLabor") != null
                && request.getParameter("txtIdEmpresa") != null
                && request.getParameter("txtIdSede") != null
                && request.getParameter("cboEmpleado") != null) {

            programacion = new programacion();
            programacion.setFechaInicioLabor(request.getParameter("txtFechaInicioLabor"));
            programacion.setFechaFinLabor(request.getParameter("txtFechaFinLabor"));
            programacion.setHoraEntrada(request.getParameter("txtHoraInicioLabor"));
            programacion.setHoraSalida(request.getParameter("txtHoraFinLabor"));
            emp = new empresa();
            emp.setId_empresa(Integer.parseInt(request.getParameter("txtIdEmpresa")));
            sede = new sede();
            sede.setIdSede(Integer.parseInt(request.getParameter("txtIdSede")));
            programacion.setEmpresa(emp);
            programacion.setSede(sede);
            usuario = new usuario();
            usuario.setId_usuario(Integer.parseInt(request.getParameter("cboEmpleado")));
            programacion.setUsuario(usuario);

            int codSede = Integer.parseInt(request.getParameter("txtIdSede"));

            daoprog = new DAOPROG();
            try {
                daoprog.registrar(programacion);
                System.out.println("registró programación");
                response.sendRedirect("srvUsuario?accion=listarHSsupervisor&cod="+codSede);
            } catch (Exception e) {
                request.setAttribute("msje",
                        "No se pudo registrar la programación" + e.getMessage());
                request.setAttribute("programacion", programacion);
                this.presentarFormularioProgramacionSupervisor(request, response);
            }
        }
    }

    private void listarHorariosSede(HttpServletRequest request, HttpServletResponse response) {
        DAOPROG dao = new DAOPROG();
        DAOSEDE daosede = new DAOSEDE();
        List<programacion> pr = null;
        empresa emp;
        usuario usu;
        sede se;

        if (request.getParameter("cod") != null) {
            se = new sede();
            se.setIdSede(Integer.parseInt(request.getParameter("cod")));
            //se.setEmpresa(emp);
            try {
                se = daosede.leerDatosSede(se);
                request.setAttribute("sede", se);
            } catch (Exception e) {
                request.setAttribute("msje", "No se pudieron obtener los datos de sede" + e.getMessage());
                System.out.println("no se obtuvieron los datos de sede" + e.getMessage());
            }
            try {
                pr = dao.listarHS(se);
                System.out.println(pr.size());
                request.setAttribute("programaciones", pr);
            } catch (Exception e) {
                request.setAttribute("msje", "No se pudieron listar los horarios" + e.getMessage());
                System.out.println("no se pudieron listar los horarios" + e.getMessage());
            } finally {
                dao = null;
            }
            try {
                this.getServletConfig().getServletContext()
                        .getRequestDispatcher("/vistas/Administrador/listarHS.jsp").forward(request, response);
            } catch (Exception ex) {
                request.setAttribute("msje", "No se puedo realizar la petición" + ex.getMessage());
                System.out.println("No se puedo realizar la petición" + ex.getMessage());
            }
        }
    }

    private void listarHorariosSedeSupervisor(HttpServletRequest request, HttpServletResponse response) {
        DAOPROG dao = new DAOPROG();
        DAOSEDE daosede = new DAOSEDE();
        List<programacion> pr = null;
        empresa emp;
        usuario usu;
        sede se;

        if (request.getParameter("cod") != null) {
            se = new sede();
            se.setIdSede(Integer.parseInt(request.getParameter("cod")));
            //se.setEmpresa(emp);
            try {
                se = daosede.leerDatosSede(se);
                request.setAttribute("sede", se);
            } catch (Exception e) {
                request.setAttribute("msje", "No se pudieron obtener los datos de sede" + e.getMessage());
                System.out.println("no se obtuvieron los datos de sede" + e.getMessage());
            }
            try {
                pr = dao.listarHS(se);
                System.out.println(pr.size());
                request.setAttribute("programaciones", pr);
            } catch (Exception e) {
                request.setAttribute("msje", "No se pudieron listar los horarios" + e.getMessage());
                System.out.println("no se pudieron listar los horarios" + e.getMessage());
            } finally {
                dao = null;
            }
            try {
                this.getServletConfig().getServletContext()
                        .getRequestDispatcher("/vistas/Supervisor/listarHSsupervisor.jsp").forward(request, response);
            } catch (Exception ex) {
                request.setAttribute("msje", "No se puedo realizar la petición" + ex.getMessage());
                System.out.println("No se puedo realizar la petición" + ex.getMessage());
            }
        }
    }

    private void registrarSede(HttpServletRequest request, HttpServletResponse response) {
        DAOSEDE daosede;
        sede sede = null;
        empresa emp;
        if (request.getParameter("txtDireccion") != null
                && request.getParameter("txtNombreContacto") != null
                && request.getParameter("txtNumeroContacto") != null
                && request.getParameter("txtCorreo") != null
                && request.getParameter("cboEmpresa") != null) {

            sede = new sede();
            sede.setDireccion(request.getParameter("txtDireccion"));
            sede.setNombreContacto(request.getParameter("txtNombreContacto"));
            sede.setNumeroContacto(Integer.parseInt(request.getParameter("txtNumeroContacto")));
            sede.setCorreo(request.getParameter("txtCorreo"));
            emp = new empresa();
            emp.setId_empresa(Integer.parseInt(request.getParameter("cboEmpresa")));
            sede.setEmpresa(emp);

            daosede = new DAOSEDE();
            try {
                daosede.registrar(sede);
                response.sendRedirect("srvUsuario?accion=listarEmpresas");
            } catch (Exception e) {
                request.setAttribute("msje",
                        "No se pudo registrar el usuario" + e.getMessage());
                request.setAttribute("sede", sede);
                this.presentarFormularioSede(request, response);
            }
        }
    }

    private void reporteEmpleados(HttpServletRequest request, HttpServletResponse response) throws IOException {

        DAOUSPS udao;
        usuario usuario;
        udao = new DAOUSPS();
        DAOUSUARIO daousuario = new DAOUSUARIO();
        ServletOutputStream out = response.getOutputStream();
        try {
            java.io.InputStream logo = this.getServletConfig()
                    .getServletContext()
                    .getResourceAsStream("Reportes/img/logo.png");
            if (logo != null) {
                System.out.println("logo cargado con exito");
            }
            java.io.InputStream reporteUsuario = this.getServletConfig()
                    .getServletContext()
                    .getResourceAsStream("Reportes/RepEmpleados.jasper");
            if (reporteUsuario != null) {
                System.out.println("reporte cargado con exito");
            }
            //Validar que no vengan vacios
            if (logo != null && reporteUsuario != null) {
                //Crear lista de la clase Vo para guardar resultado de la consulta
                List<usuario> reporteUsuario1 = new ArrayList<>();
                reporteUsuario1 = udao.listar();

                //Declarar variable tipo Jasper Reports asignando el reporte creado
                JasperReport report = (JasperReport) JRLoader.loadObject(reporteUsuario);
                //Declarar variable ds para asignar el reporteUsuario1
                JRBeanArrayDataSource ds = new JRBeanArrayDataSource(reporteUsuario1.toArray());

                //Mapeamos los parámetros del Jasper reports
                Map<String, Object> parameters = new HashMap();
                parameters.put("ds", ds);
                parameters.put("logo", logo);
                //Formateamos la salida del reporte
                response.setContentType("application/pdf");
                //Para abrir el reporte en otra pestaña
                response.addHeader("Content-disposition", "inline; filename=ReporteUsuarios.pdf");
                //Imprimimos el reporte
                JasperPrint jasperPrint = JasperFillManager.fillReport(report, parameters, ds);
                JasperExportManager.exportReportToPdfStream(jasperPrint, out);
                out.flush();
                out.close();
            } else {
                response.setContentType("text/plain");
                out.println("no se pudo generar el reporte");
                out.println("esto puede deberse a que la lista de datos no fue recibida o el "
                        + "archivo plantilla del reporte no se ha encontrado");
                out.println("contacte a soporte");
            }
        } catch (Exception e) {
            response.setContentType("text/plain");
            out.print("ocurrió un error al intentar generar el reporte:" + e.getMessage());
            e.printStackTrace();
        }

    }

    private void CertificadoLaboral(HttpServletRequest request, HttpServletResponse response) throws IOException {

        DAOUSPS udao;
        usuario usuario;
        udao = new DAOUSPS();
        DAOUSUARIO daousuario = new DAOUSUARIO();
        ServletOutputStream out = response.getOutputStream();
        try {
            java.io.InputStream logo = this.getServletConfig()
                    .getServletContext()
                    .getResourceAsStream("Reportes/img/logo.png");
            if (logo != null) {
                System.out.println("logo cargado con exito");
            }
            java.io.InputStream certificado = this.getServletConfig()
                    .getServletContext()
                    .getResourceAsStream("Certificados/Certificado.jasper");
            if (certificado != null) {
                System.out.println("reporte cargado con exito");
            }
            java.lang.String nombreUsuario = request.getParameter("nom");

            if (logo != null) {
                System.out.println("logo cargado con exito");
            }if (nombreUsuario != null) {
                System.out.println("nombreUsuario cargado con exito");
            }

            //Validar que no vengan vacios
            if (logo != null && certificado != null
                && request.getParameter("cod") != null
                && request.getParameter("nom") != null) {
                //Crear lista de la clase Vo para guardar resultado de la consulta
                usuario CertificadoLaboral = new usuario();
                CertificadoLaboral = udao.CertificadoLaboral(Integer.parseInt(request.getParameter("cod")));

                //Declarar variable tipo Jasper Reports asignando el reporte creado
                JasperReport report = (JasperReport) JRLoader.loadObject(certificado);

                //Mapeamos los parámetros del Jasper reports
                Map<String, Object> parameters = new HashMap();
                parameters.put("logo", logo);
                parameters.put("nombreUsuario", nombreUsuario);
                //Formateamos la salida del reporte
                response.setContentType("application/pdf");
                //Para abrir el reporte en otra pestaña
                response.addHeader("Content-disposition", "inline; filename=ReporteUsuarios.pdf");
                //Imprimimos el reporte
                JasperPrint jasperPrint = JasperFillManager.fillReport(report, parameters);
                JasperExportManager.exportReportToPdfStream(jasperPrint, out);
                out.flush();
                out.close();
            } else {
                response.setContentType("text/plain");
                out.println("no se pudo generar el reporte");
                out.println("esto puede deberse a que la lista de datos no fue recibida o el "
                        + "archivo plantilla del reporte no se ha encontrado");
                out.println("contacte a soporte");
            }
        } catch (Exception e) {
            response.setContentType("text/plain");
            out.print("ocurrió un error al intentar generar el reporte:" + e.getMessage());
            e.printStackTrace();
        }

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
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
        } else {
            request.setAttribute("msje", "Credenciales Incorrectas");
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
        u.setNumeroIdentificacion(Integer.parseInt(request.getParameter("txtUsu")));
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

    private void listarEmpresasSupervisor(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("listó empresas supervisor");
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
                    .getRequestDispatcher("/vistas/Supervisor/listarEmpresasSupervisor.jsp").forward(request, response);
        } catch (Exception ex) {
            request.setAttribute("msje", "No se puedo realizar la petición" + ex.getMessage());
        }
    }

    private void listarSedes(HttpServletRequest request, HttpServletResponse response) {
        DAOSEDE dao = new DAOSEDE();
        List<sede> se = null;
        DAOEMPRESA daoemp = new DAOEMPRESA();
        empresa emp;

        if (request.getParameter("cod") != null) {
            emp = new empresa();
            emp.setId_empresa(Integer.parseInt(request.getParameter("cod")));
            //se.setEmpresa(emp);

            try{
                emp = daoemp.leerEmpresa(emp);
                request.setAttribute("empresa", emp);
            }catch (Exception e){
                request.setAttribute("msje", "No se pudieron listar las empresas" + e.getMessage());
            }finally{
                daoemp = null;
            }
            try {
                se = dao.listar(emp);
                request.setAttribute("sedes", se);
            } catch (Exception e) {
                request.setAttribute("msje", "No se pudieron listar las sedes" + e.getMessage());
            } finally {
                dao = null;
            }
            try {
                this.getServletConfig().getServletContext()
                        .getRequestDispatcher("/vistas/Administrador/listarSedes.jsp").forward(request, response);
            } catch (Exception ex) {
                request.setAttribute("msje", "No se puedo realizar la petición" + ex.getMessage());
            }
        }
    }
    private void listarSedesSupervisor(HttpServletRequest request, HttpServletResponse response) {
        DAOSEDE dao = new DAOSEDE();
        List<sede> se = null;
        empresa emp;

        if (request.getParameter("cod") != null) {
            emp = new empresa();
            emp.setId_empresa(Integer.parseInt(request.getParameter("cod")));
            //se.setEmpresa(emp);

            try {
                se = dao.listar(emp);
                request.setAttribute("sedes", se);
            } catch (Exception e) {
                request.setAttribute("msje", "No se pudieron listar las sedes" + e.getMessage());
            } finally {
                dao = null;
            }
            try {
                this.getServletConfig().getServletContext()
                        .getRequestDispatcher("/vistas/Supervisor/listarSedesSupervisor.jsp").forward(request, response);
            } catch (Exception ex) {
                request.setAttribute("msje", "No se puedo realizar la petición" + ex.getMessage());
            }
        }
    }

    private void listarHorariosAdm(HttpServletRequest request, HttpServletResponse response) {
        DAOPROG dao = new DAOPROG();
        List<programacion> pr = null;
        empresa emp;
        usuario usu;

        if (request.getParameter("cod") != null) {
            usu = new usuario();
            usu.setId_usuario(Integer.parseInt(request.getParameter("cod")));
            //se.setEmpresa(emp);

            try {
                pr = dao.listar(usu);
                System.out.println(pr.size());
                request.setAttribute("programaciones", pr);
            } catch (Exception e) {
                request.setAttribute("msje", "No se pudieron listar los horarios" + e.getMessage());
                System.out.println("no se pudieron listar los horarios" + e.getMessage());
            } finally {
                dao = null;
            }
            try {
                this.getServletConfig().getServletContext()
                        .getRequestDispatcher("/vistas/Administrador/listarHorariosAdm.jsp").forward(request, response);
            } catch (Exception ex) {
                request.setAttribute("msje", "No se puedo realizar la petición" + ex.getMessage());
                System.out.println("No se puedo realizar la petición" + ex.getMessage());
            }
        }
    }
    private void listarNovedadesAdmin(HttpServletRequest request, HttpServletResponse response) {
        DAONOVEDADES dao = new DAONOVEDADES();
        List<novedades> novedades = null;
        empresa emp;
        usuario usu;
        tipoNovedad tipoNovedad;

        if (request.getParameter("cod") != null) {
            usu = new usuario();
            usu.setId_usuario(Integer.parseInt(request.getParameter("cod")));
            //se.setEmpresa(emp);

            try {
                novedades = dao.listar(usu);
                System.out.println(novedades.size());
                request.setAttribute("novedades", novedades);
            } catch (Exception e) {
                request.setAttribute("msje", "No se pudieron listar las novedades" + e.getMessage());
                System.out.println("no se pudieron listar las novedades" + e.getMessage());
            } finally {
                dao = null;
            }
            try {
                this.getServletConfig().getServletContext()
                        .getRequestDispatcher("/vistas/Administrador/listarNovedadesAdministrador.jsp").forward(request, response);
            } catch (Exception ex) {
                request.setAttribute("msje", "No se puedo realizar la petición" + ex.getMessage());
                System.out.println("No se puedo realizar la petición" + ex.getMessage());
            }
        }
    }



    private void listarProgramacionesEmpleado(HttpServletRequest request, HttpServletResponse response) {
        DAOPROG dao = new DAOPROG();
        List<programacion> pr = null;
        empresa emp;
        usuario usu;

        if (request.getParameter("cod") != null) {
            usu = new usuario();
            usu.setId_usuario(Integer.parseInt(request.getParameter("cod")));
            //se.setEmpresa(emp);

            try {
                pr = dao.listar(usu);
                System.out.println(pr.size());
                request.setAttribute("programaciones", pr);
            } catch (Exception e) {
                request.setAttribute("msje", "No se pudieron listar los horarios" + e.getMessage());
                System.out.println("no se pudieron listar los horarios" + e.getMessage());
            } finally {
                dao = null;
            }
            try {
                this.getServletConfig().getServletContext()
                        .getRequestDispatcher("/vistas/Empleado/listarProgramacionesEmpleado.jsp").forward(request, response);
            } catch (Exception ex) {
                request.setAttribute("msje", "No se puedo realizar la petición" + ex.getMessage());
                System.out.println("No se puedo realizar la petición" + ex.getMessage());
            }
        }
    }


    private void presentarFormulario(HttpServletRequest request, HttpServletResponse response) {
        try {
            this.cargarCargos(request);
            this.cargarIdentificaciones(request);
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

    private void presentarFormularioSede(HttpServletRequest request, HttpServletResponse response) {
        try {
            this.cargarEmpresas(request);
            this.getServletConfig().getServletContext()
                    .getRequestDispatcher("/vistas/Administrador/agregarSede.jsp").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("msje", "No se pudo cargar la vista");
        }
    }

    private void presentarFormularioProgramacion(HttpServletRequest request, HttpServletResponse response) {
        sede sedes;
        DAOSEDE dao;

        if (request.getParameter("cod") != null) {
            sedes = new sede();
            sedes.setIdSede(Integer.parseInt(request.getParameter("cod")));

            dao = new DAOSEDE();
            try {
                sedes = dao.leerDatosSede(sedes);
                if (sedes != null) {
                    request.setAttribute("sede", sedes);
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
            this.cargarEmpleados(request);
            this.getServletConfig().getServletContext()
                    .getRequestDispatcher("/vistas/Administrador/nuevaProgramacionAdmin.jsp").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("msje", "No se pudo cargar la vista");
        }
    }

    private void presentarFormularioProgramacionSupervisor(HttpServletRequest request, HttpServletResponse response) {
        sede sedes;
        DAOSEDE dao;

        if (request.getParameter("cod") != null) {
            sedes = new sede();
            sedes.setIdSede(Integer.parseInt(request.getParameter("cod")));

            dao = new DAOSEDE();
            try {
                sedes = dao.leerDatosSede(sedes);
                if (sedes != null) {
                    request.setAttribute("sede", sedes);
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
            this.cargarEmpleados(request);
            this.getServletConfig().getServletContext()
                    .getRequestDispatcher("/vistas/Supervisor/nuevaProgramacionSupervisor.jsp").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("msje", "No se pudo cargar la vista");
        }
    }

    private void presentarFormularioSup(HttpServletRequest request, HttpServletResponse response) {
        try {
            this.cargarIdentificaciones(request);
            this.getServletConfig().getServletContext()
                    .getRequestDispatcher("/vistas/Supervisor/nuevoEmpleado.jsp").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("msje", "No se pudo cargar la vista");
        }
    }

    private void presentarPassword(HttpServletRequest request, HttpServletResponse response) {
        try {
            this.getServletConfig().getServletContext()
                    .getRequestDispatcher("/vistas/Administrador/changePass.jsp").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("msje", "No se pudo cargar la vista");
        }
    }

    private void presentarPasswordEmp(HttpServletRequest request, HttpServletResponse response) {
        try {
            this.getServletConfig().getServletContext()
                    .getRequestDispatcher("/vistas/Empleado/changePassEmp.jsp").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("msje", "No se pudo cargar la vista");
        }
    }

    private void presentarPasswordSup(HttpServletRequest request, HttpServletResponse response) {
        try {
            this.getServletConfig().getServletContext()
                    .getRequestDispatcher("/vistas/Supervisor/changePassSup.jsp").forward(request, response);
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

    private void cargarIdentificaciones(HttpServletRequest request) {
        DAOIDENTIFICACIONES dao = new DAOIDENTIFICACIONES();
        List<identificaciones> ide = null;
        try {
            ide = dao.listarIdentificaciones();
            request.setAttribute("identificaciones", ide);
        } catch (Exception e) {
            request.setAttribute("msje", "No se pudo cargar las identificaciones :( " + e.getMessage());
        } finally {
            ide = null;
            dao = null;
        }
    }

    private void cargarEmpleados(HttpServletRequest request) {
        DAOPROG daoprog = new DAOPROG();
        List<usuario> usuarios = null;
        try {
            usuarios = daoprog.listarEmpleadosProgramacion();
            request.setAttribute("empleados", usuarios);
        } catch (Exception e) {
            request.setAttribute("msje", "No se pudo cargar los empleados :( " + e.getMessage());
        } finally {
            usuarios = null;
            daoprog = null;
        }
    }

    private void cargarEmpresas(HttpServletRequest request) {
        DAOEMPRESA dao = new DAOEMPRESA();
        List<empresa> emp = null;
        try {
            emp = dao.listarEmpresasSede();
            request.setAttribute("empresas", emp);
        } catch (Exception e) {
            request.setAttribute("msje", "No se pudo cargar las empresas" + e.getMessage());
            System.out.println("error cargando empresas: " + e.getMessage());
        } finally {
            emp = null;
            dao = null;
        }
    }

    private void registrarUsuario(HttpServletRequest request, HttpServletResponse response) {
        DAOUSPS daoUsu;
        usuario usu = null;
        cargo carg;
        identificaciones ident;
        if (request.getParameter("txtNombre") != null
                && request.getParameter("txtCorreo") != null
                && request.getParameter("cboIdentificacion") != null
                && request.getParameter("txtNumeroIdentificacion") != null
                && request.getParameter("cboCargo") != null) {

            usu = new usuario();
            usu.setNombreUsuario(request.getParameter("txtNombre"));
            usu.setCorreoUsuario(request.getParameter("txtCorreo"));
            String contrasena1 = contrasena.getPassword();
            usu.setClave(daousuario.getMD5(contrasena1));
            ident = new identificaciones();
            ident.setId_identificacion(Integer.parseInt(request.getParameter("cboIdentificacion")));
            usu.setId_identificacion(ident);
            usu.setNumeroIdentificacion(Integer.parseInt(request.getParameter("txtNumeroIdentificacion")));
            carg = new cargo();
            carg.setCodigo(Integer.parseInt(request.getParameter("cboCargo")));
            usu.setCargo(carg);
            if (request.getParameter("chkEstado") != null) {
                usu.setEstado(true);
            } else {
                usu.setEstado(false);
            }

            String destinatario = request.getParameter("txtCorreo");
            String asunto = "Bienvenido a Docx";
            String cuerpo = "<h1> Gracias por registrarse en Docx </h1>"
                    + " <img src ='https://lideresmexicanos.com/wp-content/uploads/2021/08/ISP3.jpg'/>"
                    + " <h4> Para iniciar sesión diríjase al siguiente enlace</h4>"
                    + " <h4> Datos de ingreso: </h4>" + usu.getNumeroIdentificacion() + ", " + contrasena1
                    + " <a href='http://localhost:8080/DocxProy_war_exploded/identificar.jsp'>Haga click aquí para iniciar sesión</a>";

            try {
                Configmail.enviarCorreo(host, puerto, remitente, password, destinatario, asunto, cuerpo);
                System.out.println("El mensaje fue enviado correctamente");
            } catch (Exception e) {
                System.out.println("El mensaje NO fue enviado correctamente " + e.getMessage());
            }

            daoUsu = new DAOUSPS();
            try {
                daoUsu.registrar(usu);
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
        DAOUSPS daoUsu;
        usuario usu = null;
        identificaciones ident;
        cargo car;
        if (request.getParameter("txtNombre") != null
                && request.getParameter("txtCorreo") != null
                && request.getParameter("cboIdentificacion") != null
                && request.getParameter("txtNumeroIdentificacion") != null) {
            usu = new usuario();
            usu.setNombreUsuario(request.getParameter("txtNombre"));
            usu.setCorreoUsuario(request.getParameter("txtCorreo"));
            String contrasena1 = contrasena.getPassword();
            usu.setClave(daousuario.getMD5(contrasena1));
            ident = new identificaciones();
            ident.setId_identificacion(Integer.parseInt(request.getParameter("cboIdentificacion")));
            usu.setId_identificacion(ident);
            car = new cargo();
            car.setCodigo(2);
            usu.setCargo(car);
            ident.setId_identificacion(Integer.parseInt(request.getParameter("cboIdentificacion")));
            usu.setId_identificacion(ident);
            usu.setNumeroIdentificacion(Integer.parseInt(request.getParameter("txtNumeroIdentificacion")));

            if (request.getParameter("chkEstado") != null) {
                usu.setEstado(true);
            } else {
                usu.setEstado(false);
            }

            String destinatario = request.getParameter("txtCorreo");
            String asunto = "Bienvenido a Docx";
            String cuerpo = "<h1> Gracias por registrarse en Docx </h1>"
                    + " <img src ='https://lideresmexicanos.com/wp-content/uploads/2021/08/ISP3.jpg'/>"
                    + " <h4> Para iniciar sesión diríjase al siguiente enlace</h4>"
                    + " <h4> Datos de ingreso: </h4>" + usu.getNumeroIdentificacion() + ", " + contrasena1
                    + " <a href='http://localhost:8080/DocxProy_war_exploded/identificar.jsp'>Haga click aquí para iniciar sesión</a>";

            try {
                Configmail.enviarCorreo(host, puerto, remitente, password, destinatario, asunto, cuerpo);
                System.out.println("El mensaje fue enviado correctamente");
            } catch (Exception e) {
                System.out.println("El mensaje NO fue enviado correctamente " + e.getMessage());
            }

            daoUsu = new DAOUSPS();
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

    private void presentarDocumento(HttpServletRequest request, HttpServletResponse response) {
        try {
            this.getServletConfig().getServletContext()
                    .getRequestDispatcher("/vistas/Supervisor/archivosSupervisor.jsp").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("msje", "No se pudo cargar la vista");
        }
    }

    private void saveDocument(HttpServletRequest req, HttpServletResponse res) throws IOException {
        DAODOCUMENTOS daodocumentos = new DAODOCUMENTOS();
        try {

            String name = req.getParameter("name");
            Part part = req.getPart("file");

            if (part == null) {
                System.out.println("No ha seleccionado un archivo");
                return;
            }

            if (isExtension(part.getSubmittedFileName(), extensiones)) {
                String photo = saveFile(part, uploads);
                documentos docs = new documentos(name, photo);
                daodocumentos.addArchivos(docs);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        res.sendRedirect("srvUsuario?accion=listarEmpleados");
    }


    private String saveFile(Part part, File pathUploads) {
        String pathAbsolute = "";

        try {
            Path path = Paths.get(part.getSubmittedFileName());
            String fileName = path.getFileName().toString();
            InputStream input = part.getInputStream();

            if (input != null) {
                File file = new File(pathUploads, fileName);
                pathAbsolute = file.getAbsolutePath();
                Files.copy(input, file.toPath());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return pathAbsolute;
    }

    private boolean isExtension(String fileName, String[] extensiones) {
        for (String et : extensiones) {
            if (fileName.toLowerCase().endsWith(et)) {
                return true;
            }
        }
        return false;
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
            this.cargarIdentificaciones(request);
            this.getServletConfig().getServletContext().
                    getRequestDispatcher("/vistas/Administrador/actualizarUsuario.jsp"
                    ).forward(request, response);
        } catch (Exception e) {
            request.setAttribute("msje", "No se pudo realizar la operacion" + e.getMessage());
        }
    }

    private void presentarSede(HttpServletRequest request, HttpServletResponse response) {
        DAOSEDE dao;
        sede sedes;
        if (request.getParameter("cod") != null) {
            sedes = new sede();
            sedes.setIdSede(Integer.parseInt(request.getParameter("cod")));

            dao = new DAOSEDE();
            try {
                sedes = dao.leerSede(sedes);
                if (sedes != null) {
                    request.setAttribute("sede", sedes);
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
            this.cargarEmpresas(request);
            this.getServletConfig().getServletContext().
                    getRequestDispatcher("/vistas/Administrador/actualizarSede.jsp"
                    ).forward(request, response);
        } catch (Exception e) {
            request.setAttribute("msje", "No se pudo realizar la operacion" + e.getMessage());
        }
    }

    private void presentarProgramacion(HttpServletRequest request, HttpServletResponse response) {
        DAOPROG daoprog;
        programacion programacion;
        if (request.getParameter("cod") != null) {
            programacion = new programacion();
            programacion.setIdProgramacion(Integer.parseInt(request.getParameter("cod")));

            daoprog = new DAOPROG();
            try {
                programacion = daoprog.leerProgramacion(programacion);
                if (programacion != null) {
                    request.setAttribute("programacion", programacion);
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
            this.cargarEmpleados(request);
            this.getServletConfig().getServletContext().
                    getRequestDispatcher("/vistas/Administrador/EditarProgramacionAdmin.jsp"
                    ).forward(request, response);
        } catch (Exception e) {
            request.setAttribute("msje", "No se pudo realizar la operacion" + e.getMessage());
        }
    }


    private void presentarProgramacionSupervisor(HttpServletRequest request, HttpServletResponse response) {
        DAOPROG daoprog;
        programacion programacion;
        if (request.getParameter("cod") != null) {
            programacion = new programacion();
            programacion.setIdProgramacion(Integer.parseInt(request.getParameter("cod")));

            daoprog = new DAOPROG();
            try {
                programacion = daoprog.leerProgramacion(programacion);
                if (programacion != null) {
                    request.setAttribute("programacion", programacion);
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
            this.cargarEmpleados(request);
            this.getServletConfig().getServletContext().
                    getRequestDispatcher("/vistas/Supervisor/EditarProgramacionSupervisor.jsp"
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
                    request.setAttribute("msje", "No se encontró el usuario");
                }
            } catch (Exception e) {
                request.setAttribute("msje", "No se pudo acceder a la base de datos" + e.getMessage());
            }
        } else {
            request.setAttribute("msje", "No se tiene el parámetro necesario");
        }
        try {
            this.cargarIdentificaciones(request);
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
        identificaciones ide;
        if (request.getParameter("hCodigo") != null
                && request.getParameter("txtNombre") != null
                && request.getParameter("txtCorreo") != null
                && request.getParameter("txtNumeroIdentificacion") != null
                && request.getParameter("cboIdentificacion") != null
                && request.getParameter("cboCargo") != null) {

            usus = new usuario();
            usus.setId_usuario(Integer.parseInt(request.getParameter("hCodigo")));
            usus.setNombreUsuario(request.getParameter("txtNombre"));
            usus.setCorreoUsuario(request.getParameter("txtCorreo"));
            usus.setNumeroIdentificacion(Integer.parseInt(request.getParameter("txtNumeroIdentificacion")));
            car = new cargo();
            car.setCodigo(Integer.parseInt(request.getParameter("cboCargo")));
            usus.setCargo(car);
            ide = new identificaciones();
            ide.setId_identificacion(Integer.parseInt(request.getParameter("cboIdentificacion")));
            usus.setId_identificacion(ide);
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
                this.cargarIdentificaciones(request);
                this.getServletConfig().getServletContext().
                        getRequestDispatcher("/vistas/Administrador/actualizarUsuario.jsp"
                        ).forward(request, response);
            } catch (Exception ex) {
                request.setAttribute("msje", "No se pudo realizar la operacion" + ex.getMessage());
                System.out.println(ex);
            }
        }
    }

    private void actualizarSede(HttpServletRequest request, HttpServletResponse response) {
        DAOSEDE daosede;
        sede sedes = null;
        empresa emp;

        if (request.getParameter("hCodigo") != null
                && request.getParameter("txtDireccion") != null
                && request.getParameter("txtNombreContacto") != null
                && request.getParameter("txtNumeroContacto") != null
                && request.getParameter("txtCorreo") != null
                && request.getParameter("cboEmpresa") != null) {

            sedes = new sede();
            sedes.setIdSede(Integer.parseInt(request.getParameter("hCodigo")));
            sedes.setDireccion(request.getParameter("txtDireccion"));
            sedes.setNombreContacto(request.getParameter("txtNombreContacto"));
            sedes.setNumeroContacto(Integer.parseInt(request.getParameter("txtNumeroContacto")));
            sedes.setCorreo(request.getParameter("txtCorreo"));
            emp = new empresa();
            emp.setId_empresa(Integer.parseInt(request.getParameter("cboEmpresa")));
            int codigoEmpresa = emp.getId_empresa();
            sedes.setEmpresa(emp);
            daosede = new DAOSEDE();
            try {
                daosede.actualizarSede(sedes);
                response.sendRedirect("srvUsuario?accion=listarSedes&cod=" + codigoEmpresa);
            } catch (Exception e) {
                request.setAttribute("msje",
                        "No se pudo actualizar la sede" + e.getMessage());
                request.setAttribute("sede", sedes);
                System.out.println(e);
            }
            try {
                this.cargarEmpresas(request);
                this.getServletConfig().getServletContext().
                        getRequestDispatcher("/vistas/Administrador/actualizarSede.jsp"
                        ).forward(request, response);
            } catch (Exception ex) {
                request.setAttribute("msje", "No se pudo realizar la operacion" + ex.getMessage());
                System.out.println(ex);
            }
        }
    }

    private void actualizarProgramacion(HttpServletRequest request, HttpServletResponse response) {
        DAOPROG daoprog;
        programacion programacion = null;
        empresa emp;
        sede sede;
        usuario usu;

        if (request.getParameter("hCodigo") != null
                && request.getParameter("txtFechaInicioLabor") != null
                && request.getParameter("txtFechaFinLabor") != null
                && request.getParameter("txtHoraInicioLabor") != null
                && request.getParameter("txtHoraFinLabor") != null
                && request.getParameter("txtIdEmpresa") != null
                && request.getParameter("txtIdSede") != null
                && request.getParameter("cboEmpleado") != null) {

            programacion = new programacion();
            programacion.setIdProgramacion(Integer.parseInt(request.getParameter("hCodigo")));
            programacion.setFechaInicioLabor(request.getParameter("txtFechaInicioLabor"));
            programacion.setFechaFinLabor(request.getParameter("txtFechaFinLabor"));
            programacion.setHoraEntrada(request.getParameter("txtHoraInicioLabor"));
            programacion.setHoraSalida(request.getParameter("txtHoraFinLabor"));
            emp = new empresa();
            emp.setId_empresa(Integer.parseInt(request.getParameter("txtIdEmpresa")));
            sede = new sede();
            sede.setIdSede(Integer.parseInt(request.getParameter("txtIdSede")));
            int idSede = Integer.parseInt(request.getParameter("txtIdSede"));

            usu = new usuario();
            usu.setId_usuario(Integer.parseInt(request.getParameter("cboEmpleado")));
            programacion.setEmpresa(emp);
            programacion.setSede(sede);
            programacion.setUsuario(usu);

            daoprog = new DAOPROG();
            try {
                daoprog.actualizarProgramacion(programacion);
                System.out.println("hizo el dao");
                response.sendRedirect("srvUsuario?accion=listarHS&cod=" + idSede);
            } catch (Exception e) {
                request.setAttribute("msje",
                        "No se pudo actualizar la sede" + e.getMessage());
                request.setAttribute("programacion", programacion);
                System.out.println(e);
            }
            try {
                this.cargarEmpleados(request);
                this.getServletConfig().getServletContext().
                        getRequestDispatcher("/vistas/Administrador/EditarProgramacionAdmin.jsp"
                        ).forward(request, response);
            } catch (Exception ex) {
                request.setAttribute("msje", "No se pudo realizar la operacion" + ex.getMessage());
                System.out.println(ex);
            }
        }
    }

    private void actualizarProgramacionSupervisor(HttpServletRequest request, HttpServletResponse response) {
        DAOPROG daoprog;
        programacion programacion = null;
        empresa emp;
        sede sede;
        usuario usu;

        if (request.getParameter("hCodigo") != null
                && request.getParameter("txtFechaInicioLabor") != null
                && request.getParameter("txtFechaFinLabor") != null
                && request.getParameter("txtHoraInicioLabor") != null
                && request.getParameter("txtHoraFinLabor") != null
                && request.getParameter("txtIdEmpresa") != null
                && request.getParameter("txtIdSede") != null
                && request.getParameter("cboEmpleado") != null) {

            programacion = new programacion();
            programacion.setIdProgramacion(Integer.parseInt(request.getParameter("hCodigo")));
            programacion.setFechaInicioLabor(request.getParameter("txtFechaInicioLabor"));
            programacion.setFechaFinLabor(request.getParameter("txtFechaFinLabor"));
            programacion.setHoraEntrada(request.getParameter("txtHoraInicioLabor"));
            programacion.setHoraSalida(request.getParameter("txtHoraFinLabor"));
            emp = new empresa();
            emp.setId_empresa(Integer.parseInt(request.getParameter("txtIdEmpresa")));
            sede = new sede();
            sede.setIdSede(Integer.parseInt(request.getParameter("txtIdSede")));
            int idSede = Integer.parseInt(request.getParameter("txtIdSede"));

            usu = new usuario();
            usu.setId_usuario(Integer.parseInt(request.getParameter("cboEmpleado")));
            programacion.setEmpresa(emp);
            programacion.setSede(sede);
            programacion.setUsuario(usu);

            daoprog = new DAOPROG();
            try {
                daoprog.actualizarProgramacion(programacion);
                System.out.println("hizo el dao");
                response.sendRedirect("srvUsuario?accion=listarHSsupervisor&cod=" + idSede);
            } catch (Exception e) {
                request.setAttribute("msje",
                        "No se pudo actualizar la sede" + e.getMessage());
                request.setAttribute("programacion", programacion);
                System.out.println(e);
            }
            try {
                this.cargarEmpleados(request);
                this.getServletConfig().getServletContext().
                        getRequestDispatcher("/vistas/Supervisor/EditarProgramacionSupervisor.jsp"
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
        identificaciones ide;
        if (request.getParameter("hCodigo") != null
                && request.getParameter("txtNombre") != null
                && request.getParameter("txtCorreo") != null
                && request.getParameter("txtNumeroIdentificacion") != null
                && request.getParameter("cboIdentificacion") != null) {

            usus = new usuario();
            usus.setId_usuario(Integer.parseInt(request.getParameter("hCodigo")));
            usus.setNombreUsuario(request.getParameter("txtNombre"));
            usus.setCorreoUsuario(request.getParameter("txtCorreo"));
            usus.setNumeroIdentificacion(Integer.parseInt(request.getParameter("txtNumeroIdentificacion")));
            ide = new identificaciones();
            ide.setId_identificacion(Integer.parseInt(request.getParameter("cboIdentificacion")));
            usus.setId_identificacion(ide);
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
                System.out.println(e);
            }
            try {
                this.cargarIdentificaciones(request);
                this.getServletConfig().getServletContext().
                        getRequestDispatcher("/vistas/Supervisor/actualizarEmpleado.jsp"
                        ).forward(request, response);
            } catch (Exception ex) {
                request.setAttribute("msje", "No se pudo realizar la operacion" + ex.getMessage());
                System.out.println(ex);
            }
        }
    }

    private void actPassword(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DAOUSUARIO dao;
        usuario usus = null;

        if (request.getParameter("id") != null
                && request.getParameter("passnew") != null) {
            usus = new usuario();
            usus.setId_usuario(Integer.parseInt(request.getParameter("id")));
            usus.setClave(request.getParameter("passnew"));
        }
        dao = new DAOUSUARIO();
        try {
            dao.changePassword(usus);
            request.getRequestDispatcher("srvUsuario?accion=cerrar").forward(request, response);
        } catch (Exception e) {
            System.out.println("error al cambiar password " + e.getMessage());
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

    private void eliminarSede(HttpServletRequest request, HttpServletResponse response) {
        DAOSEDE dao = new DAOSEDE();
        sede sedes = new sede();
        if (request.getParameter("cod") != null) {
            sedes.setIdSede(Integer.parseInt(request.getParameter("cod")));
            try {
                dao.eliminar(sedes);
                int empresaSede = sedes.getEmpresa().getId_empresa();
                response.sendRedirect("srvUsuario?accion=listarSedes&cod=" + empresaSede);
            } catch (Exception e) {
                request.setAttribute("msje", "No se pudo acceder a la base de datos" + e.getMessage());
            }
        } else {
            request.setAttribute("msje", "No se encontro el usuario");
        }
    }

    private void eliminarProgramacion(HttpServletRequest request, HttpServletResponse response) {
        DAOPROG dao = new DAOPROG();
        programacion pr = new programacion();
        if (request.getParameter("cod") != null) {
            pr.setIdProgramacion(Integer.parseInt(request.getParameter("cod")));
            try {
                dao.eliminar(pr);
                int sedeProgramacion = pr.getSede().getIdSede();
                response.sendRedirect("srvUsuario?accion=listarSedes&cod=" + sedeProgramacion);
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
                && request.getParameter("txtTelefonoContacto") != null) {
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

    private void validarCorreo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        DAOUSPS vdao = new DAOUSPS();
        response.setContentType("text/html; charset=iso-8859-1");
        PrintWriter out = response.getWriter();
        try {
            int cant = vdao.validarCorreo(request.getParameter("txtNombre"));
            System.out.println(request.getParameter("txtNombre"));
            System.out.println("Usuarios encontrados " + cant);

            if (cant != 0) {
                System.out.println("El correo ya se encuentra registrado");
                out.println("El correo ya se encuentra registrado");
            } else {
                System.out.println("El nombre no se encuentra registrado");
                out.println("El correo no se encuentra registrado puede continuar su registro");
            }


        } catch (Exception e) {
            System.out.println("Correo no encontrado " + e.getMessage());
        } finally {
            //rdao=null;
        }

    }
}
