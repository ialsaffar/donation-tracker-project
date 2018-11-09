package com.capybaras.donationtracker.models;


import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

import static java.lang.Integer.parseInt;

public class User {
    private static int numberOfUsers;

    private int id;
    private String username;
    private String password;
    private String email;
    private UserTypes type;
    private Location location;

    public User(String username, String password, String email, UserTypes type) {
        this(username,password,email,type,null);
    }

    public User(String username,
                String password,
                String email,
                UserTypes type,
                Location location) {
        this(++numberOfUsers, username,password,email,type,location);
    }

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
    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

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

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserTypes getType() {
        return type;
    }

    public void setType(UserTypes type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (this == o) return true;
        User that = (User) o;
        return id == that.id &&
                username.equals(that.username) &&
                email.equals(that.email) &&
                type == that.type;
    }

    @Override
    public int hashCode() {
        return Model.hash(id, username, email, type);
    }

    public void saveAsText(PrintWriter writer) {
        writer.println(id + "\t" + username + "\t" + password + "\t" + email + "\t" + type.getNonCaps());
        if(location != null) {
            location.saveAsTextSansItems(writer);
        }
    }

    public static User parseEntry(String line, String locLine) {
        assert line != null;
        String[] tokens = line.split("\t");
        if (locLine == null) {
            User fromFile = new User(parseInt(tokens[0]),
                    tokens[1],
                    tokens[2],
                    tokens[3],
                    UserTypes.getByName(tokens[4]),
                    null);

            return fromFile;
        }

        Location loc = Location.parseEntry(locLine);
        List<Location> locList = LocationList.getLocations();
        for (int i = 0; i < locList.size(); i++) {
            if ((loc.getPhone().equals(locList.get(i).getPhone())) && (loc.getName().equals(locList.get(i).getName()))) {
                loc = locList.get(i);
                i = locList.size();
            }
        }

        User fromFile = new User(parseInt(tokens[0]),
                                 tokens[1],
                                 tokens[2],
                                 tokens[3],
                                 UserTypes.getByName(tokens[4]),
                                 loc);

        return fromFile;
    }
}
