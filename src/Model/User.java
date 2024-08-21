package Model;
public class User
{
  private int userid;
  public String getProfilePicturepath() {
    return profilePicturepath;
}

public void setProfilePicturepath(String profilePicturepath) {
    this.profilePicturepath = profilePicturepath;
}
private String userName;
  private String password;
  private String firstName;
  private String lastName;
  private String address;
  private String email;
  private String bio;
  private int age;
  private String profilePicturepath;

 public User(int userid)
{
    this.userid=userid;
} 

public User(String userName,String firstName,String lastName,String address,String email,String bio, int age)
{
    
    this.userName=userName;
    this.firstName=firstName;
    this.lastName=lastName;
    this.address=address;
    this.email=email;
    this.bio=bio;
    this.age=age;
}



public User() {
    
}

public String getUserName() {
    return userName;
}

public String getPassword() {
    return password;
}

public String getFirstName() {
    return firstName;
}

public String getLastName() {
    return lastName;
}

public String getAddress() {
    return address;
}

public String getEmail() {
    return email;
}

public String getBio() {
    return bio;
}

public int getAge() {
    return age;

}
public int getUserid()
{
    return userid;
}


}