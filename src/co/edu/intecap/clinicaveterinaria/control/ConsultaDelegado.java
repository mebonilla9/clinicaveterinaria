/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.intecap.clinicaveterinaria.control;

import co.edu.intecap.clinicaveterinaria.modelo.dao.ConsultaDao;
import co.edu.intecap.clinicaveterinaria.modelo.vo.ConsultaVo;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Lord_Nightmare
 */
public class ConsultaDelegado {
    
    private final JPanel contenedor;
    private final ConsultaDao consultaDao;

    public ConsultaDelegado(JPanel contenedor) {
        this.contenedor = contenedor;
        this.consultaDao = new ConsultaDao();
    }
    
    public void registrarConsulta(ConsultaVo consulta) {
        try {
            this.consultaDao.insertar(consulta);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(contenedor, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void editarConsulta(ConsultaVo consulta) {
        try {
            this.consultaDao.editar(consulta);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(contenedor, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public List<ConsultaVo> consultarConsultas() {
        List<ConsultaVo> listaConsultas = new ArrayList<>();
        try {
            listaConsultas = this.consultaDao.consultar();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(contenedor, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        return listaConsultas;
    }

    public ConsultaVo consultarConsulta(int id) {
        ConsultaVo consulta = new ConsultaVo();
        try {
            consulta = this.consultaDao.consultar(id);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(contenedor, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        return consulta;
    }
    
}
