package org.candlepin.api.v1;

import org.candlepin.dto.api.server.v1.ConsumerTypeDTO;
import org.candlepin.dto.api.server.v1.ExceptionMessage;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

import io.swagger.annotations.*;

import org.jboss.resteasy.plugins.providers.multipart.MultipartInput;
import java.util.Map;
import java.util.List;
import javax.validation.constraints.*;
import javax.validation.Valid;

@Path("/consumertypes")
@Api(description = "the ConsumerType API")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaJAXRSSpecServerCodegen", date = "2025-03-28T14:05:44.436523329-04:00[America/Detroit]", comments = "Generator version: 7.12.0")
public interface ConsumerTypeApi {

    /**
     * Creates a new consumer type
     *
     * @param consumerTypeDTO The consumer type to create
     * @return 
     * @return An unexpected exception has occurred
     */
    @POST
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @ApiOperation(value = "", notes = "Creates a new consumer type", tags={ "consumer_type" })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "", response = ConsumerTypeDTO.class),
        @ApiResponse(code = 500, message = "An unexpected exception has occurred", response = ExceptionMessage.class) })
    ConsumerTypeDTO createConsumerType(@Valid ConsumerTypeDTO consumerTypeDTO);


    /**
     * Deletes an existing consumer type
     *
     * @param id The ID of the consumer type to delete
     * @return 
     * @return A consumer type could not be found using the provided ID
     * @return An unexpected exception has occurred
     */
    @DELETE
    @Path("/{id}")
    @Produces({ "application/json" })
    @ApiOperation(value = "", notes = "Deletes an existing consumer type", tags={ "consumer_type" })
    @ApiResponses(value = { 
        @ApiResponse(code = 204, message = "", response = Void.class),
        @ApiResponse(code = 404, message = "A consumer type could not be found using the provided ID", response = ExceptionMessage.class),
        @ApiResponse(code = 500, message = "An unexpected exception has occurred", response = ExceptionMessage.class) })
    void deleteConsumerType(@PathParam("id") @ApiParam("The ID of the consumer type to delete") String id);


    /**
     * Retrieves a specific consumer type
     *
     * @param id The ID of the consumer type to retrieve
     * @return 
     * @return A consumer type could not be found using the provided ID
     * @return An unexpected exception has occurred
     */
    @GET
    @Path("/{id}")
    @Produces({ "application/json" })
    @ApiOperation(value = "", notes = "Retrieves a specific consumer type", tags={ "consumer_type" })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "", response = ConsumerTypeDTO.class),
        @ApiResponse(code = 404, message = "A consumer type could not be found using the provided ID", response = ExceptionMessage.class),
        @ApiResponse(code = 500, message = "An unexpected exception has occurred", response = ExceptionMessage.class) })
    ConsumerTypeDTO getConsumerType(@PathParam("id") @ApiParam("The ID of the consumer type to retrieve") String id);


    /**
     * Retrieves a list of known consumer types
     *
     * @return 
     * @return An unexpected exception has occurred
     */
    @GET
    @Produces({ "application/json" })
    @ApiOperation(value = "", notes = "Retrieves a list of known consumer types", tags={ "consumer_type" })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "", response = ConsumerTypeDTO.class, responseContainer = "List"),
        @ApiResponse(code = 500, message = "An unexpected exception has occurred", response = ExceptionMessage.class, responseContainer = "List") })
    java.util.stream.Stream<ConsumerTypeDTO> getConsumerTypes();


    /**
     * Updates an existing consumer type
     *
     * @param id The ID of the consumer type to update
     * @param consumerTypeDTO The fields and updated values to apply to the specified consumer type
     * @return 
     * @return A consumer type could not be found using the provided ID
     * @return An unexpected exception has occurred
     */
    @PUT
    @Path("/{id}")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @ApiOperation(value = "", notes = "Updates an existing consumer type", tags={ "consumer_type" })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "", response = ConsumerTypeDTO.class),
        @ApiResponse(code = 404, message = "A consumer type could not be found using the provided ID", response = ExceptionMessage.class),
        @ApiResponse(code = 500, message = "An unexpected exception has occurred", response = ExceptionMessage.class) })
    ConsumerTypeDTO updateConsumerType(@PathParam("id") @ApiParam("The ID of the consumer type to update") String id,@Valid ConsumerTypeDTO consumerTypeDTO);

}
