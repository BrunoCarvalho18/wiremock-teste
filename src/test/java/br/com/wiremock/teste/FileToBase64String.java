package br.com.wiremock.teste;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;

public class FileToBase64String {

	public static void main(String[] args) throws IOException {
		byte[] bytes = Files.readAllBytes(Paths.get("C:\\Users\\Bruno Carvalho\\eclipse-workspace\\wiremock\\src\\test\\resources\\novo-rg.jpg"));
		String encodedString = Base64.getMimeEncoder().encodeToString(bytes);
		System.out.println("esse é o arquivo rg: "+encodedString);
	}

}
