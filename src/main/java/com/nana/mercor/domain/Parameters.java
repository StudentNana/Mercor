package com.nana.mercor.domain;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "article",
        "brand",
        "count",
        "package",
        "spezialization",
        "articleId"
})
public class Parameters {

    @JsonProperty("article")
    private String article;
    @JsonProperty("brand")
    private String brand;
    @JsonProperty("count")
    private String count;
    @JsonProperty("package")
    private String _package;
    @JsonProperty("spezialization")
    private String spezialization;
    @JsonProperty("articleId")
    private String articleId;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("article")
    public String getArticle() {
        return article;
    }

    @JsonProperty("article")
    public void setArticle(String article) {
        this.article = article;
    }

    @JsonProperty("brand")
    public String getBrand() {
        return brand;
    }

    @JsonProperty("brand")
    public void setBrand(String brand) {
        this.brand = brand;
    }

    @JsonProperty("count")
    public String getCount() {
        return count;
    }

    @JsonProperty("count")
    public void setCount(String count) {
        this.count = count;
    }

    @JsonProperty("package")
    public String getPackage() {
        return _package;
    }

    @JsonProperty("package")
    public void setPackage(String _package) {
        this._package = _package;
    }

    @JsonProperty("spezialization")
    public String getSpezialization() {
        return spezialization;
    }

    @JsonProperty("spezialization")
    public void setSpezialization(String spezialization) {
        this.spezialization = spezialization;
    }

    @JsonProperty("articleId")
    public String getArticleId() {
        return articleId;
    }

    @JsonProperty("articleId")
    public void setArticleId(String articleId) {
        this.articleId = articleId;
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