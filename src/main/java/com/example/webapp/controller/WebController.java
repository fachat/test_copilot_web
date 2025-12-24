package com.example.webapp.controller;

import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

/**
 * Web controller for serving HTML pages using Qute templates.
 */
@Path("/")
public class WebController {

    @Inject
    Template index;

    @Inject
    Template home;

    /**
     * Serve the index/landing page.
     *
     * @return rendered index template
     */
    @GET
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance getIndex() {
        return index.data("title", "Web Application Skeleton");
    }

    /**
     * Serve the home page (after authentication).
     *
     * @return rendered home template
     */
    @GET
    @Path("/home")
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance getHome() {
        return home.data("title", "Home")
                   .data("username", "User");
    }
}
