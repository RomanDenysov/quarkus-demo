package com.demo.service;

import com.demo.model.dto.ContactDTO;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("contacts")
@RegisterRestClient(configKey = "contacts-api")
public interface ContactService {

    @GET
    @Path("{contactId}")
    @Produces(MediaType.APPLICATION_JSON)
    ContactDTO getContact(@PathParam("contactId") Long contactId);

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    ContactDTO createContact(Long userId);
}
