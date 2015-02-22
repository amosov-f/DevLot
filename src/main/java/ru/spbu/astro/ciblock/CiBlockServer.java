package ru.spbu.astro.ciblock;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.GnuParser;
import org.apache.commons.cli.OptionBuilder;
import org.apache.commons.cli.Options;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.webapp.WebAppContext;
import org.jetbrains.annotations.NotNull;
import ru.spbu.astro.ciblock.depot.ClassifierDepot;
import ru.spbu.astro.ciblock.depot.NearestNeighbourDepot;
import ru.spbu.astro.ciblock.depot.SpreadsheetDepot;
import ru.spbu.astro.ciblock.servlet.AdminHttpServlet;
import ru.spbu.astro.ciblock.servlet.FormHttpServlet;
import ru.spbu.astro.ciblock.servlet.HowHttpServlet;
import ru.spbu.astro.ciblock.servlet.SubmitHttpServlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.logging.Logger;

public final class CiBlockServer {
    private static final Logger LOGGER = Logger.getLogger(CiBlockServer.class.getName());

    private static final String OPT_PORT = "port";
    private static final char OPT_USERNAME = 'u';
    private static final char OPT_PASSWORD = 'p';
    private static final String OPT_CONFIG = "cfg";

    private static final Options OPTIONS = new Options() {{
        addOption(OptionBuilder.hasArg().isRequired().withDescription("server port").create(OPT_PORT));
        addOption(OptionBuilder.hasArg().isRequired().withDescription("google login").create(OPT_USERNAME));
        addOption(OptionBuilder.hasArg().isRequired().withDescription("google password").create(OPT_PASSWORD));
        addOption(OptionBuilder.hasArgs().isRequired().withDescription("config files").create(OPT_CONFIG));
    }};

    private static final List<Class<? extends HttpServlet>> SERVLETS = Arrays.asList(
            AdminHttpServlet.class,
            FormHttpServlet.class,
            SubmitHttpServlet.class,
            HowHttpServlet.class
    );

    private CiBlockServer() {
    }

    public static void main(@NotNull final String[] args) throws Exception {
        final CommandLine cmd = new GnuParser().parse(OPTIONS, args);
        final int port = Integer.parseInt(cmd.getOptionValue(OPT_PORT));
        final Properties properties = new Properties();
        for (final String configPath : cmd.getOptionValues(OPT_CONFIG)) {
            properties.load(CiBlockServer.class.getResourceAsStream(configPath));
        }
        properties.setProperty("username", cmd.getOptionValue(OPT_USERNAME));
        properties.setProperty("password", cmd.getOptionValue(OPT_PASSWORD));

        final Injector injector = Guice.createInjector(new AbstractModule() {
            @Override
            protected void configure() {
                bind(Properties.class).toInstance(properties);
                bind(SpreadsheetDepot.DataDepot.class);
                bind(SpreadsheetDepot.InfoDepot.class);
                bind(ClassifierDepot.class);
                bind(NearestNeighbourDepot.class);
                for (final Class<? extends HttpServlet> servlet : SERVLETS) {
                    bind(servlet).asEagerSingleton();
                }
            }
        });

        final Server server = new Server();

        final ServerConnector connector = new ServerConnector(server);
        connector.setPort(port);
        server.addConnector(connector);

        final WebAppContext context = new WebAppContext();
        context.setWar("web");
        for (final Class<? extends HttpServlet> servlet : SERVLETS) {
            context.addServlet(
                    new ServletHolder(injector.getInstance(servlet)),
                    servlet.getAnnotation(WebServlet.class).value()[0]
            );
        }
        server.setHandler(context);

        server.start();
        LOGGER.info("Server started on port " + port + " !");
    }
}
