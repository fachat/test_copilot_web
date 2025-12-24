package com.example.webapp.controller;

import com.example.webapp.model.User;
import com.example.webapp.service.UserService;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

/**
 * REST API controller for User operations using JAX-RS (JakartaEE).
 * Provides RESTful endpoints for CRUD operations on User entities.
 */
@Path("/api/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserRestController {

    @Inject
    UserService userService;

    /**
     * Get all users.
     * GET /api/users
     *
     * @return list of all users
     */
    @GET
    public Response getAllUsers() {
        List<User> users = userService.getAllUsers();
        return Response.ok(users).build();
    }

    /**
     * Get a user by ID.
     * GET /api/users/{id}
     *
     * @param id the user ID
     * @return the user if found, 404 otherwise
     */
    @GET
    @Path("/{id}")
    public Response getUserById(@PathParam("id") Long id) {
        return userService.getUserById(id)
                .map(user -> Response.ok(user).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    /**
     * Create a new user.
     * POST /api/users
     *
     * @param user the user to create
     * @return the created user with 201 status
     */
    @POST
    public Response createUser(@Valid User user) {
        User createdUser = userService.createUser(user);
        return Response.status(Response.Status.CREATED).entity(createdUser).build();
    }

    /**
     * Update an existing user.
     * PUT /api/users/{id}
     *
     * @param id the user ID
     * @param user the updated user details
     * @return the updated user if found, 404 otherwise
     */
    @PUT
    @Path("/{id}")
    public Response updateUser(@PathParam("id") Long id, @Valid User user) {
        return userService.updateUser(id, user)
                .map(updatedUser -> Response.ok(updatedUser).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    /**
     * Delete a user by ID.
     * DELETE /api/users/{id}
     *
     * @param id the user ID
     * @return 204 No Content if deleted, 404 otherwise
     */
    @DELETE
    @Path("/{id}")
    public Response deleteUser(@PathParam("id") Long id) {
        if (userService.deleteUser(id)) {
            return Response.noContent().build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }
}
