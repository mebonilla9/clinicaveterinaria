/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.intecap.clinicaveterinaria.control;

import co.edu.intecap.clinicaveterinaria.modelo.dao.HistoriaDao;
import co.edu.intecap.clinicaveterinaria.modelo.vo.HistoriaVo;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Lord_Nightmare
 */
public class HistoriaDelegado {
    
    private final JPanel contenedor;
    private final HistoriaDao historiaDao;

    public HistoriaDelegado(JPanel contenedor) {
        this.contenedor = contenedor;
        this.historiaDao = new HistoriaDao();
    }
    
    public void registrarMedico(HistoriaVo historia) {
        try {
            this.historiaDao.insertar(historia);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(contenedor, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void editarMedico(HistoriaVo historia) {
        try {
            this.historiaDao.editar(historia);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(contenedor, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public List<HistoriaVo> consultarMedicos() {
        List<HistoriaVo> listaHistorias = new ArrayList<>();
        try {
            listaHistorias = this.historiaDao.consultar();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(contenedor, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        return listaHistorias;
    }

    public HistoriaVo consultarMedico(int id) {
        HistoriaVo historia = new HistoriaVo();
        try {
            historia = this.historiaDao.consultar(id);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(contenedor, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        return historia;
    }
    
}
