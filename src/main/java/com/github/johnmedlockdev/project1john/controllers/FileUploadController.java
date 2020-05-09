package com.github.johnmedlockdev.project1john.controllers;

import com.sun.xml.internal.bind.v2.TODO;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
public class FileUploadController {
    @RequestMapping(value = "/upload",
            method = RequestMethod.POST,
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE)

    public RedirectView fileUpload(@RequestParam("file") MultipartFile file) throws IOException {
        File convertFile = new File("C:\\Storage\\" + file.getOriginalFilename());

        try (FileOutputStream fout = new FileOutputStream(convertFile)) {
            fout.write(file.getBytes());
        } catch (Exception exe) {
            exe.printStackTrace();
        }
        return new RedirectView("uploadForm"); // should go to d3 graph
    }

    @GetMapping("/files")
    public List<String> displayFiles() throws IOException {
        Path path = Paths.get("C:\\Storage\\");

        Stream<Path> subPaths = Files.walk(path, 1);
		List<String> subPathList = subPaths.filter(Files::isRegularFile)
				.map(Objects::toString).collect(Collectors.toList());


		//		System.out.println(subPathList);
//            subPaths.filter(Files::isRegularFile).forEach(System.out::println);

        return subPathList;
    }

}

// // TODO: 5/9/2020 add a buffer to multipart
// // TODO: 5/9/2020 route user based off success or failure of upload.
//// TODO: 5/9/2020 add proper logging
