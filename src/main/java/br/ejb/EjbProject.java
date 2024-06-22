package br.ejb;

import br.data.crud.CrudProject;
import br.data.model.Project;
import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import java.util.List;

@Stateless
@LocalBean
public class EjbProject {

    private CrudProject crudProject = new CrudProject();
    

    // Método para adicionar um novo projeto
    public void addProject(String name, String description) {
        int id = crudProject.getAll().size() + 1;
        Project project = new Project(id, name, description);
        crudProject.add(project);
        System.out.println(project);
        
        System.out.println("Projeto cadastrado com sucesso: " + project.getName());
    }

    // Método para obter todos os projetos
    public List<Project> getAll() {
        System.out.println(crudProject.getAll());
        return crudProject.getAll();
    }
}
