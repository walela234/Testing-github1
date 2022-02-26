package com.kyu.therehub;

public class UploadPdfModel {
    public String name;
    public String url;

    // Default constructor required for calls to
    // DataSnapshot.getValue(User.class)
    public UploadPdfModel() {
    }

    public UploadPdfModel(String name, String url) {
        this.name = name;
        this.url = url;
    }

    public String getFilename() {
        return name;
    }

    public String getFileUrl() {
        return url;
    }
}
