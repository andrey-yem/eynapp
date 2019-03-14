# Eyn app

## NDK Challenge
1.     If you have never worked with NDK before, Complete this codelab. Besides, play with more examples from here.
a.     This is a C++ example that checks whether a given number is prime or not. Following the example, write a C++ function. Let’s call it is_prime(). Write necessary JNI. Take user input in an Android app and call the C++ is_prime() function to decide whether the input number is prime or not. Print the response as a toast message.

## WhatsApp Challenge
1.     Assume a mock endpoint that returns “Hey, Got your Message!” whenever you POST any string. 
a.     First, make a basic android app that takes a string as user input and if internet is available, directly POST that string and receives the message “Hey, Got your Message!” in return. 
b.     Second, if internet is not available, the app that will “cache” that user input. In offline, the user may send multiple messages and all should be cached. As soon as the internet is available, the app will POST the user input and will clear the “cache”.
c.     Third, would there be any change if the user input is not string, but an image or a short video (e.g. < 10MB)?

## TODO
1. Create App level component, move, move common bits there (JNI init, Network listener, etc)
2. Create proper persistence layer (DB). Then messages will be saved between the launches, when app is killed
3. Unit test, cleanup
4. Regarding uploading big files - there is a new WorkManager class specifically designed for background tasks like that. Haven't had a chance to look into this yet
