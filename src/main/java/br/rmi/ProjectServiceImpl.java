package br.rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import br.data.crud.CrudProject;
import br.data.model.Project;

public class ProjectServiceImpl extends UnicastRemoteObject implements ProjectService {

    private CrudProject crudProject;

    public ProjectServiceImpl(CrudProject crudProject) throws RemoteException {
        super();
        this.crudProject = crudProject;
    }

    @Override
    public void addProject(String name, String description) throws RemoteException {
        crudProject.add(new Project(crudProject.getAll().size() + 1, name, description));
    }

    @Override
    public List<Project> getAll() throws RemoteException {
        return crudProject.getAll();
    }

    @Override
    public void removeProjectById(int id) throws RemoteException {
        Project projectToRemove = crudProject.getById(id);
        if (projectToRemove != null) {
            crudProject.remove(projectToRemove);
        } else {
            throw new RemoteException("Project with ID " + id + " not found.");
        }
    }

    @Override
    public Project getProjectById(int id) throws RemoteException {
        return crudProject.getById(id);
    }

    @Override
    public void updateProject(int id, String name, String description) throws RemoteException {
        Project projectToUpdate = crudProject.getById(id);
        if (projectToUpdate != null) {
            projectToUpdate.setName(name);
            projectToUpdate.setDescription(description);
            crudProject.update(projectToUpdate);
        } else {
            throw new RemoteException("Project with ID " + id + " not found.");
        }
    }

    // Método estático para iniciar o serviço RMI
    public static void startRMIService(CrudProject crudProject) throws Exception {
        ProjectService projectService = new ProjectServiceImpl(crudProject);
        java.rmi.registry.Registry registry = java.rmi.registry.LocateRegistry.createRegistry(1099);
        registry.rebind("ProjectService", projectService);
        System.out.println("ProjectService bound and ready for clients.");
    }
}
