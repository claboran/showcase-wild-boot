/*
 * JBoss, Home of Professional Open Source
 * Copyright 2013, Red Hat, Inc. and/or its affiliates, and individual
 * contributors by the @authors tag. See the copyright.txt in the
 * distribution for a full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package de.laboranowitsch.wild.test;

import java.io.File;
import java.util.Arrays;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.junit.Test;
import org.junit.runner.RunWith;
import de.laboranowitsch.wild.data.MemberRepository;
import de.laboranowitsch.wild.model.Member;
import de.laboranowitsch.wild.service.MemberRegistration;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@RunWith(Arquillian.class)
@Slf4j
public class MemberRegistrationTest {
    
	@Deployment
    public static Archive<?> createTestArchive() {
    	
    	File[] libs = Maven.resolver().loadPomFromFile("pom.xml").importRuntimeAndTestDependencies().resolve().withTransitivity().asFile();
    	
    	WebArchive res = ShrinkWrap.create(WebArchive.class, "test.war")
                .addPackages(true, "de.laboranowitsch.wild")
                .addAsResource("META-INF/test-persistence.xml", "META-INF/persistence.xml")
                .addAsWebInfResource("test-beans.xml", "beans.xml")
                // Deploy our test datasource
                .addAsWebInfResource("test-ds.xml");
    	
	    Arrays.asList(libs).stream().forEach((elem) -> res.addAsLibraries(elem));     
        
	    return res;
    }

    @Inject
    private MemberRegistration memberRegistration;
    @Inject
    private MemberRepository memberRepository;
    
    @Test
    public void testRegister() throws Exception {
        
    	Member newMember = new Member();
        newMember.setName("Jane Doe");
        newMember.setEmail("jane@mailinator.com");
        newMember.setPhoneNumber("2125551234");
        memberRegistration.register(newMember);
        
        assertThat(newMember.getId(), is(notNullValue()));
        log.info(newMember.getName() + " was persisted with id " + newMember.getId());
        
        newMember = memberRepository.findById(newMember.getId());
        
        assertThat("Jane Doe", is(equalTo(newMember.getName())));
        assertThat("jane@mailinator.com", is(equalTo(newMember.getEmail())));
        assertThat("2125551234", is(equalTo(newMember.getPhoneNumber())));
        log.info("New member persisted and fetched: {}", newMember);
        
    }

}
