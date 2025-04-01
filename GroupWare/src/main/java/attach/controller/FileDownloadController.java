package attach.controller;

import java.util.Base64;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import attach.model.AttachDao;

@Controller
public class FileDownloadController {
    
    private final String command = "download.erp";
    private final String getPage = "approval.approvalList";
    
    private static final String GITHUB_API_URL;  // GitHub API 疫꿸퀡�궚 URL
    private static final String GITHUB_TOKEN; // GitHub 占쎈꽅占쎄쿃
  
    @Autowired
    private AttachDao attachDao;

    @RequestMapping(command)
    @ResponseBody
    public ResponseEntity<byte[]> downloadFileFromGitHub(@RequestParam(value="doc_no", required = false) String doc_no,
    													@RequestParam(value="msg_no", required = false) String msg_no)
    																							throws Exception {
    	String serverFileName = null;
    	
    	if(doc_no != null) {
    		serverFileName = attachDao.getServerFileNameByDocNo(doc_no);
    	}else if(msg_no != null) {
    		serverFileName = attachDao.getServerFileNameByDocNo(msg_no);
    	}
    	
    	System.out.println("msg_no : " + msg_no);
    	System.out.println("serverFileName : " + serverFileName);
    	
        if (serverFileName == null) {
        	
        }

        String filePath = "mail/" + serverFileName;  
        String apiUrl = GITHUB_API_URL + filePath; 
  
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + GITHUB_TOKEN);  
        HttpEntity<String> entity = new HttpEntity<String>(headers);

        try {
            ResponseEntity<String> response = restTemplate.exchange(apiUrl, HttpMethod.GET, entity, String.class);
  
            if (response.getStatusCode() != HttpStatus.OK) {
                return new ResponseEntity<byte[]>(HttpStatus.NOT_FOUND); 
            }

            JSONObject jsonResponse = new JSONObject(response.getBody());
            String base64Content = jsonResponse.getString("content");

            if (base64Content == null || base64Content.isEmpty()) {
                return new ResponseEntity<byte[]>(HttpStatus.NOT_FOUND); 
            }

            String cleanedBase64Content = base64Content.replaceAll("\\s+", "");
            byte[] decodedBytes = Base64.getDecoder().decode(cleanedBase64Content);

            HttpHeaders downloadHeaders = new HttpHeaders();
            downloadHeaders.add("Content-Disposition", "attachment; filename=\"" + serverFileName + "\"");
            downloadHeaders.setContentType(MediaType.APPLICATION_OCTET_STREAM);

            return new ResponseEntity<byte[]>(decodedBytes, downloadHeaders, HttpStatus.OK);
            
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<byte[]>(HttpStatus.INTERNAL_SERVER_ERROR); 
        }
    }
}
