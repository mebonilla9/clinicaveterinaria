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
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Lord_Nightmare
 */
public class ClienteDao extends Conexion implements GenericoDao<ClienteVo> {

    @Override
    public void insertar(ClienteVo object) {
        PreparedStatement sentencia;
        try {
            conectar();
            // crear consulta de insersion
            String sql = "insert into cliente(nombre,correo,telefono,estado) values(?,?,?,?)";
            sentencia = cnn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
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
        } finally {
            desconectar();
        }
    }

    @Override
    public void editar(ClienteVo object) {
        PreparedStatement sentencia;
        try {
            conectar();
            String sql = "update medico set id_cliente = ?, nombre = ?, correo = ?, telefono = ?, estado = ?, where id_cliente = ?";
            sentencia = cnn.prepareStatement(sql);
            sentencia.setInt(1, object.getIdCliente());
            sentencia.setString(2, object.getNombre());
            sentencia.setString(3, object.getCorreo());
            sentencia.setString(4, object.getTelefono());
            sentencia.setBoolean(5, object.isEstado());
            sentencia.setInt(6, object.getIdCliente());
            sentencia.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(System.err);
        } finally {
            desconectar();
        }
    }

    @Override
    public List<ClienteVo> consultar() {
        PreparedStatement sentencia;
        List<ClienteVo> lista = new ArrayList<>();
        try {
            conectar();
            String sql = "select * from cliente";
            sentencia = cnn.prepareStatement(sql);
            ResultSet rs = sentencia.executeQuery();
            while (rs.next()) {
                ClienteVo cliente = new ClienteVo();
                cliente.setIdCliente(rs.getInt("id_consulta"));
                cliente.setNombre(rs.getString("nombre"));
                cliente.setTelefono(rs.getString("telefono"));
                cliente.setEstado(rs.getBoolean("estado"));
                lista.add(cliente);
            }
        } catch (Exception e) {
            e.printStackTrace(System.err);
        } finally {
            desconectar();
        }
        return lista;
    }

    @Override
    public ClienteVo consultar(int id) {
        PreparedStatement sentencia;
        ClienteVo obj = null;
        try {
            conectar();
            String sql = "select * from cliente where id_cliente = ?";
            sentencia = cnn.prepareStatement(sql);
            sentencia.setInt(1, id);
            ResultSet rs = sentencia.executeQuery();
            while (rs.next()) {
                obj = new ClienteVo();
                obj.setIdCliente(rs.getInt("id_consulta"));
                obj.setNombre(rs.getString("nombre"));
                obj.setTelefono(rs.getString("telefono"));
                obj.setEstado(rs.getBoolean("estado"));
            }
        } catch (Exception e) {
            e.printStackTrace(System.err);
        } finally {
            desconectar();
        }
        return obj;
    }

}
