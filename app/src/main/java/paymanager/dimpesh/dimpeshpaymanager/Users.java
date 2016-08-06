package paymanager.dimpesh.dimpeshpaymanager;

/**
 * Created by DIMPESH on 8/6/2016.
 */

public class Users
{
    int id;
    float amt;
    String username,email,phno,password;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getAmt() {
        return amt;
    }

    public void setAmt(float amt) {
        this.amt = amt;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhno() {
        return phno;
    }

    public void setPhno(String phno) {
        this.phno = phno;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // TODO : remove the to String method as not needed yet
    @Override
    public String toString()
    {
        return this.getId()+"\n"+this.getUsername()+"\n"+this.getAmt()+"\n"+this.getPassword()+"\n";
    }
}
