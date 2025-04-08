package org.candlepin.api.v1;

import org.candlepin.dto.api.server.v1.CdnDTO;
import org.candlepin.dto.api.server.v1.CertificateDTO;
import org.candlepin.dto.api.server.v1.EntitlementDTO;
import org.candlepin.dto.api.server.v1.ExceptionMessage;
import java.time.OffsetDateTime;
import org.candlepin.dto.api.server.v1.PoolDTO;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

import io.swagger.annotations.*;

import org.jboss.resteasy.plugins.providers.multipart.MultipartInput;
import java.util.Map;
import java.util.List;
import javax.validation.constraints.*;
import javax.validation.Valid;

@Path("/pools")
@Api(description = "the Pools API")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaJAXRSSpecServerCodegen", date = "2025-03-28T14:05:44.436523329-04:00[America/Detroit]", comments = "Generator version: 7.12.0")
public interface PoolsApi {

    /**
     * Removes a pool
     *
     * @param poolId Pool ID
     * @return Pool removed successfully
     * @return Cannot delete bonus pools, as they are auto generated
     * @return Subscription pool not found
     * @return An unexpected exception has occurred
     */
    @DELETE
    @Path("/{pool_id}")
    @Produces({ "application/json" })
    @ApiOperation(value = "", notes = "Removes a pool", authorizations = {
        
        @Authorization(value = "basicAuth")
         }, tags={ "pools" })
    @ApiResponses(value = { 
        @ApiResponse(code = 204, message = "Pool removed successfully", response = Void.class),
        @ApiResponse(code = 400, message = "Cannot delete bonus pools, as they are auto generated", response = ExceptionMessage.class),
        @ApiResponse(code = 404, message = "Subscription pool not found", response = ExceptionMessage.class),
        @ApiResponse(code = 500, message = "An unexpected exception has occurred", response = ExceptionMessage.class) })
    void deletePool(@PathParam("pool_id") @ApiParam("Pool ID") String poolId);


    /**
     * Retrieves a single pool
     *
     * @param poolId Pool ID
     * @param consumer Consumer UUID
     * @param activeon Uses ISO 8601 format
     * @return Retrieves a single pool by its ID
     * @return Users cannot access consumer(unit)
     * @return Subscription pool or consumer(unit) not found
     * @return An unexpected exception has occurred
     */
    @GET
    @Path("/{pool_id}")
    @Produces({ "application/json" })
    @ApiOperation(value = "", notes = "Retrieves a single pool", authorizations = {
        
        @Authorization(value = "basicAuth")
         }, tags={ "pools" })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Retrieves a single pool by its ID", response = PoolDTO.class),
        @ApiResponse(code = 403, message = "Users cannot access consumer(unit)", response = ExceptionMessage.class),
        @ApiResponse(code = 404, message = "Subscription pool or consumer(unit) not found", response = ExceptionMessage.class),
        @ApiResponse(code = 500, message = "An unexpected exception has occurred", response = ExceptionMessage.class) })
    PoolDTO getPool(@PathParam("pool_id") @ApiParam("Pool ID") String poolId,@QueryParam("consumer")  @ApiParam("Consumer UUID")  String consumer,@QueryParam("activeon")  @ApiParam("Uses ISO 8601 format")  OffsetDateTime activeon);


    /**
     * Retrieve a CDN for a Pool
     *
     * @param poolId Pool ID
     * @return CDN details for a Pool
     * @return Subscription pool with given ID could not be found
     * @return An unexpected exception has occurred
     */
    @GET
    @Path("/{pool_id}/cdn")
    @Produces({ "application/json" })
    @ApiOperation(value = "", notes = "Retrieve a CDN for a Pool", authorizations = {
        
        @Authorization(value = "basicAuth")
         }, tags={ "pools" })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "CDN details for a Pool", response = CdnDTO.class),
        @ApiResponse(code = 404, message = "Subscription pool with given ID could not be found", response = ExceptionMessage.class),
        @ApiResponse(code = 500, message = "An unexpected exception has occurred", response = ExceptionMessage.class) })
    CdnDTO getPoolCdn(@PathParam("pool_id") @ApiParam("Pool ID") String poolId);


    /**
     * Retrieve a list of entitlements for a pool
     *
     * @param poolId Pool ID
     * @return A list of entitlements
     * @return Subscription pool with given ID could not be found
     * @return An unexpected exception has occurred
     */
    @GET
    @Path("/{pool_id}/entitlements")
    @Produces({ "application/json" })
    @ApiOperation(value = "", notes = "Retrieve a list of entitlements for a pool", authorizations = {
        
        @Authorization(value = "basicAuth")
         }, tags={ "pools" })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "A list of entitlements", response = EntitlementDTO.class, responseContainer = "List"),
        @ApiResponse(code = 404, message = "Subscription pool with given ID could not be found", response = ExceptionMessage.class, responseContainer = "List"),
        @ApiResponse(code = 500, message = "An unexpected exception has occurred", response = ExceptionMessage.class, responseContainer = "List") })
    Iterable<EntitlementDTO> getPoolEntitlements(@PathParam("pool_id") @ApiParam("Pool ID") String poolId);


    /**
     * Retrieves a Subscription Certificate as JSON (when Content-Type is application/json) or as PEM (when Content-Type is text/plain) 
     *
     * @param poolId Pool ID
     * @return Certificate details for a Pool
     * @return Pool or certificate for given ID could not be found
     * @return An unexpected exception has occurred
     */
    @GET
    @Path("/{pool_id}/cert")
    @Produces({ "text/plain", "application/json" })
    @ApiOperation(value = "", notes = "Retrieves a Subscription Certificate as JSON (when Content-Type is application/json) or as PEM (when Content-Type is text/plain) ", authorizations = {
        
        @Authorization(value = "basicAuth")
         }, tags={ "pools" })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Certificate details for a Pool", response = Object.class),
        @ApiResponse(code = 404, message = "Pool or certificate for given ID could not be found", response = ExceptionMessage.class),
        @ApiResponse(code = 500, message = "An unexpected exception has occurred", response = ExceptionMessage.class) })
    Object getSubCert(@PathParam("pool_id") @ApiParam("Pool ID") String poolId);


    /**
     * Retrieve a list of Consumer UUIDs attached to a pool. Available only to superadmins
     *
     * @param poolId Pool ID
     * @return A list of consumer UUIDs
     * @return Subscription pool with given ID could not be found
     * @return An unexpected exception has occurred
     */
    @GET
    @Path("/{pool_id}/entitlements/consumer_uuids")
    @Produces({ "application/json" })
    @ApiOperation(value = "", notes = "Retrieve a list of Consumer UUIDs attached to a pool. Available only to superadmins", authorizations = {
        
        @Authorization(value = "basicAuth")
         }, tags={ "pools" })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "A list of consumer UUIDs", response = String.class, responseContainer = "List"),
        @ApiResponse(code = 404, message = "Subscription pool with given ID could not be found", response = ExceptionMessage.class, responseContainer = "List"),
        @ApiResponse(code = 500, message = "An unexpected exception has occurred", response = ExceptionMessage.class, responseContainer = "List") })
    List<String> listEntitledConsumerUuids(@PathParam("pool_id") @ApiParam("Pool ID") String poolId);


    /**
     * Retrieves a list of pools @deprecated Use the method on /owners. This endpoint supports paging with query parameters. For more details please visit https://www.candlepinproject.org/docs/candlepin/pagination.html#paginating-results-from-candlepin. 
     *
     * @param owner Owner ID
     * @param consumer Consumer UUID
     * @param product Product ID
     * @param listall Use with consumerUuid to list all pools available to the consumer. This will include pools which would otherwise be omitted due to a rules warning. (i.e. not recommended) Pools that trigger an error however will still be omitted. (no entitlements available, consumer type mismatch, etc) 
     * @param activeon Uses ISO 8601 format
     * @param page Page index to return
     * @param perPage Number of items to return per page
     * @param order Direction of ordering
     * @param sortBy Property to use for ordering
     * @return A list of pools
     * @return Both consumer(unit) and owner are given, or a product id is specified without a consumer(unit) or owner 
     * @return Users cannot access either of the consumer, owner, or pools
     * @return Specified consumer(unit) or owner is not found
     * @return An unexpected exception has occurred
     */
    @GET
    @Produces({ "application/json" })
    @ApiOperation(value = "", notes = "Retrieves a list of pools @deprecated Use the method on /owners. This endpoint supports paging with query parameters. For more details please visit https://www.candlepinproject.org/docs/candlepin/pagination.html#paginating-results-from-candlepin. ", tags={ "pools" })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "A list of pools", response = PoolDTO.class, responseContainer = "List"),
        @ApiResponse(code = 400, message = "Both consumer(unit) and owner are given, or a product id is specified without a consumer(unit) or owner ", response = ExceptionMessage.class, responseContainer = "List"),
        @ApiResponse(code = 403, message = "Users cannot access either of the consumer, owner, or pools", response = ExceptionMessage.class, responseContainer = "List"),
        @ApiResponse(code = 404, message = "Specified consumer(unit) or owner is not found", response = ExceptionMessage.class, responseContainer = "List"),
        @ApiResponse(code = 500, message = "An unexpected exception has occurred", response = ExceptionMessage.class, responseContainer = "List") })
    List<PoolDTO> listPools(@QueryParam("owner")  @ApiParam("Owner ID")  String owner,@QueryParam("consumer")  @ApiParam("Consumer UUID")  String consumer,@QueryParam("product")  @ApiParam("Product ID")  String product,@QueryParam("listall") @DefaultValue("false")  @ApiParam("Use with consumerUuid to list all pools available to the consumer. This will include pools which would otherwise be omitted due to a rules warning. (i.e. not recommended) Pools that trigger an error however will still be omitted. (no entitlements available, consumer type mismatch, etc) ")  Boolean listall,@QueryParam("activeon")  @ApiParam("Uses ISO 8601 format")  OffsetDateTime activeon,@QueryParam("page")  @ApiParam("Page index to return")  Integer page,@QueryParam("per_page")  @ApiParam("Number of items to return per page")  Integer perPage,@QueryParam("order")  @ApiParam("Direction of ordering")  String order,@QueryParam("sort_by")  @ApiParam("Property to use for ordering")  String sortBy);

}
