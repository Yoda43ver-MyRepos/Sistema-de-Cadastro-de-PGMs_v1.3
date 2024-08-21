package br.com.ibns.pgm.domain.pgm;

import br.com.ibns.pgm.domain.endereco.Endereco;

public record DadosDetalhamentoPgm(
        Long id,
        String nome,
        String dia,
        String hora,
        Endereco endereco,
        Boolean ativo
) {

    public DadosDetalhamentoPgm(Pgm pgm){
        this(
                pgm.getId(),
                pgm.getNome(),
                pgm.getDia(),
                pgm.getHora(),
                pgm.getEndereco(),
                pgm.getAtivo()
        );
    }
}
