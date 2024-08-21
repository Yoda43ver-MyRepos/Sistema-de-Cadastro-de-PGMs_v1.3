package br.com.ibns.pgm.controller;

import br.com.ibns.pgm.domain.membro.*;
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
@RequestMapping("membros")
public class MembroController {

    @Autowired
    private MembroRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrarMembro(@RequestBody @Valid DadosMembro dados, UriComponentsBuilder uriBuilder){
       var membro =  new Membro(dados);
        repository.save(membro);
        var uri = uriBuilder.path("/membros/{id}").buildAndExpand(membro.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhamentoMembro(membro));
    }

    @GetMapping
    public ResponseEntity <Page<DadosListagemMembros>> listarMembros(@PageableDefault(size =10, sort ={"nome"}) Pageable pagination){
        var page =  repository.findAllByAtivoTrue(pagination).map(DadosListagemMembros::new);
        return  ResponseEntity.ok(page);
    }
    @GetMapping("/inativos")
    public ResponseEntity <Page<DadosListagemMembros>> listarMembrosInativos (@PageableDefault(size =10, sort ={"nome"}) Pageable pagination){
       var page = repository.findAllByAtivoFalse(pagination).map(DadosListagemMembros::new);
        return ResponseEntity.ok(page);
    }
    @PutMapping
    @Transactional
    public ResponseEntity atualizarMembro(@RequestBody @Valid DadosAtualizacaoMembros dados){
           var membro =  repository.getReferenceById(dados.id());
           membro.atualizarInformacoesMembro(dados);
           return  ResponseEntity.ok(new DadosDetalhamentoMembro(membro));
    }
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluirMembro(@PathVariable Long id){
          var membro = repository.getReferenceById(id);
          membro.inativar();
          return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity detalharMembro(@PathVariable Long id){
        var membro =  repository.getReferenceById(id);
        return ResponseEntity.ok(new DadosDetalhamentoMembro(membro));
    }
}
