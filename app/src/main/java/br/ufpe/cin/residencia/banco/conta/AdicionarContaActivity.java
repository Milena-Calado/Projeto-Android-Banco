package br.ufpe.cin.residencia.banco.conta;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import br.ufpe.cin.residencia.banco.R;

//Ver anotações TODO no código
public class AdicionarContaActivity extends AppCompatActivity {

    ContaViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_conta);
        viewModel = new ViewModelProvider(this).get(ContaViewModel.class);

        Button btnAtualizar = findViewById(R.id.btnAtualizar);
        Button btnRemover = findViewById(R.id.btnRemover);
        EditText campoNome = findViewById(R.id.nome);
        EditText campoNumero = findViewById(R.id.numero);
        EditText campoCPF = findViewById(R.id.cpf);
        EditText campoSaldo = findViewById(R.id.saldo);

        btnAtualizar.setText("Inserir");
        btnRemover.setVisibility(View.GONE);

        //TODO: 4. validações de campos

        btnAtualizar.setOnClickListener(
                v -> {
                    String nomeCliente = campoNome.getText().toString(); //pegar o texto do campo nome
                    if (nomeCliente.length() < 5) { //verificar se o nome tem menos de 5 caracteres
                        campoNome.setError("Nome deve ter pelo menos 5 caracteres"); //se tiver, mostrar mensagem de erro
                        return; //sair do método
                    }
                    String cpfCliente = campoCPF.getText().toString();
                    if (cpfCliente.length() != 11) {
                        campoCPF.setError("CPF deve ter 11 caracteres");
                        return;
                    }
                    String numeroConta = campoNumero.getText().toString();
                    if (numeroConta.length() < 4) {
                        campoNumero.setError("Número da conta deve ter 4 caracteres");
                        return;
                    }
                    String saldoConta = campoSaldo.getText().toString();
                    if (saldoConta.length() == 0) {
                        campoSaldo.setError("Saldo deve ser um número");
                        return;
                    }
                    Conta c = new Conta(numeroConta, Double.parseDouble(saldoConta), nomeCliente, cpfCliente); //criar uma conta com os dados dos campos
                    //TODO: 4. método que vai salvar a conta no Banco de Dados
                    viewModel.inserir(c); // inserir conta no banco de dados
                    finish(); // finalizar a activity

                }
        );

    }
}