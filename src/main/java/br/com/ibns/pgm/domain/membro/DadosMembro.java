package br.com.ibns.pgm.domain.membro;

import br.com.ibns.pgm.domain.endereco.DadosEndereco;
import jakarta.validation.constraints.*;

public record DadosMembro(

        @Size(min = 3, max = 100, message="{nome.tamanho}")
        @NotBlank(message = "{nome.obrigatorio}")
        String nome,

        @NotBlank(message = "{telefone.obrigatorio}")
        String telefone,

        @NotBlank(message = "{email.obrigatorio}")
        @Email(message = "{email.formato}")
        String email,

        @Pattern(regexp = "^\\d{2}/\\d{2}$", message = "{aniversario.formato}")
        String aniversario,

        @NotNull
        DadosEndereco endereco
) {
}
