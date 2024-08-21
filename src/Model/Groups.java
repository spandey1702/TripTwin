package Model;
import java.util.List;

public class Groups 
{
    private String Groupname;
    private String groupPosts;
    private List<String> groupMembers;
    
    public String getGroupname() 
    {
        return Groupname;
    }
    public void setGroupname(String groupname) 
    {
        Groupname = groupname;
    }
    public String getGroupPosts() 
    {
        return groupPosts;
    }
    public void setGroupPosts(String groupPosts) 
    {
        this.groupPosts = groupPosts;
    }
    public List<String> getGroupMembers() 
    {
        return groupMembers;
    }
    public void setGroupMembers(List<String> groupMembers) 
    {
        this.groupMembers = groupMembers;
    }
}
