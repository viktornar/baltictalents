package lt.baltictalents.lesson4.controller;

import lombok.val;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
@RequestMapping("/model-and-view")
public class MyModelAndViewController {
    @RequestMapping(value={"", "/"})
    ModelAndView doGetModelAndView() {
        val modelAndView = new ModelAndView("model-and-view");

        modelAndView.addObject("id", 1);

        return modelAndView;
    }

    @RequestMapping(path = "/{id}")
    ModelAndView doGetModelAndView(@PathVariable("id") Optional<Integer> id) {
        val modelAndView = new ModelAndView("model-and-view");

        if (id.isPresent()) {
            modelAndView.addObject("id", id.get());
        }

        modelAndView.addObject(1);

        return modelAndView;
    }

    @RequestMapping(value={"/model", "/model/"})
    String doGetModelAndView(Model model) {
        model.addAttribute("id", 1);
        return "model-and-view";
    }

    @RequestMapping("/model/{id}")
    String doGetModel(Model model, @PathVariable("id") Optional<Integer> id) {
        if (id.isPresent()) {
            model.addAttribute("id", id.get());
        }

        model.addAttribute("id", id.get());

        return "model-and-view";
    }
}
