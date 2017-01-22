package eu.arima.mustachecomparison;

import java.util.Date;

public class Todo {
	
	private final long id;
	
	private final String name;
	
	private final Date dueDate;

	public Todo(long id, String name) {
		this.id = id;
		this.name = name;
		this.dueDate = new Date();
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