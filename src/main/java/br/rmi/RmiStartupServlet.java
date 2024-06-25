package br.rmi;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import br.data.crud.CrudProject;
import java.io.IOException;

@WebServlet(name = "RmiStartupServlet", loadOnStartup = 1, urlPatterns = {"/rmi-startup"})
public class RmiStartupServlet extends HttpServlet {

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);

        try {
            // Iniciar o serviço RMI com a mesma instância de CrudProject usada por EjbProject
            CrudProject crudProject = new CrudProject();
            ProjectServiceImpl.startRMIService(crudProject);

            System.out.println("RMI server is ready.");
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServletException("Failed to initialize RMI server.", e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/plain");
        response.getWriter().println("RMI service started successfully!");
    }
}
