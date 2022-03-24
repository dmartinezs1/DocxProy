<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    if (session.getAttribute("administrador") != null) {
%>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>Sistema Docx | Inicio</title>
    <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <link rel="stylesheet" href="bower_components/bootstrap/dist/css/bootstrap.min.css">
    <!-- Font Awesome -->
    <link rel="stylesheet" href="bower_components/font-awesome/css/font-awesome.min.css">
    <!-- Ionicons -->
    <link rel="stylesheet" href="bower_components/Ionicons/css/ionicons.min.css">
    <!-- Theme style -->
    <link href="dist/css/AdminLTE.min.css" rel="stylesheet" type="text/css"/>

    <!-- AdminLTE Skins. We have chosen the skin-blue for this starter
          page. However, you can choose any other skin. Make sure you
          apply the skin class to the body tag so the changes take effect. -->
    <link rel="stylesheet" href="dist/css/skins/skin-yellow-light.min.css">
    <link rel="stylesheet"
          href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600,700,300italic,400italic,600italic">
</head>

<body class="hold-transition skin-yellow-light sidebar-mini">
<div class="wrapper">

    <!-- Main Header -->
    <header class="main-header">
        <a href="#" class="logo">
            <!-- mini logo for sidebar mini 50x50 pixels -->
            <span class="logo-mini"><b>D</b>ocx</span>
            <!-- logo for regular state and mobile devices -->
            <span class="logo-lg"><b>Sistema </b>Docx</span>
        </a>

        <!-- Header Navbar -->
        <nav class="navbar navbar-static-top" role="navigation">
            <!-- Sidebar toggle button-->
            <a href="#" class="sidebar-toggle" data-toggle="push-menu" role="button">
                <span class="sr-only">Toggle navigation</span>
            </a>
            <!-- Navbar Right Menu -->
            <div class="navbar-custom-menu">
                <ul class="nav navbar-nav">
                    <!-- User Account Menu -->
                    <li class="dropdown user user-menu">
                        <!-- Menu Toggle Button -->
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                            <!-- The user image in the navbar-->
                            <img src="dist/img/logodocx1.png" class="user-image" alt="User Image"
                                 style="border-radius: 0%;">
                            <!-- hidden-xs hides the username on small devices so only the image appears. -->
                            <span class="hidden-xs"> ${administrador.nombreUsuario}</span>
                        </a>
                        <ul class="dropdown-menu">
                            <!-- The user image in the menu -->
                            <li class="user-header">
                                <img src="dist/img/logodocx1.png" alt="User Image">

                                <p>
                                    Bienvenido - ${administrador.nombreUsuario}
                                    <small>${administrador.cargo.nombreCargo} </small>
                                </p>
                            </li>
                            <!-- Menu Footer-->
                            <li class="user-footer">
                                <div class="pull-right">
                                    <a href="srvUsuario?accion=cerrar" class="btn btn-default btn-flat">Cerrar
                                        Sesión</a>
                                </div>
                            </li>
                        </ul>
                    </li>
                </ul>
            </div>
        </nav>
    </header>
    <!-- Left side column. contains the logo and sidebar -->
    <aside class="main-sidebar">

        <!-- sidebar: style can be found in sidebar.less -->
        <section class="sidebar">

            <!-- Sidebar user panel (optional) -->
            <div class="user-panel">
                <div class="pull-left image">
                    <img src="dist/img/logodocx1.png" alt="User Image">
                </div>
                <div class="pull-left info">
                    <p>Bienvenido, ${administrador.nombreUsuario} </p>
                    <!-- Status -->
                    <a href="#"><i class="fa fa-circle text-success"></i> Online</a>
                </div>
            </div>

            <!-- search form (Optional) -->
            <form action="#" method="get" class="sidebar-form">
                <div class="input-group">
                    <input type="text" name="q" class="form-control" placeholder="Search...">
                    <span class="input-group-btn">
                                <button type="submit" name="search" id="search-btn" class="btn btn-flat"><i
                                        class="fa fa-search"></i>
                                </button>
                            </span>
                </div>
            </form>
            <!-- /.search form -->

            <!-- Sidebar Menu -->
            <ul class="sidebar-menu" data-widget="tree">
                <li class="header">INICIO</li>
                <!-- Optionally, you can add icons to the links -->
                <li class="treeview">
                    <a href="#"><i class="fa fa-link"></i> <span>Panel Administrativo</span>
                        <span class="pull-right-container">
                            <i class="fa fa-angle-left pull-right"></i>
                            </span>
                    </a>
                    <ul class="treeview-menu">
                        <li><a href="srvUsuario?accion=abrirPassword"><i class="fa fa-user-plus"></i>Cambiar
                            contraseña</a></li>
                    </ul>
                </li>
                <li class="treeview active">
                    <a href="#"><i class="glyphicon glyphicon-th-large"></i> <span>Registros</span>
                        <span class="pull-right-container">
                                    <i class="fa fa-angle-left pull-right"></i>
                                </span>
                    </a>
                    <ul class="treeview-menu">
                        <%--<li><a href="#"><i class="fa fa-archive"></i>Categorias</a></li>
                        <li><a href="#"><i class="fa fa-tags"></i>Marcas</a></li>
                        <li><a href="#"><i class="fa fa-cube"></i>Productos</a></li>
                        <li><a href="#"><i class="fa fa-users"></i>Clientes</a></li>
                        <li><a href="#"><i class="fa fa-truck"></i>Proveedores</a></li>--%>
                        <li><a href="srvUsuario?accion=listarEmpresas"><i class="fa fa-user-plus"></i>Empresas</a></li>
                        <li class="active"><a href="srvUsuario?accion=listarUsuarios"><i class="fa fa-address-card"></i>Usuarios</a>
                        </li>
                    </ul>
                </li>
                <%--<li class="treeview">
                    <a href="#"><i class="fa fa-cart-arrow-down"></i> <span>Ventas</span>
                        <span class="pull-right-container">
                                    <i class="fa fa-angle-left pull-right"></i>
                                </span>
                    </a>
                    <ul class="treeview-menu">
                        <li><a href="#"><i class="fa fa-cart-arrow-down"></i>Nueva Venta</a></li>
                        <li><a href="#"><i class="fa fa-tags"></i>Administrar Ventas</a></li>
                    </ul>
                </li>--%>
                <li class="treeview">
                    <a href="#"><i class="fa fa-area-chart"></i> <span>Reportes</span>
                        <span class="pull-right-container">
                                    <i class="fa fa-angle-left pull-right"></i>
                                </span>
                    </a>
                    <ul class="treeview-menu">
                        <li><a href="srvUsuario?accion=reporteEmpleados"><i class="fa fa-bar-chart"></i>Reporte empleados</a></li>
                        <li><a href="<c:url value="srvUsuario">
                                                <c:param name="accion" value="CertificadoLaboral" />
                                                <c:param name="cod" value="${administrador.id_usuario}"/>
                                                <c:param name="nom" value="${administrador.nombreUsuario}"/>
                                                <c:param name="nomC" value="${administrador.cargo.nombreCargo}"/>
                                                </c:url>"><i class="fa fa-bar-chart"></i>Certificado Laboral</a>
                        </li>
                    </ul>
                </li>
            </ul>
            <!-- /.sidebar-menu -->
        </section>
        <!-- /.sidebar -->
    </aside>

    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">
        <section class="content-header">
            <div class="row">
                <div class="col-xs-12 col-md-3">
                </div>
                <div class="col-md-3 hidden-xs"></div>
                <div class="col-xs-2 col-md-1">
                </div>
                <div class="col-xs-10 col-md-5 ">
                    <div class="btn-group pull-right">
                        <a href="srvUsuario?accion=listarNovedadesAdmin&cod=${novedades.empleado.id_usuario}" class="btn btn-default">
                            <i class="fa fa-align-justify"></i> Ver listado</a>
                    </div>
                </div>
            </div>
        </section>
        <section class="content">
            <div class="box">
                <div class="box-header with-border">
                    <i class="fa fa-edit"></i>
                    <h3 class="box-title">Actualizar novedad ${novedades.empleado.nombreUsuario}</h3>
                </div>
                <form class="form-horizontal" action="srvUsuario?accion=actualizarNovedadAdmin" method="post">
                    <input type="hidden" value="${novedades.idNovedades}" name="txtIdNovedades">
                    <div class="box-body">
                        <div class="form-group">
                            <label class="col-sm-2 control-label">Fecha novedad</label>
                            <div class="col-sm-4 input-group">
                                <span class="input-group-addon"><i class="fa fa-user"></i></span>
                                <input type="date" class="form-control"
                                       name="txtFechaNovedad"
                                       value="${novedades.fechaNovedad}">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">Detalles Novedad</label>
                            <div class="col-sm-4 input-group">
                                <span class="input-group-addon"><i class="fa fa-user"></i></span>
                                <textarea type="text" id="inputField" class="form-control"
                                          name="txtDetallesNovedad" rows="5" cols="80">${novedades.detallesNovedad}
                                </textarea>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">Tipo de novedad</label>
                            <div class="col-sm-4 input-group">
                                <span class="input-group-addon"><i class="fa fa-tags"></i></span>
                                <select class="form-control" name="cboTipoNovedad" autofocus="" required="">
                                    <option value="0">Seleccione el tipo de novedad</option>
                                    <c:forEach items="${tipoNovedades}" var="tNovedad">
                                        <option value="${tNovedad.idTipoNovedad}"
                                                <c:if test="${tNovedad.idTipoNovedad ==
                                                                      novedades.tipoNovedad.idTipoNovedad}">
                                                    selected
                                                </c:if>
                                        >${tNovedad.descripcionNovedad}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <input type="hidden" value="${novedades.empleado.id_usuario}" name="txtIdEmpleado">
                        <div class="form-group">
                            <label class="col-sm-2 control-label">Empresa</label>
                            <div class="col-sm-4 input-group">
                                <span class="input-group-addon"><i class="fa fa-tags"></i></span>
                                <select class="form-control" name="cboEmpresa" autofocus="" required="">
                                    <option value="0">Seleccione la empresa</option>
                                    <c:forEach items="${empresas}" var="emp">
                                        <option value="${emp.id_empresa}"
                                                <c:if test="${emp.id_empresa ==
                                                                      novedades.empresa.id_empresa}">
                                                    selected
                                                </c:if>
                                        >${emp.nombreEmpresa}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>

                    </div>
                    <!-- /.box-body -->
                    <div class="box-footer">
                        <button type="reset" class="btn btn-danger"><i class="fa fa-close red"></i> Cancelar</button>
                        <button type="submit" id="" name="btnActualizar" value="Actualizar" class="btn btn-success"><i
                                class="fa fa-floppy-o"></i> Actualizar
                        </button>

                    </div>
                    <!-- /.box-footer -->
                </form>
            </div>
        </section>

    </div>
    <!-- /.content-wrapper -->

    <!-- Main Footer -->
    <footer class="main-footer">
        <!-- To the right -->
        <div class="pull-right hidden-xs">
            Anything you want
        </div>
        <!-- Default to the left -->
        <strong>Copyright &copy; 2022 <a href="#">Docx</a>.</strong> Todos los derechos reservados.
    </footer>

    <div class="control-sidebar-bg"></div>
</div>
<!-- ./wrapper -->

<!-- REQUIRED JS SCRIPTS -->

<!-- jQuery 3 -->
<script src="bower_components/jquery/dist/jquery.min.js"></script>
<!-- Bootstrap 3.3.7 -->
<script src="bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
<!-- AdminLTE App -->
<script src="dist/js/adminlte.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js" integrity="sha512-894YE6QWD5I59HgZOGReFYm4dnWc1Qt5NtvYSaNcOP+u1T9qYdvdihz0PPSiiqn/+/3e7Jo4EaG7TubfWGUrMQ==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
<script>
    function verifyCorreo(){
        const nombreUsuario=document.getElementById("nombre").value;
        $.ajax({
            url:"srvUsuario?accion=validarCorreo",
            data:{
                nombreUsuario:nombreUsuario
            },
            success: function(result){
                $("#validarC").html(result);
                //alert(result)
            }
        })
    }
</script>

<style>
    #inputField {
        height: 10rem;
    }
</style>

<!-- Optionally, you can add Slimscroll and FastClick plugins.
     Both of these plugins are recommended to enhance the
     user experience. -->
</body>
</html>
<%
    } else {
        response.sendRedirect("identificar.jsp");
    }
%>



