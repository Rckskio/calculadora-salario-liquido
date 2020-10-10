package com.example.calculadoradesalarioliquido;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class resultadosActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultados);

        Bundle salary = getIntent().getExtras();
        double salarioBruto = salary.getDouble("salarioBruto");
        double inss = salary.getDouble("inss");
        double irrf = salary.getDouble("irrf");
        double outrosDescontos = salary.getDouble("outrosDescontos");
        double salarioLiquido = salary.getDouble("salarioLiquido");
        double descontos = salary.getDouble("descontos");

        String setSalarioBruto = formatMoney(salarioBruto);
        String setInss = formatMoney(inss);
        String setIrrf = formatMoney(irrf);
        String setOutrosDescontos = formatMoney(outrosDescontos);
        String setSalarioLiquido = formatMoney(salarioLiquido);
        String setDescontos = String.format("%.2f %%", descontos);

        TextView salario_bruto = (TextView)findViewById(R.id.salario_bruto_value);
        salario_bruto.setText(setSalarioBruto);

        TextView inssValue = (TextView)findViewById(R.id.inss_value);
        inssValue.setText(setInss);

        TextView irrfValue = (TextView)findViewById(R.id.irrf_value);
        irrfValue.setText(setIrrf);

        TextView outros_descontos = (TextView)findViewById(R.id.outros_descontos_value);
        outros_descontos.setText(setOutrosDescontos);

        TextView salario_liquido = (TextView)findViewById(R.id.salario_liquido_value);
        salario_liquido.setText(setSalarioLiquido);

        TextView descontosValue = (TextView)findViewById(R.id.descontos_value);
        descontosValue.setText(setDescontos);
    }

    public void retornar(View view) {
        finish();
    }

    private String formatMoney(double money) {
        String result = String.format("%.2f", money);
        if (result.length() < 7) {
            return result;
        }
        String[] text = result.split("");
        ArrayList<String> texto = new ArrayList<>();
        for (int i = 0; i < text.length; i++) {
            texto.add(text[i]);
        }

        int count = 0;
        for (int i = text.length - 3; i > 0; i--) {
            if (count == 3) {
                texto.add(i,".");
                count = 0;
            }
            count++;
        }
        // Passing an array list of Strings to String
        result = TextUtils.join("", texto);
        // Another way of doing it
        //result = texto.stream().map(Object::toString)
        //                          .collect(Collectors.joining(""));
        return result;

    }
}