package uk.gov.nhsdigital.mhdstu3.docsource.client.main;

import org.hl7.fhir.dstu3.model.CarePlan;
import org.hl7.fhir.dstu3.model.DateTimeType;
import org.hl7.fhir.dstu3.model.CarePlan.CarePlanStatus;
import org.hl7.fhir.dstu3.model.Coding;
import org.hl7.fhir.dstu3.model.Condition.ConditionVerificationStatus;
import org.hl7.fhir.dstu3.model.DocumentManifest;
import org.hl7.fhir.dstu3.model.Identifier;
import org.hl7.fhir.dstu3.model.Meta;
import org.hl7.fhir.dstu3.model.Period;
import org.hl7.fhir.dstu3.model.Reference;
import org.hl7.fhir.dstu3.model.codesystems.CarePlanIntent;


public class PCC37UpdateCarePlan {
	
	@SuppressWarnings({ "unchecked", "deprecation", "rawtypes" })
	public static CarePlan createCarePlan(){
		
		final CarePlan cp1 = new CarePlan();
		
		cp1.setId("1.2.40.0.13.1.1.10.10.10.239.20170623112052975.32771");
		
		//Does NOT post meta.profile value to http://fhirtest.uhn.ca/baseDstu3
		Meta meta1 = new Meta();
		meta1.addProfile("http://fhir.nhs.uk/StructureDefinition/ldp-careplan-1-0");
		cp1.setMeta(meta1);
		
		Identifier rr4 = new Identifier();
		rr4.setValue("31daadee-26e1-4d6a-9e6a-7f4af9b58877");
		cp1.addIdentifier(rr4);
		
		cp1.setStatus(CarePlanStatus.ACTIVE);
		
		//cp1.setIntent(CarePlanIntent.PLAN);
		
		//BROKEN
		/*Coding cpcat = cp1.getCode().addCoding();
		cpcat.setSystem("http://hl7.org/fhir/ValueSet/condition-category");
		cpcat.setCode("diagnosis");
		cpcat.setDisplay("diagnosis");
		cp1.setVerificationStatus(ConditionVerificationStatus.CONFIRMED);
		return cp1;*/
		
		cp1.setDescription("cp1 desc");
		
		cp1.addAddresses(new Reference().setReference("Condition/...."));
		
		/*...relatedPlan
		This version of the profile requires that a related DynamicCarePlan be referenced when it is a relatedPlan.*/
		
		 //Subject
		cp1.setSubject(new Reference().setReference("Patient/4963").setDisplay("Mr Richard Smith"));
		/* Encounter/ Episode of Care - Identifies the original context in which this particular CarePlan was created.
		cp1.setContext(new Reference().setReference("Patient/4963").setDisplay("Mr Richard Smith"));*/
		cp1.setPeriod(new Period().setStartElement(new DateTimeType("2017-06-23T11:20:53+02:00")).setEndElement(new DateTimeType("2017-06-23T11:20:53+02:00")));
		
		  //Author
		cp1.addAuthor(new Reference().setReference("Practitioner/4961").setDisplay("Mrs Angela Smith"));
		
		return cp1;

}

	
}