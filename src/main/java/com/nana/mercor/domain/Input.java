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
        "raw_inputs",
        "intent",
        "arguments"
})
public class Input {

    @JsonProperty("raw_inputs")
    private List<RawInput> rawInputs = null;
    @JsonProperty("intent")
    private String intent;
    @JsonProperty("arguments")
    private List<Argument> arguments = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("raw_inputs")
    public List<RawInput> getRawInputs() {
        return rawInputs;
    }

    @JsonProperty("raw_inputs")
    public void setRawInputs(List<RawInput> rawInputs) {
        this.rawInputs = rawInputs;
    }

    @JsonProperty("intent")
    public String getIntent() {
        return intent;
    }

    @JsonProperty("intent")
    public void setIntent(String intent) {
        this.intent = intent;
    }

    @JsonProperty("arguments")
    public List<Argument> getArguments() {
        return arguments;
    }

    @JsonProperty("arguments")
    public void setArguments(List<Argument> arguments) {
        this.arguments = arguments;
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
