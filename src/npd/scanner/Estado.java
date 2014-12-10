/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package npd.scanner;

/**
 * Enum de estados da máquina
 */
public enum Estado {
    NADA, //Estado inicial
    PALAVRA, //Nao diferencia palavras reservadas e tem que começar com letra
    INT_LITERAL, //
    DOIS_PONTOS, //
    ASIGN_OP, //
    SCAN_EOF, //
    COMMA, //
    R_PAREN, //
    L_PAREN, //
    SEMICOLON, //
    MINUS_OP, //
    COMENTARIO, // iniciado com  --
    PLUS_OP //
}
