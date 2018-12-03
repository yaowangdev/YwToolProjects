package com.appdev.common.lib.file;

import android.content.Context;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

import okio.BufferedSource;
import okio.Okio;

public class MyFileUtils {

    /**
     * 读取Asset文件内容
     *
     * @param context
     * @param fileName
     * @return
     * @throws IOException
     */
    public static String readStringFromAsset(Context context, String fileName)
            throws IOException {
        BufferedSource bufferedSource = null;
        InputStream in = null;
        try {
            in = context.getResources().getAssets().open(fileName);
            bufferedSource = Okio.buffer(Okio.source(in));
            return bufferedSource.readString(Charset.forName("UTF-8"));
        } finally {
            if (bufferedSource != null) {
                bufferedSource.close();
            }
            if (in != null) {
                in.close();
            }
        }
    }



}
