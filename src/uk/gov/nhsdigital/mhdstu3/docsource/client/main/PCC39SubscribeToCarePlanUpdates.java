package uk.gov.nhsdigital.mhdstu3.docsource.client.main;

import java.util.ArrayList;
import java.util.List;

import org.hl7.fhir.dstu2016may.model.Subscription.SubscriptionChannelType;
import org.hl7.fhir.dstu3.model.Attachment;
import org.hl7.fhir.dstu3.model.CarePlan;
import org.hl7.fhir.dstu3.model.CodeType;
import org.hl7.fhir.dstu3.model.Coding;
import org.hl7.fhir.dstu3.model.ContactPoint;
import org.hl7.fhir.dstu3.model.InstantType;
import org.hl7.fhir.dstu3.model.Meta;
import org.hl7.fhir.dstu3.model.Reference;
import org.hl7.fhir.dstu3.model.Subscription;
import org.hl7.fhir.dstu3.model.ContactPoint.ContactPointSystem;
import org.hl7.fhir.dstu3.model.DocumentReference.DocumentReferenceContentComponent;
import org.hl7.fhir.dstu3.model.DocumentReference.DocumentReferenceRelatesToComponent;
import org.hl7.fhir.dstu3.model.Identifier.IdentifierUse;
import org.hl7.fhir.dstu3.model.Enumeration;
import org.hl7.fhir.dstu3.model.Subscription.SubscriptionChannelComponent;
import org.hl7.fhir.dstu3.model.Subscription.SubscriptionStatus;
import org.hl7.fhir.dstu3.model.codesystems.ContactPointUse;



import ca.uhn.fhir.model.primitive.DateTimeDt;

public class PCC39SubscribeToCarePlanUpdates {
	
	
	//private static final ContactPointSystem ContactPointSystem = null;

	@SuppressWarnings({ "unchecked", "deprecation", "rawtypes", "static-access" })
	public static Subscription createSubscription(){
		
		final Subscription sub1 = new Subscription();
		
		sub1.setId("1.2.40.0.13.1.1.10.10.10.239.20170623112052975.32771");
		
		//Does NOT post meta.profile value to http://fhirtest.uhn.ca/baseDstu3
		Meta meta1 = new Meta();
		meta1.addProfile("http://fhir.nhs.uk/StructureDefinition/ldp-subscription-1-0");
		sub1.setMeta(meta1);
		sub1.setStatus(SubscriptionStatus.REQUESTED);
		sub1.addContact(new ContactPoint().setSystem(ContactPointSystem.PHONE).setValue("ext 4123"));
		
		InstantType dt = new InstantType();
		dt.setValueAsString("2021-01-01T00:00:00Z");
		sub1.setEndElement(dt);
		sub1.setReason("Monitor new neonatal function");
		sub1.setCriteria("Observation?code=http://loinc.org|1975-2");
		sub1.setError("test");
		
		SubscriptionChannelComponent channel = new SubscriptionChannelComponent();
		//Bug: channel type
		//channel.setTypeElement(SubscriptionChannelType.SMS);
		channel.setEndpoint("https://biliwatch.com/customers/mount-auburn-miu/on-result");
		channel.setPayload("application/fhir+xml");
		channel.addHeader("Authorization: Bearer secret-token-abc-123");
		sub1.setChannel(channel);
	
	    sub1.getTagFirstRep().setSystem("http://example.org/fhir/cs/internal").setCode("bili-done");
		

	return sub1;
	

}

}