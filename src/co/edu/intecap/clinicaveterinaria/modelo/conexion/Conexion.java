/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.intecap.clinicaveterinaria.modelo.conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Lord_Nightmare
 */
public class Conexion {

    protected static Connection cnn;

    protected static void conectar() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            cnn = DriverManager.getConnection(
                    "jdbc:mysql://169.254.43.21:3306/veterinaria",
                    "root",
                    ""
            );
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }
    }

    protected static void desconectar() {
        try {
            cnn.close();
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }
    }

}
