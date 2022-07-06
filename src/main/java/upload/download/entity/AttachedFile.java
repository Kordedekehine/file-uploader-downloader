package upload.download.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;

@Entity
@Data
@NoArgsConstructor
public class AttachedFile {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid",strategy = "uuid2")
    private String id;

    private String fileName;
    private String fileType; //is it video, audio or just file

    /**
     * Lob means large object
     * CLOB – Character Large Object will store large text data
     * BLOB – Binary Large Object is for storing binary data like image, audio, or video
     * The @Lob annotation specifies that the database should store the property as Large Object
     */
;
    @Lob //we need lob for easy persistence..
    private byte[] data; //based on the fact that we will be storing the entire byte in the database

    public AttachedFile(String fileName, String fileType, byte[] data) {
        this.fileName = fileName;
        this.fileType = fileType;
        this.data = data;
    }
}
