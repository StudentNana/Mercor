package com.nana.mercor.web;

import com.nana.mercor.bringmeister.SearchResponse;
import com.nana.mercor.domain.ApiaiSearchQuery;
import com.nana.mercor.service.SearchService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class ServiceController {

    private final SearchService searchService;

    public ServiceController(SearchService searchService){
        this.searchService = searchService;
    }

    public static final String RESPONSE_TEMPLATE = "{\n" +
            "    \"speech\": \"%s\",\n" +
            "    \"displayText\": \"%s\",\n" +
            "    \"data\": {},\n" +
            "    \"contextOut\": [{\"name\":\"mercor\",\"lifespan\":2,\"parameters\":{\"article\":[\"cola\"],\"count\":[\"drei\"],\"date\":\"\",\"package\":[\"kiste\"]}}],\n" +
            "    \"source\": \"DuckDuckGo\"\n" +
            "}";
    public static final String PRODUCTS_FOUND_MESSAGE = "Gefundene Artikeln: %d";

    @RequestMapping(value = "mercor/search", method = RequestMethod.POST)
    @ResponseBody
    public String search(@RequestBody ApiaiSearchQuery apiaiSearchQuery) {
        final Optional<SearchResponse> searchResponse = searchService.search(
                apiaiSearchQuery.getResult().getParameters().getArticle().get(0),
                apiaiSearchQuery.getResult().getParameters().getPackage().get(0));
        String message = "No products found!";
        if(searchResponse.isPresent()) {
            message = String.format(PRODUCTS_FOUND_MESSAGE, searchResponse.get().getTotalProductsCount());
        }
        return String.format(RESPONSE_TEMPLATE, message, message);
    }

    @RequestMapping(value = "mercor/test", method = RequestMethod.GET)
    @ResponseBody
    public String test() {
        return "{\n" +
                "        \"speech\": \"Hello speech from spring boot!\",\n" +
                "        \"displayText\": \"Hello text from spring boot!\",\n" +
                "  \t\t\"data\": {},\n" +
                "        \"contextOut\": [{\"name\":\"weather\", \"lifespan\":2, \"parameters\":{\"city\":\"Rome\"}}],\n" +
                "        \"source\": \"apiai-weather-webhook-sample\"\n" +
                "}";
    }

}
