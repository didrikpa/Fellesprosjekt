package Model;

import java.util.ArrayList;

// Klasse for grupper som brukere er en del av.
public class Group {
    private Integer groupID;
    private ArrayList<User> users;
    private String groupName;

//  Instanserer brukerlisten i gruppa.
    public Group(){
    	users = new ArrayList<User>();
    	groupName = null;
    	
    	}

//    Returnerer alle brukere i gruppen.
    public ArrayList<User> getUsers() {
    	return users;
    	}

//  Setter alle brukere i gruppen med argument som en ArrayList<User>.
    public void setUsers(ArrayList<User> users) {
    	this.users = users;
    	}

//  Legger til en bruker i gruppen.
    public void addUser(User user){
    	users.add(user);
    	}

//  Hvis brukeren er en del av gruppen, returnerer denne brukeren.
    public User getUser(User user){
        if(users.contains(user)){
            for (int i = 0; i < users.size(); i++) {
                if(user.equals(users.get(i))){
                    return users.get(i);
                }
            }
        }
        return null;
    }

//  Returnerer gruppeIDen.
    public Integer getGroupID() {
    	return groupID;
    	}


//  Setter gruppeIDen som et Integer.
    public void setGroupID(Integer groupID) {
    	this.groupID = groupID;
    	}
    
    public String getGroupName() {
    	return this.groupName;
    	}


    public void setGroupName(String gn) {
    	this.groupName = gn;
    	}
    @Override
    public String toString() {
    	String mid = "";
    	for(User user : users){
    		mid += user.firstname + " " + user.lastname + ", " ;
    	}
    	mid = mid.substring(0, mid.length()-2);
    	return groupName + " - " + mid;
    }
}
