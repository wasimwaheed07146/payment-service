package com.payment.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

	@Value("${starzplay.swagger.title:}")
	private String title;

	@Value("${starzplay.swagger.description:}")
	private String description;

	@Value("${starzplay.swagger.license:Apache 2.0}")
	private String license;

	@Value("${starzplay.swagger.licenseurl:http://www.apache.org/licenses/LICENSE-2.0}")
	private String licenseUrl;

	@Bean
	public OpenAPI springShopOpenAPI() {
		return new OpenAPI()
				.info(new Info().title(title)
						.description(description)
						.version("v0.0.1")
						.license(new License().name(license).url(licenseUrl)))
				.externalDocs(new ExternalDocumentation()
						.description(title + " Wiki Documentation")
						.url("https://springshop.wiki.github.org/docs"));
	}

}
