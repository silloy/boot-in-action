package com.zj.jvmsamples;

import com.zj.jvmsamples.controller.HeapOOM;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
public class JvmSamplesApplication {

	public static void main(String[] args) {
		SpringApplication.run(JvmSamplesApplication.class, args);
	}
}
