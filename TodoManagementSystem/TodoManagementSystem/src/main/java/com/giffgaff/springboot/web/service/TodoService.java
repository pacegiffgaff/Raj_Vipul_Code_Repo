package com.giffgaff.springboot.web.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Service;

import com.giffgaff.springboot.web.model.Todo;

@Service
public class TodoService {
	private static List<Todo> todos = new ArrayList<Todo>();
    private static int todoCount = 3;

    static {
        todos.add(new Todo(1, "vipul", "Learn Spring MVC", new Date(),
                false));
        todos.add(new Todo(2, "Raj", "Learn Struts", new Date(), false));
        todos.add(new Todo(3, "vipul", "Learn Hibernate", new Date(),
                false));
        todos.add(new Todo(4, "Raj", "Learn Spring Security", new Date(),
                false));
    }

    /**
     * @param user
     * @return
     */
    public List<Todo> retrieveTodos(String user) {
        List<Todo> filteredTodos = new ArrayList<Todo>();
        for (Todo todo : todos) {
            if (todo.getUser().equals(user)) {
                filteredTodos.add(todo);
            }
        }
        return filteredTodos;
    }

    /**
     * @param id
     * @return
     */
    public Todo retrieveTodo(int id) {
        for (Todo todo : todos) {
            if (todo.getId()==id) {
                return todo;
            }
        }
        return null;
    }

    /**
     * @param todo
     */
    public void updateTodo(Todo todo){
    		todos.remove(todo);
    		todos.add(todo);
    }

    /**
     * @param name
     * @param desc
     * @param targetDate
     * @param isDone
     */
    public void addTodo(String name, String desc, Date targetDate,
            boolean isDone) {
        todos.add(new Todo(++todoCount, name, desc, targetDate, isDone));
    }

    /**
     * @param id
     */
    public void deleteTodo(int id) {
        Iterator<Todo> iterator = todos.iterator();
        while (iterator.hasNext()) {
            Todo todo = iterator.next();
            if (todo.getId() == id) {
                iterator.remove();
            }
        }
    }

}
