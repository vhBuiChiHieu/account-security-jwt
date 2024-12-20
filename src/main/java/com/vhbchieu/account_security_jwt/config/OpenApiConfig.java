package com.vhbchieu.account_security_jwt.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;

//cấu hình toàn cục
@OpenAPIDefinition(
        info = @Info(
                contact = @Contact(
                        name = "vhbchieu",
                        url = "https://facebook.com/vhBchieu",
                        email = "vh.bchieu@gmail.com"
                ),
                description = "OpenApi documentation for Spring Boot Application",
                title = "OpenApi Specification - vhbchieu",
                version = "1.0",
                license = @License(
                        name = "License name",
                        url = "https://some-url-license.com"
                ),
                termsOfService = "Temp of service"
        ),
        //server
        servers = {
                @Server(
                        description = "Local env",
                        url = "http://localhost/api"
                )
        }
        //
//        security = {
//                @SecurityRequirement(name = "bearerAuth")
//        }
)
// config schema security
@SecurityScheme(
        name = "bearerAuth",
        description = "Jwt auth description",
        scheme = "bearer",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        //token header
        in = SecuritySchemeIn.HEADER
)
public class OpenApiConfig {

}
