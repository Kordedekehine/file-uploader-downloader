package upload.download.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import upload.download.entity.AttachedFile;
import upload.download.repository.AttachedRepository;

@Service
public class AttachedServiceImpl implements AttachedService{

    @Autowired
  private AttachedRepository attachedRepository;


    @Override
    public AttachedFile saveAttachedFiles(MultipartFile file) throws Exception {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        try {
            if (fileName.contains("..")) {
                throw new Exception("Filename contains invalid path sequence "
                        + fileName);
            }
            AttachedFile attachedFile
                    = new AttachedFile(fileName, file.getContentType(), file.getBytes());
            return attachedRepository.save(attachedFile);

        } catch (Exception e) {
            throw new Exception("Could not save File: " + fileName); // TODO: 7/4/2022 use the right exceptions
        }
    }

    @Override
    public AttachedFile getAttachedFiles(String fileId) throws Exception {
        return attachedRepository
                .findById(fileId)
                .orElseThrow(
                        () -> new Exception("File not found with Id: " + fileId));
    }
}
