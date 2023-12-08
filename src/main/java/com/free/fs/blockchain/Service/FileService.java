package com.free.fs.blockchain.Service;

import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
@Service
public interface FileService {

    byte[] fileToHash(File file) throws NoSuchAlgorithmException, IOException;
}
