package eu.arima.mustachecomparison;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.script.ScriptTemplateConfigurer;
import org.springframework.web.servlet.view.script.ScriptTemplateViewResolver;

@SpringBootApplication
public class MustacheJavaJsComparisonApplication {

	public static void main(String[] args) {
		SpringApplication.run(MustacheJavaJsComparisonApplication.class, args);
	}
	
	@EnableWebMvc
	@Configuration
	@ComponentScan
	public static class SpringMvcConfig extends WebMvcConfigurerAdapter {
		
		@Bean
	    public ScriptTemplateConfigurer configurer() {
	        ScriptTemplateConfigurer configurer = new ScriptTemplateConfigurer();
	        configurer.setEngineName("nashorn");
//	        configurer.setScripts("/META-INF/resources/webjars/mustache/2.3.0/mustache.js", "/static/render.js");
	        configurer.setScripts("/META-INF/resources/webjars/mustachejs/0.8.2/mustache.js", "/static/render.js");
	        configurer.setRenderFunction("render");
	        
	        // configurer.setSharedEngine(false);
	        
	        return configurer;
	    }
		
		@Bean
	    public ViewResolver viewResolver() {
	        ScriptTemplateViewResolver viewResolver = new ScriptTemplateViewResolver();
	        viewResolver.setPrefix("classpath:/templates/");
	        viewResolver.setViewNames("javascript/*");
	        viewResolver.setSuffix(".html");
	        return viewResolver;
	    }
		
	}
	
}
