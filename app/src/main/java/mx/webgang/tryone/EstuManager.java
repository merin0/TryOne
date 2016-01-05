package mx.webgang.tryone;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class EstuManager {
    private SQLiteDatabase database;
    private userDBHelper dbHelper;

    public EstuManager(Context context) {
        dbHelper = new userDBHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public void addEstu(String[] a) {
        database.rawQuery("INSERT INTO estu (nombre, inicio, fin, lugar, desc, verd)" +
                " VALUES (?,?,?,?,?,?);", a);
    }

    public void deleteEstu(Long id) {
        String[] a = new String[]{id.toString()};
        database.rawQuery("DELETE FROM estu WHERE _id = ?;", a);
    }

    public Estu[] getAllEstu() {
        Cursor cursor = database.rawQuery("SELECT * FROM estu;", null);
        int count = cursor.getCount();
        Estu[] estu = new Estu[count];
        cursor.moveToFirst();
        for (int i = 0; i < count; i++) {
            estu[i] = new Estu();
            estu[i].setId(cursor.getLong(0));
            estu[i].setNombre(cursor.getString(1));
            estu[i].setNombre(cursor.getString(2));
            estu[i].setNombre(cursor.getString(3));
            estu[i].setNombre(cursor.getString(4));
            estu[i].setNombre(cursor.getString(5));
            estu[i].setNombre(cursor.getString(6));
            cursor.moveToNext();
        }
        return estu;
    }
}
