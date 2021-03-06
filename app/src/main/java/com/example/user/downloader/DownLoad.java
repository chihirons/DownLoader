package com.example.user.downloader;

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

    Button Clear;
    Button Saved;
    EditText Texts;
    Button Starts;
    ImageView Views;
    private DownLoadAsyncTask task;

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
        Saved = findViewById(R.id.save);
        Clear = findViewById(R.id.clear);


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
                    Saved.setVisibility(View.INVISIBLE);

                }
            }
        });


        //画像とテキストのクリアー
        Clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Texts.setText("");
                Views.setImageDrawable(null);
                Saved.setVisibility(View.INVISIBLE);
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
                    Saved.setVisibility(View.INVISIBLE);
                    no.show();
                }else{
                    Saved.setVisibility(View.VISIBLE);
                    ok.show();
                }
                Log.d("BPMの中身", String.valueOf(bpm));
            }
        };
    }
}
