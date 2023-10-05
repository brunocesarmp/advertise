package dev.brunocesar.imovelsimplificado.advertise.configs;

import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class OpenApiConfig {

    private static final String INFO_TITLE = "Advertise System";
    private static final String INFO_DESCRIPTION = "Sistema responsável pelo cadastro de Anunciantes";
    private static final String SERVER_DEV_DESCRIPTION = "Desenvolvimento";
    private static final String SERVER_PRD_DESCRIPTION = "Produção";
    private static final String SERVER_DEV_URL = "http://localhost:8081/api/v1";
    private static final String SERVER_PRD_URL = "http://advertise-system-env.eba-yp38yrgb.us-east-1.elasticbeanstalk.com/api/v1";
    private static final String BEARER_TOKEN = "bearer-token";
    private static final String SECURITY_SCHEME = "bearer";
    private static final String SECURITY_SCHEME_BEARER_FORMAT = "JWT";

    @Bean
    public OpenApiCustomizer customOpenApiCustomizer() {
        return openApi -> {
            openApi.setInfo(getInfo());
            openApi.setServers(List.of(getDevServer(), getPrdServer()));
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

    private Server getPrdServer() {
        var prdServer = new Server();
        prdServer.setDescription(SERVER_PRD_DESCRIPTION);
        prdServer.setUrl(SERVER_PRD_URL);
        return prdServer;
    }

    private Server getDevServer() {
        var devServer = new Server();
        devServer.setDescription(SERVER_DEV_DESCRIPTION);
        devServer.setUrl(SERVER_DEV_URL);
        return devServer;
    }

    private Info getInfo() {
        var info = new Info();
        info.setTitle(INFO_TITLE);
        info.setDescription(INFO_DESCRIPTION);
        return info;
    }
}