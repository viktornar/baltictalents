package lt.baltictalents;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Calendar;
import java.util.GregorianCalendar;

@Path("dukes-age")
public class DukesAgeResource {

    public DukesAgeResource() {

    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getText() {
        return String.format("Duke age: %d", getDukeAge());
    }

    @GET
    @Path("json")
//    @Path("{type}")
    @Produces(MediaType.APPLICATION_JSON)
    public DukesAge getJson(/*@PathParam("type") String type*/) {
//        System.out.println(String.format("Got type: %s", type));
        return new DukesAge(getDukeAge());
    }

    private Integer getDukeAge() {
        Calendar dukesBirthday = new GregorianCalendar(1995, Calendar.MAY, 23);
        Calendar now = GregorianCalendar.getInstance();

        int dukesAge = now.get(Calendar.YEAR) - dukesBirthday.get(Calendar.YEAR);
        dukesBirthday.add(Calendar.YEAR, dukesAge);

        if (now.before(dukesBirthday)) {
            dukesAge--;
        }

        return dukesAge;
    }

    public class DukesAge {
        private Integer age;

        DukesAge(Integer age) {
            this.setAge(age);
        }


        public Integer getAge() {
            return age;
        }

        void setAge(Integer age) {
            this.age = age;
        }
    }
}
