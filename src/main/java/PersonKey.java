import org.apache.geode.internal.lang.ObjectUtils;
import org.apache.geode.pdx.PdxReader;
import org.apache.geode.pdx.PdxSerializable;
import org.apache.geode.pdx.PdxSerializer;
import org.apache.geode.pdx.PdxWriter;

import java.util.Date;

/**
 * The com.src.dto.Person class is an abstraction modeling a person.
 */

 public class PersonKey  implements PdxSerializable, PdxSerializer {

   private static final long serialVersionUID = 42108163264l;

   protected static final String DOB_FORMAT_PATTERN =  "MM/dd/yyyy";

   private Long id;
   private String firstName;
   private String middleName;
   private String lastName;

   public PersonKey() {
   }

   public PersonKey( final Long id) {
     this.id = id;
  }

   public PersonKey( final String firstName,  final String lastName) {
     this.firstName = firstName;
     this.lastName = lastName;
  }

   public PersonKey( final Long id,  final String firstName,  final String middleName,  final String lastName) {
     this.id = id;
     this.firstName = firstName;
     this.middleName = middleName;
     this.lastName = lastName;
  }

   public Long getId() {
     return id;
  }

   public void setId( final Long id) {
     this.id = id;
  }

   public String getFirstName() {
     return firstName;
  }

   public void setFirstName( final String firstName) {
     this.firstName = firstName;
  }

   public String getLastName() {
     return lastName;
  }

   public void setLastName( final String lastName) {
     this.lastName = lastName;
  }

   public String getMiddleName() {
     return middleName;
  }

   public void setMiddleName( final String middleName) {
     this.middleName = middleName;
  }

  @Override
   public boolean equals( final Object obj) {
     if (obj ==  this) {
       return true;
    }

     if (!(obj  instanceof PersonKey)) {
       return false;
    }

     final PersonKey that = (PersonKey) obj;

     return (ObjectUtils.equals( this.getId(), that.getId())
      && ObjectUtils.equals( this.getLastName(), that.getLastName())
      && ObjectUtils.equals( this.getFirstName(), that.getFirstName()));
  }

  @Override
   public int hashCode() {
     int hashValue = 17;
    hashValue = 37 * hashValue + ObjectUtils.hashCode(getId());
    hashValue = 37 * hashValue + ObjectUtils.hashCode(getLastName());
    hashValue = 37 * hashValue + ObjectUtils.hashCode(getFirstName());
     return hashValue;
  }

  @Override
   public String toString() {
     final StringBuilder buffer =  new StringBuilder( "{ type = ");
    buffer.append(getClass().getName());
    buffer.append( ", id = ").append(getId());
    buffer.append( ", firstName = ").append(getFirstName());
    buffer.append( ", middleName = ").append(getMiddleName());
    buffer.append( ", lastName = ").append(getLastName());
    buffer.append( " }");
     return buffer.toString();
  }

   public void fromData(PdxReader pr) {

    id = pr.readLong( "id");
    firstName = pr.readString( "firstName");
    middleName = pr.readString( "middleName");
    lastName = pr.readString( "lastName");
  }

   public void toData(PdxWriter pw) {
    pw.writeLong( "id", id);
    pw.writeString( "firstName", firstName);
    pw.writeString( "middleName", middleName);
    pw.writeString( "lastName", lastName);
  }

    @Override
    public boolean toData(Object o, PdxWriter pw) {
        if(!(o instanceof PersonKey)) {
            return false;
        }
        pw.writeLong( "id", id);
        pw.writeString( "firstName", firstName);
        pw.writeString( "middleName", middleName);
        pw.writeString( "lastName", lastName);
        return true;
    }

    @Override
    public Object fromData(Class<?> clazz, PdxReader pr) {
        if(!clazz.equals(PersonKey.class)) {
            return null;
        }
        PersonKey p = new PersonKey();
        p.id = pr.readLong( "id");
        p.firstName = pr.readString( "firstName");
        p.middleName = pr.readString( "middleName");
        p.lastName = pr.readString( "lastName");
        return p;
    }
}