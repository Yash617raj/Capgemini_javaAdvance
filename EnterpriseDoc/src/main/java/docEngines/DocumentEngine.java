package docEngines;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class DocumentEngine {
	
	 	private final DocumentProcessor documentProcessor;
	    private AuditService auditService;

	    @Autowired
	    private StorageService storageService;

	    @Autowired
	    public DocumentEngine(@Qualifier("xmlDocumentProcessor") DocumentProcessor documentProcessor) {
	        this.documentProcessor = documentProcessor;
	    }

	    @Autowired
	    public void setAuditService(AuditService auditService) {
	        this.auditService = auditService;
	    }

	    public void execute(String documentName) {

	        auditService.log("Starting processing of " + documentName);
	        documentProcessor.processDocument(documentName);
	        storageService.store(documentName);

	        System.out.println("Document processing completed.");
	    }
}


