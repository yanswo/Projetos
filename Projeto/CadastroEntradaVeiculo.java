import java.time.LocalTime;

public class CadastroEntradaVeiculo{

	private LocalTime horarioEntrada;
	private Veiculo veiculo;

	public CadastroEntradaVeiculo(Veiculo veiculo) {
		this.horarioEntrada = LocalTime.now();
		this.veiculo = veiculo;
	}

	public LocalTime getHorarioEntrada() {
		return horarioEntrada;
	}

	public Veiculo getVeiculo() {
		return veiculo;
	}

}
