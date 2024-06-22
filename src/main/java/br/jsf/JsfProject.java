package br.jsf;

import br.data.model.Project;
import br.ejb.EjbProject;
import jakarta.ejb.EJB;
import jakarta.inject.Named;
import jakarta.enterprise.context.RequestScoped;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Named(value = "jsfProject")
@RequestScoped
public class JsfProject {

    @EJB
    private EjbProject ejbProject;

    @Getter @Setter
    private int id;

    @Getter @Setter
    private String name;

    @Getter @Setter
    private String description;

    @Getter
    private String message;

    public JsfProject() {
    }

    public String addProject() {
        try {
            ejbProject.addProject(name, description);
            message = "Projeto cadastrado com sucesso: " + name;
            return "projectList?faces-redirect=true";
        } catch (Exception e) {
            message = "Erro ao cadastrar projeto: " + e.getMessage();
            return null;
        }
    }

    public List<Project> getAll() {
        try {
            return ejbProject.getAll();
        } catch (Exception e) {
            message = "Erro ao carregar projetos: " + e.getMessage();
            return null;
        }
    }

    public String removeProjectById(int id) {
        try {
            ejbProject.removeProjectById(id);
            message = "Projeto removido com sucesso: ID " + id;
            return "projectList?faces-redirect=true";
        } catch (Exception e) {
            message = "Erro ao remover projeto: " + e.getMessage();
            return null;
        }
    }

    public String loadProjectById() {
        try {
            Project project = ejbProject.getProjectById(id);
            if (project != null) {
                this.id = project.getId();
                this.name = project.getName();
                this.description = project.getDescription();
                return "";
            } else {
                message = "Projeto não encontrado: ID " + id;
                return "projectList?faces-redirect=true";
            }
        } catch (Exception e) {
            message = "Erro ao carregar projeto: " + e.getMessage();
            return null;
        }
    }

    public String updateProject() {
        try {
            ejbProject.updateProject(id, name, description);
            message = "Projeto atualizado com sucesso: " + name;
            return "projectList?faces-redirect=true";
        } catch (Exception e) {
            message = "Erro ao atualizar projeto: " + e.getMessage();
            return null;
        }
    }
}
