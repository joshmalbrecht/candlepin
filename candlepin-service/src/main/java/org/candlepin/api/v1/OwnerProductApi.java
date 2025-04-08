package org.candlepin.api.v1;

import org.candlepin.dto.api.server.v1.AsyncJobStatusDTO;
import org.candlepin.dto.api.server.v1.ExceptionMessage;
import java.util.Map;
import org.candlepin.dto.api.server.v1.ProductCertificateDTO;
import org.candlepin.dto.api.server.v1.ProductDTO;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

import io.swagger.annotations.*;

import org.jboss.resteasy.plugins.providers.multipart.MultipartInput;
import java.util.Map;
import java.util.List;
import javax.validation.constraints.*;
import javax.validation.Valid;

@Path("/owners/{owner_key}/products")
@Api(description = "the OwnerProduct API")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaJAXRSSpecServerCodegen", date = "2025-03-28T14:05:44.436523329-04:00[America/Detroit]", comments = "Generator version: 7.12.0")
public interface OwnerProductApi {

    /**
     * Adds content to a product
     *
     * @param ownerKey Owner key
     * @param productId Product ID
     * @param contentId Content ID
     * @param enabled Content enabled flag
     * @return Content added to a product
     * @return Invalid inputs, unable to add content
     * @return Owner key could not be found
     * @return An unexpected exception has occurred
     */
    @POST
    @Path("/{product_id}/content/{content_id}")
    @Produces({ "application/json" })
    @ApiOperation(value = "", notes = "Adds content to a product", tags={ "owner_product" })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Content added to a product", response = ProductDTO.class),
        @ApiResponse(code = 400, message = "Invalid inputs, unable to add content", response = ExceptionMessage.class),
        @ApiResponse(code = 404, message = "Owner key could not be found", response = ExceptionMessage.class),
        @ApiResponse(code = 500, message = "An unexpected exception has occurred", response = ExceptionMessage.class) })
    ProductDTO addContentToProduct(@PathParam("owner_key") @ApiParam("Owner key") String ownerKey,@PathParam("product_id") @ApiParam("Product ID") String productId,@PathParam("content_id") @ApiParam("Content ID") String contentId,@QueryParam("enabled") @NotNull  @ApiParam("Content enabled flag")  Boolean enabled);


    /**
     * Adds content to a product in bulk
     *
     * @param ownerKey Owner key
     * @param productId Product ID
     * @param requestBody Content map
     * @return Content added to a product
     * @return Invalid inputs, unable to add content
     * @return Owner key could not be found
     * @return An unexpected exception has occurred
     */
    @POST
    @Path("/{product_id}/batch_content")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @ApiOperation(value = "", notes = "Adds content to a product in bulk", tags={ "owner_product" })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Content added to a product", response = ProductDTO.class),
        @ApiResponse(code = 400, message = "Invalid inputs, unable to add content", response = ExceptionMessage.class),
        @ApiResponse(code = 404, message = "Owner key could not be found", response = ExceptionMessage.class),
        @ApiResponse(code = 500, message = "An unexpected exception has occurred", response = ExceptionMessage.class) })
    ProductDTO addContentsToProduct(@PathParam("owner_key") @ApiParam("Owner key") String ownerKey,@PathParam("product_id") @ApiParam("Product ID") String productId,@Valid @NotNull Map<String, Boolean> requestBody);


    /**
     * Creates a product for an owner
     *
     * @param ownerKey Owner key
     * @param productDTO A product to be created
     * @return Product successfully created.
     * @return Invalid inputs, unable to create product
     * @return An unexpected exception has occurred
     */
    @POST
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @ApiOperation(value = "", notes = "Creates a product for an owner", tags={ "owner_product" })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Product successfully created.", response = ProductDTO.class),
        @ApiResponse(code = 400, message = "Invalid inputs, unable to create product", response = ExceptionMessage.class),
        @ApiResponse(code = 500, message = "An unexpected exception has occurred", response = ExceptionMessage.class) })
    ProductDTO createProduct(@PathParam("owner_key") @ApiParam("Owner key") String ownerKey,@Valid @NotNull ProductDTO productDTO);


    /**
     * Retrieves a single product for an owner
     *
     * @param ownerKey Owner key
     * @param productId Product ID
     * @return Retrieves a single product for an owner
     * @return Owner key or product ID could not be found
     * @return An unexpected exception has occurred
     */
    @GET
    @Path("/{product_id}")
    @Produces({ "application/json" })
    @ApiOperation(value = "", notes = "Retrieves a single product for an owner", tags={ "owner_product" })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Retrieves a single product for an owner", response = ProductDTO.class),
        @ApiResponse(code = 404, message = "Owner key or product ID could not be found", response = ExceptionMessage.class),
        @ApiResponse(code = 500, message = "An unexpected exception has occurred", response = ExceptionMessage.class) })
    ProductDTO getProductById(@PathParam("owner_key") @ApiParam("Owner key") String ownerKey,@PathParam("product_id") @ApiParam("Product ID") String productId);


    /**
     * Retrieves a product certificate for an owner
     *
     * @param ownerKey Owner key
     * @param productId Product ID
     * @return Retrieves a product certificate for an owner
     * @return Only numeric product IDs are allowed
     * @return Owner key or product ID could not be found
     * @return An unexpected exception has occurred
     */
    @GET
    @Path("/{product_id}/certificate")
    @Produces({ "application/json" })
    @ApiOperation(value = "", notes = "Retrieves a product certificate for an owner", tags={ "owner_product" })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Retrieves a product certificate for an owner", response = ProductCertificateDTO.class),
        @ApiResponse(code = 400, message = "Only numeric product IDs are allowed", response = ExceptionMessage.class),
        @ApiResponse(code = 404, message = "Owner key or product ID could not be found", response = ExceptionMessage.class),
        @ApiResponse(code = 500, message = "An unexpected exception has occurred", response = ExceptionMessage.class) })
    ProductCertificateDTO getProductCertificateById(@PathParam("owner_key") @ApiParam("Owner key") String ownerKey,@PathParam("product_id") @ApiParam("Product ID") String productId);


    /**
     * Retrieves a list of products within the given organization's namespace, optionally filtered by a list of product IDs. 
     *
     * @param ownerKey Owner key
     * @param product The ID of a product to fetch. If defined, the list of products returned by this endpoint will only include those matching the given ID. May be specified multiple times to filter on multiple product IDs. If multiple IDs are provided, any products matching any of the provided IDs will be returned. 
     * @param name The names of products to fetch. If defined, the list of products returned by this endpoint will only include those matching the given names (case-insensitive). May be specified multiple times to filter on multiple names. If multiple names are provided, any products matching any of the provided names will be returned. 
     * @param active A value indicating how &#39;active&#39; products should be considered when fetching products, where &#39;active&#39; is defined as a product that is currently in use by a subscription with a start time in the past and that has not yet expired, or in use by a product which itself is considered &#39;active.&#39; Must be one of &#39;include&#39;, &#39;exclude&#39;, or &#39;exclusive&#39; indicating that active products should be included along with inactive products, excluded (omitted) from the results, or exclusively selected as the only products to return. Defaults to &#39;exclusive&#39;. 
     * @param custom A value indicating how custom products are considered when fetching products, where &#39;custom&#39; is defined as a product that did not originate from a refresh operation nor manifest import. Must be one of &#39;include&#39;, &#39;exclude&#39;, or &#39;exclusive&#39; indicating that custom products should be passively included, excluded or omitted from the output, or exclusively selected as the only products to return. 
     * @return Retrieves a list of products by owner
     * @return Owner key could not be found
     * @return An unexpected exception has occurred
     */
    @GET
    @Produces({ "application/json" })
    @ApiOperation(value = "", notes = "Retrieves a list of products within the given organization's namespace, optionally filtered by a list of product IDs. ", tags={ "owner_product" })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Retrieves a list of products by owner", response = ProductDTO.class, responseContainer = "List"),
        @ApiResponse(code = 404, message = "Owner key could not be found", response = ExceptionMessage.class, responseContainer = "List"),
        @ApiResponse(code = 500, message = "An unexpected exception has occurred", response = ExceptionMessage.class, responseContainer = "List") })
    java.util.stream.Stream<ProductDTO> getProductsByOwner(@PathParam("owner_key") @ApiParam("Owner key") String ownerKey,@QueryParam("product")  @ApiParam("The ID of a product to fetch. If defined, the list of products returned by this endpoint will only include those matching the given ID. May be specified multiple times to filter on multiple product IDs. If multiple IDs are provided, any products matching any of the provided IDs will be returned. ")  List<String> product,@QueryParam("name")  @ApiParam("The names of products to fetch. If defined, the list of products returned by this endpoint will only include those matching the given names (case-insensitive). May be specified multiple times to filter on multiple names. If multiple names are provided, any products matching any of the provided names will be returned. ")  List<String> name,@QueryParam("active") @DefaultValue("exclusive")  @ApiParam("A value indicating how &#39;active&#39; products should be considered when fetching products, where &#39;active&#39; is defined as a product that is currently in use by a subscription with a start time in the past and that has not yet expired, or in use by a product which itself is considered &#39;active.&#39; Must be one of &#39;include&#39;, &#39;exclude&#39;, or &#39;exclusive&#39; indicating that active products should be included along with inactive products, excluded (omitted) from the results, or exclusively selected as the only products to return. Defaults to &#39;exclusive&#39;. ")  String active,@QueryParam("custom") @DefaultValue("include")  @ApiParam("A value indicating how custom products are considered when fetching products, where &#39;custom&#39; is defined as a product that did not originate from a refresh operation nor manifest import. Must be one of &#39;include&#39;, &#39;exclude&#39;, or &#39;exclusive&#39; indicating that custom products should be passively included, excluded or omitted from the output, or exclusively selected as the only products to return. ")  String custom);


    /**
     * Refreshes Pools by Product
     *
     * @param ownerKey Owner key
     * @param productId Product ID
     * @param lazyRegen Regenerate certificates immediatelly or allow them to be regenerated on demand
     * @return A successful operation
     * @return Unable to fulfill refresh pools request
     * @return Owner key or product ID could not be found
     * @return An unexpected exception has occurred
     */
    @PUT
    @Path("/{product_id}/subscriptions")
    @Produces({ "application/json" })
    @ApiOperation(value = "", notes = "Refreshes Pools by Product", tags={ "owner_product" })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "A successful operation", response = AsyncJobStatusDTO.class),
        @ApiResponse(code = 400, message = "Unable to fulfill refresh pools request", response = ExceptionMessage.class),
        @ApiResponse(code = 404, message = "Owner key or product ID could not be found", response = ExceptionMessage.class),
        @ApiResponse(code = 500, message = "An unexpected exception has occurred", response = ExceptionMessage.class) })
    AsyncJobStatusDTO refreshPoolsForProduct(@PathParam("owner_key") @ApiParam("Owner key") String ownerKey,@PathParam("product_id") @ApiParam("Product ID") String productId,@QueryParam("lazy_regen") @NotNull @DefaultValue("true")  @ApiParam("Regenerate certificates immediatelly or allow them to be regenerated on demand")  Boolean lazyRegen);


    /**
     * Removes a single Content from a product
     *
     * @param ownerKey Owner key
     * @param productId Product ID
     * @param contentId Content ID
     * @return Content removed from a product
     * @return Invalid inputs, unable to remove content
     * @return Owner key could not be found
     * @return An unexpected exception has occurred
     */
    @DELETE
    @Path("/{product_id}/content/{content_id}")
    @Produces({ "application/json" })
    @ApiOperation(value = "", notes = "Removes a single Content from a product", tags={ "owner_product" })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Content removed from a product", response = ProductDTO.class),
        @ApiResponse(code = 400, message = "Invalid inputs, unable to remove content", response = ExceptionMessage.class),
        @ApiResponse(code = 404, message = "Owner key could not be found", response = ExceptionMessage.class),
        @ApiResponse(code = 500, message = "An unexpected exception has occurred", response = ExceptionMessage.class) })
    ProductDTO removeContentFromProduct(@PathParam("owner_key") @ApiParam("Owner key") String ownerKey,@PathParam("product_id") @ApiParam("Product ID") String productId,@PathParam("content_id") @ApiParam("Content ID") String contentId);


    /**
     * Removes content from a product in batch
     *
     * @param ownerKey Owner key
     * @param productId Product ID
     * @param requestBody Content IDs
     * @return Content removed from a product
     * @return Invalid inputs, unable to remove content
     * @return Owner key could not be found
     * @return An unexpected exception has occurred
     */
    @DELETE
    @Path("/{product_id}/batch_content")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @ApiOperation(value = "", notes = "Removes content from a product in batch", tags={ "owner_product" })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Content removed from a product", response = ProductDTO.class),
        @ApiResponse(code = 400, message = "Invalid inputs, unable to remove content", response = ExceptionMessage.class),
        @ApiResponse(code = 404, message = "Owner key could not be found", response = ExceptionMessage.class),
        @ApiResponse(code = 500, message = "An unexpected exception has occurred", response = ExceptionMessage.class) })
    ProductDTO removeContentsFromProduct(@PathParam("owner_key") @ApiParam("Owner key") String ownerKey,@PathParam("product_id") @ApiParam("Product ID") String productId,@Valid @NotNull List<String> requestBody);


    /**
     * Removes a product for an owner
     *
     * @param ownerKey Owner key
     * @param productId Product ID
     * @return Product successfully deleted.
     * @return Product cannot be deleted while subscriptions exists
     * @return Owner key or product ID could not be found
     * @return An unexpected exception has occurred
     */
    @DELETE
    @Path("/{product_id}")
    @Produces({ "application/json" })
    @ApiOperation(value = "", notes = "Removes a product for an owner", tags={ "owner_product" })
    @ApiResponses(value = { 
        @ApiResponse(code = 204, message = "Product successfully deleted.", response = Void.class),
        @ApiResponse(code = 400, message = "Product cannot be deleted while subscriptions exists", response = ExceptionMessage.class),
        @ApiResponse(code = 404, message = "Owner key or product ID could not be found", response = ExceptionMessage.class),
        @ApiResponse(code = 500, message = "An unexpected exception has occurred", response = ExceptionMessage.class) })
    void removeProduct(@PathParam("owner_key") @ApiParam("Owner key") String ownerKey,@PathParam("product_id") @ApiParam("Product ID") String productId);


    /**
     * Updates a product for an owner
     *
     * @param ownerKey Owner key
     * @param productId Product ID
     * @param productDTO Product to be updated
     * @return A successful operation
     * @return Invalid inputs, unable to update product
     * @return Owner key could not be found
     * @return An unexpected exception has occurred
     */
    @PUT
    @Path("/{product_id}")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @ApiOperation(value = "", notes = "Updates a product for an owner", tags={ "owner_product" })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "A successful operation", response = ProductDTO.class),
        @ApiResponse(code = 400, message = "Invalid inputs, unable to update product", response = ExceptionMessage.class),
        @ApiResponse(code = 404, message = "Owner key could not be found", response = ExceptionMessage.class),
        @ApiResponse(code = 500, message = "An unexpected exception has occurred", response = ExceptionMessage.class) })
    ProductDTO updateProduct(@PathParam("owner_key") @ApiParam("Owner key") String ownerKey,@PathParam("product_id") @ApiParam("Product ID") String productId,@Valid @NotNull ProductDTO productDTO);

}
