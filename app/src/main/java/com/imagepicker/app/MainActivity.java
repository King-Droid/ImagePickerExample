package com.imagepicker.app;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.app.helper.ImagePicker;
import com.imagepicker.app.databinding.ActivityMainBinding;

import java.io.File;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    ActivityMainBinding binding;
    ImagePicker imagePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        binding.btnPickImage.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == binding.btnPickImage) {
            /*
            Pick Image from Camera or Gallery
            Note : Run Time Permissions already handled in library. No need to write before this code.
             */
            imagePicker = new ImagePicker(MainActivity.this);
            imagePicker.setMode(ImagePicker.MODE_PIC)
                    .setCompress(false)
                    .setTag("pic")
                    .setImagePickerListener(new ImagePicker.ImagePickerListener() {
                        @Override
                        public void onImagePicked(File imageFile, String tag) {
                            /*
                            User imageFile for displaying in imageView or upload to server
                             */
                            if (imageFile != null) {
                                binding.imgPicture.setImageBitmap(BitmapFactory.decodeFile(imageFile.getAbsolutePath()));
                            }
                        }
                    })
                    .pick();
        }
    }

    /*
    Handle onActivityResult of ImagePicker
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        imagePicker.onActivityResult(requestCode, resultCode, data);
    }

    /*
    Handle onRequestPermissionsResult of ImagePicker
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        imagePicker.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
