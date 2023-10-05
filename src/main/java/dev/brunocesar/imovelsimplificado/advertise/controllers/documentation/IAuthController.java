package dev.brunocesar.imovelsimplificado.advertise.controllers.documentation;

import dev.brunocesar.imovelsimplificado.advertise.configs.handler.ApplicationErrorResponse;
import dev.brunocesar.imovelsimplificado.advertise.controllers.requests.LoginRequest;
import dev.brunocesar.imovelsimplificado.advertise.controllers.responses.TokenResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Authentication", description = "Endpoints para autenticação (Authentication)")
public interface IAuthController {

    @Operation(
            summary = "Efetuar autenticação",
            description = "Endpoint responsável por realizar a autenticação do Anunciante")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Anunciante autenticado com sucesso",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = TokenResponse.class))
                    }),
            @ApiResponse(
                    responseCode = "400",
                    description = "Quando houver falha nos dados enviados",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ApplicationErrorResponse.class))
                    }),
            @ApiResponse(
                    responseCode = "401",
                    description = "Quando houver falha de autenticação",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ApplicationErrorResponse.class))
                    }),
            @ApiResponse(
                    responseCode = "500",
                    description = "Erro interno do servidor",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ApplicationErrorResponse.class))
                    }),
    })
    TokenResponse login(LoginRequest loginRequest);
}
