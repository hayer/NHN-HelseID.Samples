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


package org.openapitools.client.model;

import java.util.Objects;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.util.Arrays;
import org.openapitools.jackson.nullable.JsonNullable;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.openapitools.client.JSON;

/**
 * Used if the persons shared residence is unknown  &lt;br&gt;Remarks:   May be missing, only one of the address elements are used. See ResidentialAddress  Freg: UkjentBosted
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2024-05-30T07:50:48.244395087Z[Etc/UTC]", comments = "Generator version: 7.7.0-SNAPSHOT")
public class SharedResidenceUnknownResidence {
  public static final String SERIALIZED_NAME_MUNICIPALITY_NUMBER = "municipalityNumber";
  @SerializedName(SERIALIZED_NAME_MUNICIPALITY_NUMBER)
  private String municipalityNumber;

  public static final String SERIALIZED_NAME_MUNICIPALITY_NAME = "municipalityName";
  @SerializedName(SERIALIZED_NAME_MUNICIPALITY_NAME)
  private String municipalityName;

  public static final String SERIALIZED_NAME_COUNTY_NUMBER = "countyNumber";
  @SerializedName(SERIALIZED_NAME_COUNTY_NUMBER)
  private String countyNumber;

  public static final String SERIALIZED_NAME_COUNTY_NAME = "countyName";
  @SerializedName(SERIALIZED_NAME_COUNTY_NAME)
  private String countyName;

  public SharedResidenceUnknownResidence() {
  }

  public SharedResidenceUnknownResidence municipalityNumber(String municipalityNumber) {
    this.municipalityNumber = municipalityNumber;
    return this;
  }

   /**
   * A four digit code identifying a municipality or municipality-like area.  Municipalities are defined by SSB in classification 131.  !:https://www.ssb.no/klass/klassifikasjoner/131&lt;br&gt;FREG: Kommunenummer
   * @return municipalityNumber
  **/
  @javax.annotation.Nullable
  public String getMunicipalityNumber() {
    return municipalityNumber;
  }

  public void setMunicipalityNumber(String municipalityNumber) {
    this.municipalityNumber = municipalityNumber;
  }


  public SharedResidenceUnknownResidence municipalityName(String municipalityName) {
    this.municipalityName = municipalityName;
    return this;
  }

   /**
   * The name of the municipality. Some municipalities also include names in Sámi languages.  &lt;br&gt;SSB: Kommunenavn
   * @return municipalityName
  **/
  @javax.annotation.Nullable
  public String getMunicipalityName() {
    return municipalityName;
  }

  public void setMunicipalityName(String municipalityName) {
    this.municipalityName = municipalityName;
  }


  public SharedResidenceUnknownResidence countyNumber(String countyNumber) {
    this.countyNumber = countyNumber;
    return this;
  }

   /**
   * A two digit code identifying a county.  Counties are defined by SSB in classification 104.  !:https://www.ssb.no/klass/klassifikasjoner/104&lt;br&gt;SSB: Fylkesnummer
   * @return countyNumber
  **/
  @javax.annotation.Nullable
  public String getCountyNumber() {
    return countyNumber;
  }

  public void setCountyNumber(String countyNumber) {
    this.countyNumber = countyNumber;
  }


  public SharedResidenceUnknownResidence countyName(String countyName) {
    this.countyName = countyName;
    return this;
  }

   /**
   * The name of the county. Some counties also include names in Sámi languages.  &lt;br&gt;SSB: Fylkesnavn
   * @return countyName
  **/
  @javax.annotation.Nullable
  public String getCountyName() {
    return countyName;
  }

  public void setCountyName(String countyName) {
    this.countyName = countyName;
  }



  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SharedResidenceUnknownResidence sharedResidenceUnknownResidence = (SharedResidenceUnknownResidence) o;
    return Objects.equals(this.municipalityNumber, sharedResidenceUnknownResidence.municipalityNumber) &&
        Objects.equals(this.municipalityName, sharedResidenceUnknownResidence.municipalityName) &&
        Objects.equals(this.countyNumber, sharedResidenceUnknownResidence.countyNumber) &&
        Objects.equals(this.countyName, sharedResidenceUnknownResidence.countyName);
  }

  private static <T> boolean equalsNullable(JsonNullable<T> a, JsonNullable<T> b) {
    return a == b || (a != null && b != null && a.isPresent() && b.isPresent() && Objects.deepEquals(a.get(), b.get()));
  }

  @Override
  public int hashCode() {
    return Objects.hash(municipalityNumber, municipalityName, countyNumber, countyName);
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
    sb.append("class SharedResidenceUnknownResidence {\n");
    sb.append("    municipalityNumber: ").append(toIndentedString(municipalityNumber)).append("\n");
    sb.append("    municipalityName: ").append(toIndentedString(municipalityName)).append("\n");
    sb.append("    countyNumber: ").append(toIndentedString(countyNumber)).append("\n");
    sb.append("    countyName: ").append(toIndentedString(countyName)).append("\n");
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
    openapiFields.add("municipalityName");
    openapiFields.add("countyNumber");
    openapiFields.add("countyName");

    // a set of required properties/fields (JSON key names)
    openapiRequiredFields = new HashSet<String>();
  }

 /**
  * Validates the JSON Element and throws an exception if issues found
  *
  * @param jsonElement JSON Element
  * @throws IOException if the JSON Element is invalid with respect to SharedResidenceUnknownResidence
  */
  public static void validateJsonElement(JsonElement jsonElement) throws IOException {
      if (jsonElement == null) {
        if (!SharedResidenceUnknownResidence.openapiRequiredFields.isEmpty()) { // has required fields but JSON element is null
          throw new IllegalArgumentException(String.format("The required field(s) %s in SharedResidenceUnknownResidence is not found in the empty JSON string", SharedResidenceUnknownResidence.openapiRequiredFields.toString()));
        }
      }

      Set<Map.Entry<String, JsonElement>> entries = jsonElement.getAsJsonObject().entrySet();
      // check to see if the JSON string contains additional fields
      for (Map.Entry<String, JsonElement> entry : entries) {
        if (!SharedResidenceUnknownResidence.openapiFields.contains(entry.getKey())) {
          throw new IllegalArgumentException(String.format("The field `%s` in the JSON string is not defined in the `SharedResidenceUnknownResidence` properties. JSON: %s", entry.getKey(), jsonElement.toString()));
        }
      }
        JsonObject jsonObj = jsonElement.getAsJsonObject();
      if ((jsonObj.get("municipalityNumber") != null && !jsonObj.get("municipalityNumber").isJsonNull()) && !jsonObj.get("municipalityNumber").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `municipalityNumber` to be a primitive type in the JSON string but got `%s`", jsonObj.get("municipalityNumber").toString()));
      }
      if ((jsonObj.get("municipalityName") != null && !jsonObj.get("municipalityName").isJsonNull()) && !jsonObj.get("municipalityName").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `municipalityName` to be a primitive type in the JSON string but got `%s`", jsonObj.get("municipalityName").toString()));
      }
      if ((jsonObj.get("countyNumber") != null && !jsonObj.get("countyNumber").isJsonNull()) && !jsonObj.get("countyNumber").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `countyNumber` to be a primitive type in the JSON string but got `%s`", jsonObj.get("countyNumber").toString()));
      }
      if ((jsonObj.get("countyName") != null && !jsonObj.get("countyName").isJsonNull()) && !jsonObj.get("countyName").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `countyName` to be a primitive type in the JSON string but got `%s`", jsonObj.get("countyName").toString()));
      }
  }

  public static class CustomTypeAdapterFactory implements TypeAdapterFactory {
    @SuppressWarnings("unchecked")
    @Override
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
       if (!SharedResidenceUnknownResidence.class.isAssignableFrom(type.getRawType())) {
         return null; // this class only serializes 'SharedResidenceUnknownResidence' and its subtypes
       }
       final TypeAdapter<JsonElement> elementAdapter = gson.getAdapter(JsonElement.class);
       final TypeAdapter<SharedResidenceUnknownResidence> thisAdapter
                        = gson.getDelegateAdapter(this, TypeToken.get(SharedResidenceUnknownResidence.class));

       return (TypeAdapter<T>) new TypeAdapter<SharedResidenceUnknownResidence>() {
           @Override
           public void write(JsonWriter out, SharedResidenceUnknownResidence value) throws IOException {
             JsonObject obj = thisAdapter.toJsonTree(value).getAsJsonObject();
             elementAdapter.write(out, obj);
           }

           @Override
           public SharedResidenceUnknownResidence read(JsonReader in) throws IOException {
             JsonElement jsonElement = elementAdapter.read(in);
             validateJsonElement(jsonElement);
             return thisAdapter.fromJsonTree(jsonElement);
           }

       }.nullSafe();
    }
  }

 /**
  * Create an instance of SharedResidenceUnknownResidence given an JSON string
  *
  * @param jsonString JSON string
  * @return An instance of SharedResidenceUnknownResidence
  * @throws IOException if the JSON string is invalid with respect to SharedResidenceUnknownResidence
  */
  public static SharedResidenceUnknownResidence fromJson(String jsonString) throws IOException {
    return JSON.getGson().fromJson(jsonString, SharedResidenceUnknownResidence.class);
  }

 /**
  * Convert an instance of SharedResidenceUnknownResidence to an JSON string
  *
  * @return JSON string
  */
  public String toJson() {
    return JSON.getGson().toJson(this);
  }
}

