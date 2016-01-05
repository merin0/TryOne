package mx.webgang.tryone;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;


public class AnimateToolbar extends AppCompatActivity {

    String TITLES[] = {"5532737179",
            "54211224",
            "ga.marin.ce@gmail.com",
            "Soltero, Mexicano, 27 años",
            "Hidalgo 7 Col. Sta Úrsula Coapa, Del Coyoacán",
            "Lenguajes de programación:\nC/C++, C#, Java, HTML5, CSS3, javascript, JQuerry, PHP.",
            "SDK de Android & iOS.\n" +
                    "IDEs: Appcelerator (Titanium), Android Studio, Xcode, Eclipse.\n" +
                    "Modelado básico 3D, Unity 3D.",
            "DB: MySQL, SQLite.",
            "SO: Linux, OSX, Windows.",
            "Idiomas:\nInglés: 75% (B2.4/4 MCER)\nFrancés: 30%",
            "Responsabilidad, iniciativa, tolerancia a la fustración, creatividad, compromiso, planeación, trabajo en equipo, aprendizaje continuo."
    };

    int ICONS[] = {R.drawable.ic_action_phone,
            R.drawable.ic_action_call,
            R.drawable.ic_action_email,
            R.drawable.ic_action_person,
            R.drawable.ic_action_place,
            R.drawable.ic_action_keyboard,
            R.drawable.ic_action_phone,
            R.drawable.ic_action_storage,
            R.drawable.ic_action_dock,
            R.drawable.ic_action_web_site,
            R.drawable.ic_action_accept
    };
    CollapsingToolbarLayout collapsingToolbar;

    RecyclerView recyclerView;
    RecyclerView.Adapter mAdapter;
    RecyclerView.LayoutManager mLayoutManager;
    int mutedColor = R.attr.colorPrimary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contact);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.anim_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle("César García");

        ImageView header = (ImageView) findViewById(R.id.header);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.header);

        Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
            @Override
            public void onGenerated(Palette palette) {
                mutedColor = palette.getMutedColor(R.attr.colorPrimary);
                collapsingToolbar.setContentScrimColor(mutedColor);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    collapsingToolbar.setStatusBarScrimColor(mutedColor);
                    getWindow().setNavigationBarColor(mutedColor);
                }
            }
        });

        recyclerView = (RecyclerView) findViewById(R.id.scrollableview);
        recyclerView.setHasFixedSize(true);
        mAdapter = new MiniAdapter(TITLES, ICONS);
        recyclerView.setAdapter(mAdapter);
        mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            case R.id.action_settings:
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}