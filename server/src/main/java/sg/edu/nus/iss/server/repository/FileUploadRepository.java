package sg.edu.nus.iss.server.repository;

import java.io.IOException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;

@Repository
public class FileUploadRepository {

    @Autowired
    private AmazonS3 s3;

    public String uploadSong(MultipartFile song) throws IOException {

        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentType(song.getContentType());
        metadata.setContentLength(song.getSize());

        String songID = UUID.randomUUID().toString().substring(0, 8);

        PutObjectRequest putReq = new PutObjectRequest("songsong", "songs/%s".formatted(songID),
                song.getInputStream(), metadata);

        putReq = putReq.withCannedAcl(CannedAccessControlList.PublicRead);

        s3.putObject(putReq);

        return songID;
    }

    public S3Object getSong(String id) {
        return s3.getObject("songsong", "songs/%s".formatted(id));
    }



}
