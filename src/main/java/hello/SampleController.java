package hello;

import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

@Controller
@EnableAutoConfiguration
public class SampleController {

	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value="/", method=RequestMethod.GET, produces = "application/json")
	@ResponseBody
    String home() {
	    return "{\"success\":1}";
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(SampleController.class, args);
    }
}