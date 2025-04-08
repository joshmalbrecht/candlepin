package org.candlepin.api.v1;

import org.candlepin.dto.api.server.v1.ExceptionMessage;
import org.candlepin.dto.api.server.v1.SubscriptionDTO;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

import io.swagger.annotations.*;

import org.jboss.resteasy.plugins.providers.multipart.MultipartInput;
import java.util.Map;
import java.util.List;
import javax.validation.constraints.*;
import javax.validation.Valid;

@Path("/subscriptions")
@Api(description = "the Subscription API")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaJAXRSSpecServerCodegen", date = "2025-03-28T14:05:44.436523329-04:00[America/Detroit]", comments = "Generator version: 7.12.0")
public interface SubscriptionApi {

    /**
     * Activates a Subscription
     *
     * @param consumerUuid The UUID of the consumer of the subscription
     * @param email The email to send a notification to for the activation
     * @param emailLocale The locale of the email to send a notification to for the activation
     * @return A Subscription is being activated
     * @return Invalid request such as missing email/locale or when the consumer with the given uuid was not found
     * @return An unexpected exception has occurred
     */
    @POST
    @Produces({ "application/json" })
    @ApiOperation(value = "", notes = "Activates a Subscription", tags={ "subscription" })
    @ApiResponses(value = { 
        @ApiResponse(code = 202, message = "A Subscription is being activated", response = Void.class),
        @ApiResponse(code = 400, message = "Invalid request such as missing email/locale or when the consumer with the given uuid was not found", response = ExceptionMessage.class),
        @ApiResponse(code = 500, message = "An unexpected exception has occurred", response = ExceptionMessage.class) })
    javax.ws.rs.core.Response activateSubscription(@QueryParam("consumer_uuid") @NotNull  @ApiParam("The UUID of the consumer of the subscription")  String consumerUuid,@QueryParam("email") @NotNull  @ApiParam("The email to send a notification to for the activation")  String email,@QueryParam("email_locale") @NotNull  @ApiParam("The locale of the email to send a notification to for the activation")  String emailLocale);


    /**
     * Removes a Subscription
     *
     * @param id The ID of the subscription to remove
     * @return Subscription was successfully deleted
     * @return Subscription could not be found using the provided ID
     * @return An unexpected exception has occurred
     */
    @DELETE
    @Path("/{id}")
    @Produces({ "application/json" })
    @ApiOperation(value = "", notes = "Removes a Subscription", tags={ "subscription" })
    @ApiResponses(value = { 
        @ApiResponse(code = 204, message = "Subscription was successfully deleted", response = Void.class),
        @ApiResponse(code = 404, message = "Subscription could not be found using the provided ID", response = ExceptionMessage.class),
        @ApiResponse(code = 500, message = "An unexpected exception has occurred", response = ExceptionMessage.class) })
    void deleteSubscription(@PathParam("id") @ApiParam("The ID of the subscription to remove") String id);


    /**
     * Retrieves a list of Subscriptions
     *
     * @return A list of subscriptions
     * @return An unexpected exception has occurred
     */
    @GET
    @Produces({ "application/json" })
    @ApiOperation(value = "", notes = "Retrieves a list of Subscriptions", tags={ "subscription" })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "A list of subscriptions", response = SubscriptionDTO.class, responseContainer = "List"),
        @ApiResponse(code = 500, message = "An unexpected exception has occurred", response = ExceptionMessage.class, responseContainer = "List") })
    java.util.stream.Stream<SubscriptionDTO> getSubscriptions();

}
