package com.demo.resource;

import com.demo.model.dto.CardDTO;
import com.demo.service.CardService;
import org.eclipse.microprofile.openapi.annotations.Operation;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("users")
@Produces("application/json")
@Consumes("application/json")
public class CardResource {
    @Inject
    CardService cardService;

    @GET
    @Path("{userId}/cards")
    @Operation(description = "Provides all user's cards.")
    public List<CardDTO> getAllCardsByUser(@PathParam("userId") Long userId) {
        return cardService.getAllUsersCards(userId);
    }

    @GET
    @Path("{userId}/cards/{cardId}")
    @Operation(description = "Provides specified card.")
    public CardDTO getCardById(@PathParam("userId") Long userId,
                               @PathParam("cardId") Long cardId) {
        return cardService.getCardByUserIdAndCardId(userId, cardId);
    }

    @POST
    @Path("{userId}/cards")
    @Operation(description = "Creates new card for user.")
    public Response createCardByUser(@PathParam("userId") Long userId,
                                     CardDTO card) {
        cardService.createCardByUserId(userId, card);
        return Response.ok(card).status(201).build();
    }

    @PUT
    @Path("{userId}/cards/{cardId}")
    @Operation(description = "Updates card.")
    public Response updateCardByUser(@PathParam("userId") Long userId,
                                     @PathParam("cardId") Long cardId,
                                     CardDTO card) {
        cardService.updateCardByUserId(userId, cardId, card);
        return Response.ok(card).build();
    }

    @DELETE
    @Path("{userId}/cards/{cardId}")
    @Operation(description = "Deletes card.")
    public Response deleteCardByUser(@PathParam("userId") Long userId,
                                     @PathParam("cardId") Long cardId) {
        cardService.deleteCardByUserId(userId, cardId);
        return Response.noContent().build();
    }
}
