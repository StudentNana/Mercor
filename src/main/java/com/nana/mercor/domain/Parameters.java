
package com.nana.mercor.domain;

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
        "article",
        "count",
        "date",
        "package"
})
public class Parameters {

    @JsonProperty("article")
    private List<String> article = null;
    @JsonProperty("count")
    private List<String> count = null;
    @JsonProperty("date")
    private String date;
    @JsonProperty("package")
    private List<String> _package = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("article")
    public List<String> getArticle() {
        return article;
    }

    @JsonProperty("article")
    public void setArticle(List<String> article) {
        this.article = article;
    }

    @JsonProperty("count")
    public List<String> getCount() {
        return count;
    }

    @JsonProperty("count")
    public void setCount(List<String> count) {
        this.count = count;
    }

    @JsonProperty("date")
    public String getDate() {
        return date;
    }

    @JsonProperty("date")
    public void setDate(String date) {
        this.date = date;
    }

    @JsonProperty("package")
    public List<String> getPackage() {
        return _package;
    }

    @JsonProperty("package")
    public void setPackage(List<String> _package) {
        this._package = _package;
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