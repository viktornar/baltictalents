package lt.baltictalents.lesson4.controller;

import lombok.val;
import lt.baltictalents.lesson4.payload.SomeJson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Controller
@RequestMapping("/examples")
public class ExampleController {
    private static final Logger logger = LoggerFactory.getLogger(ExampleController.class);

    @RequestMapping(value={"", "/"})
    String doGet() {
        return "examples";
    }

    @RequestMapping(value={"/redirect", "/redirect/"})
    String doGetWithRedirect() {
        return "redirect:model-and-view";
    }

    @RequestMapping("/query")
    ModelAndView doGetWithQuery(@RequestParam(value = "name1", required = false) String name1, @RequestParam(value="name2", required = false) String name2) {
        val modelAndView = new ModelAndView("examples");
        modelAndView.addObject("name1", name1);
        modelAndView.addObject("name2", name2);

        return modelAndView;
    }

    @RequestMapping(path = "/post", method = RequestMethod.POST)
    String doPost() {
        return "examples";
    }

    @RequestMapping(path = "/{id}")
    ModelAndView doGetModelAndViewWithId(@PathVariable("id") Optional<Integer> id) {
        val modelAndView = new ModelAndView("examples");

        if (id.isPresent()) {
            modelAndView.addObject("id", id.get());
        }

        modelAndView.addObject(1);

        return modelAndView;
    }

    @RequestMapping(path = "/response-payload", produces = "application/json")
    ResponseEntity<SomeJson> doGetResponseEntity(HttpServletRequest request) {
        val modelAndView = new ModelAndView("examples");

        logger.info("Request method: " + request.getMethod());

        val someJson = new SomeJson();
        someJson.setResponse("success");

        val responseHeaders = new HttpHeaders();
        responseHeaders.set("MyResponseHeader", "MyValue");

        return new ResponseEntity<>(someJson, responseHeaders, HttpStatus.CREATED);
    }

    @RequestMapping(path = "/json", produces = "application/json")
    @ResponseBody
    SomeJson doGetJson() {
        val someJson = new SomeJson();
        someJson.setResponse("success other");

        return someJson;
    }


    @RequestMapping(value = "/json", produces = "application/json", consumes = "application/json", method = RequestMethod.POST)
    @ResponseBody
    SomeJson doPostJson(@RequestBody SomeJson someJson) {
        val someResponseJson = new SomeJson();
        someResponseJson.setResponse(someJson.getResponse());

        return someJson;
    }
}

