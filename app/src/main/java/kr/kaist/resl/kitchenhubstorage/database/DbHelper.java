package kr.kaist.resl.kitchenhubstorage.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import constants.database.KHSchemaAttrContainer;
import constants.database.KHSchemaAttrContainerAttribute;
import constants.database.KHSchemaAttribute;
import constants.database.KHSchemaContainer;
import constants.database.KHSchemaDataSource;
import constants.database.KHSchemaDatasourceContainer;
import constants.database.KHSchemaProduct;
import constants.database.KHSchemaProductInfoMeta;
import constants.database.KHSchemaRecall;

/**
 * Created by NicolaiSonne on 21-04-2015.
 */
public class DbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "kitchenhub.db";
    private static final int DATABASE_VERSION = 1;

    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * Create all databases
     *
     * @param sqLiteDatabase db
     */
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(KHSchemaDataSource.SQL_CREATE);
        sqLiteDatabase.execSQL(KHSchemaContainer.SQL_CREATE);
        sqLiteDatabase.execSQL(KHSchemaDatasourceContainer.SQL_CREATE);
        sqLiteDatabase.execSQL(KHSchemaProduct.SQL_CREATE);
        sqLiteDatabase.execSQL(KHSchemaProductInfoMeta.SQL_CREATE);
        sqLiteDatabase.execSQL(KHSchemaAttrContainer.SQL_CREATE);
        sqLiteDatabase.execSQL(KHSchemaAttrContainerAttribute.SQL_CREATE);
        sqLiteDatabase.execSQL(KHSchemaAttribute.SQL_CREATE);
        sqLiteDatabase.execSQL(KHSchemaRecall.SQL_CREATE);

    }

    /**
     * Delete old database and create new
     *
     * @param sqLiteDatabase db
     * @param oldVersion     oV
     * @param newVersion     nW
     */
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        Log.w(DbHelper.class.getName(), "Upgrading database from version " + oldVersion + " to " + newVersion + ", which will destroy all old data");
        sqLiteDatabase.execSQL(KHSchemaDataSource.SQL_DELETE);
        sqLiteDatabase.execSQL(KHSchemaContainer.SQL_DELETE);
        sqLiteDatabase.execSQL(KHSchemaDatasourceContainer.SQL_DELETE);
        sqLiteDatabase.execSQL(KHSchemaProduct.SQL_DELETE);
        sqLiteDatabase.execSQL(KHSchemaProductInfoMeta.SQL_DELETE);
        sqLiteDatabase.execSQL(KHSchemaAttrContainer.SQL_DELETE);
        sqLiteDatabase.execSQL(KHSchemaAttrContainerAttribute.SQL_DELETE);
        sqLiteDatabase.execSQL(KHSchemaAttribute.SQL_DELETE);
        sqLiteDatabase.execSQL(KHSchemaRecall.SQL_DELETE);
        onCreate(sqLiteDatabase);
    }
}
