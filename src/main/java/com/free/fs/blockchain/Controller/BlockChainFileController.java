package com.free.fs.blockchain.Controller;

import com.free.fs.blockchain.Service.FileService;
import com.free.fs.blockchain.Service.impl.FileServiceimpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/file")
public class BlockChainFileController {

    private FileService fileService = new FileServiceimpl();

    @PostMapping("/fileDeal")
    @ResponseBody
    public byte[] fileToByte(File file) throws NoSuchAlgorithmException, IOException {
        return fileService.fileToHash(file);
    }

    @PostMapping("/fileListDeal")
    @ResponseBody
    public List<byte[]> filesToBytes(List<File> fileList) throws NoSuchAlgorithmException, IOException {
        List<byte[]>  list = new ArrayList<>();
        for (File file :fileList){
            list.add(fileService.fileToHash(file));
        }
        return list;
    }
}
