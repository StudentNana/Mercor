package com.nana.mercor.bringmeister;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "totalProductsCount",
        "products",
        "facets",
        "searchTerm",
        "totalCategoriesCount"
})
public class SearchResponse {

    @JsonProperty("totalProductsCount")
    private Integer totalProductsCount;
    @JsonProperty("products")
    private List<Product> products = null;
    @JsonProperty("facets")
    private Facets facets;
    @JsonProperty("searchTerm")
    private String searchTerm;
    @JsonProperty("totalCategoriesCount")
    private Integer totalCategoriesCount;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("totalProductsCount")
    public Integer getTotalProductsCount() {
        return totalProductsCount;
    }

    @JsonProperty("totalProductsCount")
    public void setTotalProductsCount(Integer totalProductsCount) {
        this.totalProductsCount = totalProductsCount;
    }

    @JsonProperty("products")
    public List<Product> getProducts() {
        return products;
    }

    @JsonProperty("products")
    public void setProducts(List<Product> products) {
        this.products = products;
    }

    @JsonProperty("facets")
    public Facets getFacets() {
        return facets;
    }

    @JsonProperty("facets")
    public void setFacets(Facets facets) {
        this.facets = facets;
    }

    @JsonProperty("searchTerm")
    public String getSearchTerm() {
        return searchTerm;
    }

    @JsonProperty("searchTerm")
    public void setSearchTerm(String searchTerm) {
        this.searchTerm = searchTerm;
    }

    @JsonProperty("totalCategoriesCount")
    public Integer getTotalCategoriesCount() {
        return totalCategoriesCount;
    }

    @JsonProperty("totalCategoriesCount")
    public void setTotalCategoriesCount(Integer totalCategoriesCount) {
        this.totalCategoriesCount = totalCategoriesCount;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}