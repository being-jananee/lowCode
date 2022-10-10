package com.bizBrainz.external.annotations.encryption;

import com.bizBrainz.external.models.BizbrainzDomain;
import lombok.Getter;
import lombok.Setter;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class EncryptionHandlerTest {

    @Test
    public void testFindCandidateFieldsForType_AllPossibleCombinations() {
        EncryptionHandler encryptionHandler = new EncryptionHandler();

        final TestDomain testDomain = new TestDomain();
        testDomain.setEncryptedSubDomainWithValue(new TestSubDomain());
        testDomain.setTestSubDomainWithoutEncryption(new TestSubDomainWithoutEncryption());
        testDomain.setPolymorphicSubDomain(new PolymorphicSubdomain1());
        testDomain.setTestSubDomainListWithElements(List.of(new TestSubDomain()));
        testDomain.setPolymorphicSubDomainListWithElements(List.of(new PolymorphicSubdomain1()));
        testDomain.setTestSubDomainMapWithElements(Map.of("Test1", new TestSubDomain()));
        testDomain.setPolymorphicSubDomainMapWithElements(Map.of("Test2", new PolymorphicSubdomain1()));

        final List<CandidateField> candidateFieldsForType = encryptionHandler.findCandidateFieldsForType(testDomain);
        assertNotNull(candidateFieldsForType);

        // For encrypted string
        final Optional<CandidateField> encryptedString = candidateFieldsForType
                .stream()
                .filter(x -> x.getType().equals(CandidateField.Type.ANNOTATED_FIELD))
                .findFirst();
        assertTrue(encryptedString.isPresent());
        assertEquals("encryptedInDomain", encryptedString.get().getField().getName());

        // For encrypted subtype when the subtype is null
        final Optional<CandidateField> encryptedSubDomainWithoutValue = candidateFieldsForType
                .stream()
                .filter(x -> x.getType().equals(CandidateField.Type.BIZBRAINZ_FIELD_UNKNOWN))
                .findFirst();
        assertTrue(encryptedSubDomainWithoutValue.isPresent());
        assertEquals("encryptedSubDomainWithoutValue", encryptedSubDomainWithoutValue.get().getField().getName());

        // For encrypted subtype when the subtype is not null and has encrypted field
        final Optional<CandidateField> encryptedSubDomainWithValue = candidateFieldsForType
                .stream()
                .filter(x -> x.getType().equals(CandidateField.Type.BIZBRAINZ_FIELD_KNOWN))
                .findFirst();
        assertTrue(encryptedSubDomainWithValue.isPresent());
        assertEquals("encryptedSubDomainWithValue", encryptedSubDomainWithValue.get().getField().getName());

        // For encrypted subtype when the subtype is polymorphic
        final Optional<CandidateField> polymorphicSubDomain = candidateFieldsForType
                .stream()
                .filter(x -> x.getType().equals(CandidateField.Type.BIZBRAINZ_FIELD_POLYMORPHIC))
                .findFirst();
        assertTrue(polymorphicSubDomain.isPresent());
        assertEquals("polymorphicSubDomain", polymorphicSubDomain.get().getField().getName());

        // For encrypted list
        final Optional<CandidateField> testSubDomainListWithElements = candidateFieldsForType
                .stream()
                .filter(x -> x.getType().equals(CandidateField.Type.BIZBRAINZ_COLLECTION_KNOWN))
                .findFirst();
        assertTrue(testSubDomainListWithElements.isPresent());
        assertEquals("testSubDomainListWithElements", testSubDomainListWithElements.get().getField().getName());

        // For encrypted list when the list is polymorphic and null
        final Optional<CandidateField> polymorphicSubDomainListWithoutElements = candidateFieldsForType
                .stream()
                .filter(x -> x.getType().equals(CandidateField.Type.BIZBRAINZ_COLLECTION_UNKNOWN))
                .findFirst();
        assertTrue(polymorphicSubDomainListWithoutElements.isPresent());
        assertEquals("polymorphicSubDomainListWithoutElements", polymorphicSubDomainListWithoutElements.get().getField().getName());

        // For encrypted list when the list is polymorphic and null
        final Optional<CandidateField> polymorphicSubDomainListWithElements = candidateFieldsForType
                .stream()
                .filter(x -> x.getType().equals(CandidateField.Type.BIZBRAINZ_COLLECTION_POLYMORPHIC))
                .findFirst();
        assertTrue(polymorphicSubDomainListWithElements.isPresent());
        assertEquals("polymorphicSubDomainListWithElements", polymorphicSubDomainListWithElements.get().getField().getName());

        // For encrypted map
        final Optional<CandidateField> testSubDomainMapWithElements = candidateFieldsForType
                .stream()
                .filter(x -> x.getType().equals(CandidateField.Type.BIZBRAINZ_MAP_KNOWN))
                .findFirst();
        assertTrue(testSubDomainMapWithElements.isPresent());
        assertEquals("testSubDomainMapWithElements", testSubDomainMapWithElements.get().getField().getName());

        // For encrypted map when the map is polymorphic and null
        final Optional<CandidateField> polymorphicSubDomainMapWithoutElements = candidateFieldsForType
                .stream()
                .filter(x -> x.getType().equals(CandidateField.Type.BIZBRAINZ_MAP_UNKNOWN))
                .findFirst();
        assertTrue(polymorphicSubDomainMapWithoutElements.isPresent());
        assertEquals("polymorphicSubDomainMapWithoutElements", polymorphicSubDomainMapWithoutElements.get().getField().getName());

        // For encrypted map when the map is polymorphic and not null
        final Optional<CandidateField> polymorphicSubDomainMapWithElements = candidateFieldsForType
                .stream()
                .filter(x -> x.getType().equals(CandidateField.Type.BIZBRAINZ_MAP_POLYMORPHIC))
                .findFirst();
        assertTrue(polymorphicSubDomainMapWithElements.isPresent());
        assertEquals("polymorphicSubDomainMapWithElements", polymorphicSubDomainMapWithElements.get().getField().getName());

        assertEquals(10, candidateFieldsForType.size());
    }

    @Test
    public void testConvertEncryption_AllPossibleCombinations() {
        EncryptionHandler encryptionHandler = new EncryptionHandler();

        final TestDomain testDomain = new TestDomain();

        testDomain.setEncryptedInDomain("String");

        testDomain.setNotEncrypted("String");

        final TestSubDomainWithoutEncryption testSubDomainWithoutEncryption = new TestSubDomainWithoutEncryption();
        testSubDomainWithoutEncryption.setNotEncryptedInSubDomain("String");
        testDomain.setTestSubDomainWithoutEncryption(testSubDomainWithoutEncryption);

        final TestSubDomain testSubDomain1 = new TestSubDomain();
        testSubDomain1.setEncryptedInSubDomain("String");
        testDomain.setEncryptedSubDomainWithValue(testSubDomain1);

        final PolymorphicSubdomain1 polymorphicSubdomain1 = new PolymorphicSubdomain1();
        polymorphicSubdomain1.setEncryptedInPolymorphicSubdomain1("String");
        testDomain.setPolymorphicSubDomain(polymorphicSubdomain1);

        final TestSubDomain testSubDomain2 = new TestSubDomain();
        testSubDomain2.setEncryptedInSubDomain("String");
        testDomain.setTestSubDomainListWithElements(List.of(testSubDomain2));

        final PolymorphicSubdomain1 polymorphicSubdomain2 = new PolymorphicSubdomain1();
        polymorphicSubdomain2.setEncryptedInPolymorphicSubdomain1("String");
        testDomain.setPolymorphicSubDomainListWithElements(List.of(polymorphicSubdomain2));

        final TestSubDomain testSubDomain3 = new TestSubDomain();
        testSubDomain3.setEncryptedInSubDomain("String");
        testDomain.setTestSubDomainMapWithElements(Map.of("Test1", testSubDomain3));

        final PolymorphicSubdomain1 polymorphicSubdomain3 = new PolymorphicSubdomain1();
        polymorphicSubdomain3.setEncryptedInPolymorphicSubdomain1("String");
        testDomain.setPolymorphicSubDomainMapWithElements(Map.of("Test2", polymorphicSubdomain3));

        final boolean b = encryptionHandler.convertEncryption(testDomain, "Encrypted-"::concat);

        assertTrue(b);
        assertEquals("Encrypted-String", testDomain.getEncryptedInDomain());
        assertEquals("String", testDomain.getNotEncrypted());
        assertEquals("String", testDomain.getTestSubDomainWithoutEncryption().getNotEncryptedInSubDomain());
        assertEquals("Encrypted-String", testDomain.getEncryptedSubDomainWithValue().getEncryptedInSubDomain());
        assertEquals("Encrypted-String", ((PolymorphicSubdomain1) testDomain.getPolymorphicSubDomain()).getEncryptedInPolymorphicSubdomain1());
        assertEquals("Encrypted-String", testDomain.getTestSubDomainListWithElements().get(0).getEncryptedInSubDomain());
        assertEquals("Encrypted-String", ((PolymorphicSubdomain1) testDomain.getPolymorphicSubDomainListWithElements().get(0)).getEncryptedInPolymorphicSubdomain1());
        assertEquals("Encrypted-String", testDomain.getTestSubDomainMapWithElements().get("Test1").getEncryptedInSubDomain());
        assertEquals("Encrypted-String", ((PolymorphicSubdomain1) testDomain.getPolymorphicSubDomainMapWithElements().get("Test2")).getEncryptedInPolymorphicSubdomain1());

    }

    @Test
    public void testConvertEncryption_EmptySetFirstWithNonEncryptedFields() {
        EncryptionHandler encryptionHandler = new EncryptionHandler();

        TestDomainWithSet testDomain = new TestDomainWithSet();

        testDomain.setSet(new HashSet<>());

        boolean b = encryptionHandler.convertEncryption(testDomain, "Encrypted-"::concat);
        assertTrue(b); //First time field will be detected as BIZBRAINZ_COLLECTION_UNKNOWN

        BizbrainzTestSubDomainWithoutEncryption testSubDomainWithoutEncryption = new BizbrainzTestSubDomainWithoutEncryption();
        testSubDomainWithoutEncryption.setNotEncryptedInSubDomain("String");

        testDomain.setSet(new HashSet<>());
        testDomain.getSet().add(testSubDomainWithoutEncryption);

        b = encryptionHandler.convertEncryption(testDomain, "Encrypted-"::concat);
        assertFalse(b); //Second time field will be removed from cache but returns true
    }

    @Test
    public void testConvertEncryption_FilledSetFirstWithNonEncryptedFields() {
        EncryptionHandler encryptionHandler = new EncryptionHandler();

        TestDomainWithSet testDomain = new TestDomainWithSet();

        BizbrainzTestSubDomainWithoutEncryption testSubDomainWithoutEncryption = new BizbrainzTestSubDomainWithoutEncryption();
        testSubDomainWithoutEncryption.setNotEncryptedInSubDomain("String");

        testDomain.setSet(new HashSet<>());
        testDomain.getSet().add(testSubDomainWithoutEncryption);

        boolean b = encryptionHandler.convertEncryption(testDomain, "Encrypted-"::concat);
        assertFalse(b);
    }

    @Getter
    @Setter
    static class TestDomain implements BizbrainzDomain {

        // For an annotated field, we should straight up recognize this as an encrypted type
        @Encrypted
        String encryptedInDomain;

        // For non-Bizbrainz types that are not annotated, we should skip the fields
        String notEncrypted;

        // For Bizbrainz types that do not have encrypted fields in them, we should skip the fields
        TestSubDomainWithoutEncryption testSubDomainWithoutEncryption;

        // For Bizbrainz types that are null, we should recognize it as unknown
        TestSubDomain encryptedSubDomainWithoutValue;

        // For Bizbrainz types that are not null, and have encrypted fields, we should recognize it as a known field
        TestSubDomain encryptedSubDomainWithValue;

        // For Bizbrainz types that are not null, and are polymorphic, we should recognize it as a polymorphic field
        PolymorphicSubDomain polymorphicSubDomain;

        // For lists of Bizbrainz types that do not have encrypted fields, we should skip the fields
        List<TestSubDomainWithoutEncryption> testSubDomainWithoutEncryptionList;

        // For lists of Bizbrainz types that have encrypted fields, we should recognize it as a known list
        List<TestSubDomain> testSubDomainListWithElements;

        // For lists of Bizbrainz types (polymorphic or not) that do not have any elements, we should recognize it as unknown list types
        List<PolymorphicSubDomain> polymorphicSubDomainListWithoutElements;

        // For lists of polymorphic Bizbrainz types that have elements, we should recognize it as polymorphic list types
        List<PolymorphicSubDomain> polymorphicSubDomainListWithElements;

        // For maps of Bizbrainz types that do not have encrypted fields, we should skip the fields
        Map<String, TestSubDomainWithoutEncryption> testSubDomainWithoutEncryptionMap;

        // For maps of Bizbrainz types that have encrypted fields, we should recognize it as a known map
        Map<String, TestSubDomain> testSubDomainMapWithElements;

        // For maps of Bizbrainz types (polymorphic or not) that do not have any elements, we should recognize it as unknown map types
        Map<String, PolymorphicSubDomain> polymorphicSubDomainMapWithoutElements;

        // For maps of polymorphic Bizbrainz types that have elements, we should recognize it as polymorphic map types
        Map<String, PolymorphicSubDomain> polymorphicSubDomainMapWithElements;

        // TODO List<NonBizbrainzType>
        // TODO List<List<NonBizbrainzType>>
        // TODO List<List<BizbrainzTypeWithoutEncryptedFields>>
        // TODO List<List<BizbrainzTypeWithEncryptedFields>>
        // TODO List<List<BizbrainzTypeWithoutElements>>
        // TODO List<List<BizbrainzPolymorphicTypeWithElements>>
        // TODO Map<Map<NonBizbrainzType>>
        // TODO Map<Map<BizbrainzTypeWithoutEncryptedFields>>
        // TODO Map<Map<BizbrainzTypeWithEncryptedFields>>
        // TODO Map<Map<BizbrainzTypeWithoutElements>>
        // TODO Map<Map<BizbrainzPolymorphicTypeWithElements>>
        // TODO Just for kicks List<Map<BizbrainzTypeWhichHasAListOfEncryptedBizbrainzType>>>
    }

    @Getter
    @Setter
    static class TestDomainWithSet implements BizbrainzDomain {
        //this list will be created innitially empty
        Set<BizbrainzTestSubDomainWithoutEncryption> set;
    }

    @Getter
    @Setter
    static class TestSubDomainWithoutEncryption implements BizbrainzDomain {
        String notEncryptedInSubDomain;
    }

    @Getter
    @Setter
    static class TestSubDomain implements BizbrainzDomain {

        @Encrypted
        String encryptedInSubDomain;
    }

    static abstract class PolymorphicSubDomain implements BizbrainzDomain {
    }

    @Getter
    @Setter
    static class PolymorphicSubdomain1 extends PolymorphicSubDomain {

        @Encrypted
        String encryptedInPolymorphicSubdomain1;
    }
}