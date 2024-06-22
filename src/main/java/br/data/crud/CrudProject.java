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
}
