package utfpr.edu.br.teste.rmi;
import br.data.model.Project;
import br.rmi.ProjectService;
import br.rmi.ProjectService;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;

public class ProjectClient {

    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);
            ProjectService projectService = (ProjectService) registry.lookup("ProjectService");

            // Adicionar projeto
            projectService.addProject("Project 1", "Description 1");

            // Obter todos os projetos
            List<Project> projects = projectService.getAll();
            System.out.println("Projects:");
            for (Project project : projects) {
                System.out.println(project);
            }
        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }
}
