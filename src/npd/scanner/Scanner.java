/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package npd.scanner;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import npd.scanner.transicoes.*;

public class Scanner {
    /**
     * Lista de regras de transicao
     */
    public List<FuncaoTransicao> tabelaTransicao;
    
    /**
     * Estados finais validos
     */
    public List<Estado> estadosFinaisValidos;
    
    /**
     * Lista de characters do arquivo apos ele ser lido
     */
    public List<Character> fileCharArray;
    
    /**
     * Controlador de qual char está sendo lido no arquivo neste momento
     */
    public int currentChar = 0;
    
    /**
     * indicador do estado atual da maquina
     */
    public Estado estadoAtual;
    
    /**
     * Buffer para identificar palavras
     */
    public String buffer;
    

    /**
     * Construtor
     * 
     * @param caminhoArquivo
     * @throws FileNotFoundException
     * @throws IOException 
     */
    public Scanner(String caminhoArquivo) throws FileNotFoundException, IOException{
        FileReader file = new FileReader(caminhoArquivo); //Abre o arquivo
        
        fileCharArray = new ArrayList<>(); //converte o arquivo em uma ArrayList
        
        //Adiciona cada char do arquivo no ArrayList
        int read;
        Character c;
        while(( read = file.read() ) != -1 ){
            c = (char) read;
            fileCharArray.add(c);
        }
        
        buffer = "";//inicia o buffer limpo
        
        estadoAtual = Estado.NADA;//inicia o estado da maquina
        
        tabelaTransicao = new ArrayList<>();//inicia table driven
        
        //adiciona regras na tabela
        tabelaTransicao.add(new FuncaoTransicao(Estado.NADA, new Plus(), Estado.PLUS_OP));
        tabelaTransicao.add(new FuncaoTransicao(Estado.NADA, new Minus(), Estado.MINUS_OP));
        tabelaTransicao.add(new FuncaoTransicao(Estado.MINUS_OP, new Minus(), Estado.COMENTARIO));
        tabelaTransicao.add(new FuncaoTransicao(Estado.COMENTARIO, new ExceptNewLine(), Estado.COMENTARIO));
        tabelaTransicao.add(new FuncaoTransicao(Estado.NADA, new Semicolon(), Estado.SEMICOLON));
        tabelaTransicao.add(new FuncaoTransicao(Estado.NADA, new LParen(), Estado.L_PAREN));
        tabelaTransicao.add(new FuncaoTransicao(Estado.NADA, new RParen(), Estado.R_PAREN));
        tabelaTransicao.add(new FuncaoTransicao(Estado.NADA, new Comma(), Estado.COMMA));
        tabelaTransicao.add(new FuncaoTransicao(Estado.NADA, new Irrelevant(), Estado.NADA));
        tabelaTransicao.add(new FuncaoTransicao(Estado.NADA, new EOF(), Estado.SCAN_EOF));
        tabelaTransicao.add(new FuncaoTransicao(Estado.NADA, new Alphabetic(), Estado.PALAVRA));
        tabelaTransicao.add(new FuncaoTransicao(Estado.PALAVRA, new Alphabetic(), Estado.PALAVRA));
        tabelaTransicao.add(new FuncaoTransicao(Estado.NADA, new Colon(), Estado.DOIS_PONTOS));
        tabelaTransicao.add(new FuncaoTransicao(Estado.DOIS_PONTOS, new Equal(), Estado.ASIGN_OP));
        tabelaTransicao.add(new FuncaoTransicao(Estado.NADA, new Numeric(), Estado.INT_LITERAL));
        tabelaTransicao.add(new FuncaoTransicao(Estado.INT_LITERAL, new Numeric(), Estado.INT_LITERAL));
        
        // estados finais validos
        estadosFinaisValidos = new ArrayList<>();
        estadosFinaisValidos.add(Estado.PLUS_OP);
        estadosFinaisValidos.add(Estado.ASIGN_OP);
        estadosFinaisValidos.add(Estado.INT_LITERAL);
        estadosFinaisValidos.add(Estado.PALAVRA);
        estadosFinaisValidos.add(Estado.MINUS_OP);
        estadosFinaisValidos.add(Estado.SEMICOLON);
        estadosFinaisValidos.add(Estado.L_PAREN);
        estadosFinaisValidos.add(Estado.R_PAREN);
        estadosFinaisValidos.add(Estado.COMMA);
        estadosFinaisValidos.add(Estado.INT_LITERAL);
        estadosFinaisValidos.add(Estado.ASIGN_OP);
        estadosFinaisValidos.add(Estado.SCAN_EOF);
    }
    
    /**
     * @return proximo Token encontrado no arquivo
     */
    public Token getToken(){
        
        //Se chegou no final do arquivo retorna SCAN_EOF
        if(currentChar == fileCharArray.size())
            return Token.SCAN_EOF;
        
        //percorre o arquivo até encontrar um character que não possui regra definida
        for(int c = currentChar; c <fileCharArray.size(); c++){
            
            //reseta o buffer
            if(estadoAtual == Estado.NADA)
                buffer = "";
            
            //interrompe o loop quando encontrar um char que não possui regra
            if(!executarRegra(fileCharArray.get(c)))
                break;
            
            currentChar ++;
        }
        
        Token retornar = Token.INVALIDO; //variavel de controle
        
        Estado e = estadoAtual; //Salva o estado atual
        
        estadoAtual = Estado.NADA; //reseta o estado atual
        
        //se o estado final é um estado valido, retorna o token equivalente ao estado
        if(estadosFinaisValidos.contains(e))
            return getTokenByEstado(e);
        
        //se o estado final e um comentario, retorna o proximo token
        else if(e == Estado.COMENTARIO)
            return getToken();
        
        //retorna invalido
        return retornar;
    }
    
    /**
     * @param e Estado a procurar token referente
     * @return Token referente ao estado indicado
     */
    public Token getTokenByEstado(Estado e){
        Token retornar = Token.INVALIDO; //variavel de controle
        
        if(e == Estado.PALAVRA){
            //se for uma palavra, testa as palavras reservadas
            switch (buffer) {
                case "inicio":
                    retornar = Token.BEGIN;
                    break;
                case "fim":
                    retornar = Token.END;
                    break;
                case "leia":
                    retornar = Token.READ;
                    break;
                case "escreva":
                    retornar = Token.WRITE;
                    break;
                default:
                    retornar= Token.ID;
                    break;
            }
        }
        else if(e == Estado.INT_LITERAL){
            retornar = Token.INT_LITERAL;
        }
        else if(e == Estado.L_PAREN){
            retornar = Token.L_PAREN;
        }
        else if(e == Estado.R_PAREN){
            retornar = Token.R_PAREN;
        }
        else if(e == Estado.SEMICOLON){
            retornar = Token.SEMICOLON;
        }
        else if(e == Estado.COMMA){
            retornar = Token.COMMA;
        }
        else if(e == Estado.ASIGN_OP){
            retornar = Token.ASSIGN_OP;
        }
        else if(e == Estado.PLUS_OP){
            retornar = Token.PLUS_OP;
        }
        else if(e == Estado.MINUS_OP){
            retornar = Token.MINUS_OP;
        }
        else if(e == Estado.SCAN_EOF){
            retornar = Token.SCAN_EOF;
        }
        
        return retornar;
    }
    
    /**
     * Pesquisa pelo estado atual e pelo character c na lista de regras
     * Atualiza o estado atual de acordo com a regra
     * Adiciona c ao buffer
     * 
     * @param c character procurado
     * @return se existe ou nao regra para o estado atual e para 'c'
     */
    public boolean executarRegra(char c){
        
        /*  se a tabela tem algo que indica a transicao de acordo com o estado 
        atual e com o caracter de transicao, atualiza o estado atual */
        for(FuncaoTransicao funcaoNaTabela : tabelaTransicao){
            
            //quando achar uma regra correta
            if(funcaoNaTabela.estadoAtual == estadoAtual && funcaoNaTabela.isCharacterTransicao(c)){
                estadoAtual = funcaoNaTabela.estadoDestino; //atualiza o estado atual
                buffer += c; //salva c no buffer
                return true;
            }
        }
        return false;
    }
}