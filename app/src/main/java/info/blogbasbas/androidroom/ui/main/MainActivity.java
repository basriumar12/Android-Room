package info.blogbasbas.androidroom.ui.main;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import info.blogbasbas.androidroom.R;
import info.blogbasbas.androidroom.db.model.ModelPeserta;
import info.blogbasbas.androidroom.ui.add.AddActivity;

public class MainActivity extends AppCompatActivity implements View.OnLongClickListener {
    RecyclerView rvPeserta;
    MainAdapter adapter;
    MainViewModel viewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        rvPeserta = (RecyclerView) findViewById(R.id.rvPeserta);
        rvPeserta.setLayoutManager(new LinearLayoutManager(this));


        adapter = new MainAdapter(new ArrayList<ModelPeserta>(),this);
        viewModel = ViewModelProviders.of(this).get(MainViewModel.class);

        rvPeserta.setAdapter(adapter);

        //subscribe view model
        viewModel.getAll().observe(MainActivity.this, new Observer<List<ModelPeserta>>() {
            @Override
            public void onChanged(@Nullable List<ModelPeserta> modelPesertas) {
                adapter.addUser(modelPesertas);

            }
        });


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(MainActivity.this, AddActivity.class));
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onLongClick(View view) {
        ModelPeserta modelPeserta = (ModelPeserta) view.getTag();
        viewModel.deleteItem(modelPeserta);
        Toast.makeText(this, "Berhasil dihapus", Toast.LENGTH_SHORT).show();
        return true;
    }
}
