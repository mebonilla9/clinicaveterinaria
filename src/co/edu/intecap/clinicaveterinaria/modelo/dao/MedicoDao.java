/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.intecap.clinicaveterinaria.modelo.dao;

import co.edu.intecap.clinicaveterinaria.modelo.conexion.Conexion;
import co.edu.intecap.clinicaveterinaria.modelo.vo.MedicoVo;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Lord_Nightmare
 */
public class MedicoDao extends Conexion implements GenericoDao<MedicoVo>{

    @Override
    public void insertar(MedicoVo object) {
        PreparedStatement sentencia;
        try {
            conectar();
            // crear consulta de insersion
            String sql = "insert into medico(nombre,correo,documento,tarjeta_profesional,estado) values(?,?,?,?,?)";
            sentencia = cnn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            // asignar parametros a la insersion
            sentencia.setString(1, object.getNombre());
            sentencia.setString(2, object.getCorreo());
            sentencia.setString(3, object.getDocumento());
            sentencia.setString(4, object.getTarjetaProfesional());
            sentencia.setBoolean(5, object.isEstado());
            // ejecutar la insersion
            sentencia.executeUpdate();
            // obtener la llave de registro de la mascota
            ResultSet rs = sentencia.getGeneratedKeys();
            if (rs.next()) {
                object.setIdMedico(rs.getInt(1));
            }
        } catch (Exception e) {
            e.printStackTrace(System.err);
        } finally{
            desconectar();
        }
    }

    @Override
    public void editar(MedicoVo object) {
        PreparedStatement sentencia;
        try {
            conectar();
            String sql = "update medico set id_medico = ?, nombre = ?, correo = ?, documento = ?, tarjeta_profesional = ?, estado = ?, where id_medico = ?";
            sentencia = cnn.prepareStatement(sql);
            sentencia.setInt(1, object.getIdMedico());
            sentencia.setString(2, object.getNombre());
            sentencia.setString(3, object.getCorreo());
            sentencia.setString(4, object.getDocumento());
            sentencia.setString(5, object.getTarjetaProfesional());
            sentencia.setBoolean(6, object.isEstado());
            sentencia.setInt(7, object.getIdMedico());
            sentencia.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(System.err);
        } finally {
            desconectar();
        }
    }

    @Override
    public List<MedicoVo> consultar() {
        PreparedStatement sentencia;
        List<MedicoVo> lista = new ArrayList<>();
        try {
            conectar();
            String sql = "select * from medico";
            sentencia = cnn.prepareStatement(sql);
            ResultSet rs = sentencia.executeQuery();
            while (rs.next()) {
                MedicoVo medico = new MedicoVo();
                medico.setIdMedico(rs.getInt("id_tipo_mascota"));
                medico.setNombre(rs.getString("nombre"));
                medico.setCorreo(rs.getString("correo"));
                medico.setDocumento(rs.getString("documento"));
                medico.setTarjetaProfesional(rs.getString("tarjeta_profesional"));
                medico.setEstado(rs.getBoolean("estado"));
                lista.add(medico);
            }
        } catch (Exception e) {
            e.printStackTrace(System.err);
        } finally {
            desconectar();
        }
        return lista;
    }

    @Override
    public MedicoVo consultar(int id) {
        PreparedStatement sentencia;
        MedicoVo obj = new MedicoVo();
        try {
            conectar();
            String sql = "select * from medico where id_medico = ?";
            sentencia = cnn.prepareStatement(sql);
            sentencia.setInt(1, id);
            ResultSet rs = sentencia.executeQuery();
            if (rs.next()) {
                obj.setIdMedico(rs.getInt("id_tipo_mascota"));
                obj.setNombre(rs.getString("nombre"));
                obj.setCorreo(rs.getString("correo"));
                obj.setDocumento(rs.getString("documento"));
                obj.setTarjetaProfesional(rs.getString("tarjeta_profesional"));
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
