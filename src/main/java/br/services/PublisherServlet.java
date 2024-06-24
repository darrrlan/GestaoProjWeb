package br.services;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.xml.ws.Endpoint;
import br.ejb.EjbProject;

@WebServlet(name = "PublisherServlet", urlPatterns = {"/JAX-start"})
public class PublisherServlet extends HttpServlet {

    @Override
    public void init() throws ServletException {
        super.init();

        // Endereço de publicação do serviço web JAX-WS
        String address = "http://localhost:1010/gestao-projetos-ws";
        
        // Instância do serviço JAX-WS
        EjbProject greetingService = new EjbProject();

        // Publica o serviço web JAX-WS
        Endpoint.publish(address, greetingService);

        System.out.println("Serviço web JAX-WS GestaoProjetosService publicado em: " + address);
    }

    @Override
    public void destroy() {
        // Desliga o serviço web JAX-WS ao destruir a servlet (opcional)
        Endpoint.publish("/gestao-projetos-ws", null);

        super.destroy();
    }
}
