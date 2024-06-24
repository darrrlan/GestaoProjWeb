/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.rmi;

/**
 *
 * @author darla
 */
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import br.data.crud.CrudProject;
import br.data.lock.LockManager;
import br.data.model.Project;

public class EjbProjectImpl extends UnicastRemoteObject implements ProjectService {

    private CrudProject crudProject = new CrudProject();
    private LockManager lockManager = new LockManager();

    public EjbProjectImpl() throws RemoteException {
        super();
    }

    @Override
    public void addProject(String name, String description) throws RemoteException {
        try {
            System.out.println("Locking to add project...");
            lockManager.lock(0);
            System.out.println("Lock obtained to add project.");

            crudProject.add(new Project(crudProject.getAll().size() + 1, name, description));
            System.out.println("Project added successfully: " + name);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println("Releasing lock after adding project.");
            lockManager.unlock(0);
        }
    }

    @Override
    public List<Project> getAll() throws RemoteException {
        try {
            System.out.println("Locking to get all projects...");
            lockManager.lock(1);
            System.out.println("Lock obtained to get all projects.");

            return crudProject.getAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
            return null;
        } finally {
            System.out.println("Releasing lock after getting all projects.");
            lockManager.unlock(1);
        }
    }

    @Override
    public void removeProjectById(int id) throws RemoteException {
        try {
            System.out.println("Locking to remove project by ID " + id + "...");
            lockManager.lock(id);
            System.out.println("Lock obtained to remove project by ID " + id + ".");

            Project projectToRemove = crudProject.getById(id);
            if (projectToRemove != null) {
                crudProject.remove(projectToRemove);
                System.out.println("Project removed successfully: " + projectToRemove.getName());
            } else {
                System.out.println("Project with ID " + id + " not found.");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println("Releasing lock after removing project by ID " + id + ".");
            lockManager.unlock(id);
        }
    }

    @Override
    public Project getProjectById(int id) throws RemoteException {
        try {
            System.out.println("Locking to get project by ID " + id + "...");
            lockManager.lock(id);
            System.out.println("Lock obtained to get project by ID " + id + ".");

            return crudProject.getById(id);
        } catch (InterruptedException e) {
            e.printStackTrace();
            return null;
        } finally {
            System.out.println("Releasing lock after getting project by ID " + id + ".");
            lockManager.unlock(id);
        }
    }

    @Override
    public void updateProject(int id, String name, String description) throws RemoteException {
        try {
            System.out.println("Locking to update project by ID " + id + "...");
            lockManager.lock(id);
            System.out.println("Lock obtained to update project by ID " + id + ".");

            Project projectToUpdate = crudProject.getById(id);
            if (projectToUpdate != null) {
                projectToUpdate.setName(name);
                projectToUpdate.setDescription(description);
                crudProject.update(projectToUpdate);
                System.out.println("Project updated successfully: " + name);
            } else {
                System.out.println("Project with ID " + id + " not found.");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println("Releasing lock after updating project by ID " + id + ".");
            lockManager.unlock(id);
        }
    }
}