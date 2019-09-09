package com.iridium.gridviewdisplay;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "GRID_VIEW_DISPLAY";
    private RecyclerView recyclerView;
    private final static int REQUEST_CODE_BROWSE_PICTURE = 1;
    private List<Uri> userSelectedImageUriList = null;
    private List <Image> images = new ArrayList<>();
    private Uri uri;
    private Button button;

    // Currently displayed user selected image index in userSelectedImageUriList.
    private int currentDisplayedUserSelectImageIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recycler_view);
        button = findViewById(R.id.capture);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this,3));

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    openPictureGallery();
                }
            });


    }



    /* Invoke android os system file browser to select images. */
    private void openPictureGallery()
    {
        // Create an intent.
        Intent openAlbumIntent = new Intent();

        // Only show images in the content chooser.
        // If you want to select all type data then openAlbumIntent.setType("*/*");
        // Must set type for the intent, otherwise there will throw android.content.ActivityNotFoundException: No Activity found to handle Intent { act=android.intent.action.GET_CONTENT }
        openAlbumIntent.setType("image/*");

        // Set action, this action will invoke android os browse content app.
        openAlbumIntent.setAction(Intent.ACTION_GET_CONTENT);

        openAlbumIntent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);

        // Start the activity.
        startActivityForResult(openAlbumIntent, REQUEST_CODE_BROWSE_PICTURE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        int countClipData = 0;
        currentDisplayedUserSelectImageIndex = 0;
        int imageListIndex = 0;

        //
        switch (requestCode)
        {

            case REQUEST_CODE_BROWSE_PICTURE:
                Log.d(TAG, "CLIPDATA NOT EMPTY");

                 if (data.getData() != null)
                 {
                     uri = data.getData();
                     images.add(new Image(uri));
                 }
                 else if(data.getClipData() != null)
                 {
                     countClipData = data.getClipData().getItemCount();

                     while (imageListIndex < countClipData)
                     {
                         uri = data.getClipData().getItemAt(imageListIndex).getUri();
                         images.add(new Image(uri));
                         imageListIndex++;

                     }

                 }

                break;
        }


        Adapter adapter = new Adapter(images);
        recyclerView.setAdapter(adapter);

    }

}
