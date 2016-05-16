package com.webshop.shop.controller;

import java.net.URI;
import java.net.URISyntaxException;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.webshop.po.User;

@Controller
@RequestMapping("/test")
public class LoginController {

	/**
	 * RequestParam
	 * @param username
	 * @param model
	 * @return
	 */
	/*@RequestMapping(value = "/test/welcome")
	public ModelMap welcome(@RequestParam(required = false) String username,
			ModelMap model) {
		System.out.println(username);
		model.addAttribute("username", username);
		return model;
	}*/

	/**
	 * RequestBody
	 * @param model
	 * @param body
	 * @param writer
	 * @return
	 */
	/*@RequestMapping(value = "/test/welcome")
	public void welcome( @RequestBody String body, Writer writer) {
		try {
			writer.write(body);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}*/
	
	
	/**
	 * An @ModelAttribute on a method indicates the purpose of that method is 
	 * to add one or more model attributes.
	 *  Such methods support the same argument types as @RequestMapping methods 
	 *  but cannot be mapped directly to requests
	 *  
	 *  A controller can have any number of @ModelAttribute methods. 
	 *  All such methods are invoked before @RequestMapping methods of the same controller.
	 * @param username
	 * @return
	 */
	/*@ModelAttribute
	public User getUser(@RequestParam(required = false) String username ) {
		User user = new User();
		user.setUsername(username);
		return user;
	}
	
	@RequestMapping(value = "/test/welcome")
	public ModelMap welcome(ModelMap model) {
		User user = (User)model.get("user");
		System.out.println(model.containsAttribute("user"));
		System.out.println(user.getUsername());
		return model;
	}
	
	@RequestMapping(value = "/test/welcome1")
	public ModelMap welcome1(ModelMap model) {
		User user = (User)model.get("user");
		System.out.println(model.containsAttribute("user"));
		System.out.println(user.getUsername());
		return model;
	}*/
	
	/*@RequestMapping(value = "/test/welcome")
	public Model welcome(@ModelAttribute User user, Model model) {
		System.out.println("username:"+user.getUsername());
		return model;
	}*/
	
	@RequestMapping(value = "/welcome/{id}")
	public Model welcome(@Valid @ModelAttribute User user, BindingResult result, Model model) {
		System.out.println("id:"+user.getId());
		System.out.println("password:" + user.getPassword());
		System.out.println("email:" + user.getEmail());
		System.out.println(result.getErrorCount());
		
		for (ObjectError error : result.getAllErrors()) {
			System.out.println(error.getDefaultMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "hello/{id}")
	public String hello(RedirectAttributes ra) {
		User user = new User();
		user.setEmail("111111");
		ra.addFlashAttribute("user", user);
		User u = null;
		System.out.println(u.getEmail());
		return "redirect:/test/welcome/{id}";
	}
	
	
	/*@ExceptionHandler(NullPointerException.class)
	public ResponseEntity<String> handleIOException(NullPointerException ex) {
		ResponseEntity<String> response = new ResponseEntity<String>("nullPointerException",HttpStatus.NOT_FOUND);
	    return response;
	    
	}*/

}
