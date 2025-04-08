package org.candlepin.api.v1;

import org.candlepin.dto.api.server.v1.AsyncJobStatusDTO;
import org.candlepin.dto.api.server.v1.ExceptionMessage;
import org.candlepin.dto.api.server.v1.ProductDTO;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

import io.swagger.annotations.*;

import org.jboss.resteasy.plugins.providers.multipart.MultipartInput;
import java.util.Map;
import java.util.List;
import javax.validation.constraints.*;
import javax.validation.Valid;

@Path("/products")
@Api(description = "the Products API")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaJAXRSSpecServerCodegen", date = "2025-03-28T14:05:44.436523329-04:00[America/Detroit]", comments = "Generator version: 7.12.0")
public interface ProductsApi {

    /**
     * Retrieves a single Product
     *
     * @param productUuid The product UUID
     * @return A single product
     * @return Product not found
     * @return An unexpected exception has occurred
     */
    @GET
    @Path("/{product_uuid}")
    @Produces({ "application/json" })
    @ApiOperation(value = "", notes = "Retrieves a single Product", tags={ "products" })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "A single product", response = ProductDTO.class),
        @ApiResponse(code = 404, message = "Product not found", response = ExceptionMessage.class),
        @ApiResponse(code = 500, message = "An unexpected exception has occurred", response = ExceptionMessage.class) })
    ProductDTO getProductByUuid(@PathParam("product_uuid") @ApiParam("The product UUID") String productUuid);


    /**
     * Retrieves list containing all known products
     *
     * @param owner The key of an owner to use to limit the product search. If defined, the list of products returned by this endpoint will only include those available to the given owner. May be specified multiple times to filter by multiple owners. If multiple owners are provided, products available to any of the provided owners will be returned. 
     * @param product The ID of a product to fetch. If defined, the list of products returned by this endpoint will only include those matching the given ID. May be specified multiple times to filter on multiple product IDs. If multiple IDs are provided, any products matching any of the provided IDs will be returned. 
     * @param name The names of products to fetch. If defined, the list of products returned by this endpoint will only include those matching the given names (case-insensitive). May be specified multiple times to filter on multiple names. If multiple names are provided, any products matching any of the provided names will be returned. 
     * @param active A value indicating how &#39;active&#39; products should be considered when fetching products, where &#39;active&#39; is defined as a product that is currently in use by a subscription with a start time in the past and that has not yet expired, or in use by a product which itself is considered &#39;active.&#39; Must be one of &#39;include&#39;, &#39;exclude&#39;, or &#39;exclusive&#39; indicating that active products should be included along with inactive products, excluded (omitted) from the results, or exclusively selected as the only products to return. Defaults to &#39;exclusive&#39;. 
     * @param custom A value indicating how custom products are considered when fetching products, where &#39;custom&#39; is defined as a product that did not originate from a refresh operation nor manifest import. Must be one of &#39;include&#39;, &#39;exclude&#39;, or &#39;exclusive&#39; indicating that custom products should be passively included, excluded or omitted from the output, or exclusively selected as the only products to return. 
     * @return product list successfully returned
     * @return An unexpected exception has occurred
     */
    @GET
    @Produces({ "application/json" })
    @ApiOperation(value = "", notes = "Retrieves list containing all known products", tags={ "products" })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "product list successfully returned", response = ProductDTO.class, responseContainer = "List"),
        @ApiResponse(code = 500, message = "An unexpected exception has occurred", response = ExceptionMessage.class, responseContainer = "List") })
    java.util.stream.Stream<ProductDTO> getProducts(@QueryParam("owner")  @ApiParam("The key of an owner to use to limit the product search. If defined, the list of products returned by this endpoint will only include those available to the given owner. May be specified multiple times to filter by multiple owners. If multiple owners are provided, products available to any of the provided owners will be returned. ")  List<String> owner,@QueryParam("product")  @ApiParam("The ID of a product to fetch. If defined, the list of products returned by this endpoint will only include those matching the given ID. May be specified multiple times to filter on multiple product IDs. If multiple IDs are provided, any products matching any of the provided IDs will be returned. ")  List<String> product,@QueryParam("name")  @ApiParam("The names of products to fetch. If defined, the list of products returned by this endpoint will only include those matching the given names (case-insensitive). May be specified multiple times to filter on multiple names. If multiple names are provided, any products matching any of the provided names will be returned. ")  List<String> name,@QueryParam("active") @DefaultValue("exclusive")  @ApiParam("A value indicating how &#39;active&#39; products should be considered when fetching products, where &#39;active&#39; is defined as a product that is currently in use by a subscription with a start time in the past and that has not yet expired, or in use by a product which itself is considered &#39;active.&#39; Must be one of &#39;include&#39;, &#39;exclude&#39;, or &#39;exclusive&#39; indicating that active products should be included along with inactive products, excluded (omitted) from the results, or exclusively selected as the only products to return. Defaults to &#39;exclusive&#39;. ")  String active,@QueryParam("custom") @DefaultValue("include")  @ApiParam("A value indicating how custom products are considered when fetching products, where &#39;custom&#39; is defined as a product that did not originate from a refresh operation nor manifest import. Must be one of &#39;include&#39;, &#39;exclude&#39;, or &#39;exclusive&#39; indicating that custom products should be passively included, excluded or omitted from the output, or exclusively selected as the only products to return. ")  String custom);


    /**
     * Refreshes Pools by Product
     *
     * @param product Multiple product Ids
     * @param lazyRegen Regenerate certificates immediatelly or allow them to be regenerated on demand
     * @return A list of statuses of the created refresh pools jobs
     * @return No product IDs specified
     * @return An unexpected exception has occurred
     */
    @PUT
    @Path("/subscriptions")
    @Produces({ "application/json" })
    @ApiOperation(value = "", notes = "Refreshes Pools by Product", tags={ "products" })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "A list of statuses of the created refresh pools jobs", response = AsyncJobStatusDTO.class, responseContainer = "List"),
        @ApiResponse(code = 400, message = "No product IDs specified", response = ExceptionMessage.class, responseContainer = "List"),
        @ApiResponse(code = 500, message = "An unexpected exception has occurred", response = ExceptionMessage.class, responseContainer = "List") })
    java.util.stream.Stream<AsyncJobStatusDTO> refreshPoolsForProducts(@QueryParam("product") @NotNull  @ApiParam("Multiple product Ids")  List<String> product,@QueryParam("lazy_regen") @DefaultValue("true")  @ApiParam("Regenerate certificates immediatelly or allow them to be regenerated on demand")  Boolean lazyRegen);

}
