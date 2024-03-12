import org.apache.geode.internal.lang.ObjectUtils;
import org.apache.geode.pdx.PdxReader;
import org.apache.geode.pdx.PdxSerializable;
import org.apache.geode.pdx.PdxSerializer;
import org.apache.geode.pdx.PdxWriter;

import java.util.Date;

/**
 * The com.src.dto.Person class is an abstraction modeling a person.
 */

 public class PersonValue implements PdxSerializable, PdxSerializer {

   private static final long serialVersionUID = 42108163364l;

   private Long id;

   private Long age;

   private Address address;

   public PersonValue() {
  }

   public PersonValue(final Long id) {
     this.id = id;
  }

   public PersonValue(final Long id,final Long age,final Address address) {
     this.id = id;
     this.age = age;
     this.address = address;
  }

   public Long getId() {
     return id;
  }

   public void setId( final Long id) {
     this.id = id;
  }

    public Long getAge() {
        return age;
    }

    public void setAge(Long age) {
        this.age = age;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Address getAddress() {
        return address;
    }

    @Override
   public boolean equals( final Object obj) {
     if (obj ==  this) {
       return true;
    }

     if (!(obj  instanceof PersonValue)) {
       return false;
    }

     final PersonValue that = (PersonValue) obj;

     return (ObjectUtils.equals( this.getId(), that.getId())
      && (ObjectUtils.equals( this.getAddress(), that.getAddress())
      && ObjectUtils.equals( this.getAge(), that.getAge())));
  }

  @Override
   public int hashCode() {
     int hashValue = 17;
    hashValue = 37 * hashValue + ObjectUtils.hashCode(getId());
    hashValue = 37 * hashValue + ObjectUtils.hashCode(getAge());
    hashValue = 37 * hashValue + ObjectUtils.hashCode(getAddress());
     return hashValue;
  }

  @Override
   public String toString() {
     final StringBuilder buffer =  new StringBuilder( "{ type = ");
    buffer.append(getClass().getName());
    buffer.append( ", id = ").append(getId());
    buffer.append( ", age = ").append(getAge());
    buffer.append( ", address = ").append(getAddress());
    buffer.append( " }");
     return buffer.toString();
  }

   public void fromData(PdxReader pr) {

    id = pr.readLong( "id");
    age = pr.readLong( "age");
    address = (Address) pr.readObject( "address");
  }

   public void toData(PdxWriter pw) {
    pw.writeLong( "id", id);
    pw.writeLong( "age", getAge());
    pw.writeObject( "address", getAddress());
  }

    @Override
    public boolean toData(Object o, PdxWriter pw) {
        if(!(o instanceof PersonValue)) {
            return false;
        }
        pw.writeLong( "id", id);
        pw.writeLong( "age", age);
        pw.writeObject( "address", address);
        return true;
    }

    @Override
    public Object fromData(Class<?> clazz, PdxReader pr) {
        if(!clazz.equals(PersonValue.class)) {
            return null;
        }
        PersonValue p = new PersonValue();
        p.id = pr.readLong( "id");
        p.age = pr.readLong( "age");
        p.address = (Address)pr.readObject("address");
        return p;
    }
}