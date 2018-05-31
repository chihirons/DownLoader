package com.example.user.downloader;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class DownLoad extends AppCompatActivity {

    private Button Clear;
    private EditText Texts;
    private Button Starts;
    private ImageView Views;
    private Button Intents;
    private DownLoadAsyncTask task;
    private Intent intent;

    Toast no;
    Toast ok;

    String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_down_load);
        no = Toast.makeText(this,"画像取得に失敗",Toast.LENGTH_LONG);
        ok = Toast.makeText(this,"ダウンロードが完了しました", Toast.LENGTH_LONG);

        //紐づけ
        Texts = findViewById(R.id.texts);
        Starts = findViewById(R.id.starts);
        Views = findViewById(R.id.views);
        Clear = findViewById(R.id.clear);
        Intents = findViewById(R.id.g_intent);

        intent = new Intent(this, Search.class);


        //ダウンロード開始ボタンタップ時
        Starts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                url = Texts.getText().toString();

                if (url.length() != 0) {
                    //EditTextに文字が入っている場合
                    task = new DownLoadAsyncTask();
                    //Lisntenerを設定
                    task.setListener(createListener());
                    task.execute(url);
                }else{
                    no.show();
                }
            }
        });


        //画像とテキストのクリアー
        Clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Texts.setText("");
                Views.setImageDrawable(null);
            }
        });


        //画面遷移に呼び出される
        Intents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent);
                onStop();
            }
        });

    }

    @Override
    protected void onDestroy() {
        task.setListener(null);
        super.onDestroy();
    }

    private DownLoadAsyncTask.Listener createListener(){
        return new DownLoadAsyncTask.Listener() {
            @Override
            public void onSuccess(Bitmap bpm) {
                Views.setImageBitmap(bpm);
                if (bpm == null){
                    no.show();
                }else{
                    ok.show();
                }
                Log.d("BPMの中身", String.valueOf(bpm));
            }
        };
    }
}
