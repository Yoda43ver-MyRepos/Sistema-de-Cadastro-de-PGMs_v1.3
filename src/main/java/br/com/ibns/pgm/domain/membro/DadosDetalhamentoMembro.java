package br.com.ibns.pgm.domain.membro;

import br.com.ibns.pgm.domain.endereco.Endereco;

public record DadosDetalhamentoMembro(
        Long id,
        String nome,
        String telefone,
        String email,
        String aniversario,
        Endereco endereco,
        Boolean ativo
) {
    public DadosDetalhamentoMembro(Membro membro){
        this(
                membro.getId(),
                membro.getNome(),
                membro.getTelefone(),
                membro.getEmail(),
                membro.getAniversario(),
                membro.getEndereco(),
                membro.getAtivo()
        );
    }

}
