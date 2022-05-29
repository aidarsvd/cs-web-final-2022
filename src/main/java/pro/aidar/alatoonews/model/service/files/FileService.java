package pro.aidar.alatoonews.model.service.files;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;


public interface FileService {

    String save(MultipartFile file);

    Resource get(String filename);

    void delete(String filename);

}
