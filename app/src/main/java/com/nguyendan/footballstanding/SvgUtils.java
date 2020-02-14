package com.nguyendan.footballstanding;

import android.content.Context;
import android.util.Log;
import android.widget.ImageView;

import com.pixplicity.sharp.Sharp;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.InputStream;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class SvgUtils {
        private static OkHttpClient httpClient;

        public static void fetchSvg(Context context, String url, ImageView target) throws IOException {
            httpClient = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(url).build();
            httpClient.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(@NotNull Call call, @NotNull IOException e) {
                    e.printStackTrace();
                }

                @Override
                public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                    InputStream stream = response.body().byteStream();
                    try {
                        Sharp.loadInputStream(stream).into(target);
                    }catch (Exception e){

                    }
                    stream.close();
                }
            });
        }

}
