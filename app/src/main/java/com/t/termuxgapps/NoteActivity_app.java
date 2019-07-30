package com.t.termuxgapps;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import com.t.termuxgapps.model.Note_app;
import com.t.termuxgapps.helper.DbAdapterNote;

public class NoteActivity_app extends Activity {

    private ListView listCatatan;
    private ArrayList<Note_app> dataCatatan;
    private AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.3F);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_app);

        // StatusBar Color
        Window window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(Color.parseColor("#292B3D"));

        setupView();

        ambilData();


        final ImageView back = (ImageView) findViewById(R.id.btnBack);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(NoteActivity_app.this, MainActivity.class);
                startActivity(i);
            }

        });

        final ImageView fab = (ImageView) findViewById(R.id.btnAddNoteApp);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getBaseContext(), TambahNoteActivity.class);
                i.putExtra("edit", false);
                startActivity(i);
                return;

            }

        });

    }



    private void setupView() {
        //koneksikan variabel listCatatan dengan list di layout
        listCatatan = (ListView) findViewById(R.id.lvCatatan);
        //event saat listview diklik pindah ke halaman detail
        listCatatan.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                view.startAnimation(buttonClick);
                //ambil catatan sesuai dengan listview yang diambil
                Note_app c = dataCatatan.get(position);
                //buat intent baru
                Intent i = new Intent(getBaseContext(), DetailActivity.class);
                //tambahkan data yang mau dikirim ke halaman detail
                i.putExtra("judul", c.getTitle());
                i.putExtra("isi", c.getIsi());
                i.putExtra("tanggal", c.getTanggal());
                startActivity(i);
            }
        });

        //event saat listview diklik lama untuk edit dan delete
        listCatatan.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> arg0, View view, int position, long arg3) {

                view.startAnimation(buttonClick);
                //ambil data catatan yang dipilih
                Note_app c = dataCatatan.get(position);
                //tampilkan dialog edit dan delete
                tampilkanDialog(c);
                return false;
            }
        });

    }

    //dialog edit dan delete
    private void tampilkanDialog(final Note_app c) {
        String opsiDialog[] = {"Edit Catatan", "Hapus Catatan"};
        AlertDialog.Builder builder = new AlertDialog.Builder(
                NoteActivity_app.this);

        builder.setNeutralButton("Tutup",
                new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }

                });
        builder.setTitle("Edit Or Delete");
        builder.setItems(opsiDialog, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case 0:
                        //saat edit dipilih pindah ke halaman edit
                        Intent i = new Intent(getBaseContext(),
                                TambahNoteActivity.class);
                        i.putExtra("edit", true);
                        i.putExtra("id", c.getId());
                        i.putExtra("judul", c.getTitle());
                        i.putExtra("isi", c.getIsi());
                        i.putExtra("tanggal", c.getTanggal());
                        startActivity(i);
                        break;
                    case 1:
                        //saat delete dipilih, tampilkan konfirmasi delete
                        AlertDialog.Builder builderx = new AlertDialog.Builder(
                                NoteActivity_app.this);
                        builderx.setNeutralButton("Cancel",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,
                                                        int which) {
                                        dialog.dismiss();
                                    }

                                });
                        builderx.setPositiveButton("Hapus", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //koneksi db dan hapus data yang akan dihapus
                                DbAdapterNote db = new DbAdapterNote(
                                        NoteActivity_app.this);
                                db.open();
                                db.deleteData(c.getId());
                                db.close();
                                // refreshh list view
                                ambilData();
                            }
                        });
                        builderx.setTitle("Hapus Catatan");
                        builderx.setMessage("Apakah Anda Yakin Mau menghapus Catatan ini?");
                        builderx.show();
                        break;
                }
            }
        });
        builder.show();
    }

    //method untuk mengambil semua data catatan dari database
    private void ambilData() {
        dataCatatan = new ArrayList<Note_app>();
        //buat object dari class DBAdapter yang ada di package helper
        DbAdapterNote db = new DbAdapterNote(getBaseContext());
        //buka koneksi databse
        db.open();
        //ambil semua data catatan dgn method getData()
        Cursor cur = db.getData();

        cur.moveToFirst();
        if (cur.getCount() > 0) {
            while (cur.isAfterLast() == false) {
                Note_app c = new Note_app();
                c.setId((cur.getInt(cur
                        .getColumnIndexOrThrow(DbAdapterNote.IDCATATAN))));
                c.setTitle((cur.getString(cur
                        .getColumnIndexOrThrow(DbAdapterNote.TITLE_CATATAN))));
                c.setIsi((cur.getString(cur
                        .getColumnIndexOrThrow(DbAdapterNote.ISI_CATATAN))));
                c.setTanggal((cur.getString(cur
                        .getColumnIndexOrThrow(DbAdapterNote.TANGGAL_CATATAN))));
                //tambahkan ke arraylist dataCatatan
                dataCatatan.add(c);
                cur.moveToNext();
            }
            //tutup koneksi database
            db.close();

            // masukkan kedalam custom listview
            //buat adapter dari inner class CustomAdapter
            CustomAdapter adapter = new CustomAdapter(getBaseContext(),
                    dataCatatan);
            //masukkan adapter ke dalam listView
            listCatatan.setAdapter(adapter);
        }
    }

    // subclass untuk custom adapter pada listview
    private class CustomAdapter extends BaseAdapter {

        private Context context;
        private ArrayList<Note_app> dataz;
        private LayoutInflater inflater = null;

        public CustomAdapter(Context c, ArrayList<Note_app> data) {
            context = c;
            dataz = data;
            inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        public int getCount() {
            return dataz.size();
        }

        public Object getItem(int position) {
            return position;
        }

        public long getItemId(int position) {
            return position;
        }

        public View getView(int position, View view, ViewGroup parent) {
            View vi = view;
            if (view == null)
                vi = inflater.inflate(R.layout.custom_listview, null);

            TextView title = (TextView) vi.findViewById(R.id.tvCustomTitle);

            Note_app c = dataz.get(position);
            //masukkan judul dari catatan
            title.setText(c.getTitle());
            int color = c.getWarna();

            //set warna backgroud listview sesuai warna yang dipilih
            if (color == 0) {
                vi.setBackgroundColor(Color.parseColor("#ffff00"));
            }

            return vi;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // tambahkan menu daftar catatan
        getMenuInflater().inflate(R.menu.daftar_catatan, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.add_catatan) {
            //jika menu diklik akan pindah ke halaman tambah catatan
            Intent i = new Intent(getBaseContext(), TambahNoteActivity.class);
            i.putExtra("edit", false);
            startActivity(i);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }



}
