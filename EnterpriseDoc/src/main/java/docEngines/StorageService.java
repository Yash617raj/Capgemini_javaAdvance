package docEngines;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("singleton")
public class StorageService {
	
	public void store(String doc) {
		System.out.println("Storing document: "+doc);
	}
}
