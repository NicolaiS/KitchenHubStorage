package kr.kaist.resl.kitchenhubstorage.contentprovider;

import android.content.ContentResolver;

/**
 * Created by nicolais on 4/23/15.
 *
 * ContentProvider contracts
 */
public final class Contract {

    public final static class DataSource {
        private static final String CONTENT_POSTFIX = "/kitchenhubstorage_datasource";
        public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + CONTENT_POSTFIX;
        public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + CONTENT_POSTFIX;
    }

    public final static class Container {
        private static final String CONTENT_POSTFIX = "/kitchenhubstorage_container";
        public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + CONTENT_POSTFIX;
        public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + CONTENT_POSTFIX;
    }

    public final static class DatasourceContainerRelation {
        private static final String CONTENT_POSTFIX = "/kitchenhubstorage_reader_container_reation";
        public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + CONTENT_POSTFIX;
        public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + CONTENT_POSTFIX;
    }

    public final static class Product {
        private static final String CONTENT_POSTFIX = "/kitchenhubstorage_products";
        public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + CONTENT_POSTFIX;
        public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + CONTENT_POSTFIX;
    }

    public final static class ProductInfoMeta {
        private static final String CONTENT_POSTFIX = "/kitchenhubstorage_product_info_meta";
        public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + CONTENT_POSTFIX;
        public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + CONTENT_POSTFIX;
    }

    public final static class AttrContainer {
        private static final String CONTENT_POSTFIX = "/kitchenhubstorage_attr_container";
        public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + CONTENT_POSTFIX;
        public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + CONTENT_POSTFIX;
    }

    public final static class AttrContainerAttribute {
        private static final String CONTENT_POSTFIX = "/kitchenhubstorage_attr_container_attribute";
        public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + CONTENT_POSTFIX;
        public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + CONTENT_POSTFIX;
    }

    public final static class Attribute {
        private static final String CONTENT_POSTFIX = "/kitchenhubstorage_attribute";
        public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + CONTENT_POSTFIX;
        public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + CONTENT_POSTFIX;
    }

    public final static class Recall {
        private static final String CONTENT_POSTFIX = "/kitchenhubstorage_recall";
        public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + CONTENT_POSTFIX;
        public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + CONTENT_POSTFIX;
    }
}