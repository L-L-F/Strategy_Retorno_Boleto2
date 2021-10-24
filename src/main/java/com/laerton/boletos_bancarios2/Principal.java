/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.laerton.boletos_bancarios2;

import com.laerton.boletos_bancarios2.strategy.ProcessarBoletos;

/**
 *
 * @author laerton
 */
public class Principal {
    public static void main(String[] args) {
        ProcessarBoletos processador = new ProcessarBoletos(ProcessarBoletos::lerBancoBrasil);
        String nomeArquivo = Principal.class.getResource("/banco-brasil-1.csv").getPath();
        
        //ProcessarBoletos processador = new ProcessarBoletos(ProcessarBoletos::lerBancoBradesco);
        //String nomeArquivo = Principal.class.getResource("/bradesco-1.csv").getPath();

        System.out.println("arquivo "+ nomeArquivo +"\n");
        processador.processar(nomeArquivo);
    }
}
