package pro.aidar.alatoonews.model.service.files;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.net.MalformedURLException;

public interface FileService {

    String save(MultipartFile file);

    Resource get(String filename);

    boolean delete(String filename) throws MalformedURLException;

}
