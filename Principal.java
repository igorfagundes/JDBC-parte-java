package jdbcesquema;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class Principal {
	
	public static void main(String[] args) throws SQLException {
		PessoaDAO.getConexao();
		int op = -1;
		int ex = op;
		while(op != 0) {
			op = menu();
			if(op == 1){
				Scanner scan = new Scanner(System.in);
				Pessoa pessoa = new Pessoa();
				System.out.println("Digite o nome");
				System.out.print("opcao: ");
				pessoa.setNome(scan.nextLine());
				
				System.out.println("Digite o telefone");
				System.out.print("opcao: ");
				pessoa.setTelefone(scan.nextLine());
			    PessoaDAO.cadastrar(pessoa);
			}else if(op == 2) {
				Scanner scan = new Scanner(System.in);
				Pessoa pessoa = new Pessoa();
				System.out.println("Digite o novo nome");
				System.out.print("opcao: ");
				pessoa.setNome(scan.nextLine());
				
				System.out.println("Digite o id do antigo nome");
				System.out.print("opcao: ");
				pessoa.setId(scan.nextInt());
			    PessoaDAO.atualizar(pessoa);
			}else if(op == 3) {
				Scanner scan = new Scanner(System.in);
				Pessoa pessoa = new Pessoa();
				System.out.println("Digite o id que deseja remover");
				System.out.print("opcao: ");
				pessoa.setId(scan.nextInt());
			    PessoaDAO.remover(pessoa);
			}else if(op == 4) {
				imprimirTodos();
			}else if(op == 5) {
				menuListaFiltrada();
			}else {
				System.out.println("Obrigado por usar meu teste JDBC!");
			}
			
		}
	}
	public static int menu() {
		Scanner scan = new Scanner(System.in);
		System.out.println("==========================");
		System.out.println("==         MENU         ==");
		System.out.println("==========================");
		System.out.println("== [1] CADASTRAR        ==");
		System.out.println("== [2] ATUALIZAR        ==");
		System.out.println("== [3] REMOVER          ==");
		System.out.println("== [4] RETORNAR LISTA   ==");
		System.out.println("== [5] LISTA FILTRADA   ==");
		System.out.println("==========================");
		System.out.println("== [0] SAIR             ==");
		System.out.println("==========================");
		
		System.out.print(" opcao: ");
		return scan.nextInt();
	}
	public static void imprimirTodos() {
		System.out.println("======================");
		System.out.println("== RETORNANDO LISTA ==");
		System.out.println("======================");
		ArrayList<Pessoa> pessoaList = PessoaDAO.retornarLista();
		for(int i = 0;i < pessoaList.size();i++) {
		System.out.println("ID: " + pessoaList.get(i).getId() + " -" + " NOME: " + pessoaList.get(i).getNome() + " -" + " TELEFONE: " + pessoaList.get(i).getTelefone());
		}
		System.out.println("======================");
	}
	public static void menuListaFiltrada() throws SQLException {
		Scanner scan = new Scanner(System.in);
		System.out.println("===================================");
		System.out.println("==       MENU DE FILTRAGEM       ==");
		System.out.println("===================================");
		System.out.println("== [1] MOSTRAR APENAS NOMES      ==");
		System.out.println("== [2] MOSTRAR APENAS ID         ==");
		System.out.println("== [3] MOSTRAR APENAS TELEFONE   ==");
		System.out.println("== [4] NOME E TELEFONE           ==");
		System.out.println("===================================");
		System.out.println("== [0] MENU ANTERIOR             ==");
		System.out.println("===================================");
		
		System.out.print("opcao: ");
		int opc = scan.nextInt();
		if(opc == 0) {
			menu();
		}
		PessoaDAO.listaFiltrada(opc);
	}

}
