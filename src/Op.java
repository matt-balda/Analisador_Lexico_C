public class Op {
	public static boolean isAux(char c) {
		if (c == '&' || c == '|' || c == '(' || c == ')' || c == '[' || c == ']' || c == '{' || c == '}' || c == ';'
			|| c == ',' || c == '~' || c == '^' || c == ' ' || c == '\n' || c == '\t' || c == '"' || c == '%' || c == '#') {
			return true;
		}
		return false;
	}
	
	public static boolean isSpace(char c) {
		return c == ' ';
	}

	public static boolean isOp(char c) {
		return c == '+' || c == '-' || c == '*' || c == '/' || c == '=';
	}

	public static boolean isPoint(char c) {
		return c == '.';
	}

	public static boolean isComparation(char c) {
		return c == '>' || c == '<' || c == '!' || c == '=';
	}
	
	public static boolean isVariable(char c) {
		return(c >= 'a' && c <='z') || (c >= 'A' && c <= 'Z') || c == '_';
	}
	
	public static boolean isVariable2(String c) {
		return c.equals(" ");
	}
	public static boolean isNumber(char c ) {
		return c >= 0 && c <= 9;
	}
	
	public static boolean isId(String c) {
		return c.equals("char") || c.equals("float") || c.equals("double") || c.equals("int") || c.equals("if") || c.equals("else") || c.equals("while") || c.equals("for") || c.equals("return") || c.equals("break") || c.equals("continue") || c.equals("printf") || c.equals("scanf") || c.equals("include") || c.equals("stdio") || c.equals("h") || c.equals("main");
	}
}