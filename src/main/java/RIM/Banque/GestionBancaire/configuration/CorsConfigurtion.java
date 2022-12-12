package RIM.Banque.GestionBancaire.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfigurtion {
	
	public WebMvcConfigurer corsConigurere() {
		
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				
				registry.addMapping("/**")
						.allowedMethods("GET","POST","PUT","DELETE")
						.allowedHeaders("*")
						.allowedOriginPatterns("*")
						.allowCredentials(true);
			}
		};
	}

}
