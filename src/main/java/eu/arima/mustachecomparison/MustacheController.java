package eu.arima.mustachecomparison;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MustacheController {

	@GetMapping("java")
	public String java(Model model, @RequestParam(value = "size", defaultValue = "10") int size) {
		model.addAttribute(createTodoList(size));
		return "todoList";
	}
	
	private static List<Todo> createTodoList(int size) {
		List<Todo> todoList = new ArrayList<>();
		
		for (int i = 0; i < size; i++) {
			todoList.add(new Todo(i, "name-" + i, new Date()));
		}
		
		return todoList;
	}
	
	public static final class Todo {
		
		private final long id;
		
		private final String name;
		
		private final Date dueDate;

		public Todo(long id, String name, Date dueDate) {
			this.id = id;
			this.name = name;
			this.dueDate = dueDate;
		}

		public long getId() {
			return id;
		}

		public String getName() {
			return name;
		}

		public Date getDueDate() {
			return dueDate;
		}
		
	}
	
	
}
