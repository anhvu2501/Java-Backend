package com.example.restservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

@RestController //marks the class as a controller where every method returns a domain object instead of a view.
public class GreetingController {
    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @GetMapping("/greeting") //This annotation ensures that HTTP GET requests to /greeting are mapped to the /greeting()/ method
    public Greeting greeting (@RequestParam(value = "name", defaultValue = "World") String name) {
        return new Greeting(counter.incrementAndGet(), String.format(template, name));
    }

    //@RequestParam binds the value of the query string parameter /name/ into the /name/ parameter of the
    ///greeting()/ method. If the /name/ parameter is absent in the request, the defaultValue /World/ is used.

    //The implementation of the method body creates and returns a new Greeting object with /id/ and /content/ attributes
    //based on the next value from the /counter/ and formats the given /name/ by using the greeting /template/

}
