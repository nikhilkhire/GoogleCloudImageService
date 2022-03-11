package com.image.googlecloudimageservice.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.image.googlecloudimageservice.config.MessagingConfig;

/**
 * This is rabbitmq sender service class which used for connecting to rabbitmq template and send message to queue.
 */
@Service
public class RabbitMQSender{

    @Autowired
    private RabbitTemplate template;

    /**
     * This method send theGCO cloud URL to mq
     * For now it is sending picsum url
     * 
     * @param uploadedFileUrl
     */
    public void send(final String uploadedFileUrl){
        this.template.convertAndSend(MessagingConfig.EXCHANGE, MessagingConfig.ROUTING_KEY, uploadedFileUrl);

        System.out.println("Image Url = " + uploadedFileUrl);

    }

    /**
     * Uploads a file to Cloud Storage and returns the uploaded file's URL.
     * 
     * public String uploadToCloudStorage(String fileName, InputStream fileInputStream) {
     * String projectId = "happy-coding-gcloud");
     * String bucketName = "happy-coding-gcloud.appspot.com";
     * Storage storage =
     * StorageOptions.newBuilder().setProjectId(projectId).build().getService();
     * BlobId blobId = BlobId.of(bucketName, fileName);
     * BlobInfo blobInfo = BlobInfo.newBuilder(blobId).build();
     * Blob blob = storage.create(blobInfo, fileInputStream);
     * return blob.getMediaLink();
     * }
     */
}
