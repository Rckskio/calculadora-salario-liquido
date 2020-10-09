package com.example.calculadoradesalarioliquido;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

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

        String setSalarioBruto = String.format("%.2f", salarioBruto);
        String setInss = String.format("%.2f", inss);
        String setIrrf = String.format("%.2f", irrf);
        String setOutrosDescontos = String.format("%.2f", outrosDescontos);
        String setSalarioLiquido = String.format("%.2f", salarioLiquido);
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
}