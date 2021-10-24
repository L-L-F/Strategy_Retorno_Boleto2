/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.laerton.boletos_bancarios2.strategy;

import com.laerton.boletos_bancarios2.entities.Boleto;
import static com.laerton.boletos_bancarios2.strategy.LeituraRetorno.FORMATO_DATA;
import static com.laerton.boletos_bancarios2.strategy.LeituraRetorno.FORMATO_DATA_HORA;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/**
 *
 * @author laerton
 */
public class ProcessarBoletos {
    private Function<String, List<Boleto>> leituraRetorno;

    public ProcessarBoletos(Function<String, List<Boleto>> leituraRetorno){
	 this.leituraRetorno = leituraRetorno;
    }

    public final void processar(String nomeArquivo) {
        System.out.println("Boletos");
        System.out.println("#--------------------------------------------#");
        List<Boleto> boletos = leituraRetorno.apply(nomeArquivo);
        
        boletos.stream().forEach((Boleto boleto) -> {System.out.println(boleto);});
        
    }

    public static List<Boleto> lerBancoBrasil(String nomeArquivo) {
        try {
            BufferedReader bufferedReader = Files.newBufferedReader(Paths.get(nomeArquivo));
            String linha;
            List<Boleto> boletos = new ArrayList<>();
            
            while ((linha = bufferedReader.readLine()) != null) {
                String[] vetor = linha.split(";");
                Boleto boleto = new Boleto();

                boleto.setId(Integer.parseInt(vetor[0]));
                boleto.setCodBanco(vetor[1]);
                boleto.setDtVencimento(LocalDate.parse(vetor[2], FORMATO_DATA));
                boleto.setDtPagamento(LocalDate.parse(vetor[3], FORMATO_DATA).atTime(0, 0, 0));
                boleto.setCpfCliente(vetor[4]);
                boleto.setValor(Double.parseDouble(vetor[5]));
                boleto.setMulta(Double.parseDouble(vetor[6]));
                boleto.setJuros(Double.parseDouble(vetor[7]));

                boletos.add(boleto);
            }
            return boletos;
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
    
    public static List<Boleto> lerBancoBradesco(String nomeArquivo) {
        try {
            BufferedReader bufferedReader = Files.newBufferedReader(Paths.get(nomeArquivo));
            String linha;
            List<Boleto> boletos = new ArrayList<>();
            
            while ((linha = bufferedReader.readLine()) != null) {
                String[] vetor = linha.split(";");
                Boleto boleto = new Boleto();

                boleto.setId(Integer.parseInt(vetor[0]));
                boleto.setCodBanco(vetor[1]);
                boleto.setAgencia(vetor[2]);
                boleto.setContaBancaria(vetor[3]);
                boleto.setDtVencimento(LocalDate.parse(vetor[4], FORMATO_DATA));
                boleto.setDtPagamento(LocalDate.parse(vetor[5], FORMATO_DATA_HORA).atTime(23, 59, 59));
                boleto.setCpfCliente(vetor[6]);
                boleto.setValor(Double.parseDouble(vetor[7]));
                boleto.setMulta(Double.parseDouble(vetor[8]));
                boleto.setJuros(Double.parseDouble(vetor[9]));

                boletos.add(boleto);
            }
            return boletos;
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
    
    public void setLeituraRetorno(Function<String, List<Boleto>> leituraRetorno) {
        this.leituraRetorno = leituraRetorno;
    }
}
