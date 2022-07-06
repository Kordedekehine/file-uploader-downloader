package upload.download.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import upload.download.ResponseData;
import upload.download.entity.AttachedFile;
import upload.download.exception.*;
import upload.download.service.AttachedService;

@RestController
@RequestMapping("/api/file")
public class AttachedController {
    @Autowired
    private AttachedService attachedService;

    @PostMapping("/upload")
   public ResponseData upload(@RequestParam ("file")MultipartFile file) throws CannotSaveFileException, FullStorageException,InvalidPathException{
       AttachedFile attachedFile = null;
       String downloadURL = " ";
       attachedFile = attachedService.saveAttachedFiles(file);
       downloadURL = ServletUriComponentsBuilder.fromCurrentContextPath().path("/download/")
               .path(attachedFile.getId()).toUriString();

       return new ResponseData(attachedFile.getFileName(),
               downloadURL,
               file.getContentType(),
               file.getSize()); //todo why must i upload to download?
   }

   @GetMapping("/download/{fileId}")
    public ResponseEntity<Resource> download(@PathVariable ("fileId") String fileId) throws EmptyFileException, CorruptFileException, InvalidPathException{
        AttachedFile attachedFile = null;
        attachedFile = attachedService.getAttachedFiles(fileId);
        return  ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(attachedFile.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attached file; filename=\"" + attachedFile.getFileName()
                                + "\"")
                .body(new ByteArrayResource(attachedFile.getData()));
    }

    @GetMapping("/search/{fileId}")
    public ResponseEntity<?> search(@PathVariable ("fileId") String fileId) throws EmptyFileException, CorruptFileException, InvalidPathException{
        try {
            AttachedFile attachedFile = null;
            //attachedFile = attachedService.getAttachedFiles(fileId);
            return new ResponseEntity<>(attachedService.findFileById(fileId), HttpStatus.OK);
        }
        catch (EmptyFileException exception){
            return new ResponseEntity<>(exception.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
}
