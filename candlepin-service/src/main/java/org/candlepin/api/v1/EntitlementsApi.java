package org.candlepin.api.v1;

import org.candlepin.dto.api.server.v1.AsyncJobStatusDTO;
import org.candlepin.dto.api.server.v1.EntitlementDTO;
import org.candlepin.dto.api.server.v1.ExceptionMessage;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

import io.swagger.annotations.*;

import org.jboss.resteasy.plugins.providers.multipart.MultipartInput;
import java.util.Map;
import java.util.List;
import javax.validation.constraints.*;
import javax.validation.Valid;

@Path("/entitlements")
@Api(description = "the Entitlements API")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaJAXRSSpecServerCodegen", date = "2025-03-28T14:05:44.436523329-04:00[America/Detroit]", comments = "Generator version: 7.12.0")
public interface EntitlementsApi {

    /**
     * Retrieves a single Entitlement
     *
     * @param entitlementId 
     * @return A successful operation
     * @return Entitlement could not be found.
     * @return An unexpected exception has occurred
     */
    @GET
    @Path("/{entitlement_id}")
    @Produces({ "application/json" })
    @ApiOperation(value = "", notes = "Retrieves a single Entitlement", tags={ "entitlements" })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "A successful operation", response = EntitlementDTO.class),
        @ApiResponse(code = 404, message = "Entitlement could not be found.", response = ExceptionMessage.class),
        @ApiResponse(code = 500, message = "An unexpected exception has occurred", response = ExceptionMessage.class) })
    EntitlementDTO getEntitlement(@PathParam("entitlement_id") String entitlementId);


    /**
     * Retrieves a Subscription Certificate
     *
     * @param dbid 
     * @return A successful operation
     * @return Entitlement could not be found. Unable to find upstream certificate for entitlement. 
     * @return An unexpected exception has occurred
     */
    @GET
    @Path("/{dbid}/upstream_cert")
    @Produces({ "text/plain", "application/json" })
    @ApiOperation(value = "", notes = "Retrieves a Subscription Certificate", tags={ "entitlements" })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "A successful operation", response = String.class),
        @ApiResponse(code = 404, message = "Entitlement could not be found. Unable to find upstream certificate for entitlement. ", response = ExceptionMessage.class),
        @ApiResponse(code = 500, message = "An unexpected exception has occurred", response = ExceptionMessage.class) })
    String getUpstreamCert(@PathParam("dbid") String dbid);


    /**
     * Checks Consumer for Product Entitlement
     *
     * @param consumerUuid 
     * @param productId 
     * @return Entitlement successfully found for customer
     * @return Entitlement could not be found.
     * @return An unexpected exception has occurred
     */
    @GET
    @Path("/consumer/{consumer_uuid}/product/{product_id}")
    @Produces({ "application/json" })
    @ApiOperation(value = "", notes = "Checks Consumer for Product Entitlement", tags={ "entitlements" })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Entitlement successfully found for customer", response = EntitlementDTO.class),
        @ApiResponse(code = 404, message = "Entitlement could not be found.", response = ExceptionMessage.class),
        @ApiResponse(code = 500, message = "An unexpected exception has occurred", response = ExceptionMessage.class) })
    EntitlementDTO hasEntitlement(@PathParam("consumer_uuid") String consumerUuid,@PathParam("product_id") String productId);


    /**
     * Retrieves list of Entitlements. This endpoint supports paging with query parameters. For more details please visit https://www.candlepinproject.org/docs/candlepin/pagination.html#paginating-results-from-candlepin
     *
     * @param consumer 
     * @param matches 
     * @param attribute Attribute filters
     * @param page Page index to return
     * @param perPage Number of items to return per page
     * @param order Direction of ordering
     * @param sortBy Property to use for ordering
     * @return A successful operation
     * @return Unit with ID \"test_id\" could not be found.
     * @return An unexpected exception has occurred
     */
    @GET
    @Produces({ "application/json" })
    @ApiOperation(value = "", notes = "Retrieves list of Entitlements. This endpoint supports paging with query parameters. For more details please visit https://www.candlepinproject.org/docs/candlepin/pagination.html#paginating-results-from-candlepin", tags={ "entitlements" })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "A successful operation", response = EntitlementDTO.class, responseContainer = "List"),
        @ApiResponse(code = 400, message = "Unit with ID \"test_id\" could not be found.", response = ExceptionMessage.class, responseContainer = "List"),
        @ApiResponse(code = 500, message = "An unexpected exception has occurred", response = ExceptionMessage.class, responseContainer = "List") })
    List<EntitlementDTO> listAllForConsumer(@QueryParam("consumer")   String consumer,@QueryParam("matches")   String matches,@QueryParam("attribute")  @ApiParam("Attribute filters")  List<String> attribute,@QueryParam("page")  @ApiParam("Page index to return")  Integer page,@QueryParam("per_page")  @ApiParam("Number of items to return per page")  Integer perPage,@QueryParam("order")  @ApiParam("Direction of ordering")  String order,@QueryParam("sort_by")  @ApiParam("Property to use for ordering")  String sortBy);


    /**
     * Migrate entitlements from one distributor consumer to another. Can specify full or partial quantity. No specified quantity will lead to full migration of the entitlement.
     *
     * @param entitlementId 
     * @param toConsumer 
     * @param quantity 
     * @return Entitlements successfuly migrated
     * @return Entitlement migration is not permissible for units this type. Source and destination units must belong to the same organization. The entitlement cannot be utilized by the destination unit. The quantity specified must be greater than zero and less than or equal to the total for this entitlement. 
     * @return Entitlement could not be found.
     * @return An unexpected exception has occurred
     */
    @PUT
    @Path("/{entitlement_id}/migrate")
    @Produces({ "application/json" })
    @ApiOperation(value = "", notes = "Migrate entitlements from one distributor consumer to another. Can specify full or partial quantity. No specified quantity will lead to full migration of the entitlement.", tags={ "entitlements" })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Entitlements successfuly migrated", response = EntitlementDTO.class, responseContainer = "List"),
        @ApiResponse(code = 400, message = "Entitlement migration is not permissible for units this type. Source and destination units must belong to the same organization. The entitlement cannot be utilized by the destination unit. The quantity specified must be greater than zero and less than or equal to the total for this entitlement. ", response = ExceptionMessage.class, responseContainer = "List"),
        @ApiResponse(code = 404, message = "Entitlement could not be found.", response = ExceptionMessage.class, responseContainer = "List"),
        @ApiResponse(code = 500, message = "An unexpected exception has occurred", response = ExceptionMessage.class, responseContainer = "List") })
    javax.ws.rs.core.Response migrateEntitlement(@PathParam("entitlement_id") String entitlementId,@QueryParam("to_consumer")   String toConsumer,@QueryParam("quantity")   Integer quantity);


    /**
     * Regenerates the Entitlement Certificates for a Product
     *
     * @param productId 
     * @param lazyRegen 
     * @return An entitlement certificate is being regenerated
     * @return An unexpected exception has occurred
     */
    @PUT
    @Path("/product/{product_id}")
    @Produces({ "application/json" })
    @ApiOperation(value = "", notes = "Regenerates the Entitlement Certificates for a Product", tags={ "entitlements" })
    @ApiResponses(value = { 
        @ApiResponse(code = 202, message = "An entitlement certificate is being regenerated", response = AsyncJobStatusDTO.class),
        @ApiResponse(code = 500, message = "An unexpected exception has occurred", response = ExceptionMessage.class) })
    AsyncJobStatusDTO regenerateEntitlementCertificatesForProduct(@PathParam("product_id") String productId,@QueryParam("lazy_regen") @DefaultValue("true")   Boolean lazyRegen);


    /**
     * Deletes an Entitlement
     *
     * @param dbid 
     * @return Entitlement successfuly deleted
     * @return Entitlement could not be found.
     * @return An unexpected exception has occurred
     */
    @DELETE
    @Path("/{dbid}")
    @Produces({ "application/json" })
    @ApiOperation(value = "", notes = "Deletes an Entitlement", tags={ "entitlements" })
    @ApiResponses(value = { 
        @ApiResponse(code = 204, message = "Entitlement successfuly deleted", response = Void.class),
        @ApiResponse(code = 404, message = "Entitlement could not be found.", response = ExceptionMessage.class),
        @ApiResponse(code = 500, message = "An unexpected exception has occurred", response = ExceptionMessage.class) })
    void unbind(@PathParam("dbid") String dbid);


    /**
     * Updates an Entitlement. This only works for the quantity.
     *
     * @param entitlementId 
     * @param entitlementDTO Updated entitlement
     * @return A successful operation
     * @return Quantity value must be greater than 0.
     * @return Entitlement could not be found.
     * @return An unexpected exception has occurred
     */
    @PUT
    @Path("/{entitlement_id}")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @ApiOperation(value = "", notes = "Updates an Entitlement. This only works for the quantity.", tags={ "entitlements" })
    @ApiResponses(value = { 
        @ApiResponse(code = 204, message = "A successful operation", response = Void.class),
        @ApiResponse(code = 400, message = "Quantity value must be greater than 0.", response = ExceptionMessage.class),
        @ApiResponse(code = 404, message = "Entitlement could not be found.", response = ExceptionMessage.class),
        @ApiResponse(code = 500, message = "An unexpected exception has occurred", response = ExceptionMessage.class) })
    void updateEntitlement(@PathParam("entitlement_id") String entitlementId,@Valid @NotNull EntitlementDTO entitlementDTO);

}
