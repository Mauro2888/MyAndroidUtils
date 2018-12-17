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

//OR 

//Scheduling a complext  job
Bundle myExtrasBundle = new Bundle();
myExtrasBundle.putString("some_key", "some_value");

Job myJob = dispatcher.newJobBuilder()
    // the JobService that will be called
    .setService(MyJobService.class)
    // uniquely identifies the job
    .setTag("my-unique-tag")
    // one-off job
    .setRecurring(false)
    // don't persist past a device reboot
    .setLifetime(Lifetime.UNTIL_NEXT_BOOT)
    // start between 0 and 60 seconds from now
    .setTrigger(Trigger.executionWindow(0, 60))
    // don't overwrite an existing job with the same tag
    .setReplaceCurrent(false)
    // retry with exponential backoff
    .setRetryStrategy(RetryStrategy.DEFAULT_EXPONENTIAL)
    // constraints that need to be satisfied for the job to run
    .setConstraints(
        // only run on an unmetered network
        Constraint.ON_UNMETERED_NETWORK,
        // only run when the device is charging
        Constraint.DEVICE_CHARGING
    )
    .setExtras(myExtrasBundle)
    .build();

dispatcher.mustSchedule(myJob);

//Cancelling a job
dispatcher.cancel("my-unique-tag");
//Cancelling all jobs
dispatcher.cancelAll();
