package br.ufpe.cin.residencia.banco;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import br.ufpe.cin.residencia.banco.conta.ContaAdapter;

//Ver anotações TODO no código
public class PesquisarActivity extends AppCompatActivity {
    BancoViewModel viewModel;
    ContaAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pesquisar);
        viewModel = new ViewModelProvider(this).get(BancoViewModel.class);
        EditText aPesquisar = findViewById(R.id.pesquisa);
        Button btnPesquisar = findViewById(R.id.btn_Pesquisar);
        RadioGroup tipoPesquisa = findViewById(R.id.tipoPesquisa);
        RecyclerView rvResultado = findViewById(R.id.rvResultado);
        adapter = new ContaAdapter(getLayoutInflater());
        rvResultado.setLayoutManager(new LinearLayoutManager(this));
        rvResultado.setAdapter(adapter);

        btnPesquisar.setOnClickListener(
                v -> {
                    RadioButton rbNome = findViewById(R.id.peloNomeCliente);
                    RadioButton rbNumero = findViewById(R.id.peloNumeroConta);
                    RadioButton rbCpf = findViewById(R.id.peloCPFcliente);

                    String oQueFoiDigitado = aPesquisar.getText().toString(); //pegar o que foi digitado pelo usuário
                    RadioButton rbSelecionado = findViewById(tipoPesquisa.getCheckedRadioButtonId());

                    //TODO 13. implementar a busca de acordo com o tipo de busca escolhido pelo usuário
                    if (tipoPesquisa.getCheckedRadioButtonId() == R.id.peloNomeCliente) {
                        viewModel.buscarPeloNome(oQueFoiDigitado);
                    } else if (tipoPesquisa.getCheckedRadioButtonId() == R.id.peloNumeroConta) {
                        viewModel.buscarPeloNumero(oQueFoiDigitado);
                    } else if (tipoPesquisa.getCheckedRadioButtonId() == R.id.peloCPFcliente) {
                        viewModel.buscarPeloCPF(oQueFoiDigitado);
                    }
                    //TODO 14. atualizar o RecyclerView com resultados da busca na medida que encontrar
                    viewModel.contas.observe(this, contas -> { //observe fica observando a lista de contas
                        adapter.submitList(contas); //atualiza o adapter com a nova lista de contas
                    });
                }
        );




    }
}