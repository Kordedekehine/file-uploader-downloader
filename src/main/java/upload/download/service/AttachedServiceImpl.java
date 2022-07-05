package upload.download.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import upload.download.entity.AttachedFile;
import upload.download.exception.*;
import upload.download.repository.AttachedRepository;

@Service
public class AttachedServiceImpl implements AttachedService{

    @Autowired
  private AttachedRepository attachedRepository;


    @Override
    public AttachedFile saveAttachedFiles(MultipartFile file) throws InvalidPathException, CannotSaveFileException, FullStorageException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        try {
            if (fileName.contains("..")) {
                throw new InvalidPathException("Filename contains invalid path sequence "
                        + fileName);
            }
            if (fileName.isBlank() || fileName.isEmpty()) {
                throw new EmptyFileException("Filename does not exist "
                        + fileName);
            }
            AttachedFile attachedFile
                    = new AttachedFile(fileName, file.getContentType(), file.getBytes());
            return attachedRepository.save(attachedFile);

        } catch (Exception e) {
            throw new CannotSaveFileException("Could not save File: " + fileName); // TODO: 7/4/2022 use the right exceptions
        }
    }

    @Override
    public AttachedFile getAttachedFiles(String fileId) throws EmptyFileException, CorruptFileException, InvalidPathException {
        return attachedRepository
                .findById(fileId)
                .orElseThrow(
                        () -> new EmptyFileException ("File not found with Id: " + fileId));
    }
}
