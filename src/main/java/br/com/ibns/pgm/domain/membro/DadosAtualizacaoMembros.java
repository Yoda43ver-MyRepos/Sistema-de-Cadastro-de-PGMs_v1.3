package br.com.ibns.pgm.domain.membro;

import br.com.ibns.pgm.domain.endereco.DadosEndereco;

public record DadosAtualizacaoMembros(
        Long id,
        String nome,
        String telefone,
        String email,
        String aniversario,
        DadosEndereco endereco
) {
}
