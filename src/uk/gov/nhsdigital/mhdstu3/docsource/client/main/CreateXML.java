package uk.gov.nhsdigital.mhdstu3.docsource.client.main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.hl7.fhir.dstu3.model.Attachment;
import org.hl7.fhir.dstu3.model.Coding;
import org.hl7.fhir.dstu3.model.DateTimeType;
import org.hl7.fhir.dstu3.model.DocumentReference;
import org.hl7.fhir.dstu3.model.DocumentReference.DocumentReferenceContentComponent;
import org.hl7.fhir.dstu3.model.DocumentReference.DocumentReferenceContextComponent;
import org.hl7.fhir.dstu3.model.DocumentReference.ReferredDocumentStatus;
import org.hl7.fhir.dstu3.model.Enumerations.DocumentReferenceStatus;
import org.hl7.fhir.dstu3.model.Identifier;
import org.hl7.fhir.dstu3.model.InstantType;
import org.hl7.fhir.dstu3.model.Reference;

import ca.uhn.fhir.model.api.IResource;
import ca.uhn.fhir.model.api.ResourceMetadataKeyEnum;
import ca.uhn.fhir.model.base.resource.ResourceMetadataMap;
import ca.uhn.fhir.model.dstu.resource.DocumentReference.Context;

import org.hl7.fhir.dstu3.*;




public class CreateXML {

	@SuppressWarnings({ "unchecked", "deprecation", "rawtypes" })
	public static DocumentReference createDocumentReference(){
		
		final DocumentReference docref = new DocumentReference();
		
		docref.setId("14daadee-26e1-4d6a-9e6a-7f4af9b58871");
		
		//NEEDS FIX 1
		/*ResourceMetadataMap meta = new ResourceMetadataMap();
		meta.put(ResourceMetadataKeyEnum.PROFILES, "http://fhir.nhs.net/StructureDefinition/nrls-documentreference-1-0");
		docref.setResourceMetadata(meta);*/
		
		
		Identifier rr5 = new Identifier();
		rr5.setSystem(" http://www.acme.com/identifiers/patient").setValue("b19f24b7-e72c-44c0-bdf4-7e45d06db1b6");
		docref.setMasterIdentifier(rr5);
		
		Identifier rr4 = new Identifier();
		rr4.setValue("29daadee-26e1-4d6a-9e6a-7f4af9b58877");
		docref.addIdentifier(rr4);
	
		  //Subject
		//docref.setSubject(new Reference().setReference("https://pds.proxy.nhs.uk/Patient/9409401122").setDisplay("Mrs Diane Hembury"));
		
		  // Coded types can naturally be set using plain strings
		Coding doctype = docref.getType().addCoding();
		doctype.setSystem("http://fhir.nhs.net/ValueSet/correspondence-document-type-1");
        doctype.setCode("00000000000000");
        doctype.setDisplay("End of Life As You Know It");
        
        //Class element not specified in nrls-documentreference-1-0 profile
       /*Coding classtype = docref.getClass.addCoding();
        classtype.setSystem("http://hl7.org/fhir/ValueSet/doc-classcodes");
        //LOINC: LP173421-1 Report 
       classtype.setCode("LP173421-1");
        classtype.setDisplay("Report");*/
		
     
        //Author
        //docref.addAuthor(new Reference().setReference("Practitioner/17458").setDisplay("Leeds Teaching Hospital"));
        
		 //Custodian
		//docref.setCustodian(new Reference().setReference("Organization/17440").setDisplay("MGP Medical Centre"));
      
		//Authenticator
		//docref.setAuthenticator(new Reference().setReference("Practitioner/17458").setDisplay("Leeds Teaching Hospital"));
        
		docref.setCreatedElement(new DateTimeType("2017-03-08T15:26:00+01:00"));
		
		InstantType dt = new InstantType();
		dt.setValueAsString("2016-03-08T15:26:01+01:00");
		docref.setIndexedElement(dt);
		docref.setStatus(DocumentReferenceStatus.CURRENT);
		docref.setDocStatus(ReferredDocumentStatus.FINAL);
		
        
		 //Relationships to other documents not specified in nrls-documentreference-1-0 profile
//      RelatesTo rt = new RelatesTo();
//        //BROKEN
//// rt.setCode(DocumentRelationshipTypeEnum.REPLACES);// rt.setCode(new BoundCodeDt().setValueAsEnum(DocumentRelationshipTypeEnum.REPLACES));
////        rt.setCode(new BoundCodeDt().setValueAsEnum(DocumentRelationshipTypeEnum.REPLACES));
////        rt.getCodeElement(DocumentRelationshipTypeEnum.REPLACES);
// // rt.setCode(DocumentRelationshipTypeEnum.REPLACES);
//  
//      rt.setTarget(new ResourceReferenceDt().setReference("DocumentReference/122393").setDisplay("End of Life Care Coordination Summary"));
//      docref.addRelatesTo(rt);
//       // docref.setRelatesTo((List<RelatesTo>) rt);
       
		docref.setDescription("EOL  As You Know It");
        
        Coding seclabel = docref.getSecurityLabelFirstRep().addCoding();
        seclabel.setSystem("http://hl7.org/fhir/ValueSet/security-labels");
		seclabel.setCode("V");
		seclabel.setDisplay("very restricted");
		
        //Content
        List<DocumentReferenceContentComponent> ContentList = new ArrayList<DocumentReferenceContentComponent>();
        DocumentReferenceContentComponent cont = new DocumentReferenceContentComponent();
		cont.setAttachment(new Attachment().setContentType("application/pdf").setUrl("https://gpsystem.nhs.uk/epcaccs/9409401122/EOLAsYouKnowIt.pdf").setSize(1000).setTitle("EPaCCs Place chosen to die summary"));
		//Format/content rules for the document not specified in nrls-documentreference-1-0 profile
		//cont.addFormat(new CodingDt().setSystem("http://hl7.org/fhir/ValueSet/formatcodes").setCode("urn:ihe:pcc:handp:2008").setDisplay("History and Physical Specification"));
		ContentList.add(cont);
		docref.setContent(ContentList);
		
		//Context
		DocumentReferenceContextComponent contx = new DocumentReferenceContextComponent();
		contx.getFacilityType().getCodingFirstRep().setSystem("http://hl7.org/fhir/ValueSet/c80-facilitycodes").setCode("83891005").setDisplay("Solo practice private office");
		docref.setContext(contx);
		
		return docref;
	}
		
}
