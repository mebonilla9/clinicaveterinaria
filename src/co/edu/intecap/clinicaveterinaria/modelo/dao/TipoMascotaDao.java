/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.intecap.clinicaveterinaria.modelo.dao;

import co.edu.intecap.clinicaveterinaria.modelo.conexion.Conexion;
import co.edu.intecap.clinicaveterinaria.modelo.vo.TipoMascotaVo;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Lord_Nightmare
 */
public class TipoMascotaDao extends Conexion implements GenericoDao<TipoMascotaVo> {

    @Override
    public void insertar(TipoMascotaVo object) {
        PreparedStatement sentencia;
        try {
            conectar();
            String sql = "insert into tipo_mascota(nombre,estado) values(?,?)";
            sentencia = cnn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            sentencia.setString(1, object.getNombre());
            sentencia.setBoolean(2, object.isEstado());
            sentencia.executeUpdate();
            ResultSet rs = sentencia.getGeneratedKeys();
            if (rs.next()) {
                object.setIdTipoMascota(rs.getInt(1));
            }
        } catch (Exception e) {
            e.printStackTrace(System.err);
        } finally {
            desconectar();
        }
    }

    @Override
    public void editar(TipoMascotaVo object) {
        PreparedStatement sentencia;
        try {
            conectar();
            String sql = "update tipo_mascota set id_tipo_mascota = ?, nombre = ?, estado = ?, where id_tipo_mascota = ?";
            sentencia = cnn.prepareStatement(sql);
            sentencia.setInt(1, object.getIdTipoMascota());
            sentencia.setString(2, object.getNombre());
            sentencia.setBoolean(3, object.isEstado());
            sentencia.setInt(4, object.getIdTipoMascota());
            sentencia.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(System.err);
        } finally {
            desconectar();
        }
    }

    @Override
    public List<TipoMascotaVo> consultar() {
        PreparedStatement sentencia;
        List<TipoMascotaVo> lista = new ArrayList<>();
        try {
            conectar();
            String sql = "select * from tipo_mascota";
            sentencia = cnn.prepareStatement(sql);
            ResultSet rs = sentencia.executeQuery();
            while (rs.next()) {
                TipoMascotaVo tipoMascota = new TipoMascotaVo();
                tipoMascota.setIdTipoMascota(rs.getInt("id_tipo_mascota"));
                tipoMascota.setNombre(rs.getString("nombre"));
                tipoMascota.setEstado(rs.getBoolean("estado"));
                lista.add(tipoMascota);
            }
        } catch (Exception e) {
            e.printStackTrace(System.err);
        } finally {
            desconectar();
        }
        return lista;
    }

    @Override
    public TipoMascotaVo consultar(int id) {
        PreparedStatement sentencia;
        TipoMascotaVo obj = null;
        try {
            conectar();
            String sql = "select * from tipo_mascota where id_tipo_mascota = ?";
            sentencia = cnn.prepareStatement(sql);
            sentencia.setInt(1, id);
            ResultSet rs = sentencia.executeQuery();
            if (rs.next()) {
                obj.setIdTipoMascota(rs.getInt("id_tipo_mascota"));
                obj.setNombre(rs.getString("nombre"));
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
