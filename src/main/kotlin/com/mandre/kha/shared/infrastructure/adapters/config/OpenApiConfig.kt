package com.mandre.kha.shared.infrastructure.adapters.config

import io.swagger.v3.oas.annotations.OpenAPIDefinition
import io.swagger.v3.oas.annotations.info.Contact
import io.swagger.v3.oas.annotations.info.Info
import io.swagger.v3.oas.annotations.servers.Server
import org.springframework.context.annotation.Configuration

@Configuration
@OpenAPIDefinition(
    info = Info(
        title = "\${api.server.title}",
        version = "\${api.version}",
        contact = Contact(name = "\${api.contact.name}", email = "\${api.contact.email}", url = "\${api.contact.url}"),
        description = "\${api.description}"
    ), servers = [Server(url = "\${api.server.url}", description = "\${api.server.description}")]
)
class OpenAPISecurityConfiguration