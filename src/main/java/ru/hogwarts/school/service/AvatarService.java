package ru.hogwarts.school.service;

import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.hogwarts.school.model.Avatar;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.AvatarRepository;
import ru.hogwarts.school.repository.StudentRepository;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static java.nio.file.StandardOpenOption.CREATE_NEW;

@Service
@Transactional
public class AvatarService {
    private final AvatarRepository avatarRepository;
    private final StudentRepository studentRepository;
    private final int BUFFER_SIZE = 1024;

    @Value("${path.to.avatars.folder}")
    private String avatarsDir;
    private static final Logger logger = LoggerFactory.getLogger(StudentService.class);

    public AvatarService(AvatarRepository avatarRepository, StudentService studentService, StudentRepository studentRepository) {
        this.avatarRepository = avatarRepository;
        this.studentRepository = studentRepository;
    }

    public void uploadAvatar(Long studentId, MultipartFile avatarFile) throws IOException {
        logger.info("uploadAvatar method was invoked");

        Student student = studentRepository
                .findById(studentId)
                .orElseThrow();

        Path path = saveToDisk(student, avatarFile);
        saveToDb(student, avatarFile, path);
    }

    public Avatar findAvatar(Long id){
        logger.info("findAvatar method was invoked");

        return avatarRepository.findById(id).orElseThrow();
    }
    private String getExtensions(String fileName) {
        logger.info("getExtensions method was invoked");

        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }

    private Avatar findAvatarByStudentId(Long studentId) {
        logger.info("findAvatarByStudentId method was invoked");

        return avatarRepository.findByStudent_id(studentId)
                .orElse(new Avatar());
    }
    private Path saveToDisk(Student student, MultipartFile avatarFile) throws IOException {
        logger.info("saveToDisk method was invoked");

        Path filePath = Path.of(avatarsDir, "student" + student.getId() + "avatar" + getExtensions(avatarFile.getOriginalFilename()));
        Files.createDirectories(filePath.getParent());
        Files.deleteIfExists(filePath);

        try (
                InputStream is = avatarFile.getInputStream();
                OutputStream os = Files.newOutputStream(filePath, CREATE_NEW);
                BufferedInputStream bis = new BufferedInputStream(is, BUFFER_SIZE);
                BufferedOutputStream bos = new BufferedOutputStream(os, BUFFER_SIZE);
        ) {
            bis.transferTo(bos);
        }
        return filePath;
    }

    private void saveToDb(Student student, MultipartFile avatarFile, Path filePath) throws IOException {
        logger.info("saveToDb method was invoked");

        Avatar avatar = findAvatarByStudentId(student.getId());
        avatar.setStudent(student);
        avatar.setFilePath(filePath.toString());
        avatar.setFileSize(avatarFile.getSize());
        avatar.setMediaType(avatarFile.getContentType());
        avatar.setData(avatarFile.getBytes());
        avatarRepository.save(avatar);
    }

    public List<Avatar> getAllAvatar(int pageNumber, int pageSize) {
        logger.info("getAllAvatar method was invoked");

        PageRequest pageRequest = PageRequest.of(pageNumber - 1, pageSize);
        return avatarRepository.findAll(pageRequest).getContent();
    }
}
