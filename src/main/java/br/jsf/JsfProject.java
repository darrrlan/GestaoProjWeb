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
    private EjbProject ejbProject;  // Injeção do EJB para operações com Project
    
    @Getter @Setter
    private String name;
    
    @Getter @Setter
    private String description;
    
    @Getter
    private String message;

    public JsfProject() {
    }

    public String addProject() {
        ejbProject.addProject(this.name, this.description);
        this.message = "Projeto cadastrado com sucesso: " + this.name;
        return "projectList?faces-redirect=true";
    }

    public List<Project> getAll() {
        return ejbProject.getAll();
    }

    public String removeProjectById(int id) {
        System.out.println("Removendo projeto com ID: " + id);
        ejbProject.removeProjectById(id);
        this.message = "Projeto removido com sucesso: ID " + id;
        return "projectList?faces-redirect=true";
    }
}
