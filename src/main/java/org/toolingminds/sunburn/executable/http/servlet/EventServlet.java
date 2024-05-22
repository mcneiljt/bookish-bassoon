package org.toolingminds.sunburn.executable.http.servlet;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.toolingminds.sunburn.emitter.Emitter;

import java.io.IOException;
import java.util.stream.Collectors;

public class EventServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setStatus(HttpServletResponse.SC_ACCEPTED);
        String body = request.getReader().lines().collect(Collectors.joining(""));
        String topic = getInitParameter("topic");
        Emitter emitter = Emitter.getOutput();
        emitter.send(topic, body);
        System.out.println("Received event: " + body);
    }
}
