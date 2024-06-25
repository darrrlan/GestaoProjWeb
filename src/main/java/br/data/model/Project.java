package br.data.model;

import java.io.Serializable;
import lombok.Data;

@Data
public class Project implements Serializable {
    private static final long serialVersionUID = 4370075544319805280L; // Definindo serialVersionUID explicitamente
    private int id;
    private String name;
    private String description;

    public Project(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id = id;
    }
}
