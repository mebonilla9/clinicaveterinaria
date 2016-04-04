/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.intecap.clinicaveterinaria.control;

import co.edu.intecap.clinicaveterinaria.modelo.dao.MedicoDao;
import co.edu.intecap.clinicaveterinaria.modelo.vo.MedicoVo;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Lord_Nightmare
 */
public class MedicoDelegado {

    private final JPanel contenedor;
    private final MedicoDao medicoDao;

    public MedicoDelegado(JPanel contenedor) {
        this.contenedor = contenedor;
        this.medicoDao = new MedicoDao();
    }

    public void registrarMedico(MedicoVo medico) {
        try {
            this.medicoDao.insertar(medico);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(contenedor, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void editarMedico(MedicoVo medico) {
        try {
            this.medicoDao.editar(medico);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(contenedor, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public List<MedicoVo> consultarMedicos() {
        List<MedicoVo> listaMedicos = new ArrayList<>();
        try {
            listaMedicos = this.medicoDao.consultar();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(contenedor, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        return listaMedicos;
    }

    public MedicoVo consultarMedico(int id) {
        MedicoVo medico = new MedicoVo();
        try {
            medico = this.medicoDao.consultar(id);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(contenedor, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        return medico;
    }
}
