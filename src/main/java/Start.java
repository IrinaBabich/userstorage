import com.servlet.UserServlet;
import freemarker.ext.servlet.FreemarkerServlet;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

public class Start {

    public static void main(String[] args) throws Exception {
        Server server = new Server(3000);
        ServletContextHandler handler = new ServletContextHandler();

        ServletHolder usersHolder = new ServletHolder(new UserServlet());
        handler.addServlet(usersHolder, "/users");

        ServletHolder freemarkerHolder = new ServletHolder(new FreemarkerServlet());
        handler.addServlet(freemarkerHolder, "/users/add");

        server.setHandler(handler);
        server.start();
        server.join();
    }
}
