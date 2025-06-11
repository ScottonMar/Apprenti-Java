This is a well-structured beginner project that demonstrates good understanding of classes and objects. I like that you put the pin generator in its own class. You did a nice job at separation of concerns!

**Overall: Passed**

Minor Tweaks:

* Method names could be more consistent. For sample, you use `promptForLockerNumber()` and `promptForPin()` , I would expect `confirm()` to be `promptForConfirmation()`.
* You have two Scanner objects, one in InputHelper and the other in LockerManager. Try to have only one Scanner object in terminal applications because it can cause funky issues.
* In general, manager or service classes shouldn't be talking to your inputHelper. The methods should receive the data they need via parameters. This will be helpful when we get into unit testing where you shouldn't have terminal input in a test.

