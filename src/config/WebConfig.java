package config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.UrlBasedViewResolver;

import com.webshop.interceptor.TimeBasedAccessInterceptor;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = { "com", "test" })
public class WebConfig extends WebMvcConfigurerAdapter {

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// super.addInterceptors(registry);
		registry.addInterceptor(new LocaleChangeInterceptor());
		registry.addInterceptor(new TimeBasedAccessInterceptor());
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**")
				.addResourceLocations("/public-resources/")
				.setCachePeriod(31556926);
	}

	/**
	 * 定制后缀名
	 */
	@Override
	public void configureContentNegotiation(
			ContentNegotiationConfigurer configurer) {
		configurer.favorPathExtension(false).favorParameter(true);
	}

	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		UrlBasedViewResolver ubv = new UrlBasedViewResolver();
		ubv.setViewClass(JstlView.class);
		ubv.setPrefix("/WEB-INF/jsp/");
		ubv.setSuffix(".jsp");
		registry.viewResolver(ubv);
	}

	@Override
	public void configureDefaultServletHandling(
			DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}

	/**
	 * This is a shortcut for defining a ParameterizableViewController that immediately forwards 
	 * to a view when invoked. Use it in static cases when there is no Java controller logic to execute 
	 * before the view generates the response.
	 */
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/").setViewName("home");
	}

}
