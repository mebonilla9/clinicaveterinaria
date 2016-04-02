/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.intecap.clinicaveterinaria.control;

import co.edu.intecap.clinicaveterinaria.modelo.dao.MascotaDao;
import co.edu.intecap.clinicaveterinaria.modelo.vo.MascotaVo;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Lord_Nightmare
 */
public class MascotaDelegado {
    
    // atributos de clase que son constantes 
    // (Su valor como objeto no puede cambiar)
    private final JFrame contenedor;
    private final MascotaDao mascotaDao;

    public MascotaDelegado(JFrame contenedor) {
        // asignacion inicial del valor de las constantes
        this.contenedor = contenedor;
        this.mascotaDao = new MascotaDao();
    }
    
    public void insertarMascota(MascotaVo mascotaVo){
        try {
            this.mascotaDao.insertar(mascotaVo);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(contenedor, e.getMessage(), "Error en insersión", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void editarMascota(MascotaVo mascotaVo){
        try {
            this.mascotaDao.editar(mascotaVo);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(contenedor, e.getMessage(), "Error en insersión", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public List<MascotaVo> consultarMascotas(){
        List<MascotaVo> listaMascotas;
        try {
            listaMascotas = this.mascotaDao.consultar();
        } catch (Exception e) {
            listaMascotas = new ArrayList<>();
            JOptionPane.showMessageDialog(contenedor, e.getMessage(), "Error en insersión", JOptionPane.ERROR_MESSAGE);
        }
        return listaMascotas;
    }
    
    public MascotaVo consultarMascota(int id){
        MascotaVo mascotaVo;
        try {
            mascotaVo = this.mascotaDao.consultar(id);
        } catch (Exception e) {
            mascotaVo = new MascotaVo();
            JOptionPane.showMessageDialog(contenedor, e.getMessage(), "Error en insersión", JOptionPane.ERROR_MESSAGE);
        }
        return mascotaVo;
    }
    
}
