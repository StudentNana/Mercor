package com.nana.mercor.web;

import com.nana.mercor.domain.ApiaiResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ServiceController {

    @RequestMapping(value = "mercor/search", method = RequestMethod.POST)
    @ResponseBody
    public String search(@RequestBody ApiaiResult apiaiResult) {
        System.out.println(apiaiResult);
        return "RESULT\n";
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
