package br.senac.tads.dsw.dados_pessoais;

import java.util.List;
import java.util.Optional;
import java.net.URI;



import jakarta.validation.Valid;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/pessoas")
public class PessoaController {

    private final PessoaService pessoaService;

    public PessoaController(PessoaService pessoaService) {
        this.pessoaService = pessoaService;
    }

    @GetMapping
    public List<PessoaDto> obterPessoas() {
        return pessoaService.obterPessoas();
    }


	// GET /pessoas/busca?q=<termo>
	@GetMapping("/busca")
	public ResponseEntity<List<PessoaDto>> buscarPessoas(@RequestParam("q") String termo) {
		return ResponseEntity.ok(pessoaService.buscarPessoas(termo));
	}


	@GetMapping("/{username}")
    public PessoaDto obterPessoa(@PathVariable("username") String username) { // <- Corrigido aqui
        Optional<PessoaDto> optPessoa = pessoaService.obterPessoa(username);

        if (optPessoa.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Pessoa não encontrada");
        }

        return optPessoa.get();
    }

	@PostMapping("/sem-validacao")
	public ResponseEntity<?>incluirNovo(@RequestBody PessoaDto pessoa){
		pessoaService.incluirNovaPessoa((pessoa));
		URI location = ServletUriComponentsBuilder
			.fromCurrentContextPath()
			.path("/pessoas/{username}")
			.buildAndExpand(pessoa.getUsername())
			.toUri();
		return ResponseEntity.created(location).build();
	}

	@PutMapping("/{username}")
	public ResponseEntity<?> alterar(@PathVariable("username") String username,

		 @RequestBody @Valid PessoaAlteracaoDto pessoa) {
		PessoaDto pessoaDtoAlterada = pessoaService.alterarPessoa(username, pessoa);
		return ResponseEntity.ok().body(pessoaDtoAlterada);
	}


		@DeleteMapping("/{username}")
		public ResponseEntity<?>remover(@PathVariable("username")String username){
		pessoaService.removerPessoa(username);
		return ResponseEntity.noContent().build();
		}

		@ExceptionHandler(NaoEncontradoException.class)
	    public ResponseEntity<ProblemDetail> tratarExcecao(NaoEncontradoException ex){
		//ProblemDetail - padrão RFC 7807 para representar erros HTTP
			ProblemDetail pd = ProblemDetail.forStatusAndDetail(
				HttpStatusCode.valueOf(404), ex.getMessage());
			return ResponseEntity.of(pd).build();

		}




	}




