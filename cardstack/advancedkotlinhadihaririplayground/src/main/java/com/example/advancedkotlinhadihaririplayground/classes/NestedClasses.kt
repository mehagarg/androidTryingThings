package com.example.advancedkotlinhadihaririplayground.classes

class DirectoryExplorer(val user: String){
    /**
     * what we got is a nested class.
     * if you dont want main to access this class, define it private.
     *
     * Lets say I dont want to constantly pass parameters to the fun,
     * can I access properties of the outer class?
     *
     *
     * private class PermissionCheck()
     */
    class PermissionCheck(){
        fun validatePermission(user: String) {
        }

        /**
         * I cannot access constructor parameter user because
         * Nested class by default does not allow access of properties
         * of outer class.
         */
        fun validatePermission(){
//            if(user)
        }
    }

    /***
     * If I want to access the properites of the outer class, I need to define
     * it as an inner class. As soon as I make it an inner class I can access properties
     * of the outer class.
     *
     * Notice that DirectoryExplorer.PermissionChecker has gone red.
     * Why is that? Because Inner class is a part of the instance
     * of the DirectoryExplorer class and can be accessed as a property
     * and not directly referring the class.
     */
    inner class PermissionCheckInner(){
        fun validatePermission(){
            if(user != "Hadi") {

            }
        }
    }

    fun listFolder(folder: String, user: String){
        val permissionCheck = PermissionCheck()
        permissionCheck.validatePermission(user)
    }
}

fun main(){
    val de = DirectoryExplorer("Hadi")
    // It allows me to access nested classes.
    // If I dont want to I can declare this class private.
    val pc = DirectoryExplorer.PermissionCheck()

    val pci = de.PermissionCheckInner()
}


// How to access this nested classes in java