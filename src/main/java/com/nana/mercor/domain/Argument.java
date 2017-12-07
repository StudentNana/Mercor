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
        "text_value",
        "raw_text",
        "name"
})
public class Argument {

    @JsonProperty("text_value")
    private String textValue;
    @JsonProperty("raw_text")
    private String rawText;
    @JsonProperty("name")
    private String name;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("text_value")
    public String getTextValue() {
        return textValue;
    }

    @JsonProperty("text_value")
    public void setTextValue(String textValue) {
        this.textValue = textValue;
    }

    @JsonProperty("raw_text")
    public String getRawText() {
        return rawText;
    }

    @JsonProperty("raw_text")
    public void setRawText(String rawText) {
        this.rawText = rawText;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
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
