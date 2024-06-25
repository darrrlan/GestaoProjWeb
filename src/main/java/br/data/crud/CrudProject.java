package br.data.crud;

import br.data.model.Project;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CrudProject implements Serializable {

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
        int nextId = proj.isEmpty() ? 1 : proj.get(proj.size() - 1).getId() + 1;
        project.setId(nextId);
        proj.add(project);
    }

    public void remove(Project project) {
        proj.remove(project);
    }

    public Project getById(int id) {
        for (Project project : proj) {
            if (project.getId() == id) {
                return project;
            }
        }
        return null;
    }

    public void update(Project updatedProject) {
        for (int i = 0; i < proj.size(); i++) {
            if (proj.get(i).getId() == updatedProject.getId()) {
                proj.set(i, updatedProject);
                break;
            }
        }
    }
}
