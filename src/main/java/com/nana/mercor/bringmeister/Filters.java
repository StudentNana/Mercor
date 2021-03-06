package com.nana.mercor.bringmeister;

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
        "bm_brand",
        "bm_glob_characteristic_filter"
})
public class Filters {

    @JsonProperty("bm_brand")
    private BmBrand bmBrand;
    @JsonProperty("bm_glob_characteristic_filter")
    private BmGlobCharacteristicFilter bmGlobCharacteristicFilter;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("bm_brand")
    public BmBrand getBmBrand() {
        return bmBrand;
    }

    @JsonProperty("bm_brand")
    public void setBmBrand(BmBrand bmBrand) {
        this.bmBrand = bmBrand;
    }

    @JsonProperty("bm_glob_characteristic_filter")
    public BmGlobCharacteristicFilter getBmGlobCharacteristicFilter() {
        return bmGlobCharacteristicFilter;
    }

    @JsonProperty("bm_glob_characteristic_filter")
    public void setBmGlobCharacteristicFilter(BmGlobCharacteristicFilter bmGlobCharacteristicFilter) {
        this.bmGlobCharacteristicFilter = bmGlobCharacteristicFilter;
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