import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Le_token {
	private BufferedReader arq;
	private String linha;
	private int nLinha;
	private int pLinha;

	public Le_token() {
		this.arq = null;
		this.linha = "";
		this.nLinha = 0;
		this.pLinha = 0;
	}

	public Le_token(String nomeArquivo) throws IOException {
		this.arq = new BufferedReader(new FileReader(nomeArquivo));
		this.linha = arq.readLine().concat("\n");
		this.nLinha = 1;
		this.pLinha = 0;
	}

	public void abreArquivo(String nomeArquivo) throws IOException {
		this.arq = new BufferedReader(new FileReader(nomeArquivo));
		this.linha = arq.readLine().concat("\n");
		this.nLinha = 1;
		this.pLinha = 0;
	}

	public String exibeArquivo(String nomeArquivo) throws IOException {
		String txtFile = null;
		try {
			txtFile = new String(Files.readAllBytes(Paths.get(nomeArquivo)), StandardCharsets.UTF_8);
		} catch (Exception ex) {
			ex.getMessage();
		}
		return txtFile;
	}

	public void fechaArquivo() throws IOException {
		this.arq.close();
		this.linha = "";
		this.nLinha = 0;
		this.pLinha = 0;
	}

	private char getChar() {
		if (linha == null)
			return 0;

		if (pLinha == linha.length()) {
			try {
				linha = arq.readLine();
				if (linha == null) {
					nLinha++;
					return 0;
				}
				linha = linha.concat("\n");
				nLinha++;
				pLinha = 0;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		char ch = linha.charAt(pLinha);
		pLinha++;
		return ch;
	}

	private boolean verificaProximoChar(char ch) {
		char nextChar = getChar();
		return ch == nextChar;
	}

	private void getNewLine() {
		try {
			linha = arq.readLine();
			if (linha != null)
				linha.concat("\n");
			pLinha = 0;
			nLinha++;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Token getToken() {
		Token token = null;
		Estados estado = Estados.NcharEsp;
		String lex = "";
		char ch;

		while (token == null) {
			switch (estado) {
			case NcharEsp:
				ch = getChar();
				switch (ch) {
				case ';':
					token = new Token(Tokens.Ponto_Virgula, ";", nLinha);
					break;
				case ',':
					token = new Token(Tokens.Virgula, ",", nLinha);
					break;
				case '#':
					token = new Token(Tokens.cerquilha, "#", nLinha);
					break;
				case '.':
					token = new Token(Tokens.ponto, ".", nLinha);
					break;

				case '{':
					token = new Token(Tokens.Abre_Chave, "{", nLinha);
					break;
				case '}':
					token = new Token(Tokens.Fecha_Chave, "}", nLinha);
					break;
				case '(':
					token = new Token(Tokens.Abre_Parenteses, "(", nLinha);
					break;
				case ')':
					token = new Token(Tokens.Fecha_Parenteses, ")", nLinha);
					break;
				case '[':
					token = new Token(Tokens.Abre_Colchete, "[", nLinha);
					break;
				case ']':
					token = new Token(Tokens.Fecha_Colchete, "]", nLinha);
					break;
				case '*':
					token = new Token(Tokens.OPMultiplicacao, "*", nLinha);
					break;
				case '=':
					if (verificaProximoChar('='))
						token = new Token(Tokens.OPIgual, "==", nLinha);
					else {
						pLinha--;
						token = new Token(Tokens.OPAtribuicao, "=", nLinha);
					}
					break;
				case '|':
					if (verificaProximoChar('|'))
						token = new Token(Tokens.OPORLOG, "||", nLinha);
					else {
						pLinha--;
						token = new Token(Tokens.OPOR, "|", nLinha);
					}
					break;
				case '&':
					if (verificaProximoChar('&'))
						token = new Token(Tokens.OPANDLOG, "&&", nLinha);
					else {
						pLinha--;
						token = new Token(Tokens.OPAND, "&", nLinha);
					}
					break;
				case '>':
					if (verificaProximoChar('='))
						token = new Token(Tokens.OPMaiorIgual, ">=", nLinha);
					else {
						pLinha--;
						token = new Token(Tokens.OPMaior, ">", nLinha);
					}
					break;
				case '<':
					if (verificaProximoChar('='))
						token = new Token(Tokens.OPMenorIgual, "<=", nLinha);
					else {
						pLinha--;
						token = new Token(Tokens.OPMenor, "<", nLinha);
					}
					break;
				case '+':
					if (verificaProximoChar('+'))
						token = new Token(Tokens.OPIncrementa, "++", nLinha);
					else {
						pLinha--;
						token = new Token(Tokens.OPSoma, "+", nLinha);
					}
					break;
				case '-':
					if (verificaProximoChar('-'))
						token = new Token(Tokens.OPDecrementa, "--", nLinha);
					else {
						pLinha--;
						token = new Token(Tokens.OPSubtracao, "-", nLinha);
					}
					break;
				case '/':
					if (verificaProximoChar('/')) {
						getNewLine();
						continue;
					} else {
						pLinha--;
						token = new Token(Tokens.OPDivisao, "/", pLinha);
					}
					break;
				case '%':
					token = new Token(Tokens.OPResto_Divisao, "%", pLinha);
					break;
				case '!':
					if (verificaProximoChar('='))
						token = new Token(Tokens.OPDiferente, "!=", nLinha);
					else {
						pLinha--;
						token = new Token(Tokens.OPNOTLOG, "!", nLinha);
					}
					break;
				case '~':
					token = new Token(Tokens.OPNOTBIN, "~", nLinha);
					break;
				default:
					if (ch == ' ' || ch == '\n' || ch == '\t')
						continue;
					else if (ch == 0)
						token = new Token(Tokens.Fim, "", nLinha);
					else if (ch == '"') {
						estado = Estados.Nstring;
						lex = String.valueOf(ch);
					} else if (Character.isLetter(ch)) {
						estado = Estados.ID;
						lex = String.valueOf(ch);
					} else if (Character.isDigit(ch)) {
						estado = Estados.Nint;
						lex = String.valueOf(ch);
					} else {
						estado = Estados.ERRO;
						lex = String.valueOf(ch);
					}
					break;
				}
				break;

			case Nint:
				ch = getChar();
				if (Op.isOp(ch) || Op.isAux(ch) || Op.isComparation(ch)) {
					token = new Token(Tokens.NumInt, lex, nLinha);
					pLinha--;
				} else {
					if (ch == '.') {
						estado = Estados.Nreal;
						lex += String.valueOf('.');
						continue;
					} else if (ch == 'e') {
						estado = Estados.Ndouble;
						lex += String.valueOf('e');
						continue;
					} else if (Character.isDigit(ch))
						lex += String.valueOf(ch);
					else {
						estado = Estados.ERRO;
						lex += String.valueOf(ch);
						continue;
					}
				}
				break;

			case Nreal:
				ch = getChar();
				if (Op.isOp(ch) || Op.isAux(ch) || Op.isComparation(ch)) {
					token = new Token(Tokens.NumFloat, lex, nLinha);
					pLinha--;
				} else {
					if (ch == 'e') {
						estado = Estados.Ndouble;
						lex += String.valueOf('e');
						continue;
					} else if (Character.isDigit(ch))
						lex += String.valueOf(ch);
					else {
						estado = Estados.ERRO;
						lex += String.valueOf(ch);
						continue;
					}
				}
				break;

			case Ndouble:
				ch = getChar();
				if ((Op.isOp(ch) || Op.isAux(ch) || Op.isComparation(ch)) && lex.charAt(lex.length() - 1) != 'e') {
					if (Character.isDigit(lex.charAt(lex.length() - 1)))
						token = new Token(Tokens.NumDouble, lex, nLinha);
					else
						token = new Token(Tokens.ERRO, lex, nLinha);

					pLinha--;
				} else {
					if (ch == '+' || ch == '-') {
						if (lex.charAt(lex.length() - 1) == 'e') {
							lex += String.valueOf(ch);
						} else {
							lex += String.valueOf(ch);
							estado = Estados.ERRO;
							continue;
						}
					} else if (Character.isDigit(ch))
						lex += String.valueOf(ch);
					else {
						lex += String.valueOf(ch);
						estado = Estados.ERRO;
						continue;
					}
				}
				break;

			case ID:
				ch = getChar();
				if (Op.isOp(ch) || Op.isAux(ch) || Op.isPoint(ch) || Op.isComparation(ch)) {
					switch (lex) {
					case "char":
						token = new Token(Tokens.Char, lex, nLinha);
						break;
					case "float":
						token = new Token(Tokens.Float, lex, nLinha);
						break;
					case "double":
						token = new Token(Tokens.Double, lex, nLinha);
						break;
					case "int":
						token = new Token(Tokens.Int, lex, nLinha);
						break;
					case "if":
						token = new Token(Tokens.IF, lex, nLinha);
						break;
					case "else":
						token = new Token(Tokens.Else, lex, nLinha);
						break;
					case "while":
						token = new Token(Tokens.While, lex, nLinha);
						break;
					case "for":
						token = new Token(Tokens.For, lex, nLinha);
						break;
					case "return":
						token = new Token(Tokens.Return, lex, nLinha);
						break;
					case "break":
						token = new Token(Tokens.Break, lex, nLinha);
						break;
					case "continue":
						token = new Token(Tokens.Continue, lex, nLinha);
						break;
					case "printf":
						token = new Token(Tokens.Printf, lex, nLinha);
						break;
					case "scanf":
						token = new Token(Tokens.Scanf, lex, nLinha);
						break;
					case "include":
						token = new Token(Tokens.include, lex, nLinha);
						break;
					case "stdio":
						token = new Token(Tokens.stdio, lex, nLinha);
						break;
					case "h":
						token = new Token(Tokens.h, lex, nLinha);
						break;
					case "main":
						token = new Token(Tokens.main, lex, nLinha);
						break;
					case "var1":
						token = new Token(Tokens.ID, lex, nLinha);
						break;
					case "var2":
						token = new Token(Tokens.ID, lex, nLinha);
						break;
					case "var3":
						token = new Token(Tokens.ID, lex, nLinha);
						break;
					case "var4":
						token = new Token(Tokens.ID, lex, nLinha);
						break;
					case "var5":
						token = new Token(Tokens.ID, lex, nLinha);
						break;
					default:
						token = new Token(Tokens.ERRO, lex, nLinha);
						break;
					}
					pLinha--;
				} else {
					if (Op.isVariable(ch)) {
						lex += String.valueOf(ch);
						break;
					} else {
						lex += String.valueOf(ch);
						break;
					}
				}
				break;
			case Nstring:
				ch = getChar();
				if ((ch == '"' && lex.charAt(lex.length() - 1) != '\\') || (ch == '"'
						&& lex.charAt(lex.length() - 1) == '\\' && lex.charAt(lex.length() - 2) == '\\')) {
					lex += String.valueOf(ch);
					token = new Token(Tokens.STR, lex, nLinha);
				} else {
					if (ch == '\n') {
						estado = Estados.ERRO;
						continue;
					} else {
						lex += String.valueOf(ch);
					}
				}
				break;

			case ERRO:
				ch = getChar();
				if (Op.isOp(ch)) {
					token = new Token(Tokens.ERRO, lex, nLinha);
					pLinha--;
				} else
					lex += String.valueOf(ch);
				break;
			}
		}
		return token;
	}
}