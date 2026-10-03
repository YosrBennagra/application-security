package example.security;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RequestDto(
    @NotBlank
    @Size(max = 120)
    String displayName,

    @NotBlank
    @Email
    @Size(max = 254)
    String email
) {}
