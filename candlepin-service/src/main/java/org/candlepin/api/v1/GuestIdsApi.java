package org.candlepin.api.v1;

import org.candlepin.dto.api.server.v1.ExceptionMessage;
import org.candlepin.dto.api.server.v1.GuestIdDTO;
import org.candlepin.dto.api.server.v1.GuestIdDTOArrayElement;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

import io.swagger.annotations.*;

import org.jboss.resteasy.plugins.providers.multipart.MultipartInput;
import java.util.Map;
import java.util.List;
import javax.validation.constraints.*;
import javax.validation.Valid;

@Path("/consumers/{consumer_uuid}/guestids")
@Api(description = "the GuestIds API")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaJAXRSSpecServerCodegen", date = "2025-03-28T14:05:44.436523329-04:00[America/Detroit]", comments = "Generator version: 7.12.0")
public interface GuestIdsApi {

    /**
     * Removes the guest from the consumer
     *
     * @param consumerUuid The UUID of the consumer who owns or hosts the guest
     * @param guestId The UUID of the guest to be deleted
     * @param unregister Unregister the consumer
     * @return A successful operation
     * @return Provided guest uuid is not valid or does not match with guest uuid in JSON
     * @return The consumer cannot unregister becuase of unknown type or invalid credentails
     * @return The consumer and/or guest could not be found using the provided uuid
     * @return A consumer was already deleted
     * @return An unexpected exception has occurred
     */
    @DELETE
    @Path("/{guest_id}")
    @Produces({ "application/json" })
    @ApiOperation(value = "", notes = "Removes the guest from the consumer", tags={ "guest_ids" })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "A successful operation", response = Void.class),
        @ApiResponse(code = 400, message = "Provided guest uuid is not valid or does not match with guest uuid in JSON", response = ExceptionMessage.class),
        @ApiResponse(code = 403, message = "The consumer cannot unregister becuase of unknown type or invalid credentails", response = ExceptionMessage.class),
        @ApiResponse(code = 404, message = "The consumer and/or guest could not be found using the provided uuid", response = ExceptionMessage.class),
        @ApiResponse(code = 410, message = "A consumer was already deleted", response = ExceptionMessage.class),
        @ApiResponse(code = 500, message = "An unexpected exception has occurred", response = ExceptionMessage.class) })
    void deleteGuest(@PathParam("consumer_uuid") @ApiParam("The UUID of the consumer who owns or hosts the guest") String consumerUuid,@PathParam("guest_id") @ApiParam("The UUID of the guest to be deleted") String guestId,@QueryParam("unregister") @NotNull @DefaultValue("false")  @ApiParam("Unregister the consumer")  Boolean unregister);


    /**
     * Retrieves a single Guest by its consumer and the guest UUID
     *
     * @param consumerUuid The UUID of the consumer to retrieve guest
     * @param guestId The UUID of the guest to retrieve
     * @return A successful operation
     * @return A Guest could not be found using the provided UUID
     * @return An unexpected exception has occurred
     */
    @GET
    @Path("/{guest_id}")
    @Produces({ "application/json" })
    @ApiOperation(value = "", notes = "Retrieves a single Guest by its consumer and the guest UUID", tags={ "guest_ids" })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "A successful operation", response = GuestIdDTO.class),
        @ApiResponse(code = 404, message = "A Guest could not be found using the provided UUID", response = ExceptionMessage.class),
        @ApiResponse(code = 500, message = "An unexpected exception has occurred", response = ExceptionMessage.class) })
    GuestIdDTO getGuestId(@PathParam("consumer_uuid") @ApiParam("The UUID of the consumer to retrieve guest") String consumerUuid,@PathParam("guest_id") @ApiParam("The UUID of the guest to retrieve") String guestId);


    /**
     * Retrieves the list of a Consumer's Guests
     *
     * @param consumerUuid 
     * @return A successful operation
     * @return An unexpected exception has occurred
     */
    @GET
    @Produces({ "application/json" })
    @ApiOperation(value = "", notes = "Retrieves the list of a Consumer's Guests", tags={ "guest_ids" })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "A successful operation", response = GuestIdDTOArrayElement.class, responseContainer = "List"),
        @ApiResponse(code = 500, message = "An unexpected exception has occurred", response = ExceptionMessage.class, responseContainer = "List") })
    java.util.stream.Stream<GuestIdDTOArrayElement> getGuestIds(@PathParam("consumer_uuid") String consumerUuid);


    /**
     * Updates a single Guest on a Consumer. Allows virt-who to avoid uploading an entire list of guests
     *
     * @param consumerUuid The UUID of the consumer who owns or hosts the guest
     * @param guestId The UUID of the guest to be updated
     * @param guestIdDTO Updated guest data
     * @return A successful operation
     * @return Provided Guest UUID is not valid or does not match with guest UUID in JSON
     * @return A Consumer could not be found using the provided UUID
     * @return An unexpected exception has occurred
     */
    @PUT
    @Path("/{guest_id}")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @ApiOperation(value = "", notes = "Updates a single Guest on a Consumer. Allows virt-who to avoid uploading an entire list of guests", tags={ "guest_ids" })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "A successful operation", response = Void.class),
        @ApiResponse(code = 400, message = "Provided Guest UUID is not valid or does not match with guest UUID in JSON", response = ExceptionMessage.class),
        @ApiResponse(code = 404, message = "A Consumer could not be found using the provided UUID", response = ExceptionMessage.class),
        @ApiResponse(code = 500, message = "An unexpected exception has occurred", response = ExceptionMessage.class) })
    void updateGuest(@PathParam("consumer_uuid") @ApiParam("The UUID of the consumer who owns or hosts the guest") String consumerUuid,@PathParam("guest_id") @ApiParam("The UUID of the guest to be updated") String guestId,@Valid @NotNull GuestIdDTO guestIdDTO);


    /**
     * Updates the list of guests on a consumer.
     *
     * @param consumerUuid The UUID of the consumer who owns or hosts the guest
     * @param guestIdDTO The list of the guests to be updated
     * @return A successful operation
     * @return An unexpected exception has occurred
     */
    @PUT
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @ApiOperation(value = "", notes = "Updates the list of guests on a consumer.", tags={ "guest_ids" })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "A successful operation", response = Void.class),
        @ApiResponse(code = 500, message = "An unexpected exception has occurred", response = ExceptionMessage.class) })
    void updateGuests(@PathParam("consumer_uuid") @ApiParam("The UUID of the consumer who owns or hosts the guest") String consumerUuid,@Valid @NotNull List<GuestIdDTO> guestIdDTO);

}
