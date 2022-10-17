package com.revature.controllers;

import java.net.http.HttpHeaders;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import com.revature.dtos.FileInfo;
import com.revature.messages.ResponseMessage;
import com.revature.services.FileStorageService;



@Controller
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
public class FilesController {

	@Autowired
	FileStorageService fileStorageService;
	
	/*@PostMapping("/upload")
	public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file){
		String message = "";
		try {
			fileStorageService.save(file);
			
			message = "Upload the file successfully: " + file.getOriginalFilename();
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
		} catch (Exception e) {
			message = "Could not upload the file: " + file.getOriginalFilename() + "!";
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
		}
	}*/
	
	@PostMapping("/upload")
	public ResponseEntity<FileInfo> uploadFile(@RequestParam("file") MultipartFile file){
		try {
			fileStorageService.save(file);
			
			return ResponseEntity.status(HttpStatus.OK).body(new FileInfo(file.getOriginalFilename(),file.getOriginalFilename()));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.OK).body(null);
		}
	}
	
	@GetMapping("/files")
	public ResponseEntity<List<FileInfo>> getListFiles(){
		List<FileInfo> fileInfos = fileStorageService.loadAll().map(path -> {
			String filename = path.getFileName().toString();
			
			 String url = MvcUriComponentsBuilder
			          .fromMethodName(FilesController.class, "getFile", path.getFileName().toString()).build().toString();
			 
			 return new FileInfo(filename,url); 
			
		}).collect(Collectors.toList());
		
		return ResponseEntity.status(HttpStatus.OK).body(fileInfos);
	}
	
	@GetMapping("/files/test")
	public ResponseEntity<ResponseMessage> getTest(){
		ResponseMessage response = new ResponseMessage("whoa!");
		
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
	
	@GetMapping("/files/{filename:.+}")
	@ResponseBody
	public ResponseEntity<Resource> getFile(@PathVariable String filename){
		Resource file = fileStorageService.load(filename);
		 return ResponseEntity.ok()
			        .header("Content-Disposition", "attachment; filename=\"" + file.getFilename() + "\"").body(file);
	}
	
	@PostMapping("/upload/test/{bla}")
	public ResponseEntity<ResponseMessage> uploadFilez(@PathVariable("bla") String bla){
		String message = "WOW IT WORKS";
		return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
		/*try {
			fileStorageService.save(file);
			
			message = "Upload the file successfully: " + file.getOriginalFilename();
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
		} catch (Exception e) {
			message = "Could not upload the file: " + file.getOriginalFilename() + "!";
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
		}*/
	}
	
}
