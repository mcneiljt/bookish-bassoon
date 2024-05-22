package org.toolingminds.sunburn.executable.http;

import org.eclipse.jetty.ee10.servlet.DefaultServlet;
import org.eclipse.jetty.ee10.servlet.ServletContextHandler;
import org.eclipse.jetty.ee10.servlet.ServletHolder;
import org.eclipse.jetty.server.*;
import org.eclipse.jetty.server.handler.ContextHandler;
import org.eclipse.jetty.server.handler.ContextHandlerCollection;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.util.resource.Resource;
import org.eclipse.jetty.util.thread.QueuedThreadPool;
import org.toolingminds.sunburn.executable.http.servlet.EventServlet;
import org.toolingminds.sunburn.executable.http.servlet.HealthServlet;

import java.net.URI;
import java.net.URL;

public class HttpListener {
    public static void main(String[] args) throws Exception {
        System.out.println("Initiating the application...");
        QueuedThreadPool threadPool = new QueuedThreadPool();
        threadPool.setName("server");

        Server server = new Server(threadPool);
        ServerConnector connector = new ServerConnector(server);
        connector.setPort(8080);

        ServletContextHandler apiHandler = new ServletContextHandler();
        apiHandler.setContextPath("/api");
        apiHandler.addServlet(HealthServlet.class, "/health");
        apiHandler.addServlet(EventServlet.class, "/track").setInitParameter("topic", "track");
        apiHandler.addServlet(EventServlet.class, "/identify").setInitParameter("topic", "identify");
        apiHandler.addServlet(EventServlet.class, "/page").setInitParameter("topic", "page");

        server.addConnector(connector);
        server.setHandler(apiHandler);

        server.start();
    }
}