package com.mhmdawad.newsapp.binding;

import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.RequestManager;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class ViewsBinding {

    @BindingAdapter({"loadImage", "reqManager"})
    public static void changeImage(ImageView imageView , String imageUrl,RequestManager requestManager){
        requestManager.load(imageUrl).into(imageView);
    }


    @BindingAdapter("openWebView")
    public static void openArticle(WebView webView, String link){
        webView.loadUrl(link);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                view.loadUrl(request.getUrl().toString());
                return false;
            }
        });
    }

    @BindingAdapter("dateFormat")
    public static void getTodayDetailsDate(TextView textView, String date) {
        SimpleDateFormat input = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss'Z'", Locale.getDefault());
        SimpleDateFormat output = new SimpleDateFormat("dd MMMM, hh:mm a", Locale.getDefault());
        Date d = null;
        try {
            d = input.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if(d != null)
            textView.setText(output.format(d));
        else
            textView.setText(date);
    }
}
