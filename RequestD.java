if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_CONTACTS)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_CONTACTS},
                    102);
        } else {
            Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
            Cursor cursor = getContentResolver().query(uri,null,null,null,null);
            while (cursor.moveToNext()){
                String name = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                String number = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                Log.d(TAG,name + " : " + number );
            }
            cursor.close();
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode){
            case 102:

                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
                    Cursor cursor = getContentResolver().query(uri,null,null,null,null);
                    while (cursor.moveToNext()){
                        String name = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                        String number = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                        int numberSecond = cursor.getInt(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                        Log.d(TAG,name + " : " + number + "  " + numberSecond);
                    }
                    cursor.close();
                }
                break;
        }

    }
