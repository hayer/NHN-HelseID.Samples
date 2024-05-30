/*
 * Persontjenesten API - ET
 * The Person API provides information on norwegian citizens for the norwegian public health sector, and is maintained by [Norsk helsenett](https://www.nhn.no/).  An API changelog is available [here](../static/changelog/index.html).  For more documentation and a complete integration guide, see the [NHN developer portal](https://utviklerportal.nhn.no/informasjonstjenester/persontjenesten/). 
 *
 * The version of the OpenAPI document: 2
 * 
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */


package org.openapitools.client.api;

import org.openapitools.client.ApiException;
import org.openapitools.client.model.Gender;
import org.openapitools.client.model.InformationPart;
import org.openapitools.client.model.PersonSearchResult;
import org.openapitools.client.model.PersonStatusType;
import org.openapitools.client.model.ProblemDetails;
import org.openapitools.client.model.SearchMatchCountResult;
import org.openapitools.client.model.StringSearchResult;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * API tests for SearchWithLegalBasisApi
 */
@Disabled
public class SearchWithLegalBasisApiTest {

    private final SearchWithLegalBasisApi api = new SearchWithLegalBasisApi();

    /**
     * Works same way as \&quot;paged-match-list\&quot; regarding search parameters, but returned result is just the total count of matches that would have been returned.  Use it to predict how many matches a search with search/paged-match-list endpoint would have returned without fetching full set of data.
     *
     * See documentation for \&quot;paged-match-list\&quot; for more details.&lt;br /&gt;&lt;br /&gt;&lt;b&gt;Requires HelseId scope:&lt;/b&gt; ReadWithLegalBasis
     *
     * @throws ApiException if the Api call fails
     */
    @Test
    public void apiLegalBasisSearchMatchCountPostTest() throws ApiException {
        String apiVersion = null;
        Boolean includeAinResults = null;
        Integer pageSize = null;
        Integer indexOffset = null;
        String fullName = null;
        String givenName = null;
        String middleName = null;
        String familyName = null;
        String streetAddress = null;
        String postalCode = null;
        String municipalityNumber = null;
        String basicStatisticalUnit = null;
        String birthDateFrom = null;
        String birthDateTo = null;
        Gender gender = null;
        List<PersonStatusType> personStatuses = null;
        SearchMatchCountResult response = api.apiLegalBasisSearchMatchCountPost(apiVersion, includeAinResults, pageSize, indexOffset, fullName, givenName, middleName, familyName, streetAddress, postalCode, municipalityNumber, basicStatisticalUnit, birthDateFrom, birthDateTo, gender, personStatuses);
        // TODO: test validations
    }

    /**
     * Search for a set of persons based on current information. Returns a list with unique person ids, see HgdPerson.Out.OpenApi.Model.Person
     *
     * Search for a set of persons based on current information. Returns a list with unique person ids, see Person&lt;br /&gt;&lt;br /&gt;&lt;b&gt;Requires HelseId scope:&lt;/b&gt; ReadWithLegalBasis
     *
     * @throws ApiException if the Api call fails
     */
    @Test
    public void apiLegalBasisSearchMatchListPostTest() throws ApiException {
        String apiVersion = null;
        String fullName = null;
        String givenName = null;
        String middleName = null;
        String familyName = null;
        String streetAddress = null;
        String postalCode = null;
        String municipalityNumber = null;
        String basicStatisticalUnit = null;
        String birthDateFrom = null;
        String birthDateTo = null;
        Gender gender = null;
        List<PersonStatusType> personStatuses = null;
        List<String> response = api.apiLegalBasisSearchMatchListPost(apiVersion, fullName, givenName, middleName, familyName, streetAddress, postalCode, municipalityNumber, basicStatisticalUnit, birthDateFrom, birthDateTo, gender, personStatuses);
        // TODO: test validations
    }

    /**
     * Search for a set of persons based on current information. Returns a search result with unique person ids, see HgdPerson.Out.OpenApi.Model.Person  The search result is limited to the provided page size and shifted by the provided index offset.
     *
     * Search for a set of persons based on current information. Returns a search result with unique person ids, see Person  together with the results start and end index with can be further used to paginate the potential remainder of the total search result.&lt;br /&gt;&lt;br /&gt;&lt;b&gt;Requires HelseId scope:&lt;/b&gt; ReadWithLegalBasis
     *
     * @throws ApiException if the Api call fails
     */
    @Test
    public void apiLegalBasisSearchPagedMatchListPostTest() throws ApiException {
        String apiVersion = null;
        Boolean includeAinResults = null;
        Integer pageSize = null;
        Integer indexOffset = null;
        String fullName = null;
        String givenName = null;
        String middleName = null;
        String familyName = null;
        String streetAddress = null;
        String postalCode = null;
        String municipalityNumber = null;
        String basicStatisticalUnit = null;
        String birthDateFrom = null;
        String birthDateTo = null;
        Gender gender = null;
        List<PersonStatusType> personStatuses = null;
        StringSearchResult response = api.apiLegalBasisSearchPagedMatchListPost(apiVersion, includeAinResults, pageSize, indexOffset, fullName, givenName, middleName, familyName, streetAddress, postalCode, municipalityNumber, basicStatisticalUnit, birthDateFrom, birthDateTo, gender, personStatuses);
        // TODO: test validations
    }

    /**
     * Search for a limited set of persons based on current information. Returns a search result with max 100 person documents.
     *
     * Search parameters will match current information, historic information will not be searchable.  For addresses, it&#39;s possible to search for norwegian residential, present and shared residence addresses.                Search parameters have the following requirements:  &lt;br&gt;1. A value for either FirstName, MiddleName, LastName or FullName&lt;br&gt;2. Combined across all names, there must be at least three letters in total (whitespace is not counted)&lt;br&gt;3. DateOfBirth OR MunicipalityNumber with at least 4 digits OR StreetName with at least three letters (whitespace is not counted)&lt;br&gt;Some search parameters are also validated individually, see the search parameter description for more details.&lt;br /&gt;&lt;br /&gt;&lt;b&gt;Requires HelseId scope:&lt;/b&gt; ReadWithLegalBasis
     *
     * @throws ApiException if the Api call fails
     */
    @Test
    public void apiLegalBasisSearchPersonPostTest() throws ApiException {
        List<InformationPart> informationParts = null;
        String apiVersion = null;
        Boolean includeHistory = null;
        Boolean includeAinResults = null;
        String birthDate = null;
        Gender gender = null;
        String fullName = null;
        String givenName = null;
        String middleName = null;
        String familyName = null;
        String streetAddress = null;
        String postalCode = null;
        String cityName = null;
        String municipalityNumber = null;
        PersonSearchResult response = api.apiLegalBasisSearchPersonPost(informationParts, apiVersion, includeHistory, includeAinResults, birthDate, gender, fullName, givenName, middleName, familyName, streetAddress, postalCode, cityName, municipalityNumber);
        // TODO: test validations
    }

}
