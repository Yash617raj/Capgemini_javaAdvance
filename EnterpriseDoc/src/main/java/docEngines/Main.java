package docEngines;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(appConfig.class);
		
		DocumentEngine documentEngine = context.getBean(DocumentEngine.class);
		
		documentEngine.execute("Hello World!");
		context.close();
	}
}
