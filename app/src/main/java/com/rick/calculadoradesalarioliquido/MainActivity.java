package com.rick.calculadoradesalarioliquido;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText SalarioBruto;
    EditText NumDependentes;
    EditText OutrosDescontos;
    double salarioBruto;
    double outrosDescontos;
    int dependentes;

    double min = 1045;
    double inss = 0;
    double irrf = 0;
    double salarioLiquido;
    double desconto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SalarioBruto = findViewById(R.id.input_salario);
        NumDependentes = findViewById(R.id.input_dependentes);
        OutrosDescontos = findViewById(R.id.input_descontos);
    }

    public void calculaSalario(View view) {
        Intent salaryIntent = new Intent(this, resultadosActivity.class);
        String text1 = String.valueOf(SalarioBruto.getText());
        String text2 = String.valueOf(OutrosDescontos.getText());
        String text3 = String.valueOf(NumDependentes.getText());

        if (text1.isEmpty()) {
            salarioBruto = 0;
        } else {
            salarioBruto = Float.parseFloat(String.valueOf(SalarioBruto.getText()));
        }
        if (text2.isEmpty()) {
            outrosDescontos = 0;
        } else {
            outrosDescontos = Float.parseFloat(String.valueOf(OutrosDescontos.getText()));
        }
        if (text3.isEmpty()) {
            dependentes = 0;
        } else {
            dependentes = Integer.parseInt(String.valueOf(NumDependentes.getText()));
        }

        inss = calculaINSS();
        irrf = calculaIRRF();
        salarioLiquido = calculaSalarioLiquido();
        desconto = calculaDescontos();

        Bundle salary = new Bundle();
        salary.putDouble("salarioBruto", salarioBruto);
        salary.putDouble("outrosDescontos", outrosDescontos);
        salary.putDouble("inss", inss);
        salary.putDouble("irrf", irrf);
        salary.putDouble("salarioLiquido", salarioLiquido);
        salary.putDouble("descontos", desconto);
        salaryIntent.putExtras(salary);

        startActivity(salaryIntent);
    }

    private double calculaINSS() {
        double aliquota = 7.5;
        double deducao = 0;
        if (salarioBruto > 6101.06) {
            return 713.10;
        } else {
            if (salarioBruto >= min + 0.1 && salarioBruto <= 2089.60) {
                aliquota = 9;
                deducao = 15.67;
            } else if (salarioBruto >= 2089.61 && salarioBruto <= 3134.40) {
                aliquota = 12;
                deducao = 78.36;
            } else if (salarioBruto >= 3134.41 && salarioBruto <= 6101.06){
                aliquota = 14;
                deducao = 141.05;
            }
        }
        return (salarioBruto * aliquota) / 100 - deducao;
    }

    private double calculaIRRF() {
        double aliquota = 0;
        double deducao = 0;
        double baseCalculo;

        baseCalculo = salarioBruto - inss - (dependentes * 189.59);

        if (baseCalculo > 1903.89 && baseCalculo <= 2826.65) {
            aliquota = 7.5;
            deducao = 142.80;
        } else if (baseCalculo > 2826.65 && baseCalculo <= 3751.05) {
            aliquota = 15;
            deducao = 354.80;
        } else if (baseCalculo > 3751.05 && baseCalculo <= 4664.68) {
            aliquota = 22.5;
            deducao = 636.13;
        } else if (baseCalculo > 4664.68) {
            aliquota = 27.5;
            deducao = 869.36;
        }

        return (baseCalculo * aliquota)/100 - deducao;
    }

    private double calculaSalarioLiquido() {
        return salarioBruto - inss - irrf - outrosDescontos;
    }

    private double calculaDescontos() {
        if (salarioBruto == 0) {
            return 0;
        }
        return (salarioBruto - salarioLiquido) * 100 / salarioBruto;
    }
}