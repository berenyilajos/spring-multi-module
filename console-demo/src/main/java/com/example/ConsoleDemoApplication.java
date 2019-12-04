package com.example;

import com.example.demo.bd.BDProduct;
import com.example.demo.bd.BDUser;
import com.example.demo.parser.manager.ParsManager;
import com.example.demo.service.ProductService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;
import java.util.Random;

@SpringBootApplication
public class ConsoleDemoApplication implements CommandLineRunner {

	@Autowired
	private UserService userService;
	@Autowired
	private ParsManager parsManager;
	@Autowired
	private ProductService productService;

	public static void main(String[] args) {
		SpringApplication.run(ConsoleDemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		String name = "valaki";
		BDUser bdUser = new BDUser();
		bdUser.setName(name);

		Random random = new Random();
		for (int i = 0; i < 10; i++) {
			bdUser.setEmail(name + i + "@example.com");
			if (random.nextBoolean()) {
				userService.addUser(bdUser);
			} else {
				userService.saveUser(bdUser);
			}
		}

		System.out.println("Users: " + userService.getAllUsers());

		System.out.println("Users with name: " + userService.getUserByName(name));

		String path = "valami";
		parsManager.write(bdUser, path);
		BDUser u = parsManager.parseUser(path);

		System.out.println("================*************=================");
		System.out.println(u);

		parsManager.write2(bdUser, path);
		u = parsManager.parseUser2(path);

		System.out.println("================*************=================");
		System.out.println("================*************=================");
		System.out.println(u);

		BDProduct product = new BDProduct();
		product.setName("Valaki22");
		product.setPrice(BigDecimal.valueOf(1000));
		String path2 = "valami2";
		parsManager.write(product, path2);
		BDProduct p = parsManager.parseProduct(path2);

		System.out.println("================*************=================");
		System.out.println("================*************=================");
		System.out.println(p);

		for (int i = 0; i < 10; i++) {
			p.setPrice(p.getPrice().add(BigDecimal.valueOf(i)));
			if (random.nextBoolean()) {
				productService.addProduct(p);
			} else {
				productService.save(p);
			}
		}

		System.out.println("================*************=================");
		System.out.println("Products: " + productService.findAll());

		System.out.println("================*************=================");
		System.out.println("All products: " + productService.getAllProducts());

		System.out.println("================*************=================");
		System.out.println("================*************=================");
		System.out.println("Products by name " + p.getName() + ": " + productService.findAll());

		System.out.println("================*************=================");
		System.out.println("All Products by name " + p.getName() + ": " + productService.getAllProducts());

		System.out.println("================*************=================");
		System.out.println("================*************=================");
		BigDecimal price = BigDecimal.valueOf(1020);
		System.out.println("All Products cheaper then " + price + ": " + productService.findProductsCheaperThen(price));
	}
}
