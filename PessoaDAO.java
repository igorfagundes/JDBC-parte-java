package jdbcesquema;

import java.sql.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


//carregar drivers de conexao
public class PessoaDAO {
	Pessoa pessoa = new Pessoa();
	static Connection conn;
	static ResultSet rs;
	static PreparedStatement pstmt;
	public static Connection getConexao() {
		conn = null;
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost/cadastramento","root","");
			System.out.println("Banco de dados conectado");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	//metodo para cadastrar[1]
	public static void cadastrar(Pessoa pessoa) throws SQLException {
		try {
		String query = "INSERT INTO pessoa(nome, telefone) VALUES(?, ?)";
		pstmt = conn.prepareStatement(query);
		pstmt.setString(1, pessoa.getNome());
		pstmt.setString(2, pessoa.getTelefone());
		pstmt.execute();
		System.out.println("Cadastro realizado com sucesso!");
		}catch(SQLException error) {
			error.printStackTrace();
			System.out.println("Erro em cadastrar!");
		}
	}
	//metodo para atualizar cadastro[2]
	public static void atualizar(Pessoa pessoa) throws SQLException {
		try {
		String query = "UPDATE pessoa SET nome = ? WHERE id = ?";
		pstmt = conn.prepareStatement(query);
		pstmt.setString(1, pessoa.getNome());
		pstmt.setInt(2, pessoa.getId());
		pstmt.execute();
		System.out.println("Atualizacao em ID: " + pessoa.getId() + " realizado com sucesso!");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Erro ao atualizar!");
		}
	}
	//metodo para remover[3]
	public static void remover(Pessoa pessoa)  throws SQLException{
		try {
		String query = "DELETE FROM pessoa WHERE id = ?";
		pstmt = conn.prepareStatement(query);
		pstmt.setInt(1, pessoa.getId());
		pstmt.execute();
		System.out.println("Cadastro ID Numero: " + pessoa.getId() + " removido com sucesso!");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Erro em remover!");
		}
	}
	//metodo para mostrar todos cadastros[4]
	public static ArrayList<Pessoa> retornarLista(){
		ArrayList<Pessoa> pessoaList = new ArrayList<>();
		String query = "SELECT * FROM pessoa";
		try {
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery(query);
			while(rs.next()) {
				Pessoa pessoa = new Pessoa();
				pessoa.setNome(rs.getString("nome"));
				pessoa.setId(rs.getInt("id"));
				pessoa.setTelefone(rs.getString("telefone"));
				pessoaList.add(pessoa);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pessoaList;
	}
	//metodo para mostrar lista filtrada[5]
	public static void listaFiltrada(int escolha) throws SQLException {
		if(escolha == 1) {
		String query = "SELECT nome FROM pessoa";
		pstmt = conn.prepareStatement(query);
		rs = pstmt.executeQuery();
		while(rs.next()) {
			String nome = rs.getString("nome");
			System.out.println("NOME: " + nome);
		}
		}else if(escolha == 2) {
			String query = "SELECT id FROM pessoa";
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				int id = rs.getInt("id");
				System.out.println("ID: " + id);
			}
		}else if(escolha == 3) {
			String query = "SELECT telefone FROM pessoa";
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				String telefone = rs.getString("telefone");
				System.out.println("TELEFONE: " + telefone);
			}
		}else if(escolha == 4) {
			String query = "SELECT nome, telefone FROM pessoa";
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				String nome = rs.getString("nome");
				String telefone = rs.getString("telefone");
				System.out.println("NOME: " + nome + " -" + " TELEFONE: " + telefone);
			}
		}
	}
}