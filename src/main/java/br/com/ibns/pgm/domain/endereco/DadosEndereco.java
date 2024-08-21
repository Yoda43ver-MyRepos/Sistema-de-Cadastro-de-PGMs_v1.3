package br.com.ibns.pgm.domain.endereco;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record DadosEndereco(

        @NotBlank (message="{logradouro.obrigatorio}")
        @Size(min = 3, max = 100, message = "{logradouro.tamanho}")
        String logradouro,

        @NotBlank (message="{bairro.obrigatorio}")
        String bairro,

        @NotBlank (message="{cep.obrigatorio}")
        @Pattern(regexp = "\\d{5}-\\d{3}", message="{cep.formato}")
        String cep,

        @NotBlank
        String numero,

        String complemento,

        @NotBlank (message ="{cidade.obrigatorio}")
        String cidade,

        @NotBlank(message="uf.obrigatorio")
        @Size(min=2, max=2, message="{uf.tamanho}")
        String uf
) {
}
