package br.ejb;

import java.util.List;
import br.data.crud.CrudProject;
import br.data.lock.LockManager;
import br.data.model.Project;
import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.jws.WebMethod;
import jakarta.jws.WebService;

@Stateless
@LocalBean
@WebService
public class EjbProject {

    private CrudProject crudProject = new CrudProject();
    private LockManager lockManager = new LockManager();
    
    @WebMethod
    public void addProject(String name, String description) {
        try {
            System.out.println("Wl[X]: Iniciando travamento para adicionar projeto...");
            lockManager.lock(0);
            System.out.println("W[x]: Travamento obtido para adicionar projeto.");

            crudProject.add(new Project(crudProject.getAll().size() + 1, name, description));
            System.out.println("Projeto cadastrado com sucesso: " + name);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println("Wu[X]: Liberando travamento para adicionar projeto.");
            lockManager.unlock(0);
        }
    }
    @WebMethod
    public List<Project> getAll() {
        try {
            System.out.println("rl[x]: Iniciando travamento para obter todos os projetos...");
            lockManager.lock(1);
            System.out.println("r[x]: Travamento obtido para obter todos os projetos.");

            return crudProject.getAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
            return null;
        } finally {
            System.out.println("ru[x]Liberando travamento para obter todos os projetos.");
            lockManager.unlock(1);
        }
    }
    @WebMethod
    public void removeProjectById(int id) {
        try {
            System.out.println("Wl[X]: Iniciando travamento para remover projeto pelo ID " + id + "...");
            lockManager.lock(id);
            System.out.println("W[x]: Travamento obtido para remover projeto pelo ID " + id + ".");

            Project projectToRemove = crudProject.getById(id);
            if (projectToRemove != null) {
                crudProject.remove(projectToRemove);
                System.out.println("Projeto removido com sucesso: " + projectToRemove.getName());
            } else {
                System.out.println("Projeto com ID " + id + " não encontrado.");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println("Liberando travamento para remover projeto pelo ID " + id + ".");
            lockManager.unlock(id);
        }
    }
    @WebMethod
    public Project getProjectById(int id) {
        try {
            System.out.println("rl[x]:Iniciando travamento para obter projeto pelo ID " + id + "...");
            lockManager.lock(id);
            System.out.println("r[x]:Travamento obtido para obter projeto pelo ID " + id + ".");

            return crudProject.getById(id);
        } catch (InterruptedException e) {
            e.printStackTrace();
            return null;
        } finally {
            System.out.println("ru[x]:: Liberando travamento para obter projeto pelo ID " + id + ".");
            lockManager.unlock(id);
        }
    }
    @WebMethod
    public void updateProject(int id, String name, String description) {
        try {
            System.out.println("Wl[X]: Iniciando travamento para atualizar projeto pelo ID " + id + "...");
            lockManager.lock(id);
            System.out.println("W[x]: Travamento obtido para atualizar projeto pelo ID " + id + ".");

            Project projectToUpdate = crudProject.getById(id);
            if (projectToUpdate != null) {
                projectToUpdate.setName(name);
                projectToUpdate.setDescription(description);
                crudProject.update(projectToUpdate);
                System.out.println("Projeto atualizado com sucesso: " + name);
            } else {
                System.out.println("Projeto com ID " + id + " não encontrado.");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println("Wu[X]:Liberando travamento para atualizar projeto pelo ID " + id + ".");
            lockManager.unlock(id);
        }
    }
}