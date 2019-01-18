package volkodav.ampilogova.darya.projecte_github;

/* EN AQUESTA CLASSE IMPLEMENTAREM ELS MÈTODES NECESSARIS PER INTERACTUAR AMB LA BASE DE DADES
AMB LA CLASSE SQLiteDatabase */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;
import java.util.List;

public class Implementacio {

    private SQLiteDatabase database;

    // CLASSE DE AJUDA
    private Controlador dbAjuda;

    private String[] allColumnsVi = { Controlador.COLUMN_ID,Controlador.COLUMN_NOMVI,Controlador.COLUMN_ANADA,
            Controlador.COLUMN_LLOC, Controlador.COLUMN_GRADUACIO,Controlador.COLUMN_DATA,
            Controlador.COLUMN_COMENTARI,Controlador.COLUMN_IDBODEGA,
            Controlador.COLUMN_IDDENOMINACIO,Controlador.COLUMN_PREU,
            Controlador.COLUMN_VALOLFATIVA,Controlador.COLUMN_VALGUSTATIVA,
            Controlador.COLUMN_VALVISUAL,Controlador.COLUMN_NOTA,Controlador.COLUMN_FOTO,
            Controlador.COLUMN_TIPUS};

    // CONSTRUCTOR
    public Implementacio(Context context) {
        dbAjuda = new Controlador(context);
    }

    public void open() {
        database = dbAjuda.getWritableDatabase();
    }

    public void close() {
        dbAjuda.close();
    }

    // CREAM UN VI NOU
    public Vi crearVi(Vi vi) {
        ContentValues values = new ContentValues();
        values.put(Controlador.COLUMN_NOMVI, vi.getNomVi());
        values.put(Controlador.COLUMN_ANADA, vi.getAnada());
        values.put(Controlador.COLUMN_TIPUS, vi.getTipus());
        values.put(Controlador.COLUMN_LLOC, vi.getLloc());
        values.put(Controlador.COLUMN_GRADUACIO, vi.getGraduacio());
        values.put(Controlador.COLUMN_DATA, String.valueOf(vi.getData()));
        values.put(Controlador.COLUMN_COMENTARI, vi.getComentari());
        values.put(Controlador.COLUMN_IDBODEGA, vi.getIdBodega());
        values.put(Controlador.COLUMN_IDDENOMINACIO,vi.getIdDenominacio());
        values.put(Controlador.COLUMN_PREU,vi.getPreu());
        values.put(Controlador.COLUMN_VALOLFATIVA,vi.getValOlfativa());
        values.put(Controlador.COLUMN_VALGUSTATIVA,vi.getValGustativa());
        values.put(Controlador.COLUMN_VALVISUAL,vi.getValVisual());
        values.put(Controlador.COLUMN_NOTA,vi.getNota() );
        values.put(Controlador.COLUMN_FOTO,vi.getFoto() );
        long insertId = database.insert(Controlador.TABLE_VI, null,values);
        vi.setId(insertId);
        return vi;
    }

    // ACTUALITZAR UN VI
    public boolean actualitzarVi(Vi vi) {
        ContentValues values = new ContentValues();
        long id = vi.getId();
        values.put(Controlador.COLUMN_NOMVI, vi.getNomVi());
        values.put(Controlador.COLUMN_ANADA, vi.getAnada());
        values.put(Controlador.COLUMN_LLOC, vi.getLloc());
        values.put(Controlador.COLUMN_TIPUS, vi.getTipus());
        values.put(Controlador.COLUMN_GRADUACIO, vi.getGraduacio());
        values.put(Controlador.COLUMN_DATA, String.valueOf(vi.getData()));
        values.put(Controlador.COLUMN_COMENTARI, vi.getComentari());
        values.put(Controlador.COLUMN_IDBODEGA, vi.getIdBodega());
        values.put(Controlador.COLUMN_IDDENOMINACIO,vi.getIdDenominacio());
        values.put(Controlador.COLUMN_PREU,vi.getPreu());
        values.put(Controlador.COLUMN_VALOLFATIVA,vi.getValOlfativa());
        values.put(Controlador.COLUMN_VALGUSTATIVA,vi.getValGustativa());
        values.put(Controlador.COLUMN_VALVISUAL,vi.getValVisual());
        values.put(Controlador.COLUMN_NOTA,vi.getNota() );
        values.put(Controlador.COLUMN_FOTO,vi.getFoto() );
        return database.update(Controlador.TABLE_VI, values,Controlador.COLUMN_ID + "=" +
                id, null) > 0;
    }

    // ESBORRAR UN VI
    public void esborrarVi(Vi vi) {
        long id = vi.getId();
        database.delete(Controlador.TABLE_VI, Controlador.COLUMN_ID + " = " + id,
                null);
    }

    // MÈTODE QUE ENS TORNA EL VI, A TRAVÉS DEL ID PASSAT PER PARÀMETRE
    public Vi getVi(long id) {
        Vi vi;
        Cursor cursor = database.query(Controlador.TABLE_VI,
                allColumnsVi, Controlador.COLUMN_ID + " = " + id, null,
                null, null, null);
        if (cursor.getCount()>0) {
            cursor.moveToFirst();
            vi = cursorToVi(cursor);
        // SI EL ID ÉS IGUAL A -1, VOLDRÀ DIR QUE NO ES TROBA EL VI
        } else { vi = new Vi(); }
        cursor.close();
        return vi;
    }

    // MÈTODE QUE ENS TORNARÀ LA LLISTA DE TOTS ELS VINS
    public List<Vi> getAllVi() {
        List<Vi> vins = new ArrayList<Vi>();
        Cursor cursor = database.query(Controlador.TABLE_VI, allColumnsVi, null,
                null, null, null, Controlador.COLUMN_DATA + " DESC");
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Vi vi = cursorToVi(cursor);
            vins.add(vi);
            cursor.moveToNext();
        }
        // ENS ASSEGUREM DE TANCAR EL CURSOR
        cursor.close();
        return vins;
    }

    private Vi cursorToVi(Cursor cursor) {
        Vi v = new Vi();
        v.setId(cursor.getLong(0));
        v.setNomVi(cursor.getString(1));
        v.setAnada(cursor.getString(2));
        v.setLloc(cursor.getString(3));
        v.setGraduacio(cursor.getString(4));
        v.setData(cursor.getString(5));
        v.setComentari(cursor.getString(6));
        v.setIdBodega(cursor.getLong(7));
        v.setIdBodega(cursor.getLong(8));
        v.setPreu(cursor.getFloat(9));
        v.setValOlfativa(cursor.getString(10));
        v.setValGustativa(cursor.getString(11));
        v.setValVisual(cursor.getString(12));
        v.setNota(cursor.getInt(13));
        v.setFoto(cursor.getString(14));
        return v;
    }
}