/*
 * Persontjenesten API - ET
 * ## Introduction  The Person API is a copy of the [National Population Register (\"Folkeregisteret\")](https://www.skatteetaten.no/en/person/national-registry/about/) serving the norwegian health sector, maintained by Norsk helsenett. More detailed information on data coming from the National Population Register is also available in Norwegian here: [Information Model](https://skatteetaten.github.io/folkeregisteret-api-dokumentasjon/informasjonsmodell/)  More detailed information about the Person API service, including how to get access, is documented here: [Persontjenesten](https://www.nhn.no/samhandlingsplattform/grunndata/persontjenesten)  ## Disclaimer  The Person API is under continuous development and will be subject to changes without notice. The changes will follow semantic versioning to prevent breaking changes. Legacy versions will be available for 6 months before they are discontinued. We encourage consumers to follow our changelog in order to keep track of any changes. Send feedback and questions to [persontjenesten@nhn.no](mailto:persontjenesten@nhn.no)  ## Changelog  See [Changelog](../static/changelog/index.html)  ## Synthetic test data  Data in our test environment is using synthetic test data coming from the [Synthetic National Register](https://skatteetaten.github.io/testnorge-tenor-dokumentasjon/kilder#syntetisk-folkeregister). To browse the data available, you can log in using ID-porten at [Testnorge](https://testdata.skatteetaten.no/web/testnorge/)  ## Authentication and authorization  This API uses [HelseID](https://www.nhn.no/samhandlingsplattform/helseid) for authentication and authorization. To use the API you will need to have a valid HelseID token with a valid scope.  There are two scopes available to consume resources from the Person API: - **ReadWithLegalBasis** | Scope: `nhn:hgd-persontjenesten-api/read-with-legal-basis`    This scope provides read access to information in the authorization bundle \"public with legal basis\" (aka statutory authority).    For version 0.5 name was `nhn:hgd-persontjenesten-api/read`  - **ReadNoLegalBasis** | Scope: `nhn:hgd-persontjenesten-api/read-no-legal-basis`    This scope provides read access to information in the public bundle \"public with**out** legal basis\". 
 *
 * The version of the OpenAPI document: 1.5
 * 
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */


package sf.nhn.helseid.demo.persontjenesten.model;

import com.google.gson.*;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.openapitools.jackson.nullable.JsonNullable;
import sf.nhn.helseid.demo.persontjenesten.JSON;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Set;

/**
 * &lt;br&gt;FREG: Matrikkeladresse.Matrikkelnummer
 */
@ApiModel(description = "<br>FREG: Matrikkeladresse.Matrikkelnummer")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2022-10-21T11:48:55.741350900+02:00[Europe/Oslo]")
public class CadastralNumber {
  public static final String SERIALIZED_NAME_MUNICIPALITY_NUMBER = "municipalityNumber";
  @SerializedName(SERIALIZED_NAME_MUNICIPALITY_NUMBER)
  private String municipalityNumber;

  public static final String SERIALIZED_NAME_MUNICIPALITY_LAND_NUMBER = "municipalityLandNumber";
  @SerializedName(SERIALIZED_NAME_MUNICIPALITY_LAND_NUMBER)
  private Long municipalityLandNumber;

  public static final String SERIALIZED_NAME_TITLE_NUMBER = "titleNumber";
  @SerializedName(SERIALIZED_NAME_TITLE_NUMBER)
  private Long titleNumber;

  public static final String SERIALIZED_NAME_LEASEHOLD_NUMBER = "leaseholdNumber";
  @SerializedName(SERIALIZED_NAME_LEASEHOLD_NUMBER)
  private Long leaseholdNumber;

  public CadastralNumber() {
  }

  public CadastralNumber municipalityNumber(String municipalityNumber) {
    
    this.municipalityNumber = municipalityNumber;
    return this;
  }

   /**
   * A number identifying a municipality or  municipality-like area  &lt;br&gt;FREG: Kommunenummer
   * @return municipalityNumber
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(example = "5053", value = "A number identifying a municipality or  municipality-like area  <br>FREG: Kommunenummer")

  public String getMunicipalityNumber() {
    return municipalityNumber;
  }


  public void setMunicipalityNumber(String municipalityNumber) {
    this.municipalityNumber = municipalityNumber;
  }


  public CadastralNumber municipalityLandNumber(Long municipalityLandNumber) {
    
    this.municipalityLandNumber = municipalityLandNumber;
    return this;
  }

   /**
   * &lt;br&gt;FREG: Gårdsnummer
   * @return municipalityLandNumber
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "<br>FREG: Gårdsnummer")

  public Long getMunicipalityLandNumber() {
    return municipalityLandNumber;
  }


  public void setMunicipalityLandNumber(Long municipalityLandNumber) {
    this.municipalityLandNumber = municipalityLandNumber;
  }


  public CadastralNumber titleNumber(Long titleNumber) {
    
    this.titleNumber = titleNumber;
    return this;
  }

   /**
   * &lt;br&gt;FREG: Bruksnummer
   * @return titleNumber
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "<br>FREG: Bruksnummer")

  public Long getTitleNumber() {
    return titleNumber;
  }


  public void setTitleNumber(Long titleNumber) {
    this.titleNumber = titleNumber;
  }


  public CadastralNumber leaseholdNumber(Long leaseholdNumber) {
    
    this.leaseholdNumber = leaseholdNumber;
    return this;
  }

   /**
   * &lt;br&gt;FREG: Festenummer
   * @return leaseholdNumber
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "<br>FREG: Festenummer")

  public Long getLeaseholdNumber() {
    return leaseholdNumber;
  }


  public void setLeaseholdNumber(Long leaseholdNumber) {
    this.leaseholdNumber = leaseholdNumber;
  }



  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CadastralNumber cadastralNumber = (CadastralNumber) o;
    return Objects.equals(this.municipalityNumber, cadastralNumber.municipalityNumber) &&
        Objects.equals(this.municipalityLandNumber, cadastralNumber.municipalityLandNumber) &&
        Objects.equals(this.titleNumber, cadastralNumber.titleNumber) &&
        Objects.equals(this.leaseholdNumber, cadastralNumber.leaseholdNumber);
  }

  private static <T> boolean equalsNullable(JsonNullable<T> a, JsonNullable<T> b) {
    return a == b || (a != null && b != null && a.isPresent() && b.isPresent() && Objects.deepEquals(a.get(), b.get()));
  }

  @Override
  public int hashCode() {
    return Objects.hash(municipalityNumber, municipalityLandNumber, titleNumber, leaseholdNumber);
  }

  private static <T> int hashCodeNullable(JsonNullable<T> a) {
    if (a == null) {
      return 1;
    }
    return a.isPresent() ? Arrays.deepHashCode(new Object[]{a.get()}) : 31;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CadastralNumber {\n");
    sb.append("    municipalityNumber: ").append(toIndentedString(municipalityNumber)).append("\n");
    sb.append("    municipalityLandNumber: ").append(toIndentedString(municipalityLandNumber)).append("\n");
    sb.append("    titleNumber: ").append(toIndentedString(titleNumber)).append("\n");
    sb.append("    leaseholdNumber: ").append(toIndentedString(leaseholdNumber)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }


  public static HashSet<String> openapiFields;
  public static HashSet<String> openapiRequiredFields;

  static {
    // a set of all properties/fields (JSON key names)
    openapiFields = new HashSet<String>();
    openapiFields.add("municipalityNumber");
    openapiFields.add("municipalityLandNumber");
    openapiFields.add("titleNumber");
    openapiFields.add("leaseholdNumber");

    // a set of required properties/fields (JSON key names)
    openapiRequiredFields = new HashSet<String>();
  }

 /**
  * Validates the JSON Object and throws an exception if issues found
  *
  * @param jsonObj JSON Object
  * @throws IOException if the JSON Object is invalid with respect to CadastralNumber
  */
  public static void validateJsonObject(JsonObject jsonObj) throws IOException {
      if (jsonObj == null) {
        if (CadastralNumber.openapiRequiredFields.isEmpty()) {
          return;
        } else { // has required fields
          throw new IllegalArgumentException(String.format("The required field(s) %s in CadastralNumber is not found in the empty JSON string", CadastralNumber.openapiRequiredFields.toString()));
        }
      }

      Set<Entry<String, JsonElement>> entries = jsonObj.entrySet();
      // check to see if the JSON string contains additional fields
      for (Entry<String, JsonElement> entry : entries) {
        if (!CadastralNumber.openapiFields.contains(entry.getKey())) {
          throw new IllegalArgumentException(String.format("The field `%s` in the JSON string is not defined in the `CadastralNumber` properties. JSON: %s", entry.getKey(), jsonObj.toString()));
        }
      }
      if ((jsonObj.get("municipalityNumber") != null && !jsonObj.get("municipalityNumber").isJsonNull()) && !jsonObj.get("municipalityNumber").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `municipalityNumber` to be a primitive type in the JSON string but got `%s`", jsonObj.get("municipalityNumber").toString()));
      }
  }

  public static class CustomTypeAdapterFactory implements TypeAdapterFactory {
    @SuppressWarnings("unchecked")
    @Override
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
       if (!CadastralNumber.class.isAssignableFrom(type.getRawType())) {
         return null; // this class only serializes 'CadastralNumber' and its subtypes
       }
       final TypeAdapter<JsonElement> elementAdapter = gson.getAdapter(JsonElement.class);
       final TypeAdapter<CadastralNumber> thisAdapter
                        = gson.getDelegateAdapter(this, TypeToken.get(CadastralNumber.class));

       return (TypeAdapter<T>) new TypeAdapter<CadastralNumber>() {
           @Override
           public void write(JsonWriter out, CadastralNumber value) throws IOException {
             JsonObject obj = thisAdapter.toJsonTree(value).getAsJsonObject();
             elementAdapter.write(out, obj);
           }

           @Override
           public CadastralNumber read(JsonReader in) throws IOException {
             JsonObject jsonObj = elementAdapter.read(in).getAsJsonObject();
             validateJsonObject(jsonObj);
             return thisAdapter.fromJsonTree(jsonObj);
           }

       }.nullSafe();
    }
  }

 /**
  * Create an instance of CadastralNumber given an JSON string
  *
  * @param jsonString JSON string
  * @return An instance of CadastralNumber
  * @throws IOException if the JSON string is invalid with respect to CadastralNumber
  */
  public static CadastralNumber fromJson(String jsonString) throws IOException {
    return JSON.getGson().fromJson(jsonString, CadastralNumber.class);
  }

 /**
  * Convert an instance of CadastralNumber to an JSON string
  *
  * @return JSON string
  */
  public String toJson() {
    return JSON.getGson().toJson(this);
  }
}

