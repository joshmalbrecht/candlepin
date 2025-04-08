package org.candlepin.api.v1;

import org.candlepin.dto.api.server.v1.ActivationKeyDTO;
import org.candlepin.dto.api.server.v1.AsyncJobStatusDTO;
import org.candlepin.dto.api.server.v1.ClaimantOwner;
import org.candlepin.dto.api.server.v1.ConsumerDTOArrayElement;
import org.candlepin.dto.api.server.v1.ContentAccessDTO;
import org.candlepin.dto.api.server.v1.EntitlementDTO;
import org.candlepin.dto.api.server.v1.EnvironmentDTO;
import org.candlepin.dto.api.server.v1.ExceptionMessage;
import java.io.File;
import org.candlepin.dto.api.server.v1.ImportRecordDTO;
import java.time.OffsetDateTime;
import org.candlepin.dto.api.server.v1.OwnerDTO;
import org.candlepin.dto.api.server.v1.OwnerInfo;
import org.candlepin.dto.api.server.v1.PoolDTO;
import java.util.Set;
import org.candlepin.dto.api.server.v1.SetConsumerEnvironmentsDTO;
import org.candlepin.dto.api.server.v1.SubscriptionDTO;
import org.candlepin.dto.api.server.v1.SystemPurposeAttributesDTO;
import org.candlepin.dto.api.server.v1.UeberCertificateDTO;
import org.candlepin.dto.api.server.v1.UpstreamConsumerDTOArrayElement;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

import io.swagger.annotations.*;

import org.jboss.resteasy.plugins.providers.multipart.MultipartInput;
import java.util.Map;
import java.util.List;
import javax.validation.constraints.*;
import javax.validation.Valid;

@Path("/owners")
@Api(description = "the Owner API")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaJAXRSSpecServerCodegen", date = "2025-03-28T14:05:44.436523329-04:00[America/Detroit]", comments = "Generator version: 7.12.0")
public interface OwnerApi {

    /**
     * Claims consumers of an anonymous owner and migrates them to the claimant owner
     *
     * @param anonymousOwnerKey The key of the owner
     * @param claimantOwner Claimant owner
     * @return A job to migrate consumers has been scheduled.
     * @return Owner key is null or empty
     * @return Owner key could not be found using the provided key
     * @return An unexpected exception has occurred
     */
    @PUT
    @Path("/{anonymous_owner_key}/claim")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @ApiOperation(value = "", notes = "Claims consumers of an anonymous owner and migrates them to the claimant owner", tags={ "owner" })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "A job to migrate consumers has been scheduled.", response = AsyncJobStatusDTO.class),
        @ApiResponse(code = 400, message = "Owner key is null or empty", response = ExceptionMessage.class),
        @ApiResponse(code = 404, message = "Owner key could not be found using the provided key", response = ExceptionMessage.class),
        @ApiResponse(code = 500, message = "An unexpected exception has occurred", response = ExceptionMessage.class) })
    AsyncJobStatusDTO claim(@PathParam("anonymous_owner_key") @ApiParam("The key of the owner") String anonymousOwnerKey,@Valid @NotNull ClaimantOwner claimantOwner);


    /**
     * Retrieves a count of consumers for the owner
     *
     * @param ownerKey The key of the owner
     * @param username The username of the consumer
     * @param type The consumer type
     * @param uuid The UUID of consumers
     * @param hypervisorId The hypervisor IDs
     * @return Consumer counts successfully retrieved
     * @return Owner key is null or empty
     * @return An owner could not be found using the provided key
     * @return An unexpected exception has occurred
     */
    @GET
    @Path("/{owner_key}/consumers/count")
    @Produces({ "application/json" })
    @ApiOperation(value = "", notes = "Retrieves a count of consumers for the owner", tags={ "owner" })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Consumer counts successfully retrieved", response = Integer.class),
        @ApiResponse(code = 400, message = "Owner key is null or empty", response = ExceptionMessage.class),
        @ApiResponse(code = 404, message = "An owner could not be found using the provided key", response = ExceptionMessage.class),
        @ApiResponse(code = 500, message = "An unexpected exception has occurred", response = ExceptionMessage.class) })
    Integer countConsumers(@PathParam("owner_key") @ApiParam("The key of the owner") String ownerKey,@QueryParam("username")  @ApiParam("The username of the consumer")  String username,@QueryParam("type")  @ApiParam("The consumer type")  Set<String> type,@QueryParam("uuid")  @ApiParam("The UUID of consumers")  List<String> uuid,@QueryParam("hypervisor_id")  @ApiParam("The hypervisor IDs")  List<String> hypervisorId);


    /**
     * Creates an activation key for the owner
     *
     * @param ownerKey The key of the owner
     * @param activationKeyDTO Activation key to be created
     * @return Activation key for the owner successfully created
     * @return Invalid inputs, activation key cannot be created. Reasons could be, The activation key name is already in use for owner or the activation key name must be alphanumeric or include the characters - or _ or the name for activation key is not provided or owner key is not provided 
     * @return An owner could not be found using the provided key
     * @return An unexpected exception has occurred
     */
    @POST
    @Path("/{owner_key}/activation_keys")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @ApiOperation(value = "", notes = "Creates an activation key for the owner", tags={ "owner" })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Activation key for the owner successfully created", response = ActivationKeyDTO.class),
        @ApiResponse(code = 400, message = "Invalid inputs, activation key cannot be created. Reasons could be, The activation key name is already in use for owner or the activation key name must be alphanumeric or include the characters - or _ or the name for activation key is not provided or owner key is not provided ", response = ExceptionMessage.class),
        @ApiResponse(code = 404, message = "An owner could not be found using the provided key", response = ExceptionMessage.class),
        @ApiResponse(code = 500, message = "An unexpected exception has occurred", response = ExceptionMessage.class) })
    ActivationKeyDTO createActivationKey(@PathParam("owner_key") @ApiParam("The key of the owner") String ownerKey,@Valid @NotNull ActivationKeyDTO activationKeyDTO);


    /**
     * Creates an environment for the owner
     *
     * @param ownerKey The key of the owner
     * @param environmentDTO Environment to be created
     * @return Environment for the owner successfully created
     * @return An owner could not be found using the provided key
     * @return An unexpected exception has occurred
     */
    @POST
    @Path("/{owner_key}/environments")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @ApiOperation(value = "", notes = "Creates an environment for the owner", tags={ "owner" })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Environment for the owner successfully created", response = EnvironmentDTO.class),
        @ApiResponse(code = 404, message = "An owner could not be found using the provided key", response = ExceptionMessage.class),
        @ApiResponse(code = 500, message = "An unexpected exception has occurred", response = ExceptionMessage.class) })
    EnvironmentDTO createEnvironment(@PathParam("owner_key") @ApiParam("The key of the owner") String ownerKey,@Valid @NotNull EnvironmentDTO environmentDTO);


    /**
     * Creates an owner
     *
     * @param ownerDTO Owner to be created
     * @return Owner successfully created
     * @return Invalid inputs, Owner cannot be created. Reasons could be, invalid key or the default service level is specified or the owner content access mode and content access mode list cannot be set directly in standalone mode 
     * @return An unexpected exception has occurred
     */
    @POST
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @ApiOperation(value = "", notes = "Creates an owner", tags={ "owner" })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Owner successfully created", response = OwnerDTO.class),
        @ApiResponse(code = 400, message = "Invalid inputs, Owner cannot be created. Reasons could be, invalid key or the default service level is specified or the owner content access mode and content access mode list cannot be set directly in standalone mode ", response = ExceptionMessage.class),
        @ApiResponse(code = 500, message = "An unexpected exception has occurred", response = ExceptionMessage.class) })
    OwnerDTO createOwner(@Valid @NotNull OwnerDTO ownerDTO);


    /**
     * Creates a custom pool for an Owner. Floating pools are not tied to any upstream subscription, and are most commonly used for custom content delivery in Satellite. Also helps in on-site deployment testing 
     *
     * @param ownerKey The key of the owner
     * @param poolDTO A pool to be created
     * @return Pool successfully created
     * @return Invalid inputs, pool cannot be created. Reasons could be, Owner key is null or empty or Pool product ID not specified 
     * @return An owner could not be found using the provided key
     * @return An unexpected exception has occurred
     */
    @POST
    @Path("/{owner_key}/pools")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @ApiOperation(value = "", notes = "Creates a custom pool for an Owner. Floating pools are not tied to any upstream subscription, and are most commonly used for custom content delivery in Satellite. Also helps in on-site deployment testing ", tags={ "owner" })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Pool successfully created", response = PoolDTO.class),
        @ApiResponse(code = 400, message = "Invalid inputs, pool cannot be created. Reasons could be, Owner key is null or empty or Pool product ID not specified ", response = ExceptionMessage.class),
        @ApiResponse(code = 404, message = "An owner could not be found using the provided key", response = ExceptionMessage.class),
        @ApiResponse(code = 500, message = "An unexpected exception has occurred", response = ExceptionMessage.class) })
    PoolDTO createPool(@PathParam("owner_key") @ApiParam("The key of the owner") String ownerKey,@Valid @NotNull PoolDTO poolDTO);


    /**
     * Creates an Ueber Entitlement Certificate. If a certificate already exists, it will be regenerated. 
     *
     * @param ownerKey The key of the owner
     * @return Ueber Entitlement Certificate for the owner successfully created
     * @return Problem generating ueber cert for an owner
     * @return An owner could not be found using the provided key
     * @return An unexpected exception has occurred
     */
    @POST
    @Path("/{owner_key}/uebercert")
    @Produces({ "application/json" })
    @ApiOperation(value = "", notes = "Creates an Ueber Entitlement Certificate. If a certificate already exists, it will be regenerated. ", tags={ "owner" })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Ueber Entitlement Certificate for the owner successfully created", response = UeberCertificateDTO.class),
        @ApiResponse(code = 400, message = "Problem generating ueber cert for an owner", response = ExceptionMessage.class),
        @ApiResponse(code = 404, message = "An owner could not be found using the provided key", response = ExceptionMessage.class),
        @ApiResponse(code = 500, message = "An unexpected exception has occurred", response = ExceptionMessage.class) })
    UeberCertificateDTO createUeberCertificate(@PathParam("owner_key") @ApiParam("The key of the owner") String ownerKey);


    /**
     * Remove the log level of an owner
     *
     * @param ownerKey The owner key
     * @return Owner log level successfully removed
     * @return An owner could not be found using the provided key
     * @return An unexpected exception has occurred
     */
    @DELETE
    @Path("/{owner_key}/log")
    @Produces({ "application/json" })
    @ApiOperation(value = "", notes = "Remove the log level of an owner", tags={ "owner" })
    @ApiResponses(value = { 
        @ApiResponse(code = 204, message = "Owner log level successfully removed", response = Void.class),
        @ApiResponse(code = 404, message = "An owner could not be found using the provided key", response = ExceptionMessage.class),
        @ApiResponse(code = 500, message = "An unexpected exception has occurred", response = ExceptionMessage.class) })
    void deleteLogLevel(@PathParam("owner_key") @ApiParam("The owner key") String ownerKey);


    /**
     * Removes an owner
     *
     * @param ownerKey The owner key
     * @param revoke Boolean value to revoke an owner
     * @param force Boolean value to forcefully delete an owner. This is not in use.
     * @return Owner successfully deleted
     * @return An owner could not be found using the provided key
     * @return Conflict occured while owner deleting an owner
     * @return An unexpected exception has occurred
     */
    @DELETE
    @Path("/{owner_key}")
    @Produces({ "application/json" })
    @ApiOperation(value = "", notes = "Removes an owner", tags={ "owner" })
    @ApiResponses(value = { 
        @ApiResponse(code = 204, message = "Owner successfully deleted", response = Void.class),
        @ApiResponse(code = 404, message = "An owner could not be found using the provided key", response = ExceptionMessage.class),
        @ApiResponse(code = 409, message = "Conflict occured while owner deleting an owner", response = ExceptionMessage.class),
        @ApiResponse(code = 500, message = "An unexpected exception has occurred", response = ExceptionMessage.class) })
    void deleteOwner(@PathParam("owner_key") @ApiParam("The owner key") String ownerKey,@QueryParam("revoke") @DefaultValue("true")  @ApiParam("Boolean value to revoke an owner")  Boolean revoke,@QueryParam("force") @DefaultValue("false")  @ApiParam("Boolean value to forcefully delete an owner. This is not in use.")  Boolean force);


    /**
     * Retrieves an aggregate of the system purpose settings of the owner's consumers
     *
     * @param ownerKey The key of the owner
     * @return The system purpose settings sucessfully retrieved
     * @return Owner key is null or empty
     * @return Owner key could not be found using the provided key
     * @return An unexpected exception has occurred
     */
    @GET
    @Path("/{owner_key}/consumers_system_purpose")
    @Produces({ "application/json" })
    @ApiOperation(value = "", notes = "Retrieves an aggregate of the system purpose settings of the owner's consumers", tags={ "owner" })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "The system purpose settings sucessfully retrieved", response = SystemPurposeAttributesDTO.class),
        @ApiResponse(code = 400, message = "Owner key is null or empty", response = ExceptionMessage.class),
        @ApiResponse(code = 404, message = "Owner key could not be found using the provided key", response = ExceptionMessage.class),
        @ApiResponse(code = 500, message = "An unexpected exception has occurred", response = ExceptionMessage.class) })
    SystemPurposeAttributesDTO getConsumersSyspurpose(@PathParam("owner_key") @ApiParam("The key of the owner") String ownerKey);


    /**
     * Retrieves a list of hypervisors for an owner
     *
     * @param ownerKey The key of the owner
     * @param hypervisorId The list of hypervisor Ids
     * @param page Page index to return
     * @param perPage Number of items to return per page
     * @param order Direction of ordering
     * @param sortBy Property to use for ordering
     * @return A list of hypervisors successfully retrieved
     * @return An owner could not be found using the provided key
     * @return An unexpected exception has occurred
     */
    @GET
    @Path("/{owner_key}/hypervisors")
    @Produces({ "application/json" })
    @ApiOperation(value = "", notes = "Retrieves a list of hypervisors for an owner", tags={ "owner" })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "A list of hypervisors successfully retrieved", response = ConsumerDTOArrayElement.class, responseContainer = "List"),
        @ApiResponse(code = 404, message = "An owner could not be found using the provided key", response = ExceptionMessage.class, responseContainer = "List"),
        @ApiResponse(code = 500, message = "An unexpected exception has occurred", response = ExceptionMessage.class, responseContainer = "List") })
    java.util.stream.Stream<ConsumerDTOArrayElement> getHypervisors(@PathParam("owner_key") @ApiParam("The key of the owner") String ownerKey,@QueryParam("hypervisor_id")  @ApiParam("The list of hypervisor Ids")  List<String> hypervisorId,@QueryParam("page")  @ApiParam("Page index to return")  Integer page,@QueryParam("per_page")  @ApiParam("Number of items to return per page")  Integer perPage,@QueryParam("order")  @ApiParam("Direction of ordering")  String order,@QueryParam("sort_by")  @ApiParam("Property to use for ordering")  String sortBy);


    /**
     * Retrieves a list of import records for an owner
     *
     * @param ownerKey The key of the owner
     * @return Import records successfully retrieved
     * @return Owner key is null or empty
     * @return An import owner could not be found using the provided key
     * @return An unexpected exception has occurred
     */
    @GET
    @Path("/{owner_key}/imports")
    @Produces({ "application/json" })
    @ApiOperation(value = "", notes = "Retrieves a list of import records for an owner", tags={ "owner" })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Import records successfully retrieved", response = ImportRecordDTO.class, responseContainer = "List"),
        @ApiResponse(code = 400, message = "Owner key is null or empty", response = ExceptionMessage.class, responseContainer = "List"),
        @ApiResponse(code = 404, message = "An import owner could not be found using the provided key", response = ExceptionMessage.class, responseContainer = "List"),
        @ApiResponse(code = 500, message = "An unexpected exception has occurred", response = ExceptionMessage.class, responseContainer = "List") })
    java.util.stream.Stream<ImportRecordDTO> getImports(@PathParam("owner_key") @ApiParam("The key of the owner") String ownerKey);


    /**
     * Retrieves a single owner
     *
     * @param ownerKey The key of the owner
     * @return Owner successfully retrieved
     * @return An owner could not be found using the provided key
     * @return An unexpected exception has occurred
     */
    @GET
    @Path("/{owner_key}")
    @Produces({ "application/json" })
    @ApiOperation(value = "", notes = "Retrieves a single owner", tags={ "owner" })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Owner successfully retrieved", response = OwnerDTO.class),
        @ApiResponse(code = 404, message = "An owner could not be found using the provided key", response = ExceptionMessage.class),
        @ApiResponse(code = 500, message = "An unexpected exception has occurred", response = ExceptionMessage.class) })
    OwnerDTO getOwner(@PathParam("owner_key") @ApiParam("The key of the owner") String ownerKey);


    /**
     * Retrieves content access of an Owner
     *
     * @param ownerKey The key of the owner
     * @return Returns content access of an owner.
     * @return Owner was not found!
     * @return An unexpected exception has occurred
     */
    @GET
    @Path("/{owner_key}/content_access")
    @Produces({ "application/json" })
    @ApiOperation(value = "", notes = "Retrieves content access of an Owner", tags={ "owner" })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Returns content access of an owner.", response = ContentAccessDTO.class),
        @ApiResponse(code = 404, message = "Owner was not found!", response = ExceptionMessage.class),
        @ApiResponse(code = 500, message = "An unexpected exception has occurred", response = ExceptionMessage.class) })
    ContentAccessDTO getOwnerContentAccess(@PathParam("owner_key") @ApiParam("The key of the owner") String ownerKey);


    /**
     * Retrieves the Owner Info for an Owner
     *
     * @param ownerKey The key of the owner
     * @return Owner info successfully retrieved
     * @return An owner could not be found using the provided key
     * @return An unexpected exception has occurred
     */
    @GET
    @Path("/{owner_key}/info")
    @Produces({ "application/json" })
    @ApiOperation(value = "", notes = "Retrieves the Owner Info for an Owner", tags={ "owner" })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Owner info successfully retrieved", response = OwnerInfo.class),
        @ApiResponse(code = 404, message = "An owner could not be found using the provided key", response = ExceptionMessage.class),
        @ApiResponse(code = 500, message = "An unexpected exception has occurred", response = ExceptionMessage.class) })
    OwnerInfo getOwnerInfo(@PathParam("owner_key") @ApiParam("The key of the owner") String ownerKey);


    /**
     * Retrieves a list of subscriptions for an owner
     *
     * @param ownerKey The key of the owner
     * @return A list of subscriptions for an owner
     * @return Owner key is null or empty
     * @return Owner key could not be found using the provided key
     * @return An unexpected exception has occurred
     */
    @GET
    @Path("/{owner_key}/subscriptions")
    @Produces({ "application/json" })
    @ApiOperation(value = "", notes = "Retrieves a list of subscriptions for an owner", tags={ "owner" })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "A list of subscriptions for an owner", response = SubscriptionDTO.class, responseContainer = "List"),
        @ApiResponse(code = 400, message = "Owner key is null or empty", response = ExceptionMessage.class, responseContainer = "List"),
        @ApiResponse(code = 404, message = "Owner key could not be found using the provided key", response = ExceptionMessage.class, responseContainer = "List"),
        @ApiResponse(code = 500, message = "An unexpected exception has occurred", response = ExceptionMessage.class, responseContainer = "List") })
    Iterable<SubscriptionDTO> getOwnerSubscriptions(@PathParam("owner_key") @ApiParam("The key of the owner") String ownerKey);


    /**
     * Retrieves the system purpose settings available to an owner
     *
     * @param ownerKey The key of the owner
     * @return The details of system purpose settings for an owner
     * @return Owner key is null or empty
     * @return Owner key could not be found using the provided key
     * @return An unexpected exception has occurred
     */
    @GET
    @Path("/{owner_key}/system_purpose")
    @Produces({ "application/json" })
    @ApiOperation(value = "", notes = "Retrieves the system purpose settings available to an owner", tags={ "owner" })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "The details of system purpose settings for an owner", response = SystemPurposeAttributesDTO.class),
        @ApiResponse(code = 400, message = "Owner key is null or empty", response = ExceptionMessage.class),
        @ApiResponse(code = 404, message = "Owner key could not be found using the provided key", response = ExceptionMessage.class),
        @ApiResponse(code = 500, message = "An unexpected exception has occurred", response = ExceptionMessage.class) })
    SystemPurposeAttributesDTO getSyspurpose(@PathParam("owner_key") @ApiParam("The key of the owner") String ownerKey);


    /**
     * Retrieves the Ueber Entitlement Certificate
     *
     * @param ownerKey The key of the owner
     * @return Ueber Entitlement Certificate for the owner successfully retrieved
     * @return An owner could not be found using the provided key or Uber certificate for owner was not found. Please generate one. 
     * @return An unexpected exception has occurred
     */
    @GET
    @Path("/{owner_key}/uebercert")
    @Produces({ "application/json" })
    @ApiOperation(value = "", notes = "Retrieves the Ueber Entitlement Certificate", tags={ "owner" })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Ueber Entitlement Certificate for the owner successfully retrieved", response = UeberCertificateDTO.class),
        @ApiResponse(code = 404, message = "An owner could not be found using the provided key or Uber certificate for owner was not found. Please generate one. ", response = ExceptionMessage.class),
        @ApiResponse(code = 500, message = "An unexpected exception has occurred", response = ExceptionMessage.class) })
    UeberCertificateDTO getUeberCertificate(@PathParam("owner_key") @ApiParam("The key of the owner") String ownerKey);


    /**
     * Retrieves a list of upstream consumers for an owner
     *
     * @param ownerKey The key of the owner
     * @return A list of upstream consumers successfully retrieved
     * @return Owner key is null or empty
     * @return Owner key could not be found using the provided key
     * @return An unexpected exception has occurred
     */
    @GET
    @Path("/{owner_key}/upstream_consumers")
    @Produces({ "application/json" })
    @ApiOperation(value = "", notes = "Retrieves a list of upstream consumers for an owner", tags={ "owner" })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "A list of upstream consumers successfully retrieved", response = UpstreamConsumerDTOArrayElement.class, responseContainer = "List"),
        @ApiResponse(code = 400, message = "Owner key is null or empty", response = ExceptionMessage.class, responseContainer = "List"),
        @ApiResponse(code = 404, message = "Owner key could not be found using the provided key", response = ExceptionMessage.class, responseContainer = "List"),
        @ApiResponse(code = 500, message = "An unexpected exception has occurred", response = ExceptionMessage.class, responseContainer = "List") })
    Iterable<UpstreamConsumerDTOArrayElement> getUpstreamConsumers(@PathParam("owner_key") @ApiParam("The key of the owner") String ownerKey);


    /**
     * Starts an asynchronous healing for the given Owner. At the end of the process the idea is that all of the consumers in the owned by the owner will be up to date. 
     *
     * @param ownerKey The key of the owner
     * @return The Owner is being healed
     * @return An owner could not be found using the provided key
     * @return An unexpected exception has occurred
     */
    @POST
    @Path("/{owner_key}/entitlements")
    @Produces({ "application/json" })
    @ApiOperation(value = "", notes = "Starts an asynchronous healing for the given Owner. At the end of the process the idea is that all of the consumers in the owned by the owner will be up to date. ", tags={ "owner" })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "The Owner is being healed", response = AsyncJobStatusDTO.class),
        @ApiResponse(code = 404, message = "An owner could not be found using the provided key", response = ExceptionMessage.class),
        @ApiResponse(code = 500, message = "An unexpected exception has occurred", response = ExceptionMessage.class) })
    AsyncJobStatusDTO healEntire(@PathParam("owner_key") @ApiParam("The key of the owner") String ownerKey);


    /**
     * Initiates an asynchronous manifest import for the given organization. This will bring in any products, content, and subscriptions that were assigned to the distributor who generated the manifest. 
     *
     * @param ownerKey The key of the owner
     * @param force This is used to override the conflicts
     * @param input 
     * @return Manifest successfully imported
     * @return Owner key is null or empty
     * @return An import owner could not be found using the provided key
     * @return Internal server error. Reasons could be, An unexpected exception occurred while scheduling job or error reading export archive or error storing uploaded archive for asynchronous processing 
     * @return An unexpected exception has occurred
     */
    @POST
    @Path("/{owner_key}/imports/async")
    @Consumes({ "multipart/form-data" })
    @Produces({ "application/json" })
    @ApiOperation(value = "", notes = "Initiates an asynchronous manifest import for the given organization. This will bring in any products, content, and subscriptions that were assigned to the distributor who generated the manifest. ", tags={ "owner" })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Manifest successfully imported", response = AsyncJobStatusDTO.class),
        @ApiResponse(code = 400, message = "Owner key is null or empty", response = ExceptionMessage.class),
        @ApiResponse(code = 404, message = "An import owner could not be found using the provided key", response = ExceptionMessage.class),
        @ApiResponse(code = 500, message = "Internal server error. Reasons could be, An unexpected exception occurred while scheduling job or error reading export archive or error storing uploaded archive for asynchronous processing ", response = ExceptionMessage.class),
        @ApiResponse(code = 500, message = "An unexpected exception has occurred", response = ExceptionMessage.class) })
    AsyncJobStatusDTO importManifestAsync(@PathParam("owner_key") @ApiParam("The key of the owner") String ownerKey,@QueryParam("force")  @ApiParam("This is used to override the conflicts")  List<String> force, MultipartInput input);


    /**
     * Retrieves a list of Consumers for the Owner
     *
     * @param ownerKey The key of the owner
     * @param username The username of the consumer
     * @param type The consumer type
     * @param uuid The UUID of consumers
     * @param hypervisorId The hypervisor IDs
     * @param fact The consumer facts
     * @param page Page index to return
     * @param perPage Number of items to return per page
     * @param order Direction of ordering
     * @param sortBy Property to use for ordering
     * @return Consumers successfully retrieved
     * @return Owner key is null or empty
     * @return An owner could not be found using the provided key
     * @return An unexpected exception has occurred
     */
    @GET
    @Path("/{owner_key}/consumers")
    @Produces({ "application/json" })
    @ApiOperation(value = "", notes = "Retrieves a list of Consumers for the Owner", tags={ "owner" })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Consumers successfully retrieved", response = ConsumerDTOArrayElement.class, responseContainer = "List"),
        @ApiResponse(code = 400, message = "Owner key is null or empty", response = ExceptionMessage.class, responseContainer = "List"),
        @ApiResponse(code = 404, message = "An owner could not be found using the provided key", response = ExceptionMessage.class, responseContainer = "List"),
        @ApiResponse(code = 500, message = "An unexpected exception has occurred", response = ExceptionMessage.class, responseContainer = "List") })
    java.util.stream.Stream<ConsumerDTOArrayElement> listConsumers(@PathParam("owner_key") @ApiParam("The key of the owner") String ownerKey,@QueryParam("username")  @ApiParam("The username of the consumer")  String username,@QueryParam("type")  @ApiParam("The consumer type")  Set<String> type,@QueryParam("uuid")  @ApiParam("The UUID of consumers")  List<String> uuid,@QueryParam("hypervisor_id")  @ApiParam("The hypervisor IDs")  List<String> hypervisorId,@QueryParam("fact")  @ApiParam("The consumer facts")  List<String> fact,@QueryParam("page")  @ApiParam("Page index to return")  Integer page,@QueryParam("per_page")  @ApiParam("Number of items to return per page")  Integer perPage,@QueryParam("order")  @ApiParam("Direction of ordering")  String order,@QueryParam("sort_by")  @ApiParam("Property to use for ordering")  String sortBy);


    /**
     * Retrieves a list of environment for an owner
     *
     * @param ownerKey The key of the owner
     * @param name The name of the environment
     * @param type The type of the environments
     * @param listAll List all boolean for environments of all types
     * @return Environments successfully retrieved
     * @return An owner could not be found using the provided key
     * @return An unexpected exception has occurred
     */
    @GET
    @Path("/{owner_key}/environments")
    @Produces({ "application/json" })
    @ApiOperation(value = "", notes = "Retrieves a list of environment for an owner", tags={ "owner" })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Environments successfully retrieved", response = EnvironmentDTO.class, responseContainer = "List"),
        @ApiResponse(code = 404, message = "An owner could not be found using the provided key", response = ExceptionMessage.class, responseContainer = "List"),
        @ApiResponse(code = 500, message = "An unexpected exception has occurred", response = ExceptionMessage.class, responseContainer = "List") })
    java.util.stream.Stream<EnvironmentDTO> listEnvironments(@PathParam("owner_key") @ApiParam("The key of the owner") String ownerKey,@QueryParam("name")  @ApiParam("The name of the environment")  String name,@QueryParam("type")  @ApiParam("The type of the environments")  List<String> type,@QueryParam("list_all") @DefaultValue("false")  @ApiParam("List all boolean for environments of all types")  Boolean listAll);


    /**
     * Retrieves a list of Pools for an Owner. This endpoint supports paging with query parameters. For more details please visit https://www.candlepinproject.org/docs/candlepin/pagination.html#paginating-results-from-candlepin 
     *
     * @param ownerKey The key of the owner
     * @param consumer The consumer UUID
     * @param activationKey The activation key name
     * @param product The product id
     * @param subscription The subscription id
     * @param listall Includes pools which are not applicable based on some of the consumer&#39;s current system facts (i.e. system architecture, sockets, cores, ram, storage_band, instance_multiplier), but will still filter pools based on other factors, such as pools restricted to a consumer of specific types, usernames, or uuids; pools restricted to guests of specific hosts; non-multi-entitlement pools that the consumer has already attached; unmapped guest pools for which the consumer is ineligible; and expired pools. 
     * @param activeon Active on date
     * @param matches Find pools matching the given pattern in a variety of fields * and ? wildcards are supported; may be specified multiple times 
     * @param attribute The attributes to return based on the specified types
     * @param addFuture When set to true, it will add future dated pools to the result, based on the activeon date 
     * @param onlyFuture When set to true, it will return only future dated pools to the result, based on the activeon date 
     * @param after Will only return pools with a start date after the supplied date. Overrides the activeOn date 
     * @param poolid One or more pool IDs to use to filter the output; only pools with IDs matching those provided will be returned; may be specified multiple times 
     * @param page Page index to return
     * @param perPage Number of items to return per page
     * @param order Direction of ordering
     * @param sortBy Property to use for ordering
     * @return Pools successfully retrieved
     * @return Invalid inputs, pools cannot be listed. Reasons could be, Owner key is null or empty or the consumer specified does not belong to owner on path or activationKey could not be found with provided key or the flags add_future and only_future cannot be used at the same time or the flags add_future and only_future cannot be used with the parameter after
     * @return User cannot access the consumer
     * @return Invalid inputs, pools cannot be listed. Reasons could be, An owner could not be found using the provided key or the consumer not found using the provided uuid 
     * @return An unexpected exception has occurred
     */
    @GET
    @Path("/{owner_key}/pools")
    @Produces({ "application/json" })
    @ApiOperation(value = "", notes = "Retrieves a list of Pools for an Owner. This endpoint supports paging with query parameters. For more details please visit https://www.candlepinproject.org/docs/candlepin/pagination.html#paginating-results-from-candlepin ", tags={ "owner" })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Pools successfully retrieved", response = PoolDTO.class, responseContainer = "List"),
        @ApiResponse(code = 400, message = "Invalid inputs, pools cannot be listed. Reasons could be, Owner key is null or empty or the consumer specified does not belong to owner on path or activationKey could not be found with provided key or the flags add_future and only_future cannot be used at the same time or the flags add_future and only_future cannot be used with the parameter after", response = ExceptionMessage.class, responseContainer = "List"),
        @ApiResponse(code = 403, message = "User cannot access the consumer", response = ExceptionMessage.class, responseContainer = "List"),
        @ApiResponse(code = 404, message = "Invalid inputs, pools cannot be listed. Reasons could be, An owner could not be found using the provided key or the consumer not found using the provided uuid ", response = ExceptionMessage.class, responseContainer = "List"),
        @ApiResponse(code = 500, message = "An unexpected exception has occurred", response = ExceptionMessage.class, responseContainer = "List") })
    java.util.stream.Stream<PoolDTO> listOwnerPools(@PathParam("owner_key") @ApiParam("The key of the owner") String ownerKey,@QueryParam("consumer")  @ApiParam("The consumer UUID")  String consumer,@QueryParam("activation_key")  @ApiParam("The activation key name")  String activationKey,@QueryParam("product")  @ApiParam("The product id")  String product,@QueryParam("subscription")  @ApiParam("The subscription id")  String subscription,@QueryParam("listall") @DefaultValue("false")  @ApiParam("Includes pools which are not applicable based on some of the consumer&#39;s current system facts (i.e. system architecture, sockets, cores, ram, storage_band, instance_multiplier), but will still filter pools based on other factors, such as pools restricted to a consumer of specific types, usernames, or uuids; pools restricted to guests of specific hosts; non-multi-entitlement pools that the consumer has already attached; unmapped guest pools for which the consumer is ineligible; and expired pools. ")  Boolean listall,@QueryParam("activeon")  @ApiParam("Active on date")  OffsetDateTime activeon,@QueryParam("matches")  @ApiParam("Find pools matching the given pattern in a variety of fields * and ? wildcards are supported; may be specified multiple times ")  List<String> matches,@QueryParam("attribute")  @ApiParam("The attributes to return based on the specified types")  List<String> attribute,@QueryParam("add_future") @DefaultValue("false")  @ApiParam("When set to true, it will add future dated pools to the result, based on the activeon date ")  Boolean addFuture,@QueryParam("only_future") @DefaultValue("false")  @ApiParam("When set to true, it will return only future dated pools to the result, based on the activeon date ")  Boolean onlyFuture,@QueryParam("after")  @ApiParam("Will only return pools with a start date after the supplied date. Overrides the activeOn date ")  OffsetDateTime after,@QueryParam("poolid")  @ApiParam("One or more pool IDs to use to filter the output; only pools with IDs matching those provided will be returned; may be specified multiple times ")  List<String> poolid,@QueryParam("page")  @ApiParam("Page index to return")  Integer page,@QueryParam("per_page")  @ApiParam("Number of items to return per page")  Integer perPage,@QueryParam("order")  @ApiParam("Direction of ordering")  String order,@QueryParam("sort_by")  @ApiParam("Property to use for ordering")  String sortBy);


    /**
     * Retrieves a list of owners that match the key provided, or all owners if no key was provided
     *
     * @param key The owner key
     * @param page Page index to return
     * @param perPage Number of items to return per page
     * @param order Direction of ordering
     * @param sortBy Property to use for ordering
     * @return A list of owners
     * @return An unexpected exception has occurred
     */
    @GET
    @Produces({ "application/json" })
    @ApiOperation(value = "", notes = "Retrieves a list of owners that match the key provided, or all owners if no key was provided", tags={ "owner" })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "A list of owners", response = OwnerDTO.class, responseContainer = "List"),
        @ApiResponse(code = 500, message = "An unexpected exception has occurred", response = ExceptionMessage.class, responseContainer = "List") })
    java.util.stream.Stream<OwnerDTO> listOwners(@QueryParam("key")  @ApiParam("The owner key")  String key,@QueryParam("page")  @ApiParam("Page index to return")  Integer page,@QueryParam("per_page")  @ApiParam("Number of items to return per page")  Integer perPage,@QueryParam("order")  @ApiParam("Direction of ordering")  String order,@QueryParam("sort_by")  @ApiParam("Property to use for ordering")  String sortBy);


    /**
     * Retrieves a list of activation keys for an owner
     *
     * @param ownerKey The key of the owner
     * @param name The name of the activation key
     * @return Activation keys successfully retrieved
     * @return Owner key is null or empty
     * @return An owner could not be found using the provided key
     * @return An unexpected exception has occurred
     */
    @GET
    @Path("/{owner_key}/activation_keys")
    @Produces({ "application/json" })
    @ApiOperation(value = "", notes = "Retrieves a list of activation keys for an owner", tags={ "owner" })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Activation keys successfully retrieved", response = ActivationKeyDTO.class, responseContainer = "List"),
        @ApiResponse(code = 400, message = "Owner key is null or empty", response = ExceptionMessage.class, responseContainer = "List"),
        @ApiResponse(code = 404, message = "An owner could not be found using the provided key", response = ExceptionMessage.class, responseContainer = "List"),
        @ApiResponse(code = 500, message = "An unexpected exception has occurred", response = ExceptionMessage.class, responseContainer = "List") })
    java.util.stream.Stream<ActivationKeyDTO> ownerActivationKeys(@PathParam("owner_key") @ApiParam("The key of the owner") String ownerKey,@QueryParam("name")  @ApiParam("The name of the activation key")  String name);


    /**
     * Retrieves the list of entitlements for an owner. This endpoint supports paging with query parameters. For more details please visit https://www.candlepinproject.org/docs/candlepin/pagination.html#paginating-results-from-candlepin 
     *
     * @param ownerKey The key of the owner
     * @param product The product id
     * @param attribute Attribute filters
     * @param page Page index to return
     * @param perPage Number of items to return per page
     * @param order Direction of ordering
     * @param sortBy Property to use for ordering
     * @return Entitlement details successfully retrieved
     * @return An owner could not be found using the provided key
     * @return An unexpected exception has occurred
     */
    @GET
    @Path("/{owner_key}/entitlements")
    @Produces({ "application/json" })
    @ApiOperation(value = "", notes = "Retrieves the list of entitlements for an owner. This endpoint supports paging with query parameters. For more details please visit https://www.candlepinproject.org/docs/candlepin/pagination.html#paginating-results-from-candlepin ", tags={ "owner" })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Entitlement details successfully retrieved", response = EntitlementDTO.class, responseContainer = "List"),
        @ApiResponse(code = 404, message = "An owner could not be found using the provided key", response = ExceptionMessage.class, responseContainer = "List"),
        @ApiResponse(code = 500, message = "An unexpected exception has occurred", response = ExceptionMessage.class, responseContainer = "List") })
    List<EntitlementDTO> ownerEntitlements(@PathParam("owner_key") @ApiParam("The key of the owner") String ownerKey,@QueryParam("product")  @ApiParam("The product id")  String product,@QueryParam("attribute")  @ApiParam("Attribute filters")  List<String> attribute,@QueryParam("page")  @ApiParam("Page index to return")  Integer page,@QueryParam("per_page")  @ApiParam("Number of items to return per page")  Integer perPage,@QueryParam("order")  @ApiParam("Direction of ordering")  String order,@QueryParam("sort_by")  @ApiParam("Property to use for ordering")  String sortBy);


    /**
     * Retrieves a list of support levels for an owner
     *
     * @param ownerKey The key of the owner
     * @param exempt exempt
     * @return A list of support levels for an owner
     * @return Owner key is null or empty
     * @return Owner key could not be found using the provided key
     * @return An unexpected exception has occurred
     */
    @GET
    @Path("/{owner_key}/servicelevels")
    @Produces({ "application/json" })
    @ApiOperation(value = "", notes = "Retrieves a list of support levels for an owner", tags={ "owner" })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "A list of support levels for an owner", response = String.class, responseContainer = "List"),
        @ApiResponse(code = 400, message = "Owner key is null or empty", response = ExceptionMessage.class, responseContainer = "List"),
        @ApiResponse(code = 404, message = "Owner key could not be found using the provided key", response = ExceptionMessage.class, responseContainer = "List"),
        @ApiResponse(code = 500, message = "An unexpected exception has occurred", response = ExceptionMessage.class, responseContainer = "List") })
    Iterable<String> ownerServiceLevels(@PathParam("owner_key") @ApiParam("The key of the owner") String ownerKey,@QueryParam("exempt") @DefaultValue("false")  @ApiParam("exempt")  String exempt);


    /**
     * Triggers an asynchronous \"refresh\" operation, updating the entitlement and subscription information for the specified owner. This endpoint is only functional in hosted mode, and no operation will be triggered when called in a standalone, or on-site, deployment. 
     *
     * @param ownerKey The key of the owner
     * @param autoCreateOwner Specify whether or not to create an owner automatically. Default is false.
     * @return A successful operation
     * @return Owner key could not be found using the provided key
     * @return An unexpected exception has occurred
     */
    @PUT
    @Path("/{owner_key}/subscriptions")
    @Produces({ "application/json" })
    @ApiOperation(value = "", notes = "Triggers an asynchronous \"refresh\" operation, updating the entitlement and subscription information for the specified owner. This endpoint is only functional in hosted mode, and no operation will be triggered when called in a standalone, or on-site, deployment. ", tags={ "owner" })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "A successful operation", response = AsyncJobStatusDTO.class),
        @ApiResponse(code = 404, message = "Owner key could not be found using the provided key", response = ExceptionMessage.class),
        @ApiResponse(code = 500, message = "An unexpected exception has occurred", response = ExceptionMessage.class) })
    AsyncJobStatusDTO refreshPools(@PathParam("owner_key") @ApiParam("The key of the owner") String ownerKey,@QueryParam("auto_create_owner") @DefaultValue("false")  @ApiParam("Specify whether or not to create an owner automatically. Default is false.")  Boolean autoCreateOwner);


    /**
     * Sets the environments of the provided consumers to the exact environments that are provided. 
     *
     * @param ownerKey The key of the owner
     * @param setConsumerEnvironmentsDTO Contains a list of consumer UUIDs and a list of environment IDs. The list of consumer UUIDs dictates the consumers that will have their environments set to the list of provided environemnt IDs. Any existing environment for a consumer that differs from the provided environments will be removed. The ordering of the environment IDs dictates the priority of the environments. For example, environment in index 0 will have priority 0 and environment in index 1 will have priority 1. If an empty list of environment IDs is provided, then the consumers will be removed from all of their current environments. 
     * @return All of the consumers were set to the provided environments.
     * @return This response indicates invalid input. Examples of invalid input includes the provided owner key is null or empty, the owner is in entitlement content access mode, the list of environments contains an environment that does not exist for the owner, the list of consumer UUIDs contains one or more consumer UUIDs that does not exist for the owner, or the list of consumer UUIDs or environment IDs exceeds the max configured size. 
     * @return An owner could not be found using the provided key
     */
    @PUT
    @Path("/{owner_key}/consumers/environments")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @ApiOperation(value = "", notes = "Sets the environments of the provided consumers to the exact environments that are provided. ", tags={ "owner" })
    @ApiResponses(value = { 
        @ApiResponse(code = 204, message = "All of the consumers were set to the provided environments.", response = Void.class),
        @ApiResponse(code = 400, message = "This response indicates invalid input. Examples of invalid input includes the provided owner key is null or empty, the owner is in entitlement content access mode, the list of environments contains an environment that does not exist for the owner, the list of consumer UUIDs contains one or more consumer UUIDs that does not exist for the owner, or the list of consumer UUIDs or environment IDs exceeds the max configured size. ", response = ExceptionMessage.class),
        @ApiResponse(code = 404, message = "An owner could not be found using the provided key", response = ExceptionMessage.class) })
    void setConsumersToEnvironments(@PathParam("owner_key") @ApiParam("The key of the owner") String ownerKey,@Valid @NotNull SetConsumerEnvironmentsDTO setConsumerEnvironmentsDTO);


    /**
     * Sets the log level for an owner
     *
     * @param ownerKey The key of the owner
     * @param level The log level
     * @return Log level successfully set
     * @return Provided level is not a valid log level or owner key is not provided 
     * @return An owner could not be found using the provided key
     * @return An unexpected exception has occurred
     */
    @PUT
    @Path("/{owner_key}/log")
    @Produces({ "application/json" })
    @ApiOperation(value = "", notes = "Sets the log level for an owner", tags={ "owner" })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Log level successfully set", response = OwnerDTO.class),
        @ApiResponse(code = 400, message = "Provided level is not a valid log level or owner key is not provided ", response = ExceptionMessage.class),
        @ApiResponse(code = 404, message = "An owner could not be found using the provided key", response = ExceptionMessage.class),
        @ApiResponse(code = 500, message = "An unexpected exception has occurred", response = ExceptionMessage.class) })
    OwnerDTO setLogLevel(@PathParam("owner_key") @ApiParam("The key of the owner") String ownerKey,@QueryParam("level") @DefaultValue("DEBUG")  @ApiParam("The log level")  String level);


    /**
     * Removes Imports for an Owner. Cleans out all imported subscriptions and triggers a background refresh pools. Link to an upstream distributor is removed for the owner, so they can import from another distributor. Other owners can also now import the manifests originally used in this owner. This call does not differentiate between any specific import, it just destroys all subscriptions with an upstream pool ID, essentially anything from an import. Custom subscriptions will be left alone. Imports do carry rules and product information which is global to the candlepin server. This import data is *not* undone, we assume that updates to this data can be safely kept. 
     *
     * @param ownerKey The key of the owner
     * @return Imports for an Owner successfully removed
     * @return An import owner could not be found using the provided key
     * @return An unexpected exception has occurred
     */
    @DELETE
    @Path("/{owner_key}/imports")
    @Produces({ "application/json" })
    @ApiOperation(value = "", notes = "Removes Imports for an Owner. Cleans out all imported subscriptions and triggers a background refresh pools. Link to an upstream distributor is removed for the owner, so they can import from another distributor. Other owners can also now import the manifests originally used in this owner. This call does not differentiate between any specific import, it just destroys all subscriptions with an upstream pool ID, essentially anything from an import. Custom subscriptions will be left alone. Imports do carry rules and product information which is global to the candlepin server. This import data is *not* undone, we assume that updates to this data can be safely kept. ", tags={ "owner" })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Imports for an Owner successfully removed", response = AsyncJobStatusDTO.class),
        @ApiResponse(code = 404, message = "An import owner could not be found using the provided key", response = ExceptionMessage.class),
        @ApiResponse(code = 500, message = "An unexpected exception has occurred", response = ExceptionMessage.class) })
    AsyncJobStatusDTO undoImports(@PathParam("owner_key") @ApiParam("The key of the owner") String ownerKey);


    /**
     * Updates an owner. Note: To un-set the defaultServiceLevel for an owner, submit an empty string. 
     *
     * @param ownerKey The key of the owner
     * @param ownerDTO Owner to be updated
     * @return Owner successfully updated
     * @return Invalid inputs, Owner cannot be updated. Reasons could be, invalid key or the owner content access mode and content access mode list cannot be set directly in standalone mode 
     * @return An owner could not be found using the provided key
     * @return An unexpected exception has occurred
     */
    @PUT
    @Path("/{owner_key}")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @ApiOperation(value = "", notes = "Updates an owner. Note: To un-set the defaultServiceLevel for an owner, submit an empty string. ", tags={ "owner" })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Owner successfully updated", response = OwnerDTO.class),
        @ApiResponse(code = 400, message = "Invalid inputs, Owner cannot be updated. Reasons could be, invalid key or the owner content access mode and content access mode list cannot be set directly in standalone mode ", response = ExceptionMessage.class),
        @ApiResponse(code = 404, message = "An owner could not be found using the provided key", response = ExceptionMessage.class),
        @ApiResponse(code = 500, message = "An unexpected exception has occurred", response = ExceptionMessage.class) })
    OwnerDTO updateOwner(@PathParam("owner_key") @ApiParam("The key of the owner") String ownerKey,@Valid @NotNull OwnerDTO ownerDTO);


    /**
     * Updates a pool for an Owner. Assumes this is a normal pool, and errors out otherwise cause we cannot create primary pools from bonus pools 
     *
     * @param ownerKey Owner key
     * @param poolDTO A pool to be updated
     * @return Pool successfully updated
     * @return Invalid inputs, pool cannot be updated. Reasons could be, Owner key is null or empty or Pool product ID not specified or cannot update bonus pools, as they are auto generated 
     * @return Invalid inputs, pool cannot be created. Reasons could be, An owner could not be found using the provided key or pool could not be found using provided pool with the ID or provided pool does not belong to the specified owner 
     * @return An unexpected exception has occurred
     */
    @PUT
    @Path("/{owner_key}/pools")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @ApiOperation(value = "", notes = "Updates a pool for an Owner. Assumes this is a normal pool, and errors out otherwise cause we cannot create primary pools from bonus pools ", tags={ "owner" })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Pool successfully updated", response = Void.class),
        @ApiResponse(code = 400, message = "Invalid inputs, pool cannot be updated. Reasons could be, Owner key is null or empty or Pool product ID not specified or cannot update bonus pools, as they are auto generated ", response = ExceptionMessage.class),
        @ApiResponse(code = 404, message = "Invalid inputs, pool cannot be created. Reasons could be, An owner could not be found using the provided key or pool could not be found using provided pool with the ID or provided pool does not belong to the specified owner ", response = ExceptionMessage.class),
        @ApiResponse(code = 500, message = "An unexpected exception has occurred", response = ExceptionMessage.class) })
    void updatePool(@PathParam("owner_key") @ApiParam("Owner key") String ownerKey,@Valid @NotNull PoolDTO poolDTO);

}
