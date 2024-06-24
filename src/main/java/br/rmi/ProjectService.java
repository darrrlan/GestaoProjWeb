/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.rmi;

/**
 *
 * @author darla
 */

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import br.data.model.Project;

public interface ProjectService extends Remote {
    void addProject(String name, String description) throws RemoteException;
    List<Project> getAll() throws RemoteException;
    void removeProjectById(int id) throws RemoteException;
    Project getProjectById(int id) throws RemoteException;
    void updateProject(int id, String name, String description) throws RemoteException;
}


