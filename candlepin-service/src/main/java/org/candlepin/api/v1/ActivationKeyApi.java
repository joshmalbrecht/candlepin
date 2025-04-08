package org.candlepin.api.v1;

import org.candlepin.dto.api.server.v1.ActivationKeyDTO;
import org.candlepin.dto.api.server.v1.ContentOverrideDTO;
import org.candlepin.dto.api.server.v1.ExceptionMessage;
import org.candlepin.dto.api.server.v1.PoolDTO;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

import io.swagger.annotations.*;

import org.jboss.resteasy.plugins.providers.multipart.MultipartInput;
import java.util.Map;
import java.util.List;
import javax.validation.constraints.*;
import javax.validation.Valid;

@Path("/activation_keys")
@Api(description = "the ActivationKey API")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaJAXRSSpecServerCodegen", date = "2025-03-28T14:05:44.436523329-04:00[America/Detroit]", comments = "Generator version: 7.12.0")
public interface ActivationKeyApi {

    /**
     * Adds or Updates a list of Content Overrides
     *
     * @param activationKeyId The ID of the activation key
     * @param contentOverrideDTO The list of the content overrides
     * @return List of the created/update content overrides of the Activation Key
     * @return ActivationKey with id test_id could not be found.
     * @return Insufficient permissions
     * @return An unexpected exception has occurred
     */
    @PUT
    @Path("/{activation_key_id}/content_overrides")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @ApiOperation(value = "", notes = "Adds or Updates a list of Content Overrides", tags={ "activation_key" })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "List of the created/update content overrides of the Activation Key", response = ContentOverrideDTO.class, responseContainer = "List"),
        @ApiResponse(code = 400, message = "ActivationKey with id test_id could not be found.", response = ExceptionMessage.class, responseContainer = "List"),
        @ApiResponse(code = 403, message = "Insufficient permissions", response = ExceptionMessage.class, responseContainer = "List"),
        @ApiResponse(code = 500, message = "An unexpected exception has occurred", response = ExceptionMessage.class, responseContainer = "List") })
    java.util.stream.Stream<ContentOverrideDTO> addActivationKeyContentOverrides(@PathParam("activation_key_id") @ApiParam("The ID of the activation key") String activationKeyId,@Valid @NotNull List<ContentOverrideDTO> contentOverrideDTO);


    /**
     * Adds a Pool to an Activation Key
     *
     * @param activationKeyId ID of the activation key
     * @param poolId ID of the pool
     * @param quantity 
     * @return Pool added to activation key
     * @return Invalid inputs, pool could not be added to activation key
     * @return An unexpected exception has occurred
     */
    @POST
    @Path("/{activation_key_id}/pools/{pool_id}")
    @Produces({ "application/json" })
    @ApiOperation(value = "", notes = "Adds a Pool to an Activation Key", tags={ "activation_key" })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Pool added to activation key", response = ActivationKeyDTO.class),
        @ApiResponse(code = 400, message = "Invalid inputs, pool could not be added to activation key", response = ExceptionMessage.class),
        @ApiResponse(code = 500, message = "An unexpected exception has occurred", response = ExceptionMessage.class) })
    ActivationKeyDTO addPoolToKey(@PathParam("activation_key_id") @ApiParam("ID of the activation key") String activationKeyId,@PathParam("pool_id") @ApiParam("ID of the pool") String poolId,@QueryParam("quantity")   Long quantity);


    /**
     * Adds an Product ID to an Activation Key
     *
     * @param activationKeyId ID of the activation key
     * @param productId ID of the product
     * @return Product ID added to an activation key
     * @return Invalid inputs, product ID could not be added to activation key
     * @return An unexpected exception has occurred
     */
    @POST
    @Path("/{activation_key_id}/product/{product_id}")
    @Produces({ "application/json" })
    @ApiOperation(value = "", notes = "Adds an Product ID to an Activation Key", tags={ "activation_key" })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Product ID added to an activation key", response = ActivationKeyDTO.class),
        @ApiResponse(code = 400, message = "Invalid inputs, product ID could not be added to activation key", response = ExceptionMessage.class),
        @ApiResponse(code = 500, message = "An unexpected exception has occurred", response = ExceptionMessage.class) })
    ActivationKeyDTO addProductIdToKey(@PathParam("activation_key_id") @ApiParam("ID of the activation key") String activationKeyId,@PathParam("product_id") @ApiParam("ID of the product") String productId);


    /**
     * Deletes an activation key
     *
     * @param activationKeyId The ID of the activation key to be deleted
     * @return A successful operation
     * @return Activation key ID is null, empty or could not be found
     * @return An unexpected exception has occurred
     */
    @DELETE
    @Path("/{activation_key_id}")
    @Produces({ "application/json" })
    @ApiOperation(value = "", notes = "Deletes an activation key", tags={ "activation_key" })
    @ApiResponses(value = { 
        @ApiResponse(code = 204, message = "A successful operation", response = Void.class),
        @ApiResponse(code = 400, message = "Activation key ID is null, empty or could not be found", response = ExceptionMessage.class),
        @ApiResponse(code = 500, message = "An unexpected exception has occurred", response = ExceptionMessage.class) })
    void deleteActivationKey(@PathParam("activation_key_id") @ApiParam("The ID of the activation key to be deleted") String activationKeyId);


    /**
     * Deletes a list of Content Overrides
     *
     * @param activationKeyId The ID of the activation key
     * @param contentOverrideDTO The list of the content overrides
     * @return List of the deleted content overrides of the Activation Key
     * @return ActivationKey with id test_id could not be found.
     * @return Insufficient permissions
     * @return An unexpected exception has occurred
     */
    @DELETE
    @Path("/{activation_key_id}/content_overrides")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @ApiOperation(value = "", notes = "Deletes a list of Content Overrides", tags={ "activation_key" })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "List of the deleted content overrides of the Activation Key", response = ContentOverrideDTO.class, responseContainer = "List"),
        @ApiResponse(code = 400, message = "ActivationKey with id test_id could not be found.", response = ExceptionMessage.class, responseContainer = "List"),
        @ApiResponse(code = 403, message = "Insufficient permissions", response = ExceptionMessage.class, responseContainer = "List"),
        @ApiResponse(code = 500, message = "An unexpected exception has occurred", response = ExceptionMessage.class, responseContainer = "List") })
    java.util.stream.Stream<ContentOverrideDTO> deleteActivationKeyContentOverrides(@PathParam("activation_key_id") @ApiParam("The ID of the activation key") String activationKeyId,@Valid @NotNull List<ContentOverrideDTO> contentOverrideDTO);


    /**
     * Retrieves a list of activation keys
     *
     * @return List of activation keys
     * @return An unexpected exception has occurred
     */
    @GET
    @Produces({ "application/json" })
    @ApiOperation(value = "", notes = "Retrieves a list of activation keys", tags={ "activation_key" })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "List of activation keys", response = ActivationKeyDTO.class, responseContainer = "List"),
        @ApiResponse(code = 500, message = "An unexpected exception has occurred", response = ExceptionMessage.class, responseContainer = "List") })
    java.util.stream.Stream<ActivationKeyDTO> findActivationKey();


    /**
     * Retrieves a single activation key
     *
     * @param activationKeyId The ID of the activation key to retrieve
     * @return Activation key
     * @return Activation key ID is null, empty or could not be found
     * @return An unexpected exception has occurred
     */
    @GET
    @Path("/{activation_key_id}")
    @Produces({ "application/json" })
    @ApiOperation(value = "", notes = "Retrieves a single activation key", tags={ "activation_key" })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Activation key", response = ActivationKeyDTO.class),
        @ApiResponse(code = 400, message = "Activation key ID is null, empty or could not be found", response = ExceptionMessage.class),
        @ApiResponse(code = 500, message = "An unexpected exception has occurred", response = ExceptionMessage.class) })
    ActivationKeyDTO getActivationKey(@PathParam("activation_key_id") @ApiParam("The ID of the activation key to retrieve") String activationKeyId);


    /**
     * Retrieves a list of pools based on the activation key
     *
     * @param activationKeyId The ID of the activation key
     * @return List of pools based on the Activation Key
     * @return Activation key ID is null, empty or could not be found
     * @return An unexpected exception has occurred
     */
    @GET
    @Path("/{activation_key_id}/pools")
    @Produces({ "application/json" })
    @ApiOperation(value = "", notes = "Retrieves a list of pools based on the activation key", tags={ "activation_key" })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "List of pools based on the Activation Key", response = PoolDTO.class, responseContainer = "List"),
        @ApiResponse(code = 400, message = "Activation key ID is null, empty or could not be found", response = ExceptionMessage.class, responseContainer = "List"),
        @ApiResponse(code = 500, message = "An unexpected exception has occurred", response = ExceptionMessage.class, responseContainer = "List") })
    Iterable<PoolDTO> getActivationKeyPools(@PathParam("activation_key_id") @ApiParam("The ID of the activation key") String activationKeyId);


    /**
     * Retrieves list of Content Overrides
     *
     * @param activationKeyId The ID of the activation key
     * @return List of content overrides of the Activation Key
     * @return ActivationKey with id test_id could not be found.
     * @return Insufficient permissions
     * @return An unexpected exception has occurred
     */
    @GET
    @Path("/{activation_key_id}/content_overrides")
    @Produces({ "application/json" })
    @ApiOperation(value = "", notes = "Retrieves list of Content Overrides", tags={ "activation_key" })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "List of content overrides of the Activation Key", response = ContentOverrideDTO.class, responseContainer = "List"),
        @ApiResponse(code = 400, message = "ActivationKey with id test_id could not be found.", response = ExceptionMessage.class, responseContainer = "List"),
        @ApiResponse(code = 403, message = "Insufficient permissions", response = ExceptionMessage.class, responseContainer = "List"),
        @ApiResponse(code = 500, message = "An unexpected exception has occurred", response = ExceptionMessage.class, responseContainer = "List") })
    java.util.stream.Stream<ContentOverrideDTO> listActivationKeyContentOverrides(@PathParam("activation_key_id") @ApiParam("The ID of the activation key") String activationKeyId);


    /**
     * Removes a Pool from an Activation Key
     *
     * @param activationKeyId The ID of the activation key
     * @param poolId The ID of a pool to be removed
     * @return Pool removed from activation key
     * @return Invalid inputs, pool could not be removed from activation key
     * @return An unexpected exception has occurred
     */
    @DELETE
    @Path("/{activation_key_id}/pools/{pool_id}")
    @Produces({ "application/json" })
    @ApiOperation(value = "", notes = "Removes a Pool from an Activation Key", tags={ "activation_key" })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Pool removed from activation key", response = ActivationKeyDTO.class),
        @ApiResponse(code = 400, message = "Invalid inputs, pool could not be removed from activation key", response = ExceptionMessage.class),
        @ApiResponse(code = 500, message = "An unexpected exception has occurred", response = ExceptionMessage.class) })
    ActivationKeyDTO removePoolFromKey(@PathParam("activation_key_id") @ApiParam("The ID of the activation key") String activationKeyId,@PathParam("pool_id") @ApiParam("The ID of a pool to be removed") String poolId);


    /**
     * Removes a Product ID from an Activation Key
     *
     * @param activationKeyId The ID of the activation key
     * @param productId The ID of the product
     * @return Product ID removed from activation key
     * @return Invalid inputs, product ID could not remove from activation key
     * @return An unexpected exception has occurred
     */
    @DELETE
    @Path("/{activation_key_id}/product/{product_id}")
    @Produces({ "application/json" })
    @ApiOperation(value = "", notes = "Removes a Product ID from an Activation Key", tags={ "activation_key" })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Product ID removed from activation key", response = ActivationKeyDTO.class),
        @ApiResponse(code = 400, message = "Invalid inputs, product ID could not remove from activation key", response = ExceptionMessage.class),
        @ApiResponse(code = 500, message = "An unexpected exception has occurred", response = ExceptionMessage.class) })
    ActivationKeyDTO removeProductIdFromKey(@PathParam("activation_key_id") @ApiParam("The ID of the activation key") String activationKeyId,@PathParam("product_id") @ApiParam("The ID of the product") String productId);


    /**
     * Updates an Activation Key
     *
     * @param activationKeyId The ID of the activation key to be updated
     * @param activationKeyDTO Activation key to be updated
     * @return Updated the activation key
     * @return Invalid inputs, could not update activation key
     * @return An unexpected exception has occurred
     */
    @PUT
    @Path("/{activation_key_id}")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @ApiOperation(value = "", notes = "Updates an Activation Key", tags={ "activation_key" })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Updated the activation key", response = ActivationKeyDTO.class),
        @ApiResponse(code = 400, message = "Invalid inputs, could not update activation key", response = ExceptionMessage.class),
        @ApiResponse(code = 500, message = "An unexpected exception has occurred", response = ExceptionMessage.class) })
    ActivationKeyDTO updateActivationKey(@PathParam("activation_key_id") @ApiParam("The ID of the activation key to be updated") String activationKeyId,@Valid @NotNull ActivationKeyDTO activationKeyDTO);

}
