#include <jni.h>
#include <math.h>
#include <stdbool.h>
#include <unistd.h>

JNIEXPORT jboolean JNICALL
Java_com_example_eynapp_ui_HomePresenter_isPrimeNumberJNI(
        JNIEnv *env, jclass type, jlong number) {

    long i;
    bool isPrime = true;

    if (number % 2 == 0) {
        return false;
    }

    sleep(5);
    long max = (long) sqrt(number);
    for (i = 3; i <= max; i += 2) {
        if (number % i == 0) {
            isPrime = false;
            break;
        }
    }

    return (jboolean) isPrime;
}
