package ka.coding.com.infectionapp;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class InfectActivity extends ListActivity {
    private static final String TAG = "InfectActivity";
    private UserListArrayAdapter adapter;
    private User users[];
    private ListView listView;
    private Button updateButton;
    private int maximumToUpdate;
    private int updatedCount = 0;
    private int highestVersion = -1;
    private int numberOfUsers = 0;
    private User selectedUser=null;

    /**
     * onCreate
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_infect);

        // Load the user data
        users = loadData();
        highestVersion = getHighestVersion(users);
        adapter = new UserListArrayAdapter(this, users);
        listView = (ListView)findViewById(android.R.id.list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                view.setSelected(true);
                selectedUser = (User) listView.getItemAtPosition(position);
                limited_infection(selectedUser);
                adapter.notifyDataSetChanged();
                if (updatedCount > 0) {
                    Toast.makeText(getApplicationContext(),
                            updatedCount + " users were updated to version " + highestVersion,
                            Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(),
                            "No users were updated",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Add the header to the listview
        LayoutInflater inflater = getLayoutInflater();
        ViewGroup header = (ViewGroup) inflater.inflate(R.layout.header, listView, false);
        listView.addHeaderView(header, null, false);

        // Dialog for specifying total or limited update
        AlertDialog updateDialog;

        // Strings to Show In Dialog with Radio Buttons
        final CharSequence[] items = {" Total update "," Limited update "};

        // Creating and Building the Dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Select the update type");
        builder.setSingleChoiceItems(items, -1, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int item) {
                switch (item) {
                    case 0:
                        doTotalInfection();
                        break;
                    case 1:
                        doLimitedInfection();
                        break;

                }
                dialog.dismiss();
            }
        });
        updateDialog = builder.create();
        updateDialog.show();
    }

    /**
     * loadData loads the sample user data
     *
     * @return User[]
     */
    public User[] loadData() {
        User userList[] = new User[15];

        User coach1 = new User("Mr. Wondolowski", 0);
        User coach2 = new User("Miss Nakamura", 1);
        User coach3 = new User("Mr. Beckham", 2);

        User coachList1[] = new User[1];
        coachList1[0] = coach1;

        User coachList2[] = new User[2];
        coachList2[0] = coach1;
        coachList2[1] = coach2;

        User coachList3[] = new User[1];
        coachList3[0] = coach3;

        User student1 = new User("An", 2);
        student1.setCoaches(coachList2);

        User student2 = new User("Makoto", 1);
        student2.setCoaches(coachList1);

        User student3 = new User("Marta", 3);
        student3.setCoaches(coachList1);

        User student4 = new User("Carlos", 0);
        student4.setCoaches(coachList2);

        User student5 = new User("Betty", 1);
        student5.setCoaches(coachList2);

        User student6 = new User("Dheeman", 2);
        student6.setCoaches(coachList3);

        User student7 = new User("Jia", 3);
        student7.setCoaches(coachList3);

        User student8 = new User("Jesse", 1);
        student8.setCoaches(coachList3);

        User student9 = new User("Sarup", 2);
        student9.setCoaches(coachList3);

        User student10 = new User("Ronaldo", 1);
        student10.setCoaches(coachList3);

        User student11 = new User("Mei", 0);
        student11.setCoaches(coachList3);

        User student12 = new User("Riya", 1);
        student12.setCoaches(coachList3);

        User studentList1[] = new User[3];
        studentList1[0] = student1;
        studentList1[1] = student2;
        studentList1[2] = student3;

        coach1.setStudents(studentList1);

        User studentList2[] = new User[3];
        studentList2[0] = student1;
        studentList2[1] = student4;
        studentList2[2] = student5;

        coach2.setStudents(studentList2);

        User studentList3[] = new User[7];
        studentList3[0] = student6;
        studentList3[1] = student7;
        studentList3[2] = student8;
        studentList3[3] = student9;
        studentList3[4] = student10;
        studentList3[5] = student11;
        studentList3[6] = student12;

        coach3.setStudents(studentList3);

        userList[0]=student1;
        userList[1]=student2;
        userList[2]=student3;
        userList[3]=student4;
        userList[4]=student5;
        userList[5]=student6;
        userList[6]=student7;
        userList[7]=student8;
        userList[8]=student9;
        userList[9]=student10;
        userList[10]=student11;
        userList[11]=student12;
        userList[12]=coach1;
        userList[13]=coach2;
        userList[14]=coach3;

        numberOfUsers = userList.length;

        return (userList);
    }

    /**
     * doTotalInfection handles the total infection case
     */
    public void doTotalInfection() {
        total_infection(users);
        adapter.notifyDataSetChanged();
        Toast.makeText(getApplicationContext(),
                updatedCount + " users were updated to version " + highestVersion,
                Toast.LENGTH_SHORT).show();
    }

    /**
     * doLimitedInfection handles the limited infection case
     */
    public void doLimitedInfection() {
        // Display dialog to allow user to input the maximum number of users to infect
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Enter the maximum number of users to infect");
        final EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(input);

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                maximumToUpdate = Integer.parseInt(input.getText().toString());
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.show();
    }

    /**
     * total_infection updates each user in the list and his students and coaches if applicable
     * for the total infection case
     *
     * @param userList
     */
    public void total_infection(User userList[]) {
        updatedCount = 0;
        if (userList.length == 0) {
            return;
        }
        for (User user : userList) {
            if (user.getVersion() < highestVersion) {
                Log.d(TAG, user.name + " : " + "Updating version " + user.getVersion() + " to " + highestVersion);
                user.setVersion(highestVersion);
                updatedCount++;
            }
            if (user.getStudents() != null && user.checkStudents) {
                for (User student : user.getStudents()) {
                    if (student != null && !student.isUpdated) {
                            updateUserTotal(student);
                    }
                }
                user.checkStudents = false;
            }
            if (user.getCoaches() != null && user.checkCoaches) {
                for (User coach : user.getCoaches()) {
                    if (coach != null && !coach.isUpdated) {
                        updateUserTotal(coach);

                    }
                }
                user.checkCoaches = false;
            }
        }
    }

    /**
     * updateUserTotal update one user for the total infection case
     * @param user
     */
    public void updateUserTotal(User user) {
        if (user == null) {
            Toast.makeText(getApplicationContext(),
                    "No users to update",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        if (user.getVersion() < highestVersion && !user.isUpdated) {
            Log.d(TAG, user.name + " : " + "Updating version " + user.getVersion() + " to " + highestVersion);
            user.setVersion(highestVersion);
            updatedCount++;

            if (user.getStudents() != null && user.checkStudents) {
                for (User student : user.getStudents()) {
                    updateUserTotal(student);
                }
                user.checkStudents = false;
            }
            if (user.getCoaches() != null && user.checkCoaches) {
                for (User coach : user.getCoaches()) {
                    updateUserTotal(coach);
                }
                user.checkCoaches = false;
            }
        }
    }

    /**
     * limited_infection update the user and his students and coaches if applicable for the limited infection case
     * @param user
     */
    public void limited_infection(User user) {
        updatedCount = 0;

        if (user == null) {
            Toast.makeText(getApplicationContext(),
                    "Please select a user",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        if (!user.isUpdated) {
            updateUserLimited(user);
        }
    }

    /**
     * updateUserLimited update one user for limited infection
     * @param user
     */
    public void updateUserLimited(User user) {
        if (user == null) {
            return;
        }

        if (updatedCount >= maximumToUpdate) {
            Toast.makeText(getApplicationContext(),
                    "Maximum number of users were updated",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        if (!user.isUpdated) {
            user.setIsUpdated(true);
            if (updatedCount < maximumToUpdate) {
                if (user.getVersion() < highestVersion) {
                    Log.d(TAG, user.name + " : " + "Updating version " + user.getVersion() + " to " + highestVersion);
                    user.setVersion(highestVersion);
                    updatedCount++;
                }

                if (user.getStudents() != null && user.checkStudents) {
                    for (User student : user.getStudents()) {
                        updateUserLimited(student);
                    }
                    user.checkStudents = false;
                }
                if (user.getCoaches() != null && user.checkCoaches) {
                    for (User coach : user.getCoaches()) {
                        updateUserLimited(coach);
                    }
                    user.checkCoaches = false;
                }
            } else {
                Log.d(TAG, "User " + user.name + " not updated.  Maximum number of users to update reached.");
            }
        }
    }

    /**
     * getHighestVersion
     *
     * @return highest version number
     */
    private int getHighestVersion(User userList[]) {
        int highestVersionNumber = -1;
        for (User user : userList) {
            if (user.getVersion() > highestVersionNumber) {
                highestVersionNumber = user.getVersion();
            }
        }
        return highestVersionNumber;
    }
}
