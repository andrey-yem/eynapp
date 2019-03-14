cmake_minimum_required(VERSION 3.4.1)

add_library(eyn-jni SHARED
        eyn-jni.c)

# Include libraries needed for eyn-jni lib
target_link_libraries(eyn-jni
                      android
                      log)
