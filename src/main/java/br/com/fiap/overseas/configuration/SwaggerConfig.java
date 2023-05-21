package br.com.fiap.overseas.configuration;
import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.responses.ApiResponse;
import io.swagger.v3.oas.models.responses.ApiResponses;

@Configuration
public class SwaggerConfig {
	@Bean
	public OpenAPI springOverSeasOpenAPI() {
		return new OpenAPI()
				.info(new Info()
					.title("Projeto OverSeas")
					.description("OverSeas - A melhor plataforma de carros elétricos do país")
					.version("v0.0.1")
				.license(new License()
					.name("FIAP")
					.url("https://fiap.com.br/"))
				.contact(new Contact()
					.name("Mayara Amorim")
					.email("moreiraamorim.business@gmail.com")))
				.externalDocs(new ExternalDocumentation()
					.description("Github")
					.url("https://github.com/Mayara-Amorim/OverSeas"));
	}

	@Bean
	public OpenApiCustomizer customerGlobalHeaderOpenApiCustomiser() {

		return openApi -> {
			openApi.getPaths().values().forEach(pathItem -> pathItem.readOperations().forEach(operation -> {

				ApiResponses apiResponses = operation.getResponses();

				apiResponses.addApiResponse("200", createApiResponse("Sucesso!"));
				apiResponses.addApiResponse("201", createApiResponse("Objeto Persistido!"));
				apiResponses.addApiResponse("204", createApiResponse("Objeto Excluído!"));
				apiResponses.addApiResponse("400", createApiResponse("Erro na Requisição!"));
				apiResponses.addApiResponse("401", createApiResponse("Acesso Não Autorizado!"));
				apiResponses.addApiResponse("404", createApiResponse("Objeto Não Encontrado!"));
				apiResponses.addApiResponse("500", createApiResponse("Erro na Aplicação!"));

			}));
		};
	}

	private ApiResponse createApiResponse(String message) {

		return new ApiResponse().description(message);

	}
	
}
