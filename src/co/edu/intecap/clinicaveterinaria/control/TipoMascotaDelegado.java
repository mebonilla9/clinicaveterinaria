/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.intecap.clinicaveterinaria.control;

import co.edu.intecap.clinicaveterinaria.modelo.dao.TipoMascotaDao;
import co.edu.intecap.clinicaveterinaria.modelo.vo.TipoMascotaVo;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Lord_Nightmare
 */
public class TipoMascotaDelegado {

    private final JPanel contenedor;
    private final TipoMascotaDao tipoMascotaDao;

    public TipoMascotaDelegado(JPanel contenedor) {
        this.contenedor = contenedor;
        this.tipoMascotaDao = new TipoMascotaDao();
    }

    public void registrarMedico(TipoMascotaVo tipoMascota) {
        try {
            this.tipoMascotaDao.insertar(tipoMascota);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(contenedor, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void editarMedico(TipoMascotaVo tipoMascota) {
        try {
            this.tipoMascotaDao.editar(tipoMascota);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(contenedor, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public List<TipoMascotaVo> consultarMedicos() {
        List<TipoMascotaVo> listaMedicos = new ArrayList<>();
        try {
            listaMedicos = this.tipoMascotaDao.consultar();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(contenedor, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        return listaMedicos;
    }

    public TipoMascotaVo consultarMedico(int id) {
        TipoMascotaVo tipoMascota = new TipoMascotaVo();
        try {
            tipoMascota = this.tipoMascotaDao.consultar(id);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(contenedor, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        return tipoMascota;
    }
}
