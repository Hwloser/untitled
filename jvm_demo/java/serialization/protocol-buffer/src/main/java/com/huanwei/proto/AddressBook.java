package com.huanwei.proto;

import com.huanwei.proto.AddressBookProtos.Person;
import com.huanwei.proto.AddressBookProtos.Person.PhoneNumber;
import com.huanwei.proto.AddressBookProtos.Person.PhoneType;

public class AddressBook {

  public static void main(String[] args) {
    Person john = Person.newBuilder()
        .setId(1234)
        .setName("John Doe")
        .setEmail("jdoe@example.com")
        .addPhones(
            PhoneNumber.newBuilder()
                .setNumber("555-4321")
                .setType(Person.PhoneType.HOME)
        ).addPhones(
            Person.PhoneNumber.newBuilder()
                .setNumber("aaaaaa")
                .setType(PhoneType.WORK)
        ).build();
    System.out.println(john);
  }

}
