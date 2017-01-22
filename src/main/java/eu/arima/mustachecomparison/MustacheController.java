package eu.arima.mustachecomparison;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MustacheController {
	
	@GetMapping("javascript")
	public String javascript(Model model, @RequestParam(value = "size", defaultValue = "10") int size) {
		return handleRequest(model, size, "javascript");
	}

	@GetMapping("java")
	public String java(Model model, @RequestParam(value = "size", defaultValue = "10") int size) {
		return handleRequest(model, size, "java");
	}
	
	private String handleRequest(Model model, int size, String type) {
		model.addAttribute("title", "Todo list");
		model.addAttribute(createTodoList(size));
		return type + "/todoList";
	}
	
	private static List<Todo> createTodoList(int size) {
		List<Todo> todoList = new ArrayList<>();
		
		for (int i = 0; i < size; i++) {
			todoList.add(new Todo(i, "name-" + i));
		}
		
		return todoList;
	}
	
}
