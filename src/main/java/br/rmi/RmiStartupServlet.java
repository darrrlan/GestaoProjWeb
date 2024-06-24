package br.rmi;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

@WebServlet(name = "RmiStartupServlet", loadOnStartup = 1, urlPatterns = {"/rmi-startup"})
public class RmiStartupServlet extends HttpServlet {

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);

        try {
            // Criar um registro RMI na porta padrão 1099
            Registry registry = LocateRegistry.createRegistry(1099);

            // Instanciar e vincular o objeto de implementação do serviço RMI ao registro
            EjbProjectImpl ejbProject = new EjbProjectImpl();
            registry.rebind("projectService", ejbProject);

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
