package br.ufpe.cin.residencia.banco.conta;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import br.ufpe.cin.residencia.banco.R;

//Ver anotações TODO no código
public class EditarContaActivity extends AppCompatActivity {

    public static final String KEY_NUMERO_CONTA = "numeroDaConta";
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
        campoNumero.setEnabled(false);

        Intent i = getIntent(); //pegar a Intent que iniciou essa Activity
        String numeroConta = i.getStringExtra(KEY_NUMERO_CONTA); //pegar o número da conta que foi passado via Intent
        //TODO 8. usar o número da conta passado via Intent para recuperar informações da conta
        viewModel.buscarPeloNumero(numeroConta); //buscar a conta pelo número
        viewModel.contaAtual.observe(this, conta -> { //observar a conta atual
            if (conta != null) { //se a conta não for nula
                campoNome.setText(conta.nomeCliente); //preencher os campos com os dados da conta
                campoNumero.setText(conta.numero); //preencher os campos com os dados da conta
                campoCPF.setText(conta.cpfCliente); //preencher os campos com os dados da conta
                campoSaldo.setText(String.valueOf(conta.saldo)); //preencher os campos com os dados da conta
            }
        });

        btnAtualizar.setText("Editar");
        btnAtualizar.setOnClickListener(
                //TODO: 9. Validações de campos
                v -> {
                    String nomeCliente = campoNome.getText().toString(); //pegar o texto do campo nome
                    if (nomeCliente.length() < 5) { //se o nome tiver menos de 5 caracteres
                        campoNome.setError("Nome deve ter pelo menos 5 caracteres"); //mostrar mensagem de erro
                        return; //sair do método
                    }
                    String cpfCliente = campoCPF.getText().toString(); //pegar o texto do campo cpf
                    if (cpfCliente.length() != 11) { //se o cpf não tiver 11 caracteres
                        campoCPF.setError("CPF deve ter 11 caracteres"); //mostrar mensagem de erro
                        return; //sair do método
                    }
                    String saldoConta = campoSaldo.getText().toString();
                    if (saldoConta.length() == 0) {
                        campoSaldo.setError("Saldo deve ser um número");
                        return;
                    }


                    //TODO: 9. chamar o método que vai atualizar a conta no Banco de Dados
                    Conta c = new Conta(numeroConta, Double.parseDouble(saldoConta), nomeCliente, cpfCliente); //criar um objeto Conta com os dados preenchidos
                    viewModel.atualizar(c); //atualizar a conta no banco de dados
                    finish();   //finalizar a Activity
                }
        );

        btnRemover.setOnClickListener(v -> { //quando o botão de remover for clicado
            //TODO 10. implementar remoção da conta
            viewModel.remover(viewModel.contaAtual.getValue()); //remover a conta atual
            finish(); //finalizar a Activity
        });
    }
}