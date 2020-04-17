package com.project.battirtourguideapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.StorageReference;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;


public class MainActivity extends AppCompatActivity
        implements BottomNavigationView.OnNavigationItemSelectedListener {
    // Folder path for Firebase Storage.
    String Storage_Path = "/";

    // Root Database Name for Firebase Database.
    static String Database_Path = "TourGuide_Database";
    // Creating URI.
    Uri FilePathUri;

    Uri downUri;
    String imageUrl;
    // Creating EditText.
    EditText ImageName ;

    // Creating StorageReference and DatabaseReference object.
    StorageReference storageReference;
    DatabaseReference databaseReference;

    // Image request code for onActivityResult() .
    int Image_Request_Code = 7;

    ProgressDialog progressDialog ;




    private BottomNavigationView bottomNavigationView;

    // hay ll slideshow
    private int[] mImages = new int[]{
            R.drawable.img1,
            R.drawable.img2,
            R.drawable.img3,
            R.drawable.img4,
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_main);

        // Assign FirebaseStorage instance to storageReference.
        //storageReference = FirebaseStorage.getInstance().getReference();

        // Assign FirebaseDatabase instance with root database name.
        //databaseReference = FirebaseDatabase.getInstance().getReference(Database_Path);

// splash


        //startActivity (new Intent (MainActivity.this, splash.class));



            // button OnClich
            Button arch = findViewById (R.id.arch);
            Button torist = findViewById (R.id.tourist);
            Button hotel = findViewById (R.id.hotel1);
            Button rest = findViewById (R.id.rest1);

            arch.setOnClickListener (new View.OnClickListener () {
                @Override
                public void onClick(View v) {
                    Intent int1 = new Intent (MainActivity.this, arch.class);
                    startActivity (int1);
                }
            });

            hotel.setOnClickListener (new View.OnClickListener () {
                @Override
                public void onClick(View v) {
                    Intent int2 = new Intent (MainActivity.this, hotel.class);
                    startActivity (int2);
                }
            });


            rest.setOnClickListener (new View.OnClickListener () {
                @Override
                public void onClick(View v) {
                    Intent int3 = new Intent (MainActivity.this, rest.class);
                    startActivity (int3);
                }
            });

            torist.setOnClickListener (new View.OnClickListener () {
                @Override
                public void onClick(View v) {
                    Intent int4 = new Intent (MainActivity.this, tourist.class);
                    startActivity (int4);
                }
            });
            //had ll slideshow

            CarouselView carouselView = findViewById (R.id.carouselView);
            carouselView.setPageCount (mImages.length);
            carouselView.setImageListener (new ImageListener () {
                @Override
                public void setImageForPosition(int position, ImageView imageView) {
                    imageView.setImageResource (mImages[position]);

                }
            });
            // carouselView.setImageClickListener (new ImageClickListener () {
            // @Override
            // public void onClick(int position) {
            // Toast.makeText (MainActivity.this, mImagesTitle[position], Toast.LENGTH_SHORT).show ();

            //ll navigation


            bottomNavigationView = findViewById (R.id.nav_view);
            setListeners ();

        }

        private void setListeners() {
            bottomNavigationView.setOnNavigationItemSelectedListener (this);
        }

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {


            //  If item is Checked make it unchecked
            if (item.isChecked ())
                item.setChecked (false);
            switch (item.getItemId ()) {
                case R.id.navigation_home:
                    // Check the Item

                    item.setChecked (true);
                    break;
                case R.id.navigation_qr:
                    startActivity (new Intent (getApplicationContext (),
                            generateQrcode.class));

                    item.setChecked (true);
                    break;
                case R.id.navigation_map:
                    startActivity (new Intent (getApplicationContext (),
                            map.class));
                    overridePendingTransition (0, 0);
                    return true;

            }
            return false;
        }
      /// about
      @Override
     public boolean onCreateOptionsMenu(Menu menu) {
      getMenuInflater().inflate(R.menu.main, menu);
       return true;
}

        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            // Handle presses on the action bar items
            switch (item.getItemId()) {
                case R.id.about:
                    startActivity (new Intent (getApplicationContext (),
                            About.class));
                default:
                    return super.onOptionsItemSelected(item);
            }
        }


        /*
        // list
        @Override
        protected void onActivityResult(int requestCode, int resultCode, Intent data) {

            super.onActivityResult(requestCode, resultCode, data);

            if (requestCode == Image_Request_Code && resultCode == RESULT_OK && data != null && data.getData() != null) {

                FilePathUri = data.getData();

                try {

                    // Getting selected image into Bitmap.
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), FilePathUri);


                }
                catch (IOException e) {

                    e.printStackTrace();
                }
            }
        }

    // Creating Method to get the selected image file Extension from File Path URI.
    public String GetFileExtension(Uri uri) {

        ContentResolver contentResolver = getContentResolver();

        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();

        // Returning the file Extension.
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri)) ;
    }

    // Creating UploadImageFileToFirebaseStorage method to upload image on storage.
    public void UploadImageFileToFirebaseStorage() {

        // Checking whether FilePathUri Is empty or not.
        if (FilePathUri != null) {

            // Setting progressDialog Title.
            progressDialog.setTitle("Image is Uploading...");

            // Showing progressDialog.
            progressDialog.show();

            // Creating second StorageReference.
            final StorageReference storageReference2 =
                    storageReference.child(Storage_Path +
                            System.currentTimeMillis() + "." +
                            GetFileExtension(FilePathUri));

            // Adding CompleteListener to second StorageReference.
            storageReference2.putFile(FilePathUri).continueWithTask(
                    new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                        @Override
                        public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                            if (!task.isSuccessful()){
                                throw task.getException();
                            }

                            // Setting progressDialog Title.
                            progressDialog.setTitle("Image is Uploading...");

                            return storageReference2.getDownloadUrl();
                        }
                    }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                @Override
                public void onComplete(@NonNull Task<Uri> task) {
                    if (task.isSuccessful()){

                        // Getting image download Url
                        downUri = task.getResult();
                    }
                }
            }).addOnSuccessListener(new OnSuccessListener<Uri>() {
                @Override
                public void onSuccess(Uri uri) {
                    // Getting image name from EditText and store into string variable.
                    String TempImageName = ImageName.getText().toString().trim();

                    // Hiding the progressDialog after done uploading.
                    progressDialog.dismiss();

                    // Showing toast message after done uploading.
                    Toast.makeText(getApplicationContext(),
                            "Image Uploaded Successfully ",
                            Toast.LENGTH_LONG).show();

                    @SuppressWarnings("VisibleForTests")
                    ImageUploadInfo imageUploadInfo = new ImageUploadInfo(
                            TempImageName, downUri.toString());

                    // Getting image upload ID.
                    String ImageUploadId = databaseReference.push().getKey();

                    // Adding image upload id s child element into databaseReference.
                    databaseReference.child(ImageUploadId).setValue(imageUploadInfo);
                }
            })
                    // If something goes wrong
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception exception) {
                            // Hiding the progressDialog.
                            progressDialog.dismiss();

                            // Showing exception error message.
                            Toast.makeText(MainActivity.this,
                                    exception.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    });
        }
        else {
            Toast.makeText(MainActivity.this,
                    "Please Select Image or Add Image Name", Toast.LENGTH_LONG).show();
        }
    }*/

        }







