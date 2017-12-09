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
        "bmSpecialBasePrice",
        "id",
        "imageUrl",
        "weightUnit",
        "weightBaseQuantity",
        "outerPackingType",
        "specialPriceToDate",
        "filterOptionIds",
        "discountMessage",
        "bmBasePrice",
        "icons",
        "formatedPrice",
        "specialPrice",
        "unitTitle",
        "depositContainerPrice",
        "isSpecialOffer",
        "name",
        "bmBaseUnitPrice",
        "addToCartUrl",
        "units",
        "addToWishlistUrl",
        "bmBasePriceCalculationBase",
        "innerPackingType",
        "price",
        "measurements",
        "discount",
        "weightQuantity",
        "sku",
        "bmBaseUnit",
        "onStock",
        "isWeightProduct",
        "packing",
        "url",
        "formatedSpecialPrice",
        "weightCirca"
})
public class Product {

    @JsonProperty("bmSpecialBasePrice")
    private Object bmSpecialBasePrice;
    @JsonProperty("id")
    private String id;
    @JsonProperty("imageUrl")
    private String imageUrl;
    @JsonProperty("weightUnit")
    private String weightUnit;
    @JsonProperty("weightBaseQuantity")
    private String weightBaseQuantity;
    @JsonProperty("outerPackingType")
    private Object outerPackingType;
    @JsonProperty("specialPriceToDate")
    private Object specialPriceToDate;
    @JsonProperty("filterOptionIds")
    private List<Object> filterOptionIds = null;
    @JsonProperty("discountMessage")
    private Object discountMessage;
    @JsonProperty("bmBasePrice")
    private Double bmBasePrice;
    @JsonProperty("icons")
    private List<Object> icons = null;
    @JsonProperty("formatedPrice")
    private String formatedPrice;
    @JsonProperty("specialPrice")
    private Object specialPrice;
    @JsonProperty("unitTitle")
    private String unitTitle;
    @JsonProperty("depositContainerPrice")
    private String depositContainerPrice;
    @JsonProperty("isSpecialOffer")
    private Boolean isSpecialOffer;
    @JsonProperty("name")
    private String name;
    @JsonProperty("bmBaseUnitPrice")
    private String bmBaseUnitPrice;
    @JsonProperty("addToCartUrl")
    private String addToCartUrl;
    @JsonProperty("units")
    private List<Unit> units = null;
    @JsonProperty("addToWishlistUrl")
    private String addToWishlistUrl;
    @JsonProperty("bmBasePriceCalculationBase")
    private Integer bmBasePriceCalculationBase;
    @JsonProperty("innerPackingType")
    private String innerPackingType;
    @JsonProperty("price")
    private Double price;
    @JsonProperty("measurements")
    private String measurements;
    @JsonProperty("discount")
    private Integer discount;
    @JsonProperty("weightQuantity")
    private String weightQuantity;
    @JsonProperty("sku")
    private String sku;
    @JsonProperty("bmBaseUnit")
    private Integer bmBaseUnit;
    @JsonProperty("onStock")
    private Boolean onStock;
    @JsonProperty("isWeightProduct")
    private Boolean isWeightProduct;
    @JsonProperty("packing")
    private String packing;
    @JsonProperty("url")
    private String url;
    @JsonProperty("formatedSpecialPrice")
    private String formatedSpecialPrice;
    @JsonProperty("weightCirca")
    private Object weightCirca;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("bmSpecialBasePrice")
    public Object getBmSpecialBasePrice() {
        return bmSpecialBasePrice;
    }

    @JsonProperty("bmSpecialBasePrice")
    public void setBmSpecialBasePrice(Object bmSpecialBasePrice) {
        this.bmSpecialBasePrice = bmSpecialBasePrice;
    }

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    @JsonProperty("imageUrl")
    public String getImageUrl() {
        return imageUrl;
    }

    @JsonProperty("imageUrl")
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @JsonProperty("weightUnit")
    public String getWeightUnit() {
        return weightUnit;
    }

    @JsonProperty("weightUnit")
    public void setWeightUnit(String weightUnit) {
        this.weightUnit = weightUnit;
    }

    @JsonProperty("weightBaseQuantity")
    public String getWeightBaseQuantity() {
        return weightBaseQuantity;
    }

    @JsonProperty("weightBaseQuantity")
    public void setWeightBaseQuantity(String weightBaseQuantity) {
        this.weightBaseQuantity = weightBaseQuantity;
    }

    @JsonProperty("outerPackingType")
    public Object getOuterPackingType() {
        return outerPackingType;
    }

    @JsonProperty("outerPackingType")
    public void setOuterPackingType(Object outerPackingType) {
        this.outerPackingType = outerPackingType;
    }

    @JsonProperty("specialPriceToDate")
    public Object getSpecialPriceToDate() {
        return specialPriceToDate;
    }

    @JsonProperty("specialPriceToDate")
    public void setSpecialPriceToDate(Object specialPriceToDate) {
        this.specialPriceToDate = specialPriceToDate;
    }

    @JsonProperty("filterOptionIds")
    public List<Object> getFilterOptionIds() {
        return filterOptionIds;
    }

    @JsonProperty("filterOptionIds")
    public void setFilterOptionIds(List<Object> filterOptionIds) {
        this.filterOptionIds = filterOptionIds;
    }

    @JsonProperty("discountMessage")
    public Object getDiscountMessage() {
        return discountMessage;
    }

    @JsonProperty("discountMessage")
    public void setDiscountMessage(Object discountMessage) {
        this.discountMessage = discountMessage;
    }

    @JsonProperty("bmBasePrice")
    public Double getBmBasePrice() {
        return bmBasePrice;
    }

    @JsonProperty("bmBasePrice")
    public void setBmBasePrice(Double bmBasePrice) {
        this.bmBasePrice = bmBasePrice;
    }

    @JsonProperty("icons")
    public List<Object> getIcons() {
        return icons;
    }

    @JsonProperty("icons")
    public void setIcons(List<Object> icons) {
        this.icons = icons;
    }

    @JsonProperty("formatedPrice")
    public String getFormatedPrice() {
        return formatedPrice;
    }

    @JsonProperty("formatedPrice")
    public void setFormatedPrice(String formatedPrice) {
        this.formatedPrice = formatedPrice;
    }

    @JsonProperty("specialPrice")
    public Object getSpecialPrice() {
        return specialPrice;
    }

    @JsonProperty("specialPrice")
    public void setSpecialPrice(Object specialPrice) {
        this.specialPrice = specialPrice;
    }

    @JsonProperty("unitTitle")
    public String getUnitTitle() {
        return unitTitle;
    }

    @JsonProperty("unitTitle")
    public void setUnitTitle(String unitTitle) {
        this.unitTitle = unitTitle;
    }

    @JsonProperty("depositContainerPrice")
    public String getDepositContainerPrice() {
        return depositContainerPrice;
    }

    @JsonProperty("depositContainerPrice")
    public void setDepositContainerPrice(String depositContainerPrice) {
        this.depositContainerPrice = depositContainerPrice;
    }

    @JsonProperty("isSpecialOffer")
    public Boolean getIsSpecialOffer() {
        return isSpecialOffer;
    }

    @JsonProperty("isSpecialOffer")
    public void setIsSpecialOffer(Boolean isSpecialOffer) {
        this.isSpecialOffer = isSpecialOffer;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("bmBaseUnitPrice")
    public String getBmBaseUnitPrice() {
        return bmBaseUnitPrice;
    }

    @JsonProperty("bmBaseUnitPrice")
    public void setBmBaseUnitPrice(String bmBaseUnitPrice) {
        this.bmBaseUnitPrice = bmBaseUnitPrice;
    }

    @JsonProperty("addToCartUrl")
    public String getAddToCartUrl() {
        return addToCartUrl;
    }

    @JsonProperty("addToCartUrl")
    public void setAddToCartUrl(String addToCartUrl) {
        this.addToCartUrl = addToCartUrl;
    }

    @JsonProperty("units")
    public List<Unit> getUnits() {
        return units;
    }

    @JsonProperty("units")
    public void setUnits(List<Unit> units) {
        this.units = units;
    }

    @JsonProperty("addToWishlistUrl")
    public String getAddToWishlistUrl() {
        return addToWishlistUrl;
    }

    @JsonProperty("addToWishlistUrl")
    public void setAddToWishlistUrl(String addToWishlistUrl) {
        this.addToWishlistUrl = addToWishlistUrl;
    }

    @JsonProperty("bmBasePriceCalculationBase")
    public Integer getBmBasePriceCalculationBase() {
        return bmBasePriceCalculationBase;
    }

    @JsonProperty("bmBasePriceCalculationBase")
    public void setBmBasePriceCalculationBase(Integer bmBasePriceCalculationBase) {
        this.bmBasePriceCalculationBase = bmBasePriceCalculationBase;
    }

    @JsonProperty("innerPackingType")
    public String getInnerPackingType() {
        return innerPackingType;
    }

    @JsonProperty("innerPackingType")
    public void setInnerPackingType(String innerPackingType) {
        this.innerPackingType = innerPackingType;
    }

    @JsonProperty("price")
    public Double getPrice() {
        return price;
    }

    @JsonProperty("price")
    public void setPrice(Double price) {
        this.price = price;
    }

    @JsonProperty("measurements")
    public String getMeasurements() {
        return measurements;
    }

    @JsonProperty("measurements")
    public void setMeasurements(String measurements) {
        this.measurements = measurements;
    }

    @JsonProperty("discount")
    public Integer getDiscount() {
        return discount;
    }

    @JsonProperty("discount")
    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    @JsonProperty("weightQuantity")
    public String getWeightQuantity() {
        return weightQuantity;
    }

    @JsonProperty("weightQuantity")
    public void setWeightQuantity(String weightQuantity) {
        this.weightQuantity = weightQuantity;
    }

    @JsonProperty("sku")
    public String getSku() {
        return sku;
    }

    @JsonProperty("sku")
    public void setSku(String sku) {
        this.sku = sku;
    }

    @JsonProperty("bmBaseUnit")
    public Integer getBmBaseUnit() {
        return bmBaseUnit;
    }

    @JsonProperty("bmBaseUnit")
    public void setBmBaseUnit(Integer bmBaseUnit) {
        this.bmBaseUnit = bmBaseUnit;
    }

    @JsonProperty("onStock")
    public Boolean getOnStock() {
        return onStock;
    }

    @JsonProperty("onStock")
    public void setOnStock(Boolean onStock) {
        this.onStock = onStock;
    }

    @JsonProperty("isWeightProduct")
    public Boolean getIsWeightProduct() {
        return isWeightProduct;
    }

    @JsonProperty("isWeightProduct")
    public void setIsWeightProduct(Boolean isWeightProduct) {
        this.isWeightProduct = isWeightProduct;
    }

    @JsonProperty("packing")
    public String getPacking() {
        return packing;
    }

    @JsonProperty("packing")
    public void setPacking(String packing) {
        this.packing = packing;
    }

    @JsonProperty("url")
    public String getUrl() {
        return url;
    }

    @JsonProperty("url")
    public void setUrl(String url) {
        this.url = url;
    }

    @JsonProperty("formatedSpecialPrice")
    public String getFormatedSpecialPrice() {
        return formatedSpecialPrice;
    }

    @JsonProperty("formatedSpecialPrice")
    public void setFormatedSpecialPrice(String formatedSpecialPrice) {
        this.formatedSpecialPrice = formatedSpecialPrice;
    }

    @JsonProperty("weightCirca")
    public Object getWeightCirca() {
        return weightCirca;
    }

    @JsonProperty("weightCirca")
    public void setWeightCirca(Object weightCirca) {
        this.weightCirca = weightCirca;
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