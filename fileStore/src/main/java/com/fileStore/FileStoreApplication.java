package com.fileStore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class FileStoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(FileStoreApplication.class, args);
	}

}
