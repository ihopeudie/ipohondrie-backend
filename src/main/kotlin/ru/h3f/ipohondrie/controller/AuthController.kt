package ru.h3f.ipohondrie.controller

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.h3f.ipohondrie.model.dto.UserRequestDto
import ru.h3f.ipohondrie.service.AuthService

@RestController
@RequestMapping("api/v1/auth/")
class AuthController(
    private val authService: AuthService
) {

    @PostMapping("/register")
    @Operation(summary = "Register new user")
    @ApiResponses(value = [ApiResponse(responseCode = "200", description = "register success")])
    fun registerUser(request: UserRequestDto) {
        authService.registerUser(request)
    }

    @PostMapping("/get-token")
    @Operation(summary = "Create and get token for user")
    @ApiResponses(value = [ApiResponse(responseCode = "200", description = "register success")])
    fun getOrCreateToken(request: UserRequestDto): String {
        return authService.getOrCreateToken(request)
    }

}
