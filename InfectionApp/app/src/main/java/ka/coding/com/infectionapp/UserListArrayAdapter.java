package ka.coding.com.infectionapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.List;

/**
 * Created by pattycase on 5/8/15.
 */
public class UserListArrayAdapter extends ArrayAdapter<User> {
    private final Context context;
    private final User[] users;

    /**
     * UserListArrayAdapter constructor
     * @param context
     * @param users
     */
    public UserListArrayAdapter(Context context, User users[]) {
        super(context, R.layout.user_list_item, users);
        this.context = context;
        this.users = users;
    }

    /**
     * getView
     * @param position
     * @param convertView
     * @param parent
     * @return
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.user_list_item, parent, false);
        TextView userName = (TextView) rowView.findViewById(R.id.userText);
        User u = users[position];
        userName.setText(u.getName());
        TextView version = (TextView) rowView.findViewById(R.id.userVersion);
        version.setText(Integer.toString(u.getVersion()));
        return rowView;
    }
}
