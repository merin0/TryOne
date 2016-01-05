package mx.webgang.tryone;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    //Activity
    String headerC[] = {"Objetivo Profesional", "Experiencia Profesional", "Formación académica"};
    String objProf = "<p>Emplear conocimientos y habilidades, así como las herramientas que brinda la ingeniería para diseñar, actualizar y mantener sistemas que se usan comúnmente en la industria, tanto software como hardware, colaborando con el desarrollo de la empresa y de esta manera lograr un fortalecimiento profesional.</p>";
    String expProf = "<p><strong>Programador en Webgang</strong><br>De julio de 2014 a la fecha<br>Actividades:</p>" +
            "<p> ○ Programador Web<br> ○ Mantenimiento y actualización Web<br> ○ Programación de Web Services(PHP)<br> ○ Administrador de Servidores<br> ○ Diseño de UI/UX para aplicaciones<br> ○ Programación de aplicaciones móviles con Appcelerator.<br> ○ Soporte Técnico</p>" +
            "<p><strong>Desarrollador Independiente de Aplicaciones</strong><br>De enero de 2013 a diciembre de 2013</p>";
    String formAcad = "<p><strong>Diplomado Diseño y Programación de Videojuegos</strong><br>De 1 de agosto de 2014 al 17 de enero de 2015<br>Facultad de Ingeniería - UNAM</p>" +
            "<p><strong>Estudios de Ingeniería Eléctrica y Electrónica (no titulado)</strong><br>De 2008 a 2013<br>Facultad de Ingeniería - UNAM</p>" +
            "<p><strong>Curso PHP + MySQL</strong><br>Del 6 al 17 de enero de 2014<br>Facultad de Ingeniería - UNAM</p>" +
            "<p><strong>Curso Algoritmos Seriales vs Algoritmos Paralelos</strong><br>De 29 de julio al 2 de agosto de 2013<br>Facultad de Ingeniería - UNAM</p>" +
            "<p><strong>Curso Administración de Sistemas Linux</strong><br>De 5 al 23 de junio de 2012, Proteco<br>Facultad de Ingeniería - UNAM</p>";
    String contenth[] = {objProf, expProf, formAcad};

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

    int PROFILE = R.drawable.me;

    private Toolbar toolbar;

    RecyclerView mRecyclerView;
    RecyclerView.Adapter mAdapter;
    RecyclerView.LayoutManager mLayoutManager;

    RecyclerView cardsSummary;
    RecyclerView.Adapter summarymAdapter;
    RecyclerView.LayoutManager summaryLayoutMan;

    DrawerLayout Drawer;
    ActionBarDrawerToggle mDrawerToggle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);

        mRecyclerView = (RecyclerView) findViewById(R.id.RecyclerView);
        mRecyclerView.setHasFixedSize(true);
        mAdapter = new MyAdapter(TITLES, ICONS, getResources().getString(R.string.name), getResources().getString(R.string.carrera), PROFILE);
        mRecyclerView.setAdapter(mAdapter);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        cardsSummary = (RecyclerView) findViewById(R.id.content);
        cardsSummary.setHasFixedSize(true);
        summarymAdapter = new CardAdapter(getApplicationContext());
        cardsSummary.setAdapter(summarymAdapter);
        summaryLayoutMan = new LinearLayoutManager(this);
        cardsSummary.setLayoutManager(summaryLayoutMan);

        Drawer = (DrawerLayout) findViewById(R.id.DrawerLayout);
        mDrawerToggle = new ActionBarDrawerToggle(this, Drawer, toolbar, R.string.openDrawer, R.string.closeDrawer) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };
        Drawer.setDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return item.getItemId() == R.id.action_settings || super.onOptionsItemSelected(item);
    }
}
