package kr.hs.dgsw.board_back.Service;

import kr.hs.dgsw.board_back.Protocol.AttachmentProtocol;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

public interface AttachmentService {
    AttachmentProtocol upload(MultipartFile srcFile);

    void download(HttpServletResponse response, String user_id);
}
