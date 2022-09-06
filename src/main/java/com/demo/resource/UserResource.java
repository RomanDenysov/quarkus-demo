package com.demo.resource;

import com.demo.model.dto.UserDTO;
import com.demo.service.UserService;
import org.eclipse.microprofile.openapi.annotations.Operation;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("users")
@Produces("application/json")
@Consumes("application/json")
public class UserResource {
    @Inject
    UserService userService;

    @GET
    @Operation(description = "Provides list of all users. " +
            "Takes any path param.")
    public List<UserDTO> getAllUsers() {
        return userService.getAll();
    }

    @GET
    @Path("{userId}")
    @Operation(description = "Provides user by id. " +
            "Id is a path param that specifies a concrete user. " +
            "Id must be positive, non-zero integer.")
    public UserDTO getSingleUserById(@PathParam("userId") Long userId) {
        return userService.getById(userId);
    }

    @POST
    @Operation(description = "Creates new unique user.")
    public Response createUser(UserDTO user) {
        userService.create(user);
        return Response.ok(user).status(201).build();
    }

    @PUT
    @Path("{userId}")
    @Operation(description = "Updates user.")
    public Response updateUserById(@PathParam("userId") Long userId,
                                   UserDTO user) {
        userService.update(userId, user);
        return Response.ok(user).build();
    }

    @DELETE
    @Path("{userId}")
    @Operation(description = "Deletes user.")
    public Response deleteUserById(@PathParam("userId") Long userId) {
        userService.delete(userId);
        return Response.noContent().build();
    }
}
