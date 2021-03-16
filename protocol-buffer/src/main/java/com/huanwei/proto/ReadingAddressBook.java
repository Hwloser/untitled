package com.huanwei.proto;

import com.huanwei.proto.AddressBookProtos.Person;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ReadingAddressBook {

  private static void print(AddressBookProtos.AddressBook addressBook) {
    for (Person person : addressBook.getPeopleList()) {
      System.out.println("Person ID: " + person.getId());
      System.out.println("  Name: " + person.getName());
      if (person.hasEmail()) {
        System.out.println("  E-mail address: " + person.getEmail());
      }

      for (Person.PhoneNumber phoneNumber : person.getPhonesList()) {
        switch (phoneNumber.getType()) {
          case MOBILE:
            System.out.print("  Mobile phone #: ");
            break;
          case HOME:
            System.out.print("  Home phone #: ");
            break;
          case WORK:
            System.out.print("  Work phone #: ");
            break;
        }
        System.out.println(phoneNumber.getNumber());
      }
    }
  }

  public static void main(String[] args) throws IOException {
    File inputFile = null;

    if (inputFile == null) throw new FileNotFoundException("please define input file firstly");
    AddressBookProtos.AddressBook addressBook = AddressBookProtos.AddressBook
        .parseFrom(new FileInputStream(inputFile));

    print(addressBook);
  }
}
