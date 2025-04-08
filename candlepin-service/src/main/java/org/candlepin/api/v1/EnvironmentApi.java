package org.candlepin.api.v1;

import org.candlepin.dto.api.server.v1.AsyncJobStatusDTO;
import org.candlepin.dto.api.server.v1.ConsumerDTO;
import org.candlepin.dto.api.server.v1.ContentOverrideDTO;
import org.candlepin.dto.api.server.v1.ContentToPromoteDTO;
import org.candlepin.dto.api.server.v1.EnvironmentDTO;
import org.candlepin.dto.api.server.v1.ExceptionMessage;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

import io.swagger.annotations.*;

import org.jboss.resteasy.plugins.providers.multipart.MultipartInput;
import java.util.Map;
import java.util.List;
import javax.validation.constraints.*;
import javax.validation.Valid;

@Path("/environments")
@Api(description = "the Environment API")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaJAXRSSpecServerCodegen", date = "2025-03-28T14:05:44.436523329-04:00[America/Detroit]", comments = "Generator version: 7.12.0")
public interface EnvironmentApi {

    /**
     * Creates a Consumer in an Environment
     *
     * @param envId 
     * @param consumerDTO 
     * @param username 
     * @param activationKeys 
     * @return Consumer was successfully created.
     * @return No such environment: test_env
     * @return Unit type must be specified or Two or more environments belong to different organizations
     * @return Principal is not authorized to register with organization org
     * @return An unexpected exception has occurred
     */
    @POST
    @Path("/{env_id}/consumers")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @ApiOperation(value = "", notes = "Creates a Consumer in an Environment", authorizations = {
        
        @Authorization(value = "ActivationKey"),
        
        @Authorization(value = "ActivationKeyOwner")
         }, tags={ "environment" })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Consumer was successfully created.", response = ConsumerDTO.class),
        @ApiResponse(code = 404, message = "No such environment: test_env", response = ExceptionMessage.class),
        @ApiResponse(code = 400, message = "Unit type must be specified or Two or more environments belong to different organizations", response = ExceptionMessage.class),
        @ApiResponse(code = 403, message = "Principal is not authorized to register with organization org", response = ExceptionMessage.class),
        @ApiResponse(code = 500, message = "An unexpected exception has occurred", response = ExceptionMessage.class) })
    ConsumerDTO createConsumerInEnvironment(@PathParam("env_id") String envId,@Valid @NotNull ConsumerDTO consumerDTO,@QueryParam("username")   String username,@QueryParam("activation_keys")   String activationKeys);


    /**
     * Deletes the specified environment, unregistering and deleting any consumers registered to the environment with no other remaining environments. If the retain_consumers flag is set to true, no consumer deletion will occur, and any such consumers will be moved to the organization's default content view. 
     *
     * @param envId 
     * @param retainConsumers Whether or not to retain affected consumers with no remaining environments. If set to true, affected consumers will be moved to the organization&#39;s default content view. 
     * @return Environment was successfully deleted.
     * @return No such environment: test_env
     * @return An unexpected exception has occurred
     */
    @DELETE
    @Path("/{env_id}")
    @Produces({ "application/json" })
    @ApiOperation(value = "", notes = "Deletes the specified environment, unregistering and deleting any consumers registered to the environment with no other remaining environments. If the retain_consumers flag is set to true, no consumer deletion will occur, and any such consumers will be moved to the organization's default content view. ", tags={ "environment" })
    @ApiResponses(value = { 
        @ApiResponse(code = 204, message = "Environment was successfully deleted.", response = Void.class),
        @ApiResponse(code = 404, message = "No such environment: test_env", response = ExceptionMessage.class),
        @ApiResponse(code = 500, message = "An unexpected exception has occurred", response = ExceptionMessage.class) })
    void deleteEnvironment(@PathParam("env_id") String envId,@QueryParam("retain_consumers") @DefaultValue("false")  @ApiParam("Whether or not to retain affected consumers with no remaining environments. If set to true, affected consumers will be moved to the organization&#39;s default content view. ")  Boolean retainConsumers);


    /**
     * Removes one or more content overrides from the given environment. If the list of content overrides to remove is empty, or the list contains one or more entries without a label, all content overrides for the specified environment will be removed. If the list contains one or more entries with a label but without an override name (key), all overrides for that label will be removed. If no matching overrides could be found, no change will be made to the environment.  Regardless of which, if any, content overrides are removed, this endpoint returns a list containing the remaining overrides on the specified environment. 
     *
     * @param environmentId The ID of the environment from which to remove content overrides 
     * @param contentOverrideDTO A list containing the content overrides to remove from the environment. The overrides need not be fully populated, as the value is ignored entirely during removal; and depending on the behavior desired, the name or label fields may not be needed, either. See the description for the operation itself for more details. 
     * @return An update list of all remaining content overrides for the specified environment 
     * @return An environment with the specified ID does not exist 
     */
    @DELETE
    @Path("/{environment_id}/content_overrides")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @ApiOperation(value = "", notes = "Removes one or more content overrides from the given environment. If the list of content overrides to remove is empty, or the list contains one or more entries without a label, all content overrides for the specified environment will be removed. If the list contains one or more entries with a label but without an override name (key), all overrides for that label will be removed. If no matching overrides could be found, no change will be made to the environment.  Regardless of which, if any, content overrides are removed, this endpoint returns a list containing the remaining overrides on the specified environment. ", tags={ "environment" })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "An update list of all remaining content overrides for the specified environment ", response = ContentOverrideDTO.class, responseContainer = "List"),
        @ApiResponse(code = 404, message = "An environment with the specified ID does not exist ", response = ExceptionMessage.class, responseContainer = "List") })
    java.util.stream.Stream<ContentOverrideDTO> deleteEnvironmentContentOverrides(@PathParam("environment_id") @ApiParam("The ID of the environment from which to remove content overrides ") String environmentId,@Valid List<ContentOverrideDTO> contentOverrideDTO);


    /**
     * Demotes a Content from an Environment. Consumer's registered to this environment will not see this content in their entitlement certificates. (after they are regenerated and synced to clients) This call accepts multiple content IDs to demote at once, allowing us to mass demote, then trigger a cert regeneration.  NOTE: This call expects the actual content IDs, *not* the ID created for each EnvironmentContent object created after a promotion. This is to help integrate with other management apps which should not have to track/lookup a specific ID for the content to demote. 
     *
     * @param envId 
     * @param content 
     * @param lazyRegen 
     * @return Content was successfully demoted
     * @return Content does not exist in environment: test_content
     * @return An unexpected exception has occurred
     */
    @DELETE
    @Path("/{env_id}/content")
    @Produces({ "application/json" })
    @ApiOperation(value = "", notes = "Demotes a Content from an Environment. Consumer's registered to this environment will not see this content in their entitlement certificates. (after they are regenerated and synced to clients) This call accepts multiple content IDs to demote at once, allowing us to mass demote, then trigger a cert regeneration.  NOTE: This call expects the actual content IDs, *not* the ID created for each EnvironmentContent object created after a promotion. This is to help integrate with other management apps which should not have to track/lookup a specific ID for the content to demote. ", tags={ "environment" })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Content was successfully demoted", response = AsyncJobStatusDTO.class),
        @ApiResponse(code = 404, message = "Content does not exist in environment: test_content", response = ExceptionMessage.class),
        @ApiResponse(code = 500, message = "An unexpected exception has occurred", response = ExceptionMessage.class) })
    AsyncJobStatusDTO demoteContent(@PathParam("env_id") String envId,@QueryParam("content") @NotNull   List<String> content,@QueryParam("lazy_regen") @DefaultValue("true")   Boolean lazyRegen);


    /**
     * Retrieves a single Environment
     *
     * @param envId 
     * @return Environment was successfully retrieved.
     * @return No such environment: test_env
     * @return An unexpected exception has occurred
     */
    @GET
    @Path("/{env_id}")
    @Produces({ "application/json" })
    @ApiOperation(value = "", notes = "Retrieves a single Environment", tags={ "environment" })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Environment was successfully retrieved.", response = EnvironmentDTO.class),
        @ApiResponse(code = 404, message = "No such environment: test_env", response = ExceptionMessage.class),
        @ApiResponse(code = 500, message = "An unexpected exception has occurred", response = ExceptionMessage.class) })
    EnvironmentDTO getEnvironment(@PathParam("env_id") String envId);


    /**
     * Fetches the overrides to be applied to all consumers in the given environment. If the environment has no overrides, this endpoint returns an empty list. 
     *
     * @param environmentId The ID of the environment for which to fetch content overrides 
     * @return A list of content overrides for the specified environment 
     * @return An environment with the specified ID does not exist 
     * @return An unexpected exception has occurred
     */
    @GET
    @Path("/{environment_id}/content_overrides")
    @Produces({ "application/json" })
    @ApiOperation(value = "", notes = "Fetches the overrides to be applied to all consumers in the given environment. If the environment has no overrides, this endpoint returns an empty list. ", tags={ "environment" })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "A list of content overrides for the specified environment ", response = ContentOverrideDTO.class, responseContainer = "List"),
        @ApiResponse(code = 404, message = "An environment with the specified ID does not exist ", response = ExceptionMessage.class, responseContainer = "List"),
        @ApiResponse(code = 500, message = "An unexpected exception has occurred", response = ExceptionMessage.class, responseContainer = "List") })
    java.util.stream.Stream<ContentOverrideDTO> getEnvironmentContentOverrides(@PathParam("environment_id") @ApiParam("The ID of the environment for which to fetch content overrides ") String environmentId);


    /**
     * Promotes a Content into an Environment. This call accepts multiple content sets to promote at once, after which all affected certificates for consumers in the environment will be regenerated. Consumers registered to this environment will now receive this content in their entitlement certificates. Because the certificate regeneraiton can be quite time consuming, this is done as an asynchronous job. The content will be promoted and immediately available for new entitlements, but existing entitlements could take some time to be regenerated and sent down to clients as they check in. 
     *
     * @param envId 
     * @param contentToPromoteDTO Contents to promote
     * @param lazyRegen 
     * @return Content was successfully promoted
     * @return No such environment: test_env
     * @return The content with id test_env has already been promoted in this environment.
     * @return An unexpected exception has occurred
     */
    @POST
    @Path("/{env_id}/content")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @ApiOperation(value = "", notes = "Promotes a Content into an Environment. This call accepts multiple content sets to promote at once, after which all affected certificates for consumers in the environment will be regenerated. Consumers registered to this environment will now receive this content in their entitlement certificates. Because the certificate regeneraiton can be quite time consuming, this is done as an asynchronous job. The content will be promoted and immediately available for new entitlements, but existing entitlements could take some time to be regenerated and sent down to clients as they check in. ", tags={ "environment" })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Content was successfully promoted", response = AsyncJobStatusDTO.class),
        @ApiResponse(code = 404, message = "No such environment: test_env", response = ExceptionMessage.class),
        @ApiResponse(code = 409, message = "The content with id test_env has already been promoted in this environment.", response = ExceptionMessage.class),
        @ApiResponse(code = 500, message = "An unexpected exception has occurred", response = ExceptionMessage.class) })
    AsyncJobStatusDTO promoteContent(@PathParam("env_id") String envId,@Valid @NotNull List<ContentToPromoteDTO> contentToPromoteDTO,@QueryParam("lazy_regen") @DefaultValue("true")   Boolean lazyRegen);


    /**
     * Adds one or more new content overrides or updates existing overrides for the given environment, then returns a list containing all known, updated overrides for the environment. If the list contains multiple values for a given content override, any previous value(s) will be overwritten. 
     *
     * @param environmentId The ID of the environment in which to add or update content overrides 
     * @param contentOverrideDTO A list containing the content overrides to apply to the environment
     * @return An updated list of all known content overrides for the specified environment 
     * @return An environment with the specified ID does not exist 
     */
    @PUT
    @Path("/{environment_id}/content_overrides")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @ApiOperation(value = "", notes = "Adds one or more new content overrides or updates existing overrides for the given environment, then returns a list containing all known, updated overrides for the environment. If the list contains multiple values for a given content override, any previous value(s) will be overwritten. ", tags={ "environment" })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "An updated list of all known content overrides for the specified environment ", response = ContentOverrideDTO.class, responseContainer = "List"),
        @ApiResponse(code = 404, message = "An environment with the specified ID does not exist ", response = ExceptionMessage.class, responseContainer = "List") })
    java.util.stream.Stream<ContentOverrideDTO> putEnvironmentContentOverrides(@PathParam("environment_id") @ApiParam("The ID of the environment in which to add or update content overrides ") String environmentId,@Valid @NotNull List<ContentOverrideDTO> contentOverrideDTO);


    /**
     * Updates the specified using the non-null fields specified in the provided EnvironmentDTO. Note that some fields of an environment cannot be changed without deleting and recreating the environment -- such as its ID or organization (owner) -- and some other properties that are mutable may not be changed by this operation, such as environment content overrides. In the case of the created and updated timestamps, this operation may not directly update those fields, but the updated field may be changed as a result of other fields changing.  Ignored EnvironmentDTO fields: - id - owner - environmentContent - created - updated* 
     *
     * @param envId 
     * @param environmentDTO An EnvironmentDTO containing the data to use to update the target environment. The object need not be fully populated, as some fields cannot be changed by an update operation and will be ignored. Any field which is not intended to be updated should be left undefined or null. An empty, non-null value should be used to clear any existing value in a given field. 
     * @return The updated environment 
     * @return An environment with the specified ID does not exist 
     * @return An unexpected exception has occurred
     */
    @PUT
    @Path("/{env_id}")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @ApiOperation(value = "", notes = "Updates the specified using the non-null fields specified in the provided EnvironmentDTO. Note that some fields of an environment cannot be changed without deleting and recreating the environment -- such as its ID or organization (owner) -- and some other properties that are mutable may not be changed by this operation, such as environment content overrides. In the case of the created and updated timestamps, this operation may not directly update those fields, but the updated field may be changed as a result of other fields changing.  Ignored EnvironmentDTO fields: - id - owner - environmentContent - created - updated* ", tags={ "environment" })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "The updated environment ", response = EnvironmentDTO.class),
        @ApiResponse(code = 404, message = "An environment with the specified ID does not exist ", response = ExceptionMessage.class),
        @ApiResponse(code = 500, message = "An unexpected exception has occurred", response = ExceptionMessage.class) })
    EnvironmentDTO updateEnvironment(@PathParam("env_id") String envId,@Valid @NotNull EnvironmentDTO environmentDTO);

}
