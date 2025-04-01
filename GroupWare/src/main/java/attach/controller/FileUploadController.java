package attach.controller;

import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class FileUploadController {

	private static final String GITHUB_API_URL;

	private static final String GITHUB_TOKEN;

	@ResponseBody
	public String uploadFile(@RequestParam("file") MultipartFile file, HttpServletResponse response) {
		System.out.println("file : " + file);

		response.setContentType("application/json; charset=UTF-8");

		if (file.isEmpty()) {
			return "{\"message\": \"�떎�뙣�뻽�뒿�땲�떎.\"}";
		}

		try {
			String timeStamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());


			String newFileName = timeStamp + "_" + file.getOriginalFilename();

			byte[] fileContent = file.getBytes();
			String encodedContent = Base64.getEncoder().encodeToString(fileContent); // json �씤肄붾뵫

			String githubFileName = uploadToGitHub(newFileName, encodedContent);

			return newFileName;
		} catch (Exception e) {
			return null;
		}
	}


	private String uploadToGitHub(String fileName, String encodedContent) {
		String filePath = "mail/" + fileName; 

		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "Bearer " + GITHUB_TOKEN); 
		headers.setContentType(MediaType.APPLICATION_JSON);

		String jsonBody = "{"
				+ "\"message\": \"Add " + fileName + "\"," 
				+ "\"content\": \"" + encodedContent + "\","
				+ "\"path\": \"" + filePath + "\""
				+ "}";

		HttpEntity<String> entity = new HttpEntity<String>(jsonBody, headers);
		RestTemplate restTemplate = new RestTemplate();

		ResponseEntity<String> response = restTemplate.exchange(GITHUB_API_URL + filePath, HttpMethod.PUT, entity, String.class);
		return response.getBody();
	}
}
