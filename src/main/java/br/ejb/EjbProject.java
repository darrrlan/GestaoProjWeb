package br.ejb;

// Importações necessárias
import java.util.List;
import br.data.crud.CrudProject;
import br.data.model.Project;
import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;

/**
 * EJB para operações relacionadas a Project.
 */
@Stateless
@LocalBean
public class EjbProject {

    private CrudProject crudProject = new CrudProject();  // Instância do CrudProject

    // Método para adicionar um novo projeto
    public void addProject(String name, String description) {
        int id = crudProject.getAll().size() + 1;  // Calcula o ID com base no tamanho da lista
        Project project = new Project(id, name, description);
        crudProject.add(project);
        System.out.println("Projeto cadastrado com sucesso: " + project.getName());
    }

    // Método para obter todos os projetos
    public List<Project> getAll() {
        return crudProject.getAll();
    }

    // Método para remover um projeto pelo ID
    public void removeProjectById(int id) {
        Project projectToRemove = crudProject.getById(id);

        if (projectToRemove != null) {
            crudProject.remove(projectToRemove);
            System.out.println("Projeto removido com sucesso: " + projectToRemove.getName());
        } else {
            System.out.println("Projeto com ID " + id + " não encontrado.");
        }
    }

}
