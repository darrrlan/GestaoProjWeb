package br.jsf;

import br.ejb.EjbProject;
import jakarta.ejb.EJB;
import jakarta.inject.Named;
import jakarta.enterprise.context.RequestScoped;
import lombok.Getter;
import lombok.Setter;

@Named(value = "jsfProject")
@RequestScoped
public class JsfProject {

    @EJB
    private EjbProject ejbProject;  // Injeção do EJB para operações com Project
    
    @Getter @Setter
    private int id;
    
    @Getter @Setter
    private String name;
    
    @Getter @Setter
    private String description;

    /**
     * Cria uma nova instância de JsfProject
     */
    public JsfProject() {
    }

    /**
     * Adiciona um novo projeto com os detalhes fornecidos.
     */
    public void addProject() {
        ejbProject.addProject(this.id, this.name, this.description);
    }
}
