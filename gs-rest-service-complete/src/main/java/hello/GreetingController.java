package hello;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/rest/greeting")
public class GreetingController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();
    private Map<String,Greeting> greetings = new HashMap<String, Greeting>();

    @GetMapping("")
    public Map<String,Greeting> getGreetings() {
    	if(greetings == null) {
    		throw new ResponseStatusException(
  		          HttpStatus.NOT_FOUND, " greetings does not exist");
    	}
    	return greetings;
    }
    
    @DeleteMapping("")
    public @ResponseBody ResponseEntity<String> deleteGreetings() {
    	greetings = null;
        return new ResponseEntity<String>("Deleted greetings", HttpStatus.OK);
    }
    
    @PutMapping("")
    public @ResponseBody ResponseEntity<String> putGreetings() {
    	greetings = new HashMap<String, Greeting>(); 
    	return new ResponseEntity<String>("Created greetings", HttpStatus.OK);
    }
    
    @GetMapping("/{name}")
    public Greeting getGreeting(@PathVariable String name) {
    	if(greetings.containsKey(name)) {
    		return greetings.get(name);
    	} else {
    		throw new ResponseStatusException(
    		          HttpStatus.NOT_FOUND, " greeting " + name + " does not exist");
    	}
    }
    
    @PutMapping("/{name}")
    public Greeting putGreeting(@PathVariable String name) {
    	if(greetings.containsKey(name) == false) {
    		Greeting greeting = new Greeting(counter.incrementAndGet(),
                    String.format(template, name));
    		greetings.put(name, greeting);
    	} else {
    		Greeting greeting = greetings.get(name);
    		greeting.setContent(String.format(template, name));
    		greetings.put(name, greeting);
    	}
    	return greetings.get(name);
    }
    
    @DeleteMapping("/{name}")
    public @ResponseBody ResponseEntity<String> deleteGreeting(@PathVariable String name) {
    	if(greetings.containsKey(name) == false) {
    		throw new ResponseStatusException(
    		          HttpStatus.NOT_FOUND, " greeting " + name + " does not exist");
    	} else {
    		greetings.remove(name);
    	}
        return new ResponseEntity<String>("Deleted " + name, HttpStatus.OK);
    }
    
    @GetMapping("/get")
    public @ResponseBody ResponseEntity<String> get() {
        return new ResponseEntity<String>("GET Response", HttpStatus.OK);
    }    
    
    @PostMapping("/post")
    public @ResponseBody ResponseEntity<String> post() {
        return new ResponseEntity<String>("POST Response", HttpStatus.OK);
    }
    
    @PutMapping("/put")
    public @ResponseBody ResponseEntity<String> put() {
        return new ResponseEntity<String>("PUT Response", HttpStatus.OK);
    }
    
    @DeleteMapping("/delete")
    public @ResponseBody ResponseEntity<String> delete() {
        return new ResponseEntity<String>("DELETE Response", HttpStatus.OK);
    }
    
    @PatchMapping("/patch")
    public @ResponseBody ResponseEntity<String> patch() {
        return new ResponseEntity<String>("PATCH Response", HttpStatus.OK);
    }  
}
