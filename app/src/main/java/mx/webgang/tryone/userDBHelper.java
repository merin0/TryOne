package mx.webgang.tryone;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class userDBHelper extends SQLiteOpenHelper {

    public static final String T_USER = "user";
    public static final String T_EXPE = "expe";
    public static final String T_ESTU = "estu";
    public static final String T_IDIOMAS = "idiomas";
    public static final String T_SKILLS = "skills";
    public static final String T_CUALIDADEs = "cualidades";

    private static final String DATABASE_NAME = "cv.db";
    private static final int DATABASE_VERSION = 1;

    private static final String CREATE_USER = "CREATE TABLE  " + T_USER +
            " (_id INTEGER PRIMARY KEY AUTOINCREMENT, nombre TEXT, carrera TEXT, puesto TEXT, " +
            "imagen TEXT, header TEXT, cel TEXT, telefono TEXT, email TEXT, dir TEXT, " +
            "nacionalidad TEXT, nacimiento TEXT, ecivil TEXT, objetico TEXT);";


    private static final String CREATE_EXPE = "CREATE TABLE  " + T_EXPE +
            " (_id INTEGER PRIMARY KEY AUTOINCREMENT, empresa TEXT, inicio TEXT, fin TEXT, " +
            "actividades TEXT, vera TEXT);";

    private static final String CREATE_ESTU = "CREATE TABLE  " + T_ESTU +
            " (_id INTEGER PRIMARY KEY AUTOINCREMENT, nombre TEXT, inicio TEXT, fin TEXT, " +
            "lugar TEXT, desc TEXT, verd TEXT);";

    private static final String CREATE_IDIOMA = "CREATE TABLE  " + T_IDIOMAS +
            "(_id INTEGER PRIMARY KEY AUTOINCREMENT, nombre TEXT, porcen TEXT, desc TEXT, verd TEXT);";

    private static final String CREATE_SKILLS = "CREATE TABLE  " + T_SKILLS +
            "(_id INTEGER PRIMARY KEY AUTOINCREMENT, tipo TEXT, titulo TEXT, icon TEXT, lista TEXT);";

    private static final String CREATE_CUALIDADES = "CREATE TABLE  " + T_CUALIDADEs +
            "(_id INTEGER PRIMARY KEY AUTOINCREMENT, texto TEXT, desc TEXT, verd TEXT);";


    public userDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(CREATE_USER);
        database.execSQL(CREATE_EXPE);
        database.execSQL(CREATE_ESTU);
        database.execSQL(CREATE_IDIOMA);
        database.execSQL(CREATE_SKILLS);
        database.execSQL(CREATE_CUALIDADES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        System.out.println("onUpgrade ñ_ñ");
    }

}
