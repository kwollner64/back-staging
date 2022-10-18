package com.revature;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

import javax.annotation.Resource;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.UrlResource;

import com.revature.dtos.FileInfo;
import com.revature.services.FileStorageService;

@ExtendWith(MockitoExtension.class)
public class FileStorageServiceTest {

	private FileStorageService fileStorageService;
	
	@Mock
	private FileStorageService testFileStorageService;
	
	@Mock
	FileInfo fileInfo;
	
	@Mock
	FileInfo otherInfo;
	
	@Mock
	UrlResource resource;
	
	@Mock
	UrlResource otherResource;
	
	@Mock
	List<FileInfo> fileInfos;
	
	@BeforeEach
	public void beforeEach() {
		fileStorageService = new FileStorageService();
		fileInfo = new FileInfo("fileInfo","fileInfo");
		otherInfo = new FileInfo("otherFileInfo","otherFileInfo");
	}
	
	@Test
	public void testCodeInsideInit() {
		Path path = Paths.get("tests");
			try {
				Files.delete(path);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		try {
			Files.createDirectory(path);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(true, Files.exists(path));
	}
	
	@Test
	public void testStartupOfFiles() {
		fileStorageService.deleteAll();
		fileStorageService.init();
		assertEquals(true, Files.exists(fileStorageService.getPath()));
	}
	
	@Test
	public void testLoadAll() {
		fileStorageService.deleteAll();
		fileStorageService.init();
		Stream<Path> pathStream = fileStorageService.loadAll();
		assertEquals(true, pathStream != null);
		
	}
	
	@Test
	public void testLoad() {
		try {
			resource = new UrlResource(Paths.get("tests").resolve("test.png").toUri());
			otherResource = new UrlResource(Paths.get("tests").resolve("test2.png").toUri());
			Mockito.when(testFileStorageService.load("test.png")).thenReturn(otherResource);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		resource = (UrlResource) testFileStorageService.load("test.png");
		assertEquals(otherResource.getFilename(), resource.getFilename());
		
	}
}
