package modelo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by alcohonsilver on 05/10/17.
 */

public class DAOContactos {
    private  Context _contexto;
    private SQLiteDatabase _midb;

    public DAOContactos(Context contexto){
        this._contexto = contexto;
        this._midb = new MiDBOpenHelper(contexto).getWritableDatabase();
    }


    public long insert(Contacto c){

        ContentValues cv = new ContentValues();

        cv.put(MiDBOpenHelper.COLUMNS_CONTACTOS[1],c.getNombre());
        cv.put(MiDBOpenHelper.COLUMNS_CONTACTOS[2],c.getCorreo_electronico());
        cv.put(MiDBOpenHelper.COLUMNS_CONTACTOS[3],c.getTwitter());
        cv.put(MiDBOpenHelper.COLUMNS_CONTACTOS[4],c.getTelefono());
        cv.put(MiDBOpenHelper.COLUMNS_CONTACTOS[5],c.getFecha_nacimiento().getTime());

        return _midb.insert(MiDBOpenHelper.TABLE_CONTACTOS_NAME,null,cv) ;

    }

    public long update(Contacto c){
        ContentValues cv = new ContentValues();

        cv.put(MiDBOpenHelper.COLUMNS_CONTACTOS[1],c.getNombre());
        cv.put(MiDBOpenHelper.COLUMNS_CONTACTOS[2],c.getCorreo_electronico());
        cv.put(MiDBOpenHelper.COLUMNS_CONTACTOS[3],c.getTwitter());
        cv.put(MiDBOpenHelper.COLUMNS_CONTACTOS[4],c.getTelefono());
        cv.put(MiDBOpenHelper.COLUMNS_CONTACTOS[5],c.getFecha_nacimiento().getTime());

        return _midb.update(MiDBOpenHelper.TABLE_CONTACTOS_NAME,
                cv,
                "_id=?",
                new String[] { String.valueOf( c.id)});
    }

    public List<Contacto> getAll(){
        List<Contacto> ls=null;

        Cursor c = _midb.query(MiDBOpenHelper.TABLE_CONTACTOS_NAME,
                MiDBOpenHelper.COLUMNS_CONTACTOS,
                null,
                null,
                null,
                null,
                MiDBOpenHelper.COLUMNS_CONTACTOS[1]);

         if (c.moveToFirst()) {
            ls = new ArrayList<Contacto>();
             do {
                Contacto ctc = new Contacto();

                 ctc.setId(
                         c.getInt(
                                 c.getColumnIndex(
                                         MiDBOpenHelper.COLUMNS_CONTACTOS[0])
                         )
                 );
                 ctc.setNombre(c.getString(1));
                 ctc.setCorreo_electronico(c.getString(2));
                 ctc.setTwitter(c.getString(3));
                 ctc.setTelefono(c.getString(4));
                 ctc.setFecha_nacimiento(new Date(c.getLong(5)));

                 ls.add(ctc);

            }while(c.moveToNext());
         }

        return ls;
    }

    public long delete(Contacto c){
        return _midb.delete(MiDBOpenHelper.TABLE_CONTACTOS_NAME, "_id=?",
                new String[]{String.valueOf(c.id)});
    }

    public List<Contacto> search(String contactName){
        List<Contacto> lsContact = new ArrayList<>();

        Cursor cursor = _midb.query(MiDBOpenHelper.TABLE_CONTACTOS_NAME,
                MiDBOpenHelper.COLUMNS_CONTACTOS,
                MiDBOpenHelper.COLUMNS_CONTACTOS[1] + "like '%?%'",
                new String[]{contactName}, null, null, MiDBOpenHelper.COLUMNS_CONTACTOS[1]);

        try {
            while (cursor.moveToNext()){
                Contacto contacto = new Contacto(cursor.getInt(0), cursor.getString(1),
                        cursor.getString(2), cursor.getString(3), cursor.getString(4),
                        new Date(cursor.getLong(5)));
                lsContact.add(contacto);
            }
        } finally {
            cursor.close();
        }

        return  lsContact;

    }

}
