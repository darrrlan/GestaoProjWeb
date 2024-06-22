package br.data.crud;

import br.data.model.Project;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe para operações CRUD relacionadas a Project.
 */
public class CrudProject {

    private List<Project> proj = new ArrayList<>();

    public CrudProject() {
        // Exemplos de projetos iniciais para fins de teste
        proj.add(new Project(1, "Projeto de extensão", "Tecnologia"));
        proj.add(new Project(2, "Projeto de IC", "Aplicativo"));
        proj.add(new Project(3, "Projeto de extensão", "Reconhecimento de imagens"));
    }

    public List<Project> getAll() {
        return new ArrayList<>(proj);
    }

    public void add(Project project) {
        proj.add(project);
    }

    public void remove(Project project) {
        proj.remove(project);
    }

    public void update(Project projectToUpdate) {
        // Encontra o projeto na lista pelo seu ID e atualiza
        for (int i = 0; i < proj.size(); i++) {
            Project p = proj.get(i);
            if (p.getId() == projectToUpdate.getId()) {
                // Atualiza o projeto com os novos valores usando os métodos gerados pelo Lombok
                p.setName(projectToUpdate.getName());
                p.setDescription(projectToUpdate.getDescription());
                proj.set(i, p);
                return;
            }
        }
        throw new IllegalArgumentException("Projeto com ID " + projectToUpdate.getId() + " não encontrado");
    }

    public Project getById(int id) {
        for (Project project : proj) {
            if (project.getId() == id) {
                return project;
            }
        }
        return null; // Retornar null se o projeto não for encontrado
    }
}
