public class Token
{
    private Tokens token;
    private String lex;
    private int linha;

    public Token(Tokens token, String lex, int linha){
        this.token = token;
        this.lex = lex;
        this.linha = linha;
    }

    public Tokens getToken(){
        return token;
    }

    public void setToken(Tokens token){
        this.token = token;
    }

    public String getLex(){
        return lex;
    }

    public void setLex(String lex){
        this.lex = lex;
    }

    public int getLinha(){
        return linha;
    }

    public void setLinha(int linha){
        this.linha = linha;
    }
    
    @Override
    public String toString(){
        return token.toString();
    }
}