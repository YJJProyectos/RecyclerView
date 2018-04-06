package com.android.jyang.recyclerview.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.android.jyang.recyclerview.models.Item;
import com.android.jyang.recyclerview.adapters.MyAdapter;
import com.android.jyang.recyclerview.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    private List<Item> items;
    @BindView(R.id.my_recycler_view)  RecyclerView recyclerView;
//    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private int counter = 0;
    private boolean estaCambiado = false;
    private final RecyclerView.LayoutManager layoutLinear = new LinearLayoutManager(this);
    private final RecyclerView.LayoutManager layoutGrid2 = new GridLayoutManager(this, 2);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setTitle("Recycler View - Card View");

        ButterKnife.bind(this);

        items = this.getItems();

//        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
//        layoutManager = new LinearLayoutManager(this);
//        layoutManager = new GridLayoutManager(this, 2);
//        layoutManager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL );
        this.layoutManager = layoutLinear;
        recyclerView.setLayoutManager(this.layoutManager);

//        adapter = new MyAdapter(names, R.layout.recycler_view_item, new MyAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(String name, int position) {
//                Toast.makeText(MainActivity.this, name + " - " + position, Toast.LENGTH_SHORT).show();
//                removeItem(position);
//            }
//        });

        adapter = new MyAdapter(items, R.layout.recycler_view_item, this, new MyAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Item item, int position) {
                Toast.makeText(MainActivity.this, "Touch " + item.getName() , Toast.LENGTH_SHORT).show();
//                removeItem(position);
            }
        });
//        recyclerView.setHasFixedSize(true);
//        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

//        registerForContextMenu(recyclerView);

    }

    private List<Item> getItems() {
        return new ArrayList<Item>() {{
            add(new Item("Manzana",R.drawable.apple_bg));
            add(new Item("Banana", R.drawable.banana_bg));
            add(new Item("Cereza", R.drawable.cherry_bg));
            add(new Item("Frutilla", R.drawable.strawberry_bg));
            add(new Item("Mora", R.drawable.raspberry_bg));
            add(new Item("Ciruela", R.drawable.plum_bg));
            add(new Item("Pera", R.drawable.pear_bg));
        }};
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.add_name:
                this.addItem(this.items.size());
                return true;
            case R.id.cambiarGrilla:
                if (estaCambiado) {
                    this.layoutManager = this.layoutLinear;
                    estaCambiado = false;
                } else {
                    this.layoutManager = this.layoutGrid2;
                    estaCambiado = true;
                }
                this.recyclerView.setLayoutManager(this.layoutManager);
//                Toast.makeText(this, "posicion " , Toast.LENGTH_SHORT).show();
                this.recyclerView.setAdapter(this.adapter);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    // Inflamos el layout del context menu
//    @Override
//    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
//        super.onCreateContextMenu(menu, v, menuInfo);
//        MenuInflater inflater = getMenuInflater();
//
////        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
////        menu.setHeaderTitle(items.get(info.position).getName());
//        inflater.inflate(R.menu.context_menu, menu);
//
//
//    }

    // Manejamos eventos click en el context menu
//    @Override
//    public boolean onContextItemSelected(MenuItem item) {
//
//        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
//
//        switch (item.getItemId()) {
//            case R.id.delete_item:
//                // obtenemos la position de la lista a borrar
//                this.items.remove(info.position);
//                this.adapter.notifyDataSetChanged();
//                return true;
//            default:
//                return super.onContextItemSelected(item);
//        }
//    }


    public void borrarTodos(View view) {
        int tamanio = this.items.size();
        for (int i = 0; i < tamanio; i++) {
            removeItem(0);
        }
    }

    private void addItem(int position) {
        int PICK_IMAGE = 1;
        items.add(position, new Item("Nueva Imagen " + (++counter), R.drawable.newmovie ));
        adapter.notifyItemInserted(position);
        layoutManager.scrollToPosition(position);
    }

    private void removeItem(int position) {
        items.remove(position);
        adapter.notifyItemRemoved(position);
    }
}
