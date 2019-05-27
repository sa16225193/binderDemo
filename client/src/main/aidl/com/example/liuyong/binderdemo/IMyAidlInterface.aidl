// IMyAidlInterface.aidl
package com.example.liuyong.binderdemo;

// Declare any non-default types here with import statements
import com.example.liuyong.binderdemo.Person;

interface IMyAidlInterface {

   void addPerson(in Person person);

   List<Person> getPersonList();
}
