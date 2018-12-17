- //declare class for dispatcher that extends 
    com.firebase.jobdispatcher.JobService

<service android:name=".SeviceBg" android:exported="false">
    <intent-filter>
        <action android:name="com.firebase.jobdispatcher.ACTION_EXECUTE"/>
    </intent-filter>
</service>

- declare new instance for firebasedispatcher 
 //initialize dispatcher
        FirebaseJobDispatcher jobDispatcher = new FirebaseJobDispatcher(new GooglePlayDriver(this));
        
//Scheduling a simple job
Job myJob = dispatcher.newJobBuilder()
    .setService(MyJobService.class) // the JobService that will be called
    .setTag("my-unique-tag")        // uniquely identifies the job
    .build();
