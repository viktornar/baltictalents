package lt.baltictalents.lesson5;

import lombok.val;
import lt.baltictalents.lesson5.helper.JDBCHelper;
import lt.baltictalents.lesson5.model.UploadedFile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class JDBCTemplateApplication {
    static public void main(String ...args) {
        val jdbcHelper = new JDBCHelper(
                "root",
                "",
                "mysql",
                "localhost",
                "33060",
                "lesson5"
        );

        val dataSource = jdbcHelper.getMysqlDataSource();

        val jdbcTemplate = new JdbcTemplate(dataSource);

        final String CREATE_UPLOAD_FILE_TABLE =
                "CREATE TABLE IF NOT EXISTS UPLOAD_FILE (" +
                        "ID INT NOT NULL PRIMARY KEY AUTO_INCREMENT, " +
                        "FILENAME TINYTEXT NOT NULL, " +
                        "CONTENT_TYPE VARCHAR(50) NOT NULL DEFAULT 'IMAGE/JPG', " +
                        "DOWNLOAD_URL TEXT NOT NULL, " +
                        "DESCRIPTION TEXT" +
                        ")";

        jdbcTemplate.execute(CREATE_UPLOAD_FILE_TABLE);

//        final String INSERT_UPLOAD_FILE_TABLE =
//                "INSERT INTO UPLOAD_FILE " +
//                        "(FILENAME, DOWNLOAD_URL, DESCRIPTION) " +
//                        "VALUES ('test3', 'some/url', 'some description')";
//
//        val insertionCount = jdbcTemplate.update(INSERT_UPLOAD_FILE_TABLE);
//
//        System.out.println(insertionCount);

        val SELECT_UPLOADED_FILES = "SELECT FILENAME, CONTENT_TYPE, DOWNLOAD_URL FROM UPLOAD_FILE";

        val uploadedFiles = jdbcTemplate.query(
            SELECT_UPLOADED_FILES,
            new RowMapper<UploadedFile>() {
                public UploadedFile mapRow(ResultSet rs, int rowNum) throws SQLException {
                    UploadedFile uploadedFile = new UploadedFile();
                    uploadedFile.setFileName(rs.getString("FILENAME"));
                    uploadedFile.setContentType(rs.getString("CONTENT_TYPE"));
                    uploadedFile.setDownloadUrl(rs.getString("DOWNLOAD_URL"));
                    return uploadedFile;
                }
            }
        );

        uploadedFiles.forEach(
            uploadedFile -> System.out.println(uploadedFile.fileName)
        );
    }
}
