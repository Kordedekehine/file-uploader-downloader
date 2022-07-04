package upload.download.service;

import org.springframework.web.multipart.MultipartFile;
import upload.download.entity.AttachedFile;

public interface AttachedService {

    AttachedFile saveAttachedFiles(MultipartFile file) throws Exception;

    AttachedFile getAttachedFiles(String fileId) throws Exception;
}
