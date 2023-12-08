package com.free.fs.blockchain.Service.impl;

import com.free.fs.blockchain.Service.FileService;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class FileServiceimpl implements FileService {

    @Override
    public byte[] fileToHash(File file) throws IOException {
        try {
            MessageDigest sha256Digest = MessageDigest.getInstance("SHA-256");
            FileInputStream fis = new FileInputStream(file);
            byte[] buffer = new byte[1024 * 1024 * 4];
            int bytesRead;
            while ((bytesRead = fis.read(buffer)) != -1) {
                sha256Digest.update(buffer, 0, bytesRead);
            }
            fis.close();
            byte[] contentHashBytes = sha256Digest.digest();
            byte[] nameBytes = file.getName().getBytes(StandardCharsets.UTF_8);
            MessageDigest finalDigest = MessageDigest.getInstance("SHA-256");
            finalDigest.update(contentHashBytes);
            finalDigest.update(nameBytes);
            byte[] finalHashBytes = finalDigest.digest();
            return finalHashBytes;
        } catch (NoSuchAlgorithmException | IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}
