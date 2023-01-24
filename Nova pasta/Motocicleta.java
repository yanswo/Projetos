
public class Motocicleta extends Veiculo {

	private String marca;
	private String numeroChassi;

	public Motocicleta(String placa) {
		super(placa);
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getNumeroChassi() {
		return numeroChassi;
	}

	public void setNumeroChassi(String numeroChassi) {
		this.numeroChassi = numeroChassi;
	}

}
