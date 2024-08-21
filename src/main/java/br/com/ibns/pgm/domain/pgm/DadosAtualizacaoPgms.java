package br.com.ibns.pgm.domain.pgm;

import br.com.ibns.pgm.domain.endereco.DadosEndereco;
import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoPgms(
        @NotNull
        Long id,
        String nome,
        String dia,
        String hora,
        DadosEndereco endereco
) {
}
