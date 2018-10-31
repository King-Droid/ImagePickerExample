# ImagePickerExample
Library project to pick image/document/File in Android apps in easy way.

Here are basic features
=======================
- Pick image from Camera
- Pick image from Gallery
- Pick a document (.doc, .docx and .pdf)
- Pick a file from Gallery

Crop & Compress
===============
Option to crop and compress picked image


Sample code
===========

             /*
            Pick Image from Camera or Gallery
            Note : Run Time Permissions already handled in library. No need to write before this code.
             */
           ImagePicker imagePicker = new ImagePicker(MainActivity.this);
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
    
    
    
