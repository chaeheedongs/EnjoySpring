package com.enjoy.Spring;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.Arrays;

@Slf4j
@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(Application.class, args);

		printPrefix();

//		printAllBeans(ctx);
		printMyBeans(ctx);

		printSuffix();
	}

	private static void printPrefix() {
		log.error("");
		log.error("### Print Spring Context Beans");
		log.error("==================================================================================================");
	}

	private static void printSuffix() {
		log.error("==================================================================================================");
		log.error("");
	}

	private static String[] getBeanNames(final ApplicationContext ctx) {
		String[] beanNames = ctx.getBeanDefinitionNames();
		Arrays.sort(beanNames);

		return beanNames;
	}

	private static void printAllBeans(final ApplicationContext ctx) {
		final String[] beanNames = getBeanNames(ctx);
		for (String beanName : beanNames) {
			log.warn(beanName);
		}
	}

	private static void printMyBeans(final ApplicationContext ctx) {
		final String[] beanNames = getBeanNames(ctx);
		for (String beanName : beanNames) {
			Object bean = ctx.getBean(beanName);
			if (bean.getClass().getPackage().getName().startsWith("com.enjoy.Spring")) {
				log.warn("{} -> {}", beanName, bean.getClass().getName());
			}
		}
	}
}
