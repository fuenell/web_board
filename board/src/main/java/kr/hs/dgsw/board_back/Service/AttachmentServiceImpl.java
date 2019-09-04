package kr.hs.dgsw.board_back.Service;

import kr.hs.dgsw.board_back.Domain.User;
import kr.hs.dgsw.board_back.Protocol.AttachmentProtocol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URLConnection;
import java.util.UUID;

@Service
public class AttachmentServiceImpl implements AttachmentService {

    @Autowired
    UserService userService;

    @Override
    public AttachmentProtocol upload(MultipartFile srcFile) {
        String destFilePath = "D:\\Other\\Project\\IntelliJ\\web_board\\board_back\\Upload\\"
                + UUID.randomUUID().toString() + "_"
                + srcFile.getOriginalFilename();
        try {
            File destFile = new File(destFilePath);
            destFile.getParentFile().mkdirs();
            srcFile.transferTo(destFile);
            return new AttachmentProtocol(destFilePath, srcFile.getOriginalFilename());

        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public void download(HttpServletResponse response, String user_id) {
        try {
            User user = this.userService.selectUser(user_id);
            String filePath = user.getProfile();

            File file = new File(filePath);
            if (file.exists() == false) return;

            String mineType = URLConnection.guessContentTypeFromName(file.getName());
            if (mineType == null) mineType = "application/octet-stream";

            response.setContentType(mineType);
            response.setHeader("Content-Disposition", "inline; filename='프로필'");
            response.setContentLength((int) file.length());

            InputStream is = new BufferedInputStream(new FileInputStream(file));
            FileCopyUtils.copy(is, response.getOutputStream());
        } catch (Exception e) {
        }
    }
}
