package com.project.battirtourguideapp;

public class ImageUploadInfo{
        public String imageName;
        public String imageDesc;
        public String imageURL1;
        public String imageURL2;
        public String imageURL3;

        public ImageUploadInfo() {

        }

        public ImageUploadInfo(String desc, String name,
                               String url1, String url2, String url3) {

            this.imageDesc = name;
            this.imageName = name;
            this.imageURL1= url1;
            this.imageURL2= url2;
            this.imageURL3= url3;
        }

        public String getImageDesc() {
        return imageDesc;
    }
        public String getImageName() {
            return imageName;
        }
        public String getImageURL1() {
            return imageURL1;
        }
        public String getImageURL2() {
        return imageURL2;
    }
        public String getImageURL3() {
        return imageURL3;
    }

    }




