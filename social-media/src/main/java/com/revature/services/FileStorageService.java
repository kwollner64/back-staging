package com.revature.services;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

import net.bytebuddy.asm.Advice.This;

@Service
public class FileStorageService {

	public static int listSize = 0;
	private final Path root = Paths.get("uploads");
	
	public Path getPath() {
		return root;
	}
	
	public void init() {
		try {
			Files.createDirectory(root);
		} catch (IOException e) {
			throw new RuntimeException("Could not initialize folder for upload!");
		}
	}
	
	public void save(MultipartFile file) {
		try {
			listSize++;
			
			Files.copy(file.getInputStream(), this.root.resolve(listSize + file.getOriginalFilename()));
		} catch (Exception e) {
			throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
		}
	}
	
	public Resource load(String filename) {
		try {
			Path file = root.resolve(filename);
			Resource resource = new UrlResource(file.toUri());
			
			if(resource.exists() || resource.isReadable()) {
				return resource;
			} else {
				throw new RuntimeException("Could not read the file!");
			}
		} catch (MalformedURLException e) {
			throw new RuntimeException("Error: " + e.getMessage());
			// TODO: handle exception
		}
	}
	
	public void deleteAll() {
		FileSystemUtils.deleteRecursively(root.toFile());
	}
	
	public Stream<Path> loadAll(){
		try {
			return Files.walk(this.root, 1).filter(path -> !path.equals(this.root));
		} catch (IOException e) {
			throw new RuntimeException("Could not load the files!");
		}
		
	}
}
