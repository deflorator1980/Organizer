package company;

import java.util.ArrayList;

/**
 * Created by a on 23.12.2014.
 */
public class Storage {
    public String id;
    public String name;
    public String post;
    public String company;
    public String email;
    public ArrayList <String> phones;



    public String getPost() {
        return post;
    }
    public void setPost(String post) {
        this.post = post;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getCompany() {
        return company;
    }
    public void setCompany(String company) {
        this.company = company;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public ArrayList<String> getPhones() {
        return phones;
    }
    public void setPhones(ArrayList<String> phones) {
        this.phones = phones;
    }

    @Override
    public String toString() {
        return "Storage [id=" + id + ", name=" + name + ", post=" + post
                + ", company=" + company + ", email=" + email + ", phones="
                + phones + "]";
    }


}
