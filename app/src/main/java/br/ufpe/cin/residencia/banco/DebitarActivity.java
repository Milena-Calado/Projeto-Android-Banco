package br.ufpe.cin.residencia.banco;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

//Ver anotações TODO no código
public class DebitarActivity extends AppCompatActivity {
    BancoViewModel viewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_operacoes);
        viewModel = new ViewModelProvider(this).get(BancoViewModel.class);

        TextView tipoOperacao = findViewById(R.id.tipoOperacao);
        EditText numeroContaOrigem = findViewById(R.id.numeroContaOrigem);
        TextView labelContaDestino = findViewById(R.id.labelContaDestino);
        EditText numeroContaDestino = findViewById(R.id.numeroContaDestino);
        EditText valorOperacao = findViewById(R.id.valor);
        Button btnOperacao = findViewById(R.id.btnOperacao);

        labelContaDestino.setVisibility(View.GONE);
        numeroContaDestino.setVisibility(View.GONE);

        valorOperacao.setHint(valorOperacao.getHint() + " debitado");
        tipoOperacao.setText("DEBITAR");
        btnOperacao.setText("Debitar");

        btnOperacao.setOnClickListener( //clique do botão de débito
                v -> { //quando clicar no botão
                    String numOrigem = numeroContaOrigem.getText().toString(); //pega o número da conta de origem
                    if (numOrigem.isEmpty()) { //verifica se o número da conta de origem está vazio
                        numeroContaOrigem.setError("Número da conta de origem é obrigatório"); //se estiver, mostra mensagem de erro
                        return; //sai do método
                    }
                    String valorOperacaoString = valorOperacao.getText().toString();
                    if (valorOperacaoString.isEmpty()) {
                        valorOperacao.setError("Valor da operação é obrigatório");
                        return;
                    }
                    //TODO 12. lembrar de implementar validação do número da conta e do valor da operação, antes de efetuar a operação de débito.
                    // O método abaixo está sendo chamado, mas precisa ser implementado na classe BancoViewModel para funcionar.
                    double valor = Double.valueOf(valorOperacao.getText().toString()); //pega o valor da operação
                    viewModel.debitar(numOrigem, valor); //chama o método de débito do ViewModel
                    finish(); //fecha a tela
                }
        );
    }
}