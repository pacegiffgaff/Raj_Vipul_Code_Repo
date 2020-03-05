package com.giffgaff.springboot.web.controller;

import com.giffgaff.springboot.web.model.Todo;
import com.giffgaff.springboot.web.service.TodoRepository;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

@Controller
public class TodoController {
	/*
	 * @Autowired TodoService todoService ;
	 */
    
    @Autowired
    private TodoRepository todoRepo;

	/**
	 * @param binder
	 */
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				dateFormat, false));
	}

	/**
	 * This method will return list of todos
	 * @param model
	 * @return
	 */
    @RequestMapping(value ="/list-todos", method = RequestMethod.GET)
    public String showTodoList(ModelMap model){
    	String username = getLoggedInUserName(model);
    	model.put("todos",todoRepo.findByUser(username));
    	//model.put("todos",todoService.retrieveTodos(username));
        return "list-todos";
    }

	/**
	 * This method used to show add todo page
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/add-todo", method = RequestMethod.GET)
	public String showAddTodoPage(ModelMap model) {
		model.addAttribute("todo", new Todo(0, getLoggedInUserName(model), "Default Desc",
				new Date(), false));
		return "todo";
	}

	/**
	 * This method used to add Todo
	 * @param model
	 * @param todo
	 * @param result
	 * @return
	 */
    @RequestMapping(value = "/add-todo", method = RequestMethod.POST)
	public String addTodo(ModelMap model,@ModelAttribute("todo")  @Valid Todo todo, BindingResult result) {
		
		if(result.hasErrors()){
			return "todo";
		}
		
		todo.setUser(getLoggedInUserName(model));
		todoRepo.save(todo);
		
		/*
		 * todoService.addTodo(getLoggedInUserName(model), todo.getDesc(),
		 * todo.getTargetDate(), false);
		 */
		return "redirect:/list-todos";
	}

	/**
	 * This method delete todo
	 * @param id
	 * @return
	 */
    @RequestMapping(value="/delete-todo", method = RequestMethod.GET)
	public String deleteTodo(@RequestParam int id){
		todoRepo.deleteById(id);
    	//todoService.deleteTodo(id);
		return "redirect:/list-todos";
	}


	/**
	 * This method used to update todo
	 * @param id
	 * @param model
	 * @return
	 */
    @RequestMapping(value = "/update-todo", method = RequestMethod.GET)
	public String showUpdateTodoPage(@RequestParam int id, ModelMap model) {
    	Todo todo = todoRepo.findById(id).get();
    	//Todo todo = todoService.retrieveTodo(id);
		model.put("todo", todo);
		return "todo";
	}

	/**
	 * This method used to update todos
	 * @param model
	 * @param todo
	 * @param result
	 * @return
	 */
	@RequestMapping(value = "/update-todo", method = RequestMethod.POST)
	public String updateTodo(ModelMap model, @Valid Todo todo, BindingResult result) {
		if (result.hasErrors()) {
			return "todo";
		}
		
		todo.setUser(getLoggedInUserName(model));
		todoRepo.save(todo);
		//todoService.updateTodo(todo);
		return "redirect:/list-todos";
	}

	/**
	 * Refactoring session name
	 */
	private String getLoggedInUserName(ModelMap model) {
		Object principal = SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();

		if (principal instanceof UserDetails)
			return ((UserDetails) principal).getUsername();

		return principal.toString();
	}
}
