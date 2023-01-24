import java.time.Duration;
import java.time.LocalTime;

public class CadastroSaidaVeiculo {

	private LocalTime horarioSaida;
	private Veiculo veiculo;
	private float precoAPagar;

	public CadastroSaidaVeiculo(Veiculo veiculo, LocalTime horarioEntrada) {
		this.horarioSaida = LocalTime.now();
		this.veiculo = veiculo;
		calculaPrecoTotal(horarioEntrada);
	}

	public LocalTime getHorarioSaida() {
		return horarioSaida;
	}

	public Veiculo getVeiculo() {
		return veiculo;
	}

	public float getPrecoAPagar() {
		return precoAPagar;
	}

	private void calculaPrecoTotal(LocalTime horarioEntrada) {
		long tempoOcupacaoVagaEmMinutos = Duration.between(horarioEntrada, horarioSaida).toMinutes();

		int tempoOcupacaoVagaEmHoras = (int) tempoOcupacaoVagaEmMinutos / 60;

		if (tempoOcupacaoVagaEmMinutos % 60 > 0) {
			tempoOcupacaoVagaEmHoras++;
		}

		if (veiculo instanceof Carro) {
			this.precoAPagar = ControleEstacionamento.PRECO_VAGA_CARRO * tempoOcupacaoVagaEmHoras;
		} else if (veiculo instanceof Motocicleta) {
			this.precoAPagar = ControleEstacionamento.PRECO_VAGA_MOTOCICLETA * tempoOcupacaoVagaEmHoras;
		}

	}

}