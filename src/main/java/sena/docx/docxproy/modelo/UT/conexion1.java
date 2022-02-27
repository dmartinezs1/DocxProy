package sena.docx.docxproy.modelo.UT;

import java.sql.Connection;
import java.sql.DriverManager;

public class conexion1 {

    private static final String bd="jdbc:mysql://localhost:3306/docx";
    private static final String usuario="root";
    private static final String clave="1234";

    private static Connection con;

    public static Connection conectar1() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con=DriverManager.getConnection(bd,usuario,clave);
            System.out.println("Conexión Exitosa");
        }catch(Exception e) {
            System.out.println("Error en la conexión "+e.getMessage().toString());
        }

        return con;
    }
}