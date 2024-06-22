package br.ejb;

import java.util.List;
import br.data.crud.CrudProject;
import br.data.model.Project;
import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;

@Stateless
@LocalBean
public class EjbProject {

    private CrudProject crudProject = new CrudProject();  // Instância do CrudProject

    public void addProject(String name, String description) {
        int id = crudProject.getAll().size() + 1;  // Calcula o ID com base no tamanho da lista
        Project project = new Project(id, name, description);
        crudProject.add(project);
        System.out.println("Projeto cadastrado com sucesso: " + project.getName());
    }

    public List<Project> getAll() {
        return crudProject.getAll();
    }

    public void removeProjectById(int id) {
        Project projectToRemove = crudProject.getById(id);
        if (projectToRemove != null) {
            crudProject.remove(projectToRemove);
            System.out.println("Projeto removido com sucesso: " + projectToRemove.getName());
        } else {
            System.out.println("Projeto com ID " + id + " não encontrado.");
        }
    }

    public Project getProjectById(int id) {
        return crudProject.getById(id);
    }

    public void updateProject(int id, String name, String description) {
        Project projectToUpdate = crudProject.getById(id);
        if (projectToUpdate != null) {
            projectToUpdate.setName(name);
            projectToUpdate.setDescription(description);
            crudProject.update(projectToUpdate);
            System.out.println("Projeto atualizado com sucesso: " + projectToUpdate.getName());
        } else {
            System.out.println("Projeto com ID " + id + " não encontrado.");
        }
    }
}
