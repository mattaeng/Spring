package com.mattaeng.mattaengapi.common.config;

import org.springdoc.core.customizers.OperationCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.media.StringSchema;
import io.swagger.v3.oas.models.parameters.Parameter;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.security.SecurityScheme.Type;

@Configuration
public class SwaggerConfig {

	private static final String SECURITY_SCHEME_NAME = "authorization";

	@Bean
	public OpenAPI swaggerApi() {
		return new OpenAPI()
			.components(new Components()
				.addSecuritySchemes(SECURITY_SCHEME_NAME, new SecurityScheme()
					.name(SECURITY_SCHEME_NAME)
					.type(Type.HTTP)
					.scheme("bearer")
					.bearerFormat("JWT")))
			.addSecurityItem(new SecurityRequirement().addList(SECURITY_SCHEME_NAME))
			.info(new Info()
				.title("Mattaeng Test")
				.description("Mattaeng Swagger Test")
				.version("1.0,0"));
	}

	@Bean
	public OperationCustomizer globalHeader() {
		return (operation, handlerMethod) -> {
			operation.addParametersItem(new Parameter()
				.in(ParameterIn.HEADER.toString())
				.schema(new StringSchema().name("Refresh-Token"))
				.name("Refresh-Token"));
			return operation;
		};
	}
}

