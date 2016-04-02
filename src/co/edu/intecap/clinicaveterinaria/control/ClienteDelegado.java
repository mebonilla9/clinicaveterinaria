/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.intecap.clinicaveterinaria.control;

import co.edu.intecap.clinicaveterinaria.modelo.dao.ClienteDao;
import co.edu.intecap.clinicaveterinaria.modelo.vo.ClienteVo;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Lord_Nightmare
 */
public class ClienteDelegado {
    
    private final JFrame contenedor;
    private final ClienteDao clienteDao;
    
    public ClienteDelegado(JFrame contenedor){
        this.contenedor = contenedor;
        this.clienteDao = new ClienteDao();
    }
    
    public void registrarCliente(ClienteVo cliente){
        try {
            this.clienteDao.insertar(cliente);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(contenedor, e.getMessage());
        }
    }
    
    public void editarCliente(ClienteVo cliente){
        try {
            this.clienteDao.editar(cliente);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(contenedor, e.getMessage());
        }
    }
    
    public List<ClienteVo> consultarClientes(){
        List<ClienteVo> listaClientes = new ArrayList<>();
        try {
            listaClientes = this.clienteDao.consultar();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(contenedor, e.getMessage());
        }
        return listaClientes;
    }
    
    public ClienteVo consultarCliente(int idCliente){
        ClienteVo cliente = new ClienteVo();
        try {
            cliente = this.clienteDao.consultar(idCliente);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(contenedor, e.getMessage());
        }
        return cliente;
    }
}
