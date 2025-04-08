package org.candlepin.api.v1;

import org.candlepin.dto.api.server.v1.ContentDTO;
import org.candlepin.dto.api.server.v1.ExceptionMessage;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

import io.swagger.annotations.*;

import org.jboss.resteasy.plugins.providers.multipart.MultipartInput;
import java.util.Map;
import java.util.List;
import javax.validation.constraints.*;
import javax.validation.Valid;

@Path("/owners/{owner_key}/content")
@Api(description = "the OwnerContent API")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaJAXRSSpecServerCodegen", date = "2025-03-28T14:05:44.436523329-04:00[America/Detroit]", comments = "Generator version: 7.12.0")
public interface OwnerContentApi {

    /**
     * Creates a content for owner
     *
     * @param ownerKey The key of the owner
     * @param contentDTO Content to be created
     * @return Created a content
     * @return Invalid inputs, Content cannot be created
     * @return An unexpected exception has occurred
     */
    @POST
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @ApiOperation(value = "", notes = "Creates a content for owner", tags={ "owner_content" })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Created a content", response = ContentDTO.class),
        @ApiResponse(code = 400, message = "Invalid inputs, Content cannot be created", response = ExceptionMessage.class),
        @ApiResponse(code = 500, message = "An unexpected exception has occurred", response = ExceptionMessage.class) })
    ContentDTO createContent(@PathParam("owner_key") @ApiParam("The key of the owner") String ownerKey,@Valid @NotNull ContentDTO contentDTO);


    /**
     * Creates Contents in bulk
     *
     * @param ownerKey key of the owner whose content has to be created
     * @param contentDTO List of contents to be created
     * @return Contents created
     * @return Invalid inputs, could not create content
     * @return An unexpected exception has occurred
     */
    @POST
    @Path("/batch")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @ApiOperation(value = "", notes = "Creates Contents in bulk", tags={ "owner_content" })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Contents created", response = ContentDTO.class, responseContainer = "List"),
        @ApiResponse(code = 400, message = "Invalid inputs, could not create content", response = ExceptionMessage.class, responseContainer = "List"),
        @ApiResponse(code = 500, message = "An unexpected exception has occurred", response = ExceptionMessage.class, responseContainer = "List") })
    java.util.stream.Stream<ContentDTO> createContentBatch(@PathParam("owner_key") @ApiParam("key of the owner whose content has to be created") String ownerKey,@Valid @NotNull List<ContentDTO> contentDTO);


    /**
     * Retrieves a single content
     *
     * @param ownerKey The key of the owner whose content has to be retrieved
     * @param contentId The id of the content to be retrieved
     * @return Content
     * @return Owner Key or ID of the Content is null, empty or could not be found
     * @return An unexpected exception has occurred
     */
    @GET
    @Path("/{content_id}")
    @Produces({ "application/json" })
    @ApiOperation(value = "", notes = "Retrieves a single content", tags={ "owner_content" })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Content", response = ContentDTO.class),
        @ApiResponse(code = 400, message = "Owner Key or ID of the Content is null, empty or could not be found", response = ExceptionMessage.class),
        @ApiResponse(code = 500, message = "An unexpected exception has occurred", response = ExceptionMessage.class) })
    ContentDTO getContentById(@PathParam("owner_key") @ApiParam("The key of the owner whose content has to be retrieved") String ownerKey,@PathParam("content_id") @ApiParam("The id of the content to be retrieved") String contentId);


    /**
     * Retrieves a list of contents within the given organization's namespace, optionally filtered by a list of content IDs. 
     *
     * @param ownerKey Owner key
     * @param content The ID of a content to fetch. If defined, the list of contents returned by this method will only include those matching the given ID. May be specified multiple times to filter on multiple content IDs. 
     * @param label The labels of content to fetch. If defined, the list of content returned by this endpoint will only include those matching the given labels (case-insensitive). May be specified multiple times to filter on multiple labels. If multiple labels are provided, any content matching any of the provided labels will be returned. 
     * @param active A value indicating how &#39;active&#39; content should be considered when fetching content, where &#39;active&#39; is defined as a content that is currently in use by a subscription with a start time in the past and that has not yet expired, or in use by a product which itself is considered &#39;active.&#39; Must be one of &#39;include&#39;, &#39;exclude&#39;, or &#39;exclusive&#39; indicating that active content should be included along with inactive content, excluded (omitted) from the results, or exclusively selected as the only content to return. Defaults to &#39;exclusive&#39;. 
     * @param custom A value indicating how custom content are considered when fetching content, where &#39;custom&#39; is defined as content that did not originate from a refresh operation nor manifest import. Must be one of &#39;include&#39;, &#39;exclude&#39;, or &#39;exclusive&#39; indicating that custom content should be passively included, excluded or omitted from the output, or exclusively selected as the only content to return. 
     * @return Content successfully listed
     * @return Owner key is null, empty or could not be found
     * @return An unexpected exception has occurred
     */
    @GET
    @Produces({ "application/json" })
    @ApiOperation(value = "", notes = "Retrieves a list of contents within the given organization's namespace, optionally filtered by a list of content IDs. ", tags={ "owner_content" })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Content successfully listed", response = ContentDTO.class, responseContainer = "List"),
        @ApiResponse(code = 404, message = "Owner key is null, empty or could not be found", response = ExceptionMessage.class, responseContainer = "List"),
        @ApiResponse(code = 500, message = "An unexpected exception has occurred", response = ExceptionMessage.class, responseContainer = "List") })
    java.util.stream.Stream<ContentDTO> getContentsByOwner(@PathParam("owner_key") @ApiParam("Owner key") String ownerKey,@QueryParam("content")  @ApiParam("The ID of a content to fetch. If defined, the list of contents returned by this method will only include those matching the given ID. May be specified multiple times to filter on multiple content IDs. ")  List<String> content,@QueryParam("label")  @ApiParam("The labels of content to fetch. If defined, the list of content returned by this endpoint will only include those matching the given labels (case-insensitive). May be specified multiple times to filter on multiple labels. If multiple labels are provided, any content matching any of the provided labels will be returned. ")  List<String> label,@QueryParam("active") @DefaultValue("exclusive")  @ApiParam("A value indicating how &#39;active&#39; content should be considered when fetching content, where &#39;active&#39; is defined as a content that is currently in use by a subscription with a start time in the past and that has not yet expired, or in use by a product which itself is considered &#39;active.&#39; Must be one of &#39;include&#39;, &#39;exclude&#39;, or &#39;exclusive&#39; indicating that active content should be included along with inactive content, excluded (omitted) from the results, or exclusively selected as the only content to return. Defaults to &#39;exclusive&#39;. ")  String active,@QueryParam("custom") @DefaultValue("include")  @ApiParam("A value indicating how custom content are considered when fetching content, where &#39;custom&#39; is defined as content that did not originate from a refresh operation nor manifest import. Must be one of &#39;include&#39;, &#39;exclude&#39;, or &#39;exclusive&#39; indicating that custom content should be passively included, excluded or omitted from the output, or exclusively selected as the only content to return. ")  String custom);


    /**
     * Deletes a Content
     *
     * @param ownerKey The key of the owner whose content has to be deleted
     * @param contentId The ID of the content to be deleted
     * @return A successful operation
     * @return Invalid inputs, content could not be deleted
     * @return An unexpected exception has occurred
     */
    @DELETE
    @Path("/{content_id}")
    @Produces({ "application/json" })
    @ApiOperation(value = "", notes = "Deletes a Content", tags={ "owner_content" })
    @ApiResponses(value = { 
        @ApiResponse(code = 204, message = "A successful operation", response = Void.class),
        @ApiResponse(code = 400, message = "Invalid inputs, content could not be deleted", response = ExceptionMessage.class),
        @ApiResponse(code = 500, message = "An unexpected exception has occurred", response = ExceptionMessage.class) })
    void removeContent(@PathParam("owner_key") @ApiParam("The key of the owner whose content has to be deleted") String ownerKey,@PathParam("content_id") @ApiParam("The ID of the content to be deleted") String contentId);


    /**
     * Updates a Content
     *
     * @param ownerKey The key of the owner to be updated
     * @param contentId The ID of the Content to be updated
     * @param contentDTO Content to be updated
     * @return Updated content
     * @return Invalid inputs, could not update content
     * @return An unexpected exception has occurred
     */
    @PUT
    @Path("/{content_id}")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @ApiOperation(value = "", notes = "Updates a Content", tags={ "owner_content" })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Updated content", response = ContentDTO.class),
        @ApiResponse(code = 400, message = "Invalid inputs, could not update content", response = ExceptionMessage.class),
        @ApiResponse(code = 500, message = "An unexpected exception has occurred", response = ExceptionMessage.class) })
    ContentDTO updateContent(@PathParam("owner_key") @ApiParam("The key of the owner to be updated") String ownerKey,@PathParam("content_id") @ApiParam("The ID of the Content to be updated") String contentId,@Valid @NotNull ContentDTO contentDTO);

}
