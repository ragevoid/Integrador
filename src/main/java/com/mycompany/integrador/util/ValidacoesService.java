/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.integrador.util;

import javax.swing.JTextField;

/**
 *
 * @author jose.zanandrea
 */
public class ValidacoesService {

    public String formatarCpfCnpj(String cpfCnpj) {
        if (cpfCnpj == null) {
            return "";
        }

        // Remove caracteres não numéricos
        cpfCnpj = cpfCnpj.replaceAll("[^0-9]", "");

        // Formata CPF
        if (cpfCnpj.length() == 11) {
            return cpfCnpj.replaceAll("(\\d{3})(\\d{3})(\\d{3})(\\d{2})", "$1.$2.$3-$4");
        }
        // Formata CNPJ
        else if (cpfCnpj.length() == 14) {
            return cpfCnpj.replaceAll("(\\d{2})(\\d{3})(\\d{3})(\\d{4})(\\d{2})", "$1.$2.$3/$4-$5");
        }
        // Retorna sem formatação se não for CPF nem CNPJ válido
        else {
            return cpfCnpj;
        }
    }

    // Método para validar CPF
    public boolean validarCpf(String cpf) {
        // Remove caracteres não numéricos
        cpf = cpf.replaceAll("[^0-9]", "");

        // Verifica se o CPF tem 11 dígitos
        if (cpf.length() != 11)
            return false;

        // Calcula o primeiro dígito verificador
        int soma = 0;
        for (int i = 0; i < 9; i++) {
            soma += Character.getNumericValue(cpf.charAt(i)) * (10 - i);
        }
        int resto = soma % 11;
        int digitoVerificador1 = resto < 2 ? 0 : 11 - resto;

        // Calcula o segundo dígito verificador
        soma = 0;
        for (int i = 0; i < 10; i++) {
            soma += Character.getNumericValue(cpf.charAt(i)) * (11 - i);
        }
        resto = soma % 11;
        int digitoVerificador2 = resto < 2 ? 0 : 11 - resto;

        // Verifica se os dígitos verificadores calculados são iguais aos informados
        return Character.getNumericValue(cpf.charAt(9)) == digitoVerificador1 &&
                Character.getNumericValue(cpf.charAt(10)) == digitoVerificador2;
    }

    // Método para validar CNPJ
    public boolean validarCnpj(String cnpj) {
        // Remove caracteres não numéricos
        cnpj = cnpj.replaceAll("[^0-9]", "");

        // Verifica se o CNPJ tem 14 dígitos
        if (cnpj.length() != 14)
            return false;

        // Calcula o primeiro dígito verificador
        int soma = 0;
        int peso = 2;
        for (int i = 11; i >= 0; i--) {
            soma += Character.getNumericValue(cnpj.charAt(i)) * peso;
            peso++;
            if (peso == 10)
                peso = 2;
        }
        int resto = soma % 11;
        int digitoVerificador1 = resto < 2 ? 0 : 11 - resto;

        // Calcula o segundo dígito verificador
        soma = 0;
        peso = 2;
        for (int i = 12; i >= 0; i--) {
            soma += Character.getNumericValue(cnpj.charAt(i)) * peso;
            peso++;
            if (peso == 10)
                peso = 2;
        }
        resto = soma % 11;
        int digitoVerificador2 = resto < 2 ? 0 : 11 - resto;

        // Verifica se os dígitos verificadores calculados são iguais aos informados
        return Character.getNumericValue(cnpj.charAt(12)) == digitoVerificador1 &&
                Character.getNumericValue(cnpj.charAt(13)) == digitoVerificador2;
    } 

    public static boolean validarEmail(JTextField emailField) {
        String email = emailField.getText();
        return email.contains("@");
    }
    
}
