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
import com.google.gson.annotations.SerializedName;

import java.io.IOException;
import com.google.gson.TypeAdapter;
import com.google.gson.JsonElement;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

/**
 * Types of parental responsibility  &lt;br&gt;FREG: Foreldreansvarstype&lt;p&gt;Possible values:&lt;/p&gt;&lt;ul&gt;&lt;li&gt;&lt;i&gt;Joint&lt;/i&gt; - FREG: Felles&lt;/li&gt;&lt;li&gt;&lt;i&gt;Mother&lt;/i&gt; - FREG: Mor&lt;/li&gt;&lt;li&gt;&lt;i&gt;Father&lt;/i&gt; - FREG: Far&lt;/li&gt;&lt;li&gt;&lt;i&gt;CoMother&lt;/i&gt; - FREG: Medmor&lt;/li&gt;&lt;li&gt;&lt;i&gt;Other&lt;/i&gt; - FREG: Andre&lt;/li&gt;&lt;li&gt;&lt;i&gt;Unknown&lt;/i&gt; - FREG: Ukjent&lt;/li&gt;&lt;/ul&gt;
 */
@JsonAdapter(ParentalResponsibilityType.Adapter.class)
public enum ParentalResponsibilityType {
  
  JOINT("Joint"),
  
  MOTHER("Mother"),
  
  FATHER("Father"),
  
  CO_MOTHER("CoMother"),
  
  OTHER("Other"),
  
  UNKNOWN("Unknown");

  private String value;

  ParentalResponsibilityType(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  @Override
  public String toString() {
    return String.valueOf(value);
  }

  public static ParentalResponsibilityType fromValue(String value) {
    for (ParentalResponsibilityType b : ParentalResponsibilityType.values()) {
      if (b.value.equals(value)) {
        return b;
      }
    }
    throw new IllegalArgumentException("Unexpected value '" + value + "'");
  }

  public static class Adapter extends TypeAdapter<ParentalResponsibilityType> {
    @Override
    public void write(final JsonWriter jsonWriter, final ParentalResponsibilityType enumeration) throws IOException {
      jsonWriter.value(enumeration.getValue());
    }

    @Override
    public ParentalResponsibilityType read(final JsonReader jsonReader) throws IOException {
      String value = jsonReader.nextString();
      return ParentalResponsibilityType.fromValue(value);
    }
  }

  public static void validateJsonElement(JsonElement jsonElement) throws IOException {
    String value = jsonElement.getAsString();
    ParentalResponsibilityType.fromValue(value);
  }
}

