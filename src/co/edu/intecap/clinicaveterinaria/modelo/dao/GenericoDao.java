/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.intecap.clinicaveterinaria.modelo.dao;

import java.util.List;

/**
 *
 * @author Lord_Nightmare
 */
public interface GenericoDao<T> {
    
    public void insertar(T object);
    public void editar(T object);
    public List<T> consultar();
    public T consultar(int id);
    
}
