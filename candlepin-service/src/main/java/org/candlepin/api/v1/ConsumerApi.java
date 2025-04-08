package org.candlepin.api.v1;

import org.candlepin.dto.api.server.v1.AsyncJobStatusDTO;
import org.candlepin.dto.api.server.v1.CertificateDTO;
import org.candlepin.dto.api.server.v1.CertificateSerialDTO;
import org.candlepin.dto.api.server.v1.ComplianceStatusDTO;
import org.candlepin.dto.api.server.v1.ConsumerDTO;
import org.candlepin.dto.api.server.v1.ConsumerDTOArrayElement;
import org.candlepin.dto.api.server.v1.ContentAccessDTO;
import org.candlepin.dto.api.server.v1.ContentOverrideDTO;
import org.candlepin.dto.api.server.v1.DeleteResult;
import org.candlepin.dto.api.server.v1.EntitlementDTO;
import org.candlepin.dto.api.server.v1.ExceptionMessage;
import java.io.File;
import java.util.Map;
import java.time.OffsetDateTime;
import org.candlepin.dto.api.server.v1.OwnerDTO;
import org.candlepin.dto.api.server.v1.PoolQuantityDTO;
import org.candlepin.dto.api.server.v1.ReleaseVerDTO;
import java.util.Set;
import org.candlepin.dto.api.server.v1.SystemPurposeComplianceStatusDTO;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

import io.swagger.annotations.*;

import org.jboss.resteasy.plugins.providers.multipart.MultipartInput;
import java.util.Map;
import java.util.List;
import javax.validation.constraints.*;
import javax.validation.Valid;

@Path("/consumers")
@Api(description = "the Consumer API")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaJAXRSSpecServerCodegen", date = "2025-03-28T14:05:44.436523329-04:00[America/Detroit]", comments = "Generator version: 7.12.0")
public interface ConsumerApi {

    /**
     * Adds or Updates a list of Content Overrides
     *
     * @param consumerUuid The ID of the Consumer
     * @param contentOverrideDTO The list of the content overrides
     * @return List of the created/update content overrides of the Consumer
     * @return The content labels are invalid. The content properties cannot be overridden. The override values are invalid. 
     * @return Insufficient permissions.
     * @return Consumer with this UUID could not be found.
     * @return An unexpected exception has occurred
     */
    @PUT
    @Path("/{consumer_uuid}/content_overrides")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @ApiOperation(value = "", notes = "Adds or Updates a list of Content Overrides", tags={ "consumer" })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "List of the created/update content overrides of the Consumer", response = ContentOverrideDTO.class, responseContainer = "List"),
        @ApiResponse(code = 400, message = "The content labels are invalid. The content properties cannot be overridden. The override values are invalid. ", response = ExceptionMessage.class, responseContainer = "List"),
        @ApiResponse(code = 403, message = "Insufficient permissions.", response = ExceptionMessage.class, responseContainer = "List"),
        @ApiResponse(code = 404, message = "Consumer with this UUID could not be found.", response = ExceptionMessage.class, responseContainer = "List"),
        @ApiResponse(code = 500, message = "An unexpected exception has occurred", response = ExceptionMessage.class, responseContainer = "List") })
    java.util.stream.Stream<ContentOverrideDTO> addConsumerContentOverrides(@PathParam("consumer_uuid") @ApiParam("The ID of the Consumer") String consumerUuid,@Valid @NotNull List<ContentOverrideDTO> contentOverrideDTO);


    /**
     * If a pool ID is specified, we know we're binding to that exact pool. Specifying an entitle date in this case makes no sense and will throw an error. If a list of product IDs are specified, we attempt to auto-bind to subscriptions which will provide those products. An optional date can be specified allowing the consumer to get compliant for some date in the future. If no date is specified we assume the current date. If neither a pool nor an ID is specified, this is a healing request. The path is similar to the bind by products, but in this case we use the installed products on the consumer, and their current compliant status, to determine which product IDs should be requested. The entitle date is used the same as with bind by products. The response will contain a list of Entitlement objects if async is false, or a JobDetail object if async is true
     *
     * @param consumerUuid Consumer UUID
     * @param pool Pool ID
     * @param product Product array
     * @param quantity Quantity
     * @param email Email address
     * @param emailLocale Email locale
     * @param async Operation Type async or sync
     * @param entitleDate Entitlement date
     * @param fromPool From pool
     * @return A successful operation
     * @return Invalid date, must use ISO 8601 format | Cannot bind by multiple parameters | Cannot specify a quantity when auto-binding. | Ignoring request to auto-attach. It is disabled for org because of the content access mode setting or hypervisor autobind setting.
     * @return Consumer with this UUID could not be found.
     * @return An unexpected exception has occurred
     */
    @POST
    @Path("/{consumer_uuid}/entitlements")
    @Produces({ "text/plain", "application/json" })
    @ApiOperation(value = "", notes = "If a pool ID is specified, we know we're binding to that exact pool. Specifying an entitle date in this case makes no sense and will throw an error. If a list of product IDs are specified, we attempt to auto-bind to subscriptions which will provide those products. An optional date can be specified allowing the consumer to get compliant for some date in the future. If no date is specified we assume the current date. If neither a pool nor an ID is specified, this is a healing request. The path is similar to the bind by products, but in this case we use the installed products on the consumer, and their current compliant status, to determine which product IDs should be requested. The entitle date is used the same as with bind by products. The response will contain a list of Entitlement objects if async is false, or a JobDetail object if async is true", tags={ "consumer" })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "A successful operation", response = String.class),
        @ApiResponse(code = 400, message = "Invalid date, must use ISO 8601 format | Cannot bind by multiple parameters | Cannot specify a quantity when auto-binding. | Ignoring request to auto-attach. It is disabled for org because of the content access mode setting or hypervisor autobind setting.", response = ExceptionMessage.class),
        @ApiResponse(code = 404, message = "Consumer with this UUID could not be found.", response = ExceptionMessage.class),
        @ApiResponse(code = 500, message = "An unexpected exception has occurred", response = ExceptionMessage.class) })
    javax.ws.rs.core.Response bind(@PathParam("consumer_uuid") @ApiParam("Consumer UUID") String consumerUuid,@QueryParam("pool")  @ApiParam("Pool ID")  String pool,@QueryParam("product")  @ApiParam("Product array")  List<String> product,@QueryParam("quantity")  @ApiParam("Quantity")  Integer quantity,@QueryParam("email")  @ApiParam("Email address")  String email,@QueryParam("email_locale")  @ApiParam("Email locale")  String emailLocale,@QueryParam("async") @DefaultValue("false")  @ApiParam("Operation Type async or sync")  Boolean async,@QueryParam("entitle_date")  @ApiParam("Entitlement date")  OffsetDateTime entitleDate,@QueryParam("from_pool")  @ApiParam("From pool")  List<String> fromPool);


    /**
     * Checks for the existence of a Consumer.
     *
     * @param consumerUuid The UUID of the Consumer
     * @return Successful Operation.
     * @return Consumer with this UUID could not be found.
     * @return An unexpected exception has occurred
     */
    @HEAD
    @Path("/{consumer_uuid}/exists")
    @Produces({ "application/json" })
    @ApiOperation(value = "", notes = "Checks for the existence of a Consumer.", tags={ "consumer" })
    @ApiResponses(value = { 
        @ApiResponse(code = 204, message = "Successful Operation.", response = Void.class),
        @ApiResponse(code = 404, message = "Consumer with this UUID could not be found.", response = ExceptionMessage.class),
        @ApiResponse(code = 500, message = "An unexpected exception has occurred", response = ExceptionMessage.class) })
    void consumerExists(@PathParam("consumer_uuid") @ApiParam("The UUID of the Consumer") String consumerUuid);


    /**
     * Checks for the existence of a Consumer in bulk. This API return UUIDs of non-existing consumer.
     *
     * @param requestBody Array of Consumer UUIDs
     * @return Consumers exist for all provided UUIDs
     * @return No UUIDs provided
     * @return Non-existing consumer UUIDs
     * @return An unexpected exception has occurred
     */
    @POST
    @Path("/exists")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @ApiOperation(value = "", notes = "Checks for the existence of a Consumer in bulk. This API return UUIDs of non-existing consumer.", tags={ "consumer" })
    @ApiResponses(value = { 
        @ApiResponse(code = 204, message = "Consumers exist for all provided UUIDs", response = Void.class),
        @ApiResponse(code = 400, message = "No UUIDs provided", response = ExceptionMessage.class),
        @ApiResponse(code = 404, message = "Non-existing consumer UUIDs", response = String.class),
        @ApiResponse(code = 500, message = "An unexpected exception has occurred", response = ExceptionMessage.class) })
    javax.ws.rs.core.Response consumerExistsBulk(@Valid Set<String> requestBody);


    /**
     * Creates a Consumer. This method is available to everyone, as we have nothing we can reliably verify in the method signature. Instead we have to figure out what owner this consumer is destined for (due to backward compatability with existing clients which do not specify an owner during registration) and then check the access to the specified owner in the method itself.
     *
     * @param consumerDTO Consumer to be created
     * @param username User name
     * @param owner Owner key
     * @param activationKeys Activation key
     * @param identityCertCreation Boolean flag for identity cert generation.
     * @return Consumer successfully created.
     * @return One or more constraint validation has failed. | Consumer type must be specified or it is of invalid type. | Organization is not specified or does not exists.
     * @return Insufficient permissions.
     * @return Owner with this key could not be found.
     * @return An unexpected exception has occurred
     */
    @POST
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @ApiOperation(value = "", notes = "Creates a Consumer. This method is available to everyone, as we have nothing we can reliably verify in the method signature. Instead we have to figure out what owner this consumer is destined for (due to backward compatability with existing clients which do not specify an owner during registration) and then check the access to the specified owner in the method itself.", tags={ "consumer" })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Consumer successfully created.", response = ConsumerDTO.class),
        @ApiResponse(code = 400, message = "One or more constraint validation has failed. | Consumer type must be specified or it is of invalid type. | Organization is not specified or does not exists.", response = ExceptionMessage.class),
        @ApiResponse(code = 403, message = "Insufficient permissions.", response = ExceptionMessage.class),
        @ApiResponse(code = 404, message = "Owner with this key could not be found.", response = ExceptionMessage.class),
        @ApiResponse(code = 500, message = "An unexpected exception has occurred", response = ExceptionMessage.class) })
    ConsumerDTO createConsumer(@Valid @NotNull ConsumerDTO consumerDTO,@QueryParam("username")  @ApiParam("User name")  String username,@QueryParam("owner")  @ApiParam("Owner key")  String owner,@QueryParam("activation_keys")  @ApiParam("Activation key")  String activationKeys,@QueryParam("identity_cert_creation") @DefaultValue("true")  @ApiParam("Boolean flag for identity cert generation.")  Boolean identityCertCreation);


    /**
     * Removes a Consumer
     *
     * @param consumerUuid The UUID of the Consumer
     * @return A successful operation.
     * @return Consumer could not be deleted due to insufficient permissions.
     * @return Consumer with this UUID is already deleted.
     * @return An unexpected exception has occurred
     */
    @DELETE
    @Path("/{consumer_uuid}")
    @Produces({ "application/json" })
    @ApiOperation(value = "", notes = "Removes a Consumer", tags={ "consumer" })
    @ApiResponses(value = { 
        @ApiResponse(code = 204, message = "A successful operation.", response = Void.class),
        @ApiResponse(code = 403, message = "Consumer could not be deleted due to insufficient permissions.", response = ExceptionMessage.class),
        @ApiResponse(code = 410, message = "Consumer with this UUID is already deleted.", response = ExceptionMessage.class),
        @ApiResponse(code = 500, message = "An unexpected exception has occurred", response = ExceptionMessage.class) })
    void deleteConsumer(@PathParam("consumer_uuid") @ApiParam("The UUID of the Consumer") String consumerUuid);


    /**
     * Deletes a list of Content Overrides
     *
     * @param consumerUuid The ID of the Consumer
     * @param contentOverrideDTO The list of the content overrides
     * @return List of the deleted content overrides of the Consumer
     * @return Insufficient permissions.
     * @return Consumer with this UUID could not be found.
     * @return An unexpected exception has occurred
     */
    @DELETE
    @Path("/{consumer_uuid}/content_overrides")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @ApiOperation(value = "", notes = "Deletes a list of Content Overrides", tags={ "consumer" })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "List of the deleted content overrides of the Consumer", response = ContentOverrideDTO.class, responseContainer = "List"),
        @ApiResponse(code = 403, message = "Insufficient permissions.", response = ExceptionMessage.class, responseContainer = "List"),
        @ApiResponse(code = 404, message = "Consumer with this UUID could not be found.", response = ExceptionMessage.class, responseContainer = "List"),
        @ApiResponse(code = 500, message = "An unexpected exception has occurred", response = ExceptionMessage.class, responseContainer = "List") })
    java.util.stream.Stream<ContentOverrideDTO> deleteConsumerContentOverrides(@PathParam("consumer_uuid") @ApiParam("The ID of the Consumer") String consumerUuid,@Valid @NotNull List<ContentOverrideDTO> contentOverrideDTO);


    /**
     * Downloads an asynchronously generated consumer export file (manifest).
     *
     * @param consumerUuid The UUID of the consumer
     * @param exportId export ID
     * @return A successful operation
     * @return Could not validate export against specifed consumer
     * @return Consumer with this UUID could not be found. | Unable to find specified manifest by this ID
     * @return An unexpected exception has occurred
     */
    @GET
    @Path("/{consumer_uuid}/export/{export_id}")
    @Produces({ "application/zip", "application/json" })
    @ApiOperation(value = "", notes = "Downloads an asynchronously generated consumer export file (manifest).", tags={ "consumer" })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "A successful operation", response = File.class),
        @ApiResponse(code = 400, message = "Could not validate export against specifed consumer", response = ExceptionMessage.class),
        @ApiResponse(code = 404, message = "Consumer with this UUID could not be found. | Unable to find specified manifest by this ID", response = ExceptionMessage.class),
        @ApiResponse(code = 500, message = "An unexpected exception has occurred", response = ExceptionMessage.class) })
    File downloadExistingExport(@PathParam("consumer_uuid") @ApiParam("The UUID of the consumer") String consumerUuid,@PathParam("export_id") @ApiParam("export ID") String exportId);


    /**
     * Retrieves a list of Pools and quantities that would be the result of an auto-bind. This is a dry run of an autobind. It allows the client to see what would be the result of an autobind without executing it. It can only do this for the prevously established list of installed products for the consumer If a service level is included in the request, then that level will override the one stored on the consumer. If no service level is included then the existing one will be used. The Response has a list of PoolQuantity objects
     *
     * @param consumerUuid Consumer UUID
     * @param serviceLevel Service level
     * @return List of pool quantities along with pool.
     * @return Either owner ID is null or Owner could not be found. Organization has auto-attach disabled which could be due to content access settings.
     * @return Consumer with this UUID could not be found.
     * @return An unexpected exception has occurred
     */
    @GET
    @Path("/{consumer_uuid}/entitlements/dry-run")
    @Produces({ "application/json" })
    @ApiOperation(value = "", notes = "Retrieves a list of Pools and quantities that would be the result of an auto-bind. This is a dry run of an autobind. It allows the client to see what would be the result of an autobind without executing it. It can only do this for the prevously established list of installed products for the consumer If a service level is included in the request, then that level will override the one stored on the consumer. If no service level is included then the existing one will be used. The Response has a list of PoolQuantity objects", tags={ "consumer" })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "List of pool quantities along with pool.", response = PoolQuantityDTO.class, responseContainer = "List"),
        @ApiResponse(code = 400, message = "Either owner ID is null or Owner could not be found. Organization has auto-attach disabled which could be due to content access settings.", response = ExceptionMessage.class, responseContainer = "List"),
        @ApiResponse(code = 404, message = "Consumer with this UUID could not be found.", response = ExceptionMessage.class, responseContainer = "List"),
        @ApiResponse(code = 500, message = "An unexpected exception has occurred", response = ExceptionMessage.class, responseContainer = "List") })
    List<PoolQuantityDTO> dryBind(@PathParam("consumer_uuid") @ApiParam("Consumer UUID") String consumerUuid,@QueryParam("service_level")  @ApiParam("Service level")  String serviceLevel);


    /**
     * Retrieves a list or a compressed file (depends on accept header) of entitlement certificates for the consumer.
     *
     * @param consumerUuid The UUID of the consumer to retrieve guest
     * @param serials Certificate serials
     * @return A successful operation
     * @return Consumer is null or does not have a defined type ID or Consumer is not associated with a valid type.
     * @return Consumer with this UUID could not be found.
     * @return Concurrent requests persisting the same content access data payload caused a database constraint violation. If you receive this status code, retry the request. 
     * @return An unexpected exception has occurred
     */
    @GET
    @Path("/{consumer_uuid}/certificates")
    @Produces({ "text/plain", "application/zip", "application/json" })
    @ApiOperation(value = "", notes = "Retrieves a list or a compressed file (depends on accept header) of entitlement certificates for the consumer.", tags={ "consumer" })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "A successful operation", response = Object.class),
        @ApiResponse(code = 400, message = "Consumer is null or does not have a defined type ID or Consumer is not associated with a valid type.", response = ExceptionMessage.class),
        @ApiResponse(code = 404, message = "Consumer with this UUID could not be found.", response = ExceptionMessage.class),
        @ApiResponse(code = 429, message = "Concurrent requests persisting the same content access data payload caused a database constraint violation. If you receive this status code, retry the request. ", response = ExceptionMessage.class),
        @ApiResponse(code = 500, message = "An unexpected exception has occurred", response = ExceptionMessage.class) })
    Object exportCertificates(@PathParam("consumer_uuid") @ApiParam("The UUID of the consumer to retrieve guest") String consumerUuid,@QueryParam("serials")  @ApiParam("Certificate serials")  String serials);


    /**
     * Retrieves a Compressed File representation of a Consumer (manifest).
     *
     * @param consumerUuid The UUID of the target consumer.
     * @param cdnLabel The label of the target CDN.
     * @param webappPrefix The URL pointing to the manifest&#39;s originating web application.
     * @param apiUrl The URL pointing to the manifest&#39;s originating candlepin API.
     * @return A successful operation
     * @return Consumer is null or does not have a defined type ID or Consumer is not associated with a valid type
     * @return Unit cannot be exported. | A manifest cannot be made for units this type. | A CDN with this label does not exist on this system.
     * @return Consumer with this UUID could not be found.
     * @return An unexpected exception has occurred
     */
    @GET
    @Path("/{consumer_uuid}/export")
    @Produces({ "application/zip", "application/json" })
    @ApiOperation(value = "", notes = "Retrieves a Compressed File representation of a Consumer (manifest).", tags={ "consumer" })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "A successful operation", response = File.class),
        @ApiResponse(code = 400, message = "Consumer is null or does not have a defined type ID or Consumer is not associated with a valid type", response = ExceptionMessage.class),
        @ApiResponse(code = 403, message = "Unit cannot be exported. | A manifest cannot be made for units this type. | A CDN with this label does not exist on this system.", response = ExceptionMessage.class),
        @ApiResponse(code = 404, message = "Consumer with this UUID could not be found.", response = ExceptionMessage.class),
        @ApiResponse(code = 500, message = "An unexpected exception has occurred", response = ExceptionMessage.class) })
    File exportData(@PathParam("consumer_uuid") @ApiParam("The UUID of the target consumer.") String consumerUuid,@QueryParam("cdn_label")  @ApiParam("The label of the target CDN.")  String cdnLabel,@QueryParam("webapp_prefix")  @ApiParam("The URL pointing to the manifest&#39;s originating web application.")  String webappPrefix,@QueryParam("api_url")  @ApiParam("The URL pointing to the manifest&#39;s originating candlepin API.")  String apiUrl);


    /**
     * Initiates an async generation of a Compressed File representation of a Consumer (manifest). The response will contain the id of the job from which its result data will contain the href to download the generated file.
     *
     * @param consumerUuid The UUID of the target consumer.
     * @param cdnLabel The lable of the target CDN.
     * @param webappPrefix The URL pointing to the manifest&#39;s originating web application.
     * @param apiUrl The URL pointing to the manifest&#39;s originating candlepin API.
     * @return A successful operation
     * @return Consumer is null or does not have a defined type ID or Consumer is not associated with a valid type
     * @return Unit cannot be exported. | A manifest cannot be made for units this type. | A CDN with this label does not exist on this system.
     * @return Consumer with this UUID could not be found.
     * @return An unexpected exception has occurred
     */
    @GET
    @Path("/{consumer_uuid}/export/async")
    @Produces({ "application/json" })
    @ApiOperation(value = "", notes = "Initiates an async generation of a Compressed File representation of a Consumer (manifest). The response will contain the id of the job from which its result data will contain the href to download the generated file.", tags={ "consumer" })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "A successful operation", response = AsyncJobStatusDTO.class),
        @ApiResponse(code = 400, message = "Consumer is null or does not have a defined type ID or Consumer is not associated with a valid type", response = ExceptionMessage.class),
        @ApiResponse(code = 403, message = "Unit cannot be exported. | A manifest cannot be made for units this type. | A CDN with this label does not exist on this system.", response = ExceptionMessage.class),
        @ApiResponse(code = 404, message = "Consumer with this UUID could not be found.", response = ExceptionMessage.class),
        @ApiResponse(code = 500, message = "An unexpected exception has occurred", response = ExceptionMessage.class) })
    AsyncJobStatusDTO exportDataAsync(@PathParam("consumer_uuid") @ApiParam("The UUID of the target consumer.") String consumerUuid,@QueryParam("cdn_label")  @ApiParam("The lable of the target CDN.")  String cdnLabel,@QueryParam("webapp_prefix")  @ApiParam("The URL pointing to the manifest&#39;s originating web application.")  String webappPrefix,@QueryParam("api_url")  @ApiParam("The URL pointing to the manifest&#39;s originating candlepin API.")  String apiUrl);


    /**
     * Retrieves the Compliance Status of a Consumer.
     *
     * @param consumerUuid Consumer UUID
     * @param onDate Date to get compliance information for, default is now.
     * @return Returns the Compliance Status of a Consumer.
     * @return Consumer with this UUID could not be found.
     * @return An unexpected exception has occurred
     */
    @GET
    @Path("/{consumer_uuid}/compliance")
    @Produces({ "application/json" })
    @ApiOperation(value = "", notes = "Retrieves the Compliance Status of a Consumer.", tags={ "consumer" })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Returns the Compliance Status of a Consumer.", response = ComplianceStatusDTO.class),
        @ApiResponse(code = 404, message = "Consumer with this UUID could not be found.", response = ExceptionMessage.class),
        @ApiResponse(code = 500, message = "An unexpected exception has occurred", response = ExceptionMessage.class) })
    ComplianceStatusDTO getComplianceStatus(@PathParam("consumer_uuid") @ApiParam("Consumer UUID") String consumerUuid,@QueryParam("on_date")  @ApiParam("Date to get compliance information for, default is now.")  OffsetDateTime onDate);


    /**
     * Retrieves a Compliance Status list for a list of Consumers.
     *
     * @param uuid Consumers UUIDs
     * @return Returns a map of consumer UUIDs & compliance status
     * @return Consumer does not have a defined type ID.
     * @return An unexpected exception has occurred
     */
    @GET
    @Path("/compliance")
    @Produces({ "application/json" })
    @ApiOperation(value = "", notes = "Retrieves a Compliance Status list for a list of Consumers.", tags={ "consumer" })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Returns a map of consumer UUIDs & compliance status", response = ComplianceStatusDTO.class, responseContainer = "Map"),
        @ApiResponse(code = 400, message = "Consumer does not have a defined type ID.", response = ExceptionMessage.class, responseContainer = "Map"),
        @ApiResponse(code = 500, message = "An unexpected exception has occurred", response = ExceptionMessage.class, responseContainer = "Map") })
    Map<String, ComplianceStatusDTO> getComplianceStatusList(@QueryParam("uuid")  @ApiParam("Consumers UUIDs")  List<String> uuid);


    /**
     * Retrieves a single Consumer
     *
     * @param consumerUuid Consumer UUID
     * @return Returns single consumer.
     * @return Consumer with this UUID could not be found.
     * @return An unexpected exception has occurred
     */
    @GET
    @Path("/{consumer_uuid}")
    @Produces({ "application/json" })
    @ApiOperation(value = "", notes = "Retrieves a single Consumer", tags={ "consumer" })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Returns single consumer.", response = ConsumerDTO.class),
        @ApiResponse(code = 404, message = "Consumer with this UUID could not be found.", response = ExceptionMessage.class),
        @ApiResponse(code = 500, message = "An unexpected exception has occurred", response = ExceptionMessage.class) })
    ConsumerDTO getConsumer(@PathParam("consumer_uuid") @ApiParam("Consumer UUID") String consumerUuid);


    /**
     * Retrieves the body of the Content Access Certificate for the Consumer
     *
     * @param consumerUuid The UUID of the consumer
     * @param ifModifiedSince Modified date. Accepted format EEE, dd MMM yyyy HH:mm:ss z
     * @return A successful operation.
     * @return Not modified since date supplied.
     * @return Unable to parse modified date. Accepted format \"EEE, dd MMM yyyy HH:mm:ss z\" | Cannot retrieve content access certificate.
     * @return Consumer with this UUID could not be found.
     * @return Concurrent requests persisting the same content access data payload caused a database constraint violation. If you receive this status code, retry the request. 
     * @return An unexpected exception has occurred
     */
    @GET
    @Path("/{consumer_uuid}/accessible_content")
    @Produces({ "text/plain", "application/json" })
    @ApiOperation(value = "", notes = "Retrieves the body of the Content Access Certificate for the Consumer", tags={ "consumer" })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "A successful operation.", response = String.class),
        @ApiResponse(code = 304, message = "Not modified since date supplied.", response = ExceptionMessage.class),
        @ApiResponse(code = 400, message = "Unable to parse modified date. Accepted format \"EEE, dd MMM yyyy HH:mm:ss z\" | Cannot retrieve content access certificate.", response = ExceptionMessage.class),
        @ApiResponse(code = 404, message = "Consumer with this UUID could not be found.", response = ExceptionMessage.class),
        @ApiResponse(code = 429, message = "Concurrent requests persisting the same content access data payload caused a database constraint violation. If you receive this status code, retry the request. ", response = ExceptionMessage.class),
        @ApiResponse(code = 500, message = "An unexpected exception has occurred", response = ExceptionMessage.class) })
    javax.ws.rs.core.Response getContentAccessBody(@PathParam("consumer_uuid") @ApiParam("The UUID of the consumer") String consumerUuid,@HeaderParam("If-Modified-Since")   @ApiParam("Modified date. Accepted format EEE, dd MMM yyyy HH:mm:ss z") String ifModifiedSince);


    /**
     * Retrieves content access of a Consumer
     *
     * @param consumerUuid Consumer UUID
     * @return Returns content access of a Consumer.
     * @return Consumer with this UUID could not be found.
     * @return An unexpected exception has occurred
     */
    @GET
    @Path("/{consumer_uuid}/content_access")
    @Produces({ "application/json" })
    @ApiOperation(value = "", notes = "Retrieves content access of a Consumer", tags={ "consumer" })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Returns content access of a Consumer.", response = ContentAccessDTO.class),
        @ApiResponse(code = 404, message = "Consumer with this UUID could not be found.", response = ExceptionMessage.class),
        @ApiResponse(code = 500, message = "An unexpected exception has occurred", response = ExceptionMessage.class) })
    ContentAccessDTO getContentAccessForConsumer(@PathParam("consumer_uuid") @ApiParam("Consumer UUID") String consumerUuid);


    /**
     * Retrieves a list of Certiticate Serials for the given consumer. This is a small subset of data clients can use to determine which certificates they need to update/fetch.
     *
     * @param consumerUuid Consumer UUID
     * @return A list of certificate serials
     * @return Cannot retrieve content access certificate
     * @return Consumer with this UUID could not be found.
     * @return An unexpected exception has occurred
     */
    @GET
    @Path("/{consumer_uuid}/certificates/serials")
    @Produces({ "application/json" })
    @ApiOperation(value = "", notes = "Retrieves a list of Certiticate Serials for the given consumer. This is a small subset of data clients can use to determine which certificates they need to update/fetch.", tags={ "consumer" })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "A list of certificate serials", response = CertificateSerialDTO.class, responseContainer = "List"),
        @ApiResponse(code = 400, message = "Cannot retrieve content access certificate", response = ExceptionMessage.class, responseContainer = "List"),
        @ApiResponse(code = 404, message = "Consumer with this UUID could not be found.", response = ExceptionMessage.class, responseContainer = "List"),
        @ApiResponse(code = 500, message = "An unexpected exception has occurred", response = ExceptionMessage.class, responseContainer = "List") })
    List<CertificateSerialDTO> getEntitlementCertificateSerials(@PathParam("consumer_uuid") @ApiParam("Consumer UUID") String consumerUuid);


    /**
     * Retrieves a list of Guest Consumers of a Consumer
     *
     * @param consumerUuid Consumer UUID
     * @return Returns the list of consumers
     * @return The system with this UUID is a virtual guest. It does not have guests.
     * @return Consumer with this UUID could not be found.
     * @return An unexpected exception has occurred
     */
    @GET
    @Path("/{consumer_uuid}/guests")
    @Produces({ "application/json" })
    @ApiOperation(value = "", notes = "Retrieves a list of Guest Consumers of a Consumer", tags={ "consumer" })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Returns the list of consumers", response = ConsumerDTOArrayElement.class, responseContainer = "List"),
        @ApiResponse(code = 400, message = "The system with this UUID is a virtual guest. It does not have guests.", response = ExceptionMessage.class, responseContainer = "List"),
        @ApiResponse(code = 404, message = "Consumer with this UUID could not be found.", response = ExceptionMessage.class, responseContainer = "List"),
        @ApiResponse(code = 500, message = "An unexpected exception has occurred", response = ExceptionMessage.class, responseContainer = "List") })
    List<ConsumerDTOArrayElement> getGuests(@PathParam("consumer_uuid") @ApiParam("Consumer UUID") String consumerUuid);


    /**
     * Retrieves a Host Consumer of a Consumer
     *
     * @param consumerUuid Consumer UUID
     * @return Returns Host Consumer of a Consumer
     * @return The system with this UUID is not a virtual guest.
     * @return Consumer with this UUID could not be found.
     * @return An unexpected exception has occurred
     */
    @GET
    @Path("/{consumer_uuid}/host")
    @Produces({ "application/json" })
    @ApiOperation(value = "", notes = "Retrieves a Host Consumer of a Consumer", tags={ "consumer" })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Returns Host Consumer of a Consumer", response = ConsumerDTO.class),
        @ApiResponse(code = 400, message = "The system with this UUID is not a virtual guest.", response = ExceptionMessage.class),
        @ApiResponse(code = 404, message = "Consumer with this UUID could not be found.", response = ExceptionMessage.class),
        @ApiResponse(code = 500, message = "An unexpected exception has occurred", response = ExceptionMessage.class) })
    ConsumerDTO getHost(@PathParam("consumer_uuid") @ApiParam("Consumer UUID") String consumerUuid);


    /**
     * Retrieves the Owner associated to a Consumer
     *
     * @param consumerUuid Consumer UUID
     * @return A successful operation
     * @return OwnerId is null or owner not found for this ID.
     * @return Consumer with this UUID could not be found.
     * @return An unexpected exception has occurred
     */
    @GET
    @Path("/{consumer_uuid}/owner")
    @Produces({ "application/json" })
    @ApiOperation(value = "", notes = "Retrieves the Owner associated to a Consumer", tags={ "consumer" })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "A successful operation", response = OwnerDTO.class),
        @ApiResponse(code = 400, message = "OwnerId is null or owner not found for this ID.", response = ExceptionMessage.class),
        @ApiResponse(code = 404, message = "Consumer with this UUID could not be found.", response = ExceptionMessage.class),
        @ApiResponse(code = 500, message = "An unexpected exception has occurred", response = ExceptionMessage.class) })
    OwnerDTO getOwnerByConsumerUuid(@PathParam("consumer_uuid") @ApiParam("Consumer UUID") String consumerUuid);


    /**
     * Retrieves the release of a consumer
     *
     * @param consumerUuid Consumer UUID
     * @return Returns the release of a consumer
     * @return Consumer with this UUID could not be found
     * @return An unexpected exception has occurred
     */
    @GET
    @Path("/{consumer_uuid}/release")
    @Produces({ "application/json" })
    @ApiOperation(value = "", notes = "Retrieves the release of a consumer", tags={ "consumer" })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Returns the release of a consumer", response = ReleaseVerDTO.class),
        @ApiResponse(code = 404, message = "Consumer with this UUID could not be found", response = ExceptionMessage.class),
        @ApiResponse(code = 500, message = "An unexpected exception has occurred", response = ExceptionMessage.class) })
    ReleaseVerDTO getRelease(@PathParam("consumer_uuid") @ApiParam("Consumer UUID") String consumerUuid);


    /**
     * Retrieves the System Purpose Compliance Status of a Consumer.
     *
     * @param consumerUuid Consumer UUID
     * @param onDate Date to get compliance information for, default is now.
     * @return Returns the System Purpose Compliance Status of a Consumer.
     * @return Consumer with this UUID could not be found.
     * @return An unexpected exception has occurred
     */
    @GET
    @Path("/{consumer_uuid}/purpose_compliance")
    @Produces({ "application/json" })
    @ApiOperation(value = "", notes = "Retrieves the System Purpose Compliance Status of a Consumer.", tags={ "consumer" })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Returns the System Purpose Compliance Status of a Consumer.", response = SystemPurposeComplianceStatusDTO.class),
        @ApiResponse(code = 404, message = "Consumer with this UUID could not be found.", response = ExceptionMessage.class),
        @ApiResponse(code = 500, message = "An unexpected exception has occurred", response = ExceptionMessage.class) })
    SystemPurposeComplianceStatusDTO getSystemPurposeComplianceStatus(@PathParam("consumer_uuid") @ApiParam("Consumer UUID") String consumerUuid,@QueryParam("on_date")  @ApiParam("Date to get compliance information for, default is now.")  OffsetDateTime onDate);


    /**
     * Retrieves list of Content Overrides
     *
     * @param consumerUuid The ID of the consumer
     * @return List of content overrides of the Consumer.
     * @return Insufficient permissions.
     * @return Consumer with this UUID could not be found.
     * @return An unexpected exception has occurred
     */
    @GET
    @Path("/{consumer_uuid}/content_overrides")
    @Produces({ "application/json" })
    @ApiOperation(value = "", notes = "Retrieves list of Content Overrides", tags={ "consumer" })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "List of content overrides of the Consumer.", response = ContentOverrideDTO.class, responseContainer = "List"),
        @ApiResponse(code = 403, message = "Insufficient permissions.", response = ExceptionMessage.class, responseContainer = "List"),
        @ApiResponse(code = 404, message = "Consumer with this UUID could not be found.", response = ExceptionMessage.class, responseContainer = "List"),
        @ApiResponse(code = 500, message = "An unexpected exception has occurred", response = ExceptionMessage.class, responseContainer = "List") })
    java.util.stream.Stream<ContentOverrideDTO> listConsumerContentOverrides(@PathParam("consumer_uuid") @ApiParam("The ID of the consumer") String consumerUuid);


    /**
     * Retrieves a list of Entitlements. This endpoint supports paging with query parameters.
     *
     * @param consumerUuid Consumer UUID
     * @param product ID of a Product
     * @param regen Boolean flag to regenerate entitlements
     * @param attribute Attribute filters
     * @param page Page index to return
     * @param perPage Number of items to return per page
     * @param order Direction of ordering
     * @param sortBy Property to use for ordering
     * @return A list of entitlements
     * @return Product with this ID could not be found.
     * @return Consumer with this  UUID could not be found.
     * @return An unexpected exception has occurred
     */
    @GET
    @Path("/{consumer_uuid}/entitlements")
    @Produces({ "application/json" })
    @ApiOperation(value = "", notes = "Retrieves a list of Entitlements. This endpoint supports paging with query parameters.", tags={ "consumer" })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "A list of entitlements", response = EntitlementDTO.class, responseContainer = "List"),
        @ApiResponse(code = 400, message = "Product with this ID could not be found.", response = ExceptionMessage.class, responseContainer = "List"),
        @ApiResponse(code = 404, message = "Consumer with this  UUID could not be found.", response = ExceptionMessage.class, responseContainer = "List"),
        @ApiResponse(code = 500, message = "An unexpected exception has occurred", response = ExceptionMessage.class, responseContainer = "List") })
    List<EntitlementDTO> listEntitlements(@PathParam("consumer_uuid") @ApiParam("Consumer UUID") String consumerUuid,@QueryParam("product")  @ApiParam("ID of a Product")  String product,@QueryParam("regen") @DefaultValue("true")  @ApiParam("Boolean flag to regenerate entitlements")  Boolean regen,@QueryParam("attribute")  @ApiParam("Attribute filters")  List<String> attribute,@QueryParam("page")  @ApiParam("Page index to return")  Integer page,@QueryParam("per_page")  @ApiParam("Number of items to return per page")  Integer perPage,@QueryParam("order")  @ApiParam("Direction of ordering")  String order,@QueryParam("sort_by")  @ApiParam("Property to use for ordering")  String sortBy);


    /**
     * Regenerates the Entitlement Certificates for a Consumer
     *
     * @param consumerUuid The UUID of the Consumer
     * @param entitlement Entitlement ID
     * @param lazyRegen Lazy regeneration of entitlement certs
     * @param cleanupEntitlements Whether or not to remove unnecessary or unused entitlements for the consumer before regenerating certificates 
     * @return Successful operation
     * @return Consumer does not have a defined type ID. Invalid consumer type.
     * @return Consumer with this UUID could not be found Or Entitlement with this ID could not be found.
     * @return An unexpected exception has occurred
     */
    @PUT
    @Path("/{consumer_uuid}/certificates")
    @Produces({ "application/json" })
    @ApiOperation(value = "", notes = "Regenerates the Entitlement Certificates for a Consumer", tags={ "consumer" })
    @ApiResponses(value = { 
        @ApiResponse(code = 204, message = "Successful operation", response = Void.class),
        @ApiResponse(code = 400, message = "Consumer does not have a defined type ID. Invalid consumer type.", response = ExceptionMessage.class),
        @ApiResponse(code = 404, message = "Consumer with this UUID could not be found Or Entitlement with this ID could not be found.", response = ExceptionMessage.class),
        @ApiResponse(code = 500, message = "An unexpected exception has occurred", response = ExceptionMessage.class) })
    void regenerateEntitlementCertificates(@PathParam("consumer_uuid") @ApiParam("The UUID of the Consumer") String consumerUuid,@QueryParam("entitlement")  @ApiParam("Entitlement ID")  String entitlement,@QueryParam("lazy_regen") @DefaultValue("true")  @ApiParam("Lazy regeneration of entitlement certs")  Boolean lazyRegen,@QueryParam("cleanup_entitlements") @DefaultValue("false")  @ApiParam("Whether or not to remove unnecessary or unused entitlements for the consumer before regenerating certificates ")  Boolean cleanupEntitlements);


    /**
     * Retrieves a single Consumer & regenerate identity certificates
     *
     * @param consumerUuid Consumer UUID
     * @return Returns consumer with regenerated Identity certs.
     * @return Consumer type validation failed or Problem regenerating ID cert for this unit.
     * @return Consumer with this UUID could not be found.
     * @return An unexpected exception has occurred
     */
    @POST
    @Path("/{consumer_uuid}")
    @Produces({ "application/json" })
    @ApiOperation(value = "", notes = "Retrieves a single Consumer & regenerate identity certificates", tags={ "consumer" })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Returns consumer with regenerated Identity certs.", response = ConsumerDTO.class),
        @ApiResponse(code = 400, message = "Consumer type validation failed or Problem regenerating ID cert for this unit.", response = ExceptionMessage.class),
        @ApiResponse(code = 404, message = "Consumer with this UUID could not be found.", response = ExceptionMessage.class),
        @ApiResponse(code = 500, message = "An unexpected exception has occurred", response = ExceptionMessage.class) })
    ConsumerDTO regenerateIdentityCertificates(@PathParam("consumer_uuid") @ApiParam("Consumer UUID") String consumerUuid);


    /**
     * Removes the Deletion Record for a Consumer Allowed for a superadmin. The main use case for this would be if a user accidently deleted a non-RHEL hypervisor, causing it to no longer be auto-detected via virt-who.
     *
     * @param consumerUuid The UUID of the Consumer
     * @return A successful operation.
     * @return Deletion record for hypervisor is not found.
     * @return An unexpected exception has occurred
     */
    @DELETE
    @Path("/{consumer_uuid}/deletionrecord")
    @Produces({ "application/json" })
    @ApiOperation(value = "", notes = "Removes the Deletion Record for a Consumer Allowed for a superadmin. The main use case for this would be if a user accidently deleted a non-RHEL hypervisor, causing it to no longer be auto-detected via virt-who.", tags={ "consumer" })
    @ApiResponses(value = { 
        @ApiResponse(code = 204, message = "A successful operation.", response = Void.class),
        @ApiResponse(code = 404, message = "Deletion record for hypervisor is not found.", response = ExceptionMessage.class),
        @ApiResponse(code = 500, message = "An unexpected exception has occurred", response = ExceptionMessage.class) })
    void removeDeletionRecord(@PathParam("consumer_uuid") @ApiParam("The UUID of the Consumer") String consumerUuid);


    /**
     * Retrieves a list of the Consumers according to search criteria.
     *
     * @param username Username
     * @param type Consumer type
     * @param owner Owner key
     * @param uuid The UUID of consumers
     * @param hypervisorId Hypervisor IDs
     * @param registrationAuthenticationMethod Registration Authentication Method
     * @param fact The consumer facts
     * @param environmentId Environment identifier
     * @param page Page index to return
     * @param perPage Number of items to return per page
     * @param order Direction of ordering
     * @param sortBy Property to use for ordering
     * @return List of consumers
     * @return Must specify at least one search criteria.
     * @return Owner with this key could not be found.
     * @return An unexpected exception has occurred
     */
    @GET
    @Produces({ "application/json" })
    @ApiOperation(value = "", notes = "Retrieves a list of the Consumers according to search criteria.", tags={ "consumer" })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "List of consumers", response = ConsumerDTOArrayElement.class, responseContainer = "List"),
        @ApiResponse(code = 400, message = "Must specify at least one search criteria.", response = ExceptionMessage.class, responseContainer = "List"),
        @ApiResponse(code = 404, message = "Owner with this key could not be found.", response = ExceptionMessage.class, responseContainer = "List"),
        @ApiResponse(code = 500, message = "An unexpected exception has occurred", response = ExceptionMessage.class, responseContainer = "List") })
    java.util.stream.Stream<ConsumerDTOArrayElement> searchConsumers(@QueryParam("username")  @ApiParam("Username")  String username,@QueryParam("type")  @ApiParam("Consumer type")  Set<String> type,@QueryParam("owner")  @ApiParam("Owner key")  String owner,@QueryParam("uuid")  @ApiParam("The UUID of consumers")  List<String> uuid,@QueryParam("hypervisor_id")  @ApiParam("Hypervisor IDs")  List<String> hypervisorId,@QueryParam("registration_authentication_method")  @ApiParam("Registration Authentication Method")  String registrationAuthenticationMethod,@QueryParam("fact")  @ApiParam("The consumer facts")  List<String> fact,@QueryParam("environment_id")  @ApiParam("Environment identifier")  String environmentId,@QueryParam("page")  @ApiParam("Page index to return")  Integer page,@QueryParam("per_page")  @ApiParam("Number of items to return per page")  Integer perPage,@QueryParam("order")  @ApiParam("Direction of ordering")  String order,@QueryParam("sort_by")  @ApiParam("Property to use for ordering")  String sortBy);


    /**
     * Unbinds all Entitlements for a Consumer. The result contains the total number of entitlements unbound.
     *
     * @param consumerUuid The UUID of the Consumer
     * @return The total number of revoked entitlements
     * @return Consumer with this UUID could not be found.
     * @return An unexpected exception has occurred
     */
    @DELETE
    @Path("/{consumer_uuid}/entitlements")
    @Produces({ "application/json" })
    @ApiOperation(value = "", notes = "Unbinds all Entitlements for a Consumer. The result contains the total number of entitlements unbound.", tags={ "consumer" })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "The total number of revoked entitlements", response = DeleteResult.class),
        @ApiResponse(code = 404, message = "Consumer with this UUID could not be found.", response = ExceptionMessage.class),
        @ApiResponse(code = 500, message = "An unexpected exception has occurred", response = ExceptionMessage.class) })
    DeleteResult unbindAll(@PathParam("consumer_uuid") @ApiParam("The UUID of the Consumer") String consumerUuid);


    /**
     * Removes an Entitlement from a Consumer By the Entitlement ID
     *
     * @param consumerUuid The UUID of the Consumer
     * @param dbid Entitlement ID
     * @return Successful operation
     * @return Consumer with this UUId could not be found. OR Entitlement ID could not be found.
     * @return An unexpected exception has occurred
     */
    @DELETE
    @Path("/{consumer_uuid}/entitlements/{dbid}")
    @Produces({ "application/json" })
    @ApiOperation(value = "", notes = "Removes an Entitlement from a Consumer By the Entitlement ID", tags={ "consumer" })
    @ApiResponses(value = { 
        @ApiResponse(code = 204, message = "Successful operation", response = Void.class),
        @ApiResponse(code = 404, message = "Consumer with this UUId could not be found. OR Entitlement ID could not be found.", response = ExceptionMessage.class),
        @ApiResponse(code = 500, message = "An unexpected exception has occurred", response = ExceptionMessage.class) })
    void unbindByEntitlementId(@PathParam("consumer_uuid") @ApiParam("The UUID of the Consumer") String consumerUuid,@PathParam("dbid") @ApiParam("Entitlement ID") String dbid);


    /**
     * Removes all Entitlements from a Consumer by Pool Id
     *
     * @param consumerUuid The ID of the Consumer
     * @param poolId The ID of the Consumer
     * @return Successful operation
     * @return No entitlements found for this consumer & with this pool ID
     * @return An unexpected exception has occurred
     */
    @DELETE
    @Path("/{consumer_uuid}/entitlements/pool/{pool_id}")
    @Produces({ "application/json" })
    @ApiOperation(value = "", notes = "Removes all Entitlements from a Consumer by Pool Id", tags={ "consumer" })
    @ApiResponses(value = { 
        @ApiResponse(code = 204, message = "Successful operation", response = Void.class),
        @ApiResponse(code = 404, message = "No entitlements found for this consumer & with this pool ID", response = ExceptionMessage.class),
        @ApiResponse(code = 500, message = "An unexpected exception has occurred", response = ExceptionMessage.class) })
    void unbindByPool(@PathParam("consumer_uuid") @ApiParam("The ID of the Consumer") String consumerUuid,@PathParam("pool_id") @ApiParam("The ID of the Consumer") String poolId);


    /**
     * Removes an Entitlement from a Consumer By the Certificate Serial
     *
     * @param consumerUuid The UUID of the Consumer
     * @param serial certificate serial
     * @return A successful operation.
     * @return Consumer with this UUID could not be found or Entitlement Certificate with serial number could not be found
     * @return An unexpected exception has occurred
     */
    @DELETE
    @Path("/{consumer_uuid}/certificates/{serial}")
    @Produces({ "application/json" })
    @ApiOperation(value = "", notes = "Removes an Entitlement from a Consumer By the Certificate Serial", tags={ "consumer" })
    @ApiResponses(value = { 
        @ApiResponse(code = 204, message = "A successful operation.", response = Void.class),
        @ApiResponse(code = 404, message = "Consumer with this UUID could not be found or Entitlement Certificate with serial number could not be found", response = ExceptionMessage.class),
        @ApiResponse(code = 500, message = "An unexpected exception has occurred", response = ExceptionMessage.class) })
    void unbindBySerial(@PathParam("consumer_uuid") @ApiParam("The UUID of the Consumer") String consumerUuid,@PathParam("serial") @ApiParam("certificate serial") Long serial);


    /**
     * Updates a Consumer
     *
     * @param consumerUuid The UUID of the Consumer
     * @param consumerDTO Consumer to be updated
     * @return Successful operation
     * @return One or more constraint validation has failed. Problem updating consumer.
     * @return Consumer with this UUID could not be found.
     * @return An unexpected exception has occurred
     */
    @PUT
    @Path("/{consumer_uuid}")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @ApiOperation(value = "", notes = "Updates a Consumer", tags={ "consumer" })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Successful operation", response = Void.class),
        @ApiResponse(code = 400, message = "One or more constraint validation has failed. Problem updating consumer.", response = ExceptionMessage.class),
        @ApiResponse(code = 404, message = "Consumer with this UUID could not be found.", response = ExceptionMessage.class),
        @ApiResponse(code = 500, message = "An unexpected exception has occurred", response = ExceptionMessage.class) })
    void updateConsumer(@PathParam("consumer_uuid") @ApiParam("The UUID of the Consumer") String consumerUuid,@Valid @NotNull ConsumerDTO consumerDTO);

}
