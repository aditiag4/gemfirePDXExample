import org.apache.geode.internal.lang.ObjectUtils;
import org.apache.geode.pdx.PdxReader;
import org.apache.geode.pdx.PdxSerializable;
import org.apache.geode.pdx.PdxSerializer;
import org.apache.geode.pdx.PdxWriter;

import java.util.Date;

/**
 * The com.src.dto.Person class is an abstraction modeling a person.
 */


 public class Person implements PdxSerializable, PdxSerializer {

   private static final long serialVersionUID = 42108163264l;

   protected static final String DOB_FORMAT_PATTERN =  "MM/dd/yyyy";

   private Long id;

   private Long birthDate;

   private Gender gender;

   private String firstName;
   private String middleName;
   private String lastName;

   public Person() {
  }

   public Person(final Long id) {
     this.id = id;
  }

   public Person(final String firstName, final String lastName) {
     this.firstName = firstName;
     this.lastName = lastName;
  }

   public Person(final Long id, final String firstName, final String middleName, final String lastName, Long date, Gender gender) {
     this.id = id;
     this.firstName = firstName;
     this.middleName = middleName;
     this.lastName = lastName;
     this.birthDate = date;
     this.gender = gender;
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

   public Long getBirthDate() {
     return birthDate;
  }

   public void setBirthDate( final Long birthDate) {
     this.birthDate = birthDate;
  }

   public Gender getGender() {
     return gender;
  }

   public void setGender( final Gender gender) {
     this.gender = gender;
  }

  @Override
   public boolean equals( final Object obj) {
     if (obj ==  this) {
       return true;
    }

     if (!(obj  instanceof Person)) {
       return false;
    }

     final Person that = (Person) obj;

     return (ObjectUtils.equals( this.getId(), that.getId())
      || (ObjectUtils.equals( this.getBirthDate(), that.getBirthDate())
      && ObjectUtils.equals( this.getLastName(), that.getLastName())
      && ObjectUtils.equals( this.getFirstName(), that.getFirstName())));
  }

  @Override
   public int hashCode() {
     int hashValue = 17;
    hashValue = 37 * hashValue + ObjectUtils.hashCode(getId());
    hashValue = 37 * hashValue + ObjectUtils.hashCode(getBirthDate());
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
    buffer.append( ", birthDate = ").append(getBirthDate());
    buffer.append( ", gender = ").append(getGender());
    buffer.append( " }");
     return buffer.toString();
  }

   public void fromData(PdxReader pr) {

    id = pr.readLong( "id");
    firstName = pr.readString( "firstName");
    middleName = pr.readString( "middleName");
    lastName = pr.readString( "lastName");
    birthDate = pr.readLong( "birthDate");
    gender = (Gender)pr.readObject( "gender");
  }

   public void toData(PdxWriter pw) {
    pw.writeLong( "id", id);
    pw.writeString( "firstName", firstName);
    pw.writeString( "middleName", middleName);
    pw.writeString( "lastName", lastName);
    pw.writeLong( "birthDate", birthDate);
    pw.writeObject( "gender", gender);
  }

    @Override
    public boolean toData(Object o, PdxWriter pw) {
        if(!(o instanceof Person)) {
            return false;
        }
        pw.writeLong( "id", id);
        pw.writeString( "firstName", firstName);
        pw.writeString( "middleName", middleName);
        pw.writeString( "lastName", lastName);
        pw.writeLong( "birthDate", birthDate);
        pw.writeObject( "gender", gender);
        return true;
    }

    @Override
    public Object fromData(Class<?> clazz, PdxReader pr) {
        if(!clazz.equals(Person.class)) {
            return null;
        }
        Person p = new Person();
        p.id = pr.readLong( "id");
        p.firstName = pr.readString( "firstName");
        p.middleName = pr.readString( "middleName");
        p.lastName = pr.readString( "lastName");
        p.birthDate = pr.readLong( "birthDate");
        p.gender = (Gender)pr.readObject( "gender");
        return p;
    }
}