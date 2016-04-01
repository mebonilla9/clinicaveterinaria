/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.intecap.clinicaveterinaria.modelo.dao;

import co.edu.intecap.clinicaveterinaria.modelo.conexion.Conexion;
import co.edu.intecap.clinicaveterinaria.modelo.vo.ClienteVo;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

/**
 *
 * @author Lord_Nightmare
 */
public class ClienteDao extends Conexion implements GenericoDao<ClienteVo>{

    @Override
    public void insertar(ClienteVo object) {
        PreparedStatement sentencia;
        try {
            conectar();
            // crear consulta de insersion
            String sql = "insert into cliente(nombre,correo,telefono,estado) values(?,?,?,?)";
            sentencia = cnn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            // asignar parametros a la insersion
            sentencia.setString(1, object.getNombre());
            sentencia.setString(2, object.getCorreo());
            sentencia.setString(3, object.getTelefono());
            sentencia.setBoolean(4, object.isEstado());
            // ejecutar la insersion
            sentencia.executeUpdate();
            // obtener la llave de registro de la mascota
            ResultSet rs = sentencia.getGeneratedKeys();
            if (rs.next()) {
                object.setIdCliente(rs.getInt(1));
            }
        } catch (Exception e) {
            e.printStackTrace(System.err);
        } finally{
            desconectar();
        }
    }

    @Override
    public void editar(ClienteVo object) {
        PreparedStatement sentencia;
        try {
            conectar();
            String sql = "update medico set id_consulta = ?, fecha = ?, motivo = ?, descripcion = ?, estado = ?, id_medico = ?, id_historia = ?, where id_consulta = ?";
            sentencia = cnn.prepareStatement(sql);
            sentencia.setString(1, object.getNombre());
            sentencia.setString(2, object.getCorreo());
            sentencia.setString(3, object.getTelefono());
            sentencia.setBoolean(4, object.isEstado());
            sentencia.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(System.err);
        } finally {
            desconectar();
        }
    }

    @Override
    public List<ClienteVo> consultar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ClienteVo consultar(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
