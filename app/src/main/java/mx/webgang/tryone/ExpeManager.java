package mx.webgang.tryone;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class ExpeManager {
    private SQLiteDatabase database;
    private userDBHelper dbHelper;

    public ExpeManager(Context context) {
        dbHelper = new userDBHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public void addExpe(String[] a) {
        database.rawQuery("INSERT INTO expe (empresa, inicio, fin, actividades, vera)" +
                " VALUES (?,?,?,?,?);", a);
    }

    public void deleteExpe(Long id) {
        String[] a = new String[]{id.toString()};
        database.rawQuery("DELETE FROM expe WHERE _id = ?;", a);
    }

    public Expe[] getAllExpe() {
        Cursor cursor = database.rawQuery("SELECT * FROM expe;", null);
        int count = cursor.getCount();
        Expe[] expe = new Expe[count];
        cursor.moveToFirst();
        for (int i = 0; i < count; i++) {
            expe[i] = new Expe();
            expe[i].setId(cursor.getLong(0));
            expe[i].setEmpresa(cursor.getString(1));
            expe[i].setInicio(cursor.getString(2));
            expe[i].setFin(cursor.getString(3));
            expe[i].setActividades(cursor.getString(4));
            expe[i].setVera(cursor.getString(5));
            cursor.moveToNext();
        }
        System.out.println("Hola Mundo ñ_ñ: count: " + expe.length);
        return expe;
    }

}
