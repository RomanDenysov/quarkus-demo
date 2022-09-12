package com.demo.resource;

import com.demo.model.dto.ContactDTO;
import com.demo.model.dto.UserDTO;
import com.demo.service.ContactService;
import com.demo.service.UserService;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.rest.client.inject.RestClient;

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

    @Inject
    @RestClient
    ContactService contactService;

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

    @GET
    @Path("{userId}/contacts")
    public ContactDTO getUserContacts(@PathParam("userId") Long userId) {
        UserDTO user = userService.getById(userId);
        return contactService.getContact(user.getContactId());
    }

    @POST
    @Operation(description = "Creates new unique user.")
    public Response createUser(UserDTO user) {
        userService.createUser(user);
        UserDTO userDTO = userService.getByName(user.getName());
        ContactDTO contactDTO = contactService.createContact(userDTO.getId());
        userService.updateContact(userDTO, contactDTO);
        return Response.ok(userDTO).status(201).build();
    }

    @PUT
    @Path("{userId}")
    @Operation(description = "Updates user.")
    public Response updateUserById(@PathParam("userId") Long userId,
                                   UserDTO user) {
        userService.updateUser(userId, user);
        return Response.ok(user).build();
    }

    @DELETE
    @Path("{userId}")
    @Operation(description = "Deletes user.")
    public Response deleteUserById(@PathParam("userId") Long userId) {
        userService.deleteUser(userId);
        return Response.noContent().build();
    }
}
