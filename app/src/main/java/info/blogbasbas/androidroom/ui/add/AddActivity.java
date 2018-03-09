package info.blogbasbas.androidroom.ui.add;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import info.blogbasbas.androidroom.R;
import info.blogbasbas.androidroom.db.model.ModelPeserta;
import info.blogbasbas.androidroom.ui.main.MainActivity;

public class AddActivity extends AppCompatActivity {
    EditText edtNama, EdtPelatihan;
    Button btnSubmit;
    AddViewModel viewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        btnSubmit = (Button)findViewById(R.id.btnSubmit);
        edtNama = (EditText)findViewById(R.id.edtNama);
        EdtPelatihan = (EditText)findViewById(R.id.edtPelatihan);

        viewModel = ViewModelProviders.of(this).get(AddViewModel.class);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edtNama.getText().toString().isEmpty()){
                    edtNama.setError("Insert Nama");
                } else if (EdtPelatihan.getText().toString().isEmpty()){
                        EdtPelatihan.setError("Insert Pelatihan");
                } else {
                    String nama = edtNama.getText().toString();
                    String pelatihan = EdtPelatihan.getText().toString();


                    viewModel.addPeserta(new ModelPeserta(nama, pelatihan));
                    Toast.makeText(AddActivity.this, "berhasil ditambahkan", Toast.LENGTH_SHORT).show();
                    finish();

                }

            }
        });

    }
}
