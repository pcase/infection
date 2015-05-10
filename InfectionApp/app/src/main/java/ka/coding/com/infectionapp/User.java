package ka.coding.com.infectionapp;

/**
 * Created by pattycase on 5/8/15.
 */
public class User {
    public int version;
    public String name;
    public boolean isUpdated;
    public boolean checkStudents=true;
    public boolean checkCoaches=true;
    public User students[];
    public User coaches[];

    /**
     * User constructor
     * @param name
     * @param version
     */
    public User(String name, int version) {
        this.version = version;
        this.name = name;
        this.isUpdated = false;
    }

    /**
     * getName
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * setName
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * isUpdated
     *
     * @return isUpdated
     */
    public boolean isUpdated() {
        return isUpdated;
    }

    /**
     * setIsUpdated
     * @param isUpdated
     */
    public void setIsUpdated(boolean isUpdated) {
        this.isUpdated = isUpdated;
    }

    /**
     * getStudents
     *
     * @return students
     */
    public User[] getStudents() {
        return students;
    }

    /**
     * setStudents
     *
     * @param students
     */
    public void setStudents(User[] students) {
        this.students = students;
    }

    /**
     * getCoaches
     *
     * @return coaches
     */
    public User[] getCoaches() {
        return coaches;
    }

    /**
     * setCoaches
     *
     * @param coaches
     */
    public void setCoaches(User[] coaches) {
        this.coaches = coaches;
    }

    /**
     * getVersion
     *
     * @return version
     */
    public int getVersion() {
        return version;
    }

    /**
     * setVersion
     *
     * @param version
     */
    public void setVersion(int version) {
        this.version = version;
    }
}

