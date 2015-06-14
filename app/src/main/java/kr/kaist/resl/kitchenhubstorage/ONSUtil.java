package kr.kaist.resl.kitchenhubstorage;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import constants.KHBroadcasts;
import constants.ProviderConstants;

/**
 * Created by nicolais on 4/29/15.
 */
public class ONSUtil {

    private static final String ONS_PREFS = "ons_prefs";

    private static final String CONTENT_POSTFIX = "/kitchenhubstorage_ons_addr";
    public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + CONTENT_POSTFIX;

    /**
     * Get stored ONS address
     *
     * @param context context
     * @return ONS address. null if not found.
     */
    public static String getONSaddr(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(ONS_PREFS, Context.MODE_PRIVATE);
        return prefs.getString(ProviderConstants.ONS_ADDR, null);
    }

    /**
     * Insert/Update ONS address
     *
     * @param context context
     * @param onsAddr ONS address
     */
    public static void insertONSaddr(Context context, String onsAddr) {
        SharedPreferences prefs = context.getSharedPreferences(ONS_PREFS, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();

        editor.putString(ProviderConstants.ONS_ADDR, onsAddr);
        editor.commit();
    }
}
