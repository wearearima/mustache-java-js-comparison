package eu.arima.mustachecomparison;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.util.StreamUtils;

public class MustacheVersionTests {

	private static String template = "<html>\n" +
			"    <head>\n" +
			"        <title>{{title}}</title>\n" +
			"    </head>\n" +
			"    <body>\n" +
			"        <p>{{todo.name}}</p>\n" +
			"        <table>\n" +
			"            <thead>\n" + 
			"                <tr>\n" +
			"                    <th>id</th>\n" + 
			"                    <th>name</th>\n" + 
			"                </tr>\n" + 
			"            </thead>\n" + 
			"            <tbody>\n" + 
			"                {{#todoList}}\n" + 
			"                <tr>\n" + 
			"                    <td>{{id}}</td>\n" + 
			"                    <td>{{name}}</td>\n" + 
			"                </tr>\n" +
			"                {{/todoList}}\n" + 
			"            </tbody>\n" + 
			"        </table\n>" + 
			"    </body>\n" +
			"</html>";

	private static String expectedHtmlResult = "<html>\n" +
			"    <head>\n" +
			"        <title>Todo List</title>\n" +
			"    </head>\n" +
			"    <body>\n" +
			"        <p>unique-todo</p>\n" +			
			"        <table>\n" +
			"            <thead>\n" + 
			"                <tr>\n" +
			"                    <th>id</th>\n" + 
			"                    <th>name</th>\n" + 
			"                </tr>\n" + 
			"            </thead>\n" + 
			"            <tbody>\n" + 
			"                <tr>\n" + 
			"                    <td>1</td>\n" + 
			"                    <td>name-1</td>\n" + 
			"                </tr>\n" +
			"                <tr>\n" + 
			"                    <td>2</td>\n" + 
			"                    <td>name-2</td>\n" + 
			"                </tr>\n" +			
			"            </tbody>\n" + 
			"        </table\n>" + 
			"    </body>\n" +
			"</html>";
	
	@Test
	public void engine_0_8_2() {
		doTest("META-INF/resources/webjars/mustachejs/0.8.2/mustache.js");
	}
	
	@Test
	public void engine_2_3_0() {
		doTest("META-INF/resources/webjars/mustache/2.3.0/mustache.js");
	}
	
	public void doTest(String mustachejsResource) {
		ScriptEngine engine = createEngine(mustachejsResource);
		Invocable invocable = (Invocable) engine;
		
		Map<String, Object> params = new HashMap<>();
		params.put("title", "Todo List");
		params.put("todo", new Todo(10, "unique-todo"));
		params.put("todoList", Arrays.asList(new Todo(1, "name-1"), new Todo(2, "name-2")));
		
		try {
			String result = (String) invocable.invokeFunction("render", template, params);
			Assert.assertEquals(expectedHtmlResult, result);
		} catch (NoSuchMethodException | ScriptException e) {
			throw new IllegalStateException(e);
		}
	}
	
	private static ScriptEngine createEngine(String resource) {
		try {
			String mustachejs = StreamUtils.copyToString(MustacheVersionTests.class.getClassLoader().getResourceAsStream(resource), StandardCharsets.UTF_8);
			String renderjs = StreamUtils.copyToString(MustacheVersionTests.class.getClassLoader().getResourceAsStream("static/render.js"), StandardCharsets.UTF_8);
			
			ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");
			engine.eval(mustachejs);
			engine.eval(renderjs);
			
			return engine;
			
		} catch (IOException | ScriptException e) {
			throw new IllegalStateException(e);
		}
	}
}
