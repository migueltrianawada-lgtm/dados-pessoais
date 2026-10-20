package br.senac.tads.dsw.dados_pessoais;

import java.time.LocalDate; // ADICIONADO
import java.util.ArrayList; // ADICIONADO
import java.util.List;      // ADICIONADO
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import org.springframework.stereotype.Service;
import jakarta.annotation.PostConstruct;

@Service
public class PessoaService {

	private AtomicInteger contador = new AtomicInteger(0);
	private Map<String, PessoaDto> mapPessoas = new ConcurrentHashMap<>();

	@PostConstruct
	public void init() {
		mapPessoas.put("fulano", new PessoaDto(
			contador.incrementAndGet(),
			"fulano",
			"Fulano da Silva",
			"fulano@email.com",
			"(11) 99999-1234",
			LocalDate.parse("2000-10-20")
		));

		mapPessoas.put("ciclano", new PessoaDto(
			contador.incrementAndGet(),
			"ciclano",
			"Ciclano de Souza",
			"ciclano@email.com",
			"(11) 98888-5678",
			LocalDate.parse("1999-05-10")
		));

		mapPessoas.put("beltrana", new PessoaDto(
			contador.incrementAndGet(),
			"beltrana",
			"Beltrana dos Santos",
			"beltrana@email.com",
			"(11) 97777-9012",
			LocalDate.parse("2001-02-23")
		));
	}

	public List<PessoaDto> obterPessoas() {
		return new ArrayList<>(mapPessoas.values());
	}

	public Optional<PessoaDto> obterPessoa(String username) {
		return Optional.ofNullable(mapPessoas.get(username));
	}

	public PessoaDto incluirNovaPessoa(PessoaDto pessoa){
		pessoa.setId((contador.incrementAndGet()));
		mapPessoas.put(pessoa.getUsername(),pessoa);
		return pessoa;
	}

	public PessoaDto alterarPessoa(String username, PessoaAlteracaoDto pessoaAlteracao){
		if(!mapPessoas.containsKey(username)){
			throw new NaoEncontradoException("Pessoa "+ username + " Não encontrada");
		}
		PessoaDto pessoaDtoOriginal = mapPessoas.get(username);
		pessoaDtoOriginal.setNome(pessoaAlteracao.getNome());
		pessoaDtoOriginal.setEmail(pessoaAlteracao.getEmail());
		pessoaDtoOriginal.setTelefone(pessoaAlteracao.getTelefone());
		pessoaDtoOriginal.setDataNascimento(pessoaAlteracao.getDataNascimento()); // Corrigido para "Nascimento"
		pessoaDtoOriginal.setConhecimentos(pessoaAlteracao.getConhecimentos());   // Corrigido para "Conhecimentos"

		return pessoaDtoOriginal;
	}

	public void removerPessoa(String username){
		if(!mapPessoas.containsKey(username)){
			throw new NaoEncontradoException("Pessoa "+ username + " Não encontrada");
		}

		mapPessoas.remove(username);
	}
}
