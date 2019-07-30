package com.t.termuxgapps;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.t.termuxgapps.helper.DbAdapterNote;
import com.t.termuxgapps.model.Note_app;

public class TambahNoteActivity extends Activity {

    private EditText txtJudul, txtCatatan;
    private Button btnSimpan;

    private boolean isEdit = false;
    private int id = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_note);

        //cek apakah statenya mengedit atau menambah data
        isEdit = getIntent().getExtras().getBoolean("edit", false);
        setupView();

        if (isEdit) {
            //jika edit maka ambil data yang akan diedit dan ditampilkan
            String judul = getIntent().getExtras().getString("judul");
            String isi = getIntent().getExtras().getString("isi");
            id = getIntent().getExtras().getInt("id");


            txtJudul.setText(judul);
            txtCatatan.setText(isi);

        }
    }

    private void setupView() {
        txtJudul = (EditText) findViewById(R.id.txtJudul);
        txtCatatan = (EditText) findViewById(R.id.txtCatatan);
        btnSimpan = (Button) findViewById(R.id.btnSimpan);

        //event tombol submit diklik
        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                simpan();
            }
        });

    }

        protected void simpan() {
            //cek apakah sudah diisi judul catatan
            if (txtJudul.getText().toString().equalsIgnoreCase("")) {
                Toast.makeText(getBaseContext(), "Judul Harus Diisi",
                        Toast.LENGTH_LONG).show();
                //cek apakah sudah diisi  catatannya
            } else if (txtCatatan.getText().toString().equalsIgnoreCase("")) {
                Toast.makeText(getBaseContext(), "Catatan Harus Diisi",
                        Toast.LENGTH_LONG).show();
            } else {
                //buat object DBAdapter
                DbAdapterNote db = new DbAdapterNote(getBaseContext());
                //buka koneksi kedatabase
                db.open();
                //buat objek model data Catatan
                Note_app c = new Note_app();
                c.setTitle(txtJudul.getText().toString());
                c.setIsi(txtCatatan.getText().toString());
                boolean hasil = false;
                if (isEdit) {
                    //jika statenya edit maka update data
                    hasil = db.updateData(c, id);
                } else {
                    //sebaliknya jika tambah maka
                    //panggil method insertData
                    hasil = db.insertData(c);
                }
                if (hasil) {
                    //jika berhasil disimpan, buat notifikasi
                    //dan pindah ke halam list daftar
                    Toast.makeText(getBaseContext(), "Catatan Berhasil Disimpan",
                            Toast.LENGTH_LONG).show();
                    startActivity(new Intent(getBaseContext(),
                            NoteActivity_app.class));
                } else {
                    Toast.makeText(getBaseContext(),
                            "Ooo, error simpan data. silahkan coba lagi.",
                            Toast.LENGTH_LONG).show();
                }
                //tutup koneksi database
                db.close();
                finish();
            }
        }
}
