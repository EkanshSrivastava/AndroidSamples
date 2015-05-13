package com.example.lp_eng_ggn_068.testapp;

/**
 * Created by lp-eng-ggn-068 on 12/05/15.
 */
public class Record {

    // private variables
    int _id;
    String _imageDetails;
    String _date;
    byte[] _image;

    // Empty constructor
    public Record() {

    }

    // constructor
    public Record(int keyId, String imageDetails, byte[] image, String date) {
        this._id = keyId;
        this._imageDetails = imageDetails;
        this._image = image;
        this._date = date;

    }

    // constructor
    public Record(String imageDetails, byte[] image, String date) {
        this._imageDetails = imageDetails;
        this._image = image;
        this._date = date;
    }

    // getting ID
    public int getID() {
        return this._id;
    }

    // setting id
    public void setID(int keyId) {
        this._id = keyId;
    }

    // getting date
    public String getDate() {
        return this._date;
    }

    // setting date
    public void setDate(String date) {
        this._date = date;
    }

    // getting imagedetails
    public String getImageDetails() {
        return this._imageDetails;
    }

    // setting imagedetails
    public void setImageDetails(String imageDetails) {
        this._imageDetails = imageDetails;
    }

    // getting image
    public byte[] getImage() {
        return this._image;
    }

    // setting image
    public void setImage(byte[] image) {
        this._image = image;
    }
}