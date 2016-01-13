package com.nitish.telegram.response;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

@Generated("org.jsonschema2pojo")
public class DictionaryResponse {

@SerializedName("Word")
@Expose
private String Word;
@SerializedName("PartOfSpeech")
@Expose
private String PartOfSpeech;
@SerializedName("Forms")
@Expose
private List<Object> Forms = new ArrayList<Object>();
@SerializedName("Definitions")
@Expose
private List<String> Definitions = new ArrayList<String>();

/**
* 
* @return
* The Word
*/
public String getWord() {
return Word;
}

/**
* 
* @param Word
* The Word
*/
public void setWord(String Word) {
this.Word = Word;
}

/**
* 
* @return
* The PartOfSpeech
*/
public String getPartOfSpeech() {
return PartOfSpeech;
}

/**
* 
* @param PartOfSpeech
* The PartOfSpeech
*/
public void setPartOfSpeech(String PartOfSpeech) {
this.PartOfSpeech = PartOfSpeech;
}

/**
* 
* @return
* The Forms
*/
public List<Object> getForms() {
return Forms;
}

/**
* 
* @param Forms
* The Forms
*/
public void setForms(List<Object> Forms) {
this.Forms = Forms;
}

/**
* 
* @return
* The Definitions
*/
public List<String> getDefinitions() {
return Definitions;
}

/**
* 
* @param Definitions
* The Definitions
*/
public void setDefinitions(List<String> Definitions) {
this.Definitions = Definitions;
}

@Override
public String toString() {
return ToStringBuilder.reflectionToString(this);
}

@Override
public int hashCode() {
return new HashCodeBuilder().append(Word).append(PartOfSpeech).append(Forms).append(Definitions).toHashCode();
}

@Override
public boolean equals(Object other) {
if (other == this) {
return true;
}
if ((other instanceof DictionaryResponse) == false) {
return false;
}
DictionaryResponse rhs = ((DictionaryResponse) other);
return new EqualsBuilder().append(Word, rhs.Word).append(PartOfSpeech, rhs.PartOfSpeech).append(Forms, rhs.Forms).append(Definitions, rhs.Definitions).isEquals();
}

}