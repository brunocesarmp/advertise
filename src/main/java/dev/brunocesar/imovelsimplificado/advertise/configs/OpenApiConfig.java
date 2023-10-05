package dev.brunocesar.imovelsimplificado.advertise.configs;

import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class OpenApiConfig {

    private static final String INFO_TITLE = "Advertise System";
    private static final String INFO_DESCRIPTION = "Sistema responsável pelo cadastro de Anunciantes";
    private static final String BEARER_TOKEN = "bearer-token";
    private static final String SECURITY_SCHEME = "bearer";
    private static final String SECURITY_SCHEME_BEARER_FORMAT = "JWT";

    @Value("${server.url:}")
    private String serverUrl;

    @Value("${server.servlet.context-path:}")
    private String serverContextPath;

    @Bean
    public OpenApiCustomizer customOpenApiCustomizer() {
        var applicationUrl = serverUrl + serverContextPath;
        return openApi -> {
            openApi.setInfo(getInfo());
            openApi.setServers(List.of(getServer(applicationUrl)));
            openApi.getComponents().addSecuritySchemes(BEARER_TOKEN, getSecurityScheme());
            openApi.getPaths()
                    .values()
                    .forEach(pathItem -> pathItem.readOperations()
                            .forEach(operation -> operation.setSecurity(getSecurityRequirements())));
        };
    }

    private List<SecurityRequirement> getSecurityRequirements() {
        var securityRequirement = new SecurityRequirement();
        securityRequirement.addList(BEARER_TOKEN, BEARER_TOKEN);
        return List.of(securityRequirement);
    }

    private SecurityScheme getSecurityScheme() {
        return new SecurityScheme()
                .type(SecurityScheme.Type.HTTP)
                .scheme(SECURITY_SCHEME)
                .bearerFormat(SECURITY_SCHEME_BEARER_FORMAT);
    }

    private Server getServer(String applicationUrl) {
        var prdServer = new Server();
        prdServer.setUrl(applicationUrl);
        return prdServer;
    }

    private Info getInfo() {
        var info = new Info();
        info.setTitle(INFO_TITLE);
        info.setDescription(INFO_DESCRIPTION);
        return info;
    }
}