package uk.gov.nhsdigital.mhdstu3.docsource.client.main;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.rest.api.MethodOutcome;
import ca.uhn.fhir.rest.client.IGenericClient;
import ca.uhn.fhir.rest.client.interceptor.LoggingInterceptor;
import ca.uhn.fhir.rest.gclient.ICreate;
import uk.gov.nhsdigital.mhdstu3.docsource.client.main.CreateXMLDocument;
import org.hl7.fhir.dstu3.model.Patient;

import javax.annotation.Resource;

import org.hl7.fhir.dstu3.model.Attachment;
import org.hl7.fhir.dstu3.model.Binary;
import org.hl7.fhir.dstu3.model.Bundle;
import org.hl7.fhir.dstu3.model.Bundle.BundleEntryComponent;
import org.hl7.fhir.dstu3.model.Bundle.BundleEntryRequestComponent;
import org.hl7.fhir.dstu3.model.Bundle.HTTPVerb;
import org.hl7.fhir.dstu3.model.Coding;
import org.hl7.fhir.dstu3.model.DateTimeType;
import org.hl7.fhir.dstu3.model.DocumentManifest;
import org.hl7.fhir.dstu3.model.DocumentReference;
import org.hl7.fhir.dstu3.model.DocumentReference.DocumentReferenceContentComponent;
import org.hl7.fhir.dstu3.model.DocumentReference.DocumentReferenceContextComponent;
import org.hl7.fhir.dstu3.model.DocumentReference.ReferredDocumentStatus;
import org.hl7.fhir.dstu3.model.Enumeration;
import org.hl7.fhir.dstu3.model.Enumerations.DocumentReferenceStatus;
import org.hl7.fhir.dstu3.model.Identifier;
import org.hl7.fhir.dstu3.model.InstantType;
import org.hl7.fhir.dstu3.model.Reference;

public class MHDClient {

/*
	 * This class contains MHDClient test calls
	 */

	public static void CreateDocumentReference() {

			// create a context for DSTU2
			FhirContext ctx = FhirContext.forDstu3();
			
			org.hl7.fhir.dstu3.model.DocumentReference docref = CreateXML.createDocumentReference();
			// We can now use a parser to encode this resource into a string.
			String output = ctx.newXmlParser().setPrettyPrint(true).encodeResourceToString(docref);
			
			System.out.println(output);
			
			//create client
			String serverBaseUrl = "http://localhost:8080/hapi-fhir-jpaserver-example/baseDstu3";
			//String serverBaseUrl = "http://localhost:4080/vonk.furore.com";
			//String serverBaseUrl = "http://fhirtest.uhn.ca/baseDstu3";
			//String serverBaseUrl = "http://fhir3.healthintersections.com.au/open";
			//String serverBaseUrl = "http://phh-l-iopweb-l1:8080/hapi-fhir-jpaserver/baseDstu2";
			IGenericClient client = ctx.newRestfulGenericClient(serverBaseUrl);
					
			//use client to store a new resource instance
			MethodOutcome outcome = client.create().resource(docref).execute();
			System.out.println(outcome.getId());
			
		}
	
	
	public static void CreateITKFHIRDocumentLevel1() {
	
	// create a context for STU3
	FhirContext ctx = FhirContext.forDstu3();
	// Create ITK Level 1 XML FHIR Document
	org.hl7.fhir.dstu3.model.Bundle bndl = CreateXMLDocument.createBundle();
	DocumentReference dr = CreateXMLDocument.createDocumentReference();
	Binary by = CreateXMLDocument.createBinary();
	/*Composition cp = CreateXMLDocument.createComposition();
	Patient pt = CreateXMLDocument.createPatient();
	Practitioner prac = CreateXMLDocument.createPractitioner();
	Practitioner prac1 = CreateXMLDocument.createPractitioner1();
	Practitioner prac2 = CreateXMLDocument.createPractitioner2();
	Organization orgn = CreateXMLDocument.createOrganization();
	Organization orgn1 = CreateXMLDocument.createOrganization1();
	Encounter enc1 = CreateXMLDocument.createEncounter();
	//Due to 'Type mismatch: cannot convert from Location to Encounter.Location' Location code:
	ca.uhn.fhir.model.dstu2.resource.Location loc3 = CreateXMLDocument.createLocation();*/
	
	BundleEntryComponent entryBundle = new BundleEntryComponent();
	//final BundleEntryComponent entryBundle = bndl.addEntry();		
	entryBundle.setFullUrl("urn:uuid:d81fac1b-1bf5-4e8c-a16c-eeead038e77d");
	bndl.addEntry(entryBundle);
	entryBundle.setResource(dr);
	
	BundleEntryComponent entryBinary = new BundleEntryComponent();
	//final BundleEntryComponent entryBinary = bndl.addEntry();
	entryBinary.setFullUrl("urn:uuid:d82fac1b-1bf5-4e8c-a16c-eeead038e77d");
	bndl.addEntry(entryBinary);
	entryBinary.setResource(by);
/*	final Entry entryPrac = bndl.addEntry();
	final Entry entryPrac1 = bndl.addEntry();
	final Entry entryPrac2 = bndl.addEntry();
	final Entry entryOrg = bndl.addEntry();
	final Entry entryPat = bndl.addEntry();
	final Entry entryEnc = bndl.addEntry();
	final Entry entryLoc = bndl.addEntry();
	final Entry entryOrg1 = bndl.addEntry();*/
	
//	entryBundle.setResource(dr);
//	entryBinary.setResource(by);
	/*entryBundle.setResource(cp);
	entryPrac.setResource(prac);
	entryPrac1.setResource(prac1);
	entryOrg.setResource(orgn);
	entryPat.setResource(pt);
	entryPrac2.setResource(prac2);
	entryEnc.setResource(enc1);
	entryOrg1.setResource(orgn1);
	entryLoc.setResource(loc3);*/
	
	// We can now use a parser to encode this resource into a string.
	String output = ctx.newXmlParser().setPrettyPrint(true).encodeResourceToString(bndl);
	System.out.println(output);
	
	//create client
	
	String serverBaseUrl = "http://localhost:8080/hapi-fhir-jpaserver-example/baseDstu3";
	//String serverBaseUrl = "http://fhirtest.uhn.ca/baseDstu3";
	//String serverBaseUrl = "http://fhir3.healthintersections.com.au/open";
	IGenericClient client = ctx.newRestfulGenericClient(serverBaseUrl);
	
	//use client to store a new resource instance
	MethodOutcome outcome = client.create().resource(bndl).execute();
	System.out.println(outcome.getId());
	
}
	
	
	/*3.65 Provide Document Bundle [ITI-65]
	The Document Source shall initiate a FHIR “transaction” using a “create” action by sending an 
	HTTP POST request methods composed of a FHIR Bundle Resource containing the DocumentManifest resource, 
	one or more DocumentReference Resources, zero or more List Resources, and zero or more Binary Resources 
	to the Document Recipient. Refer to ITI TF-3: 4.5.1 for details on the FHIR Resources and how Document Sharing metadata attributes are mapped.*/
	
	public static void ITI65ProvideDocumentBundleScenario1() {
		
		// create a context for STU3
		FhirContext ctx = FhirContext.forDstu3();
		// Create ITK Level 1 XML FHIR Document
		org.hl7.fhir.dstu3.model.Bundle bndl = CreateXMLDocumentManifest.createBundle();
		DocumentManifest dm = CreateXMLDocumentManifest.createDocumentManifest();
		DocumentReference dr = CreateXMLDocumentManifest.createDocumentReference();
		Binary by = CreateXMLDocumentManifest.createBinary();
		
		BundleEntryComponent entryBundle = new BundleEntryComponent();
		//final BundleEntryComponent entryBundle = bndl.addEntry();		
		entryBundle.setFullUrl("urn:uuid:d81fac1b-1bf5-4e8c-a16c-eeead038e77d");
		entryBundle.getRequest().setMethod(HTTPVerb.POST).setUrl("DocumentManifest");
		bndl.addEntry(entryBundle);
		entryBundle.setResource(dm);

		BundleEntryComponent entryDocumentRef = new BundleEntryComponent();
		//final BundleEntryComponent entryBundle = bndl.addEntry();		
		entryDocumentRef.setFullUrl("urn:uuid:14daadee-26e1-4d6a-9e6a-7f4af9b58877");
		entryDocumentRef.getRequest().setMethod(HTTPVerb.POST).setUrl("DocumentReference");
		bndl.addEntry(entryDocumentRef);
		entryDocumentRef.setResource(dr);
		
		/*BundleEntryComponent entryBinary = new BundleEntryComponent();
		//final BundleEntryComponent entryBinary = bndl.addEntry();
		entryBinary.setFullUrl("urn:uuid:d82fac1b-1bf5-4e8c-a16c-eeead038e77d");
		entryBinary.getRequest().setMethod(HTTPVerb.POST).setUrl("Binary");
		bndl.addEntry(entryBinary);
		entryBinary.setResource(by);*/

		// We can now use a parser to encode this resource into a string.
		String output = ctx.newXmlParser().setPrettyPrint(true).encodeResourceToString(bndl);
		System.out.println(output);
		
		//create client
		String serverBaseUrl = "http://localhost:8080/hapi-fhir-jpaserver-example/baseDstu3";
		//String serverBaseUrl = "http://fhirtest.uhn.ca/baseDstu3";
		//String serverBaseUrl = "http://fhir3.healthintersections.com.au/open";
		IGenericClient client = ctx.newRestfulGenericClient(serverBaseUrl);
		
		//use client to execute a transaction by manually creating and passing in a populated bundle
		Bundle outcome = client.transaction().withBundle(bndl).execute();
		System.out.println(outcome.getId());
		
		/*The above client.transaction().....code fixed this ISSUE
		Exception in thread "main" ca.uhn.fhir.rest.server.exceptions.UnprocessableEntityException: 
		HTTP 422 Unprocessable Entity: Unable to store a Bundle resource on this server with a Bundle.type value of: transaction
		at sun.reflect.NativeConstructorAccessorImpl.newInstance0(Native Method)
		bndl.setType(BundleType.TRANSACTION);*/
		
	}
	
	
public static void CreateLDPITI65ProvideDocumentBundle() {
		
		// create a context for STU3
		FhirContext ctx = FhirContext.forDstu3();
		// Create ITK Level 1 XML FHIR Document
		org.hl7.fhir.dstu3.model.Bundle bndl = CreateIT165ProvideDocumentBundle.createBundle();
		DocumentManifest dm = CreateIT165ProvideDocumentBundle.createDocumentManifest();
		DocumentReference dr = CreateIT165ProvideDocumentBundle.createDocumentReference();
		Binary by = CreateIT165ProvideDocumentBundle.createBinary();
		
		BundleEntryComponent entryBundle = new BundleEntryComponent();
		//final BundleEntryComponent entryBundle = bndl.addEntry();		
		entryBundle.setFullUrl("urn:uuid:1.2.40.0.13.1.1.10.10.10.239.20170623112052975.32771");
		entryBundle.getRequest().setMethod(HTTPVerb.POST).setUrl("DocumentManifest");
		bndl.addEntry(entryBundle);
		entryBundle.setResource(dm);

		BundleEntryComponent entryDocumentRef = new BundleEntryComponent();
		//final BundleEntryComponent entryBundle = bndl.addEntry();		
		entryDocumentRef.setFullUrl("urn:uuid:1.2.40.0.13.1.1.10.10.10.239.20170623112052975.32769");
		entryDocumentRef.getRequest().setMethod(HTTPVerb.POST).setUrl("DocumentReference");
		bndl.addEntry(entryDocumentRef);
		entryDocumentRef.setResource(dr);
		
		/*BundleEntryComponent entryBinary = new BundleEntryComponent();
		//final BundleEntryComponent entryBinary = bndl.addEntry();
		entryBinary.setFullUrl("urn:uuid:d82fac1b-1bf5-4e8c-a16c-eeead038e77d");
		entryBinary.getRequest().setMethod(HTTPVerb.POST).setUrl("Binary");
		bndl.addEntry(entryBinary);
		entryBinary.setResource(by);*/

		// We can now use a parser to encode this resource into a string.
		String output = ctx.newXmlParser().setPrettyPrint(true).encodeResourceToString(bndl);
		System.out.println(output);
		
		//create client
		/*String serverBaseUrl = "http://localhost:8080/hapi-fhir-jpaserver-example/baseDstu3";
		//String serverBaseUrl = "http://fhirtest.uhn.ca/baseDstu3";
		//String serverBaseUrl = "http://fhir3.healthintersections.com.au/open";
		IGenericClient client = ctx.newRestfulGenericClient(serverBaseUrl);
		
		//use client to execute a transaction by manually creating and passing in a populated bundle
		Bundle outcome = client.transaction().withBundle(bndl).execute();
		System.out.println(outcome.getId());*/
		
		/*The above client.transaction().....code fixed this ISSUE
		Exception in thread "main" ca.uhn.fhir.rest.server.exceptions.UnprocessableEntityException: 
		HTTP 422 Unprocessable Entity: Unable to store a Bundle resource on this server with a Bundle.type value of: transaction
		at sun.reflect.NativeConstructorAccessorImpl.newInstance0(Native Method)
		bndl.setType(BundleType.TRANSACTION);*/
		
	}


   public static void CreateLDPITI65ProvideDocumentBundleConnectathonScenario1() {
	
	// create a context for STU3
	FhirContext ctx = FhirContext.forDstu3();
	// Create ITK Level 1 XML FHIR Document
	org.hl7.fhir.dstu3.model.Bundle bndl = CreateITI65ProvideDocumentBundleConnectathonScenario1.createBundle();
	DocumentManifest dm = CreateITI65ProvideDocumentBundleConnectathonScenario1.createDocumentManifest();
	DocumentReference dr = CreateITI65ProvideDocumentBundleConnectathonScenario1.createDocumentReference();
	Binary by = CreateITI65ProvideDocumentBundleConnectathonScenario1.createBinary();
	
	BundleEntryComponent entryBundle = new BundleEntryComponent();
	//final BundleEntryComponent entryBundle = bndl.addEntry();	
	/*Absolute URL for the Resource. 
	  Identifies the resource within bundle
	  Must be provided for all bundle entries.
	  fullURL MUST match id in resource.*/
	entryBundle.setFullUrl("urn:uuid:1.2.40.0.13.1.1.10.10.10.239.20170623112052975.32771");
	/*First Resource:  DocumentManifest*/
	entryBundle.getRequest().setMethod(HTTPVerb.POST).setUrl("DocumentManifest");
	bndl.addEntry(entryBundle);
	entryBundle.setResource(dm);

	BundleEntryComponent entryDocumentRef = new BundleEntryComponent();
	//final BundleEntryComponent entryBundle = bndl.addEntry();	
	/*Identifies the resource within bundle*/
	entryDocumentRef.setFullUrl("urn:uuid:1.2.40.0.13.1.1.10.10.10.239.20170623112052975.32769");
	/*Second Resource:  DocumentReference*/
	entryDocumentRef.getRequest().setMethod(HTTPVerb.POST).setUrl("DocumentReference");
	bndl.addEntry(entryDocumentRef);
	entryDocumentRef.setResource(dr);
	
	/*Third Resource:  Binary*/
	/*BundleEntryComponent entryBinary = new BundleEntryComponent();
	//final BundleEntryComponent entryBinary = bndl.addEntry();
	entryBinary.setFullUrl("urn:uuid:d82fac1b-1bf5-4e8c-a16c-eeead038e77d");
	entryBinary.getRequest().setMethod(HTTPVerb.POST).setUrl("Binary");
	bndl.addEntry(entryBinary);
	entryBinary.setResource(by);*/

	// We can now use a parser to encode this resource into a string.
	String output = ctx.newXmlParser().setPrettyPrint(true).encodeResourceToString(bndl);
	System.out.println(output);
	
	//create client
	
	/*//ALMADOC project Server
	//String serverBaseUrl = "http://fhir.hl7fundamentals.org/baseDstu3";
	//String serverBaseUrl = "http://localhost:8080/hapi-fhir-jpaserver-example/baseDstu3";
	String serverBaseUrl = "http://fhirtest.uhn.ca/baseDstu3";
	//String serverBaseUrl = "http://fhir3.healthintersections.com.au/open";
	IGenericClient client = ctx.newRestfulGenericClient(serverBaseUrl);
	
	//use client to execute a transaction by manually creating and passing in a populated bundle
	Bundle outcome = client.transaction().withBundle(bndl).execute();
	System.out.println(outcome.getId());*/
	
	/*The above client.transaction().....code fixed this ISSUE
	Exception in thread "main" ca.uhn.fhir.rest.server.exceptions.UnprocessableEntityException: 
	HTTP 422 Unprocessable Entity: Unable to store a Bundle resource on this server with a Bundle.type value of: transaction
	at sun.reflect.NativeConstructorAccessorImpl.newInstance0(Native Method)
	bndl.setType(BundleType.TRANSACTION);*/
	
}
	
	public static void MHDClientSearch1() {
		
		//See: http://hapifhir.io/doc_rest_client.html#SearchQuery_-_Type
		
		FhirContext ctx = FhirContext.forDstu3();
		IGenericClient client = ctx.newRestfulGenericClient("http://fhirtest.uhn.ca/baseDstu3");
		
		//Log requests and responses (verbose testing)
		client.registerInterceptor(new LoggingInterceptor(true));
		
		//build a search and execute it
		Bundle response = client.search()
				.forResource(Patient.class)
				.where(Patient.NAME.matches().value("Test"))
				.and(Patient.BIRTHDATE.before().day("2014-01-01"))
				.count(100)
				.returnBundle(Bundle.class)
				.execute();
		
		//how many resources did we find?
		System.out.println("Responses:" + response.getTotal());
		
		//Print the Id of the first one
		System.out.println(response.getEntry().get(0).getResource().getId());
			
	}
	
	
	
public static void MHDClientSearch2() {
		
		//See: http://hapifhir.io/doc_rest_client.html#SearchQuery_-_Type
	
		//Location: http://fhirtest.uhn.ca/baseDstu3/Bundle/216923/_history/1[\r][\n]"
		
		FhirContext ctx = FhirContext.forDstu3();
		IGenericClient client = ctx.newRestfulGenericClient("https://fhirtest.uhn.ca/baseDstu3");
		//IGenericClient client = ctx.newRestfulGenericClient("https://fhirtest.uhn.ca/baseDstu2");
		
		//Log requests and responses (verbose testing)
		client.registerInterceptor(new LoggingInterceptor(true));
		
		//build a search and execute it
		Bundle response = client.search()
				.forResource(DocumentReference.class)
				//.where(DocumentReference.TYPE.hasSystemWithAnyCode("http://snomed.info/sct"))
				.where(DocumentReference.IDENTIFIER.exactly().identifier("212867"))
				/*.where(DocumentReference.SUBJECT.hasId("Patient/17895"))
				//.where(DocumentReference.NAME.matches().value("Test"))
				.and(DocumentReference.INDEXED.exactly().day("2015-02-17"))
				//.and(DocumentReference.INDEXED.before().day("2015-02-18"))
				//.and(DocumentReference.BIRTHDATE.before().day("2014-01-01"))
				.count(5)*/
				.returnBundle(Bundle.class)
				.execute();
		
		//how many resources did we find?
		System.out.println("Responses:" + response.getTotal());
		
		//Print the Id of the first one
		System.out.println(response.getEntry().get(0).getResource().getId());
			
	}


//Search Works
public static void MHDClientSearch3() {
	
	//See: http://hapifhir.io/doc_rest_client.html#SearchQuery_-_Type

	/*"GET /baseDstu3/DocumentReference?created=ge2016-03-08&created=le2016-03-09 HTTP/1.1[\r][\n]"
	Responses:1
	https://fhirtest.uhn.ca/baseDstu3/DocumentReference/212867/_history/1*/	
	
	FhirContext ctx = FhirContext.forDstu3();
	IGenericClient client = ctx.newRestfulGenericClient("https://fhirtest.uhn.ca/baseDstu3");
	
	//Log requests and responses (verbose testing)
	client.registerInterceptor(new LoggingInterceptor(true));
	
	//build a search and execute it
	Bundle response = client.search()
			.forResource(DocumentReference.class)
			.where(DocumentReference.CREATED.afterOrEquals().day("2016-03-08"))
			.and(DocumentReference.CREATED.beforeOrEquals().day("2016-03-09"))
			.count(5)
			.returnBundle(Bundle.class)
			.execute();
	
	//how many resources did we find?
	System.out.println("Responses:" + response.getTotal());
	
	//Print the Id of the first one
	System.out.println(response.getEntry().get(0).getResource().getId());
		
}


public static void CreatePCCUpdateCarePlan() {

	// create a context for DSTU2
	FhirContext ctx = FhirContext.forDstu3();
	
	org.hl7.fhir.dstu3.model.CarePlan careplan = PCC37UpdateCarePlan.createCarePlan();
	// We can now use a parser to encode this resource into a string.
	String output = ctx.newXmlParser().setPrettyPrint(true).encodeResourceToString(careplan);
	
	System.out.println(output);
	
	/*//create client
	String serverBaseUrl = "http://localhost:8080/hapi-fhir-jpaserver-example/baseDstu3";
	//String serverBaseUrl = "http://fhirtest.uhn.ca/baseDstu3";
	//String serverBaseUrl = "http://fhir3.healthintersections.com.au/open";
	//String serverBaseUrl = "http://phh-l-iopweb-l1:8080/hapi-fhir-jpaserver/baseDstu2";
	IGenericClient client = ctx.newRestfulGenericClient(serverBaseUrl);
			
	//use client to store a new resource instance
	MethodOutcome outcome = client.create().resource(careplan).execute();
	System.out.println(outcome.getId());*/
	
}
	

public static void CreatePCCSubscribeToCarePlanUpdate() {

	// create a context for STU3
	FhirContext ctx = FhirContext.forDstu3();
	
	org.hl7.fhir.dstu3.model.Subscription subscription = PCC39SubscribeToCarePlanUpdates.createSubscription();
	// We can now use a parser to encode this resource into a string.
	String output = ctx.newXmlParser().setPrettyPrint(true).encodeResourceToString(subscription);
	
	System.out.println(output);
	
	/*//create client
	String serverBaseUrl = "http://localhost:8080/hapi-fhir-jpaserver-example/baseDstu3";
	//String serverBaseUrl = "http://fhirtest.uhn.ca/baseDstu3";
	//String serverBaseUrl = "http://fhir3.healthintersections.com.au/open";
	//String serverBaseUrl = "http://phh-l-iopweb-l1:8080/hapi-fhir-jpaserver/baseDstu2";
	IGenericClient client = ctx.newRestfulGenericClient(serverBaseUrl);
			
	//use client to store a new resource instance
	MethodOutcome outcome = client.create().resource(careplan).execute();
	System.out.println(outcome.getId());*/
	
}


	public static void main(String[] args) {

	    //CreateDocumentReference();
		//CreateITKFHIRDocumentLevel1();
		//MHDClientSearch1();
		//MHDClientSearch2();
		//MHDClientSearch3();
		//ITI65ProvideDocumentBundleScenario1();
		//CreateLDPITI65ProvideDocumentBundle();
		//CreatePCCUpdateCarePlan();
		//CreateLDPITI65ProvideDocumentBundleConnectathonScenario1();
		CreatePCCSubscribeToCarePlanUpdate();
	}
	
}