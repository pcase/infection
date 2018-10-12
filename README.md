# infection
Android app for a Khan Academy take-home project

This is an Android Studio project

Two ways to run the app:
- Run the project from Studio
  - Import the project into Studio
  - Run InfectActivity
  
-Install the app from the command line
  -Use adb install <path to apk ~/InfectionApp/app/build/outputs/apk/app-debug.apk>

Two options:
-total infection: all users are updated to the highest version
-limited infection: only the specified number of users are updated to the highest version

How to use:
-For total infection, choose total infection on the dialog, and all the users will be updated
-For limited infection, choose limited infection from the dialog.  A subsequent dialog will display prompting you for the maximum number of users to infect.  Select a student from the list to be the starting point.  The users starting from that user will be infected.

Notes:
-The highest version number is obtained from the highest version number in the sample user data.
-A user is updated at most once.
-For the limited infection case, the app allows you to select the starting user which was not part of the specification.
-For the limited infection case, the actual number of updated users might be less than the specified maxinum number.  Only the users connected to the starting user are updated, so if, for instance, that user has no students or coaches, only that user will be updated.  
-The updating process will stop when the maximum number of updates is reached, so it is possible that the group of students coached by a coach might be at different version numbers.

Future enhancements:
-For the limited infection case, the app can find the user graph that best matches the maximum number of users to infect.  It could, for instance, find a coach with a group of students whose size is closest to the maximum.  If the maximum is very large, the app could find muliple user graphs whose total number of users is the closest to the maximum.  The app could present the user with a candidate list of users to infect.
-Currently the app will stop the updating process when the maximum is reached.  This can cause a group of users coached by the same person to be at different versions.  The app should update all of a coach's students.  With the previous enhancement, the best-fitting user/users will be chosen for updating.

