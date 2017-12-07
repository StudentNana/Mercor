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
        "conversation_id",
        "type",
        "conversation_token"
})
public class Conversation {

    @JsonProperty("conversation_id")
    private String conversationId;
    @JsonProperty("type")
    private Integer type;
    @JsonProperty("conversation_token")
    private String conversationToken;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("conversation_id")
    public String getConversationId() {
        return conversationId;
    }

    @JsonProperty("conversation_id")
    public void setConversationId(String conversationId) {
        this.conversationId = conversationId;
    }

    @JsonProperty("type")
    public Integer getType() {
        return type;
    }

    @JsonProperty("type")
    public void setType(Integer type) {
        this.type = type;
    }

    @JsonProperty("conversation_token")
    public String getConversationToken() {
        return conversationToken;
    }

    @JsonProperty("conversation_token")
    public void setConversationToken(String conversationToken) {
        this.conversationToken = conversationToken;
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
