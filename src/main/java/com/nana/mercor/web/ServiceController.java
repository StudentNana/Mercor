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
@RequestMapping("mercor")
public class ServiceController {

    private final SearchService searchService;

    public ServiceController(SearchService searchService){
        this.searchService = searchService;
    }

    @RequestMapping(value = "search", method = RequestMethod.POST)
    @ResponseBody
    public String search(@RequestBody ApiaiSearchQuery apiaiSearchQuery) {
        return searchService.search(apiaiSearchQuery.getResult().getParameters().getArticle().get(0),
                apiaiSearchQuery.getResult().getParameters().getPackage().get(0));
    }

    @RequestMapping(value = "test", method = RequestMethod.GET)
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
