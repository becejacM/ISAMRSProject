package rs.team15.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class UploadPhotoController {

	static String sp;
    static String imagesPath;

    static {
        sp = File.separator;
        imagesPath = "pictures" + sp;
    }
    
    

    @RequestMapping(
            value = "/upload",
            method = RequestMethod.GET)
    public
    @ResponseBody
    String provideUploadInfo() {
        return "You can upload a file by posting to this same URL.";
    }

    @RequestMapping(value = "api/upload/{item_type}/{unique_item_id}", method = RequestMethod.POST)
    public
    @ResponseBody
    String handleFileUpload(@RequestParam("file") MultipartFile file, @PathVariable("item_type") String type, @PathVariable("unique_item_id") String id) {
        String[] parts = file.getOriginalFilename().split("\\.");
        String ext = parts[parts.length-1];
        if (!file.isEmpty()) {
            String name = imagesPath + type + "_" + id + "." + ext;
            try {
                byte[] bytes = file.getBytes();
                BufferedOutputStream stream =
                        new BufferedOutputStream(new FileOutputStream(new File(name)));
                stream.write(bytes);
                stream.close();
                return "You successfully uploaded image!";
            } catch (Exception e) {
                return "You failed to upload image => " + e.getMessage();
            }
        } else {
            return "You failed to upload image because the file was empty.";
        }
    }
}
