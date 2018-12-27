package lt.baltictalents.lesson5.model;

import lombok.Data;

import java.sql.Date;

@Data
public class UploadedFile {
    public String fileName;
    public String contentType;
    public String downloadUrl;
    public Date uploadedAt;
    public String description;
}
