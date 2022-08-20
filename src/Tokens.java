public enum Tokens{
    ERRO, Fim, Ponto_Virgula, Virgula, Char, Float, Double, Int, ID, NumInt, NumFloat, NumDouble,
    Abre_Chave, Fecha_Chave, Abre_Parenteses, Fecha_Parenteses, Abre_Colchete, Fecha_Colchete,
    IF, Else, While, For, Return, Break, Continue, OPMultiplicacao, OPAtribuicao,
    OPOR, OPAND, OPSoma, OPSubtracao, OPDivisao, OPResto_Divisao,OPIncrementa, OPDecrementa,
    STR, OPIgual, OPDiferente, OPMaior, OPMaiorIgual, OPMenorIgual, OPMenor,
    OPORLOG, OPANDLOG, OPNOTLOG,OPNOTBIN, Printf, Scanf, include, cerquilha, ponto, stdio, h, main;
    
    @Override
    public String toString(){
        switch(this){
            case ERRO:
                return "Token inválido";
            case Fim:
                return "Final de Arquivo";
            case Ponto_Virgula:
                return "';'";
            case Virgula:
                return "','";
            case Char:
                return "identificador 'char'";
            case Float:
                return "identificador 'float'";
            case Double:
            	return "identificador 'double'";
            case Int:
                return "identificador 'int'";
            case ID:
                return "variavel";
            case NumInt:
                return "número 'inteiro'";
            case NumFloat:
                return "número 'float'";
            case NumDouble:
            	return "número 'double'"; 
            case Abre_Chave:
                return "'{'";
            case Fecha_Chave:
                return "'}'";
            case Abre_Parenteses:
                return "'('";
            case Fecha_Parenteses:
                return "')'";
            case Abre_Colchete:
                return "'['";
            case Fecha_Colchete:
                return "']'";
            case IF:
                return "comando 'if'";
            case Else:
                return "comando 'else'";
            case While:
                return "comando 'while'";
            case For:
                return "comando 'for'";
            case Return:
                return "comando 'return'";
            case Break:
                return "comando 'break'";
            case Continue:
                return "comando 'continue'";
            case Printf:
            	return "comando 'printf'";
            case Scanf:
            	return "comando 'scanf'";
            case include:
            	return "";
            case stdio:
            	return "";
            case h:
            	return "";
            case cerquilha:
            	return "comando '#include <stdio.h>'";
            case ponto:
            	return "";
            case OPMultiplicacao:
                return "operador '*'";
            case OPAtribuicao:
                return "operador '='";
            case OPOR:
                return "operador '|'";
            case OPAND:
                return "operador '&'";
            case OPSoma:
                return "operador '+'";
            case OPSubtracao:
                return "operador '-'";
            case OPDivisao:
                return "operador '/'";
            case OPResto_Divisao:
                return "operador '%'";
            case OPIncrementa:
                return "operador '++'";
            case OPDecrementa:
                return "operador '--'";
            case STR:
                return "string";
            case OPIgual:
                return "operador '=='";
            case OPDiferente:
                return "operador '!='";
            case OPMaior:
                return "operador '>'";
            case OPMaiorIgual:
                return "operador '>='";
            case OPMenorIgual:
                return "operador '<='";
            case OPMenor:
                return "operador '<'";
            case OPORLOG:
                return "operador '||'";
            case OPANDLOG:
                return "operador '&&'";
            case OPNOTLOG:
                return "operador '!'";
            case OPNOTBIN:
                return "operador '~'";
            case main:
            	return "main";
            default:
                return "";
        }
    }
}