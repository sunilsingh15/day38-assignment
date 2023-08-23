package sg.edu.nus.iss.server.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import sg.edu.nus.iss.server.repository.FileUploadRepository;

@RestController
@RequestMapping("/api")
@CrossOrigin()
public class FileUploadController {

    @Autowired
    private FileUploadRepository repository;

    @PostMapping(path = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> uploadSong(@RequestPart MultipartFile song) {

        try {
            String id = repository.uploadSong(song);
            JsonObject successJson = Json.createObjectBuilder()
                    .add("success", id)
                    .build();
            return ResponseEntity.status(200).body(successJson.toString());
        } catch (IOException e) {
            e.printStackTrace();
            JsonObject errorJson = Json.createObjectBuilder()
                    .add("error", e.getMessage())
                    .build();
            return ResponseEntity.status(500).body(errorJson.toString());
        }

    }

    @GetMapping(path = "/songs/{id}")
    public ResponseEntity<byte[]> retrieveSong(@PathVariable String id) throws IOException {

        S3Object obj = repository.getSong(id);
        ObjectMetadata metadata = obj.getObjectMetadata();
        S3ObjectInputStream is = obj.getObjectContent();

        return ResponseEntity.status(200)
                .contentType(MediaType.parseMediaType(metadata.getContentType()))
                .body(is.readAllBytes());
    }

}
