package com.capybaras.donationtracker.models;


import java.io.PrintWriter;
import java.util.List;
import static java.lang.Integer.parseInt;

/**
 * User Class
 */
public class User {
    private static int numberOfUsers;

    private final int id;
    private String username;
    private String password;
    private String email;
    private UserTypes type;
    private Location location;

    /**
     * Constructor of User
     * @param username the username of the user
     * @param password the password for the user account
     * @param email the email of the user
     * @param type the type of user
     */
    public User(String username, String password, String email, UserTypes type) {
        this(username,password,email,type,null);
    }

    /**
     * Constructor II for the user
     * @param username the username for the user
     * @param password the password of the user account
     * @param email the email of the user
     * @param type the type of user
     * @param location where the user is located
     */
    public User(String username,
                String password,
                String email,
                UserTypes type,
                Location location) {
        this(numberOfUsers + 1, username,password,email,type,location);
        numberOfUsers++;
    }

    /**
     * Constructor III for User
     * @param id the user's ID
     * @param username the user's username
     * @param password the user's password
     * @param email the user's email
     * @param type the type of user
     * @param location the user's location
     */
    public User(int id,
                String username,
                String password,
                String email,
                UserTypes type,
                Location location) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.type = type;
        this.location = location;
    }

    /**
     * Gets the location of the user
     * @return the location
     */
    public Location getLocation() {
        return location;
    }

    /**
     * Sets the location of the user
     * @param location the new location
     */
    public void setLocation(Location location) {
        this.location = location;
    }

    /**
     * Sets the location of the user by the locationID
     * @param locationId the location's ID
     */
    public void setLocation(int locationId) {
        location = getLocationByID(locationId);
    }

    private Location getLocationByID(int locationId) {
        List<Location> locations = Model.getInstance().getLocations();
        for (Location l: locations) {
            if (locationId == l.getKey()) {
                return l;
            }
        }
        throw new IllegalArgumentException("No location matching ID");
    }

    /**
     * Gets the user's ID
     * @return the ID
     */
    public int getId() {
        return id;
    }

    /**
     * Gets the user's username
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the user's username
     * @param username the new username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets the password of the user
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password of the user's account
     * @param password the new password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets the email of the user
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the user's email
     * @param email the new email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets the type of user
     * @return the userType
     */
    public UserTypes getType() {
        return type;
    }

    /**
     * Sets the user type
     * @param type the new user type
     */
    public void setType(UserTypes type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if ((o == null) || (getClass() != o.getClass())) {
            return false;
        }
        if (this == o) {
            return true;
        }
        User that = (User) o;
        return (id == that.id) &&
                username.equals(that.username) &&
                email.equals(that.email) &&
                (type == that.type);
    }

    @Override
    public int hashCode() {
        return Model.hash(id, username, email, type);
    }

    /**
     * Saves the User's info as a text
     * @param writer the print writer
     */
    public void saveAsText(PrintWriter writer) {
        writer.println(id + "\t" + username + "\t" + password + "\t" + email + "\t" +
                type.getNonCaps());
        if(location != null) {
            location.saveAsTextSansItems(writer);
        }
    }

    /**
     * parses through a line and gets the user's info
     * @param line the line to parse
     * @param locLine the location of the line
     * @return the User object (With all it's info)
     */
    public static User parseEntry(String line, String locLine) {
        assert line != null;
        String[] tokens = line.split("\t");
        if (locLine == null) {

            return new User(parseInt(tokens[0]),
                    tokens[1],
                    tokens[2],
                    tokens[3],
                    UserTypes.getByName(tokens[4]),
                    null);
        }

        boolean hasChanged = false;
        Location loc = Location.parseEntry(locLine);
        List<Location> locList = LocationList.getLocations();
        for (int i = 0; i < locList.size(); i++) {
            if ((loc.getPhone().equals(locList.get(i).getPhone())) && (loc.getName()
                    .equals(locList.get(i).getName()))) {
                loc = locList.get(i);
                i = locList.size();
                hasChanged = true;
            }
        }
        if (!hasChanged) {
            LocationList.addLocation(loc);
        }

        return new User(parseInt(tokens[0]),
                tokens[1],
                tokens[2],
                tokens[3],
                UserTypes.getByName(tokens[4]),
                loc);
    }
}
