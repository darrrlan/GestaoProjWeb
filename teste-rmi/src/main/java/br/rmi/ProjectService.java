package br.rmi;


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
