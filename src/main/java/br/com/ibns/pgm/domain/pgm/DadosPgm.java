package br.com.ibns.pgm.domain.pgm;

import br.com.ibns.pgm.domain.endereco.DadosEndereco;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record DadosPgm(

        @Size(min = 3, max = 100, message="{nome.tamanho}")
        @NotBlank(message = "{nome.obrigatorio}")
        String nome,

        @NotBlank (message="{dia.obrigatorio}")
        @Size(min = 2, max = 20, message="{nome.tamanho}")
        String dia,

        @NotBlank (message="{hora.obrigatorio}")
        @Size(min = 2, max = 15, message="{hora.tamanho}")
        String hora,

        @NotNull
        DadosEndereco endereco

) {
}
