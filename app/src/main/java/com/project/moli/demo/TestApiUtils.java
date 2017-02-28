package com.project.moli.demo;

import android.util.Log;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by Administrator on 2017/2/24.
 */

public class TestApiUtils {
    public static String getString(){
        final StringBuffer sbf = new StringBuffer();
        String url="http://wmhty.com/Api.ashx?action=getgoodsinfo";
        OkGo.post(url).params("login_userid","比爱玉人牛B").params("id",56).execute(new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {
                Log.i("aaa","s==="+s);
                sbf.append(s+"");
            }
        });

        return  sbf.toString()+"";

    }


}
