package com.image.googlecloudimageservice.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.image.googlecloudimageservice.service.RabbitMQSender;

/**
 * Rest Controller to store image in google cloud bucket
 */
@RestController
public class ImageController{

    @Autowired
    RabbitMQSender rabbitMQSender;

    @PostMapping("/store")
    String storeImageInCloudBucket(){

        /*
         * This code is for uploading the image to cloud bucket
         * Part filePart = request.getPart("image");
         * String fileName = filePart.getSubmittedFileName();
         * InputStream fileInputStream = filePart.getInputStream();
         * // Upload the file and get its URL
         * String uploadedFileUrl = this.rabbitMQSender.uploadToCloudStorage(fileName, fileInputStream);
         */

        // Picsum helps to upload random image on cloud
        // For now we are assuming that we stored the image in cloud and added cloud url in rabbitmq
        final String uploadedFileUrl = "https://picsum.photos/500/300?random=" + Math.random();
        this.rabbitMQSender.send(uploadedFileUrl);

        return "Image uploaded successfully";
    }
}
