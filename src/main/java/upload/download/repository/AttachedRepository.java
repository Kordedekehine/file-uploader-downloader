package upload.download.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import upload.download.entity.AttachedFile;

@Repository
public interface AttachedRepository extends JpaRepository< AttachedFile,String> {

}
