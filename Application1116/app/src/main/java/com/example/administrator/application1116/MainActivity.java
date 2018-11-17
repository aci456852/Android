package com.example.administrator.application1116;

import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView lv;
    private List<image> images=new ArrayList<image>();;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        requestPower();
        lv = (ListView) findViewById(R.id.lv);
        getimages();
        lv.setAdapter(new MyAdapter());
    }
    //获取权限
    protected void requestPower(){
        if(ContextCompat.checkSelfPermission(this,android.Manifest.permission.MOUNT_UNMOUNT_FILESYSTEMS)!=
                PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,new String[]{android.Manifest.permission.MOUNT_UNMOUNT_FILESYSTEMS,
                    android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    android.Manifest.permission.READ_EXTERNAL_STORAGE},1);
        }
    }
    //读取图片
    private void getimages() {
        Uri mImageUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        ContentResolver mContentResolver = MainActivity.this.getContentResolver();
        Cursor cursor = mContentResolver.query(mImageUri, null,
                MediaStore.Images.Media.MIME_TYPE + "=? or "
                        + MediaStore.Images.Media.MIME_TYPE + "=?",
                new String[]{"image/jpeg", "image/png"}, MediaStore.Images.Media.DATE_MODIFIED);

        if (cursor == null) {
            Toast.makeText(MainActivity.this, "没有数据！！", Toast.LENGTH_SHORT).show();
            return;
        }
        while (cursor.moveToNext()){
            String name=cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DISPLAY_NAME));
            String size=cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.SIZE));
            String path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            Bitmap data = BitmapFactory.decodeFile(path);
            image im=new image(name,size,data);
            images.add(im);
        }
        cursor.close();
    }
    //适配器
    private class MyAdapter extends BaseAdapter {
        private static final String TAG = "MyAdapter";

        @Override
        public int getCount() {
            return images.size();
        }

        @Override
        public Object getItem(int position) {
            return images.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            image im = images.get(position);
            View view = View.inflate(MainActivity.this, R.layout.item, null);
            TextView name = (TextView) view.findViewById(R.id.name);
            TextView size = (TextView) view.findViewById(R.id.size);
            ImageView imageView = (ImageView) view.findViewById(R.id.imageView);
            name.setText("照片名称：" + im.getName());
            size.setText("照片大小：" + im.getSize());
            imageView.setImageBitmap(im.getData());
            return view;
        }
    }
}
