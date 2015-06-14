package kr.kaist.resl.kitchenhubstorage.contentprovider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.text.TextUtils;

import constants.ProviderConstants;
import constants.database.KHSchema;
import constants.database.KHSchemaAttrContainer;
import constants.database.KHSchemaAttrContainerAttribute;
import constants.database.KHSchemaAttribute;
import constants.database.KHSchemaContainer;
import constants.database.KHSchemaDataSource;
import constants.database.KHSchemaDatasourceContainer;
import constants.database.KHSchemaProduct;
import constants.database.KHSchemaProductInfoMeta;
import constants.database.KHSchemaRecall;
import kr.kaist.resl.kitchenhubstorage.ONSUtil;
import kr.kaist.resl.kitchenhubstorage.database.DbHelper;

/**
 * Created by NicolaiSonne on 21-04-2015.
 * <p/>
 * Content Provider methods
 */
public class DataProvider extends ContentProvider {

    private static final int DATA_SOURCE_LIST = 1;
    private static final int DATA_SOURCE_ID = 2;
    private static final int CONTAINER_LIST = 5;
    private static final int CONTAINER_ID = 6;
    private static final int DS_C_LIST = 9;
    private static final int DS_C_ID = 10;
    private static final int PRODUCT_LIST = 13;
    private static final int PRODUCT_ID = 14;
    private static final int ONS_ADDR = 17;
    private static final int PIM_LIST = 19;
    private static final int PIM_ID = 20;
    private static final int ATTR_CONTAINER_LIST = 23;
    private static final int ATTR_CONTAINER_ID = 24;
    private static final int ATTR_CONTAINER_ATTRIBUTE_LIST = 27;
    private static final int ATTR_CONTAINER_ATTRIBUTE_ID = 28;
    private static final int ATTRIBUTE_LIST = 31;
    private static final int ATTRIBUTE_ID = 32;
    private static final int RECALL_LIST = 35;
    private static final int RECALL_ID = 36;

    private static final UriMatcher URI_MATCHER;

    private DbHelper dbHelper = null;

    // prepare the UriMatcher
    static {
        URI_MATCHER = new UriMatcher(UriMatcher.NO_MATCH);

        URI_MATCHER.addURI(ProviderConstants.DB_AUTHORITY, KHSchemaDataSource.TABLE_NAME, DATA_SOURCE_LIST);
        URI_MATCHER.addURI(ProviderConstants.DB_AUTHORITY, KHSchemaDataSource.TABLE_NAME + "/#", DATA_SOURCE_ID);
        URI_MATCHER.addURI(ProviderConstants.DB_AUTHORITY, KHSchemaContainer.TABLE_NAME, CONTAINER_LIST);
        URI_MATCHER.addURI(ProviderConstants.DB_AUTHORITY, KHSchemaContainer.TABLE_NAME + "/#", CONTAINER_ID);
        URI_MATCHER.addURI(ProviderConstants.DB_AUTHORITY, KHSchemaDatasourceContainer.TABLE_NAME, DS_C_LIST);
        URI_MATCHER.addURI(ProviderConstants.DB_AUTHORITY, KHSchemaDatasourceContainer.TABLE_NAME + "/#", DS_C_ID);
        URI_MATCHER.addURI(ProviderConstants.DB_AUTHORITY, KHSchemaProduct.TABLE_NAME, PRODUCT_LIST);
        URI_MATCHER.addURI(ProviderConstants.DB_AUTHORITY, KHSchemaProduct.TABLE_NAME + "/#", PRODUCT_ID);
        URI_MATCHER.addURI(ProviderConstants.DB_AUTHORITY, ProviderConstants.ONS_ADDR, ONS_ADDR);
        URI_MATCHER.addURI(ProviderConstants.DB_AUTHORITY, KHSchemaProductInfoMeta.TABLE_NAME, PIM_LIST);
        URI_MATCHER.addURI(ProviderConstants.DB_AUTHORITY, KHSchemaProductInfoMeta.TABLE_NAME + "/#", PIM_ID);
        URI_MATCHER.addURI(ProviderConstants.DB_AUTHORITY, KHSchemaAttrContainer.TABLE_NAME, ATTR_CONTAINER_LIST);
        URI_MATCHER.addURI(ProviderConstants.DB_AUTHORITY, KHSchemaAttrContainer.TABLE_NAME + "/#", ATTR_CONTAINER_ID);
        URI_MATCHER.addURI(ProviderConstants.DB_AUTHORITY, KHSchemaAttrContainerAttribute.TABLE_NAME, ATTR_CONTAINER_ATTRIBUTE_LIST);
        URI_MATCHER.addURI(ProviderConstants.DB_AUTHORITY, KHSchemaAttrContainerAttribute.TABLE_NAME + "/#", ATTR_CONTAINER_ATTRIBUTE_ID);
        URI_MATCHER.addURI(ProviderConstants.DB_AUTHORITY, KHSchemaAttribute.TABLE_NAME, ATTRIBUTE_LIST);
        URI_MATCHER.addURI(ProviderConstants.DB_AUTHORITY, KHSchemaAttribute.TABLE_NAME + "/#", ATTRIBUTE_ID);
        URI_MATCHER.addURI(ProviderConstants.DB_AUTHORITY, KHSchemaRecall.TABLE_NAME, RECALL_LIST);
        URI_MATCHER.addURI(ProviderConstants.DB_AUTHORITY, KHSchemaRecall.TABLE_NAME + "/#", RECALL_ID);
    }

    @Override
    public boolean onCreate() {
        dbHelper = new DbHelper(getContext());
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        SQLiteQueryBuilder builder = new SQLiteQueryBuilder();

        switch (URI_MATCHER.match(uri)) {
            case DATA_SOURCE_ID:
            case CONTAINER_ID:
            case DS_C_ID:
            case PRODUCT_ID:
            case PIM_ID:
            case ATTR_CONTAINER_ID:
            case ATTR_CONTAINER_ATTRIBUTE_ID:
            case ATTRIBUTE_ID:
            case RECALL_ID:
                builder.appendWhere(KHSchema.CN_ID + " = " + uri.getLastPathSegment());
                break;
        }

        switch (URI_MATCHER.match(uri)) {
            case DATA_SOURCE_LIST:
            case DATA_SOURCE_ID:
                builder.setTables(KHSchemaDataSource.TABLE_NAME);
                break;
            case CONTAINER_LIST:
            case CONTAINER_ID:
                builder.setTables(KHSchemaContainer.TABLE_NAME);
                break;
            case DS_C_LIST:
            case DS_C_ID:
                builder.setTables(KHSchemaDatasourceContainer.TABLE_NAME);
                break;
            case PRODUCT_LIST:
            case PRODUCT_ID:
                builder.setTables(KHSchemaProduct.TABLE_NAME);
                break;
            case PIM_LIST:
            case PIM_ID:
                builder.setTables(KHSchemaProductInfoMeta.TABLE_NAME);
                break;
            case ATTR_CONTAINER_LIST:
            case ATTR_CONTAINER_ID:
                builder.setTables(KHSchemaAttrContainer.TABLE_NAME);
                break;
            case ATTR_CONTAINER_ATTRIBUTE_LIST:
            case ATTR_CONTAINER_ATTRIBUTE_ID:
                builder.setTables(KHSchemaAttrContainerAttribute.TABLE_NAME);
                break;
            case ATTRIBUTE_LIST:
            case ATTRIBUTE_ID:
                builder.setTables(KHSchemaAttribute.TABLE_NAME);
                break;
            case RECALL_LIST:
            case RECALL_ID:
                builder.setTables(KHSchemaRecall.TABLE_NAME);
                break;
            case ONS_ADDR:
                MatrixCursor cursor = new MatrixCursor(new String[]{ProviderConstants.ONS_ADDR});
                cursor.addRow(new Object[]{ONSUtil.getONSaddr(getContext())});
                return cursor;
            default:
                throw new IllegalArgumentException("Unsupported URI: " + uri);
        }

        Cursor cursor = builder.query(db, projection, selection, selectionArgs, null, null, sortOrder);

        return cursor;
    }


    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        String idStr;
        String where;
        switch (URI_MATCHER.match(uri)) {
            case DATA_SOURCE_ID:
            case CONTAINER_ID:
            case DS_C_ID:
            case PRODUCT_ID:
            case PIM_ID:
            case ATTR_CONTAINER_ID:
            case ATTR_CONTAINER_ATTRIBUTE_ID:
            case ATTRIBUTE_ID:
            case RECALL_ID:
                idStr = uri.getLastPathSegment();
                where = KHSchema.CN_ID + " = " + idStr;
                if (!TextUtils.isEmpty(selection)) {
                    where += " AND " + selection;
                }
                break;
            default:
                where = selection;
                break;
        }

        int updateCount;
        switch (URI_MATCHER.match(uri)) {
            case DATA_SOURCE_LIST:
            case DATA_SOURCE_ID:
                updateCount = db.update(KHSchemaDataSource.TABLE_NAME, values, where, selectionArgs);
                break;
            case CONTAINER_LIST:
            case CONTAINER_ID:
                updateCount = db.update(KHSchemaContainer.TABLE_NAME, values, where, selectionArgs);
                break;
            case DS_C_LIST:
            case DS_C_ID:
                updateCount = db.update(KHSchemaDatasourceContainer.TABLE_NAME, values, where, selectionArgs);
                break;
            case PRODUCT_LIST:
            case PRODUCT_ID:
                updateCount = db.update(KHSchemaProduct.TABLE_NAME, values, where, selectionArgs);
                break;
            case PIM_LIST:
            case PIM_ID:
                updateCount = db.update(KHSchemaProductInfoMeta.TABLE_NAME, values, where, selectionArgs);
                break;
            case ATTR_CONTAINER_LIST:
            case ATTR_CONTAINER_ID:
                updateCount = db.update(KHSchemaAttrContainer.TABLE_NAME, values, where, selectionArgs);
                break;
            case ATTR_CONTAINER_ATTRIBUTE_LIST:
            case ATTR_CONTAINER_ATTRIBUTE_ID:
                updateCount = db.update(KHSchemaAttrContainerAttribute.TABLE_NAME, values, where, selectionArgs);
                break;
            case ATTRIBUTE_LIST:
            case ATTRIBUTE_ID:
                updateCount = db.update(KHSchemaAttribute.TABLE_NAME, values, where, selectionArgs);
                break;
            case RECALL_LIST:
            case RECALL_ID:
                updateCount = db.update(KHSchemaRecall.TABLE_NAME, values, where, selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("Unsupported URI: " + uri);
        }

        return updateCount;
    }

    @Override
    public String getType(Uri uri) {
        switch (URI_MATCHER.match(uri)) {
            case DATA_SOURCE_LIST:
                return Contract.DataSource.CONTENT_TYPE;
            case DATA_SOURCE_ID:
                return Contract.DataSource.CONTENT_ITEM_TYPE;
            case CONTAINER_LIST:
                return Contract.Container.CONTENT_TYPE;
            case CONTAINER_ID:
                return Contract.Container.CONTENT_ITEM_TYPE;
            case DS_C_LIST:
                return Contract.DatasourceContainerRelation.CONTENT_TYPE;
            case DS_C_ID:
                return Contract.DatasourceContainerRelation.CONTENT_ITEM_TYPE;
            case PRODUCT_LIST:
                return Contract.Product.CONTENT_TYPE;
            case PRODUCT_ID:
                return Contract.Product.CONTENT_ITEM_TYPE;
            case PIM_LIST:
                return Contract.ProductInfoMeta.CONTENT_TYPE;
            case PIM_ID:
                return Contract.ProductInfoMeta.CONTENT_ITEM_TYPE;
            case ATTR_CONTAINER_LIST:
                return Contract.AttrContainer.CONTENT_TYPE;
            case ATTR_CONTAINER_ID:
                return Contract.AttrContainer.CONTENT_ITEM_TYPE;
            case ATTR_CONTAINER_ATTRIBUTE_LIST:
                return Contract.AttrContainerAttribute.CONTENT_TYPE;
            case ATTR_CONTAINER_ATTRIBUTE_ID:
                return Contract.AttrContainerAttribute.CONTENT_ITEM_TYPE;
            case ATTRIBUTE_LIST:
                return Contract.Attribute.CONTENT_TYPE;
            case ATTRIBUTE_ID:
                return Contract.Attribute.CONTENT_ITEM_TYPE;
            case RECALL_LIST:
                return Contract.Recall.CONTENT_TYPE;
            case RECALL_ID:
                return Contract.Recall.CONTENT_ITEM_TYPE;
            case ONS_ADDR:
                return ONSUtil.CONTENT_TYPE;
            default:
                throw new IllegalArgumentException("Unsupported URI: " + uri);
        }
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        switch (URI_MATCHER.match(uri)) {
            case DATA_SOURCE_ID:
            case CONTAINER_ID:
            case DS_C_ID:
            case PRODUCT_ID:
            case PIM_ID:
            case ATTR_CONTAINER_ID:
            case ATTR_CONTAINER_ATTRIBUTE_ID:
            case ATTRIBUTE_ID:
            case RECALL_ID:
                throw new IllegalArgumentException("Unsupported URI for insertion: " + uri);
        }

        SQLiteDatabase db = dbHelper.getWritableDatabase();

        long id = 0;
        switch (URI_MATCHER.match(uri)) {
            case DATA_SOURCE_LIST:
                id = db.insert(KHSchemaDataSource.TABLE_NAME, null, values);
                break;
            case CONTAINER_LIST:
                id = db.insert(KHSchemaContainer.TABLE_NAME, null, values);
                break;
            case DS_C_LIST:
                id = db.insert(KHSchemaDatasourceContainer.TABLE_NAME, null, values);
                break;
            case PRODUCT_LIST:
                id = db.insert(KHSchemaProduct.TABLE_NAME, null, values);
                break;
            case PIM_LIST:
                id = db.insert(KHSchemaProductInfoMeta.TABLE_NAME, null, values);
                break;
            case ATTR_CONTAINER_LIST:
                id = db.insert(KHSchemaAttrContainer.TABLE_NAME, null, values);
                break;
            case ATTR_CONTAINER_ATTRIBUTE_LIST:
                id = db.insert(KHSchemaAttrContainerAttribute.TABLE_NAME, null, values);
                break;
            case ATTRIBUTE_LIST:
                id = db.insert(KHSchemaAttribute.TABLE_NAME, null, values);
                break;
            case RECALL_LIST:
                id = db.insert(KHSchemaRecall.TABLE_NAME, null, values);
                break;
            case ONS_ADDR:
                String onsAddr = values.getAsString(ProviderConstants.ONS_ADDR);
                ONSUtil.insertONSaddr(getContext(), onsAddr);
                break;
        }
        return getUriForId(id, uri);
    }

    private Uri getUriForId(long id, Uri uri) {
        if (id > 0) {
            return ContentUris.withAppendedId(uri, id);
        }
        return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        String idStr;
        String where;
        switch (URI_MATCHER.match(uri)) {
            case DATA_SOURCE_ID:
            case CONTAINER_ID:
            case DS_C_ID:
            case PRODUCT_ID:
            case PIM_ID:
            case ATTR_CONTAINER_ID:
            case ATTR_CONTAINER_ATTRIBUTE_ID:
            case ATTRIBUTE_ID:
            case RECALL_ID:
                idStr = uri.getLastPathSegment();
                where = KHSchema.CN_ID + " = " + idStr;
                if (!TextUtils.isEmpty(selection)) {
                    where += " AND " + selection;
                }
                break;
            default:
                where = selection;
                break;
        }

        int delCount = 0;
        switch (URI_MATCHER.match(uri)) {
            case DATA_SOURCE_LIST:
            case DATA_SOURCE_ID:
                delCount = db.delete(KHSchemaDataSource.TABLE_NAME, where, selectionArgs);
                break;
            case CONTAINER_LIST:
            case CONTAINER_ID:
                delCount = db.delete(KHSchemaContainer.TABLE_NAME, where, selectionArgs);
                break;
            case DS_C_LIST:
            case DS_C_ID:
                delCount = db.delete(KHSchemaDatasourceContainer.TABLE_NAME, where, selectionArgs);
                break;
            case PRODUCT_LIST:
            case PRODUCT_ID:
                delCount = db.delete(KHSchemaProduct.TABLE_NAME, where, selectionArgs);
                break;
            case PIM_LIST:
            case PIM_ID:
                delCount = db.delete(KHSchemaProductInfoMeta.TABLE_NAME, where, selectionArgs);
                break;
            case ATTR_CONTAINER_LIST:
            case ATTR_CONTAINER_ID:
                delCount = db.delete(KHSchemaAttrContainer.TABLE_NAME, where, selectionArgs);
                break;
            case ATTR_CONTAINER_ATTRIBUTE_LIST:
            case ATTR_CONTAINER_ATTRIBUTE_ID:
                delCount = db.delete(KHSchemaAttrContainerAttribute.TABLE_NAME, where, selectionArgs);
                break;
            case ATTRIBUTE_LIST:
            case ATTRIBUTE_ID:
                delCount = db.delete(KHSchemaAttribute.TABLE_NAME, where, selectionArgs);
                break;
            case RECALL_LIST:
            case RECALL_ID:
                delCount = db.delete(KHSchemaRecall.TABLE_NAME, where, selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("Unsupported URI: " + uri);
        }

        return delCount;
    }
}
