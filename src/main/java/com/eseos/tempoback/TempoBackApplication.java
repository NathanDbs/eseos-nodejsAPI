package com.eseos.tempoback;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
public class TempoBackApplication {

	private static ConfigurableApplicationContext context;

	public static void main(String[] args) {
		context = SpringApplication.run(TempoBackApplication.class, args);
	}

	public static void restart(String profile) {

		Thread threadForRestart = new Thread(() -> {
			context.close();
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			context = SpringApplication.run(TempoBackApplication.class, "--spring.profiles.active="+profile);
		});

		threadForRestart.setDaemon(false);
		threadForRestart.start();
	}

	public static boolean appIsRunning(){
		return context.isRunning();
	}
}
