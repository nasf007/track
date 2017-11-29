package com.inteltrack.inteltrack.vehiculos;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.gson.JsonArray;
import com.inteltrack.inteltrack.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class VehiculosActivity extends AppCompatActivity implements VehiculosContract.View {

    @BindView(R.id.appbar)
    Toolbar appbar;
    @BindView(R.id.listaVehiculos)
    RecyclerView listaVehiculos;
    @BindView(R.id.progress)
    ProgressBar progress;

    private VehiculosContract.Presenter presenter;
    private VehiculosAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehiculos);
        ButterKnife.bind(this);
        new VehiculosPresenter(this, new VehiculosInteractor(this));
    }

    @Override
    public void setPresenter(VehiculosContract.Presenter presenter) {
        this.presenter = presenter;
        presenter.consultarData();
    }

    @Override
    public void setProgress(boolean show) {
        progress.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    @Override
    public void message(String mensaje) {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void finalizar() {
        finish();
    }

    @Override
    public void errorDeConexion() {
        message(getString(R.string.conexioninternet));
    }

    @Override
    public void abrirWaze(double latitud, double longitud) {

    }

    @Override
    public void abrirMaps(double latitud, double longitud) {

    }

    @Override
    public void abrirPlaystore() {

    }

    @Override
    public void crearAdapter(JsonArray jsonArray) {
        adapter= new VehiculosAdapter();
        listaVehiculos.setHasFixedSize(true);
        listaVehiculos.setAdapter(adapter);
        listaVehiculos.setLayoutManager(
                new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false));
        listaVehiculos.addItemDecoration(
                new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        listaVehiculos.setItemAnimator(new DefaultItemAnimator());
    }
}
