package upload.download.service;

import org.springframework.web.multipart.MultipartFile;
import upload.download.entity.AttachedFile;
import upload.download.exception.*;

public interface AttachedService {

    AttachedFile saveAttachedFiles(MultipartFile file) throws CannotSaveFileException, FullStorageException,InvalidPathException;

    AttachedFile getAttachedFiles(String fileId) throws CorruptFileException, InvalidPathException, EmptyFileException;
}
