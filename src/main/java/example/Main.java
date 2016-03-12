package example;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.core.UriBuilder;
import java.awt.*;
import java.io.IOException;
import java.net.URI;

public class Main {

    private static Logger log = LoggerFactory.getLogger(Main.class);

    static final URI BASE_URI = UriBuilder.fromUri("http://localhost/").port(8090).build();


    public static void main(String[] args) throws IOException {

        final HttpServer server =
                GrizzlyHttpServerFactory.createHttpServer(
                        BASE_URI,
                        ResourceConfig.forApplicationClass(RsResourceConfig.class));

        Runtime.getRuntime().addShutdownHook(new Thread(server::shutdownNow));
        openBrowser();

        log.info("Start grizzly. Press any key to exit.");
        System.in.read();
        System.exit(0);

    }


    private static void openBrowser() {
        try {
            if (Desktop.isDesktopSupported())
                Desktop.getDesktop().browse(Main.BASE_URI);
        } catch (IOException ignore) {
            log.error("failed to launch browser.", ignore);
        }
    }


    public static class RsResourceConfig extends ResourceConfig {

        public RsResourceConfig() {

            packages("example.web.resource");

        }
    }
}
