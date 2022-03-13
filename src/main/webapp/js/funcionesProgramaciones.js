$(document).ready(function () {
    $("tr #deleteUser").click(function (e) {
        e.preventDefault();
        var cod = $(this).parent().find('#codigo').val();
        var codSede = $(this).parent().find('#codigoSede').val();
        swal({
                title: "Esta Seguro de Eliminar?",
                text: "Una vez eliminado deberá agregar de nuevo!",
                type: "warning",
                showCancelButton: true,
                confirmButtonClass: "btn-danger",
                confirmButtonText: "Sí, Eliminar!",
                cancelButtonText: "No, Cancelar!",
                closeOnConfirm: false,
                closeOnCancel: false
            },
            function (isConfirm) {
                if (isConfirm) {
                    eliminarSede(cod);
                    swal("Eliminado!", "La sede se ha eliminado", "success");
                    setTimeout(function () {
                        parent.location.href = "srvUsuario?accion=listarHS&cod="+codSede
                    }, 1800);
                } else {
                    swal("Cancelado", "Cancelaste la eliminación", "error");
                }
            });
    });

    function eliminarSede(cod) {
        var url = "srvUsuario?accion=eliminarProgramacion&cod=" + cod;
        console.log("eliminado");
        $.ajax({
            type: 'POST',
            url: url,
            async: true,
            success: function (r) {

            }
        });
    }
});

