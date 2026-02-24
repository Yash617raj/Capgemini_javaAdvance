package Lazy_Demo;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
	public  static void main(String[] args) {
		System.out.println("Container Created!");
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(LazyConfig.class);
		
		LazyBean lazyBean = context.getBean(LazyBean.class);
		System.out.println();
		
		System.out.println("Container Started!");
		lazyBean.start();
		context.close();
	}
}
