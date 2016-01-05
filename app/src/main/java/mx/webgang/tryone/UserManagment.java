package mx.webgang.tryone;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class UserManagment {
    private SharedPreferences sp;
    private SQLiteDatabase database;
    private userDBHelper dbHelper;


    public UserManagment(Context context) {
        sp = context.getSharedPreferences("main", 0);
        dbHelper = new userDBHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public void addUser(String[] a) {
        database.rawQuery("INSERT OR REPLACE INTO user VALUES (1,?,?,?,?,?,?,?,?,?,?,?,?,?);", a);
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean("saved", true);
        editor.commit();
    }

    public User getUser() {
        User user = new User();
        Cursor cursor = database.rawQuery("SELECT * FROM user", null);
        cursor.moveToFirst();
        user.setId(cursor.getLong(0));
        user.setNombre(cursor.getString(1));
        user.setCarrera(cursor.getString(2));
        user.setPuesto(cursor.getString(3));
        user.setImagen(cursor.getString(4));
        user.setHeader(cursor.getString(5));
        user.setCel(cursor.getString(6));
        user.setTelefono(cursor.getString(7));
        user.setEmail(cursor.getString(8));
        user.setDir(cursor.getString(9));
        user.setNacionalidad(cursor.getString(10));
        user.setNacimiento(cursor.getString(11));
        user.setEcivil(cursor.getString(12));
        user.setObjetico(cursor.getString(13));
        return user;
    }
}
