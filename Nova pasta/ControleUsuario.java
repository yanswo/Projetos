import java.util.Scanner;

public class ControleUsuario {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		ControleEstacionamento estacionamento = new ControleEstacionamento();

		int opcao = -1;

		System.out.println("Bem vindo ao estacionamento do seu ze");
		System.out.println("Precos:");
		System.out.println("Carro - 15.00 R$/Hora");
		System.out.println("Motocicleta - 10.00 R$/Hora");
		System.out.println("A Permanecia de menos de 1 minuto no estacionamento e de graca.");

		while (opcao != 0) {
			System.out.println("\nEscolha uma opção:");
			System.out.println("1 - Cadastrar de entrada de veiculo");
			System.out.println("2 - Relatorio de ocupação");
			System.out.println("3 - Cadastrar saida de veiculo");
			System.out.println("4 - Relatorio financeiro");
			System.out.println("0 - Encerrar sistema");
			try {
				opcao = Integer.parseInt(scan.nextLine());

				if (opcao == 1) {

					System.out.println("\nDigite a placa do veiculo");
					String placa = scan.nextLine();

					System.out.println("Digite o tipo de veiculo:");
					System.out.println("1 - Carro");
					System.out.println("2 - Motocicleta");
					System.out.println("0 - Cancelar");

					int opcaoCadastro = Integer.parseInt(scan.nextLine());

					if (opcaoCadastro != 1 && opcaoCadastro != 2) {
						System.out.println("\nOperacao cancelada");
						continue;
					}

					Veiculo veiculo;

					if (opcaoCadastro == 1) {
						veiculo = new Carro(placa);
					} else {
						veiculo = new Motocicleta(placa);
					}

					estacionamento.adicionarVeiculo(veiculo);
				} else if (opcao == 2) {
					estacionamento.exibirRelatorioOcupacao();
				} else if (opcao == 3) {
					System.out.println("\nDigite a placa do veiculo");
					String placa = scan.nextLine();

					estacionamento.removerVeiculo(placa);
				} else if (opcao == 4) {
					estacionamento.exibirRelatorioFinanceiro();
				}
			} catch (Exception ex) {
				System.out.println("Digite uma opcao valida!");
			}
		}
	}

}
