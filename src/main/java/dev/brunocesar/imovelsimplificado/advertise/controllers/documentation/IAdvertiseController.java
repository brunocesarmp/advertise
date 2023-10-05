package dev.brunocesar.imovelsimplificado.advertise.controllers.documentation;

import dev.brunocesar.imovelsimplificado.advertise.configs.handler.ApplicationErrorResponse;
import dev.brunocesar.imovelsimplificado.advertise.configs.security.AdvertiseUserDetails;
import dev.brunocesar.imovelsimplificado.advertise.controllers.requests.AdvertiseRequest;
import dev.brunocesar.imovelsimplificado.advertise.controllers.requests.NewAdvertiseRequest;
import dev.brunocesar.imovelsimplificado.advertise.controllers.responses.AdvertiseResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Advertise", description = "Endpoints para gerenciar cadastro de Anunciante (Advertise)")
public interface IAdvertiseController {

    @Operation(
            summary = "Criar um novo Anunciante",
            description = "Endpoint responsável por realizar a criação de um Anunciante")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Anunciante criado com sucesso",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = AdvertiseResponse.class))
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
    AdvertiseResponse save(NewAdvertiseRequest request);

    @Operation(
            summary = "Buscar dados do Anunciante logado",
            description = "Endpoint responsável por realizar a busca dos dados do Anunciante logado")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Anunciante encontrado com sucesso",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = AdvertiseResponse.class))
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
    AdvertiseResponse get(AdvertiseUserDetails advertiseUserDetails);

    @Operation(
            summary = "Atualizar dados do Anunciante logado",
            description = "Endpoint responsável por realizar a atualização de dados de um Anunciante")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Anunciante atualizado com sucesso",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = AdvertiseResponse.class))
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
    AdvertiseResponse update(AdvertiseUserDetails advertiseUserDetails,
                             AdvertiseRequest request);
}
