package br.ejb;

import br.data.model.Project;
import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import java.util.ArrayList;
import java.util.List;

/**
 * EJB para operações relacionadas a Project.
 */
@Stateless
@LocalBean
public class EjbProject {

    // Lista para armazenar os projetos
    private List<Project> projects = new ArrayList<>();

    // Variável para armazenar a última mensagem de texto

    // Método para adicionar um novo projeto
    public void addProject(int id, String name, String description) {
        Project project = new Project(id, name, description);
        projects.add(project);
        
        // Define a mensagem de texto após cadastrar o projeto
        System.out.print("Projeto cadastrado com sucesso: " + project.getName());
    }
}

  
