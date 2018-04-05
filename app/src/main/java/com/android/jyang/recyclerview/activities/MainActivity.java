package com.android.jyang.recyclerview.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.android.jyang.recyclerview.models.Movie;
import com.android.jyang.recyclerview.adapters.MyAdapter;
import com.android.jyang.recyclerview.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    private List<Movie> movies;
    @BindView(R.id.my_recycler_view)  RecyclerView recyclerView;
//    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private int counter = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setTitle("Recycler View - Card View");

        ButterKnife.bind(this);

        movies = this.getMovies();

//        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        layoutManager = new LinearLayoutManager(this);
//        layoutManager = new GridLayoutManager(this, 2);
//        layoutManager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL );

        recyclerView.setLayoutManager(layoutManager);

//        adapter = new MyAdapter(names, R.layout.recycler_view_item, new MyAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(String name, int position) {
//                Toast.makeText(MainActivity.this, name + " - " + position, Toast.LENGTH_SHORT).show();
//                removeMovie(position);
//            }
//        });

        adapter = new MyAdapter(movies, R.layout.recycler_view_item, new MyAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Movie movie, int position) {
                Toast.makeText(MainActivity.this, "Touch " + movie.getName() , Toast.LENGTH_SHORT).show();
//                removeMovie(position);
            }
        });
//        recyclerView.setHasFixedSize(true);
//        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        registerForContextMenu(recyclerView);

    }

    private List<Movie> getMovies() {
        return new ArrayList<Movie>() {{
            add(new Movie("Manzana",R.drawable.apple_bg));
            add(new Movie("Banana", R.drawable.banana_bg));
            add(new Movie("Cereza", R.drawable.cherry_bg));
            add(new Movie("Frutilla", R.drawable.strawberry_bg));
            add(new Movie("Mora", R.drawable.raspberry_bg));
            add(new Movie("Ciruela", R.drawable.plum_bg));
            add(new Movie("Pera", R.drawable.pear_bg));
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
                this.addMovie(this.movies.size() );
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    // Inflamos el layout del context menu
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();

//        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
//        menu.setHeaderTitle(movies.get(info.position).getName());
        inflater.inflate(R.menu.context_menu, menu);


    }

    // Manejamos eventos click en el context menu
    @Override
    public boolean onContextItemSelected(MenuItem item) {

        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        switch (item.getItemId()) {
            case R.id.delete_item:
                // obtenemos la position de la lista a borrar
                this.movies.remove(info.position);
                this.adapter.notifyDataSetChanged();
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }


    private void addMovie(int position) {
        int PICK_IMAGE = 1;
        movies.add(position, new Movie("Nueva Imagen " + (++counter), R.drawable.newmovie ));
        adapter.notifyItemInserted(position);
        layoutManager.scrollToPosition(position);
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE);
    }

    private void removeMovie(int position) {
        movies.remove(position);
        adapter.notifyItemRemoved(position);
//        layoutManager.scrollToPosition(position);
    }
}
