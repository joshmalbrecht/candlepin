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

@Path("/content")
@Api(description = "the Content API")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaJAXRSSpecServerCodegen", date = "2025-03-28T14:05:44.436523329-04:00[America/Detroit]", comments = "Generator version: 7.12.0")
public interface ContentApi {

    /**
     * Retrieves a single Content
     *
     * @param contentUuid 
     * @return Content successfully retrieved
     * @return Content with the given UUID was not found
     * @return An unexpected exception has occurred
     */
    @GET
    @Path("/{content_uuid}")
    @Produces({ "application/json" })
    @ApiOperation(value = "", notes = "Retrieves a single Content", tags={ "content" })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Content successfully retrieved", response = ContentDTO.class),
        @ApiResponse(code = 404, message = "Content with the given UUID was not found", response = ExceptionMessage.class),
        @ApiResponse(code = 500, message = "An unexpected exception has occurred", response = ExceptionMessage.class) })
    ContentDTO getContentByUuid(@PathParam("content_uuid") String contentUuid);


    /**
     * Retrieves list of Content
     *
     * @param owner The key of an owner to use to limit the content search. If defined, the list of contents returned by this endpoint will only include those available to the given owner. May be specified multiple times to filter by multiple owners. If multiple owners are provided, contents available to any of the provided owners will be returned. 
     * @param content The ID of a content to fetch. If defined, the list of contents returned by this method will only include those matching the given ID. May be specified multiple times to filter on multiple content IDs. 
     * @param label The labels of content to fetch. If defined, the list of content returned by this endpoint will only include those matching the given labels (case-insensitive). May be specified multiple times to filter on multiple labels. If multiple labels are provided, any content matching any of the provided labels will be returned. 
     * @param active A value indicating how &#39;active&#39; content should be considered when fetching content, where &#39;active&#39; is defined as a content that is currently in use by a subscription with a start time in the past and that has not yet expired, or in use by a product which itself is considered &#39;active.&#39; Must be one of &#39;include&#39;, &#39;exclude&#39;, or &#39;exclusive&#39; indicating that active content should be included along with inactive content, excluded (omitted) from the results, or exclusively selected as the only content to return. Defaults to &#39;exclusive&#39;. 
     * @param custom A value indicating how custom content are considered when fetching content, where &#39;custom&#39; is defined as content that did not originate from a refresh operation nor manifest import. Must be one of &#39;include&#39;, &#39;exclude&#39;, or &#39;exclusive&#39; indicating that custom content should be passively included, excluded or omitted from the output, or exclusively selected as the only content to return. 
     * @return Content successfully listed
     * @return An unexpected exception has occurred
     */
    @GET
    @Produces({ "application/json" })
    @ApiOperation(value = "", notes = "Retrieves list of Content", tags={ "content" })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Content successfully listed", response = ContentDTO.class, responseContainer = "List"),
        @ApiResponse(code = 500, message = "An unexpected exception has occurred", response = ExceptionMessage.class, responseContainer = "List") })
    java.util.stream.Stream<ContentDTO> getContents(@QueryParam("owner")  @ApiParam("The key of an owner to use to limit the content search. If defined, the list of contents returned by this endpoint will only include those available to the given owner. May be specified multiple times to filter by multiple owners. If multiple owners are provided, contents available to any of the provided owners will be returned. ")  List<String> owner,@QueryParam("content")  @ApiParam("The ID of a content to fetch. If defined, the list of contents returned by this method will only include those matching the given ID. May be specified multiple times to filter on multiple content IDs. ")  List<String> content,@QueryParam("label")  @ApiParam("The labels of content to fetch. If defined, the list of content returned by this endpoint will only include those matching the given labels (case-insensitive). May be specified multiple times to filter on multiple labels. If multiple labels are provided, any content matching any of the provided labels will be returned. ")  List<String> label,@QueryParam("active") @DefaultValue("exclusive")  @ApiParam("A value indicating how &#39;active&#39; content should be considered when fetching content, where &#39;active&#39; is defined as a content that is currently in use by a subscription with a start time in the past and that has not yet expired, or in use by a product which itself is considered &#39;active.&#39; Must be one of &#39;include&#39;, &#39;exclude&#39;, or &#39;exclusive&#39; indicating that active content should be included along with inactive content, excluded (omitted) from the results, or exclusively selected as the only content to return. Defaults to &#39;exclusive&#39;. ")  String active,@QueryParam("custom") @DefaultValue("include")  @ApiParam("A value indicating how custom content are considered when fetching content, where &#39;custom&#39; is defined as content that did not originate from a refresh operation nor manifest import. Must be one of &#39;include&#39;, &#39;exclude&#39;, or &#39;exclusive&#39; indicating that custom content should be passively included, excluded or omitted from the output, or exclusively selected as the only content to return. ")  String custom);

}
