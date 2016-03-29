package com.slashandhyphen.tasterly;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.MediaStore;
import android.widget.Toast;

import java.io.File;

/**
 * Created by ookamijin on 3/28/2016.
 *
 * This class encapsulates the interaction with the Android camera service.
 */
public class Camera {
    Activity activity;
    String photoPath;

    Camera(Activity activity) {
        this.activity = activity;
    }

    private File createImageFile() {
        // build name. located in bin folder on sd, i believe
        String imageFileName = "Picture_" + System.currentTimeMillis()
                + "_";
        File image = new File(activity.getExternalFilesDir(null), imageFileName);

        photoPath = image.getAbsolutePath();
        return image;
    }

    void takePicture() {
        if (!activity.getPackageManager()
                .hasSystemFeature(PackageManager.FEATURE_CAMERA)) {
            Toast.makeText(activity, "No camera on this device", Toast.LENGTH_LONG)
                    .show();
        } else {
            // call camera application
            Intent takePictureIntent = new Intent(
                    MediaStore.ACTION_IMAGE_CAPTURE);
            File f = createImageFile();
            takePictureIntent
                    .putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(f));
            int cameraReturn = 0;
            activity.startActivityForResult(takePictureIntent, cameraReturn);

        }
    }
}
