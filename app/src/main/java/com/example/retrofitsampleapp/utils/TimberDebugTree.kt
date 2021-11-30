package com.example.retrofitsampleapp.utils

import timber.log.Timber.DebugTree

/**
 * This is used for printing fileName, lineNumber and method name in logs using Timber
 */
class TimberDebugTree : DebugTree() {
    override fun createStackElementTag(element: StackTraceElement): String {
        return String.format(
            "(%s:%s)#%s",
            element.fileName,
            element.lineNumber,
            element.methodName
        )
    }
}