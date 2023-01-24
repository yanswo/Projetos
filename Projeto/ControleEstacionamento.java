import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ControleEstacionamento {
	public static String tipoMoto = "Motocicleta";
	public static String tipoCarro = "Carro";

	public static final int QUANTIDADE_VAGAS_CARRO = 15;
	public static final int PRECO_VAGA_CARRO = 15;

	public static final int QUANTIDADE_VAGAS_MOTOCICLETA = 5;
	public static final int PRECO_VAGA_MOTOCICLETA = 10;

	private List<CadastroEntradaVeiculo> registrosEntrada;
	private List<CadastroSaidaVeiculo> registrosSaida;

	public ControleEstacionamento() {
		this.registrosEntrada = new ArrayList<>();
		this.registrosSaida = new ArrayList<>();
	}

	public void adicionarVeiculo(Veiculo veiculo) {
		boolean veiculoJaExiste = registrosEntrada.stream()
				.anyMatch(cev -> cev.getVeiculo().getPlaca().equals(veiculo.getPlaca()));

		if (veiculoJaExiste) {
			System.out.println("\nVeiculo ja cadastrado!");
			return;
		}

		if (veiculo instanceof Carro) {
			long quantidadeAtualCarro = registrosEntrada.stream().filter(cev -> cev.getVeiculo() instanceof Carro)
					.count();

			if (quantidadeAtualCarro == QUANTIDADE_VAGAS_CARRO) {
				System.out.println("\nNâo ha vagas para carro");
				return;
			}
		}

		if (veiculo instanceof Motocicleta) {
			long quantidadeAtualMotocicleta = registrosEntrada.stream()
					.filter(cev -> cev.getVeiculo() instanceof Motocicleta).count();

			if (quantidadeAtualMotocicleta == QUANTIDADE_VAGAS_MOTOCICLETA) {
				System.out.println("\nNao ha vagas para motocicleta");
				return;
			}
		}

		registrosEntrada.add(new CadastroEntradaVeiculo(veiculo));
	}

	public void exibirRelatorioOcupacao() {
		long quantidadeAtualCarro = registrosEntrada.stream().filter(cev -> cev.getVeiculo() instanceof Carro).count();
		long quantidadeAtualMotocicleta = registrosEntrada.stream()
				.filter(cev -> cev.getVeiculo() instanceof Motocicleta).count();

		System.out.println("\nQuantidade de vagas ocupadas de carro: " + quantidadeAtualCarro);
		exibirPlacas(registrosEntrada, tipoCarro);
		System.out.println(
				"Quantidade de vagas disponiveis de carro: " + (QUANTIDADE_VAGAS_CARRO - quantidadeAtualCarro));
		System.out.println("\nQuantidade de vagas ocupadas de motocicleta: " + quantidadeAtualMotocicleta);
		exibirPlacas(registrosEntrada, tipoMoto);
		System.out.println("Quantidade de vagas disponiveis de motocicleta: "
				+ (QUANTIDADE_VAGAS_MOTOCICLETA - quantidadeAtualMotocicleta));
	}

	public void removerVeiculo(String placa) {
		Optional<CadastroEntradaVeiculo> optionalCadastroEntrada = registrosEntrada.stream()
				.filter(cev -> cev.getVeiculo().getPlaca().equals(placa)).findFirst();

		if (optionalCadastroEntrada.isEmpty()) {
			System.out.println("\nVeiculo nao encontrado!");
			return;
		}

		CadastroEntradaVeiculo cadastroEntradaEncontrado = optionalCadastroEntrada.get();

		CadastroSaidaVeiculo cadastroSaidaVeiculo = new CadastroSaidaVeiculo(cadastroEntradaEncontrado.getVeiculo(),
				cadastroEntradaEncontrado.getHorarioEntrada());

		System.out.println("O valor a ser pago e de: " + cadastroSaidaVeiculo.getPrecoAPagar());

		registrosSaida.add(cadastroSaidaVeiculo);
		registrosEntrada.remove(cadastroEntradaEncontrado);
	}

	public void exibirRelatorioFinanceiro() {
		if (registrosSaida.isEmpty()) {
			System.out.println("Nao ha registro de saidas hoje.");
			return;
		}

		System.out.println("\nRelatorio financeiro: ");
		for (CadastroSaidaVeiculo registroSaida : registrosSaida) {
			System.out.printf("Placa: %s Valor pago: R$ %.2f\n", registroSaida.getVeiculo().getPlaca(),
					registroSaida.getPrecoAPagar());
		}
		System.out.println("");
	}

	private void exibirPlacas(List<CadastroEntradaVeiculo> registrosEntrada, String tipoVeiculo) {
		
		registrosEntrada.stream()
		.filter(cev -> cev.getVeiculo().getClass().getTypeName().equals(tipoVeiculo))
		.forEach(c ->  exibirPlaca(c.getVeiculo()));		
	}

	private void exibirPlaca(Veiculo veiculo) {
		System.out.printf("Placa do veiculo: %s\n", veiculo.getPlaca());
	}

}
