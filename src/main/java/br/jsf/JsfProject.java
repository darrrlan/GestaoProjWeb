package br.jsf;

import br.data.model.Project;
import br.ejb.EjbProject;
import jakarta.annotation.ManagedBean;
import jakarta.ejb.EJB;
import jakarta.inject.Named;
import jakarta.enterprise.context.RequestScoped;
import java.util.ArrayList;
import lombok.Getter;
import lombok.Setter;

@Named(value = "jsfProject")
@ManagedBean
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

    public ArrayList<Project> getAll() {
        return (ArrayList<Project>) ejbProject.getAll();
    }
}
