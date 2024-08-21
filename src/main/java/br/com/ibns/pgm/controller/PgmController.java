package br.com.ibns.pgm.controller;

import br.com.ibns.pgm.domain.pgm.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;


@RestController
@RequestMapping("pgms")
public class PgmController {

    @Autowired
    private PgmRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrarPgm(@RequestBody @Valid DadosPgm dados, UriComponentsBuilder uriBuilder) {
        var pgm =  new Pgm(dados);
         repository.save(pgm);
         var uri = uriBuilder.path("/pgms/{id}").buildAndExpand(pgm.getId()).toUri();
         return ResponseEntity.created(uri).body(new DadosDetalhamentoPgm(pgm));
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemPgms>> listarPgms(@PageableDefault(size =10, sort ={"nome"}) Pageable pagination) {
        var page = repository.findAllByAtivoTrue(pagination).map(DadosListagemPgms::new);
        return ResponseEntity.ok(page);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluirPgm(@PathVariable Long id) {
        var pgm = repository.getReferenceById(id);
        pgm.inativarPgm();
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/activate/{id}")
    @Transactional
    public ResponseEntity tornarPgmAtivo(@PathVariable Long id) {
        var pgm = repository.getReferenceById(id);
        pgm.activatePgm();
        return ResponseEntity.noContent().build();
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizarPgmDados(@RequestBody @Valid DadosAtualizacaoPgms dados){
        var pgm = repository.getReferenceById(dados.id());
        pgm.atualizarDadosPgm(dados);
        return ResponseEntity.ok(new DadosDetalhamentoPgm(pgm));
    }

    @GetMapping("/{id}")
    public ResponseEntity detalharPgm(@PathVariable Long id) {
        var pgm = repository.getReferenceById(id);
        return ResponseEntity.ok(new DadosDetalhamentoPgm(pgm));
    }




}
