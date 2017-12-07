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
        "inputs",
        "user",
        "conversation"
})
public class Data {

    @JsonProperty("inputs")
    private List<Input> inputs = null;
    @JsonProperty("user")
    private User user;
    @JsonProperty("conversation")
    private Conversation conversation;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("inputs")
    public List<Input> getInputs() {
        return inputs;
    }

    @JsonProperty("inputs")
    public void setInputs(List<Input> inputs) {
        this.inputs = inputs;
    }

    @JsonProperty("user")
    public User getUser() {
        return user;
    }

    @JsonProperty("user")
    public void setUser(User user) {
        this.user = user;
    }

    @JsonProperty("conversation")
    public Conversation getConversation() {
        return conversation;
    }

    @JsonProperty("conversation")
    public void setConversation(Conversation conversation) {
        this.conversation = conversation;
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
