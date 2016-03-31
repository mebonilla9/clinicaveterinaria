/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.intecap.clinicaveterinaria.modelo.dao;

import co.edu.intecap.clinicaveterinaria.modelo.conexion.Conexion;
import co.edu.intecap.clinicaveterinaria.modelo.vo.MascotaVo;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

/**
 *
 * @author Lord_Nightmare
 */
public class MascotaDao extends Conexion implements GenericoDao<MascotaVo>{

    @Override
    public void insertar(MascotaVo object) {
        PreparedStatement sentencia = null;
        try {
            conectar();
            // crear consulta de insersion
            String sql = "insert into mascota(nombre,edad,estado,id_tipo_mascota,id_cliente) values(?,?,?,?,?)";
            sentencia = cnn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            // asignar parametros a la insersion
            sentencia.setString(1, object.getNombre());
            sentencia.setInt(2, object.getEdad());
            sentencia.setBoolean(3, object.isEstado());
            sentencia.setInt(4, object.getIdTipoMascota());
            sentencia.setInt(5, object.getIdCliente());
            // ejecutar la insersion
            sentencia.executeUpdate();
            // obtener la llave de registro de la mascota
            ResultSet rs = sentencia.getGeneratedKeys();
            if (rs.next()) {
                object.setIdMascota(rs.getInt(1));
            }
        } catch (Exception e) {
            e.printStackTrace(System.err);
        } finally{
            desconectar();
        }
    }

    @Override
    public void editar(MascotaVo object) {
        
    }

    @Override
    public List<MascotaVo> consultar() {
        return null;
    }

    @Override
    public MascotaVo consultar(int id) {
        return null;
    }
    
}
