package com.iridium.gridviewdisplay;

import android.net.Uri;

public class Image
{
    private Uri imageUrl;

    public Image()
    {

    }

    public Uri getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(Uri imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Image(Uri imageUrl)
    {
        this.imageUrl = imageUrl;

    }

}
