import org.apache.geode.internal.lang.ObjectUtils;
import org.apache.geode.pdx.PdxReader;
import org.apache.geode.pdx.PdxSerializable;
import org.apache.geode.pdx.PdxSerializer;
import org.apache.geode.pdx.PdxWriter;

/**
 * The com.src.dto.Person class is an abstraction modeling a person.
 */

 public class Address implements PdxSerializable, PdxSerializer {

   private static final long serialVersionUID = 42108163264l;

   protected static final String DOB_FORMAT_PATTERN =  "MM/dd/yyyy";

   private String city;
   private String state;
   private String zip;

   public Address() {
   }

   public Address(final String city, final String state,final String zip) {
     this.city = city;
     this.state = state;
     this.zip = zip;
  }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    @Override
   public boolean equals( final Object obj) {
     if (obj ==  this) {
       return true;
    }
     if (!(obj  instanceof Address)) {
       return false;
    }
     final Address that = (Address) obj;
     return (ObjectUtils.equals( this.getCity(), that.getCity())
      && ObjectUtils.equals( this.getState(), that.getState())
      && ObjectUtils.equals( this.getZip(), that.getZip()));
  }

  @Override
   public int hashCode() {
     int hashValue = 17;
    hashValue = 37 * hashValue + ObjectUtils.hashCode(getCity());
    hashValue = 37 * hashValue + ObjectUtils.hashCode(getState());
    hashValue = 37 * hashValue + ObjectUtils.hashCode(getZip());
     return hashValue;
  }

  @Override
   public String toString() {
     final StringBuilder buffer =  new StringBuilder( "{ type = ");
    buffer.append(getClass().getName());
    buffer.append( ", city = ").append(getCity());
    buffer.append( ", state = ").append(getState());
    buffer.append( ", zip = ").append(getZip());
    buffer.append( " }");
     return buffer.toString();
  }

   public void fromData(PdxReader pr) {
    city = pr.readString( "city");
    state = pr.readString( "state");
    zip = pr.readString( "zip");
  }

   public void toData(PdxWriter pw) {
    pw.writeString( "city", city);
    pw.writeString( "state", state);
    pw.writeString( "zip", zip);
  }

    @Override
    public boolean toData(Object o, PdxWriter pw) {
        if(!(o instanceof Address)) {
            return false;
        }
        pw.writeString( "city", city);
        pw.writeString( "state", state);
        pw.writeString( "zip", zip);
        return true;
    }

    @Override
    public Object fromData(Class<?> clazz, PdxReader pr) {
        if(!clazz.equals(Address.class)) {
            return null;
        }
        Address p = new Address();
        p.city = pr.readString( "city");
        p.state = pr.readString( "state");
        p.zip = pr.readString( "zip");
        return p;
    }
}