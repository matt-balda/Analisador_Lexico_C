import java.io.IOException;

public class Main {
	public static void main(String args[]) {
		Le_token lt = new Le_token();
		try {
			lt.abreArquivo("teste_passou.txt");
			String fileString = lt.exibeArquivo("teste_passou.txt");
			System.out.println("========Código Fonte========");
			System.out.println(fileString);
			System.out.println("============================");
			Token token;
			token = lt.getToken();
			while (token.getToken() != Tokens.Fim) {
					System.out.println(token);
					token = lt.getToken();
			}
			System.out.println(token);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
}