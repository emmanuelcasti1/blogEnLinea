package com.example.blogEnLinea.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"username", "mensaje", "token", "status"})
public record AuthResponseDTO(String username, String mensaje, String token, boolean status) {
}
